/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsCommon.rpt.db;

/**
 *
 * @author lmeans
 */
public class rptSql {
     public  final String
             sqlTEST = "",
     sql_rpt_flt_v2 =
             " SELECT f.FLT_DEPARTURE_DATE,f.FLT_ARRIVAL_DATE,v.vendor_name, "+
             " CONCAT(f.FLT_DEPARTURE_PLACE, CONCAT(' to ',f.FLT_ARRIVAL_PLACE)) flt "+
              " FROM flight_information f,vendor v "+
             " where v.vendor_id = f.vendor_id and "+
             " f.email_key = ? ",
     sql_rpt_client_check_in_V2 =
             " SELECT count(*),ADDDATE(cr.eff_date,  x.idtmpCntr),v.vendor_name "+
             " FROM customer_room cr,tmpcntr x,vendor v "+
             " where "+
	             " x.idtmpCntr < cr.num_days and "+
	             " v.vendor_id = cr.vendor_id and "+
	             " v.vendor_type != -12 and "+
	             " cr.sponsor_id = ? and cr.email_key = ? and "+
	             " cr.cust_room_type in (-5,-6) "+
             " group by ADDDATE(cr.eff_date,  x.idtmpCntr),v.vendor_name "+
             " order  by 3,2 ",
     sql_rpt_client_check_in_XXX =
            " select count(*), v.vendor_name, sub.dPick,v.vendor_id,s.sys_desc "+
            " from customer_room cr,(SELECT ADDDATE(xc.eff_date,  x.idtmpCntr) dPick "+
            " FROM tmpCntr x,customer_room xc where xc.sponsor_id = ? and xc.email_key = ?) sub,vendor v,lookup_sys s "+
            " where cr.sponsor_id = ? and cr.email_key = ? and "+
            " sub.dPick >= DATE(cr.eff_date) and sub.dPick < cr.end_date and cr.cust_room_type in (-5,-6) "+
             " and v.vendor_id = cr.vendor_id and s.sys_id = v.vendor_type "+
            " group by v.vendor_name, sub.dPick ,v.vendor_id ,s.sys_desc order by 2,3",
     sql_rpt_1st_Night_V2 = "select cr.cust_room_type,v.vendor_id,s.sys_desc,v.vendor_name,v.addr1, "+
                " l.lookup_desc,b.block_comment,c.last_name,c.first_name,cr.rm_cost, "+
                " cr.cust_room_cnt,cr.roommate_split "+
                " from customer c,customer_room cr,block b,lookup l,vendor v,lookup_sys s "+
                " where cr.cust_id = c.cust_id and b.block_id = cr.block_id and s.sys_id = cr.cust_room_type and "+
                " l.lookup_id = cr.lookup_id and v.vendor_id = cr.vendor_id and l.lookup_rollup_id= -2  "+
                " and cr.cust_room_type in (-5,-6) "+
                " and cr.sponsor_id = ? and v.vendor_id = ? and cr.email_key = ?  and v.email_key = ? order by v.vendor_name,c.last_name,c.first_name";
}
