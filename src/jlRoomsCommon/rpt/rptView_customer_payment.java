/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsCommon.rpt;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
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
public class rptView_customer_payment implements Serializable{
    rptView_customer_paymentAry rptView_customer_paymentAry;
    rptView_customer_paymentSql rptView_customer_paymentSql;
    rptView_customer_paymentPDF rptView_customer_paymentPDF;
    public rptView_customer_payment(){
        rptView_customer_paymentAry = new rptView_customer_paymentAry();
        rptView_customer_paymentSql = new rptView_customer_paymentSql();
        rptView_customer_paymentPDF = new rptView_customer_paymentPDF();
    }
   
   // ========================================================
    public   rptBeanColumesAmtList rptView_customer_payment_sponsor_customer(int spon,int cust,String web,dbMgrInterface db) {
        return rptView_customer_paymentAry.get_rptView_customer_payment(crs_rptView_customer_payment_sponsor_customer(spon,cust,web,db));
    }
   private   CachedRowSet crs_rptView_customer_payment_sponsor_customer(int spon,int cust,String web,dbMgrInterface db) {
        CachedRowSet rs = null;
        try {
            rs = db.getCachedRowSet(rptView_customer_paymentSql.rptView_customer_payment_sponsor_customer(web), 
                    new Object[]{new Integer(spon),new Integer(cust)});
        } catch (Exception e) {
            Logger.getLogger(rptviewObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return rs;
    }
   
   // ========================================================
    public  rptBeanColumesAmtList get_rptView_customer_payment(int spon,Date start, Date end,String web,dbMgrInterface db) {
        return rptView_customer_paymentAry.get_rptView_customer_payment(crs_get_rptView_customer_payment(spon,start,end,web,db));
    }
    
    private  CachedRowSet crs_get_rptView_customer_payment(int i, Date start, Date end,String web,dbMgrInterface db) {
        CachedRowSet rs = null;
        try {
            rs = db.getCachedRowSet(rptView_customer_paymentSql.rptView_customer_payment(web), new Object[]{new Integer(i), start, end});
        } catch (Exception e) {
            Logger.getLogger(rptviewObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return rs;
    }
    
    // ========================================================
    public   rptBeanColumesAmtList get_rptView_customer_payment(int spon,String web,dbMgrInterface db) {
        return rptView_customer_paymentAry.get_rptView_customer_payment(crs_get_rptView_customer_payment(spon,web,db));
    }
    private   CachedRowSet crs_get_rptView_customer_payment(int spon,String web,dbMgrInterface db) {
        CachedRowSet rs = null;
        try {
            rs = db.getCachedRowSet(rptView_customer_paymentSql.rptView_customer_payment_sponsor(web), new Object[]{new Integer(spon)});
        } catch (Exception e) {
            Logger.getLogger(rptviewObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return rs;
    }
    // ====================================================================
    public   void  get_rptView_customer_payment(Date start, Date end,int cust,String web,dbMgrInterface db,Document doc) {
        CachedRowSet cr = crs_get_rptView_customer_payment(start,end,cust,web,db);
        if (cr != null && cr.size() > 0){
            rptView_customer_paymentPDF.get_rptView_customer_payment(doc,cr);
        }
        db.closeCachedRowSet(cr);
    }
     private   CachedRowSet crs_get_rptView_customer_payment(Date start, Date end,int cust,String web,dbMgrInterface db) {
        CachedRowSet rs = null;
        try {
            rs = db.getCachedRowSet(rptView_customer_paymentSql.rptView_customer_payment(web), new Object[]{cust,start, end});
        } catch (Exception e) {
            Logger.getLogger(rptviewObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return rs;
    }
    // ============== 2014 03 06 ==========================================
    
    public   rptBeanColumesAmtList  get_rptView_customer_payment(Date start, Date end,String web,dbMgrInterface db) {
        return rptView_customer_paymentAry.get_rptView_customer_payment(crs_get_rptView_customer_payment(start,end,web,db));
    }
    
    
     public   void  get_rptView_customer_payment(Date start, Date end,String web,dbMgrInterface db,Document doc) {
         CachedRowSet cr = crs_get_rptView_customer_payment(start,end,web,db);
         if (cr != null && cr.size() > 0){
            rptView_customer_paymentPDF.get_rptView_customer_payment(doc,cr);
         }
         db.closeCachedRowSet(cr);
     }
     private   CachedRowSet crs_get_rptView_customer_payment(Date start, Date end,String web,dbMgrInterface db) {
        CachedRowSet rs = null;
        try {
            rs = db.getCachedRowSet(rptView_customer_paymentSql.rptView_customer_payment_dateRange(web), new Object[]{start, end});
        } catch (Exception e) {
            Logger.getLogger(rptviewObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return rs;
    }
     
     
   
   
    
   
}
