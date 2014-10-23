/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsCommon.rpt;

import com.lowagie.text.Document;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import jlRoomsCommon._objects.jlRoomsDbConnIinterface;
import jlRoomsCommon.rpt.db.rptviewObj;
import jlRoomsCommon.rpt.db.rptviewSql;
import obj.db.v1.dbMgrInterface;
import sun.jdbc.rowset.CachedRowSet;

/**
 *
 * @author lmeans
 */
public class rptView_payment_type_summary implements Serializable{
     private jlRoomsDbConnIinterface jlRoomsFactoryDB;
     rptviewSql rptviewSql;
     rptView_payment_typePDF rptView_payment_typePDF;
     rptView_payment_type_summarySql rptView_payment_type_summarySql;
    private rptView_payment_type_summary(){
        rptviewSql = new rptviewSql();
        rptView_payment_typePDF = new rptView_payment_typePDF();
        rptView_payment_type_summarySql = new rptView_payment_type_summarySql();
    }
    public rptView_payment_type_summary(jlRoomsDbConnIinterface x) {
        this();
      this.jlRoomsFactoryDB = x;
    }
    
     // ========================================================
    public  void rptview_payment_type_summary(Date start, Date end,String web,dbMgrInterface db,Document doc) {
        try {
            CachedRowSet cr = rptview_payment_type_summary( start,  end, web, db);
            rptView_payment_typePDF.rptView_payment_typePDF_Summary(doc, cr);
            db.closeCachedRowSet(cr);
        } catch (Exception ex) {
            Logger.getLogger(rptView_vendor_payment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private  CachedRowSet rptview_payment_type_summary(Date start, Date end,String web,dbMgrInterface db) {
        CachedRowSet rs = null;
        try {
            rs = db.getCachedRowSet(rptView_payment_type_summarySql.rptview_payment_type_summary_dateRange(web), new Object[]{start, end});
        } catch (Exception e) {
            Logger.getLogger(rptviewObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return rs;
    }
    // =======================================================================
    
    private  CachedRowSet rptview_payment_type_summaryXXX(int spon) {
        return rptview_payment_type_summaryXXX(spon,this.jlRoomsFactoryDB,null);
    }
    
    private   CachedRowSet rptview_payment_type_summaryXXX(int spon,dbMgrInterface db,String email) {
        CachedRowSet rs = null;
        try {
            rs = db.getCachedRowSet(rptviewSql.rptView_payment_type_summaryXXX(email), new Object[]{new Integer(spon)});
        } catch (Exception e) {
            Logger.getLogger(rptviewObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return rs;
    }
    // =======================================================================================
}
