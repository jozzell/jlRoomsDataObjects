/*
 *

#1)
#2)
#3)
#4)
#5)
#6)
#7)
#8)
#9)
 *
 * */
package jlRoomsCommon.rpt.db;

public class rptFirstNightSql2 {


    public  void doStuff() {

    }
  /*
      ----------------------------------------------------------------
      ----------------------------------------------------------------
  */

    public  String getRptCustomerDetail(String where) {
      return getRptCustomerDetail(where,"");
    }
  public  String getRptCustomerDetail(String where,String in) {
    //
    return
            //select *  from rptView_Vendor_payment order by 4,5,2,8 desc
            //select * from rptView_Vendor_payment order by 4,5,2,8;

    
        " select 0,cust_id,rm_cnt,vendor_name,rm_type,lookup_desc,booking,total_cost * -1,db_timestamp,full_name,cust_room_type from VIEW_summary_room_detail_ver2 where "+in+" "+where+" "+
        " union select 1,cust_id,0,'','','',sys_desc,amt,db_timestamp,full_name,-6 from VIEW_summary_payment_cust_booked_detail where "+where+" "+
        " union select 2,cust_id,0,'','','',sys_desc,amt,db_timestamp,full_name,-6 from VIEW_summary_payment_cust_detail where "+where+" "+
        " order by 2,1 ";
  }
  /*
     ----------------------------------------------------------------
     ----------------------------------------------------------------
 */

public  String getRptVendorDetail(String where) {
  return getRptVendorDetail(where,"");
}
  public  String getRptVendorDetail(String where,String in) {
    System.out.println(in+where);
    return
        " select 0,vendor_id,rm_cnt,vendor_name,rm_type,room,booking,total_cost,db_timestamp from VIEW_summary_room_vendor_detail where "+in+" "+where+" "+
        " union select 1,vendor_id,0,vendor_name,'','',lookup_desc,amt,db_timestamp from VIEW_summary_payment_vendor_detail where "+where+" "+
        " union select 2,vendor_id,0,vendor_name,'','',lookup_desc,amt,db_timestamp from VIEW_summary_payment_vendor_booked_detail where "+where+" "+
        " order by 2,1 ";

  }

}
