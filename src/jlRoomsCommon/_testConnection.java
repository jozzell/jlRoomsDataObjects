/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsCommon;

import java.util.logging.Level;
import java.util.logging.Logger;
import obj.db.v1.dbMgrInterface;
import sun.jdbc.rowset.CachedRowSet;

/**
 *
 * @author Lloyd
 */
public class _testConnection {
   public void dbRoom(dbMgrInterface dbMgr){
     String sql=      "SELECT count(*) FROM lookup_sys where sys_type = 2";
     CachedRowSet rs = null;
    try {
      rs = dbMgr.getCachedRowSet(sql, new Object[]{});
      while (rs.next()){
          
      }
    }catch(Exception e) {
        e.printStackTrace();
        Logger.getLogger(_testConnection.class.getName()).log(Level.SEVERE, null, e);
    } finally {
      dbMgr.closeCachedRowSet(rs);
    }
   }
   public void dbRoot(dbMgrInterface dbMgr){
       String sql = "SELECT count(*) FROM serials_vendor where id = -1";
       CachedRowSet rs = null;
    try {
      rs = dbMgr.getCachedRowSet(sql, new Object[]{});
      while (rs.next()){
          
      }
    }catch(Exception e) {
        e.printStackTrace();
        Logger.getLogger(_testConnection.class.getName()).log(Level.SEVERE, null, e);
    } finally {
      dbMgr.closeCachedRowSet(rs);
    }
   }
   
}
