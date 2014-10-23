package jlRoomsCommon.vendor.db;

import jlRoomsCommon.objMgr;

public class vendorSql extends objMgr {
    public  final String
            sqlGetDefaultID = "Select max(vendor_id) from vendor where vendor_type = ? and email_key = ? and vendor_ind = ?";
    private  final String 
            test_001 = "",
            sql =
            " select v.vendor_id,v.vendor_name,v.vendor_code,v.vendor_ind,v.vendor_type, "
            + " v.flag_ind,v.addr1,v.addr2,v.city,v.state,v.zip,v.zip_plus,v.phone,v.fax,email,v.vendor_rollup_id, "
            + " v.vendor_first,v.region_id,v.parking_fee,v.ext,v.contact_person,s.rpt_desc "
            + " from vendor v,lookup_sys s where s.sys_id = v.vendor_type ";
    private  final String 
            sqlGetId = "Select max(vendor_id) from vendor where vendor_name = ?",
            
            sqlUpdate = "update vendor set vendor_name = ?,vendor_code = ?,vendor_ind = ?,vendor_type = ?,"
                + "flag_ind = ?,addr1 = ?,addr2 = ?,city = ?,state = ?,zip = ?,zip_plus = ?,phone = ?, "
                + " fax = ?,email = ?,ext = ? where vendor_id = ?",
            sqlInsert = "insert into vendor "
                + " (vendor_name,vendor_code,vendor_ind,vendor_type,flag_ind,addr1,addr2,city,state,"
                + " zip,zip_plus,phone,fax,email,ext) values "
                + " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    public  String sqlGetId(String web){
        if (web == null){
            return sqlGetId;
        } else {
            return sqlGetId +this.getEMailKeyEquals(web);
        }
    }
    public  String sqlUpdate(String web){
        if (web == null){
            return sqlUpdate;
        } else {
            return  "update vendor set vendor_name = ?,vendor_code = ?,vendor_ind = ?,vendor_type = ?,"
                + "flag_ind = ?,addr1 = ?,addr2 = ?,city = ?,state = ?,zip = ?,zip_plus = ?,phone = ?, "
                + " fax = ?,email = ?,ext = ?  where vendor_id = ?"+this.getEMailKeyEquals(web);
        }
    }
    public  String sqlInsert(String web){
        if (web == null){
            return sqlInsert;
            
        } else {
           return  "insert into vendor "
                + " (vendor_name,vendor_code,vendor_ind,vendor_type,flag_ind,addr1,addr2,city,state,"
                + " zip,zip_plus,phone,fax,email,ext,email_key) values "
                + " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"+ this.getEMailKeyStr(web)+")";
        }
    }
    private  final String 
            sqlSeekAll = sql + " and upper(vendor_name) like upper(?) order by upper(vendor_name)",
            //sqlSeek = sql + " where upper(vendor_name) like upper(?) and vendor_type = ? order by upper(vendor_name)",
            sqlGetVendor = sql + " and vendor_id = ?",
            sqlGetWakingVendor = sql + " and vendor_type = ?";

    public  String sqlSeekAll(String web) {
        if (web == null) {
            return sqlSeekAll;
        } else {
            return sql + " and upper(vendor_name) like upper(?) " + this.getEMailKeyEquals(web) + " order by upper(vendor_name)";
        }
    }

    public  String sqlGetVendor(String web) {
        if (web == null) {
            return sqlGetVendor;
        } else {
            return sqlGetVendor + this.getEMailKeyEquals(web);
        }
    }

    public  String sqlGetWakingVendor(String web) {
        if (web == null) {
            return sqlGetWakingVendor;
        } else {
            return sqlGetWakingVendor + this.getEMailKeyEquals(web);
        }

    }

    public  String sqlSeek(String web) {
        if (web == null) {
            return sql + " and upper(vendor_name) like upper(?) and vendor_type = ? order by upper(vendor_name)";
        } else {
            return sql + " and upper(vendor_name) like upper(?) and vendor_type = ? " + this.getEMailKeyEquals(web) + "order by upper(vendor_name)";
        }
    }

    
   

    public  String XXXXsqlGetWakingVendor(boolean mysql) {
        if (mysql) {
            return sql + " and vendor_type = ? and email_key = ?";
        } else {
            return sql + " and vendor_type = ?";
        }
    }
}
