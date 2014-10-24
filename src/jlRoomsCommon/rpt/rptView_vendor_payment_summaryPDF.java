/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsCommon.rpt;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import java.awt.Color;
import jlRoomsCommon.jlRoomsFactoryRpt;
import jlRoomsCommon.objMgr;
import jlRoomsDO.JlRoomsDataObjects;
import sun.jdbc.rowset.CachedRowSet;

/**
 *
 * @author lmeans
 */
public class rptView_vendor_payment_summaryPDF extends objMgr {
    public  void rptView_vendor_payment_summaryPDF(Document doc, CachedRowSet cr)throws Exception {
        jlRoomsFactoryRpt jlRoomsFactoryRpt =  new jlRoomsFactoryRpt();
        float[] widths = {0.20f, 0.20f,0.30f,0.20f,0.20f};
         PdfPTable table = new PdfPTable(widths);
         table.setWidthPercentage(100); // percentage
         PdfPCell cell = new PdfPCell();
        cell.setColspan(widths.length);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setPhrase(jlRoomsFactoryRpt.getRptFont("Vendor Payment Summary", true,14));
        table.addCell(cell);
        double tot = 0,amt=0;
        try {
            PdfPCell 
                    cellAmt = new PdfPCell(),
                    totCell = new PdfPCell(),
                    amtCell = new PdfPCell();
            totCell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            amtCell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            cellAmt.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            totCell.setBackgroundColor(Color.lightGray);
            amtCell.setBackgroundColor(Color.lightGray);

            totCell.setColspan(widths.length -1);
            jlRoomsFactoryRpt.setRptHeaderCell(new String[]{"1st", "Last", "Vendor", "Desc","Amount"}, table);

            //Date d = null;
            //Calendar cal = Calendar.getInstance();
            boolean ok = false;
            String vendor = "";
            int curr = -1,last = -1;
            while (cr.next()) {
                curr = cr.getInt(2);
                if (last != -1 && curr != last){
                    totCell.setPhrase(jlRoomsFactoryRpt.getRptFont(vendor+" Total"));
                    table.addCell(totCell);
                    amtCell.setPhrase(jlRoomsFactoryRpt.getRptFont(this.getDollarFormat(amt)));
                    table.addCell(amtCell);
                    amt= 0;
                }
                table.addCell(jlRoomsFactoryRpt.getRptFont((cr.getString(11))));
                table.addCell(jlRoomsFactoryRpt.getRptFont((cr.getString(12))));
                table.addCell(jlRoomsFactoryRpt.getRptFont((cr.getString(5))));
                table.addCell(jlRoomsFactoryRpt.getRptFont((cr.getString(7))));
                cellAmt.setPhrase(jlRoomsFactoryRpt.getRptFont(this.getDollarFormat(cr.getDouble(8))));
                table.addCell(cellAmt);
                vendor = cr.getString(5);
                
                amt+= cr.getDouble(8);
                tot+= cr.getDouble(8);
                last = curr;
            }
            if (last != -1) {
                totCell.setPhrase(jlRoomsFactoryRpt.getRptFont(vendor+" Total"));
                table.addCell(totCell);
                amtCell.setPhrase(jlRoomsFactoryRpt.getRptFont(this.getDollarFormat(amt)));
                table.addCell(amtCell);
            }
            
            
            
            totCell.setPhrase(jlRoomsFactoryRpt.getRptFont("Total"));
            table.addCell(totCell);
            amtCell.setPhrase(jlRoomsFactoryRpt.getRptFont(this.getDollarFormat(tot)));
            table.addCell(amtCell);
            (new JlRoomsDataObjects()).docPgBreak(doc,null);
            doc.add(table);
        } catch (Exception e){
            throw e;
        }
    }
}
