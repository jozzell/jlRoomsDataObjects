package jlRoomsCommon.rpt.db.sql;

import jlRoomsCommon.objMgr;

public class htmlPaymentCustVendorSql extends objMgr {
    
   
  public  String sqlBookingCost(){
      return sqlBookingCost(true,null);
  }
  public  String sqlBookingCost(boolean cust,String web){
      if (web == null){
          web = "";
      } else {
        web= " and "+
             " cr.email_key = "+this.getEMailKeyStr(web)+" and "+
             " v.email_key = "+this.getEMailKeyStr(web)+" and "+
             //" s.email_key = "+this.getEMailKeyStr(web)+" and "+
             " lk.email_key = "+this.getEMailKeyStr(web)+" and "+
             " b.email_key = "+this.getEMailKeyStr(web)+" " ;
    }
      
      return " select cr.rm_cost,cr.total_cost,cr.num_days,b.rm_cost bRmCost, "+
      " s.sys_desc,lk.lookup_desc,v.vendor_name,cr.ROOMMATE_SPLIT,v.vendor_type,cr.CUST_ROOM_CNT,cr.block_id, "+
      " cr.BLOCK_COST_CNT,cr.BALANCE_DUE_DATE from customer_room cr left OUTER join vendor v on v.vendor_id = cr.vendor_id, lookup_sys s,lookup lk,block b "+
      " where s.sys_id = cr.cust_room_type and lk.lookup_id = cr.lookup_id and b.block_id = cr.block_id and cr.sponsor_id = ? " +
              (cust ? " and cr.cust_id = ? ":"")
              +web+" order by v.vendor_name ";
  }
  
  public  String sqlBookingCostVendor(){
      return sqlBookingCostVendor(true,null);
  }
  public  String sqlBookingCostVendor(boolean  vendor,String web){
      if (web == null){
          web = "";
      } else {
        web= " and "+
             " cr.email_key = "+this.getEMailKeyStr(web)+" and "+
             " v.email_key = "+this.getEMailKeyStr(web)+" and "+
             //" s.email_key = "+this.getEMailKeyStr(web)+" and "+
             " lk.email_key = "+this.getEMailKeyStr(web)+" and "+
             " b.email_key = "+this.getEMailKeyStr(web)+" " ;
    }
   return   " select sum(cr.rm_cost),sum(cr.total_cost),sum(cr.num_days),sum(b.rm_cost) bRmCost, "+
     " s.sys_desc,lk.lookup_desc,v.vendor_name,sum(cr.ROOMMATE_SPLIT),v.vendor_type,sum(cr.CUST_ROOM_CNT),cr.block_id, "+
     " sum(cr.BLOCK_COST_CNT),max(cr.BALANCE_DUE_DATE) "+
     " from customer_room cr  left OUTER join vendor v on v.vendor_id = cr.vendor_id, lookup_sys s,lookup lk,block b "+
     " where s.sys_id = cr.cust_room_type and lk.lookup_id = cr.lookup_id "+
     " and b.block_id = cr.block_id and cr.sponsor_id = ? "+
           (vendor ? " and cr.vendor_id = ? ":"")
           +web+
     " group by s.sys_desc,lk.lookup_desc,v.vendor_name,cr.ROOMMATE_SPLIT,v.vendor_type,cr.block_id,cr.BALANCE_DUE_DATE "+
     " order by v.vendor_name "

      ;
  }
  public  String getPaymentDetail(boolean booked, boolean cust) {
        return getPaymentDetail(booked, cust, null);
    }
  /// ***************************************************************************************
    public  String getPaymentDetail(boolean booked, boolean cust, String email) {
        return getPaymentDetailPrefix(booked,email)+
                " and " + (cust ? "b.cust_id = ? " : "b.vendor_id = ?");
    }
     public  String getPaymentDetail(boolean booked,  String email) {
        return getPaymentDetailPrefix(booked,email);
                //" and " + (cust ? "b.cust_id = ? " : "b.vendor_id = ?");
    }
    private  String getPaymentDetailPrefix(boolean booked, String email) {
        return " select 1,b.amt_rec,b.db_timestamp, "
                + "       b.vendor_id,v.vendor_type,v.vendor_name, "
                + "       l.lookup_id,l.lookup_desc, "
                + "       s.sys_id,s.sys_desc, "
                + "       c.cust_id,c.last_name,c.first_name,m.lookup_desc, "
                + (booked ? "2" : "1") + "," + (booked ? "CUST_PAYMENT_BOOKED_ID" : "CUST_PAYMENT_ID")
                + "       from customer_payment" + (booked ? "_booked" : "") + " b"
                + "        left OUTER join customer c on c.cust_id = b.cust_id "
                + "        left OUTER join lookup_sys s on b.payment_type = s.sys_id "
                + "        left OUTER join lookup l on l.lookup_id = b.lookup_id "
                + "        left OUTER join vendor v on v.vendor_id = b.vendor_id "
                + " left OUTER join lookup m on b.chk_type = m.lookup_id "
                + "      where  "
                + "       b.sponsor_id = ? and b.db_timestamp_flg = 0  ";

    }
    
    

}
