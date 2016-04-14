/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsV3.rpt.block;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jlRoomsCommon._beans.rptBeanColumes;
import jlRoomsCommon._beans.rptBeanColumesAmtList;
import jlRoomsCommon.objMgr;
import obj.db.v1.dbMgrInterface;
import sun.jdbc.rowset.CachedRowSet;

/**
 *
 * @author lmeans
 */
public class blockRptV3_Obj  extends objMgr implements Serializable {
    private List<rptBeanColumesAmtList> rpt = new ArrayList<rptBeanColumesAmtList>();
    SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd yyyy hh:mm a");
    private double  totalAmt[] = new double[4];
    private double cnt;
    private double amt;
    public List<rptBeanColumesAmtList> genBlockRpt(int spondorId, String key, dbMgrInterface x) {
        CachedRowSet cr = null;
        boolean ok = true;
        rpt = new ArrayList<rptBeanColumesAmtList>();
        List<rptBeanColumes> list = new ArrayList<rptBeanColumes>();; // = new ArrayList<rptBeanColumes>();
        try {
            cr = x.getCachedRowSet((new blockRptV3_Sql()).getBlockRpt01(key),  new Object[]{spondorId});
            double blockCurr,blockLast=-1;
            while(cr.next()){
                blockCurr =  cr.getInt(2);
                if (blockLast != -1 && blockLast != blockCurr){
                    rptBeanColumesAmtList a = new rptBeanColumesAmtList();
                    a.setRptList(list);
                    a.setTotal(getTotalAmt());
                    if (ok){
                        a.setHeader(getDollarFormat(this.getTotalAmt()[3]));
                    } else {
                        a.setHeader("---.--");
                    }
                    rpt.add(a);
                    
                    list = new ArrayList<rptBeanColumes>();
                    totalAmt = new double[4];
                }
                if (ok = cr.getInt(1) != 9)
                if (list.isEmpty()&& cr.getInt(1) == 1) {
                    list.add( getRpt1St(cr));
                }
                list.add(getRpt(cr));
                blockLast = blockCurr;
            }
            if (!list.isEmpty()){
                 rptBeanColumesAmtList a = new rptBeanColumesAmtList();
                    a.setRptList(list);
                    a.setTotal(getTotalAmt());
                    
                    a.setHeader(getDollarFormat(this.getTotalAmt()[3]));
                    rpt.add(a);
                    
            }
        } catch (Exception ex) {
            Logger.getLogger(blockRptV3_Obj.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            x.closeCachedRowSet(cr);
        }
        return rpt;
    }
    private rptBeanColumes getRpt1St(CachedRowSet cr){
        rptBeanColumes rpt = new rptBeanColumes();;
       try {
            
            int type = cr.getInt(9);
            
            rpt.setCol01(cr.getString(6));
            rpt.setCol02(cr.getString(7));
            rpt.setCol03("[Reserved Block]");
            
            rpt.setCol04(""+cr.getDouble(3));
            cnt+= cr.getDouble(3);
            rpt.setCol05(getDollarFormat(cr.getDouble(4)));
            //rpt.setCol06(""+cr.getInt(14));
            
            rpt.setCol08("");
             rpt.setCol09("");
                     
            rpt.setCol10(cr.getString(5));
            if (cr.getInt(1) != 9){
           
                rpt.setCol07(getDollarFormat(cr.getDouble(4) * cr.getDouble(3) ));
                totalAmt[0] += (cr.getDouble(3) );
                //total[1] += cr.getDouble(13);
                //total[2] += cr.getDouble(14);
                amt += (cr.getDouble(4) * cr.getDouble(3));
                totalAmt[3] += (cr.getDouble(4) * cr.getDouble(3));
            
            //cnt += cr.getDouble(3);
            //amt += (cr.getDouble(4) * cr.getDouble(3));
            }
           
           
            
        } catch (SQLException ex) {
            Logger.getLogger(blockRptV3_Obj.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rpt;
    }
    private rptBeanColumes getRpt(CachedRowSet cr){
        rptBeanColumes rpt = null;
        
        try {
            rpt = new rptBeanColumes();
            int type = cr.getInt(9);
            
            rpt.setCol01(cr.getString(6));
            rpt.setCol02(cr.getString(7));
            rpt.setCol03(cr.getString(8));
            if (rpt.getCol03() == null) rpt.setCol03("Booked");
            
            
            
            rpt.setCol05(getDollarFormat(cr.getDouble(13)));
            rpt.setCol06(""+cr.getDouble(14));
            
            
            if (cr.getInt(1) == 1){
                rpt.setCol04(""+(cr.getDouble(12) * -1));
                rpt.setCol07(getDollarFormat(cr.getDouble(15) * -1));
                totalAmt[0] += cr.getDouble(12) * -1;
                totalAmt[1] += cr.getDouble(13) * -1;
                totalAmt[2] += cr.getDouble(14) * -1;
                totalAmt[3] += cr.getDouble(15) * -1;
            
                cnt += cr.getDouble(12) * -1 ;
                amt += cr.getDouble(15) * -1;
            } else {
                rpt.setCol12(""+(cr.getDouble(12) * -1));
            }
            
            
            
            if(cr.getDate(16) != null) rpt.setCol08(chkDate(sdf.format(cr.getDate(16))));
            if(cr.getDate(17) != null) rpt.setCol09(chkDate(sdf.format(cr.getDate(17))));
            rpt.setCol10(cr.getString(5));
            
        } catch (SQLException ex) {
            Logger.getLogger(blockRptV3_Obj.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rpt;
    }

    /**
     * @return the totalAmt
     */
    public double[] getTotalAmt() {
        return totalAmt;
    }

    /**
     * @return the cnt
     */
    public double getCnt() {
        return cnt;
    }

    /**
     * @return the amt
     */
    public double getAmt() {
        return amt;
    }
    
}
