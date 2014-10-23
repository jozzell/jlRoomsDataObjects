package jlRoomsCommon.sponsorHotel.db;

import jlRoomsCommon.objMgr;

public class sponsorHotelSql extends objMgr {
    public  String sqlCalendar_V2All(String web){
        return 
               " SELECT v.vendor_name,l.sys_desc,sh.eff_date,sh.end_date,sh.Balance_due_by, "+
               " sh.cutoff_date,sh.cancel_date,sh.check_due_by "+
               " FROM sponsor_hotel sh,vendor v,lookup_sys l "+
               " where v.vendor_id = sh.vendor_id and l.sys_id = sh.vendor_type and  "+
                
               " sh.flag_ind = 0 and "+
               " sh.email_key = "+this.getEMailKeyStr(web)+" and "+
               " v.email_key = "+this.getEMailKeyStr(web)+"  "
            ;
                
                
    }
    public  String sqlCalendar_V2(String web){
        return 
               " SELECT v.vendor_name,l.sys_desc,sh.eff_date,sh.end_date,sh.Balance_due_by, "+
               " sh.cutoff_date,sh.cancel_date,sh.check_due_by "+
               " FROM sponsor_hotel sh,vendor v,lookup_sys l "+
               " where v.vendor_id = sh.vendor_id and l.sys_id = sh.vendor_type and  "+
                
               " sh.sponsor_id = ? and sh.flag_ind = 0 and "+
               "  sh.email_key = "+this.getEMailKeyStr(web)+" and "+
               "  v.email_key = "+this.getEMailKeyStr(web)+"  "
            ;
                
                
    }
    
    
    private   String sql(String web){
    return 
     "select h.sponsor_hotel_id,h.sponsor_id,h.eff_date, h.vendor_id, " +
     " h.end_date, h.flag_Ind, h.balance_due_by, h.cutoff_date, h.service_charge,h.process_fee, " +
     " h.report_comment, h.cancel_date, h.location, h.walkin, h.hotel_fee, h.check_due_by, " +
     " v.vendor_name,v.addr1,v.city,v.state,v.zip,h.vendor_type,h.HOTEL_FEE,s.sys_desc, "+
     " h.DSP_STR1,h.DSP_STR2,h.DSP_STR3,h.FLT_ID"+
     " from sponsor_hotel h,vendor v,lookup_sys s "+
     " where s.sys_id = h.vendor_type and v.vendor_id = h.vendor_id"+getSub(web)
     ;
    }
 private  final String
     sqlInsert_v2 = " insert into sponsor_hotel "+
     " (sponsor_id,eff_date, vendor_id,end_date, flag_Ind, balance_due_by, cutoff_date, service_charge,process_fee, " +
     " report_comment, cancel_date, location, walkin, hotel_fee, check_due_by,vendor_type)"+
     " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
     sqlNewSponsorVendor = " insert into sponsor_hotel (vendor_id,sponsor_id,eff_date) values (?,?,?)"
     ;
  public  String sqlInsert(String web){
      if (web == null){
          return sqlInsert_v2;
      } else {
          return  " insert into sponsor_hotel "+
     " (sponsor_id,eff_date, vendor_id,end_date, flag_Ind, balance_due_by, cutoff_date, service_charge,process_fee, " +
     " report_comment, cancel_date, location, walkin, hotel_fee, check_due_by,vendor_type,CREATED ,email_key)"+
     " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"+this.getEMailKeyStr(web) +")";
      }
  }
  public  String sqlNewSponsorVendor(String web){
      if (web == null){
          return sqlNewSponsorVendor;
      } else {
          return " insert into sponsor_hotel (vendor_id,sponsor_id,eff_date,email_key) values (?,?,?,"+this.getEMailKeyStr(web) +")";
      }
  }
  public  String sqlUpdate_dsp_str1(String web){
     return    "update sponsor_hotel set dsp_str1  = ?,flt_id = ? where sponsor_hotel_id = ? "
             +(web == null ? "" :this.getEMailKeyEquals(web));
 }
   public  String sqlGetSponsorHotelId(String web){
     return   "select max(sponsor_hotel_id) from sponsor_hotel where sponsor_id = ? and  vendor_id = ? and CREATED = ? " 
             +(web == null ? "" : this.getEMailKeyEquals(web));
 }
 public  String sqlUpdate(String web){
     return   "update sponsor_hotel set"+
     " sponsor_id = ?,eff_date= ?, vendor_id= ?, " +
     " end_date= ?, flag_Ind= ?, balance_due_by= ?, cutoff_date= ?, service_charge= ?,process_fee= ?, " +
     " report_comment= ?, cancel_date= ?, location= ?, walkin=?, hotel_fee= ?, check_due_by= ? " +
     " where sponsor_hotel_id = ?" 
      +(web == null ? "" :this.getEMailKeyEquals(web))                    ;

 }
 
 public  String  sql_update_flt_info(String web){
      return  "update sponsor_hotel set flt_id = (select min(flt_id) from flight_information "+
                       " where sponsor_hotel_id = ?) where sponsor_hotel_id = ?";
     
 }
 
 public  String sqlSponsorHotelList(String web){
     return  sql(web)+" and h.sponsor_id = ? and (h.vendor_type = ?  or v.vendor_type = ?) order by v.vendor_name";
 }
 public  String sqlSponsorHotelListAll(String web){
     return  sql(web)+" and h.sponsor_id = ?  order by s.sys_desc,v.vendor_name" ;
 }
 public  String sqlGetSponsorHotel(String web){
     return  sql(web)+" and h.sponsor_hotel_id = ?";
 }
 public  String getGetSponsorVendor(String web){
     return  sql(web)+" and v.vendor_id = ? and h.sponsor_id = ?";
 }
 
 private  String getSub(String web){
     if (web == null) {
         return "" ;
     } else {
         return " and "+
              "  h.email_key = "+this.getEMailKeyStr(web)+" and "+
              "  v.email_key = "+this.getEMailKeyStr(web)+"  "
                 ;
     }
 }
}
