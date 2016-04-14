package jlRoomsCommon.customerRoom.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;
import jlRoomsCommon._beans.blockBean;
import jlRoomsCommon._beans.custBean;

import jlRoomsCommon._beans.custRmBean;
import jlRoomsCommon._beans.roommateBean;
import jlRoomsCommon._beans.sponsorBean;
import jlRoomsCommon._objects.jlRoomsDbConnIinterface;
import jlRoomsCommon.customerPayment.db.customerPaymentObj;
import jlRoomsCommon.JlRoomsDataObjects;
import jlRoomsCommon.vendorObjTypesENum;
import obj.db.v1.dbMgrInterface;


import sun.jdbc.rowset.*;

/**
 * Title: Room Tracking Web Applicaion (rtwa) Description: Copyright: Copyright
 * (c) 2003 Company: Jozzell, llc
 *
 * @author Lloyd Means
 * @version 1.0
 */
public class customerRmObj  implements Serializable{
    private customerPaymentObj customerPaymentObj;
    private jlRoomsDbConnIinterface jlRoomsFactory;
    private String webID = null;
    roommateObj roommateObj;
    roommateSql roommateSql;
    customerRmSql customerRmSql;
    public customerRmObj(){
        customerRmSql = new customerRmSql();
        roommateSql = new roommateSql();
    }
    public customerRmObj(String x) {
        customerRmSql = new customerRmSql();
        this.webID = x;
        roommateObj = new roommateObj(x);
        customerPaymentObj = new customerPaymentObj(x);
    }

    public customerRmObj(jlRoomsDbConnIinterface x) {
        customerPaymentObj = new customerPaymentObj(x);
        this.jlRoomsFactory = x;
    }
    // *************************************************  
   // custRmBean = customerRmObj.getCustRoomBean(blockBean, custBean,JlRoomsDataObjects.getProcFee(this.sponsorBean,pickListType));
    public  custRmBean getCustRoomBean333(blockBean bean, custBean cust,sponsorBean spon) {
        double amt = vendorObjTypesENum.DEFAULT.getENUM(bean.getVendorType()).getProcFee(spon);
        return getCustRoomBean(bean, cust,amt);
    }
    public custRmBean getCustRoomBean(blockBean bean, custBean cust,double fee) {
        
        custRmBean b = new custRmBean();
        b.setCustRoomType(bean.getVendorType());
        
        b.setEffDate(bean.getEffDate());
        b.setEndDate(bean.getEndDate());
        b.setSponsor(bean.getSponsor());
        b.setVendor(bean.getVendor());
        b.setLookupId(bean.getLookupId());
        b.setLookupDesc(bean.getLookupDesc());
        b.setCustId(cust.getCustId());
        b.setBlockId(bean.getBlockId());
        b.setVendorType(bean.getVendorType());
        b.setVendorId(bean.getVendorId());
        b.setRmCost(bean.getRmCost());
        b.setFltId(bean.getFltId());
        b.setBlockHotelFee(bean.getHotelFee() != 0 ? bean.getHotelFee():fee);
        b.setSponsorHotelId(bean.getSponsorHotelId());
        //if (b.getBlockHotelFee() == 0) custRmBean.setBlockHotelFee(fee);
        b.setBlockProcessFee(bean.getNoFeeAppyied() == 1 ? .0001 : bean.getProcessFee());
        b.setBlockServiceCharge(bean.getServiceCharge());
        return b;
    }
    
    // *************************************************  
    
    public custRmBean getCustomerRoom(int rm) {
        return getCustomerRoom(rm,this.jlRoomsFactory);
    }
    public custRmBean getCustomerRoom(int rm,dbMgrInterface db) {
        return getCustomerRoom(customerRmSql.sqlCustomerRoom(this.webID),
                new Object[]{new Integer(rm)},db);
    }
    // *************************************************  dbMgrInterface db
    public custRmBean getCustomerRoom(String sql, Object ary[]) {
        return getCustomerRoom(sql,ary,this.jlRoomsFactory);
    }
    public custRmBean getCustomerRoom(String sql, Object ary[],dbMgrInterface db) {
        custRmBean bean = null;
        CachedRowSet rs = null;
        try {
            rs = db.getCachedRowSet(sql, ary);
            while (rs.next()) {
                bean = getCustomerRoom(rs);
            }

        } catch (Exception e) {
            Logger.getLogger(customerRmObj.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
            }

        }
        return bean;
    }

    private custRmBean getCustomerRoom(CachedRowSet rs) {
        custRmBean bean = new custRmBean();
        try {
            bean.setCustRoomId(rs.getInt(1));
            bean.setCustId(rs.getInt(2));
            bean.setBlockId(rs.getInt(3));
            bean.setEffDate(rs.getDate(4));
            bean.setEndDate(rs.getDate(5));
            bean.setRmCost(rs.getDouble(6));
            bean.setSponsor(rs.getInt(7));
            bean.setCustRoomCnt(rs.getDouble(8));
            bean.setRoomComment(rs.getString(9));
            bean.setDue(rs.getDate(10));
            bean.setDueComment(rs.getString(11));
            bean.setCustRoomType(rs.getInt(12));
            bean.setRoomPaid(rs.getInt(13));
            // *******************

            bean.setRoommateSplit(rs.getInt(14));
            bean.setVendor(rs.getString(15));
            
            bean.setStatus(rs.getString(17));
            bean.setRmTypeDesc(rs.getString(16));
            
            bean.setVendorType(rs.getInt(18));
            bean.setRoommateId(rs.getInt(19));
            bean.setTotalCost(rs.getDouble(20));
            bean.setLookupId(rs.getInt(21));
            bean.setVendorId(rs.getInt(22));
            bean.setFltId(rs.getInt(23));
            bean.setSponsorHotelId(rs.getInt(24));
            bean.setRmDesc(rs.getString(25));

            //bean.setBlockNoFee(rs.getInt(25));
            bean.setBlockServiceCharge(rs.getDouble(26));
            //bean.setBlockProcessFee(rs.getDouble(27));
            bean.setBlockProcessFee(rs.getInt(25) == 1 ? .0001 : rs.getDouble(27));
            bean.setBlockHotelFee(rs.getDouble(28));
            bean.setBlockCostCnt(rs.getDouble(29));
            bean.setLookupDesc(rs.getString(30));
            bean.setDays(rs.getInt(31));
            bean.setDspStr1(rs.getString(32));
            bean.setTicket_info(rs.getString(33));
           
        } catch (Exception e) {
            Logger.getLogger(customerRmObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return bean;
    }
    // *************************************************  dbMgrInterface db
    
    
    public void sqlDeleteCustomerRoom_RmId(int cust, int room,dbMgrInterface db) {
        Object obj[] = new Object[]{
            new Integer(cust), new Integer(room)};
        try {
            db.updateDatabase(customerRmSql.sqlDeleteCustomerRoom_RmId(this.webID), obj);
        } catch (Exception ex) {
            Logger.getLogger(customerRmObj.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            
            
    public void delete_roommate(int cust, int room, int spon) {
        delete_roommate(cust,room,spon,this.jlRoomsFactory);
    }
    public void delete_roommate(int cust, int room, int spon,dbMgrInterface db) {
        Object obj[] = new Object[]{
            new Integer(spon), new Integer(room), new Integer(cust)};
        try {
            db.updateDatabase(roommateSql.sqlDeleteCust_01(this.webID), obj);
            db.updateDatabase(roommateSql.sqlDeleteCust_02(this.webID), obj);
        } catch (Exception e) {
            Logger.getLogger(customerRmObj.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    // 
    public int insertTblRoommateCust(int id, int spon, int cust, int room, int vendor, int sys) {
        return insertTblRoommateCust(id,spon,cust,room,vendor,sys,this.jlRoomsFactory);
    }
    public int insertTblRoommateCust(int id, int spon, int cust, int room, int vendor, int sys,dbMgrInterface db) {
        Object obj[] = new Object[]{
            new Integer(id),
            new Integer(spon),
            new Integer(cust),
            new Integer(room),
            new Integer(vendor),
            new Integer(sys)
        };
        try {
            CachedRowSet rs = db.getCachedRowSet(roommateSql.sqlGetRoommateCust_RmId(this.webID), obj);
            while (rs.next()) {
                if (rs.getInt(1) > 0) {
                    id = -1;
                }
            }
            if (id > 0) {

                db.updateDatabase(roommateSql.sqlInsertRoommateCust(this.webID), obj);
            }

        } catch (Exception e) {
            Logger.getLogger(customerRmObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return id;
    }
// *************************************************
    
   
// *************************************************
    public int insertTblRoommate(int cust, int hotel, int spon, int vendor, int sys) {
        return insertTblRoommate(cust,hotel,spon,vendor,sys,this.jlRoomsFactory);
    }
    public int insertTblRoommate(int cust, int hotel, int spon, int vendor, int sys,dbMgrInterface db) {

        //jlRoomsFactory.updateDatabase
        CachedRowSet rs;
        int id = 0;
        try {
            /*
            rs = db.getCachedRowSet(roommateSql.sqlCheckRoommate(this.webID), new Object[]{
                new Integer(cust),
                new Integer(spon),
                new Integer(hotel)});
            while (rs.next()) {
                if (rs.getInt(1) > 0) {
                    id = rs.getInt(1);
                }
            }
            */
            id = roommateObj.getRoommateCust_RmId( cust,  hotel,  spon, db);
            
            Object obj[] = new Object[]{
                new Integer(cust),
                new Integer(spon),
                new Integer(hotel),
                new Integer(vendor),
                new Integer(sys)};
                
            if (id <= 0) {
                db.updateDatabase(roommateSql.sqlInsertRoommate(this.webID), obj);
            }
            /*
            rs = db.getCachedRowSet(roommateSql.sql_GetRoomateId(this.webID), new Object[]{
                new Integer(cust),
                new Integer(spon),
                new Integer(hotel)});
            while (rs.next()) {
                id = rs.getInt(1);
            }
            */
            id = roommateObj.insertRoommate( cust,  hotel,  spon,  vendor,  sys, db);
            if (id > 0) {
                insertTblRoommateCust(id, spon, cust, hotel, vendor, sys,db);
            }
        } catch (Exception e) {
            Logger.getLogger(customerRmObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return id;
    }
// *************************************************  
    public List<roommateBean> getRoommatesList(int cust, int spon, int hotel) {
        return getRoommatesList(cust,spon,hotel,this.jlRoomsFactory);
    }
    public List<roommateBean> getRoommatesList(int cust, int spon, int hotel,dbMgrInterface db) {
        List<roommateBean> v = new ArrayList<roommateBean>();
        CachedRowSet rs;
        try {
            rs = db.getCachedRowSet(customerRmSql.sqlGetRoommateList(this.webID),
                    new Object[]{
                new Integer(cust),
                new Integer(spon),
                new Integer(hotel)
            });
            while (rs.next()) {
                v.add(getRoomateBean(rs, false));
            }
        } catch (Exception e) {
            Logger.getLogger(customerRmObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return v;
    }
// *************************************************  
    public List<roommateBean> getRoommate(int cust, int spon) {
        return getRoommate(cust,spon,this.jlRoomsFactory);
    }
    public List<roommateBean> getRoommate(int cust, int spon,dbMgrInterface db) {
        List<roommateBean> ary = new ArrayList<roommateBean>();
        CachedRowSet rs = null;

        try {
            rs = db.getCachedRowSet(customerRmSql.sqlGetRoommate(this.webID),
                    new Object[]{
                new Integer(cust),
                new Integer(spon)
            });
            while (rs.next()) {
                ary.add(getRoomateBean(rs, false));
            }
        } catch (Exception e) {
            Logger.getLogger(customerRmObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return ary;
    }

// *************************************************  
    public List<roommateBean> getRoommateRpt(int spon) {
        return getRoommateRpt(spon, this.jlRoomsFactory);
    }

    public List<roommateBean> getRoommateRpt(int spon, dbMgrInterface db) {
        List<roommateBean> ary = new ArrayList<roommateBean>();
        CachedRowSet rs = null;

        try {
            rs = db.getCachedRowSet(customerRmSql.sqlGetRoommateRpt(this.webID),
                    new Object[]{
                new Integer(spon)
            });
            while (rs.next()) {
                ary.add(getRoomateBean(rs, false));
            }
        } catch (Exception e) {
            Logger.getLogger(customerRmObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return ary;
    }

// *************************************************  
    public List<roommateBean> getRoommateRptVendor(int spon, int ven) {
        return getRoommateRptVendor(spon, ven, this.jlRoomsFactory);
    }

    public List<roommateBean> getRoommateRptVendor(int spon, int ven, dbMgrInterface db) {
        List<roommateBean> ary = new ArrayList<roommateBean>();
        CachedRowSet rs = null;

        try {
            rs = db.getCachedRowSet(customerRmSql.sqlGetRoommateRptVendor(this.webID),
                    new Object[]{
                new Integer(spon),
                new Integer(ven)
            });
            while (rs.next()) {
                ary.add(getRoomateBean(rs, false));
            }
        } catch (Exception e) {
            Logger.getLogger(customerRmObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return ary;
    }

    public roommateBean getRoomateBean(CachedRowSet rs, boolean desc) {
        roommateBean bean = new roommateBean();
        try {
            bean.setCustId(rs.getInt(1));
            bean.setName(rs.getString(2)+" "+rs.getString(3));
            bean.setRmId(rs.getInt(4));

            if (desc) {
                bean.setDesc(rs.getString(5));
                bean.setSysId(rs.getInt(6));
            } else {
                bean.setPrimary(rs.getInt(5) == rs.getInt(1));
                bean.setHotelId(rs.getInt(6));
                bean.setVendorName(rs.getString(7));
                bean.setPrimId(rs.getInt(5));
                // 8 primary name
                bean.setAddr1(rs.getString(9));
                bean.setCity(rs.getString(10));
                bean.setCustRoomId(rs.getInt(11));
               
            }
        } catch (Exception e) {
            Logger.getLogger(customerRmObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return bean;
    }
// *************************************************  

    public List<custRmBean> custRmBooking(int cust, int spon) {
        return custRmBooking(cust, spon, this.jlRoomsFactory);
    }

    public List<custRmBean> custRmBooking(int cust, int spon, dbMgrInterface db) {
        List<custRmBean> ary = new ArrayList<custRmBean>();
        //jlRoomsFactory jlRoomsFactory = new jlRoomsFactory();
        CachedRowSet rs = null;
        try {
            rs = db.getCachedRowSet(customerRmSql.sqlCustomerBooking(this.webID),
                    new Object[]{
                new Integer(cust),
                new Integer(spon)
            });
            while (rs.next()) {
                ary.add(getCustomerRoom(rs));
            }
        } catch (Exception e) {
            Logger.getLogger(customerRmObj.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
            }

        }
        return ary;
    }
// *************************************************  

    public void delete(int i) {
        delete(i, this.jlRoomsFactory);
    }

    public void delete(int i, dbMgrInterface db) {
        Object obj[] = new Object[]{new Integer(i)};
        try {
            db.updateDatabase(customerRmSql.sqlDelete_booking_payment_1(this.webID), obj);
            db.updateDatabase(customerRmSql.sqlDelete_booking_payment_2(this.webID), obj);
            db.updateDatabase(customerRmSql.sqlDelete_booking(this.webID), obj);
        } catch (Exception ex) {
            Logger.getLogger(customerRmObj.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    // *************************************************  

    public int getCustRmId(custRmBean bean) {
        return getCustRmId(bean, this.jlRoomsFactory);
    }

    public int getCustRmId(custRmBean bean, dbMgrInterface db) {
        CachedRowSet rs = null;
        int i = 0;
        try {
            rs = db.getCachedRowSet(customerRmSql.sqlCurrCustRm(this.webID),
                    new Object[]{
                new Integer(bean.getCustId()),
                new Integer(bean.getBlockId()),
                bean.getDbTimeStamp()});
            while (rs.next()) {
                i = rs.getInt(1);
            }
        } catch (Exception e) {
            Logger.getLogger(customerRmObj.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            db.closeCachedRowSet(rs);
        }
        return i;
    }
    // *************************************************  

    public void updatePrimaryRoommate(int cust, int rm_id) {
        updatePrimaryRoommate(cust, rm_id, this.jlRoomsFactory);
    }

    public void updatePrimaryRoommate(int cust, int rm_id, dbMgrInterface db) {
        try {
            db.updateDatabase(roommateSql.sqlUpdateRoomatePrimary(this.webID),
                    new Object[]{
                new Integer(cust),
                new Integer(rm_id)
            });


        } catch (Exception e) {
            Logger.getLogger(customerRmObj.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    // *************************************************

    public int update(custRmBean bean) {
        return update(bean, this.jlRoomsFactory);
    }

    public int update(custRmBean bean, dbMgrInterface db) {
        bean.setDbTimeStamp((new JlRoomsDataObjects()).getTimeStampString());
        String sql = bean.getCustRoomId() == 0 ? customerRmSql.sqlInsertCustRoom(this.webID) : customerRmSql.sqlUpdateCustRoom(this.webID);
        try {
            db.updateDatabase(sql, getCutomerRoom(bean));
            if (bean.getCustRoomId() == 0) {
                bean.setCustRoomId(getCustRmId(bean,db));
            }
     
      // 'Skip Proc Fee'
      if (bean.isApplyProcFee())  customerPaymentObj.addDefaultPayment(bean,bean.getLoginId(),db);
           

        } catch (Exception e) {
            Logger.getLogger(customerRmObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return bean.getCustRoomId();
    }

    private java.sql.Date getSqlDate(java.util.Date d) {
        if (d == null) {
            return new java.sql.Date(0);
        }
        return new java.sql.Date(d.getTime());
    }

    private Object[] getCutomerRoom(custRmBean bean) {
        if (bean.getCustRoomId() == 0) {
            return new Object[]{
                new Integer(bean.getCustId()),
                new Integer(bean.getBlockId()),
                getSqlDate(bean.getEffDate()),
                getSqlDate(bean.getEndDate()),
                new Double(bean.getRmCost()),
                new Integer(bean.getSponsor()),
                new Double(bean.getCustRoomCnt()),
                bean.getRoomComment() == null ? "" : bean.getRoomComment(),
                getSqlDate(bean.getDue()),
                bean.getDueComment() == null ? "" : bean.getDueComment(),
                new Integer(bean.getCustRoomType()),
                new Integer(bean.getRoomPaid()),
                new Integer(bean.getRoommateSplit()),
                new Integer(bean.getRoommateId()),
                new Double(bean.getTotalCost()),
                new Integer(bean.getDays()),
                new Integer(bean.getLookupId()),
                new Integer(bean.getVendorId()),
                new Integer(bean.getFltId()),
                bean.getDbTimeStamp(),
                new Double(bean.getBlockCostCnt()),
                bean.getTicket_info()==null ? "": bean.getTicket_info(),
            };

        } else {
            return new Object[]{
                new Integer(bean.getCustId()),
                new Integer(bean.getBlockId()),
                getSqlDate(bean.getEffDate()),
                getSqlDate(bean.getEndDate()),
                new Double(bean.getRmCost()),
                new Integer(bean.getSponsor()),
                new Double(bean.getCustRoomCnt()),
                bean.getRoomComment() == null ? "" : bean.getRoomComment(),
                getSqlDate(bean.getDue()),
                bean.getDueComment() == null ? "" : bean.getDueComment(),
                new Integer(bean.getCustRoomType()),
                new Integer(bean.getRoomPaid()),
                new Integer(bean.getRoommateSplit()),
                new Integer(bean.getRoommateId()),
                new Double(bean.getTotalCost()),
                new Integer(bean.getDays()),
                new Integer(bean.getLookupId()),
                new Double(bean.getBlockCostCnt()),
                 bean.getTicket_info()==null ? "": bean.getTicket_info(),
                new Integer(bean.getCustRoomId())
            };
        }
    }
}
