/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jlRoomsCommon.rpt.db;

/**
 *
 * @author lmeans
 */
public class rptFirstNightSql_View {
    public  void doStuff() {

    }
    public  final String
         view2_99 = "create VIEW rpt_summary_room_detail_ver2 "+
         "       select 1,c.cust_id as cust_id,cr.vendor_id as vendor_id,cr.sponsor_id as sponsor_id, "+
    " c.last_name+', '+c.first_name as full_name, v.vendor_name as vendor_name,ls.sys_desc as rm_type,rt.sys_desc as booking, "+
   " sum(cr.rm_cost) as rm_cost,sum(cr.total_cost) as total_cost,sum(cr.num_days) as num_days,sum(cr.cust_room_cnt) as rm_cnt, "+
   " max(cr.db_timestamp) as db_timestamp,l.lookup_desc as lookup_desc, l.LOOKUP_ROLLUP_ID as LOOKUP_ROLLUP_ID,cr.cust_room_type "+
   " from    customer c ,   customer_room cr, "+
   " vendor v,lookup_sys ls,lookup l,lookup_sys rt "+
   " where rt.sys_id = cr.cust_room_type and c.cust_id = cr.cust_id and cr.vendor_id = v.vendor_id and  "+
   " v.vendor_type = ls.sys_id and l.lookup_id = cr.lookup_id and cr.cust_room_type in (-5,-6) "+
   " group by cr.sponsor_id,c.cust_id,cr.vendor_id,c.last_name,c.first_name, v.vendor_name,ls.sys_desc, "+
    " rt.sys_desc,l.lookup_desc,cr.db_timestamp, l.LOOKUP_ROLLUP_ID,cr.cust_room_type",

            view94 =" alter VIEW VIEW_summary_payment_vendor AS "+
            " select 2,cp.sponsor_id,cp.vendor_id,v.vendor_name,s.sys_desc,l.lookup_desc, "+
                     " sum(cp.amt_rec) amt,max(cp.db_timestamp) db_timestamp "+
                     " from vendor v, "+
                     " customer_payment cp "+
                     "  left outer join lookup_sys s on s.sys_id = cp.payment_type "+
                      " left outer join lookup l on l.lookup_id = cp.lookup_id "+
                      " left outer join customer c on c.cust_id = cp.cust_id "+
               " where v.vendor_id = cp.vendor_id "+
                  " group by cp.sponsor_id,cp.vendor_id,v.vendor_name,s.sys_desc,l.lookup_desc "
         ,
         view95 =  " alter VIEW rpt_summary_payment_vendor_booked AS "+
            " select 1,cp.sponsor_id,cp.vendor_id,v.vendor_name,s.sys_desc,l.lookup_desc, "+
                     " sum(cp.amt_rec) amt,max(cp.db_timestamp) db_timestamp "+
                     " from vendor v, "+
                     " customer_payment_booked cp "+
                     "  left outer join lookup_sys s on s.sys_id = cp.payment_type "+
                     "  left outer join lookup l on l.lookup_id = cp.lookup_id "+
                     "  left outer join customer c on c.cust_id = cp.cust_id "+
               " where v.vendor_id = cp.vendor_id "+
                 "  group by cp.sponsor_id,cp.vendor_id,v.vendor_name,s.sys_desc,l.lookup_desc",


         
         
        view98 =" alter VIEW VIEW_summary_payment_cust_booked AS "+
        " select cp.sponsor_id as sponsor_id,cp.cust_id as cust_id ,c.last_name+', '+c.first_name as full_name, "+
           " s.sys_desc as sys_desc,l.lookup_desc as lookup_desc, "+
              " sum(cp.amt_rec) as amt, max(cp.db_timestamp) as db_timestamp "+
              " from "+
           " customer c, "+
              " customer_payment_booked cp "+
               " left outer join lookup_sys s on s.sys_id = cp.payment_type "+
              "  left outer join lookup l on l.lookup_id = cp.lookup_id "+
           " where c.cust_id = cp.cust_id "+
           " group by cp.sponsor_id,cp.cust_id,c.last_name,c.first_name,s.sys_desc,l.lookup_desc "
        ,
        view99 ="     alter VIEW rpt_summary_room AS "+

                " select 1,c.cust_id as cust_id,cr.vendor_id as vendor_id,cr.sponsor_id as sponsor_id,  "+
           " c.last_name+', '+c.first_name as full_name, v.vendor_name as vendor_name,ls.sys_desc as rm_type,rt.sys_desc as booking,  "+
          "   sum(cr.rm_cost) as rm_cost,sum(cr.total_cost) as total_cost,sum(cr.num_days) as num_days,sum(cr.cust_room_cnt) as rm_cnt,  "+
          "   max(cr.db_timestamp) as db_timestamp,l.lookup_desc as lookup_desc, l.LOOKUP_ROLLUP_ID as LOOKUP_ROLLUP_ID,cr.cust_room_type "+
          "   from customer c ,  customer_room cr ,    vendor v,lookup_sys ls,lookup l,lookup_sys rt  "+
          "   where rt.sys_id = cr.cust_room_type and c.cust_id = cr.cust_id and cr.vendor_id = v.vendor_id and v.vendor_type = ls.sys_id  "+
		" and l.lookup_id = cr.lookup_id and cr.cust_room_type in (-5,-6)"+
        "     group by cr.sponsor_id,c.cust_id,cr.vendor_id,c.last_name,c.first_name, v.vendor_name, "+
		" ls.sys_desc,rt.sys_desc,l.lookup_desc,l.LOOKUP_ROLLUP_ID,cr.cust_room_type "

        // " select 1,c.cust_id as cust_id,cr.vendor_id as vendor_id,cr.sponsor_id as sponsor_id, "+
        //   " c.last_name+', '+c.first_name as full_name, v.vendor_name as vendor_name,ls.sys_desc as rm_type,rt.sys_desc as booking, "+
        //   " sum(cr.rm_cost) as rm_cost,sum(cr.total_cost) as total_cost,sum(cr.num_days) as num_days,sum(cr.cust_room_cnt) as rm_cnt, "+
        //   " max(cr.db_timestamp) as db_timestamp,l.lookup_desc as lookup_desc, l.LOOKUP_ROLLUP_ID as LOOKUP_ROLLUP_ID "+
        //   " from "+
        //   " customer c , "+
        //   " customer_room cr "+
        //   "   left outer join lookup_sys rt on rt.sys_id =masterkey cr.cust_room_type, "+
        //   " vendor v,lookup_sys ls,lookup l "+
        //   " where c.cust_id = cr.cust_id and cr.vendor_id = v.vendor_id and v.vendor_type = ls.sys_id and l.lookup_id = cr.lookup_id "+
        //   " group by cr.sponsor_id,c.cust_id,cr.vendor_id,c.last_name,c.first_name, v.vendor_name,ls.sys_desc,rt.sys_desc,l.lookup_desc,l.LOOKUP_ROLLUP_ID"
           ;

}
