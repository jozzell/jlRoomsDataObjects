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
public class rptView_payment_booked_type implements Serializable {
    rptView_payment_booked_typePDF rptView_payment_booked_typePDF;
    rptView_payment_booked_typeSql rptView_payment_booked_typeSql;
    rptView_payment_booked_typeAry rptView_payment_booked_typeAry;
    public rptView_payment_booked_type(){
        rptView_payment_booked_typePDF = new rptView_payment_booked_typePDF();
        rptView_payment_booked_typeSql = new rptView_payment_booked_typeSql();
        rptView_payment_booked_typeAry = new rptView_payment_booked_typeAry();
    }
     public    void rptview_payment_booked_type(Date start,Date end, String web, dbMgrInterface db,Document doc) {
         try {
             CachedRowSet rs = rptview_payment_booked_type( start, end,  web,  db);
             rptView_payment_booked_typePDF.rptviewPdfEvent(doc, rs);
             //rptView_customer_payment_summaryPDF.get_rptView_customer_payment_summary(doc, rs);
             db.closeCachedRowSet(rs);
         } catch (Exception ex) {
             Logger.getLogger(rptView_payment_booked_type.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
    public    CachedRowSet rptview_payment_booked_type(Date start,Date end, String web, dbMgrInterface db) {
         CachedRowSet rs = null;
          try {
            rs = db.getCachedRowSet(rptView_payment_booked_typeSql.rptview_payment_booked_type_daterange(web), new Object[]{start,end});
        } catch (Exception e) {
            Logger.getLogger(rptviewObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return rs;
    }
   // ==============================================================================
   

   
    private   CachedRowSet crs_rptview_payment_booked_type(int spon, String web, dbMgrInterface db) {
        CachedRowSet rs = null;
        try {
            rs = db.getCachedRowSet(rptView_payment_booked_typeSql.rptview_payment_booked_type_sponsor(web), new Object[]{new Integer(spon)});
        } catch (Exception e) {
            Logger.getLogger(rptviewObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return rs;
    }
     public    rptBeanColumesAmtList rptview_payment_booked_type_sponsor(int spon, String web, dbMgrInterface db) {
        return rptView_payment_booked_typeAry.rptView_payment_booked_type(crs_rptview_payment_booked_type(spon,web,db));
    }
     // ================================================================================
    
      private    CachedRowSet crs_rptview_payment_booked_type_sponsor_cust(int spon, int cust,String web, dbMgrInterface db) {
        CachedRowSet rs = null;
        try {
            rs = db.getCachedRowSet(rptView_payment_booked_typeSql.rptview_payment_booked_type_sponsor_cust(web), 
                    new Object[]{new Integer(spon),new Integer(cust)});
        } catch (Exception e) {
            Logger.getLogger(rptviewObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return rs;
    }
     public    rptBeanColumesAmtList rptview_payment_booked_type_sponsor_cust(int spon,int cust, String web, dbMgrInterface db) {
        return rptView_payment_booked_typeAry.rptView_payment_booked_type(crs_rptview_payment_booked_type_sponsor_cust(spon,cust,web,db));
    }
     
     
    // ==================================================================================
     
    

}
