/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsV3.rpt.client;

import jlRoomsV3.rpt.payment.paymentV3_Obj_Rpt;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jlRoomsCommon._beans.rptBeanColumes;
import jlRoomsCommon.objMgr;
import obj.db.v1.dbMgrInterface;
import sun.jdbc.rowset.CachedRowSet;

/**
 *
 * @author lmeans
 */
public class clientRptV3_Obj extends objMgr implements Serializable{
    private double total;
    private double cnt;
    private SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd yyyy hh:mm a");
    
    public  List<rptBeanColumes> get1StNight(int vendor, int sponsor,String web, dbMgrInterface x) {
        return get1StNight((new clientRptV3_Sql()).getClentRpt_1stNightVendor(web),new Object[]{vendor,sponsor},x);
    }
    public  List<rptBeanColumes> get1StNight(int sponsor,String web, dbMgrInterface x) {
        return get1StNight((new clientRptV3_Sql()).getClentRpt_1stNightSponsor(web),new Object[]{sponsor},x);
    }
    private List<rptBeanColumes> get1StNight(String sql,Object[] obj, dbMgrInterface x) {
        List<rptBeanColumes> list = new ArrayList<rptBeanColumes>();
       CachedRowSet cr = null;
        try {
            
            cr = x.getCachedRowSet(sql,obj);
            while(cr.next()) {
                list.add(get1StNight(cr));
            }
        } catch (Exception ex) {
            Logger.getLogger(clientRptV3_Obj.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            x.closeCachedRowSet(cr);
        }
        return list;
    }
     private rptBeanColumes get1StNight(CachedRowSet cr){
        rptBeanColumes r = new rptBeanColumes();
        try { 
            r.setCol01(cr.getString(2));
            r.setCol02(cr.getString(3));
            r.setCol03(cr.getString(4));
            r.setCol04(cr.getString(5));
            
            r.setCol05(getDollarFormat(cr.getDouble(6)));
            r.setCol06(cr.getString(7));
            r.setCol07(cr.getString(8));
            
            r.setCol08(""+cr.getDouble(9));
            r.setCol09(cr.getString(11));
            r.setCol10(cr.getString(12));
            cnt += cr.getDouble(9);
            double amt = cr.getDouble(6) * cr.getDouble(9) ;
            r.setCol11(getDollarFormat(amt));
            total += amt;
        } catch (SQLException ex) {
            Logger.getLogger(clientRptV3_Obj.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return r;
    }
    // =================================================================================================
    public List<rptBeanColumes> genClientBooking(int spondorId, String key, dbMgrInterface x) {
        //(new clientRptV3_Sql()).getClentRpt01Sponsor(key),obj);
        return genClientBooking((new clientRptV3_Sql()).getClentRpt01Sponsor(key),new Object[]{spondorId},x);
    }
    public List<rptBeanColumes> genClientBooking(int vendor,int spondorId, String key, dbMgrInterface x) {
        //(new clientRptV3_Sql()).getClentRpt01Sponsor(key),obj);
        return genClientBooking((new clientRptV3_Sql()).getClentRpt01Vendor(key),new Object[]{vendor,spondorId},x);
    }
    private List<rptBeanColumes> genClientBooking(String sql,Object[] obj, dbMgrInterface x) {
        CachedRowSet cr = null;
        List<rptBeanColumes> rptList = new ArrayList<rptBeanColumes>();
        try {
            
            cr = x.getCachedRowSet(sql,obj);
            while(cr.next()) {
                rptList.add(getrptBeanColumes(cr));
            }
        } catch (Exception ex) {
            Logger.getLogger(paymentV3_Obj_Rpt.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            x.closeCachedRowSet(cr);
        }
        return rptList;
    }
    private rptBeanColumes getrptBeanColumes(CachedRowSet cr){
        
        rptBeanColumes r = new rptBeanColumes();
        try {
            r.setCol01(cr.getString(2));
            r.setCol02(cr.getString(3));
            r.setCol03(cr.getString(4));
            r.setCol04(cr.getString(5));
            r.setCol05(cr.getString(6));
            
            r.setCol06( this.chkDate(sdf.format(cr.getDate(12))));
            r.setCol07( this.chkDate(sdf.format(cr.getDate(13))));
            if (cr.getInt(32) == -6 || cr.getInt(32) == -5  ){
                r.setCol08(getDollarFormat(cr.getDouble(14)));
                r.setCol09(getDollarFormat(cr.getDouble(15)));
                total+=cr.getDouble(15);
            }
            int i = cr.getInt(10);
            if (i == -2 || i == -13 || i == 31){
                r.setCol10(""+cr.getDouble(18));
                r.setCol18(cr.getString(23));
            }
            
            r.setCol11(""+cr.getDouble(19));
            
            if (cr.getString(28) != null){
               
                r.setCol12(cr.getString(28));
                r.setCol06( this.chkDate(sdf.format(cr.getDate(29))));
                r.setCol07( this.chkDate(sdf.format(cr.getDate(30))));
                r.setCol13(cr.getString(31));
                r.setCol19( this.chkDate(sdf.format(cr.getDate(29))));
                r.setCol20( this.chkDate(sdf.format(cr.getDate(30))));
            }
            r.setCol14(cr.getString(24));
            r.setCol15(cr.getString(25));
            r.setCol16(cr.getString(26));
            r.setCol17(cr.getString(27));
            
        } catch (SQLException ex) {
            Logger.getLogger(clientRptV3_Obj.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return r;
    }

    /**
     * @return the total
     */
    public double getTotal() {
        return total;
    }

    /**
     * @return the cnt
     */
    public double getCnt() {
        return cnt;
    }
}
