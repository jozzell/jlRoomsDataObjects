/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsCommon._jlroot;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import jlRoomsCommon._beans.userAccountBean;
import obj.db.v1.dbMgrInterface;
import sun.jdbc.rowset.CachedRowSet;

/**
 *
 * @author lmeans
 */
public class serialsObj {
    serialsSql serialsSql;
    public serialsObj(){
        serialsSql = new serialsSql();
    }
        
    public  userAccountBean getUserAccountBean(dbMgrInterface db,boolean ok){
        return getUserAccountBean(serialsSql.sqlSelectDBAccount, new Object[]{},db);
    }
    public  userAccountBean getUserAccountBean(int id,dbMgrInterface db,boolean ok){
        return getUserAccountBean(serialsSql.sqlSelectDBAccoutID, new Object[]{id},db);
    }
   private  userAccountBean getUserAccountBean(String sql,Object[] obj,dbMgrInterface db){
       userAccountBean bean = new userAccountBean();;
       CachedRowSet rs;
       try {
           rs = db.getCachedRowSet(
                      sql,
                      obj);
           while(rs.next()){
               bean.setId(rs.getInt(1));
               bean.setJndi(rs.getString(2));
               bean.setUrl(rs.getString(3));
               bean.setUser(rs.getString(4));
               bean.setPass(rs.getString(5));
               bean.setPath(rs.getString(7));
           }
       } catch (Exception ex) {
           Logger.getLogger(serialsObj.class.getName()).log(Level.SEVERE, null, ex);
       }
       return bean;
       
   }
   public  void insertLogin(String email,String siteID,dbMgrInterface dbRoot){
       Calendar cal = Calendar.getInstance();
       cal.set(Calendar.MILLISECOND, 0);
        try {
            dbRoot.updateDatabase(serialsSql.sqlInsertLogin, new Object[]{
                new Timestamp(cal.getTime().getTime()),
                email,
                siteID});
        } catch (Exception ex) {
            Logger.getLogger(serialsObj.class.getName()).log(Level.SEVERE, null, ex);
        }
       
   
   }
    public  serialsBean updateCustomerKey(int i, dbMgrInterface dbRoot,dbMgrInterface userDb,boolean ok) {
        serialsBean serialsBean = getKey(i,true, dbRoot);
        
        updateCustKey(i, serialsBean.getKey(), userDb);
        return serialsBean;
    }
    
    private  void updateCustKey(int i, String key, dbMgrInterface db) {
        try {
            db.updateDatabase(serialsSql.sqlUpdateCustomerKey, new Object[]{
                key, new Integer(i)
            });
        } catch (Exception ex) {
            Logger.getLogger(serialsObj.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private  serialsBean getKey(int i, boolean cust, dbMgrInterface db) {
        CachedRowSet rs;
        if (!cust) {
            i = i + 1000;
        }
        try {
            rs = db.getCachedRowSet(
                    cust ? serialsSql.sqlKeyCustomerID : serialsSql.sqlKeyVendorID,
                    new Object[]{new Integer(i)
            });
            return getKey(rs);
        } catch (Exception ex) {
            Logger.getLogger(serialsObj.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
    
    public  serialsBean getKey(String s, boolean cust, dbMgrInterface db,boolean ok) {
        CachedRowSet rs = null;
        try {
            rs = db.getCachedRowSet(cust ? serialsSql.sqlKeyCustomerStr : serialsSql.sqlKeyVendorStr,
                    new Object[]{s
            });
            return getKey(rs);
        } catch (Exception ex) {
            Logger.getLogger(serialsObj.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private  serialsBean getKey(CachedRowSet rs) {
        serialsBean serialsBean = null;
        try {
            while (rs.next()) {
                serialsBean = new serialsBean();
                serialsBean.setId(rs.getInt(1));
                serialsBean.setKey(rs.getString(2).toLowerCase());
            }
        } catch (Exception ex) {
            serialsBean = null;
            Logger.getLogger(serialsObj.class.getName()).log(Level.SEVERE, null, ex);
        }
        return serialsBean;
    }
    public  int checkDupCustomer(String user, dbMgrInterface db,boolean ok){
        try {
            return checkDup(serialsSql.sqlEMailCustomer,user.trim().toLowerCase(),db);
        } catch (Exception ex) {
            Logger.getLogger(serialsObj.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    public  int checkDupCompany(String email, dbMgrInterface db,boolean ok){
       
        try {
           return checkDup(serialsSql.sqlEMailCompany,email.trim().toLowerCase(),db);
        } catch (Exception ex) {
            Logger.getLogger(serialsObj.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return -1;
    }
    private  int checkDup(String sql,String str,  dbMgrInterface db) throws Exception{
        CachedRowSet rs=null;
        int i = -1;
       
            rs = db.getCachedRowSet(sql,
                    new Object[]{str
            });
            while(rs.next()){
                i = rs.getInt(1);
            }
            
       
        return i;
    }
    
}
