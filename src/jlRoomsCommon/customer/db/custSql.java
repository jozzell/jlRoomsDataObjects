package jlRoomsCommon.customer.db;

import jlRoomsCommon.objMgr;

public class custSql  extends objMgr {

    private  final String 
            
            sql =
            " select  cust_id,  first_name,  last_name,  addr1,  addr2,  city,  state,  zip, "
            + " hm_phone,  wk_phone,  WK_EXT ,email,keyStr  from customer";
    private  final String 
            
            sqlCustSearch = sql + " where upper(last_name) like upper(?) order by upper(last_name)",
            sqlCust = sql + " where cust_id = ? ",
            sqlLast = " select max(cust_id) from customer where first_name = ? and last_name = ?",
            sqlUpdateCustomer = " update customer set "
            + " first_name=?,  last_name=?,  addr1=?,  addr2=?,  city=?,  state=?, zip=?,"
            + " hm_phone=?,  wk_phone=?,WK_EXT =?, email=? where cust_id = ?",
            sqlInsertCustomer = " insert into customer "
            + " (first_name,  last_name,  addr1,  addr2,  city,  state, zip, hm_phone ,  wk_phone, WK_EXT, email)"
            + " values (?,?,?,?,?,?,?,?,?,?,?)",
            sqlUpdateEMail = "update customer set email=? where cust_id = ?";
    
    public  String sqlCustSearch(String web){
        if (web == null) {
            return sqlCustSearch;
        } else {
            return sql + " where upper(last_name) like upper(?) "+
                    getEMailKeyEquals(web)+
                    " order by upper(last_name)";
        }
    }
    
    
    public  String sqlCust(String web){
        if (web == null){
            return sqlCust;
            
        } else {
            return sqlCust+getEMailKeyEquals(web);
        }
    }
    public  String sqlLast(String web){
        if (web == null){
            return sqlLast;
        } else {
            return sqlLast+getEMailKeyEquals(web);
        }
    }
    public  String sqlUpdateCustomer(String web){
        if (web == null){
            return sqlUpdateCustomer;
        } else {
            return sqlUpdateCustomer+getEMailKeyEquals(web);
        }
    }
    public  String sqlInsertCustomer(String web) {
        if (web == null) {
            return sqlInsertCustomer;

        } else {
            return " insert into customer "
                    + " (first_name,  last_name,  addr1,  addr2,  city,  state, zip, hm_phone ,  wk_phone, WK_EXT, email,email_key)"
                    + " values (?,?,?,?,?,?,?,?,?,?,?,"
                    + getEMailKeyStr(web) + ")";
        }
    }

    public  String sqlUpdateEMail(String web) {
        if (web == null) {
            return sqlUpdateEMail;
        } else {
            return sqlUpdateEMail + getEMailKeyEquals(web);
        }
    }
}
