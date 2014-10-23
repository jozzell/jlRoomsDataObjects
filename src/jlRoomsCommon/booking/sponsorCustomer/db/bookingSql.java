package jlRoomsCommon.booking.sponsorCustomer.db;

import jlRoomsCommon.objMgr;

/**
 * Title:        Room Tracking Web Applicaion (rtwa)
 * Description:
 * Copyright:    Copyright (c) 2003
 * Company:      Jozzell, llc
 * @author Lloyd Means
 * @version 1.0
 */

public class bookingSql  extends objMgr{
  public  final String
      sqlCustBookingList(String web){
        return    
      " select  r.cust_room_id,r.eff_date,r.total_cost,"+
      " v.vendor_name,v.addr1,v.city,v.state,s.sys_desc,l.lookup_desc,t.sys_desc,t.sys_id,r.block_id "+
      " ,(select '['+FLT_DEPARTURE_PLACE+'-'+flt_number+']' from flight_information where flt_id = r.flt_id and r.flt_id > 0)"+
      " from customer_room r, vendor v,lookup_sys s,lookup l,lookup_sys t "+
      " where r.vendor_id = v.vendor_id and r.cust_room_type = t.sys_id and "+
      " s.sys_id = v.vendor_type and l.lookup_id = r.lookup_id"+
      " and r.cust_id = ? and r.sponsor_id = ?"+
                sqlCustBookingList_sub(web);
  }

  public  String sqlCustBookingList_sub(String web){
      if (web == null) return "";
      return " and "+
              "  r.email_key = "+getEMailKeyStr(web)+" and "+
              "  v.email_key = "+getEMailKeyStr(web)+" and "+
              "  s.email_key = "+getEMailKeyStr(web)+" and "+
              "  l.email_key = "+getEMailKeyStr(web)+"  ";
  }
  public  String sqlCustRoom(String web){
   return " SELECT "+
   " cust_room_id,cust_id,block_id,eff_date,end_date,exp_date,rm_cost,sponsor_id,cust_room_type, "+
   " cust_room_cnt,cust_rollup_id,room_paid,room_comment,balance_due_date,balance_comment,created,rp_date, "+
   " primary_room,sponsor_hotel_id,sponsor_id "+
   " from customer_ROOM "+(web == null ? "":" where email_key = "+getEMailKeyStr(web));   
  }
  public  String sql_rooms_booked(String web){
      return 
     "select sum(cust_room_cnt),block_id "+
     " from customer_room "+
     " where sponsor_id = ? "+
              (web == null ? "":getEMailKeyEquals(web))+
     " group by block_id order by 2 ";
  }
  public  String sql_rooms_avl(String web){
      return 
      "select lt.lookup_desc,b.block_id, "+
      " vh.vendor_name,lk.lookup_desc,"+
      " b.rm_cost,b.block_comment,b.rm_cnt,lt.lookup_id "+
      " from  block b,lookup lk,lookup lt, "+
      "		sponsor_hotel sh,vendor vh "+
      "	where 	sh.sponsor_id = ? and 	b.sponsor_hotel_id = sh.sponsor_hotel_id and "+
      "		lk.lookup_type = 1 and	lt.lookup_type = 0 and 	lk.Lookup_rollup_id = lt.lookup_id and "+
      "		lk.lookup_id = b.lookup_id and 	vh.vendor_id = sh.hotel_id "+
              sql_rooms_avl_hotel_sub(web)+
      "	order by lt.lookup_desc,vh.vendor_name,lk.lookup_desc";
  }
  
  public  String sql_rooms_avl_hotel(String web){
      return 
      " select lt.lookup_desc,b.block_id, "+
      " vh.vendor_name,lk.lookup_desc,"+
      " b.rm_cost,b.block_comment,b.rm_cnt,lt.lookup_id "+
      " from  block b,lookup lk,lookup lt, "+
      "		sponsor_hotel sh,vendor vh "+
      "	where 	 vh.vendor_id = ? and sh.sponsor_id = ? and 	b.sponsor_hotel_id = sh.sponsor_hotel_id and "+
      "		lk.lookup_type = 1 and	lt.lookup_type = 0 and 	lk.Lookup_rollup_id = lt.lookup_id and "+
      "		lk.lookup_id = b.lookup_id and 	vh.vendor_id = sh.vendor_id "+
              sql_rooms_avl_hotel_sub(web)+
      "	order by lt.lookup_desc,lk.lookup_desc";
  }
  public  String sql_rooms_avl_hotel_sub(String web){
      if (web == null) return "";
      return " and "+
              "  b.email_key = "+getEMailKeyStr(web)+" and "+
              " lk.email_key = "+getEMailKeyStr(web)+" and "+
              " lt.email_key = "+getEMailKeyStr(web)+" and "+
              " sh.email_key = "+getEMailKeyStr(web)+" and "+
              " vh.email_key = "+getEMailKeyStr(web)+" ";
  }
  public  String sql_hotel(String web) {
      return "select "+
      " date_format(h.end_date,'%a %b %d'), "+
      " date_format(h.end_date,'%a %b %d'),"+
      " date_format(h.cutoff_date,'%a %b %d'),"+
      " h.service_charge,v.vendor_name,v.city,"+
      " v.zip,v.vendor_type,l.lookup_desc,h.sponsor_hotel_id"+
      " from sponsor_hotel h, vendor v, lookup l "+
      " where sponsor_id = ? and v.vendor_id = h.hotel_id and "+
      " l.lookup_id = v.vendor_type "+
              sql_hotel_sub(web)+
      " order by l.lookup_desc,v.vendor_name";
  }
  private  String sql_hotel_sub(String web){
      if (web == null) return "";
      return " and "+
              " h.email_key = "+getEMailKeyStr(web)+" and "+
              " v.email_key = "+getEMailKeyStr(web)+" and "+
              " l.email_key = "+getEMailKeyStr(web)+" ";
  }
  
}




/*
 * 
 * private  final String
      sqlCustBookingList =
      " select  r.cust_room_id,r.eff_date,r.total_cost,"+
      " v.vendor_name,v.addr1,v.city,v.state,s.sys_desc,l.lookup_desc,t.sys_desc,t.sys_id,r.block_id "+
      " ,(select '['+FLT_DEPARTURE_PLACE+'-'+flt_number+']' from flight_information where flt_id = r.flt_id and r.flt_id > 0)"+
      " from customer_room r, vendor v,lookup_sys s,lookup l,lookup_sys t "+
      " where r.vendor_id = v.vendor_id and r.cust_room_type = t.sys_id and "+
      " s.sys_id = v.vendor_type and l.lookup_id = r.lookup_id"+
      " and r.cust_id = ? and r.sponsor_id = ?"


      ,test="";

  private  final String
   sqlCustRoom =
   " SELECT "+
   " cust_room_id,cust_id,block_id,eff_date,end_date,exp_date,rm_cost,sponsor_id,cust_room_type, "+
   " cust_room_cnt,cust_rollup_id,room_paid,room_comment,balance_due_date,balance_comment,created,rp_date, "+
   " primary_room,sponsor_hotel_id,sponsor_id "+
   " from customer_ROOM ",

    sql_rooms_booked = "select sum(cust_room_cnt),block_id "+
     " from customer_room "+
     " where sponsor_id = ? "+
     " group by block_id order by 2 ",
    sql_rooms_avl = "select lt.lookup_desc,b.block_id, "+
      " vh.vendor_name,lk.lookup_desc,"+
      " b.rm_cost,b.block_comment,b.rm_cnt,lt.lookup_id "+
      " from  block b,lookup lk,lookup lt, "+
      "		sponsor_hotel sh,vendor vh "+
      "	where 	sh.sponsor_id = ? and 	b.sponsor_hotel_id = sh.sponsor_hotel_id and "+
      "		lk.lookup_type = 1 and	lt.lookup_type = 0 and 	lk.Lookup_rollup_id = lt.lookup_id and "+
      "		lk.lookup_id = b.lookup_id and 	vh.vendor_id = sh.hotel_id "+
      "	order by lt.lookup_desc,vh.vendor_name,lk.lookup_desc",
          
    sql_rooms_avl_hotel = "select lt.lookup_desc,b.block_id, "+
      " vh.vendor_name,lk.lookup_desc,"+
      " b.rm_cost,b.block_comment,b.rm_cnt,lt.lookup_id "+
      " from  block b,lookup lk,lookup lt, "+
      "		sponsor_hotel sh,vendor vh "+
      "	where 	 vh.vendor_id = ? and sh.sponsor_id = ? and 	b.sponsor_hotel_id = sh.sponsor_hotel_id and "+
      "		lk.lookup_type = 1 and	lt.lookup_type = 0 and 	lk.Lookup_rollup_id = lt.lookup_id and "+
      "		lk.lookup_id = b.lookup_id and 	vh.vendor_id = sh.vendor_id "+
      "	order by lt.lookup_desc,lk.lookup_desc",
    sql_hotel =
      "select "+
      " date_format(h.end_date,'%a %b %d'), "+
      " date_format(h.end_date,'%a %b %d'),"+
      " date_format(h.cutoff_date,'%a %b %d'),"+
      " h.service_charge,v.vendor_name,v.city,"+
      " v.zip,v.vendor_type,l.lookup_desc,h.sponsor_hotel_id"+
      " from sponsor_hotel h, vendor v, lookup l "+
      " where sponsor_id = ? and v.vendor_id = h.hotel_id and "+
      " l.lookup_id = v.vendor_type "+
      " order by l.lookup_desc,v.vendor_name";
 */