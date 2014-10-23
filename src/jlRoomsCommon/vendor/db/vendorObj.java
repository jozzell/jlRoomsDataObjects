package jlRoomsCommon.vendor.db;
import java.io.Serializable;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import jlRoomsCommon._beans.vendorBean;
import jlRoomsCommon._objects.jlRoomsDbConnIinterface;
import jlRoomsDO.JlRoomsDataObjects;
import obj.db.v1.dbMgrInterface;

import sun.jdbc.rowset.*;

public class vendorObj  implements Serializable{
    private jlRoomsDbConnIinterface jlRoomsFactory;
    private String WebID=null;
    final private vendorSql vendorSql;
     public vendorObj(){
        this.vendorSql = new vendorSql();
        
    }
  public vendorObj(jlRoomsDbConnIinterface x) {
        this.vendorSql = new vendorSql();
      this.jlRoomsFactory = x;
  }
  public vendorObj(String id) {
        this.vendorSql = new vendorSql();
      this.WebID = id;
  }
  // ------------------------------------- dbMgrInterface obj
  public   int getDefaultVendorID( int id,String key,dbMgrInterface mgr) {
    int i = 0;
    CachedRowSet rs = null;
     try {
       
    
        rs = mgr.getCachedRowSet(vendorSql.sqlGetDefaultID,
                new Object[] {
                    JlRoomsDataObjects.VENDOR_SPONSOR,
                    key,
                    id});
        while (rs.next()){
            i = rs.getInt(1);
        }
      
    } catch(Exception e) {
      Logger.getLogger(vendorObj.class.getName()).log(Level.SEVERE, null, e);
    }finally {
      mgr.closeCachedRowSet(rs);
    }
    return i;
  }
  // ====================================================================
public vendorBean getVendorBean(int id) {
    return getVendorBean(id,jlRoomsFactory);
}
   public vendorBean getVendorBean(int id,dbMgrInterface obj) {
      CachedRowSet rs = null;
     try {
      rs = obj.getCachedRowSet(vendorSql.sqlGetVendor(this.WebID),new Object[] {new Integer(id)});
      while(rs.next()) return getVendorBean(rs);

    } catch(Exception e) {
      Logger.getLogger(vendorObj.class.getName()).log(Level.SEVERE, null, e);
    } finally {
      obj.closeCachedRowSet(rs);
    }
    return null;
   }
   
   
     // ------------------------------------- dbMgrInterface obj
   public vendorBean getWalkinVendorBean() {
       return getWalkinVendorBean(jlRoomsFactory);
   }
    public vendorBean getWalkinVendorBean(dbMgrInterface obj) {
      CachedRowSet rs = null;
     try {
      rs = obj.getCachedRowSet(vendorSql.sqlGetWakingVendor(this.WebID),new Object[] {new Integer(-4)});
      while(rs.next()) return getVendorBean(rs);

    } catch(Exception e) {
      Logger.getLogger(vendorObj.class.getName()).log(Level.SEVERE, null, e);
    } finally {
      obj.closeCachedRowSet(rs);
    }
    return null;
   }
    // ------------------------------------- dbMgrInterface obj jlRoomsFactory
     public List<vendorBean> getVendorList(int type, dbMgrInterface obj) {
        return getVendorBean(vendorSql.sqlGetWakingVendor(this.WebID), new Object[]{new Integer(type)}, obj);
    }
    // ------------------------------------- dbMgrInterface obj jlRoomsFactory
    public List<vendorBean> getVendorBean(String str) {
        return getVendorBean(str, jlRoomsFactory);
    }

    public List<vendorBean> getVendorBean(String str, dbMgrInterface obj) {
        return getVendorBean(vendorSql.sqlSeekAll(this.WebID), new Object[]{"%" + str + "%"}, obj);
    }
    // ------------------------------------- jlRoomsFactory

    public List<vendorBean> getVendorBean(String str, int type) {
        return getVendorBean(str, type, jlRoomsFactory);
    }

    public List<vendorBean> getVendorBean(String str, int type, dbMgrInterface obj) {
        return getVendorBean(vendorSql.sqlSeek(this.WebID)  , new Object[]{"%" + str + "%", new Integer(type)}, obj);
    }
  // ------------------------------------- dbMgrInterface obj
    public  List<vendorBean> getVendorBean(String sql, Object[] obj) {
        return getVendorBean(sql,obj, jlRoomsFactory);
    }
  public  List<vendorBean> getVendorBean(String sql, Object[] obj,dbMgrInterface mgr) {
    List<vendorBean> v = new ArrayList<vendorBean>();
    CachedRowSet rs = null;
    try {
      rs = mgr.getCachedRowSet(sql,obj);
      while (rs.next()) v.add(getVendorBean(rs));
    } catch(Exception e) {
      Logger.getLogger(vendorObj.class.getName()).log(Level.SEVERE, null, e);
    } finally {
      mgr.closeCachedRowSet(rs);
    }
    return v;
  }



  // ------------------------------------- dbMgrInterface obj
  public  int updateVendor( vendorBean bean) {
      return updateVendor(bean,jlRoomsFactory);
  }
  
  public  int updateVendor( vendorBean bean,dbMgrInterface mgr) {
    String sql = bean.getVendorId() == 0 ? vendorSql.sqlInsert(this.WebID) : vendorSql.sqlUpdate(this.WebID);
    CachedRowSet rs = null;
     try {
        mgr.updateDatabase(sql,updateVendorData(bean));
      if (bean.getVendorId() == 0) {
        rs = mgr.getCachedRowSet(vendorSql.sqlGetId(this.WebID),new Object[] {bean.getVendorName()});
        while (rs.next()) bean.setVendorId(rs.getInt(1));
      }
    } catch(Exception e) {
      Logger.getLogger(vendorObj.class.getName()).log(Level.SEVERE, null, e);
    }finally {
      mgr.closeCachedRowSet(rs);
    }
    return bean.getVendorId();
  }

  private  Object[] updateVendorData(vendorBean bean) {
    java.sql.Timestamp ts = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
    return new Object[] {
        bean.getVendorName(),
        bean.getVendor_code() == null ? "" : bean.getVendor_code(),
        new Integer(bean.getVendorInd()),
        new Integer(bean.getVendorType()),
        new Integer(bean.getFlag()),
        bean.getAddr() == null ? "" : bean.getAddr(),
        bean.getAddr2nd() == null ? "" : bean.getAddr2nd(),
        bean.getCity() == null ? "" : bean.getCity(),
        bean.getState() == null ? "" : bean.getState(),
        bean.getZip() == null ? "" : bean.getZip(),
        bean.getZipPlus() == null ? "" : bean.getZipPlus() ,
        bean.getPhone() == null ? "" : bean.getPhone() ,
        bean.getFax() == null ? "" : bean.getFax(),
        bean.getEMail() == null ? "" : bean.getEMail(),
        bean.getPhoneExt()  == null ? "" : bean.getPhoneExt(),
        bean.getVendorId() == 0 ? null : new Integer(bean.getVendorId())
    };
  }
  private  vendorBean getVendorBean(CachedRowSet cr) throws Exception{
    vendorBean bean = new vendorBean();
    try {
      bean.setVendorId(cr.getInt(1));
      bean.setVendorName(cr.getString(2));
      bean.setVendor_code(cr.getString(3));
      bean.setVendorInd(cr.getInt(4));
      bean.setVendorType(cr.getInt(5));
      bean.setFlag(cr.getInt(6));
      bean.setAddr(cr.getString(7));
      bean.setAddr2nd(cr.getString(8));
      bean.setCity(cr.getString(9));
      bean.setState(cr.getString(10));
      bean.setZip(cr.getString(11));
      bean.setZipPlus(cr.getString(12));
      bean.setPhone(cr.getString(13));
      bean.setFax(cr.getString(14));
      bean.setEMail(cr.getString(15));
       bean.setPhoneExt(cr.getString(20));
       bean.setRptDesc(cr.getString(22));
      // rollup 16
      //vendor_first 17
      // region id 18
      // parking 19


      // contact person 21

    } catch(Exception e) {
      throw new Exception(e);
    }
    return bean;
  }
}
