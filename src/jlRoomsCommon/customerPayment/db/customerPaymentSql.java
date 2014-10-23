package jlRoomsCommon.customerPayment.db;

import jlRoomsCommon.objMgr;

public class customerPaymentSql  extends objMgr {
  private  final String
      sql =
      " select  p.cust_payment_id, p.cust_room_id, p.payment_type, p.amt_rec, " +
      " p.chk_number, p.chk_type, p.region, p.db_timestamp, p.roommate_id, " +
      " p.sponsor_id, pt.lookup_desc pay, pt.lookup_desc chk " +
      " from customer_payment p,lookup pt,lookup ct " +
      " where pt.lookup_id =* p.payment_type and ct.lookup_id =* p.chk_type ";
  private  final String
      sqlInsertBookedFirst = "update CUSTOMER_PAYMENT_BOOKED set DB_TIMESTAMP_FLG = 1 where DB_TIMESTAMP_FLG = 0 and cust_room_id = ?",
      sqlInsertBooked =  "insert into CUSTOMER_PAYMENT_BOOKED " +
      " (cust_room_id,cust_id,db_user,payment_type,amt_rec," +
      " block_id,vendor_type,sponsor_id,DB_TIMESTAMP_FLG,vendor_id,lookup_id,chk_type,DB_TIMESTAMP) " +
      " values (?,?,?,?,?,?,?,?,?,?,?,?,?) ",
       //CUSTOMER_PAYMENT_BOOKED
       sqlInsertPayment =  "insert into CUSTOMER_PAYMENT " +
       " (cust_room_id,cust_id,db_user,payment_type,amt_rec," +
       " block_id,vendor_type,sponsor_id,DB_TIMESTAMP_FLG,vendor_id,lookup_id,comments,notes,DB_TIMESTAMP,chk_type) " +
       " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ",
      sqlDeletePayment = "delete from CUSTOMER_PAYMENT where cust_payment_id = ? ",
      sqlDeletePaymnetBooked = "delete from CUSTOMER_PAYMENT_BOOKED where CUST_PAYMENT_BOOKED_ID = ?",

      sqlUpdatePayment = "update CUSTOMER_PAYMENT set db_timestamp = ? where cust_payment_id = ?",
      sqlUpdatePaymentBooked = "update CUSTOMER_PAYMENT_BOOKED set DB_TIMESTAMP = ? where CUST_PAYMENT_BOOKED_ID = ?",
      sqlPayment = sql + " and p.cust_payment_id = ? ";
  
  
  public  String sqlInsertBookedFirst(String web){
      if (web == null){
          return sqlInsertBookedFirst;
      } else {
          return 
                  "update CUSTOMER_PAYMENT_BOOKED set DB_TIMESTAMP_FLG = 1 where DB_TIMESTAMP_FLG = 0 and cust_room_id = ?"+
                  getEMailKeyEquals(web);
      }
  }
  public  String sqlInsertBooked(String web){
      if (web == null){
          return sqlInsertBooked;
      } else {
          return "insert into CUSTOMER_PAYMENT_BOOKED " +
      " (cust_room_id,cust_id,db_user,payment_type,amt_rec," +
      " block_id,vendor_type,sponsor_id,DB_TIMESTAMP_FLG,vendor_id,lookup_id,chk_type,email_key) " + //DB_TIMESTAMP,
      " values (?,?,?,?,?,?,?,?,?,?,?,?,"+ getEMailKeyStr(web) +" ) "; //?,
      }
  }
  public  String sqlInsertPayment(String web){
     if (web == null){
         return sqlInsertPayment;
     } else {
         return "insert into CUSTOMER_PAYMENT " +
       " (cust_room_id,cust_id,db_user,payment_type,amt_rec," +
       " block_id,vendor_type,sponsor_id,DB_TIMESTAMP_FLG,vendor_id,lookup_id,comments,notes,DB_TIMESTAMP,chk_type,email_key) " +
       " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"+ getEMailKeyStr(web)+") ";
     }
  }
  public  String sqlDeletePayment(String web){
      if (web == null){
          return sqlDeletePayment;
      } else {
          return sqlDeletePayment+getEMailKeyEquals(web);
      }
  }
  public  String sqlDeletePaymnetBooked(String web){
      if (web == null){
          return sqlDeletePaymnetBooked;
      } else {
          return sqlDeletePaymnetBooked+getEMailKeyEquals(web);
      }
  }
  public  String sqlUpdatePayment(String web){
      if (web == null){
          return sqlUpdatePayment;
      } else {
          return sqlUpdatePayment+getEMailKeyEquals(web);
      }
  }
  public  String sqlUpdatePaymentBooked(String web) {
      if (web == null){
          return sqlUpdatePaymentBooked;
      } else {
          return sqlUpdatePaymentBooked+getEMailKeyEquals(web);
      }
  }
  
  
  public  String sqlPayment(String web){
      if (web == null){
          return sqlPayment;
      } else {
          return sqlPayment+webId(web);
      }
  }
  private  String webId(String web) {
      //" from block b,lookup l,sponsor_hotel s,vendor v
      return " and "+
             "  p.email_key = "+getEMailKeyStr(web)+" and "+
             " pt.email_key = "+getEMailKeyStr(web)+" and "+
             " ct.email_key = "+getEMailKeyStr(web)+" "
              ;
  }
}
