package jlRoomsCommon.lookup_sys.db;

public class lookupSysSql {
  private  String sql = "select s.sys_id,s.sys_desc,s.sys_type,s.sys_rollup_id,"+
      "s.amt,s.credit,s.RPT_DESC from lookup_sys s";
  public  String sqlSelectType = sql+" where s.sys_type = ? order by s.sys_desc";
}
