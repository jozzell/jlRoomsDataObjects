/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jlRoomsCommon.login;

import jlRoomsCommon.objMgr;

/**
 *
 * @author lmeans
 */
public class loginSql  extends objMgr{
     
     private  String
             sql = "Select user_id,last_name,first_name,pass_word,email, "+
                " access,flag_ind, LOGIN_DATE from user_acct ";
     
     private  final String sql_insert =
            " insert into user_acct (last_name,first_name,email,access,pass_word) values (?,?,?,?,?)";
     
     public  String sql_insert(String web){
         if (web == null){
             return sql_insert;
         } else {
             return " insert into user_acct (last_name,first_name,email,access,pass_word,email_key) values (?,?,?,?,?,"+ this.getEMailKeyStr(web)+ ")";
         }
     }
     
     //public  String
     
            public  String  sql_login_user(String web){
                return "update user_acct set HOST_IP = ?, HOST_NAME = ?, login_date = ?, login_time = ? where user_id = ?"+
                        (web == null ? "": this.getEMailKeyEquals(web));
            }
            
            public  String  sql_check_pw(String web){
                return "select user_id from user_acct where user_id = ? and pass_word = ?"+
                        (web == null ? "": this.getEMailKeyEquals(web));
            }
            
           
            
            
            public  String  sql_update(String web){
                return "update user_acct set last_name = ?, first_name = ?,email = ? , access = ? where user_id = ?"+
                        (web == null ? "": this.getEMailKeyEquals(web));
            }
             
            public  String  sql_update_pw(String web){
                return"update user_acct set pass_word = ? where user_id = ?"+
                        (web == null ? "": this.getEMailKeyEquals(web));
            }
            
            
            
            public  String  sql_get_user(String web){
                return sql+" where user_id = ?"+
                        (web == null ? "": this.getEMailKeyEquals(web));
            } 
            
            
              
            public  String  sql_all_user(String web){
                return sql+" where access < 100 " +
                        (web == null ? "": this.getEMailKeyEquals(web))+
                        " order by "+
                " last_name,first_name";
            }


}
