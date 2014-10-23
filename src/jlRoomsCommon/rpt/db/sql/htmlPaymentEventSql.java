package jlRoomsCommon.rpt.db.sql;

public class htmlPaymentEventSql {
  public  String
      sqlBookedSummary =
      " select 3, v.vendor_name, max(cr.db_timestamp), sum(cr.total_cost), "+
      " cr.vendor_id,count(*) "+
      " from customer_room cr, vendor v "+
      " where cr.vendor_id = v.vendor_id and cr.sponsor_id = ? and "+
      " cr.cust_room_type not in( -23, -30) "+
      " group by v.vendor_name, cr.vendor_id "+
      " union "+
      " select 3, null, max(cr.db_timestamp), sum(cr.total_cost),  "+
      " 0,count(*)  "+
      " from customer_room cr, customer c  "+
      " where c.cust_id = cr.cust_id and cr.sponsor_id = ? and "+
      " cr.cust_room_type not in( -23, -30) "

      ;

  public  String getPaymentSummary(boolean booked) {
  return " select "+(booked ? 1 : 2)+ ",v.vendor_name,max(b.db_timestamp),sum(b.amt_rec),b.vendor_id,count(*)"+
    " from customer_payment"+(booked ? "_booked":"")+" b"+
    "  left OUTER join vendor v     on v.vendor_id = b.vendor_id"+
    " where "+
    " b.sponsor_id = ? and db_timestamp_flg = 0 "+
    " group by b.vendor_id,v.vendor_type,v.vendor_name";
}

}
