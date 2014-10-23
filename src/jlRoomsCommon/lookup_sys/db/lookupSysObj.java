package jlRoomsCommon.lookup_sys.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jlRoomsCommon._beans.lookupBean;
import jlRoomsCommon._objects.jlRoomsDbConnIinterface;
import obj.db.v1.dbMgrInterface;
import sun.jdbc.rowset.*;
public class lookupSysObj implements Serializable{
    private jlRoomsDbConnIinterface jlRoomsFactory=null;
    lookupSysSql lookupSysSql;
    public lookupSysObj(){
        lookupSysSql = new lookupSysSql();
    }
    public lookupSysObj(jlRoomsDbConnIinterface x) {
        this();
      this.jlRoomsFactory = x;

    }
    
 // ------------------------------------------------------------------------
  public  List<lookupBean> getLookupBeanVector(int type) {
    return getLookupBeanVector(type,jlRoomsFactory);
  }
  public   List<lookupBean> getLookupBeanVector(int type,dbMgrInterface dbMgr) {
    return getLookupBean(lookupSysSql.sqlSelectType,new Object[]{new Integer(type)},dbMgr);
  }
  // ------------------------------------------------------------------------
  private  List<lookupBean> getLookupBean(String sql,Object[] ary,dbMgrInterface dbMgr) {
    List<lookupBean> v = new ArrayList<lookupBean>();
    CachedRowSet rs = null;
    try {
      rs = dbMgr.getCachedRowSet(sql, ary);
      while (rs.next()) v.add(getLookupBean(rs));
    }catch(Exception e) {
      Logger.getLogger(lookupSysObj.class.getName()).log(Level.SEVERE, null, e);
    } finally {
      dbMgr.closeCachedRowSet(rs);
    }
    return v;
  }
  private  lookupBean getLookupBean(CachedRowSet r) throws Exception {
    lookupBean b = new lookupBean();
    try {
      b.setLookupId(r.getInt(1));
      b.setDesc(r.getString(2));
      b.setLookupType(r.getInt(3));
      b.setLookupRollupId(r.getInt(4));
      b.setRptDesc(r.getString(7));

    } catch(Exception e) {
        Logger.getLogger(lookupSysObj.class.getName()).log(Level.SEVERE, null, e);
      throw e;
    }

    return b;
  }
}
