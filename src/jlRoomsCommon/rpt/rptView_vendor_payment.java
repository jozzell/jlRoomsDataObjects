/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsCommon.rpt;

import com.itextpdf.text.Document;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import jlRoomsCommon._beans.rptBeanColumesAmtList;
import jlRoomsCommon.rpt.db.rptviewObj;

import obj.db.v1.dbMgrInterface;
import sun.jdbc.rowset.CachedRowSet;

/**
 *
 * @author lmeans
 */
public class rptView_vendor_payment implements Serializable{
    rptView_vendor_paymentPdf  rptView_vendor_paymentPdf;
    rptView_vendor_paymentSql rptView_vendor_paymentSql;
    rptView_vendor_paymentAry rptView_vendor_paymentAry;
    public rptView_vendor_payment(){
        rptView_vendor_paymentPdf = new rptView_vendor_paymentPdf();
        rptView_vendor_paymentSql = new rptView_vendor_paymentSql();
        rptView_vendor_paymentAry = new rptView_vendor_paymentAry();
    }
    // ========================================================
    public  void rptView_Vendor_payment(Date start, Date end,String web,dbMgrInterface db,Document doc) {
        try {
            CachedRowSet cr = rptView_Vendor_payment( start,  end, web, db);
            rptView_vendor_paymentPdf.get_rptView_Vendor_payment(doc, cr);
            db.closeCachedRowSet(cr);
        } catch (Exception ex) {
            Logger.getLogger(rptView_vendor_payment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private  CachedRowSet rptView_Vendor_payment(Date start, Date end,String web,dbMgrInterface db) {
        CachedRowSet rs = null;
        try {
            rs = db.getCachedRowSet(rptView_vendor_paymentSql.rptView_Vendor_DateRange(web), new Object[]{start, end});
        } catch (Exception e) {
            Logger.getLogger(rptviewObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return rs;
    }
    // =============================================================================
    public  void rptView_Vendor_payment(Date start, Date end,int id,String web,dbMgrInterface db,Document doc) {
        try {
            CachedRowSet cr = rptView_VendorID_payment( start,  end,id,web, db);
            rptView_vendor_paymentPdf.get_rptView_Vendor_payment(doc, cr);
            db.closeCachedRowSet(cr);
        } catch (Exception ex) {
            Logger.getLogger(rptView_vendor_payment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     private  CachedRowSet rptView_VendorID_payment(Date start, Date end,int ven,String web,dbMgrInterface db) {
        CachedRowSet rs = null;
        try {
            rs = db.getCachedRowSet(rptView_vendor_paymentSql.rptView_VendorID_DateRange(web), new Object[]{start, end, ven});
        } catch (Exception e) {
            Logger.getLogger(rptviewObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return rs;
    }
    // ========================================================
    private CachedRowSet rptView_Vendor_payment(int spondor_id,Date start, Date end,String web,dbMgrInterface db) {
        CachedRowSet rs = null;
        try {
            rs = db.getCachedRowSet(rptView_vendor_paymentSql.rptView_Vendor_spondor_payment_daterange(web), new Object[]{new Integer(spondor_id),start, end});
        } catch (Exception e) {
            Logger.getLogger(rptviewObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return rs;
    }
    // ========================================================
    private CachedRowSet rptView_Vendor_payment(int spondor_id,int vendor,  Date start, Date end,String web,dbMgrInterface db) {
        CachedRowSet rs = null;
        try {
            rs = db.getCachedRowSet(rptView_vendor_paymentSql.rptView_Vendor_Sponsor_DateRange_payment(web), new Object[]{new Integer(spondor_id),new Integer(vendor), start, end});
        } catch (Exception e) {
            Logger.getLogger(rptviewObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return rs;
    }
     // ************* &&&&&&&&&&& Edited &&&&&&&&&&&&&&& ***************
     // ================================================================
    private  CachedRowSet rptView_Vendor_payment_crs(int spon,String web,dbMgrInterface db ) {
        CachedRowSet rs = null;
        try {
            rs = db.getCachedRowSet(rptView_vendor_paymentSql.rptView_Vendor_payment_sponsor(web), new Object[]{new Integer(spon)});
        } catch (Exception e) {
            Logger.getLogger(rptviewObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return rs;
    }
    public  rptBeanColumesAmtList rptView_Vendor_payment(int spon,String web,dbMgrInterface db ) {
       
        try {
            return rptView_vendor_paymentAry.getRptBeanColumesAmtList(rptView_Vendor_payment_crs(spon,web,db));
        } catch (Exception e){
            Logger.getLogger(rptviewObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
    // ========================================================
    private  CachedRowSet rptView_Vendor_payment_crs(int spondor_id,int vendor,String web,dbMgrInterface db) {
        CachedRowSet rs = null;
        try {
            rs = db.getCachedRowSet(rptView_vendor_paymentSql.rptView_Vendor_Sponsor_payment(web), new Object[]{new Integer(spondor_id),new Integer(vendor)});
        } catch (Exception e) {
            Logger.getLogger(rptviewObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return rs;
    }
    public  rptBeanColumesAmtList rptView_Vendor_payment(int spondor_id,int vendor,String web,dbMgrInterface db) {
        try {
            return rptView_vendor_paymentAry.getRptBeanColumesAmtList(rptView_Vendor_payment_crs(spondor_id,vendor,web,db));
        } catch (Exception e){
            Logger.getLogger(rptviewObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return new rptBeanColumesAmtList();
    }
    // ========================================================
   
    
    
}
             

