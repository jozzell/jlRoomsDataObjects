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
import jlRoomsCommon._beans.rptBeanColumesAmtList;
import jlRoomsCommon._objects.jlRoomsDbConnIinterface;
import jlRoomsCommon.rpt.db.rptviewObj;
import obj.db.v1.dbMgrInterface;
import sun.jdbc.rowset.CachedRowSet;

/**
 *
 * @author lmeans
 */
public class rptView_customer_payment_summary implements Serializable {
    rptView_customer_payment_summaryPDF rptView_customer_payment_summaryPDF;
    rptView_customer_payment_summarySql rptView_customer_payment_summarySql;
    rptView_customer_payment_summaryAry rptView_customer_payment_summaryAry;
    private jlRoomsDbConnIinterface jlRoomsFactoryDB;

    
    public rptView_customer_payment_summary(){
        rptView_customer_payment_summaryPDF = new rptView_customer_payment_summaryPDF();
        rptView_customer_payment_summarySql = new rptView_customer_payment_summarySql();
        rptView_customer_payment_summaryAry = new rptView_customer_payment_summaryAry();
    }
    public rptView_customer_payment_summary(jlRoomsDbConnIinterface x) {
        this();
        this.jlRoomsFactoryDB = x;

    }
    // ===========================================================================================

    public  void rptView_customer_payment_summary_dateRange(Date start, Date end, String email, dbMgrInterface db, Document doc) {
        CachedRowSet rs = rptView_customer_payment_summary_dateRange(start, end, email, db);
        try {
            rptView_customer_payment_summaryPDF.get_rptView_customer_payment_summary(doc, rs);
        } catch (Exception ex) {
            Logger.getLogger(rptView_customer_payment_summary.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.closeCachedRowSet(rs);
    }

    public  CachedRowSet rptView_customer_payment_summary_dateRange(Date start, Date end, String email, dbMgrInterface db) {
        CachedRowSet rs = null;
        try {
            rs = db.getCachedRowSet(rptView_customer_payment_summarySql.rptView_customer_payment_summary_dateRange(email), new Object[]{start, end});
        } catch (Exception e) {
            Logger.getLogger(rptviewObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return rs;
    }
    // ===========================================================================================

    public  void rptView_customer_payment_summary_cust_date(int cust, Date start, Date end, String email, dbMgrInterface db, Document doc) {
        CachedRowSet rs = rptView_customer_payment_summary_cust_date(cust, start, end, email, db);
        try {
            rptView_customer_payment_summaryPDF.get_rptView_customer_payment_summary(doc, rs);
        } catch (Exception ex) {
            Logger.getLogger(rptView_customer_payment_summary.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.closeCachedRowSet(rs);
    }

    public  CachedRowSet rptView_customer_payment_summary_cust_date(int cust, Date start, Date end, String email, dbMgrInterface db) {
        CachedRowSet rs = null;
        try {
            rs = db.getCachedRowSet(rptView_customer_payment_summarySql.rptView_customer_payment_summary_cust_date(email), new Object[]{new Integer(cust), start, end});
        } catch (Exception e) {
            Logger.getLogger(rptviewObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return rs;
    }
    // ===========================================================================================

    public  void rptView_customer_payment_summary_sponsor_customer(int spon, int cust, String email, dbMgrInterface db, Document doc) {
        CachedRowSet rs = rptView_customer_payment_summary_sponsor_customer(spon, cust, email, db);
        try {
            rptView_customer_payment_summaryPDF.get_rptView_customer_payment_summary(doc, rs);
        } catch (Exception ex) {
            Logger.getLogger(rptView_customer_payment_summary.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.closeCachedRowSet(rs);
    }

    public  CachedRowSet rptView_customer_payment_summary_sponsor_customer(int spon, int cust, String email, dbMgrInterface db) {
        CachedRowSet rs = null;
        try {
            rs = db.getCachedRowSet(rptView_customer_payment_summarySql.rptView_customer_payment_summary_sponsor_customer(email), new Object[]{new Integer(spon), new Integer(cust)});
        } catch (Exception e) {
            Logger.getLogger(rptviewObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return rs;
    }
    // ===========================================================================================

    public  void rptView_customer_payment_summary(int spon, String email, dbMgrInterface db, Document doc) {
        CachedRowSet rs = rptView_customer_payment_summary(spon, email, db);
        try {
            rptView_customer_payment_summaryPDF.get_rptView_customer_payment_summary(doc, rs);
        } catch (Exception ex) {
            Logger.getLogger(rptView_customer_payment_summary.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.closeCachedRowSet(rs);
    }

    public  CachedRowSet rptView_customer_payment_summary(int spon, String email, dbMgrInterface db) {
        CachedRowSet rs = null;
        try {
            rs = db.getCachedRowSet(rptView_customer_payment_summarySql.rptView_customer_payment_summary_sponsor(email), new Object[]{new Integer(spon)});
        } catch (Exception e) {
            Logger.getLogger(rptviewObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return rs;
    }

     public  rptBeanColumesAmtList get_rptView_customer_payment_summary(int spon, String web, dbMgrInterface db) { //
         CachedRowSet cr = rptView_customer_payment_summary(spon, web, db);
         return rptView_customer_payment_summaryAry.get_rptView_customer_payment_summary(cr);
     }
    // ===========================================================================================
   
    // ********************************************************************************************************
    // public void get_rptView_customer_payment_summary(Document doc, int spon) throws Exception{
    //   get_rptView_customer_payment_summary(doc,spon,null,this.jlRoomsFactoryDB);
    //}

   
    /*
     * public void get_rptView_customer_payment_summary(Document doc, int spon) throws Exception{
     get_rptView_customer_payment_summary(doc,spon,null,this.jlRoomsFactoryDB);
     }
     public  void get_rptView_customer_payment_summary(Document doc, int spon,String web,dbMgrInterface db) throws Exception{
     try {
     jlRoomsFactoryRpt.getOffice(doc,false,spon,"Customer Summary Report");
     } catch(Exception e){
     throw e;
     }
     CachedRowSet cr;
     String name = null,str="";
     int curr=0,last=0;
     double amt=0,total=0;
     float[] widths = {0.20f, 0.25f,0.10f,0.30f,0.15f};
     PdfPTable table = new PdfPTable(widths);
     table.setWidthPercentage(100); // percentage
     jlRoomsFactoryRpt.setRptHeaderCell(new String[] {"Last Activity", "Name","Desc","Booking", "Amount"}, table);
     try {
     PdfPCell cell1 = new PdfPCell(),
     cell2 = new PdfPCell(),
     cell3 = new PdfPCell();
     cell2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
     cell1.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
     cell1.setBackgroundColor(Color.lightGray);

     cell3.setPhrase(jlRoomsFactoryRpt.getRptFont("Total", true));
     cell3.setColspan(4);
     cell3.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
     cell3.setBackgroundColor(Color.lightGray);
     cr = rptView_customer_payment_summary(spon,web,db);
     while (cr.next()){
     curr = cr.getInt(2);
     if (last > 0 && curr != last){
     cell3.setPhrase(jlRoomsFactoryRpt.getRptFont(name+" Total", true));
     table.addCell(cell3);
     cell1.setPhrase(jlRoomsFactoryRpt.getRptFont(objMgr.getDollarFormat(amt)));
     table.addCell(cell1);
     amt = 0;
     }
     table.addCell(jlRoomsFactoryRpt.getRptFont(cr.getString(12)));
     table.addCell(jlRoomsFactoryRpt.getRptFont(cr.getString(5)));
     switch(cr.getInt(1)){
     case 3:
     str = "Payments";
     break;
     case 2:
     str =  "Fees";
     break;
     default:
     str = cr.getString(9);
     break;
     }
     table.addCell(jlRoomsFactoryRpt.getRptFont(str));
     table.addCell(jlRoomsFactoryRpt.getRptFont(cr.getString(10)));
     cell2.setPhrase(jlRoomsFactoryRpt.getRptFont(objMgr.getDollarFormat(cr.getDouble(6))));
     table.addCell(cell2);
     if (cr.getInt(4) == -6) {
     amt += cr.getDouble(6);
     total += cr.getDouble(6);
     }
     name = cr.getString(5);
     last = curr;
                
     }
     if (amt > 0){
     cell3.setPhrase(jlRoomsFactoryRpt.getRptFont(name+" Total", true));
     table.addCell(cell3);
     cell1.setPhrase(jlRoomsFactoryRpt.getRptFont(objMgr.getDollarFormat(amt)));
     table.addCell(cell1);
     amt = 0;
     }
     if (total != amt){
     cell3.setPhrase(jlRoomsFactoryRpt.getRptFont("Grand Total\n", true));
     cell3.setColspan(4);
     table.addCell(cell3);
     //table.addCell(jlRoomsFactoryRpt.getRptFont(jlRoomsFactory.getDollarFormat(total)));
     cell1.setPhrase(jlRoomsFactoryRpt.getRptFont(objMgr.getDollarFormat(total)));
     table.addCell(cell1);

     }
     doc.add(table);
     }catch(Exception e){
     Logger.getLogger(rptView_customer_payment_summary.class.getName()).log(Level.SEVERE, null, e);
     }
        
        
        
     }
     */
}
