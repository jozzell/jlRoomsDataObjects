/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsV3.rpt.block;

import java.io.Serializable;
import jlRoomsCommon.objMgr;

/**
 *
 * @author lmeans
 */
public class blockRptV3_Sql  extends objMgr implements Serializable {
    public String getBlockRpt01(String web) {
        return getBlockRpt01(web," and b.sponsor_id = ?");
    }
    private String getBlockRpt01(String web, String where) {
        return (new StringBuilder())
                .append(" SELECT ")
               .append(this.getCase(" cr.cust_room_type "))
                .append(" ,b.block_id, ")
                .append(" max(b.rm_cnt),max(b.rm_cost), ")
                .append(" l.lookup_desc, ")
                .append(" s.sponsor_desc,   v.vendor_name, ")
                .append(" (select sys_desc from lookup_sys sys where sys.sys_id = cr. cust_room_type) stat, ")
                .append(" cr.cust_room_type, ")
                .append(" v.vendor_id,l.lookup_id, ")
                .append(" ROUND(sum(cr.cust_room_cnt * (1/cr.roommate_split)),2)	 booked , ")
                .append(" round(sum(cr.rm_cost),2),round(sum(cr.block_cost_cnt),2),round(sum(cr.total_cost),2), ")
                .append(" min(cr.eff_date),max(cr.end_date) ")
                .append(" FROM ")
                .append(" sponsor_hotel sh, ")
                .append(" vendor v, ")
                .append(" sponsor s, ")
                .append(" lookup l, ")
                .append(" block b ")
                .append(" left outer join customer_room cr ON cr.block_id = b.block_id ")
                .append(getEMailKeyEquals(web, "cr."))
                .append(" where l.lookup_id = b.lookup_id and b.sponsor_id = s.sponsor_id and sh.sponsor_hotel_id = b.sponsor_hotel_id ")
                .append(" and v.vendor_id = sh.vendor_id  ")
                .append(getEMailKeyEquals(web, "sh."))
                .append(getEMailKeyEquals(web, "v."))
                .append(getEMailKeyEquals(web, "s."))
                .append(getEMailKeyEquals(web, "l."))
                .append(getEMailKeyEquals(web, "b."))
                .append(where == null ? "" : " " + where)
                .append(" group by cr.cust_room_type,b.block_id,v.vendor_type,v.vendor_id,l.lookup_desc,l.lookup_id,s.sponsor_desc,   v.vendor_name ")
                .append(" order by  7,2,8 ").toString();
    }
    
    

    public String getCase(String cse) {
        return (new StringBuilder())
                .append(" case ").append(cse)
                .append(" when  -5 then 1 ")
                .append(" when  -6 then 1 ")
                .append(" when  -30 then 9 ")
                .append(" when  -23 then 9 ")
                .append(" when  -8 then 2 ")
                .append(" when  -7 then 2 ")
                .append(" else 1 ")
                .append(" end ").toString();

    }
    
}
