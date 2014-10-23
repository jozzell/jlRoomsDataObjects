/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsCommon.customerRoom.db;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jlRoomsCommon._beans.clientRmBean;
import jlRoomsCommon._beans.clientRmMateBean;
import jlRoomsCommon._beans.v2RptClientRmMateBean;
import jlRoomsCommon._beans.v2RptClientRmMateBeanList;
import obj.db.v1.dbMgrInterface;
import sun.jdbc.rowset.CachedRowSet;

/**
 *
 * @author lmeans
 */
public class clientRoommateObj  implements Serializable {

    private String email;
    clientRoommateSql clientRoommateSql;
    public clientRoommateObj(){
        clientRoommateSql = new clientRoommateSql();
    }
    public clientRoommateObj(String str) {
        this();
        this.email = str;
    }
    // ==================================================================
    
    public List<v2RptClientRmMateBeanList>  getSqlSelectRmMateList_CustSponsor(int cust,int spon,dbMgrInterface db){
        Object[] obj = new Object[]{
            new Integer(cust),
            new Integer(spon)
        };
        try{
            CachedRowSet rs =  db.getCachedRowSet(clientRoommateSql.getSqlSelectRmMateList_CustSponsor(email), obj);
            return getv2RptClientRmMateBeanListArray(rs);
        } catch (Exception ex) {
            Logger.getLogger(clientRoommateObj.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<v2RptClientRmMateBeanList>();
    }
    public List<v2RptClientRmMateBeanList>  getSqlSelectRmMateList_VendorTypeCustSponsor(int type,int cust,int spon,dbMgrInterface db){
        Object[] obj = new Object[]{
            type, cust, spon};
        try{
            CachedRowSet rs =  db.getCachedRowSet(clientRoommateSql.getSqlSelectRmMateList_VendorTypeCustSponsor(email), obj);
            return getv2RptClientRmMateBeanListArray(rs);
        } catch (Exception ex) {
            Logger.getLogger(clientRoommateObj.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<v2RptClientRmMateBeanList>();
    }
    
     public List<v2RptClientRmMateBeanList>  getSqlSelectRmMateList_Sponsor(int spon,dbMgrInterface db){
        Object[] obj = new Object[]{
            
            new Integer(spon)
        };
        try{
            CachedRowSet rs =  db.getCachedRowSet(clientRoommateSql.getSqlSelectRmMateList_Sponsor(email), obj);
            return getv2RptClientRmMateBeanListArray(rs);
        } catch (Exception ex) {
            Logger.getLogger(clientRoommateObj.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<v2RptClientRmMateBeanList>();
    }       
            
            
    // ==================================================================
    public void updateClientRm(int id,String desc,dbMgrInterface db){
        try {
            db.updateDatabase(clientRoommateSql.sqlUpdateClientRm,  new Object[]{
                desc,
                new Integer(id),
                this.email
            });
        } catch (Exception ex) {
            Logger.getLogger(clientRoommateObj.class.getName()).log(Level.SEVERE, null, ex);
        }
        //sqlUpdateClientRm = "update client_rm set client_rm_desc = ? where client_rm_id = ? and  email_key = ?",
    }
    public void removeRoommdate(int id,dbMgrInterface db){
       
          try {
            db.updateDatabase(clientRoommateSql.sqlDeleteRoommdate,  new Object[]{
                id,
                this.email
            });
        } catch (Exception ex) {
            Logger.getLogger(clientRoommateObj.class.getName()).log(Level.SEVERE, null, ex);
        }
        //
    }
    public void saveClientRmCustomer(int rmId,int spon,int cust,dbMgrInterface db){
        
        Object[] obj = new Object[]{
            rmId,
                this.email, spon, cust};
        try {
            db.updateDatabase(clientRoommateSql.sqlInsertClientCustomer, obj);
        } catch (Exception ex) {
            Logger.getLogger(clientRoommateObj.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int saveClientRm(clientRmBean bean, dbMgrInterface db) {
        if (bean.getClientRmID() == 0) {
            return insertClientRm(bean, db);
        }
        return 0;
    }

    private int insertClientRm(clientRmBean bean, dbMgrInterface db) {
        CachedRowSet rs;
        int i = 0;
        try {
            bean.setDbTimestamp(bean.getDbTimestamp()+"."+bean.getVendorId());
            
            if (bean.getWho() != null){
                  bean.setDbTimestamp(bean.getDbTimestamp()+"."+ bean.getWho());
                  if (bean.getDbTimestamp().length() > 110){
                      bean.setDbTimestamp(bean.getDbTimestamp().substring(0, 99));
                  }
            } else {
                bean.setDesc("*** Missing Group Name ****");
            }
            
            Object[] obj = new Object[]{
                bean.getDesc(),
                email, bean.getSponsorId(), bean.getLookupRollupId(), bean.getLookupId(), bean.getVendorId(),
                bean.getDbTimestamp()
            };
            db.updateDatabase(clientRoommateSql.sqlInsertClientRm, obj);
            obj = new Object[]{
                email,
                new Integer(bean.getSponsorId()),
                bean.getDbTimestamp()
            };
            
            rs = db.getCachedRowSet(clientRoommateSql.sqlInsertClientRmMax, obj);
            while(rs.next()){
                i = rs.getInt(1);
                break;
            }
            
        } catch (Exception ex) {
            Logger.getLogger(clientRoommateObj.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        //client_rm_desc ,email_key,sponsor_ID,lookup_rollup_id,lookup_id,Vendor_id ,DB_TIMESTAMP

        return i;
    }
    
    public List<clientRmBean>  getCutomerRoommateGroupList(int spon,int cust,dbMgrInterface db){
        Object[] obj = new Object[]{
            new Integer(spon),
            new Integer(cust),
            email
        };
        
        List<clientRmBean> l = null;
        try {
            CachedRowSet rs =  db.getCachedRowSet(clientRoommateSql.getCustomerRoommateGroupSql(email), obj);
            return getclientRmBeanList(rs);
        } catch (Exception ex) {
            Logger.getLogger(clientRoommateObj.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }
    // ======================================================================================================
    public List<v2RptClientRmMateBean>  getv2RptClientRmMateBean(int spon,dbMgrInterface db){
         Object[] obj = new Object[]{
            new Integer(spon)
        };
         return getv2RptClientRmMateBean(obj,false,db);
        
    }
    public List<v2RptClientRmMateBean>  getv2RptClientRmMateBean(int spon,int cust,dbMgrInterface db){
         Object[] obj = new Object[]{
            new Integer(spon),
            new Integer(cust)
        };
        return getv2RptClientRmMateBean(obj,true,db);
    }
    private List<v2RptClientRmMateBean>  getv2RptClientRmMateBean(Object[] obj,boolean cust,dbMgrInterface db){
       
        
        List<v2RptClientRmMateBean> l = null;
        try {
            CachedRowSet rs =  db.getCachedRowSet(clientRoommateSql.getSqlSelectRmMateListClient(email,cust), obj);
            return getv2RptClientRmMateBeanList(rs);
        } catch (Exception ex) {
            Logger.getLogger(clientRoommateObj.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }
    // ======================================================================================================
    public List<clientRmMateBean> getRoommateList(int id,dbMgrInterface db){
        Object[] obj = new Object[]{
            new Integer(id),
            this.email
        };
        try {
            CachedRowSet rs =  db.getCachedRowSet(clientRoommateSql.sqlSelectRmMateList, obj);
            return getclientRmMateBeanList(rs);
        } catch (Exception ex) {
            Logger.getLogger(clientRoommateObj.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    // ******************************************************************
    private List<clientRmBean> getclientRmBeanList(CachedRowSet rs){
        List<clientRmBean> l = new ArrayList<clientRmBean>();
        try {
            while(rs.next()){
                l.add(getclientRmBean(rs));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(clientRoommateObj.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return l;
    }
     private clientRmBean getclientRmBean( CachedRowSet rs){
        clientRmBean b = new clientRmBean();
        try {
            b.setClientRmID(rs.getInt(1));
            b.setDesc(rs.getString(2));
            b.setVendorId(rs.getInt(3));
            b.setLookupRollupId(rs.getInt(4));
            b.setVendorName(rs.getString(5));
            b.setSysName(rs.getString(6));
            b.setCount(rs.getInt(7));
        } catch (SQLException ex) {
            Logger.getLogger(clientRoommateObj.class.getName()).log(Level.SEVERE, null, ex);
        }
        return b;
    }
    // ******************************************************************
    private  List<clientRmMateBean> getclientRmMateBeanList(CachedRowSet rs){
        List<clientRmMateBean> l = new ArrayList<clientRmMateBean>();
        try {
            while(rs.next()){
                l.add(getClientRmMateBean(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(clientRoommateObj.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }
    private String getStr(String str){
        if (str == null || str.trim().length() == 0) return "";
        return str.trim()+" ";
    }
    private clientRmMateBean getClientRmMateBean(CachedRowSet rs){
        clientRmMateBean b = new clientRmMateBean();
        try {
            b.setClientRmMateId(rs.getInt(1));
            b.setClientRmID(rs.getInt(2));
            b.setSponsorId(rs.getInt(3));
            b.setCustId(rs.getInt(4));
            b.setCustomerName(rs.getString(5)+" "+rs.getString(6));
            b.setEmail(rs.getString(7));
            String str = getStr(rs.getString(8));
            str += getStr(rs.getString(9));
            str += getStr(rs.getString(10));
            str += getStr(rs.getString(11));
            str += getStr(rs.getString(12));
            b.setAddress(str.trim());
            b.setPhone(rs.getString(13));
            str = getStr(rs.getString(14));
            str += getStr(rs.getString(15));
            b.setWkPhone(str);
            
            
            //b.setAddress(email);
            
        } catch (SQLException ex) {
            Logger.getLogger(clientRoommateObj.class.getName()).log(Level.SEVERE, null, ex);
        }
        return b;
    }
    // ===============================================================================
    
    // ******************************************************************
     public List<v2RptClientRmMateBeanList> getv2RptClientRmMateBeanListArray(CachedRowSet rs){
         List<v2RptClientRmMateBean> l = new ArrayList<v2RptClientRmMateBean>();
         List<v2RptClientRmMateBeanList> a = new ArrayList<v2RptClientRmMateBeanList>();
         int last=0;
         try {
             v2RptClientRmMateBeanList bean = null;
            while(rs.next()){
                v2RptClientRmMateBean b = getv2RptClientRmMateBean(rs);
                if (last != 0 && b.getCustRmId() != last){
                    bean.setList(l);
                    a.add(bean);
                    bean = null;
                    l = new ArrayList<v2RptClientRmMateBean>();
                }
                if (bean == null){
                    bean = new v2RptClientRmMateBeanList();
                    bean.setCustRmId(b.getCustRmId());
                    bean.setDesc(b.getDesc());
                    bean.setLookupDesc(b.getLookupDesc());
                    bean.setVendor(b.getVendor());
                }
                last = b.getCustRmId();
               l.add(b);
            }
            if (bean != null){
                 bean.setList(l);
                 a.add(bean);
            }
        } catch (SQLException ex) {
            Logger.getLogger(clientRoommateObj.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
     
    public List<v2RptClientRmMateBean> getv2RptClientRmMateBeanList(CachedRowSet rs){
        List<v2RptClientRmMateBean> l = new ArrayList<v2RptClientRmMateBean>();
         try {
            while(rs.next()){
                l.add(getv2RptClientRmMateBean(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(clientRoommateObj.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }
   
    public v2RptClientRmMateBean getv2RptClientRmMateBean(CachedRowSet rs){
        v2RptClientRmMateBean bean = new v2RptClientRmMateBean();
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
      bean.setDesc(rs.getString(13));
      bean.setVendor(rs.getString(14));
      bean.setLookupDesc(rs.getString(15));
      bean.setCustRmMateId(rs.getInt(16));
      bean.setCustRmId(rs.getInt(17));
      } catch (SQLException ex) {
            Logger.getLogger(clientRoommateObj.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bean;
    }
}
