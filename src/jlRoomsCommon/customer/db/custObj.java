package jlRoomsCommon.customer.db;
import java.io.Serializable;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


import jlRoomsCommon._beans.custBean;
import jlRoomsCommon._objects.jlRoomsDbConnIinterface;
import jlRoomsCommon._jlroot.serialsBean;
import jlRoomsCommon._jlroot.serialsObj;
import obj.db.v1.dbMgrInterface;

import sun.jdbc.rowset.*;


public class custObj  implements Serializable {
private jlRoomsDbConnIinterface jlRoomsFactory;
private String webID=null;
custSql custSql = new custSql();
serialsObj serialsObj;
public custObj(){
    serialsObj = new serialsObj();
    custSql = new custSql();
}
 public custObj(String x){
     this();
     this.webID = x;
 }
  public custObj(jlRoomsDbConnIinterface x) {
      this();
      this.jlRoomsFactory = x;
 }
public  void updateCustomerEMail(int id,String eMail){
    updateCustomerEMail(id,eMail,this.jlRoomsFactory);
}
  public  void updateCustomerEMail(int id,String eMail, dbMgrInterface db){
          try {
          db.updateDatabase(custSql.sqlUpdateEMail(this.webID),new Object[]{new Integer(id) ,eMail});

        }catch(Exception e) {
          Logger.getLogger(custObj.class.getName()).log(Level.SEVERE, null, e);
    }
  }
  // **********************************************************************
  public  int updateCustomer(custBean b) {
      return updateCustomer(b,this.jlRoomsFactory,this.jlRoomsFactory);
  }
  public  int updateCustomer(custBean b, dbMgrInterface db,dbMgrInterface root) {
    String s = (b.getCustId() == 0 ? custSql.sqlInsertCustomer(this.webID) : custSql.sqlUpdateCustomer(this.webID));
    try {
      db.updateDatabase(s,getCustomerBean(b));
      if(b.getCustId() == 0) {
         b.setCustId(getLastCust(b.getFirstName(),b.getLastName(),db));
         serialsBean serialsBean = serialsObj.updateCustomerKey(b.getCustId(), root,db,false);
         b.setKeyStr(serialsBean.getKey());
      }
    }catch(Exception e) {
      Logger.getLogger(custObj.class.getName()).log(Level.SEVERE, null, e);
    }
    return b.getCustId();
  }
  
  // **********************************************************************
  public  int getLastCust(String f,String l) {
      return getLastCust(f,l,this.jlRoomsFactory);
  }
  public  int getLastCust(String f,String l,dbMgrInterface db) {
    int i = 0;
    CachedRowSet rs = null;
    try {
      rs = db.getCachedRowSet(custSql.sqlLast(this.webID), new Object[]{f,l});
      while (rs.next()) i = rs.getInt(1);
    }
    catch (Exception e) {
      Logger.getLogger(custObj.class.getName()).log(Level.SEVERE, null, e);
    }finally {
      db.closeCachedRowSet(rs);
    }
    return i;

  }
 // ***************************************************************************************************
  public  List<custBean> custSearch(String str) {
      return custSearch(str,this.jlRoomsFactory);
  }
  public  List<custBean> custSearch(String str,dbMgrInterface db) {
      List<custBean> v = new ArrayList<custBean>();
      //jlRoomsFactory jlRoomsFactory = new jlRoomsFactory();
      CachedRowSet rs = null;
      try {
      rs = db.getCachedRowSet(custSql.sqlCustSearch(this.webID), new Object[]{"%"+str+"%"});
      while (rs.next()) v.add(getCustomerBean(rs));
    }
    catch (Exception e) {
      Logger.getLogger(custObj.class.getName()).log(Level.SEVERE, null, e);
    }
    finally {
      try {
        rs.close();
      }
      catch (Exception e) {}
      
    }


      return v;
  }
  private  String ck(String s) {
    return s == null ? "" : s;
  }
  public  Object[] getCustomerBean(custBean b) {
    if (b.getCustId() == 0) {
      return new Object[] {
          ck(b.getFirstName()),
          ck(b.getLastName()),
          ck(b.getAddr1()),
          ck(b.getAddr2()),
          ck(b.getCity()),
          ck(b.getState()),
          ck(b.getZip()),
          ck(b.getHmPhone()),
          ck(b.getWkPhone()),
          ck(b.getWkPhoneExt()),
          ck(b.getEMail())
      };
    } else {
      return new Object[] {
          ck(b.getFirstName()),
          ck(b.getLastName()),
          ck(b.getAddr1()),
          ck(b.getAddr2()),
          ck(b.getCity()),
          ck(b.getState()),
          ck(b.getZip()),
          ck(b.getHmPhone()),
          ck(b.getWkPhone()),
          ck(b.getWkPhoneExt()),
          ck(b.getEMail()),
          new Integer(b.getCustId())
      };
    }
  }
  public  custBean getCustomerBean(CachedRowSet rs) {
    custBean bean = new custBean();
    try {
      bean.setCustId(rs.getInt(1));
      bean.setFirstName(rs.getString(2));
      bean.setLastName(rs.getString(3));
      bean.setAddr1(rs.getString(4));
      bean.setAddr2(rs.getString(5));
      bean.setCity(rs.getString(6));
      bean.setState(rs.getString(7));

      bean.setZip(rs.getString(8));
      bean.setHmPhone(rs.getString(9));
      bean.setWkPhone(rs.getString(10));
      bean.setWkPhoneExt(rs.getString(11));
      bean.setEMail(rs.getString(12));
      bean.setKeyStr(rs.getString(13));
      
    }
    catch (Exception e) {
      Logger.getLogger(custObj.class.getName()).log(Level.SEVERE, null, e);
    }
    return bean;
  }

 // ***************************************************************************************************
  public  custBean getCustomerBean(int i) {
      return getCustomerBean(new Object[]{new Integer(i)},custSql.sqlCust(this.webID),jlRoomsFactory);
  }
  public  custBean getCustomerBean(int i,dbMgrInterface db) {
      return getCustomerBean(new Object[]{new Integer(i)},custSql.sqlCust(this.webID),db);
  }
  private  custBean getCustomerBean(Object[] ary,String sql,dbMgrInterface db) {
    custBean bean = new custBean();
    CachedRowSet rs = null;
    try {

      rs = db.getCachedRowSet(sql, ary);
      while (rs.next()) bean = getCustomerBean(rs);
    }
    catch (Exception e) {
      Logger.getLogger(custObj.class.getName()).log(Level.SEVERE, null, e);
    }
    finally {
      try {
        rs.close();
      }
      catch (Exception e) {}
      
    }
    return bean;
  }

}
