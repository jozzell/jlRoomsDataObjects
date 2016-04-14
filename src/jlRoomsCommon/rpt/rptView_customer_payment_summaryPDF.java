/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsCommon.rpt;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import jlRoomsCommon.jlRoomsFactoryRpt;
import jlRoomsCommon.objMgr;
import jlRoomsCommon.JlRoomsDataObjects;
import sun.jdbc.rowset.CachedRowSet;

/**
 *
 * @author lmeans
 */
public class rptView_customer_payment_summaryPDF extends objMgr {
     public  void get_rptView_customer_payment_summary(Document doc, CachedRowSet cr) throws Exception {
         jlRoomsFactoryRpt jlRoomsFactoryRpt =  new jlRoomsFactoryRpt();
        if (cr == null) return;
        String name = null, str = "";
        int curr = 0, last = 0;
        double amt = 0, total = 0;
        float[] widths = {0.20f, 0.25f, 0.10f, 0.30f, 0.15f};
        PdfPTable table = new PdfPTable(widths);
        table.setWidthPercentage(100); // percentage
        
        PdfPCell cell = new PdfPCell();
        cell.setColspan(widths.length);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setPhrase(jlRoomsFactoryRpt.getRptFont("Customer Payment (Summary)", true,14));
        table.addCell(cell);
        
        
        
        
        jlRoomsFactoryRpt.setRptHeaderCell(new String[]{"Last Activity", "Name", "Desc", "Booking", "Amount"}, table);
        try {
            PdfPCell cell1 = new PdfPCell(),
                    cell2 = new PdfPCell(),
                    cell3 = new PdfPCell();
            cell2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            cell1.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);

            cell3.setPhrase(jlRoomsFactoryRpt.getRptFont("Total", true));
            cell3.setColspan(4);
            cell3.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            cell3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            //cr = rptView_customer_payment_summary(spon,web,db);
            while (cr.next()) {
                curr = cr.getInt(2);
                if (last > 0 && curr != last) {
                    cell3.setPhrase(jlRoomsFactoryRpt.getRptFont(name + " Total", true));
                    table.addCell(cell3);
                    cell1.setPhrase(jlRoomsFactoryRpt.getRptFont(this.getDollarFormat(amt)));
                    table.addCell(cell1);
                    amt = 0;
                }
                table.addCell(jlRoomsFactoryRpt.getRptFont(cr.getString(12)));
                table.addCell(jlRoomsFactoryRpt.getRptFont(cr.getString(5)));
                switch (cr.getInt(1)) {
                    case 3:
                        str = "Payments";
                        break;
                    case 2:
                        str = "Fees";
                        break;
                    default:
                        str = cr.getString(9);
                        break;
                }
                table.addCell(jlRoomsFactoryRpt.getRptFont(str));
                table.addCell(jlRoomsFactoryRpt.getRptFont(cr.getString(10)));
                cell2.setPhrase(jlRoomsFactoryRpt.getRptFont(this.getDollarFormat(cr.getDouble(6))));
                table.addCell(cell2);
                if (cr.getInt(4) == -6) {
                    amt += cr.getDouble(6);
                    total += cr.getDouble(6);
                }
                name = cr.getString(5);
                last = curr;

            }
            if (amt > 0) {
                cell3.setPhrase(jlRoomsFactoryRpt.getRptFont(name + " Total", true));
                table.addCell(cell3);
                cell1.setPhrase(jlRoomsFactoryRpt.getRptFont(this.getDollarFormat(amt)));
                table.addCell(cell1);
                amt = 0;
            }
            if (total != amt) {
                cell3.setPhrase(jlRoomsFactoryRpt.getRptFont("Grand Total\n", true));
                cell3.setColspan(4);
                table.addCell(cell3);
                //table.addCell(jlRoomsFactoryRpt.getRptFont(jlRoomsFactory.getDollarFormat(total)));
                cell1.setPhrase(jlRoomsFactoryRpt.getRptFont(this.getDollarFormat(total)));
                table.addCell(cell1);

            }
            
                (new JlRoomsDataObjects()).docPgBreak(doc,null);
                doc.add(table);
           
        } catch (Exception e) {
            Logger.getLogger(rptView_customer_payment_summary.class.getName()).log(Level.SEVERE, null, e);
        }



    }
}
