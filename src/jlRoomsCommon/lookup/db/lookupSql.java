package jlRoomsCommon.lookup.db;

import jlRoomsCommon.objMgr;

public class lookupSql  extends objMgr{
  private  final String
      sql =
      " select lookup_id,lookup_type,lookup_rollup_id,lookup_Desc, "+
      " flag_ind,credit,sponsor_link_id  from lookup";
  private  final String sqlInsert = "insert into lookup (lookup_type,lookup_rollup_id,lookup_Desc,flag_ind) values (?,?,?,?)"; //flag_ind,
  public  String sqlInsert(String web){
      if (web == null){
          return sqlInsert;
      } else {
          return "insert into lookup (lookup_type,lookup_rollup_id,lookup_Desc,flag_ind,email_key) values (?,?,?,?,"+ this.getEMailKeyStr(web)+ ")";
      }
  }
  
  public  String sqlLookupRollup(String web){
      return  sql+" where lookup_rollup_id = ? "+(web == null ? "": this.getEMailKeyEquals(web))+
              "order by upper(lookup_Desc)";
  }  
  public  String sqlLookupId(String web){
       return sql+" where lookup_id = ? "+(web == null ? "": this.getEMailKeyEquals(web))+
               "order by upper(lookup_Desc)";
  }  
  public  String sqlLookupType(String web){
        return sql+" where lookup_type = ? "+(web == null ? "": this.getEMailKeyEquals(web))+
                "order by upper(lookup_Desc)";
  }  
  public  String sqlLookupTypeRollup(String web){
       return sql+" where lookup_type = ? and lookup_rollup_id = ? "+(web == null ? "": this.getEMailKeyEquals(web))+
               "order by upper(lookup_Desc)";
  }  
  public  String sqlUpdate(String web){
       return "update lookup set lookup_Desc = ?, flag_ind = ? where lookup_id = ?"+(web == null ? "": this.getEMailKeyEquals(web));
  }
}
