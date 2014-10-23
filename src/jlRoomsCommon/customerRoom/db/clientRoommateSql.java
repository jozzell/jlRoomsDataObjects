/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsCommon.customerRoom.db;

import jlRoomsCommon.objMgr;

/**
 * clientRmID
 *
 * @author lmeans
 */
public class clientRoommateSql  extends objMgr {

    public  final String 
            sqlUpdateClientRm = "update client_rm set client_rm_desc = ? where client_rm_id = ? and  email_key = ?",
            sqlDeleteRoommdate = 
            "delete from client_rmmate where client_rm_mate_id = ? and email_key = ?",
            sqlInsertClientRm =
            " insert into client_rm "
            + " (client_rm_desc ,email_key,sponsor_ID,lookup_rollup_id,lookup_id,Vendor_id ,DB_TIMESTAMP)"
            + " values (?,?,?,?,?,?,?)",
            sqlInsertClientRmMax =
            " select max(client_rm_id) from client_rm  where email_key = ? and sponsor_ID = ? and DB_TIMESTAMP = ?",
            sqlInsertClientCustomer =
            "insert into client_rmmate (client_rm_id ,email_key,sponsor_ID ,cust_id) values (?,?,?,?)",
      sqlSelectRmMateList = 
            " SELECT rm.client_rm_mate_id,rm.client_rm_id,rm.sponsor_ID,rm.cust_id,c.last_name,c.first_name, "+
            " c.email , c.addr1,  c.addr2,  c.city,  c.state,  c.zip, c.hm_phone,  c.wk_phone,  c.WK_EXT"+
            " FROM client_rmmate rm,customer c "+
            " where c.cust_id = rm.cust_id and rm.client_rm_id = ? and rm.email_key = ?",
            getSqlSelectRmMateListClient = 
            "";
    // ======================================================================
    private   String getSqlSelectRmMateListClient (String web){
        return 
            " select c.cust_id, c.first_name,c.last_name,c.addr1,c.addr2,c.city,c.state,c.zip,c.hm_phone,   "+
            " c.wk_phone,c.wk_ext,c.email,   "+
            " rm.client_rm_desc,v.vendor_name,lv.sys_desc,r.client_rm_mate_id,rm.client_rm_id   "+
            " from client_rm rm, vendor v,lookup_sys lv, client_rmmate r,customer c  "+
            " where   c.cust_id = r.cust_id and "+
            " 	v.vendor_id = rm.vendor_id and   "+ 
            " 	lv.sys_id = v.vendor_type and    "+
            " 	r.client_rm_id = rm.client_rm_id and    "+
            " c.email_key = " + getEMailKeyStr(web) + " and "+
            " rm.email_key =  " + getEMailKeyStr(web) + " and "+
            " v.email_key =  " + getEMailKeyStr(web) + " and "+
            " r.email_key =  " + getEMailKeyStr(web) + "and ";
    }
    public   String getSqlSelectRmMateListClient (String web,boolean cust){
         return   getSqlSelectRmMateListClient(web)+
            " rm.client_rm_id in ( "+
	            " select client_rm_id from client_rmmate  where "+
	           (cust ?  " cust_id = ? and ": "" ) +
                    " sponsor_id = ? and email_key = " + getEMailKeyStr(web) + ")  "+
            " order by upper(rm.client_rm_desc),lv.sys_desc,rm.client_rm_id,v.vendor_name,c.last_name,c.first_name ";
     } 
    public   String getSqlSelectRmMateList_VendorTypeCustSponsor (String web){
         return   getSqlSelectRmMateListClient(web)+
            " v.vendor_type = ? and "+
            " rm.client_rm_id in ( "+
	            " select client_rm_id from client_rmmate  where "+
	           " cust_id = ? and " +
                    " sponsor_id = ? and email_key = " + getEMailKeyStr(web) + ")  "+
            " order by lv.sys_desc,v.vendor_name,rm.client_rm_id,c.last_name,c.first_name ";
     }
    public   String getSqlSelectRmMateList_Sponsor (String web){
         return   getSqlSelectRmMateListClient(web)+
            
            " rm.sponsor_id = ? order by rm.client_rm_id,lv.sys_desc,v.vendor_name,c.last_name,c.first_name ";
     }
    public   String getSqlSelectRmMateList_CustSponsor (String web){
         return   getSqlSelectRmMateListClient(web)+
            " rm.client_rm_id in ( "+
	            " select client_rm_id from client_rmmate  where "+
	           " cust_id = ? and " +
                    " sponsor_id = ? and email_key = " + getEMailKeyStr(web) + ")  "+
            " order by lv.sys_desc,v.vendor_name,rm.client_rm_id,c.last_name,c.first_name ";
     }
    // ======================================================================
    private  final String 
       sqlSelectClientGroup_pre =
            " select  rm.client_rm_id, rm.client_rm_desc, rm.Vendor_id,rm.lookup_rollup_Id,v.vendor_name,lv.sys_desc, count(*) "
            + " from client_rm rm, vendor v,lookup_sys lv, client_rmmate r "
            + " where  "
            + " v.vendor_id = rm.vendor_id and "
            + " lv.sys_id = v.vendor_type and  "
            + " r.client_rm_id = rm.client_rm_id" ,
      sqlSelectClientGroup_post = 
            " group by rm.client_rm_id, rm.client_rm_desc, "
            + " rm.Vendor_id,rm.lookup_rollup_Id,v.vendor_name,lv.sys_desc "
;

    
    public  String getCustomerRoommateGroupSql(String email){
        return sqlSelectClientGroup(email,
                 " and rm.client_rm_id in (select client_rm_id from client_rmmate where cust_id = ? and sponsor_id = ? and email_key = ?)"
                );
    }
    private  String sqlSelectClientGroup(String email,String sql) {
        return sqlSelectClientGroup_pre+sqlGetRmListRoommate_V2(email)+sql+
                sqlSelectClientGroup_post;
    }

    private  final String sqlGetRmListRoommate_V2(String web) {
        if (web == null) {
            return null;
        }
        return " and "
                + "  rm.email_key = " + getEMailKeyStr(web) + " and "
                + "  v.email_key = " + getEMailKeyStr(web) + " and "
                + "  r.email_key = " + getEMailKeyStr(web);
    }
    /*
     sqlInsertClientRm = 
     "inset into client_rm "+
            
     "( clientRmID) "+
     " values (?,?,?,?,?,?)",
     sqlUpdateClientRm = 
     " update client_rm  set lookup_id = ? where client_rm_id = ? and email_key = ?",
     sqlMaxClientRmID =
     "select max(client_rm_id) from client_rm  "+
     " where email_key = ? and sponsor_ID = ? and cust_id = ? and DB_TIMESTAMP = ? ",
     sqlInsertClientRmMate = 
     " insert into client_rm_mate (client_rm_id,email_key,sponsor_ID , cust_id) "+
     " values (?,?,?,?) ",
     sqlDeleteClientRmMate = 
     " delete from client_rm_mate where "+
     " email_key = ? and sponsor_ID = ? and client_rm_id = ? and cust_id = ? ",
     sqlSelectClientRmMage = 
     " select cr.client_rm_id,cr.client_rm_desc ,c.last_name,c.first_name,c.hm_phone,c.EMAIL,c.cust_id "+
     " from client_rm cr,client_rm_mate crm, CUSTOMER c "+
     " where c.cust_id = crm.cust_id and "+
     " cr.client_rm_id = crm.client_rm_id and "+
     " cr.client_rm_id in (select client_rm_id from  "+
     " client_rm_mate where email_key = ? and sponsor_ID = ? and cust_id = ? ) and "+
     " c.email_key = ? and cr.email_key = ? and crm.email_key = ?";
     * */
}
