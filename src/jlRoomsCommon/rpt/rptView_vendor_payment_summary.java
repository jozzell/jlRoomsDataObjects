/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsCommon.rpt;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import obj.db.v1.dbMgrInterface;
import sun.jdbc.rowset.CachedRowSet;

/**
 *
 * @author lmeans
 */
public class rptView_vendor_payment_summary {
    rptView_vendor_payment_summarySql rptView_vendor_payment_summarySql;
    rptView_vendor_payment_summaryPDF rptView_vendor_payment_summaryPDF;
    public rptView_vendor_payment_summary(){
        rptView_vendor_payment_summarySql = new rptView_vendor_payment_summarySql();
        rptView_vendor_payment_summaryPDF = new rptView_vendor_payment_summaryPDF();
    }
    public  void rptView_vendor_payment_summary(Date start, Date end,String web,dbMgrInterface db,Document doc){
        try {
            rptView_vendor_payment_summarySql.rptView_vendor_payment_summary_DateRange(web);
            
            CachedRowSet cr = rptView_vendor_payment_summary( start,  end, web, db);
            rptView_vendor_payment_summaryPDF.rptView_vendor_payment_summaryPDF(doc, cr);
            db.closeCachedRowSet(cr);
        } catch (Exception ex) {
            Logger.getLogger(rptView_vendor_payment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // ========================================================================================
     private  CachedRowSet rptView_vendor_payment_summary(Date start, Date end,int id,String web,dbMgrInterface db) {
        CachedRowSet rs = null;
        try {
            rs = db.getCachedRowSet(rptView_vendor_payment_summarySql.rptView_vendorID_payment_summary_DateRange(web), new Object[]{start, end,id});
        } catch (Exception e) {
            Logger.getLogger(rptView_vendor_payment_summary.class.getName()).log(Level.SEVERE, null, e);
        }
        return rs;
    }
    
     public  void rptView_vendor_payment_summary(Date start, Date end,int id,String web,dbMgrInterface db,Document doc){
        try {
            rptView_vendor_payment_summarySql.rptView_vendor_payment_summary_DateRange(web);
            
            CachedRowSet cr = rptView_vendor_payment_summary( start,  end,id,web, db);
            rptView_vendor_payment_summaryPDF.rptView_vendor_payment_summaryPDF(doc, cr);
            db.closeCachedRowSet(cr);
        } catch (Exception ex) {
            Logger.getLogger(rptView_vendor_payment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // ========================================================================================
     private  CachedRowSet rptView_vendor_payment_summary(Date start, Date end,String web,dbMgrInterface db) {
        CachedRowSet rs = null;
        try {
            rs = db.getCachedRowSet(rptView_vendor_payment_summarySql.rptView_vendor_payment_summary_DateRange(web), new Object[]{start, end});
        } catch (Exception e) {
            Logger.getLogger(rptView_vendor_payment_summary.class.getName()).log(Level.SEVERE, null, e);
        }
        return rs;
    }
}
