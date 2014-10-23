/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsCommon.rpt.db;

import java.io.Serializable;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.jdbc.rowset.CachedRowSet;

import jlRoomsCommon._objects.jlRoomsDbConnIinterface;

/**
 *
 * @author lmeans
 */
public class rptviewObj  implements Serializable{

    private jlRoomsDbConnIinterface jlRoomsFactory;
    private rptviewSql rptviewSql;
    public rptviewObj(){
        rptviewSql = new rptviewSql();
    }
    public rptviewObj(jlRoomsDbConnIinterface x) {
        this();
        this.jlRoomsFactory = x;

    }
    
    
   
    // ======================================================================================
    public CachedRowSet getEventBooking(int spon, boolean booking) {
        CachedRowSet rs = null;
        try {
            rs = jlRoomsFactory.getCachedRowSet(rptviewSql.getEventBooking(booking), new Object[]{new Integer(spon)});
        } catch (Exception e) {
            Logger.getLogger(rptviewObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return rs;
    }
   
    // ========================================================
   
    
   
    // ========================================================
    public CachedRowSet rptview_payment_booked_type(Date start, Date end) {
        CachedRowSet rs = null;
        try {
            rs = jlRoomsFactory.getCachedRowSet(rptviewSql.rptview_payment_type_daterange, new Object[]{start, end});
        } catch (Exception e) {
            Logger.getLogger(rptviewObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return rs;
    }
    // ========================================================
    public CachedRowSet rptview_payment_type(int spon) {
        CachedRowSet rs = null;
        try {
            rs = jlRoomsFactory.getCachedRowSet(rptviewSql.rptview_payment_type_sponsor, new Object[]{new Integer(spon)});
        } catch (Exception e) {
            Logger.getLogger(rptviewObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return rs;
    }
    // ========================================================
    public CachedRowSet rptview_payment_type(Date start, Date end) {
        CachedRowSet rs = null;
        try {
            rs = jlRoomsFactory.getCachedRowSet(rptviewSql.rptview_payment_type_daterange, new Object[]{start, end});
        } catch (Exception e) {
            Logger.getLogger(rptviewObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return rs;
    }
    // ========================================================
    public CachedRowSet rptView_Vendor_payment_summary(int spon) {
        CachedRowSet rs = null;
        try {
            rs = jlRoomsFactory.getCachedRowSet(rptviewSql.rptView_Vendor_payment_summary, new Object[]{new Integer(spon)});
        } catch (Exception e) {
            Logger.getLogger(rptviewObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return rs;
    }
   
    
   
    // ========================================================
    public CachedRowSet getDailyRmCnt(int spon) {
        CachedRowSet rs = null;
        try {
            rs = jlRoomsFactory.getCachedRowSet(rptviewSql.sql_rpt_get_day_cnt, new Object[]{new Integer(spon)});
        } catch (Exception e) {
            Logger.getLogger(rptviewObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return rs;
    }
    // ========================================================
    public CachedRowSet getRoommate(int spon) {
        CachedRowSet rs = null;
        try {
            rs = jlRoomsFactory.getCachedRowSet(
                    rptviewSql.sql_getRoommate_listing,
                    new Object[]{new Integer(spon)});
        } catch (Exception e) {
            Logger.getLogger(rptviewObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return rs;

    }
    
    // ==============================================================================================
/*
    public void get_rptview_payment_booking_type(Document doc, int spon) throws Exception {

        try {
            jlRoomsFactoryRpt.getOffice(doc, false, spon, "Processing Fee Detail Report");
            get_rptview_payment_type(doc, jlRoomsFactoryObj.get_rptviewObj().rptview_payment_booked_type(spon));
        } catch (Exception ex) {
            throw ex;
        }
    }

    public  void get_rptview_payment_booking_type(Document doc, Date s, Date e) throws Exception {
        String str = objMgr.getDateFormat(s);
        if (!s.equals(e)) {
            str += " thru " + objMgr.getDateFormat(e);
        }

        try {
            jlRoomsFactoryRpt.getOffice(doc, false, 0, "Processing Fee Detail Report \n" + str);
            get_rptview_payment_type(doc, jlRoomsFactoryObj.get_rptviewObj().rptview_payment_booked_type(s, e));
        } catch (Exception ex) {
            throw ex;
        }
    }

    public  void get_rptview_payment_type(Document doc, int spon) throws Exception {

        try {
            jlRoomsFactoryRpt.getOffice(doc, false, spon, "Payment Detail Report");
            get_rptview_payment_type(doc, jlRoomsFactoryObj.get_rptviewObj().rptview_payment_type(spon));
        } catch (Exception ex) {
            throw ex;
        }
    }

    public  void get_rptview_payment_type(Document doc, Date s, Date e) throws Exception {
        String str = objMgr.getDateFormat(s);
        if (!s.equals(e)) {
            str += " thru " + objMgr.getDateFormat(e);
        }

        try {
            jlRoomsFactoryRpt.getOffice(doc, false, 0, "Payment Detail Report \n" + str);
            get_rptview_payment_type(doc, jlRoomsFactoryObj.get_rptviewObj().rptview_payment_type(s, e));
        } catch (Exception ex) {
            throw ex;
        }
    }

    public  double rptview_payment_booked_type_summary(Document doc, int spon) throws Exception {
        try {
            return rptView_payment_booked_type_summary.rptview_payment_booked_type_summary(doc, spon, true);
        } catch (Exception e) {
            throw e;
        }
    }
    public  double get_rptview_payment_type(Document doc, CachedRowSet cr) throws Exception {
        try {
            return get_rptview_payment_type(doc, cr, true, true);
        } catch (Exception e) {
            throw e;
        }
    }
    * */
    //public CachedRowSet rptview_payment_booked_type_summary(int spon) {
    //return rptview_payment_booked_type_summary(spon, null, this.jlRoomsFactoryDB);
    //}
}
