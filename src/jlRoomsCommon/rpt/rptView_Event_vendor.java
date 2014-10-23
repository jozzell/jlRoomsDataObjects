/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsCommon.rpt;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import jlRoomsCommon.objMgr;
import jlRoomsCommon.rpt.db.rptviewObj;
import jlRoomsCommon.rpt.db.rptviewSql;
import obj.db.v1.dbMgrInterface;
import sun.jdbc.rowset.CachedRowSet;

/**
 *
 * @author lmeans
 */
public class rptView_Event_vendor extends objMgr implements Serializable  {
    public  String rptView_Event_vendor(String web){
     return "select * from rptView_Event "+
            " where vendor_id in (?) "+
               (web == null ? "":this.getEMailKeyEquals(web))+
               "  order by 9 desc ,10,11,2,3,4";
    }
    public  String rptView_Event_sponsor(String web){
     return "select * from rptView_Event "+
            " where sponsor_id in (?) "+
               (web == null ? "":this.getEMailKeyEquals(web))+
               " order by 9 desc ,10,11,2,3,4";
    }
     // ========================================================
    public  CachedRowSet rptView_Event_sponsor(int spon,String web,dbMgrInterface db) {
        CachedRowSet rs = null;
        try {
            rs = db.getCachedRowSet(rptView_Event_sponsor(web), new Object[]{new Integer(spon)});
        } catch (Exception e) {
            Logger.getLogger(rptviewObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return rs;
    }
}
