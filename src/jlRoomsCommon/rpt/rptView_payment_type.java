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
import jlRoomsCommon.rpt.db.rptviewObj;
import obj.db.v1.dbMgrInterface;
import sun.jdbc.rowset.CachedRowSet;
/**
 *
 * @author lmeans
 */
public class rptView_payment_type {
    //rptView_payment_typePDF
    rptView_payment_typePDF rptView_payment_typePDF;
    rptView_payment_typeSql rptView_payment_typeSql;
    public rptView_payment_type(){
        rptView_payment_typePDF = new rptView_payment_typePDF();
        rptView_payment_typeSql = new rptView_payment_typeSql();
    }
    public   void rptView_customer_payment_dateRange(Date start, Date end, String email, dbMgrInterface db,Document doc) {
        CachedRowSet rs = rptView_customer_payment_summary_dateRange( start,  end,  email,  db);
        try {
            rptView_payment_typePDF.rptView_payment_typePDF(doc, rs);
        } catch (Exception ex) {
            Logger.getLogger(rptView_payment_type.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            db.closeCachedRowSet(rs);
        }
         
    }
    public  CachedRowSet rptView_customer_payment_summary_dateRange(Date start, Date end, String email, dbMgrInterface db) {
        CachedRowSet rs = null;
        try {
            rs = db.getCachedRowSet(rptView_payment_typeSql.rptview_payment_type_dateRange(email), new Object[]{start, end});
        } catch (Exception e) {
            Logger.getLogger(rptviewObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return rs;
    }
}
