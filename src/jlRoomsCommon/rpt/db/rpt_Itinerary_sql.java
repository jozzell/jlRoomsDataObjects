/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsCommon.rpt.db;

import jlRoomsCommon.objMgr;

/**
 *
 * @author lmeans
 */
public class rpt_Itinerary_sql extends objMgr  {
   public  String getItinerary_CustomerBooking(String web){ 
       return
  " select DATE_FORMAT(r.eff_date, '%a %b %d %Y') eff_date, DATE_FORMAT(r.end_date, '%a %b %d %Y') end_date, "+
	" 	r.rm_cost,  r.cust_room_cnt, r.room_comment, DATE_FORMAT(r.balance_due_date, '%a %b %d %Y') balance_due_date, r.balance_comment,    "+
        "       r.ROOMMATE_SPLIT,v.vendor_name,l.sys_desc,v.vendor_type,  "+
        "       r.ROOMMATE_ID,r.TOTAL_COST,r.flt_id,DATE_FORMAT(s.BALANCE_DUE_BY, '%a %b %d %Y') BALANCE_DUE_BY,  "+
	" 	DATE_FORMAT(s.cutoff_date , '%a %b %d %Y') cutoff_date, "+
	" 	DATE_FORMAT(s.cancel_date , '%a %b %d %Y') cancel_date, "+
	" 	DATE_FORMAT(s.CHECK_DUE_BY , '%a %b %d %Y') CHECK_DUE_BY,x.lookup_desc, "+
        "  v.addr1,v.addr2,v.city,v.state,v.zip,v.phone,r.cust_room_type, "+
       "   r.end_date - r.eff_date,lx.rpt_desc, "+
	" 	f.FLT_NUMBER,f.FLT_COMMENt, "+
	" 	DATE_FORMAT(f.FLT_ARRIVAL_DATE , '%a %b %d %Y @ %h:%i %p' ) FLT_ARRIVAL_DATE,f.FLT_ARRIVAL_TIME,f.FLT_ARRIVAL_PLACE, "+
	" 	DATE_FORMAT(f.FLT_DEPARTURE_DATE , '%a %b %d %Y @ %h:%i %p' ) FLT_DEPARTURE_DATE,f.FLT_DEPARTURE_TIME,f.FLT_DEPARTURE_PLACE "+
               " ,lx.sys_desc,r.ticket_info"+
         "      from lookup x, "+
	" 		  customer_room r  "+
	" 			    left OUTER join flight_information f on f.flt_id = r.flt_id "+" and f.email_key = "+this.getEMailKeyStr(web)+
	" 		            left OUTER join lookup_sys l on r.cust_room_type = l.sys_id "+
        "       ,vendor v left OUTER join lookup_sys lx on v.vendor_type = lx.sys_id, "+
	" 		  block b,sponsor_hotel s  "+
         "      where x.lookup_id = r.lookup_id and v.vendor_id = r.vendor_id and b.block_id = r.block_id and b.sponsor_hotel_id = s.sponsor_hotel_id "+
               " and r.email_key = "+this.getEMailKeyStr(web)+" and b.email_key = "+this.getEMailKeyStr(web)+
               " and x.email_key = "+this.getEMailKeyStr(web)+" and v.email_key = "+this.getEMailKeyStr(web)+
               " and s.email_key = "+this.getEMailKeyStr(web)         ;
   }
   public String getFlt(String web){
       return getItinerary_CustomerBooking(web)+ " and r.cust_id = ? and r.sponsor_id = ? and v.vendor_type=?";
   }
}


/*

*/