/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsV3.rpt.payment;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jlRoomsCommon._beans.rptBeanColumes;
import jlRoomsCommon._beans.rptBeanColumesAmtList;
import jlRoomsCommon.objMgr;
import sun.jdbc.rowset.CachedRowSet;

/**
 *
 * @author Lloyd
 */
public class paymentV3_Obj_ extends objMgr implements Serializable{
    private String who=null;
    private List<rptBeanColumes> rptList = null;
    private double total = 0,grandTotal;
    
    public List<rptBeanColumesAmtList> getCustPaymentAll( String key,int ptr,int whoPtr, CachedRowSet cr) {
        return getCustPaymentAll(key,ptr,whoPtr,cr,-1);
    }
    public List<rptBeanColumesAmtList> getCustPaymentAll( String key,int ptr,int whoPtr, CachedRowSet cr,int whoPtr2) {
        rptList = new ArrayList<rptBeanColumes>();
        List<rptBeanColumesAmtList> list = new ArrayList<rptBeanColumesAmtList>();
        double custCurr,custLast=-1;
        try {
            
            
            while(cr.next()){
                custCurr = cr.getDouble(ptr);
                if (custLast!= -1 && custLast != custCurr){
                    rptBeanColumesAmtList rpt = new rptBeanColumesAmtList();
                    rpt.setHeader(who);
                    rpt.setAmt(total);
                    rpt.setRptList(rptList);
                    grandTotal += total;
                    
                    list.add(rpt);
                    
                    rptList = new ArrayList<rptBeanColumes>();
                    total = 0;
                    who = null;
                }
                rptList.add(getrptBeanColumes(whoPtr,cr,whoPtr2));
                custLast = custCurr;
            }
            if (rptList.size() > 0){
                rptBeanColumesAmtList rpt = new rptBeanColumesAmtList();
                rpt.setHeader(who);
                rpt.setAmt(total);
                rpt.setRptList(rptList);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(paymentV3_Obj_Rpt.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return list;
    }
    // ==========================================================================
    public rptBeanColumes getrptBeanColumes(int whoPtr,CachedRowSet cr){
        return getrptBeanColumes(whoPtr,cr,-1);
    }
    public rptBeanColumes getrptBeanColumes(int whoPtr,CachedRowSet cr,int whoPtr2){
         rptBeanColumes x = new rptBeanColumes();
        try {
           
            x.setCol01(this.getDollarFormat(cr.getDouble(3)));
            total += cr.getDouble(3);
            x.setCol02(cr.getString(4));
            x.setCol03(cr.getString(5));
            if (who == null) {
                who =cr.getString(whoPtr);
                //if (whoPtr2 > 0) who+=" "+cr.getString(whoPtr2);
            }
            
            x.setCol04(cr.getString(6));
            x.setCol05(cr.getString(7));
        } catch (SQLException ex) {
            Logger.getLogger(paymentV3_Obj_Rpt.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;
    }
    // ==========================================================================
    public void setRptList(List<rptBeanColumes> list){
        this.rptList = list;
    }
    public List<rptBeanColumes> getRptList() {
        if (this.rptList == null) {
            this.rptList = new ArrayList<rptBeanColumes>();
        }
        return rptList;
    }

    public double getTotal() {
        return total;
    }

    public String getTotalStr() {
        return getDollarFormat(total);
    }

    /**
     * @return the who
     */
    public String getWho() {
        return who;
    }

   
    public double getGrandTotal() {
        return grandTotal;
    }
}
