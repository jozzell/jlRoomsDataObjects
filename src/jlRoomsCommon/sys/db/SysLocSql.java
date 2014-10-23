package jlRoomsCommon.sys.db;

public class SysLocSql {
    private  final String
            sqlSys =
       " select sys_id,sys_name,sys_code,sys_ind,sys_type, "+
       " flag_ind,addr1,addr2,city,state,zip,zip_plus,phone,fax,email,sys_rollup_id, "+
       " sys_first,region_id,parking_fee,ext,contact_person "+
       " from sys_loc";
    
  public final String
   sqlSysGetId = "Select max(sys_id) from sys_loc where sys_name = ?",
   sqlSysUpdate = "update sys_loc set sys_name = ?,sys_code = ?,sys_ind = ?,sys_type = ?,"+
               "flag_ind = ?,addr1 = ?,addr2 = ?,city = ?,state = ?,zip = ?,zip_plus = ?,phone = ?, "+
               " fax = ?,email = ?,ext = ? where sys_id = ?",
   sqlSysInsert = "insert into sys_loc "+
   " (sys_name,sys_code,sys_ind,sys_type,flag_ind,addr1,addr2,city,state,"+
   " zip,zip_plus,phone,fax,email,ext) values "+
   " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",

    
     sqlSysSeek = sqlSys +" where upper(sys_name) like upper(?) and sys_type = ? order by upper(sys_name)",
     sqlSysSeekAll = sqlSys +" where upper(sys_name) like upper(?) order by upper(sys_name)",
     sqlGetSysType = sqlSys + " where sys_type = ?",
     sqlGetSys = sqlSys + " where sys_id = ?";

}
