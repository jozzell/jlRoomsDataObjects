/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsCommon.rpt.db;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jlRoomsCommon._beans.lookupBean;
import jlRoomsCommon._beans.rptBeanColumes;
import jlRoomsCommon._beans.rptBeanColumesAmtList;
import jlRoomsCommon.objMgr;
import obj.db.v1.dbMgrInterface;
import sun.jdbc.rowset.CachedRowSet;

/**
 *
 * @author lmeans
 */
public class rptObj extends objMgr {
    rptSql rptSql;
     public rptObj(){
         rptSql = new rptSql();
     }
     public   List<lookupBean> getClientCheckAir(String eMail, dbMgrInterface db){
        CachedRowSet rs = null;
        List<lookupBean> l = new ArrayList<lookupBean>();
        try {
           
            
             rs = db.getCachedRowSet(rptSql.sql_rpt_flt_v2, new Object[]{eMail});
                while(rs.next()){
                    lookupBean b = new lookupBean();
                    b.setDesc(rs.getString(3)+" "+rs.getString(4));
                    b.setDate(rs.getDate(1));
                    b.setEndDate(rs.getDate(2));
                    l.add(b);
                }
        } catch (Exception ex) {
            Logger.getLogger(rptObj.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeCachedRowSet(rs);
        }
        return l;
    }
     
     
     
    public   List<lookupBean> getClientCheckInCnt(int spon,String eMail, dbMgrInterface db){
        CachedRowSet rs = null;
        List<lookupBean> l = new ArrayList<lookupBean>();
        try {
            
            
             rs = db.getCachedRowSet(rptSql.sql_rpt_client_check_in_V2, new Object[]{spon,eMail});
                while(rs.next()){
                    lookupBean b = new lookupBean();
                    b.setDesc(rs.getString(3)+" Chk In Cnt: "+rs.getInt(1));
                    b.setDate(rs.getDate(2));
                    l.add(b);
                }
        } catch (Exception ex) {
            Logger.getLogger(rptObj.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeCachedRowSet(rs);
        }
        return l;
    }
     public  rptBeanColumesAmtList get1StNightDepositV2(int spon,int ven,String eMail, dbMgrInterface db) {
         List<rptBeanColumes> list = new ArrayList<rptBeanColumes>();
        CachedRowSet rs = null;
        double amt,total=0;
            String str,header="jlRooms";
        try {
            int curr_id = 0;
            
            rs = db.getCachedRowSet(rptSql.sql_rpt_1st_Night_V2, new Object[]{spon,ven,eMail,eMail});
            while(rs.next()){
                curr_id = rs.getInt(1);
                header = rs.getString(4);
                rptBeanColumes b = new rptBeanColumes();
                b.setCol01(rs.getString(8) + " " + rs.getString(9));
                b.setCol02(rs.getString(6) + (rs.getString(7) == null ? "" : " " + rs.getString(7)));

               
                amt = rs.getDouble(10);
                b.setCol03(this.getDollarFormat(amt));
                b.setCol04(""+rs.getDouble(11));
                str = rs.getInt(12) > 0 ? "1/" + (rs.getInt(12) + 1) : "";
                b.setCol05(str);
                if (curr_id == -6 ){
                    amt *= rs.getDouble(11);
                    amt = amt * (1 / (rs.getDouble(12) + 1));
                    total += amt;
                    b.setCol06(this.getDollarFormat(amt));
                    
                } else {
                    b.setCol06(rs.getString(3));
                }
                list.add(b);
            }

        } catch (Exception e) {
            Logger.getLogger(rptviewObj.class.getName()).log(Level.SEVERE, null, e);
            
        } finally {
         db.closeCachedRowSet(rs);
     }
     return new rptBeanColumesAmtList(header,total,list);
    }
}
