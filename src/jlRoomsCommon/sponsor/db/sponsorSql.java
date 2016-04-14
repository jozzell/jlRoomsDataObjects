package jlRoomsCommon.sponsor.db;

import jlRoomsCommon.objMgr;

public class sponsorSql extends objMgr {
    
  private  final String
          sqlXX="",
  sql_sponsor =
   "select "+
        " s.sponsor_id,	s.eff_date,s.sponsor_desc,s.end_date,"+
        " s.flag_ind,s.process_fee,s.process_fee_hotel,s.process_fee_car,"+
        " s.process_fee_air,s.process_fee_ticket,s.process_fee_type,"+
        " s.process_fee_train,s.inv_comment,v.vendor_id,v.vendor_name,s.PROCESS_FEE_CRUISE,"+
        " s.DB_TIMESTAMP,s.CUST_ID, DATE_FORMAT(eff_date, '%a %b %d %H:%i'),DATE_FORMAT(end_date, '%a %b %d %H:%i')"+
         
    " from sponsor s , vendor v where  v.vendor_id = s.vendor_id ";
  private  final String
    //sql_sponsor_default = sql_sponsor+"  and s.cust_id = ? and s.eff_date >= ? order by s.sponsor_desc,s.DB_TIMESTAMP desc",
    

    
    sql_sponsor_update = "update sponsor set "+
       " eff_date = ?,sponsor_desc= ?,end_date= ?,"+
       " flag_ind= ?,process_fee= ?,process_fee_hotel= ?,process_fee_car= ?,"+
       " process_fee_air= ?,process_fee_ticket= ?,process_fee_type= ?,"+
       " process_fee_train= ?,inv_comment= ?,vendor_id= ?,PROCESS_FEE_CRUISE=?"+
       " where sponsor_id = ? ",
    sql_sponsor_insert = "insert into sponsor  "+
       " (eff_date,sponsor_desc,end_date,"+
       " flag_ind,process_fee,process_fee_hotel,process_fee_car,"+
       " process_fee_air,process_fee_ticket,process_fee_type,"+
       " process_fee_train,inv_comment,vendor_id,PROCESS_FEE_CRUISE,CUST_ID)"+
       " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) "
          ;
  
  public  String sql_sponsor_update(String web){
      if (web == null){
          return sql_sponsor_update;
      } else {
          return sql_sponsor_update+this.getEMailKeyEquals(web);
      }
  }
  public  String sql_sponsor_insert(String web){
      if (web == null){
          return sql_sponsor_insert;
      } else {
          return "insert into sponsor  "+
       " (eff_date,sponsor_desc,end_date,"+
       " flag_ind,process_fee,process_fee_hotel,process_fee_car,"+
       " process_fee_air,process_fee_ticket,process_fee_type,"+
       " process_fee_train,inv_comment,vendor_id,PROCESS_FEE_CRUISE,CUST_ID,keyStr,email_key)"+
       " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"+this.getEMailKeyStr(web) +") "
          ;
      }
      
  }
  
  private  final String
          sql_sponsor_next_Demo = sql_sponsor+" and s.keyStr = ? and s.db_timestamp >= DATE_ADD(now(), INTERVAL -3 DAY)",
          sql_sponsor_next = sql_sponsor+" and s.keyStr = ?",
          sql_sponsor_seek = sql_sponsor+"  and upper(s.sponsor_desc) like upper(?) order by s.sponsor_desc",
          sql_getSponsor = sql_sponsor +"  and s.sponsor_id = ? ",
          sql_sponsor_default = "  and s.cust_id = ? and s.eff_date >= ? order by s.sponsor_desc,s.DB_TIMESTAMP desc",
          sql_get_cust_sponsor_bean = sql_sponsor +" and s.sponsor_id in (select max(sponsor_id) from sponsor where cust_id = ?)"
          ;
 
  public  String sql_sponsor_next(String web,boolean demo){
      if (web == null){
          return sql_sponsor_next;
      } else {
          if (!demo){
            return sql_sponsor_next+getWebID(web);
          } else {
              return sql_sponsor_next_Demo+getWebID(web);
          }
      }
  }
  public  String sql_sponsor_seek(String web){
      if (web == null){
          return sql_sponsor_seek;
      } else {
          return sql_sponsor+"  and upper(s.sponsor_desc) like upper(?)"+getWebID(web) +" order by s.sponsor_desc";
      }
  }
  public  String sql_getSponsor(String web){
      if (web == null){
          return sql_getSponsor;
      } else {
          return sql_getSponsor+getWebID(web);
      }
  }
  public  String sql_get_cust_sponsor_bean(String web){
      if (web== null ){
          return sql_get_cust_sponsor_bean;
      } else {
          return sql_sponsor +
                  " and s.sponsor_id in (select max(sponsor_id) from sponsor where cust_id = ? "+ this.getEMailKeyEquals(web)+")"+getWebID(web);
      }
  }
  public  String sql_sponsor_default(String webID){
      if (webID == null){
        return sql_sponsor+sql_sponsor_default;
      } else {
        return  sql_sponsor+getWebID(webID)+sql_sponsor_default;
      }
  }
  private  String getWebID(String WebID){
      WebID = " '"+WebID+"' ";
      return  " and s.email_key = "+WebID+" and v.email_key = "+WebID ;
  }
}