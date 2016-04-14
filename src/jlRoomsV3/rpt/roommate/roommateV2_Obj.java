/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsV3.rpt.roommate;

import jlRoomsV3.rpt.payment.paymentV3_Obj_Rpt;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jlRoomsCommon._beans.rptBeanColumes;
import jlRoomsCommon._beans.rptBeanColumesAmtList;
import obj.db.v1.dbMgrInterface;
import sun.jdbc.rowset.CachedRowSet;
/**
 *
 * @author Lloyd
 */
public class roommateV2_Obj {
    String who;
    //get01CustomerRoomate
    public List<rptBeanColumesAmtList> getRoommate(int spondorId, String key, dbMgrInterface x){
        return getRoommate((new roommateV2_Sql()).get01CustomerRoomateAll(key), new Object[]{ spondorId},x);
    }
    public List<rptBeanColumesAmtList> getRoommate(int cust,int spondorId, String key, dbMgrInterface x){
        return getRoommate((new roommateV2_Sql()).get01CustomerRoomate(key), new Object[]{cust, spondorId},x);
    }
    public List<rptBeanColumesAmtList> getRoommateVendor(int vendorId,int spondorId, String key, dbMgrInterface x){
        return getRoommate((new roommateV2_Sql()).get01CustomerRoomateVendor(key), new Object[]{vendorId, spondorId},x);
    }
    private List<rptBeanColumesAmtList> getRoommate(String sql,Object[] obj, dbMgrInterface x){
         CachedRowSet cr = null;
         int curr,last=-1;
          List<rptBeanColumesAmtList> rpt = new ArrayList<rptBeanColumesAmtList>();
          List<rptBeanColumes> list = new  ArrayList<rptBeanColumes>();
          try {
            //
            cr = x.getCachedRowSet(sql, obj);
            while(cr.next()){
                curr = cr.getInt(1);
                if (last != -1 && curr != last ){
                    rptBeanColumesAmtList b = new rptBeanColumesAmtList();
                    b.setHeader(who);
                    b.setRptList(list);
                    rpt.add(b);
                    who = null;
                    list = new  ArrayList<rptBeanColumes>();
                }
                list.add(getrptBeanColumes(cr));
                last = curr;
            }
            if (!list.isEmpty()){
                rptBeanColumesAmtList b = new rptBeanColumesAmtList();
                    b.setHeader(who);
                    b.setRptList(list);
                    rpt.add(b);
            }
        } catch (Exception ex) {
            Logger.getLogger(paymentV3_Obj_Rpt.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            x.closeCachedRowSet(cr);
        }
          return rpt;
     }
     private rptBeanColumes getrptBeanColumes(CachedRowSet cr){
         rptBeanColumes b = new rptBeanColumes();
         try {
             b.setCol01(""+cr.getInt(1));
             b.setCol02(cr.getString(2));
             b.setCol03(cr.getString(3));
             if (who == null) who = cr.getString(3);
             b.setCol04(cr.getString(4));
             b.setCol05(cr.getString(5));
             b.setCol06(cr.getString(6));
             b.setCol07(cr.getString(7));
             b.setCol08(cr.getString(8));
             b.setCol09(cr.getString(9));
             b.setCol10(cr.getString(10));
         } catch (SQLException ex) {
             Logger.getLogger(roommateV2_Obj.class.getName()).log(Level.SEVERE, null, ex);
         }
                 
         return b;
     }
     //================================================================================
     
}
