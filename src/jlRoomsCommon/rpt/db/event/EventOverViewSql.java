package jlRoomsCommon.rpt.db.event;

public class EventOverViewSql {

  //and"+(cust ? "b.cust_id > 0" : "b.vendor_id > 0")
  public  String getPaymentOverview(boolean booked,boolean cust) {
    return getPaymentOverview(booked," and "+(cust ? "b.cust_id > 0" : "b.vendor_id > 0"));
  }

  public  String getPaymentOverview(boolean booked) {
    return getPaymentOverview(booked,"");
  }
  public  String getPaymentOverview(boolean booked,String and) {
    return " select "+(booked ? 1 : 2)+ ",v.vendor_name,c.last_name,c.first_name,max(b.db_timestamp),sum(b.amt_rec),b.vendor_id,v.vendor_type,c.cust_id "+
      " from customer_payment"+(booked ? "_booked":"")+" b"+
      "  left OUTER join customer c   on c.cust_id = b.cust_id"+
      "  left OUTER join vendor v     on v.vendor_id = b.vendor_id"+
      " where "+
      " b.sponsor_id = ? and db_timestamp_flg = 0 "+and+
      " group by b.vendor_id,v.vendor_type,v.vendor_name,c.cust_id,c.last_name,c.first_name";
  }

  public  final String
      sqlPg01Summary =
      " select ((b.eff_date+1) - b.end_date)/1000/60/24 days,count(*) cnt,max(cr.cust_room_id) id,"+
     "  max(b.rm_cnt) rsv_cnt,max(b.rm_cost) rsv_cost,  s.sys_desc, "+
      " b.block_id,b.block_comment,b.eff_date,b.end_date,b.flag_ind,b.sponsor_id,l.lookup_desc,v.vendor_name,v.city, "+
      " s.sponsor_hotel_id"+
      " from block b left OUTER join customer_room cr on cr.block_id = b.block_id, "+
      " lookup l,sponsor_hotel s,vendor v,lookup_sys s "+
      " where l.lookup_id = b.lookup_id and  s.sys_id = v.vendor_type and "+
      " s.sponsor_hotel_id =  b.sponsor_hotel_id and s.vendor_id = v.vendor_id and "+
      " b.sponsor_id = ? "+
      " group by s.sys_desc,b.block_id,b.block_comment,b.eff_date,b.end_date,"+
      " b.flag_ind,b.sponsor_id,l.lookup_desc,v.vendor_name,v.city,s.sponsor_hotel_id "+
      " order by s.sys_desc,v.vendor_name,v.city ",
      sqlPg01CustSummary =
      " select r.eff_date, r.end_date, r.rm_cost,  "+
      "        r.cust_room_cnt, r.room_comment, r.balance_due_date, r.balance_comment,    "+
      "        r.ROOMMATE_SPLIT,v.vendor_name,l.sys_desc,v.vendor_type, "+
      "        r.ROOMMATE_ID,r.TOTAL_COST,r.flt_id,s.BALANCE_DUE_BY, s.cutoff_date,s.cancel_date,s.CHECK_DUE_BY,x.lookup_desc,"+
      "   v.addr1,v.addr2,v.city,v.state,v.zip,v.phone,r.cust_room_type,"+
      "   r.end_date - r.eff_date"+
      "        from lookup x,customer_room r "+
      "        left OUTER join lookup_sys l on r.cust_room_type = l.sys_id, "+
      "        vendor v ,block b,sponsor_hotel s "+
      "        where x.lookup_id = r.lookup_id and v.vendor_id = r.vendor_id and b.block_id = r.block_id and b.sponsor_hotel_id = s.sponsor_hotel_id and"+
      "       r.cust_id = ? and r.sponsor_id = ?"







  ;


}
