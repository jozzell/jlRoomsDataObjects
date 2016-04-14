package jlRoomsCommon.sponsor.db;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import jlRoomsCommon._beans.sponsorBean;
import jlRoomsCommon._objects.jlRoomsDbConnIinterface;
import jlRoomsCommon.JlRoomsDataObjects;
import obj.db.v1.dbMgrInterface;

import sun.jdbc.rowset.*;

/**
 * Title:        Room Tracking Web Applicaion (rtwa)
 * Description:
 * Copyright:    Copyright (c) 2003
 * Company:      Jozzell, llc
 * @author Lloyd Means
 * @version 1.0
 */

public class sponsorObj  implements Serializable{

  //private jlRoomsFactory jlRoomsFactory ;
  public  final int HTML = 0, BEAN = 1;
  private jlRoomsDbConnIinterface jlRoomsFactory;
  private String webID=null;
  sponsorSql sponsorSql;
  
  public sponsorObj(jlRoomsDbConnIinterface x) {
      this();
      
      this.jlRoomsFactory = x;
  }
  public sponsorObj(){
      sponsorSql = new sponsorSql();
  }
  public sponsorObj(String web){
      this();
      this.webID = web;
  }
  // ==================================================================
  public List<sponsorBean> genEventList(String key,boolean demo,dbMgrInterface x){
     
     return getEventList( 
              sponsorSql.sql_sponsor_next(this.webID,demo),
              new Object[]{key},
              x);
  }
  
   private  List<sponsorBean> getEventList(String sql,Object[] obj,dbMgrInterface x) {
     List<sponsorBean> v = new ArrayList<sponsorBean>();
     CachedRowSet rs = null;
     try {
       rs = x.getCachedRowSet(sql,obj);
       while(rs.next()) v.add(getBean(rs));
     } catch(Exception e) {
       Logger.getLogger(sponsorObj.class.getName()).log(Level.SEVERE, null, e);
     }
     if (rs != null) x.closeCachedRowSet(rs);
     return v;
   }
  
   private  sponsorBean getBean(CachedRowSet rs) throws Exception {
     sponsorBean bean = new sponsorBean();
     try {

       bean.setSponsorId(rs.getInt(1));
       bean.setEffDate(rs.getDate(2));
       bean.setSponsorDesc(rs.getString(3));
       bean.setEndDate(rs.getDate(4));
       bean.setFlagId(rs.getInt(5));
       bean.setProcFee(rs.getDouble(6));
       bean.setProcFeeHotel(rs.getDouble(7));
       bean.setProcFeeCar(rs.getDouble(8));
       bean.setProcFeeAir(rs.getDouble(9));
       bean.setProcFeeTicket(rs.getDouble(10));
       bean.setProcessFeeType(rs.getInt(11));
       bean.setProcFeeTrain(rs.getDouble(12));
       bean.setComment(rs.getString(13));
       bean.setVendorId(rs.getInt(14));
       bean.setVendorName(rs.getString(15));
       bean.setProcFeeCruise(rs.getDouble(16));
       bean.setTimeStamp(rs.getDate(17));
       bean.setCustId(rs.getInt(18));
       bean.setEffDateStr(rs.getString(19));
       bean.setEndDateStr(rs.getString(20));
       //bean.setNoFeeApplied(rs.getInt(17));
     }
     catch (Exception e) {
         Logger.getLogger(sponsorObj.class.getName()).log(Level.SEVERE, null, e);
       throw new Exception(e);
     }
     return bean;
   }
   
  
  // ==================================================================
  
  /*
    ************************************************************
    ************************************************************
    */
   public  List<sponsorBean> getEventList(String s) {
     return getEventList(s,this.jlRoomsFactory);

   }
   public  List<sponsorBean> getEventList(String s,dbMgrInterface x) {
     return getEventList(sponsorSql.sql_sponsor_seek(this.webID),new Object[]{"%"+s+"%"},
             x);

   }
   /*
    ************************************************************
    ************************************************************
    */
   public  List<sponsorBean> getEventList(dbMgrInterface x) {
     Calendar c = Calendar.getInstance();
     c.add(Calendar.MONTH,-24);
     return getEventList(0,c,x);
   }
   public  List<sponsorBean> getEventList() {
     Calendar c = Calendar.getInstance();
     c.add(Calendar.MONTH,-12);
     return getEventList(0,c);
   }
  
   //public  List<sponsorBean> getEventList(int id,Calendar c) {
      // return getEventList(id,c,false);
   //}
   
   public  List<sponsorBean> getEventList(int id,Calendar c) {
       return getEventList(id,c,this.jlRoomsFactory);
   }
   public  List<sponsorBean> getEventList(int id,Calendar c,dbMgrInterface x) {
       if (c == null){
           c = Calendar.getInstance();
           c.add(Calendar.MONTH, -16);
       }
     return getEventList(
             sponsorSql.sql_sponsor_default(webID),
             new Object[]{new Integer(id), new java.sql.Date(c.getTime().getTime())},
             x);
   }
   
  
   /// ********************************************************************
    public  sponsorBean getCustSponsorBean(int cust_id){
        return getCustSponsorBean(cust_id,jlRoomsFactory);
    }
   public  sponsorBean getCustSponsorBean(int cust_id,dbMgrInterface db){
     CachedRowSet rs = null;
     sponsorBean bean=null;
     try {
       rs = db.getCachedRowSet(sponsorSql.sql_get_cust_sponsor_bean(this.webID),new Object[]{new Integer(cust_id)});
       while(rs.next()) bean = getBean(rs);
     } catch(Exception e) {
       Logger.getLogger(sponsorObj.class.getName()).log(Level.SEVERE, null, e);
     } finally {
         db.closeCachedRowSet(rs);
     }
     return bean;

   }
    public void getSponsorList(){
        
    }
    public  int  getSponsorID(String key,dbMgrInterface db){
     CachedRowSet rs = null;
     
     try {
       rs = db.getCachedRowSet(sponsorSql.sql_sponsor_next(this.webID,false),new Object[]{key});
       while(rs.next()){
          return getBean(rs).getSponsorId();
       }
     } catch(Exception e) {
       Logger.getLogger(sponsorObj.class.getName()).log(Level.SEVERE, null, e);
     } finally {
         db.closeCachedRowSet(rs);
     }
     return 0;

   }
    
   // ************************************************************
   public  void update(sponsorBean b) {
       update(b,jlRoomsFactory);
   }
   public  void update(sponsorBean b,dbMgrInterface db) {
     String sql = (b.getSponsorId() == 0 ? sponsorSql.sql_sponsor_insert(this.webID) : sponsorSql.sql_sponsor_update(this.webID));
        try {
            Object[] obj = getSponsorBeanObject(b);
            db.updateDatabase(sql, getSponsorBeanObject(b));
        } catch (Exception ex) {
            Logger.getLogger(sponsorObj.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   // ************************************************************
   private  Object[] getSponsorBeanObject(sponsorBean b) {
       
     if (b.getSponsorId() == 0) {
        
       return new Object[] {
           (new JlRoomsDataObjects()).getDate(b.getEffDate()),
           b.getSponsorDesc() == null ? "" : b.getSponsorDesc(),
           (new JlRoomsDataObjects()).getDate(b.getEndDate()), b.getFlagId(), b.getProcFee(),
           new Double(b.getProcFeeHotel()),
           new Double(b.getProcFeeCar()),
           new Double(b.getProcFeeAir()),
           new Double(b.getProcFeeTicket()),
           new Integer(b.getProcessFeeType()),
           new Double(b.getProcFeeTrain()),
           b.getComment() == null ? "" : b.getComment(),
           new Integer(b.getVendorId()),
           new Double(b.getProcFeeCruise()),
           new Integer(b.getCustId()),
          
           b.getKeyStr()
       };
     } else {
         
       return new Object[] {
          (new JlRoomsDataObjects()).getDate(b.getEffDate()),
          b.getSponsorDesc() == null ? "" : b.getSponsorDesc(),
          (new JlRoomsDataObjects()).getDate(b.getEndDate()),
          new Integer(b.getFlagId()),
          new Double(b.getProcFee()),
          new Double(b.getProcFeeHotel()),
          new Double(b.getProcFeeCar()),
          new Double(b.getProcFeeAir()),
          new Double(b.getProcFeeTicket()),
          new Integer(b.getProcessFeeType()),
          new Double(b.getProcFeeTrain()),
          b.getComment() == null ? "" : b.getComment(),
          new Integer(b.getVendorId()),
          new Double(b.getProcFeeCruise()),
          new Integer( b.getSponsorId())
      };

     }
   }


















 

  /*
   ************************************************************
   ************************************************************
   */



  /*
   ************************************************************
   ************************************************************
   */
public  sponsorBean sponsorGetEvent(int id) throws Exception {
    return sponsorGetEvent(id,this.webID,jlRoomsFactory);
}
  public  sponsorBean sponsorGetEvent(int id,String key,dbMgrInterface db) throws Exception {
    sponsorBean bean = new sponsorBean();
    CachedRowSet rs = null;


    try {
      rs = db.getCachedRowSet(
            sponsorSql.sql_getSponsor(key), new Object[] {new Integer(id)});

      while (rs.next()) bean = getBean(rs);
    }
    catch (Exception e) {
      throw (e);
    } finally {
     db.closeCachedRowSet(rs);
    }
    return bean;
  }

  /*
   ************************************************************
   ************************************************************
   */



  //private final String
  //spf_qry_sponsor = "spf_qry_sponsor(:2,:3); end",
  //spf_qry_sponsor = "begin :1 :=spf_qry_sponsor(:2,:3); end",
  //spf_get_sponsor = "begin :1 :=spf_get_sponsor(:2); end";

}
