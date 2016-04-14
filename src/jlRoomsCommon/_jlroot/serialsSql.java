/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsCommon._jlroot;

/**
 *
 * @author lmeans
 */
public class serialsSql {
 public  final   String
         sql="demo",
         sqlInsertLogin =
         " insert into jlRooms_CUSTOMER_login (DB_TIMESTAMP,srv_ip,keyStr) values (?,?,?) ",
         sqlSelectDBAccountSql= 
         " select db_ID,jndi,url,jndi_user,jndi_pass,jndi_cnt,file_path "+                 
         " from jlRooms_dbaccount ",
        sqlSelectDBAccoutID =sqlSelectDBAccountSql+"  where db_id  = ?",
        sqlSelectDBAccount =sqlSelectDBAccountSql+
         " where db_id in (select maX(db_ID) FROM jlRooms_dbaccount where jndi_cnt = 1) ",
        sqlUpdateCustomerKey = "update customer set keyStr = ? where cust_id = ?",
         sqlKey = " select x.id,x.serialKey emailKey,y.serialKey siteId "+
            " from serials_vendor x,serials_customer y where x.id = ? and y.id = x.id ",
        sqlKeyCustomerID = "SELECT id,LOWER(serialKey) FROM serials_customer where id = ?",
        sqlKeyVendorID = "SELECT id,LOWER(serialKey) FROM serials_vendor where id = ?",
        sqlKeyCustomerStr = "SELECT id,LOWER(serialKey) FROM serials_customer where serialKey = ?",
        sqlKeyVendorStr = "SELECT id,LOWER(serialKey) FROM serials_vendor where serialKey = ?",
        sqlEMailCompany = "select max(vendor_id) from jlRooms_VENDOR where email = trim(?)",
        sqlEMailCustomer = " SELECT max(cust_id) FROM jlrooms_customer where email = ? ",
        sqlUpdateComapnyKey = "update jlrooms_vendor set keyStr = ?, email_key = ? where VENDOR_ID = ?",
        // ========================================================
        sqlUpdateCustomer =
         " update jlRooms_CUSTOMER set "+
         " FIRST_NAME = ?, LAST_NAME=?, user_pass=?, access_lvl=? where CUST_ID = ? ",
        sqlSelectCustomer =
            " select CUST_ID,LOWER(email_key), LOWER(keyStr),FIRST_NAME, LAST_NAME, vendor_id, user_name, user_pass, access_lvl,EMAIL  "+
            " from jlRooms_CUSTOMER where keyStr = ? and user_name = ? ",
        sqlSelectCustomerEMail =
            " select CUST_ID,LOWER(email_key), LOWER(keyStr),FIRST_NAME, LAST_NAME, vendor_id, user_name, user_pass, access_lvl,EMAIL  "+
            " from jlRooms_CUSTOMER where EMAIL = ? ",
         sqlInsertNewCustomer = 
        " insert into jlRooms_CUSTOMER "+
        " (email_key, keyStr, FIRST_NAME, LAST_NAME, vendor_id, user_name, user_pass, access_lvl,EMAIL) "+
        " values (?,?,?,?,?,?,?,?,?)",
         // ========================================================
         sqlInsertCompany=
            " insert into   jlRooms_VENDOR "+
            " (vendor_name,vendor_code,vendor_ind,vendor_type, "+
            " flag_ind,addr1,addr2,city,state,zip,zip_plus,phone,fax,email, "+
            " ext,vendor_rollup_id,VENDOR_paypal_type,dbId ) "+
            " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ",
         sqlSelectCompany =
           " select vendor_id,vendor_name,vendor_code,vendor_ind,vendor_type, "+
           " flag_ind,addr1,addr2,city,state,zip,zip_plus,phone,fax,email, "+
           " ext,vendor_rollup_id, LOWER(keyStr),LOWER(email_key),VENDOR_paypal_type,dbId "+
           " from jlRooms_VENDOR  where vendor_id = ? ",
         sqlUpdateCompany =
         " update jlRooms_VENDOR set "+
         " vendor_name = ?, addr1 = ?,addr2 = ?,city = ?,state = ?, "+
         " zip = ?,zip_plus = ?,phone = ?,fax = ?,ext=? where vendor_id = ? ",
         // ========================================================
         sqlCustPaymentMethodInsert = 
         " INSERT INTO jlrooms_cust_payment_method (cntr_type,DB_TIMESTAMP,email_key) VALUES (?,?,?)",
         sqlCustPaymentMethodSelect = 
         " select cntr_id,cntr_type ,DATEDIFF(db_timestamp,NOW()),DATE_FORMAT(db_timestamp, '%a %b %d, %Y') "+
         " from jlrooms_cust_payment_method where cntr_id in "+
         " (select max(cntr_id) from jlrooms_cust_payment_method where email_key = ?) "
        
            ;


}
