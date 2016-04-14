package jlRoomsCommon.lookup.db;

import jlRoomsCommon.objMgr;

public class lookupSql  extends objMgr{
  private  final String
      sqlBlock =
          "  select l.lookup_id * -1,l.lookup_type,l.lookup_rollup_id,l.lookup_Desc,  "+
       "  l.flag_ind,l.credit,l.sponsor_link_id ,b.block_id,b.rm_cnt, "+
       "  (select count(*) from customer_room c where c.block_id = b.block_id and c.cust_room_type in (-7,-6,-5)) "+
          ",b.rm_cost "+
       "  from lookup l,block b , sponsor_hotel h"+
      "   where l.lookup_id = b.lookup_id  and h.sponsor_hotel_id = b.sponsor_hotel_id",
      sql =
      " select lookup_id,lookup_type,lookup_rollup_id,lookup_Desc, "+
      " flag_ind,credit,sponsor_link_id,0,0,0,0  from lookup";
  private  final String sqlInsert = "insert into lookup (lookup_type,lookup_rollup_id,lookup_Desc,flag_ind) values (?,?,?,?)"; //flag_ind,
  public  String sqlInsert(String web){
      if (web == null){
          return sqlInsert;
      } else {
          return "insert into lookup (lookup_type,lookup_rollup_id,lookup_Desc,flag_ind,vendor_id,email_key) values (?,?,?,?,?,"+ this.getEMailKeyStr(web)+ ")";
      }
  }
  //=============================================================
  public  String sqlLookupRollup(String web){
      return  sql+" where lookup_rollup_id = ? "+(web == null ? "": this.getEMailKeyEquals(web))+
              "order by upper(lookup_Desc)";
  }  
  public  String sqlLookupRollupBlock(String web){
      return  sql+" where lookup_rollup_id = ? "+(web == null ? "": this.getEMailKeyEquals(web))+
               " union "+sqlBlock+
              " and l.lookup_rollup_id = ? and h.vendor_id = ? and b.flt_id =? "
                +(web == null ? "": this.getEMailKeyEquals(web,"l."))
                +(web == null ? "": this.getEMailKeyEquals(web,"h."))
                +(web == null ? "": this.getEMailKeyEquals(web,"b."))
              +"order by 4";
  }  
  public  String sqlLookupRollupVendor(String web){
      return  sql+" where lookup_rollup_id = ? and vendor_id = ? "+(web == null ? "": this.getEMailKeyEquals(web))+
              "order by upper(lookup_Desc)";
  } 
   public  String sqlLookupRollupVendorBlock(String web){
      return  sql+" where lookup_rollup_id = ? and vendor_id = ? "+(web == null ? "": this.getEMailKeyEquals(web))+
              " union "+sqlBlock+
              " and l.lookup_rollup_id = ? and h.vendor_id = ? and b.flt_id =? "
                +(web == null ? "": this.getEMailKeyEquals(web,"l."))
                +(web == null ? "": this.getEMailKeyEquals(web,"h."))
                +(web == null ? "": this.getEMailKeyEquals(web,"b."))
              +"order by 4"
              ;
  } 
  //=============================================================
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
  public  String sqlLookupTypeRollupVendor(String web){
       return sql+" where lookup_type = ? and lookup_rollup_id = ? and vendor_id = ? "+(web == null ? "": this.getEMailKeyEquals(web))+
               "order by upper(lookup_Desc)";
  }  
  public  String sqlUpdate(String web){
       return "update lookup set lookup_Desc = ?, flag_ind = ? where lookup_id = ?"+(web == null ? "": this.getEMailKeyEquals(web));
  }
}
