package jlRoomsCommon.sponsorHotel.db;
import java.io.Serializable;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import jlRoomsCommon._beans.jsfCalendarBean;
import jlRoomsCommon._beans.sponsorHotelBean;
import jlRoomsCommon._objects.jlRoomsDbConnIinterface;
import jlRoomsDO.JlRoomsDataObjects;
import obj.db.v1.dbMgrInterface;


import sun.jdbc.rowset.*;
public class sponsorHotelObj  implements Serializable{
    private jlRoomsDbConnIinterface jlRoomsFactory;
    private String webID=null;
    private sponsorHotelSql sponsorHotelSql;
    public sponsorHotelObj(){
        sponsorHotelSql = new sponsorHotelSql();
    }
  public sponsorHotelObj(String web){
      this();
      this.webID = web;
  }
  public sponsorHotelObj(jlRoomsDbConnIinterface x) {
      this();
      jlRoomsFactory = x;

  }
  
  
  public List<jsfCalendarBean> getCalendar(int sponsor,dbMgrInterface db){
       List<jsfCalendarBean> list = new ArrayList<jsfCalendarBean>();
       CachedRowSet rs=null;
        try {
            rs = db.getCachedRowSet( sponsorHotelSql.sqlCalendar_V2(webID),new Object[]{new Integer(sponsor)});
            list = getCalendar(rs);
        } catch (Exception ex) {
            Logger.getLogger(sponsorHotelObj.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            db.closeCachedRowSet(rs);
        }
       return list;
  }
  
  public List<jsfCalendarBean> getCalendar(dbMgrInterface db){
       List<jsfCalendarBean> list = new ArrayList<jsfCalendarBean>();
       CachedRowSet rs=null;
        try {
            rs = db.getCachedRowSet( sponsorHotelSql.sqlCalendar_V2All(webID),new Object[]{});
            list = getCalendar(rs);
        } catch (Exception ex) {
            Logger.getLogger(sponsorHotelObj.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            db.closeCachedRowSet(rs);
        }
       return list;
  }
  private List<jsfCalendarBean> getCalendar(CachedRowSet rs){
      List<jsfCalendarBean> list = new ArrayList<jsfCalendarBean>();
      try {
      String str;
     
      while(rs.next()){
          str = rs.getString(1);
          list.add(new jsfCalendarBean(str,rs.getDate(3),rs.getDate(4)));
          if (rs.getDate(5) != null)list.add(new jsfCalendarBean(str+" Balance Due Date",rs.getDate(5)));
          if (rs.getDate(6) != null)list.add(new jsfCalendarBean(str+" Cutoff Date",rs.getDate(6)));
          if (rs.getDate(7) != null)list.add(new jsfCalendarBean(str+" Cancel Date",rs.getDate(7)));
          if (rs.getDate(8) != null)list.add(new jsfCalendarBean(str+" Check Due Date",rs.getDate(8)));
      }
    } catch(Exception e) {
        Logger.getLogger(sponsorHotelObj.class.getName()).log(Level.SEVERE, null, e);
    } 
   return list;
  }
  public List<jsfCalendarBean> getCalendarXXX(int sponsor,dbMgrInterface db){
    List<jsfCalendarBean> list = new ArrayList<jsfCalendarBean>();
    CachedRowSet rs = null;
    try {
      String str;
      rs = db.getCachedRowSet( sponsorHotelSql.sqlCalendar_V2(webID),new Object[]{new Integer(sponsor)});
      while(rs.next()){
          str = rs.getString(1);
          list.add(new jsfCalendarBean(str,rs.getDate(3),rs.getDate(4)));
          if (rs.getDate(5) != null)list.add(new jsfCalendarBean(str+" Balance Due Date",rs.getDate(5)));
          if (rs.getDate(6) != null)list.add(new jsfCalendarBean(str+" Cutoff Date",rs.getDate(6)));
          if (rs.getDate(7) != null)list.add(new jsfCalendarBean(str+" Cancel Date",rs.getDate(7)));
          if (rs.getDate(8) != null)list.add(new jsfCalendarBean(str+" Check Due Date",rs.getDate(8)));
          
      }
    } catch(Exception e) {
     Logger.getLogger(sponsorHotelObj.class.getName()).log(Level.SEVERE, null, e);
    } finally {
     db.closeCachedRowSet(rs);
   }
   return list;
      
  }
  // =====================================================================================
  public  void addVendorToSponsor(int vendor, int sponsor,Date d) {
      addVendorToSponsor(vendor,sponsor,d,jlRoomsFactory);
  }
  public  void addVendorToSponsor(int vendor, int sponsor,Date d,dbMgrInterface db) {
    try {
      db.updateDatabase(sponsorHotelSql.sqlNewSponsorVendor(this.webID),
                                            new Object[]{new Integer(vendor),new Integer(sponsor),new java.sql.Date(d.getTime())});
    } catch(Exception e) {
      Logger.getLogger(sponsorHotelObj.class.getName()).log(Level.SEVERE, null, e);
    }
  }
  // ********************************************************
  public  List<sponsorHotelBean>  getSponsorHotelVectorAll(int spon) {
      return getSponsorHotelVectorAll(spon,this.jlRoomsFactory);
  }
  public  List<sponsorHotelBean>  getSponsorHotelVectorAll(int spon,dbMgrInterface db) {
   return getSponsorHotelVector(
          sponsorHotelSql.sqlSponsorHotelListAll(this.webID),new Object[]{new Integer(spon)},db);
 }
    // ********************************************************
 public  List<sponsorHotelBean>  getSponsorHotelVector(int spon,int id) {
     return getSponsorHotelVector( spon, id,this.jlRoomsFactory);
 }
 public  List<sponsorHotelBean>  getSponsorHotelVector(int spon,int id,dbMgrInterface db) {
    return getSponsorHotelVector(
           sponsorHotelSql.sqlSponsorHotelList(this.webID),new Object[]{new Integer(spon),new Integer(id),new Integer(id)},db);

  }
    // ********************************************************
  public  List<sponsorHotelBean> getSponsorHotelVector(String sql,Object obj[]) {
      return getSponsorHotelVector(sql,obj,this.jlRoomsFactory);
  }
  private  List<sponsorHotelBean> getSponsorHotelVector(String sql,Object obj[],dbMgrInterface db) {
    List<sponsorHotelBean> v = new ArrayList<sponsorHotelBean>();
    CachedRowSet rs = null;
    try {
      rs = db.getCachedRowSet( sql,obj);
      while(rs.next())v.add(getSponsorHotelBean(rs));
    } catch(Exception e) {
     Logger.getLogger(sponsorHotelObj.class.getName()).log(Level.SEVERE, null, e);
    } finally {
     db.closeCachedRowSet(rs);
   }
    return v;
  }


  // ********************************************************
  
  public  sponsorHotelBean getSponsorHotelVendorBean(int vendor,int sponsor) {
      return getSponsorHotelVendorBean(vendor,sponsor,jlRoomsFactory);
  }
  public  sponsorHotelBean getSponsorHotelVendorBean(int vendor,int sponsor,dbMgrInterface db) {
    CachedRowSet rs = null;
   try {
     rs = db.getCachedRowSet(
          sponsorHotelSql.getGetSponsorVendor(this.webID),new Object[]{new Integer(vendor),new Integer(sponsor)});
     while(rs.next())return getSponsorHotelBean(rs);
   } catch(Exception e) {
     Logger.getLogger(sponsorHotelObj.class.getName()).log(Level.SEVERE, null, e);
   } finally {
     db.closeCachedRowSet(rs);
   }
   return null;

  }
  // ********************************************************
  public  sponsorHotelBean getSpoinsorHotelBean(int id) {
   return   getSpoinsorHotelBean(id,jlRoomsFactory);
  }
  public  sponsorHotelBean getSpoinsorHotelBean(int id,dbMgrInterface db) {
   CachedRowSet rs = null;
   try {
     rs = db.getCachedRowSet(
          sponsorHotelSql.sqlGetSponsorHotel(this.webID),new Object[]{new Integer(id)});
     while(rs.next())return getSponsorHotelBean(rs);
   } catch(Exception e) {
     Logger.getLogger(sponsorHotelObj.class.getName()).log(Level.SEVERE, null, e);
   } finally {
     db.closeCachedRowSet(rs);
   }
   return null;

  }
  // ********************************************************
  private  int getSponsorHotelId(sponsorHotelBean b,dbMgrInterface db) {
    int i = 0;
    CachedRowSet rs = null;
   try {
     rs = db.getCachedRowSet(
          sponsorHotelSql.sqlGetSponsorHotelId(this.webID) ,new Object[]{
             
          new Integer(b.getSponsor_id()),
          new Integer(b.getVendor_id()),
           b.getCreated()});
     while(rs.next()) return rs.getInt(1);
   } catch(Exception e) {
     Logger.getLogger(sponsorHotelObj.class.getName()).log(Level.SEVERE, null, e);
   } finally {
        db.closeCachedRowSet(rs);
   }

    return i;
  }
  public  int update(sponsorHotelBean b) {
      return  update(b,jlRoomsFactory);
  }
  public  int update(sponsorHotelBean b,dbMgrInterface db) {
    if (b.getVendorType() == JlRoomsDataObjects.VENDOR_AIRLINES){
        b.setCreated(JlRoomsDataObjects.getTimeStampString());
    } else {
        b.setCreated("00:00:00");
    }
    
    Object[] obj = getSponsorHotelObject(b);
    try {
      if(b.getSponsor_hotel_id() == 0) {
        db.updateDatabase(sponsorHotelSql.sqlInsert(this.webID),
                                              getSponsorHotelObject(b));
        return getSponsorHotelId(b,db);
        
        //return x;
      } else {
        db.updateDatabase(sponsorHotelSql.sqlUpdate(this.webID),
                                              getSponsorHotelObject(b));
      }
    } catch(Exception e) {
      Logger.getLogger(sponsorHotelObj.class.getName()).log(Level.SEVERE, null, e);
    }
    return b.getSponsor_hotel_id();
  }
  // ********************************************************
  
  private  Object[] getSponsorHotelObject(sponsorHotelBean b) {
     
    if (b.getSponsor_hotel_id() > 0) {
      return new Object[] {
          new Integer(b.getSponsor_id()),
          JlRoomsDataObjects.getDate(b.getEff_date()),
          new Integer(b.getVendor_id()),
          JlRoomsDataObjects.getDate(b.getEnd_date()),
          new Integer(b.getFlag_Ind()),
          JlRoomsDataObjects.getDate(b.getBalance_due_by()),
          JlRoomsDataObjects.getDate(b.getCutoff_date()),
          new Double(b.getService_charge()),
          new Double(b.getProcess_fee()),
          b.getReport_comment() == null ? "" : b.getReport_comment(),
          JlRoomsDataObjects.getDate(b.getCancel_date()), //getDate(b.getCancel_date()),
          b.getLocation() == null ? "" : b.getLocation() + " ",
          new Integer(0),
          new Double(b.getHotel_fee()),
          JlRoomsDataObjects.getDate(b.getCheck_due_by()),
          new Integer(b.getSponsor_hotel_id())
      };

    } else {
      return new Object[] {
          new Integer(b.getSponsor_id()),
          JlRoomsDataObjects.getDate(b.getEff_date()),
          new Integer(b.getVendor_id()),
          JlRoomsDataObjects.getDate(b.getEnd_date()),
          new Integer(b.getFlag_Ind()),
          JlRoomsDataObjects.getDate(b.getBalance_due_by()),
          JlRoomsDataObjects.getDate(b.getCutoff_date()),
          new Double(b.getService_charge()),
          new Double(b.getProcess_fee()),
          b.getReport_comment() == null ? "" : b.getReport_comment(),
          JlRoomsDataObjects.getDate(b.getCancel_date()), //getDate(b.getCancel_date()),
          b.getLocation() == null ? "" : b.getLocation() + " ",
          new Integer(0),
          new Double(b.getHotel_fee()),
          JlRoomsDataObjects.getDate(b.getCheck_due_by()),
          new Integer(b.getVendorType()),
          b.getCreated()
         

      };
    }
  }
  private  sponsorHotelBean getSponsorHotelBean(CachedRowSet rs) throws Exception{
    sponsorHotelBean b = new sponsorHotelBean();
    try {
      b.setSponsor_hotel_id(rs.getInt(1));
      b.setSponsor_id(rs.getInt(2));
      b.setEff_date(rs.getDate(3));
      b.setVendor_id(rs.getInt(4));
      b.setEnd_date(rs.getDate(5));
      b.setFlag_Ind(rs.getInt(6));
      b.setBalance_due_by(rs.getDate(7));
      b.setCutoff_date(rs.getDate(8));
      b.setService_charge(rs.getDouble(9));
      b.setProcess_fee(rs.getDouble(10));
      b.setReport_comment(rs.getString(11));
      b.setCancel_date(rs.getDate(12));
      b.setLocation(rs.getString(13));
      b.setWalkin(rs.getInt(14));
      b.setHotel_fee(rs.getDouble(15));
      b.setCheck_due_by(rs.getDate(16));

      b.setVendorName(rs.getString(17));
      b.setAddr(rs.getString(18));
      b.setCity(rs.getString(19));
      b.setState(rs.getString(20));
      b.setZip(rs.getString(21));
      b.setVendorType(rs.getInt(22));
      b.setHotel_fee(rs.getDouble(23));
      b.setVendorTypeDesc(rs.getString(24));
      b.setDSP_STR1(rs.getString(25));
      b.setDSP_STR2(rs.getString(26));
      b.setDSP_STR3(rs.getString(27));
      b.setFlt_id(rs.getInt(28));
    }catch (Exception e) {
        Logger.getLogger(sponsorHotelObj.class.getName()).log(Level.SEVERE, null, e);
     throw(e);
    }
    return b;
  }


}
