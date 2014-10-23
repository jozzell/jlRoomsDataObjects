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
public class rptviewSql extends objMgr {
  
    // ======================================================================================
    /*
     *      **************************************************************
     */
    // ======================================================================================
    
    public  String rptView_payment_type_summaryXXX(String web){
      return "select * from rptview_payment_type_summary "+
            " where sponsor_id in (?)  "+(web == null ? "":this.getEMailKeyEquals(web))+
              " order by 1";
              
    }
    
   
             
    // ================================================================================  
    public  String getEventBooking(boolean booking){
        return "select v.vendor_id,cr.cust_room_type,c.last_name+', '+c.first_name,l.lookup_desc, "+
                " v.vendor_name,sys_desc,cr.total_cost,cr.roommate_split, "+
                " cr.CUST_ROOM_CNT,cr.NUM_DAYS"+
                " from customer c,lookup l,customer_room cr,vendor v,lookup_sys s "+
                " where l.lookup_id = cr.lookup_id and c.cust_id = cr.cust_id and "+
                " v.vendor_id = cr.vendor_id and cr.cust_room_type = s.sys_id and "+
                " cr.sponsor_id = ? and cr.cust_room_type "+(booking ? " in ": " not in ")+ " (-5,-6) "+
                " order by 5,3 ";
        }
    public  final String
            strZZR= "ser",

            rptArv = "select c.last_name+', '+c.first_name,cr.eff_date,cr.end_date,v.vendor_name, "+
            " l.lookup_desc,cr.room_comment,cr.cust_room_cnt,roommate_split "+
            " from customer c,customer_room cr,vendor v,lookup l "+
            " where c.cust_id = cr.cust_id and v.vendor_id = cr.vendor_id  "+
            " and l.lookup_id = cr.lookup_id and  "+
            " cr.cust_room_type in (-5,-6) and cr.sponsor_id = ? "+
            " order by v.vendor_name,v.vendor_id,c.last_name,c.first_name",

   

           
            rptview_payment_type_sponsor = " select * from rptview_payment_type "+
            " where sponsor_id in (?) order by 1",
            
            rptview_payment_type_daterange = " select * from rptview_payment_type "+
            " where db_timestamp >= ? and db_timestamp < ? order by 1",



           
            
            rptView_Vendor_payment_summary  = "select * from rptView_Vendor_payment_summary "+
            " where sponsor_id in (?) order by 5,2",
            

           
            
            
            

            sql_getRoommate_listing =
                "select r1.cust_id,r2.cust_id,r1.rm_id,r1.sys_id,c1.last_name,c1.first_name,c2.last_name,c2.first_name,s.sys_desc "+
                "from roommate_cust r1,roommate_cust r2,customer c1,customer c2,lookup_sys s "+
                "where r1.roommate_id in (select max(roommate_id) from roommate_cust where sponsor_id = ? group by rm_id) "+
                "and c1.cust_id = r1.cust_id and c2.cust_id = r2.cust_id and s.sys_id = r1.sys_id and r1.rm_id = r2.rm_id "+
                "order by r1.sys_id,r1.cust_id,c1.last_name,c1.first_name,c2.last_name,c2.first_name",
            sql_rpt_get_day_cnt =
                " select tdate.batch_id,tdate.eff,count(*) cnt,v.vendor_id,v.vendor_name,l.lookup_desc,l.lookup_id "+
                " from customer_room cr ,vendor v,lookup l, "+
                " (select x.batch_id + (select min(eff_date)  from customer_room where sponsor_id = 188) eff,x.batch_id "+
                " from batch x) tdate  "+
                " where cr.eff_date <= tDate.eff and cr.end_date >= tDate.eff and v.vendor_id = cr.vendor_id and "+
                " l.lookup_id = cr.lookup_id and "+
                " cr.sponsor_id = ? and cr.cust_room_type in (-5,-6)  "+
                " group by tdate.eff,v.vendor_name,v.vendor_id,l.lookup_desc,tdate.batch_id,l.lookup_id "+
                " order by v.vendor_id,tdate.eff,l.lookup_desc";

    public  void doStuff() {

    }

}
