/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsV3.rpt.roommate;

import java.io.Serializable;
import jlRoomsCommon.objMgr;

/**
 *
 * @author Lloyd
 */
public class roommateV2_Sql extends objMgr implements Serializable {
    public String get01CustomerRoomate(String web) {
        return get01CustomerRoomate(web,
                " and rm.client_rm_id in (select client_rm_id from client_rmmate  where cust_id = ? "
                        +getEMailKeyEquals(web)+
                        ") and cr.sponsor_id = ?");
    }
    public String get01CustomerRoomateAll(String web) {
        return get01CustomerRoomate(web," and cr.sponsor_id = ?");
    }
    public String get01CustomerRoomateVendor(String web) {
        return get01CustomerRoomate(web," and v.vendor_id = ? and cr.sponsor_id = ?");
    }
    private String get01CustomerRoomate(String web, String where) {
        return (new StringBuilder())
                .append(" SELECT cr.client_rm_id ,cr.client_rm_desc,v.vendor_name,sys.sys_desc,s.sponsor_desc, ")
                .append(" CONCAT(c.last_name, ', ', c.first_name), ")
                .append(" CONCAT(c.addr1, ' ', c.addr2,' ',c.city,' ',c.state,' ',c.zip) ,")
                .append(" c.email,c.hm_phone,CONCAT(c.wk_phone,' ',c.wk_ext)")
                .append(" FROM client_rm cr, vendor v,lookup_sys sys,client_rmmate rm,sponsor s,customer c ")
                .append(" where  ")
                .append(" v.vendor_id = cr.vendor_id and sys.sys_id = cr.lookup_id and ")
                .append(" rm.client_rm_id = cr.client_rm_id and cr.sponsor_id = s.sponsor_id and ")
                .append(" rm.cust_id = c.cust_id ")
                .append(getEMailKeyEquals(web, "cr."))
                .append(getEMailKeyEquals(web, "v."))
                .append(getEMailKeyEquals(web, "rm."))
                .append(getEMailKeyEquals(web, "s."))
                .append(getEMailKeyEquals(web, "c."))
                .append(where == null ? "" : " " + where)
                .append("  order by 3,1,2,6 ").toString();
    }
}
