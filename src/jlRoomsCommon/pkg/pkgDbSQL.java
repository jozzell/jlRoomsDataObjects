/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jlRoomsCommon.pkg;

import jlRoomsCommon.objMgr;

/**
 *
 * @author lmeans
 */
public class pkgDbSQL  extends objMgr{
   private  String
           
           sql_detail = " select d.pkg_detail_id,d.pkg_id,d.pkg_type,d.pkg_cat,d.pkg_night,d.pkg_comment ,  "+
                " d.pkg_parm1 ,d.pkg_parm2 ,d.pkg_parm3 ,d.SPONSOR_ID,s.sys_desc,l.lookup_desc ,room_split"+
                " from pkg_detail d,lookup_sys s,lookup l "+
                " where s.sys_id = d.pkg_type and l.lookup_id = d.pkg_cat ",
           sql =  " select pkg_id,pkg_desc,pkg_amt,pkg_fee,sponsor_id,cnt,cnt_kids,cnt_sr from pkg where ";

    private  final String
            
            sql_insert_pkg_detail = "insert into pkg_detail (pkg_id,	pkg_type,pkg_cat,pkg_night,pkg_comment , "+
                " pkg_parm1 ,pkg_parm2 ,pkg_parm3 ,SPONSOR_ID,room_split ) "+
                " values (?,?,?,?,?,?,?,?,?,?)",            
            sql_insert_pkg = "insert into pkg (pkg_desc,pkg_amt,pkg_fee,pkg_date,pkg_time,flag_ind,sponsor_id,cnt,cnt_kids,cnt_sr)"+
                "  values (?,?,?,?,?,?,?,?,?,?)",
            sql_pkgX = "select pkg_desc,pkg_id,pkg_amt from cust_pkg";
    
    public  String sql_insert_pkg_detail(String web){
        if(web == null){
            return sql_insert_pkg_detail;
        } else {
            return"insert into pkg_detail (pkg_id,	pkg_type,pkg_cat,pkg_night,pkg_comment , "+
                " pkg_parm1 ,pkg_parm2 ,pkg_parm3 ,SPONSOR_ID,room_split,email_key ) "+
                " values (?,?,?,?,?,?,?,?,?,?,"+this.getEMailKeyStr(web)  +" )";
                    
        }
    }
    public  String sql_insert_pkg(String web){
        if (web == null){
            return sql_insert_pkg;
        } else {
            return "insert into pkg (pkg_desc,pkg_amt,pkg_fee,pkg_date,pkg_time,flag_ind,sponsor_id,cnt,cnt_kids,cnt_sr,email_key)"+
                "  values (?,?,?,?,?,?,?,?,?,?,"+this.getEMailKeyStr(web)+ ")";
        }
    }
    public  String  sql_insert_select(String web){
        return   "select max(pkg_id) from pkg where pkg_desc = ? and pkg_date = ? and pkg_time = ?"+(web==null? "":this.getEMailKeyEquals(web));
    }
    public  String  sql_update_pkg(String web){
        return  "update pkg set pkg_desc=?,pkg_amt=?,pkg_fee=?,flag_ind=?,sponsor_id=?, cnt=?,cnt_kids=?,cnt_sr=? where pkg_id = ?"+(web==null? "":this.getEMailKeyEquals(web));
    }
    public  String  sql_select_search (String web){
        return  sql + " lower(pkg_desc) like ?"+(web==null? "":this.getEMailKeyEquals(web));
    }
    public  String  sql_select_default(String web){
        return   sql+ "  sponsor_id in (0,?) and flag_ind = 0"+(web==null? "":this.getEMailKeyEquals(web));
    }
    public  String  sql_update_pkg_detail (String web){
        return  "update pkg_detail set pkg_cat=?,pkg_night=?,pkg_comment=? , "+
                " pkg_parm1=? ,pkg_parm2=? ,pkg_parm3=? ,SPONSOR_ID=?,room_split=? where pkg_detail_id = ?"+(web==null? "":this.getEMailKeyEquals(web));
    }
    public  String  sql_select_detail (String web){
        return sql_detail + " and d.pkg_id = ?"+sql_select_detail_sub(web);
    }
     private  String sql_select_detail_sub(String web){
        if(web == null) return "";
        return " and "+
             " d.email_key = "+this.getEMailKeyStr(web)+" and "+
             " s.email_key = "+this.getEMailKeyStr(web)+" and "+
             " l.email_key = "+this.getEMailKeyStr(web)+" ";
    }
    
}
