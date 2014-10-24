package jlRoomsCommon.block.db;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jlRoomsCommon._beans.blockBean;
import jlRoomsCommon._beans.brwBookingSubBean;
import jlRoomsCommon._objects.jlRoomsDbConnIinterface;
import jlRoomsDO.JlRoomsDataObjects;
import obj.db.v1.dbMgrInterface;
import obj.reusableObj;
import sun.jdbc.rowset.*;

/**
 * Title: Room Tracking Web Applicaion (rtwa) Description: Copyright: Copyright
 * (c) 2003 Company: Jozzell, llc
 *
 * @author Lloyd Means
 * @version 1.0
 */
public class blockObj implements Serializable{
    private jlRoomsDbConnIinterface jlRoomsFactory;
    private String webID=null;
    blockSql blockSql;
     public blockObj(){
         blockSql = new blockSql();
     }
    public blockObj(jlRoomsDbConnIinterface x) {
        this();
        this.jlRoomsFactory = x;
    }
   public blockObj(String x) {
       this();
        this.webID = x;
    }

    public blockBean getBlock(int blk) {
        Object ary[] = new Object[1];
        ary[0] = new Integer(blk);
        return getBlock(ary);
    }

    public String getListDisp(blockBean blockBean) {
        return blockBean.getLookupDesc()
                + (blockBean.getBlockComment() == null ? "" : " " + blockBean.getBlockComment())
                + (blockBean.getRmCost() == 0 ? "" : " " + jlRoomsFactory.getDollarFormat(blockBean.getRmCost()));
    }

    public String getListDispBooking(blockBean blockBean) {
        return blockBean.getVendor() + ": " + blockBean.getLookupDesc() + " "
                + (blockBean.getRmCost() == 0 ? "" : " @ " + jlRoomsFactory.getDollarFormat(blockBean.getRmCost()))
                + (blockBean.getBlockComment() == null ? "" : " " + blockBean.getBlockComment());
    }

   // ******************************************************************************************************************
    
    public List<blockBean> getBlockSponsorListAvl(int sponsor, int vendorType) {
        return getBlockVector(blockSql.sqlAvlBooking(this.webID), new Object[]{new Integer(sponsor), new Integer(vendorType)});
    }
     public List<blockBean> getBlockSponsorListAvl(int sponsor, int vendorType,dbMgrInterface db) {
        return getBlockVector(blockSql.sqlAvlBooking(this.webID), new Object[]{new Integer(sponsor), new Integer(vendorType)},db);
    }
    // ******************************************************************************************************************
    public List<blockBean> getBlockSponsorList(int id, int roll) {
        return getBlockVector(blockSql.sqlBrwBlockSponsorHotelId(this.webID), new Object[]{new Integer(id), new Integer(roll)});
    }
    public List<blockBean> getBlockSponsorList(int id, int roll,dbMgrInterface db) {
        return getBlockVector(blockSql.sqlBrwBlockSponsorHotelId(this.webID), new Object[]{new Integer(id), new Integer(roll)},db);
    }
// *************************************************************************
    public List<blockBean> getBlockSwitchList(int block) {
        return getBlockVector(blockSql.sqlBlockSwitch(this.webID), new Object[]{new Integer(block)});
    }
    public List<blockBean> getBlockSwitchList(int block,dbMgrInterface db) {
        return getBlockVector(blockSql.sqlBlockSwitch(this.webID), new Object[]{new Integer(block)},db);
    }
    
    
// *************************************************************************
    private List<blockBean> getBlockVector(String sql, Object[] obj) {
        return getBlockVector(sql,obj,this.jlRoomsFactory);
    }
    private List<blockBean> getBlockVector(String sql, Object[] obj,dbMgrInterface db) {
        //Vector v = new Vector();
        List<blockBean> v = new ArrayList<blockBean>();
        CachedRowSet rs = null;
        try {
            rs = db.getCachedRowSet(sql, obj);
            while (rs.next()) {
                v.add(getBlock(rs));
            }
        } catch (Exception e) {
            Logger.getLogger(blockObj.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            db.closeCachedRowSet(rs);
        }

        return v;
    }
// *************************************************************************
    private int getBlockId(blockBean b,dbMgrInterface db) {
        int i = -1;
        CachedRowSet rs = null;
        try {
            rs = db.getCachedRowSet(blockSql.sqlGetBlockId(this.webID), new Object[]{
                        b.getDB_TIMESTAMP(),
                        new Integer(b.getSponsor()),
                        new Integer(b.getLookupId()),
                        new Integer(b.getSponsorHotelId())
                    });
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            Logger.getLogger(blockObj.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            db.closeCachedRowSet(rs);
        }

        return i;
    }
    // *************************************************************************
public int updateBlock(blockBean b) {
    return updateBlock(b,jlRoomsFactory);
}
    public int updateBlock(blockBean b,dbMgrInterface db) {
        
        b.setDB_TIMESTAMP((new JlRoomsDataObjects()).getTimeStampString());
        Object obj[];
        String sql ;
        try {
            if (b.getBlockId() == 0) {
                sql = blockSql.sqlInsertBlock(this.webID);
                obj = new Object[]{
                    new Integer(b.getSponsorHotelId()),
                    new Integer(b.getLookupId()),
                    new Double(b.getRmCnt()),
                    new Double(b.getRmCost()),
                    b.getBlockComment() == null ? "" : b.getBlockComment(),
                    new Integer(b.getFlagInd()),
                    new Integer(b.getSponsor()),
                    new Integer(b.getFltId()),
                    b.getDB_TIMESTAMP()
                };

            } else {
                sql = blockSql.sqlUpdateBlock(this.webID);
                obj = new Object[]{
                    new Double(b.getRmCnt()),
                    new Double(b.getRmCost()),
                    b.getBlockComment(),
                    new Integer(b.getFlagInd()),
                    //new Integer(b.getFltId()),
                    new Integer(b.getBlockId())
                };

            }
            db.updateDatabase(sql, obj);
            if (b.getBlockId() == 0) {
                return getBlockId(b,db);
            }
        } catch (Exception e) {
            Logger.getLogger(blockObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return b.getBlockId();
    }

    private blockBean getBlock(CachedRowSet rs) {
        blockBean bean = new blockBean(true);
        try {
            bean.setBlockId(rs.getInt(1));
            bean.setSponsorHotelId(rs.getInt(2));
            bean.setLookupId(rs.getInt(3));

            bean.setRmCnt(rs.getDouble(4));
            bean.setRmCost(rs.getDouble(5));
            bean.setBlockComment(rs.getString(6));

            bean.setEffDate(rs.getDate(7));
            bean.setEndDate(rs.getDate(8));
            bean.setFlagInd(rs.getInt(9));
            bean.setSponsor(rs.getInt(10));
            bean.setLookupDesc(rs.getString(11));
            bean.setVendor(rs.getString(12));
            bean.setCity(rs.getString(13));
            bean.setVendorId(rs.getInt(14));
            bean.setFltId(rs.getInt(15));
            bean.setVendorType(rs.getInt(16));

            bean.setNoFeeAppyied(rs.getInt(17));
            bean.setServiceCharge(rs.getDouble(18));
            bean.setProcessFee(rs.getDouble(19));
            bean.setHotelFee(rs.getDouble(20));
            bean.setDspStr1(rs.getString(21));
            bean.setBookCnt(rs.getDouble(22));

        } catch (Exception e) {
            Logger.getLogger(blockObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return bean;
    }
    // *************************************************************************
    public blockBean getBlock(Object[] ary) {
        return getBlock(ary,this.jlRoomsFactory);
    }
    public blockBean getBlock(Object[] ary,dbMgrInterface db) {
        blockBean bean = null;
        CachedRowSet rs = null;
        try {
            rs = db.getCachedRowSet(blockSql.sqlBlock(this.webID), ary);
            while (rs.next()) {
                bean = getBlock(rs);
            }
        } catch (Exception e) {
            Logger.getLogger(blockObj.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            db.closeCachedRowSet(rs);
        }
        if (bean == null) {
            return new blockBean();
        }
        return bean;
    }
// *************************************************************************
    private  double grabBookingCnt(int block,Hashtable hTable) {
        try {
            return (new reusableObj()).round(((Double) hTable.get(new Integer(block))).doubleValue(), 1);

        } catch (Exception e) {
            return 0.0;
        }
    }
// *************************************************************************
    
    private  Hashtable  grabBookingCnt2nd(int id,dbMgrInterface db) {
        Hashtable hTable = new Hashtable();
        //Collection ary = new ArrayList();
        CachedRowSet rs = null;
        //jlRoomsFactory jlRoomsFactory = new jlRoomsFactory();
        try {
            rs = db.getCachedRowSet(blockSql.sqlBrwBlockSubCnt_V3(this.webID),
                    new Object[]{new Integer(id)});
            
            while (rs.next()) {
                hTable.put(new Integer(rs.getInt(2)), new Double(rs.getDouble(1)));
            }
        } catch (Exception e) {
            Logger.getLogger(blockObj.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            db.closeCachedRowSet(rs);
        }
        return hTable;

    }
 // *************************************************************************
    public List<brwBookingSubBean> sqlBrwBlockSub_XXXX(int id) {
        
        return sqlBrwBlockSub_XXXX(id,this.jlRoomsFactory);
    }
    public List<brwBookingSubBean> sqlBrwBlockSub_XXXX( int id,dbMgrInterface db) {
        Hashtable hTable = grabBookingCnt2nd(id,db);
        List<brwBookingSubBean> ary = new ArrayList<brwBookingSubBean>();
        CachedRowSet rs = null;
        try {
            rs = db.getCachedRowSet(blockSql.sqlBrwBlockSub_XXXX(this.webID), new Object[]{ new Integer(id)});
            while (rs.next()) {
                ary.add(new jlRoomsCommon._beans.brwBookingSubBean(
                        rs.getString(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDouble(5),
                        rs.getString(6),
                        rs.getDouble(7),
                        rs.getInt(8),
                        rs.getInt(9), grabBookingCnt(rs.getInt(2),hTable)));
            }
        } catch (Exception e) {
            Logger.getLogger(blockObj.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (rs !=null) rs.close();
            } catch (Exception e) {
            }
            
        }
        
        return ary;
    }
    // *************************************************************************
    
    public List<blockBean>   getBlockEvents(int id) {
        return getBlockEvents(id,this.jlRoomsFactory);
    }
    public List<blockBean>  getBlockEvents(int id,dbMgrInterface db) {
        List<blockBean>  ary = new ArrayList();
        CachedRowSet rs = null;
        //jlRoomsFactory jlRoomsFactory = new jlRoomsFactory();
        try {
            rs = db.getCachedRowSet(blockSql.sqlBlockEvent(this.webID), new Object[]{new Integer(id)});
            while (rs.next()) {
                ary.add(getBlock(rs));
            }
        } catch (Exception e) {
            Logger.getLogger(blockObj.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            db.closeCachedRowSet(rs);
        }
        return ary;
    }
}
