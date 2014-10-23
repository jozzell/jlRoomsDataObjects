/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsCommon.rpt;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import java.awt.Color;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jlRoomsCommon.jlRoomsFactoryRpt;
import jlRoomsCommon.objMgr;
import jlRoomsDO.JlRoomsDataObjects;
import sun.jdbc.rowset.CachedRowSet;

/**
 *
 * @author lmeans
 */
public class rptView_payment_booked_typePDF extends objMgr {
        public  PdfPTable rptView_payment_booked_type() {
        float[] widths = {0.10f,  0.20f, .20f,.40f, 0.10f};
        PdfPTable table = new PdfPTable(widths);
        table.setWidthPercentage(100); // percentage
        jlRoomsFactoryRpt.setRptHeaderCell(new String[]{"Date", "Category",  "Method", "Name/Vendor", "Total"}, table);
        return table;
    }
        
        
    public  void rptView_payment_booked_type(Document doc, CachedRowSet cr){
            try {
                doc.add(rptView_payment_booked_type());
                 
                PdfPCell 
                    date = new PdfPCell(),
                    cat = new PdfPCell(),
                    meth = new PdfPCell(),
                    name = new PdfPCell(),
                    total = new PdfPCell();
                while (cr.next()){
                    
                }
            } catch (DocumentException ex) {
                Logger.getLogger(rptView_payment_booked_typePDF.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(rptView_payment_booked_typePDF.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    public  double rptviewPdfEvent(Document doc, CachedRowSet cr) throws Exception {
        if (true){
            return 0;
        }
        double gTot=0.0;
        try {
            //CachedRowSet cr = rptView_Event_vendor.rptView_Event_sponsor(spon,null,null);
            double amt[] = {0.0, 0.0},
                    cnt[] = {0.0, 0.0},
                    cCnt=0.0,ccHold=0,sub=0;
            int days = 0;
            String name = null, room = "";
            int cLookup = -1, lLookup = -1,
                    cVen=-1,lVen=-1;
            PdfPTable table = rptView_payment_booked_type();
            PdfPCell cntr = new PdfPCell(),
                    rght = new PdfPCell(),
                    head = new PdfPCell(),
                    brk = new PdfPCell();
            brk.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            brk.setColspan(7);
            //brk.setBackgroundColor(Color.lightGray);

            cntr.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            rght.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            head.setBackgroundColor(Color.lightGray);
            head.setColspan(8);
            
            while (cr.next()) {
                cLookup = cr.getInt(4);
                cVen = (int)cr.getDouble(2);
                if (cVen != lVen) {
                    if (name != null){
                        brk.setPhrase(jlRoomsFactoryRpt.getRptFont(name+" Total"));
                        table.addCell(brk);
                        rght.setPhrase(jlRoomsFactoryRpt.getRptFont(this.getDollarFormat(sub)));
                        table.addCell(rght);
                        sub = 0;
                    }
                    head.setPhrase(jlRoomsFactoryRpt.getRptFont(cr.getString(10)));
                    table.addCell(head);
                }
                if (lLookup > 0 && cLookup != lLookup) {
                    table.addCell(jlRoomsFactoryRpt.getRptFont(room));

                    cntr.setPhrase(jlRoomsFactoryRpt.getRptFont("" + days));
                    table.addCell(cntr);

                    cntr.setPhrase(jlRoomsFactoryRpt.getRptFont("" + cnt[0]));
                    table.addCell(cntr);

                    cntr.setPhrase(jlRoomsFactoryRpt.getRptFont("" + obj.reusableObj.round(cnt[1], 1)));
                    table.addCell(cntr);

                    cntr.setPhrase(jlRoomsFactoryRpt.getRptFont("" + obj.reusableObj.round(ccHold, 1)));
                    table.addCell(cntr);

                    rght.setPhrase(jlRoomsFactoryRpt.getRptFont(this.getDollarFormat(amt[0])));
                    table.addCell(rght);

                    cntr.setPhrase(jlRoomsFactoryRpt.getRptFont("" + obj.reusableObj.round(cCnt, 1)));
                    table.addCell(cntr);

                    rght.setPhrase(jlRoomsFactoryRpt.getRptFont(this.getDollarFormat(amt[1])));
                    table.addCell(rght);

                    amt = new double[]{0.0, 0.0};
                    cnt = new double[]{0.0, 0.0};
                    days = 0;
                    cCnt = 0.0;
                    ccHold = 0.0;
                }
                lLookup = cLookup;
                lVen = cVen;
                
                cnt[cr.getInt(1) - 1] = cr.getDouble(7);
                if (cr.getInt(1) == 2) {
                    if (cr.getInt(13) == -5){
                        ccHold = cr.getDouble(12);
                    } else {
                        amt[cr.getInt(1) - 1] = cr.getDouble(5);
                        days = cr.getInt(6);
                        cCnt = cr.getDouble(12);
                        gTot += cr.getDouble(5);
                        sub += cr.getDouble(5);
                    }
                } else {

                }
                name = cr.getString(10);
                room = cr.getString(11);

            }

            //table.addCell(jlRoomsFactoryRpt.getRptFont(name));
             table.addCell(jlRoomsFactoryRpt.getRptFont(room));

                    cntr.setPhrase(jlRoomsFactoryRpt.getRptFont("" + days));
                    table.addCell(cntr);

                    cntr.setPhrase(jlRoomsFactoryRpt.getRptFont("" + cnt[0]));
                    table.addCell(cntr);

                    cntr.setPhrase(jlRoomsFactoryRpt.getRptFont("" + obj.reusableObj.round(cnt[1], 1)));
                    table.addCell(cntr);

                    cntr.setPhrase(jlRoomsFactoryRpt.getRptFont("" + obj.reusableObj.round(ccHold, 1)));
                    table.addCell(cntr);

                    rght.setPhrase(jlRoomsFactoryRpt.getRptFont(this.getDollarFormat(amt[0])));
                    table.addCell(rght);

                     cntr.setPhrase(jlRoomsFactoryRpt.getRptFont("" + obj.reusableObj.round(cCnt, 1)));
                    table.addCell(cntr);

                    rght.setPhrase(jlRoomsFactoryRpt.getRptFont(this.getDollarFormat(amt[1])));
                    table.addCell(rght);

                    if (name != null){
                        brk.setPhrase(jlRoomsFactoryRpt.getRptFont(name+" Total"));
                        table.addCell(brk);
                        rght.setPhrase(jlRoomsFactoryRpt.getRptFont(this.getDollarFormat(sub)));
                        table.addCell(rght);
                        //sub = 0;
                    }
                    
            //amt = new double[]{0.0, 0.0};
            //cnt = new double[]{0.0, 0.0};
            //days = 0;
            head.setColspan(6);

             head.setPhrase(jlRoomsFactoryRpt.getRptFont("Grand Total"));
             head.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
             head.setColspan(7);
             table.addCell(head);
             
             rght.setPhrase(jlRoomsFactoryRpt.getRptFont(this.getDollarFormat(gTot)));
             rght.setBackgroundColor(Color.lightGray);
             table.addCell(rght);
            JlRoomsDataObjects.docPgBreak(doc,"Payment (Booked)");
            doc.add(table);
        } catch (Exception e) {
            throw e;
        }
        return gTot;
    }
}
