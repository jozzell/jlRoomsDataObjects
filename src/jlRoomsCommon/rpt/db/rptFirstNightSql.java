package jlRoomsCommon.rpt.db;

public class rptFirstNightSql {

    public  final String
            testXYm = "",
            sql_rpt_arrival =
            " select v.vendor_name,v.vendor_id,c.hm_phone,c.last_name,c.first_name, cr.eff_date,cr.end_date,cr.end_date - cr.eff_date days,"+
            "cr.cust_room_cnt,l.lookup_desc,cr.room_comment,s.sys_desc,cr.roommate_split,cr.cust_room_type "+
            "from customer_room cr,lookup l,lookup_sys s,customer c,vendor v "+
            "where cr.sponsor_id = ? and cr.cust_room_type in (-5,-6) and l.lookup_id = cr.lookup_id and "+
            "cr.cust_room_type = s.sys_id and c.cust_id = cr.cust_id and v.vendor_id = cr.vendor_id "+
            "order by v.vendor_name,c.last_name,c.first_name",
            sql_rpt_get_day_cnt_id =
            "select distinct l.lookup_id,l.lookup_desc from customer_room cr,lookup l "+
            " where l.lookup_id = cr.lookup_id and cr.sponsor_id = ? and cr.cust_room_type in (-5,-6)",
            sql_rpt_get_payments =
            "select sum(amt_rec),max(db_timestamp) from customer_payment where vendor_id = ?";
    

public  final int WHERE_SPONSOR=0,WHERE_DB_TIMESTAMP=2;
  public  String inClase(int[] ary) {
    if (ary == null || ary.length == 0) return "";
      String s = "";
      for (int i = 0; i < ary.length; i++) {
        s += (s.length() > 0 ? ",": "")+ary[i];
      }
      return  s == null ? "" : "  LOOKUP_ROLLUP_ID in ("+s+") and ";
  }
  public  String getRptCustomerSummary(String where) {
    return getRptCustomerSummary(where,null);
  }


public  String getRptCustomerSummary(String where,String in) {
  return
      " select 0,cust_id,rm_cnt,vendor_name,rm_type,lookup_desc,booking,total_cost * -1,db_timestamp,full_name,cust_room_type from VIEW_summary_room where  "+in+" "+where+" "+
      " union select 1,cust_id,0,'','','',sys_desc,amt,db_timestamp,full_name,-6 from VIEW_summary_payment_cust_booked where "+where+" "+
      " union select 2,cust_id,0,'','','',sys_desc,amt,db_timestamp,full_name,-6 from VIEW_summary_payment_cust where "+where+" "+
      " order by 2,1 ";
}
/*
    ----------------------------------------------------------------
    ----------------------------------------------------------------
*/

public  String getRptVendorSummary(String where) {
  return getRptVendorSummary(where,"");
}
public  String getRptVendorSummary(String where,String in) {
  System.out.println(in+where);
  return
      " select 0,vendor_id,rm_cnt,vendor_name,rm_type,room,booking,total_cost,db_timestamp,cust_room_id from VIEW_summary_room_vendor where   "+in+" "+where+" "+
      " union select 1,vendor_id,0,vendor_name,'','',lookup_desc,amt,db_timestamp,0 from VIEW_summary_payment_vendor where   "+where+" "+
      " union select 2,vendor_id,0,vendor_name,'','',lookup_desc,amt,db_timestamp,0 from VIEW_summary_payment_vendor_booked where   "+where+" "+
      " order by 2,1 ";

}
/*
    ----------------------------------------------------------------
    ----------------------------------------------------------------
*/

public  String getRptVendor(String where) {
  return getRptVendor(where,"");
}
public  String getRptVendor(String where,String in) {
  System.out.println(in+where);
  return
      " select 0,vendor_id,vendor_name,rm_type,rm_cost * rm_cnt, total_cost"+
      " from VIEW_summary_room_vendor"+
      " where "+in+" "+where+" "+
      " union"+
      " select 1,vendor_id,vendor_name,'',amt,0"+
      " from VIEW_summary_payment_vendor "+
      " where "+where+" "+
      " union "+
      " select 2,vendor_id,vendor_name,'',amt,0 "+
      " from VIEW_summary_payment_vendor_booked "+
      " where "+where+" "+
      " order by 2,1"
;
}
/*
    ----------------------------------------------------------------
    ----------------------------------------------------------------
*/

public  String getRptCust(String where) {
  return getRptCust(where,"");
}
 public  String getRptCust(String where,String in) {
   System.out.println(in+where);
   return
       " select 0,cust_id,full_name,rm_type,booking,vendor_name, "+
       " rm_cost * rm_cnt,total_cost "+
       " from VIEW_summary_room "+
       " where "+in+" "+where+" "+
       " union "+
       " select 1,cust_id,full_name,sys_desc,lookup_desc,'',amt,0 "+
       "  from VIEW_summary_payment_cust_booked "+
       "  where "+where+" "+
       " union "+
       " select 2,cust_id,full_name,sys_desc,lookup_desc,'',amt,0 "+
       " from VIEW_summary_payment_cust "+
       " where "+where+" "+
       " order by 2,1 ";

 }




 
 
}
