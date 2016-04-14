package jlRoomsCommon.block.db;

import jlRoomsCommon.objMgr;

public class blockSql extends objMgr{
   
    
             
  private  final String sql =
      " select  b.block_id,b.sponsor_hotel_id,b.lookup_id,b.rm_cnt,b.rm_cost, " +
      " b.block_comment,b.eff_date,b.end_date,b.flag_ind,b.sponsor_id,l.lookup_desc,v.vendor_name,v.city,v.vendor_id, " +
      " b.flt_id,v.vendor_type,s.NO_FEE_APPLYIED,s.service_charge,s.process_fee,s.HOTEL_FEE,    "+
       "  (SELECT max(CONCAT(flt_departure_place, CONCAT(' to ',flt_arrival_place)) )  FROM flight_information where flt_id = b.flt_id) "+   
       " ,(SELECT sum(w1.block_cost_cnt) FROM customer_room w1  where w1.block_id = b.block_id) "+
      " from block b,lookup l,sponsor_hotel s,vendor v where l.lookup_id = b.lookup_id and " +
      " s.sponsor_hotel_id =  b.sponsor_hotel_id and s.vendor_id = v.vendor_id ";
  private  final String
      
      sqlUpdateBlock =
      "update block set rm_cnt=?, rm_cost=?, block_comment=?, flag_ind=? where block_id = ?",
      sqlInsertBlock = "insert into Block"+
      " (sponsor_hotel_id,lookup_id,rm_cnt,rm_cost,block_comment,flag_ind,sponsor_id,flt_id,DB_TIMESTAMP)"+
      " values (?,?,?,?,?,?,?,?,?) ",
      // ----------------------------------------------------------------------------------
      sqlBrwBlockSubCnt =
      " select sum(cust_room_cnt) ,b.block_id from customer_room  c, block b " +
      " where c.block_id = b.block_id and b.sponsor_id = ? group by b.block_id ",
     sqlGetBlockId = "select max(block_id) from block where DB_TIMESTAMP = ? and sponsor_id = ? and lookup_id = ? and sponsor_hotel_id = ?"
     , sqlBrwBlockSub_XXXX =
      "select lt.lookup_desc type_desc,b.block_id,vh.vendor_name,lk.lookup_desc booked_type," +
      " b.rm_cost,b.block_comment,b.rm_cnt,vh.vendor_type,vh.vendor_id " +
      " from block b,lookup lk,lookup lt,sponsor_hotel sh,vendor vh " +
      " where 	sh.sponsor_id = ? and b.sponsor_hotel_id = sh.sponsor_hotel_id and " +
      " lk.Lookup_rollup_id = lt.lookup_id and lk.lookup_id = b.lookup_id and vh.vendor_id = sh.vendor_id " +
      " order by lt.lookup_desc,lk.lookup_desc "
      ;
  public  String sqlBrwBlockSub_XXXX(String web){
      if (web == null){
          return sqlBrwBlockSub_XXXX;
      } else {
          return 
            "select lt.lookup_desc type_desc,b.block_id,vh.vendor_name,lk.lookup_desc booked_type," +
            " b.rm_cost,b.block_comment,b.rm_cnt,vh.vendor_type,vh.vendor_id " +
            " from block b,lookup lk,lookup lt,sponsor_hotel sh,vendor vh " +
            " where 	sh.sponsor_id = ? and b.sponsor_hotel_id = sh.sponsor_hotel_id and " +
            " lk.Lookup_rollup_id = lt.lookup_id and lk.lookup_id = b.lookup_id and vh.vendor_id = sh.vendor_id " +
                 " and "+
             " b.email_key = "+getEMailKeyStr(web)+" and "+
             " lk.email_key = "+getEMailKeyStr(web)+" and "+
             " lt.email_key = "+getEMailKeyStr(web)+" and "+
             " sh.email_key = "+getEMailKeyStr(web)+" and "+ 
             " vh.email_key = "+getEMailKeyStr(web)+" "+
            " order by lt.lookup_desc,lk.lookup_desc ";
      }
  }
  public  String sqlGetBlockId(String web){
      if (web == null){
          return sqlGetBlockId;
      } else {
          return 
                  "select max(block_id) from block where DB_TIMESTAMP = ? and sponsor_id = ? and lookup_id = ? and sponsor_hotel_id = ?"+
                  getEMailKeyEquals(web)
     ;
      }
  }
  public  String sqlGrabBlockId(String web){
      if (web == null){
          return sqlGetBlockId;
      } else {
          return 
                  "select max(block_id) from block where sponsor_id = ? and lookup_id = ? and sponsor_hotel_id = ?"+
                  getEMailKeyEquals(web)
     ;
      }
  }
  public  String sqlBrwBlockSubCnt_V3(String web){
      if (sqlBrwBlockSubCnt == null){
          return sqlBrwBlockSubCnt;
      } else {
          return 
             " select sum(cust_room_cnt) ,b.block_id " +
                  " from customer_room  c, block b " +
             " where c.block_id = b.block_id and b.sponsor_id = ? and "+
                  " b.email_key = "+getEMailKeyStr(web)+" and "+
                  " c.email_key = "+getEMailKeyStr(web)+" "+
             "group by b.block_id "
     ;
      }
  }
  public  String sqlInsertBlock(String web){
      if (web == null){
          return sqlInsertBlock;
      } else {
          return 
                  "insert into Block"+
      " (sponsor_hotel_id,lookup_id,rm_cnt,rm_cost,block_comment,flag_ind,sponsor_id,flt_id,DB_TIMESTAMP  ,email_key)"+
      " values (?,?,?,?,?,?,?,?,?,"+getEMailKeyStr(web) + ") ";
      }
  }
  public  String sqlUpdateBlock(String web){
      if (web ==null){
          return sqlUpdateBlock;
      } else {
          return 
                  "update block set rm_cnt=?, rm_cost=?, block_comment=?, flag_ind=? where block_id = ? "+
                  getEMailKeyEquals(web);
      }
  }
  private final  String 
      sqlBlockSwitch =sql+ "  and b.sponsor_hotel_id in (select sponsor_hotel_id from block where block_id = ?) order by v.vendor_name",
      sqlBrwBlockSponsorHotelId = sql+ " and b.sponsor_hotel_id = ? and l.lookup_rollup_id = ? order by upper(l.lookup_desc),b.block_comment",
      sqlAvlBooking = sql+" and b.sponsor_id = ? and l.lookup_rollup_id = ? order by v.vendor_name,upper(l.lookup_desc),b.block_comment",
      sqlBlock = sql + " and b.block_id = ? ",
      sqlBlockEvent = sql + " and b.sponsor_id = ? ";
  
  public  final String sqlBlockEvent(String web){
      if (web == null){
          return sqlBlockEvent;
      } else {
          return sqlBlockEvent + webId(web);
      }
  }
          
  public  final String sqlBlock(String web){
      if (web == null){
          return sqlBlock;
      } else {
          return sqlBlock +webId(web);
      }
  }
          
  public  final String sqlAvlBooking(String web){
      if (web == null) {
          return sqlAvlBooking;
      } else {
          return sql+
                  " and b.sponsor_id = ? and l.lookup_rollup_id = ? "+
                   webId(web)+
                  "order by v.vendor_name,upper(l.lookup_desc),b.block_comment";
      }
  }
  public  final String sqlBrwBlockSponsorHotelId(String web){
      if (web == null){
          return sqlBrwBlockSponsorHotelId;
      } else {
          return sql+ 
                  " and b.sponsor_hotel_id = ? and l.lookup_rollup_id = ? "+
                  webId(web)+
                  " order by upper(l.lookup_desc),b.block_comment";
      }
  }
  public  final String sqlBlockSwitch(String web){
      if (web == null){
          return sqlBlockSwitch;
      } else {
          return sql+ "  and b.sponsor_hotel_id in (select sponsor_hotel_id from block where block_id = ?) "+webId(web);
      }
  }
          
          
  private  String webId(String web) {
      //" from block b,lookup l,sponsor_hotel s,vendor v
      return " and "+
             " b.email_key = "+getEMailKeyStr(web)+" and "+
             " l.email_key = "+getEMailKeyStr(web)+" and "+
             " s.email_key = "+getEMailKeyStr(web)+" and "+
             " v.email_key = "+getEMailKeyStr(web)+" "
              ;
  }
}
/*
 * 
 * 
 * 
 */