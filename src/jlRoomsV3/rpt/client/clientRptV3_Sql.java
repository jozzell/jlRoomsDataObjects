/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsV3.rpt.client;

import java.io.Serializable;
import jlRoomsCommon.objMgr;

/**
 *
 * @author lmeans
 */
public class clientRptV3_Sql  extends objMgr implements Serializable {
    public String getClentRpt01Sponsor(String web) {
        return getClentRpt01(web," and cr.sponsor_id = ?");
    }
    public String getClentRpt01Vendor(String web) {
        return getClentRpt01(web," and v.vendor_id = ? and cr.sponsor_id = ?");
    }
    private String getClentRpt01(String web, String where) {
        return (new StringBuilder())
                .append(" SELECT ")
                .append(" 1/cr.roommate_split split, ")
                .append(" CONCAT(c.last_name, ', ', c.first_name),   l.lookup_desc,  rt.sys_desc,   ")
                .append(" v.vendor_name,s.sponsor_desc, ")
                .append(" c.cust_id, cr.block_id, cr.created,v.vendor_type, ")
                .append(" cr.sponsor_id, ")
                .append(" cr.eff_date, ")
                .append(" cr.end_date, ")
                .append(" cr.rm_cost, cr.total_cost, cr.tax, cr.block_cost_cnt,  cr.num_days,    cr.cust_room_cnt,    cr.ticket_info,    cr.room_comment,    ")
                .append(" cr.balance_due_date, ")
                .append(" case cr.roommate_split")
                 .append(" when 1 then '' ")
                 .append(" else CONCAT('1/',cr.roommate_split)")
                .append("	end, ")
                .append(" CONCAT(c.addr1, ' ', c.addr2,' ',c.city,' ',c.state,' ',c.zip) ,c.hm_phone,c.email,CONCAT(c.wk_phone,' ',c.wk_ext), ")
                .append(" f.flt_number,    ")
                .append(" f.flt_departure_date, ")
                .append(" f.flt_arrival_date, ")
                .append(" CONCAT(f.flt_departure_place,            '-',    f.flt_arrival_place) ")
                .append(",rt.sys_id")
                .append(" FROM ")
                .append(" customer_room cr  left outer join   flight_information f ON cr.flt_id = f.flt_id ")
                 .append(getEMailKeyEquals(web, "f.")).append(",")
                .append(" customer c, ")
                .append(" lookup l, ")
                .append(" vendor v, ")
                .append(" lookup_sys rt, ")
                .append(" sponsor s ")
                .append(" where ")
                .append(" cr.cust_id = c.cust_id ")
                .append(" and s.sponsor_id = cr.sponsor_id ")
                .append(" and l.lookup_id = cr.lookup_id ")
                .append(" and v.vendor_id = cr.vendor_id ")
                .append(" and rt.sys_id = cr.cust_room_type ")
                .append(getEMailKeyEquals(web, "cr."))
                .append(getEMailKeyEquals(web, "c."))
                .append(getEMailKeyEquals(web, "l."))
                .append(getEMailKeyEquals(web, "s."))
                .append(where == null ? "" : " " + where)
                .append(" order by 2 ").toString();

    }
    // ================================================================================
    public String getClentRpt_1stNightSponsor(String web) {
        return getClentRpt_1stNight(web," and cr.sponsor_id = ?");
    }
    public String getClentRpt_1stNightVendor(String web) {
        return getClentRpt_1stNight(web," and v.vendor_id = ? and cr.sponsor_id = ?");
    }
    private String getClentRpt_1stNight(String web, String where) {
        return (new StringBuilder())
                .append(" select v.vendor_id,s.sys_desc,v.vendor_name, ")
                .append(" l.lookup_desc, CONCAT(c.last_name, ', ', c.first_name) nm,cr.rm_cost,  ")
                .append(" CONCAT(c.addr1, ' ', c.addr2,' ',c.city,' ',c.state,' ',c.zip) adr, ")
                .append(" ifnull(c.email,ifnull(c.hm_phone,CONCAT(c.wk_phone,' ',c.wk_ext))) contact, ")
                .append(" round(cr.cust_room_cnt * (1/cr.roommate_split),2),1/cr.roommate_split  ")
                .append(" ,sp.sponsor_desc ")
                .append(" ,case cr.roommate_split when 1 then ''	else CONCAT('1/',cr.roommate_split) end splt")
                .append(" from customer c,customer_room cr,block b,lookup l,vendor v,lookup_sys s ,sponsor sp ")
                
                .append(" where cr.cust_id = c.cust_id and b.block_id = cr.block_id and s.sys_id = cr.cust_room_type and  ")
                .append(" l.lookup_id = cr.lookup_id and v.vendor_id = cr.vendor_id  ")
                .append(" and cr.cust_room_type in (-5,-6) and cr.cust_room_type not in (-12) ")
                .append(" and sp.sponsor_id = cr.sponsor_id ")
                .append(getEMailKeyEquals(web, "cr."))
                .append(getEMailKeyEquals(web, "c."))
                .append(getEMailKeyEquals(web, "b."))
                .append(getEMailKeyEquals(web, "l."))
                .append(getEMailKeyEquals(web, "v."))
                .append(where == null ? "" : " " + where)
                .append(" order by 3,1 ").toString();
    }
}
