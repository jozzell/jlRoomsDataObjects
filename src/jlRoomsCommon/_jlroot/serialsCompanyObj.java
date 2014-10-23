/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsCommon._jlroot;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import jlRoomsCommon._beans.appCustPaymentMethond;
import jlRoomsCommon._beans.custBean;
import jlRoomsCommon._beans.loginBean;
import jlRoomsCommon._beans.vendorBean;
//import  jlRoomsCommon._jlroot.serialsObj.checkDupCompany;
import jlRoomsDO.JlRoomsDataObjects;
import obj.db.v1.dbMgrInterface;
import sun.jdbc.rowset.CachedRowSet;

/**
 *
 * @author lmeans
 */
public class serialsCompanyObj {
    serialsSql serialsSql;
    serialsObj serialsObj;
    public serialsCompanyObj(){
        serialsSql = new serialsSql();
        serialsObj = new serialsObj();
    }
    public  void updateCustomerInfo(custBean b ,dbMgrInterface db,boolean ok){
        try {
            db.updateDatabase(serialsSql.sqlUpdateCustomer, new Object[]{
                    ck(b.getFirstName()), 
                    ck(b.getLastName()), 
                    ck(b.getPassWord()),
                    b.getAccessLevel(),
                    b.getCustId()
                });
        } catch (Exception ex) {
            Logger.getLogger(serialsCompanyObj.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // =====================================================================
    public  void setDefaultPaymentMethod(String key,dbMgrInterface db,boolean ok){
        setPaymentMethod(key,3,appCustPaymentMethond.JLROOMS_TRYBUY,db, ok);
    }
    public  void setPaymentMethod(String key,int day,int type,dbMgrInterface db,boolean ok){
         try {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, day);
            cal.set(Calendar.MILLISECOND, 0);
            
            Date d = new Date(cal.getTime().getTime());
            
            db.updateDatabase(serialsSql.sqlCustPaymentMethodInsert, new Object[]{
                new Integer(type),
                d,
                key
            });
        } catch (Exception ex) {
            Logger.getLogger(serialsObj.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public  appCustPaymentMethond getAppCustPaymentMethond(String key,dbMgrInterface db,boolean ok){
        appCustPaymentMethond bean = null;
        CachedRowSet rs=null;
         try {
             
             rs = db.getCachedRowSet(
                    serialsSql.sqlCustPaymentMethodSelect,
                    new Object[]{
                        key
            });
             while(rs.next()){
                  bean = new appCustPaymentMethond();
                   bean.setCntr_id(rs.getInt(1));
                   bean.setCntr_type(rs.getInt(2));
                   bean.setCntr(rs.getInt(3));
                   bean.setDate(rs.getString(4));
             }
         } catch (Exception ex) {
             Logger.getLogger(serialsCompanyObj.class.getName()).log(Level.SEVERE, null, ex);
         } finally {
             db.closeCachedRowSet(rs);
         }
         if (bean == null){
             setDefaultPaymentMethod(key,db,true);
             bean = new appCustPaymentMethond();
             bean.setCntr(3);
             bean.setCntr_type(appCustPaymentMethond.JLROOMS_TRYBUY);
             bean.setDate("Payment Verification Method Needed");
         }
        return bean;
    }
    public  custBean getCustEMailBean(String email,dbMgrInterface db,boolean ok){
        custBean b = null; //new custBean();
        CachedRowSet rs=null;
         try {
             
             rs = db.getCachedRowSet(
                    serialsSql.sqlSelectCustomerEMail,
                    new Object[]{
                        email
            });
             while(rs.next()){
                 b = getCustBean(rs);
             }
         } catch (Exception ex) {
             Logger.getLogger(serialsCompanyObj.class.getName()).log(Level.SEVERE, null, ex);
         } finally {
             db.closeCachedRowSet(rs);
         }
        return b;
    }
    public  custBean getCustBean(String user,String key,dbMgrInterface db,boolean ok){
        custBean b = null; //new custBean();
        CachedRowSet rs=null;
         try {
             
             rs = db.getCachedRowSet(
                    serialsSql.sqlSelectCustomer,
                    new Object[]{
                        key.toUpperCase(),
                        user.toLowerCase()
            });
             while(rs.next()){
                 b = getCustBean(rs);
             }
         } catch (Exception ex) {
             Logger.getLogger(serialsCompanyObj.class.getName()).log(Level.SEVERE, null, ex);
         } finally {
             db.closeCachedRowSet(rs);
         }
        return b;
    }
    private  custBean getCustBean(CachedRowSet rs) throws SQLException{
        
                 custBean b = new custBean();
                  b.setCustId(rs.getInt(1));//CUST_ID,
                  b.setEmailKey(rs.getString(2));//email_key, 
                  b.setKeyStr(rs.getString(3));//keyStr
                  b.setFirstName(rs.getString(4));       //,FIRST_NAME, 
                  b.setLastName(rs.getString(5));//        LAST_NAME, 
                  b.setVendorID((rs.getInt(6)));//         vendor_id, 
                  b.setUserName((rs.getString(7)));//        user_name, 
                  b.setPassWord(rs.getString(8));//        user_pass, 
                  b.setAccessLevel(rs.getInt(9));//       access_lvl
                  b.setEMail(rs.getString(10));
        return b;
    }
    
    
     public  serialsBean updateCompanyKey(int i, dbMgrInterface db,boolean ok){
         serialsBean serialsBean = getCompanyKey(i, db);
         try {
            db.updateDatabase(serialsSql.sqlUpdateComapnyKey, new Object[]{
                serialsBean.getKey(),
                serialsBean.getEmailKey(), 
                new Integer(i)
            });
        } catch (Exception ex) {
            Logger.getLogger(serialsObj.class.getName()).log(Level.SEVERE, null, ex);
        }
         return serialsBean;
    }
     public  void updateCompanyBean(vendorBean b,dbMgrInterface db,boolean ok){
        try {
            db.updateDatabase(serialsSql.sqlUpdateCompany, newComapnyBeanUpdate(b));
        } catch (Exception ex) {
            Logger.getLogger(serialsCompanyObj.class.getName()).log(Level.SEVERE, null, ex);
        }
         
     }
    public  serialsBean newCompany(int dbID,vendorBean b,loginBean l,dbMgrInterface db,boolean ok){
        
        //b.setEMail(l.getEmail().trim().toLowerCase());
        serialsBean serialsBean =  null;
        int i = serialsObj.checkDupCompany(b.getEMail(),db,true);
        try {
            
            if (i > 0) return null;
            Object[] obj = newComapnyBean(b,dbID);
            db.updateDatabase(serialsSql.sqlInsertCompany, obj);
            i = serialsObj.checkDupCompany(b.getEMail(),db,true);
            if (i <= 0) return null;
            serialsBean = updateCompanyKey(i,db,ok);
            if (serialsBean == null) return serialsBean;
            serialsBean.setId(i);
            b.setSerialsBean(serialsBean);
            b.setVendorId(i);
            l.setAccessLevel(9);
            insertCustomer(i,l,serialsBean,db);
            
            
            setDefaultPaymentMethod(serialsBean.getEmailKey(),db,ok);
        } catch (Exception ex) {
            Logger.getLogger(serialsObj.class.getName()).log(Level.SEVERE, null, ex);
        }
        return serialsBean;
    }
    private  void insertCustomer(int id,loginBean l,serialsBean x,dbMgrInterface db ){
        Object[] obj = new Object[]{
            x.getEmailKey(),
            x.getKey(),
            ck(l.getFirstName()),
            ck(l.getLastName()),
            id,
            l.getUserName().trim().toLowerCase(),
            l.getPassWord().trim().toLowerCase(),
            l.getAccessLevel(),
            l.getEmail().trim().toLowerCase(),
        };
        CachedRowSet rs=null;
         try {
             db.updateDatabase(serialsSql.sqlInsertNewCustomer, obj);
             rs = db.getCachedRowSet(
                    serialsSql.sqlSelectCustomer,
                    new Object[]{x.getKey(),l.getUserName()
            });
             while(rs.next()){
                 l.setCustomeId(rs.getInt(1));
             }
         } catch (Exception ex) {
             Logger.getLogger(serialsCompanyObj.class.getName()).log(Level.SEVERE, null, ex);
         } finally {
             db.closeCachedRowSet(rs);
         }
    }
    private  serialsBean getCompanyKey(int i,  dbMgrInterface db) {
        CachedRowSet rs=null;
        //if (!cust) {
         //   i = i + 1000;
        //}
        serialsBean serialsBean = new serialsBean();
        serialsBean.setId(i);
        try {
            rs = db.getCachedRowSet(
                    serialsSql.sqlKeyCustomerID,
                    new Object[]{new Integer(i)
            });
            while(rs.next()){
                serialsBean.setKey(rs.getString(2));
            }
            // ============================================
            rs = db.getCachedRowSet(
                    serialsSql.sqlKeyVendorID,
                    new Object[]{new Integer(i)
            });
            while(rs.next()){
                serialsBean.setEmailKey(rs.getString(2));
            }
            //return getKey(rs);
        } catch (Exception ex) {
            Logger.getLogger(serialsObj.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
             db.closeCachedRowSet(rs);
         }
        return serialsBean;
    }
  
    private  Object[] newComapnyBean(vendorBean b,int id){
        return new Object[]{
                 ck(    b.getVendorName()),
                 ck(     b.getVendor_code()),
                     b.getVendorInd(),
                     b.getVendorType(),
                     b.getFlag_ind(),
                 ck(     b.getAddr()),
                 ck(     b.getAddr2nd()),
                 ck(     b.getCity()),
                 ck(     b.getState()),
                 ck(     b.getZip()),
                  ck(    b.getZipPlus()),
                 ck(     b.getPhone()),
                 ck(     b.getFax()),
                     b.getEMail(),
                ck(      b.getExt()),
                     b.getVendorRollupID(),
                     b.getPaypalType(),
                id
             };
    }
    private  Object[] newComapnyBeanUpdate(vendorBean b){
        return new Object[]{
                 ck(b.getVendorName()),
                 ck(b.getAddr()),
                 ck(b.getAddr2nd()),
                 ck(b.getCity()),
                 ck(b.getState()),
                 ck(b.getZip()),
                  ck(b.getZipPlus()),
                 ck(b.getPhone()),
                 ck(b.getFax()),
                ck(b.getExt()),
                b.getVendorId()
             };
    }
    private   String ck(String s) {
    return s == null ? "" : s;
  }
    public  vendorBean getCompanyBean(int id, dbMgrInterface db,boolean ok) {
        vendorBean bean = new vendorBean();
        CachedRowSet cr = null;
        try {
            
            cr = db.getCachedRowSet(serialsSql.sqlSelectCompany,
                    new Object[]{new Integer(id)
            });
            while (cr.next()) {
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
                bean.setPhoneExt(cr.getString(16));
                bean.setVendorRollupID(cr.getInt(17));
                bean.setKeyStr(cr.getString(18));
                bean.setEmailKey(cr.getString(19));
                bean.setPaypalType(cr.getInt(20));
                bean.setDbId(cr.getInt(21));
            }
        } catch (Exception ex) {
            Logger.getLogger(serialsObj.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
             db.closeCachedRowSet(cr);
         }
        return bean;
    }
}
