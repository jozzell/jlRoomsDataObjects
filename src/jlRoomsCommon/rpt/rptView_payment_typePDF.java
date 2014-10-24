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
public class rptView_payment_typePDF extends objMgr  {
     public  void rptView_payment_typePDF_Summary(Document doc, CachedRowSet cr) throws Exception {
        rptView_payment_typePDF(doc,cr,true);
    }
    public  void rptView_payment_typePDF(Document doc, CachedRowSet cr) throws Exception {
        rptView_payment_typePDF(doc,cr,false);
    }
    public  void rptView_payment_typePDF(Document doc, CachedRowSet cr,boolean summeary) throws Exception {
        jlRoomsFactoryRpt jlRoomsFactoryRpt =  new jlRoomsFactoryRpt();
        float[] widths = new float[]{0.20f, 0.20f, 0.15f, 0.30f, 0.15f};
        double amt = 0, tot = 0,amt2=0;
        PdfPTable table = new PdfPTable(widths);
        
        PdfPCell  cell = new PdfPCell();
        cell.setColspan(5);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setPhrase(jlRoomsFactoryRpt.getRptFont("Payment Category/Method Report",true,14));
        table.addCell(cell);
        
        table.setWidthPercentage(100); // percentage
        jlRoomsFactoryRpt.setRptHeaderCell(new String[]{"Date", "Category", "Method", "Name/Vendor", "Amt"}, table);
        String last = null, curr = null;
        
       
        PdfPCell 
                cell0 = new PdfPCell(),
                cell1 = new PdfPCell(),
                cell3 = new PdfPCell();
        cell3.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell1.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell1.setColspan(4);
        cell1.setBackgroundColor(Color.lightGray);
        cell0.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell0.setBackgroundColor(Color.lightGray);
        String str,type=null;
        int currType=-1,lastType=-1;
        while (cr.next()) {
            if (summeary){
                curr = cr.getString(7);
                
            } else {
                curr = cr.getString(3)+" ("+cr.getString(7)+")";
            }
            currType = cr.getInt(8);
            
            if (last != null && !curr.endsWith(last)) {
                cell1.setPhrase(jlRoomsFactoryRpt.getRptFont(last + " Total", true));
                table.addCell(cell1);
                cell0.setPhrase(jlRoomsFactoryRpt.getRptFont(this.getDollarFormat(amt)));
                table.addCell(cell0);
                amt = 0;
                if (!summeary && currType != lastType){
                    cell1.setPhrase(jlRoomsFactoryRpt.getRptFont(type + " Total", true));
                    table.addCell(cell1);
                    cell0.setPhrase(jlRoomsFactoryRpt.getRptFont(this.getDollarFormat(amt2)));
                    table.addCell(cell0);
                    amt2 = 0;
                }
            }
            type = cr.getString(7);
            table.addCell(jlRoomsFactoryRpt.getRptFont(cr.getString(10)));
            table.addCell(jlRoomsFactoryRpt.getRptFont(cr.getString(3)));
            table.addCell(jlRoomsFactoryRpt.getRptFont(cr.getString(7)));
            str = cr.getString(11);
            if (str == null) {
                str = cr.getString(12);
            }
            if (str == null) {
                str = "";
            }
            amt2+= cr.getDouble(2);
            amt += cr.getDouble(2);
            tot += cr.getDouble(2);
            table.addCell(jlRoomsFactoryRpt.getRptFont(str));
            cell3.setPhrase(jlRoomsFactoryRpt.getRptFont(this.getDollarFormat(cr.getDouble(2))));
            table.addCell(cell3);
            last = curr;
            lastType = currType;
        }
        if (last != null) {
            cell1.setPhrase(jlRoomsFactoryRpt.getRptFont(last + " Total", true));
            table.addCell(cell1);
            cell0.setPhrase(jlRoomsFactoryRpt.getRptFont(this.getDollarFormat(amt)));
            table.addCell(cell0);
            if (!summeary ){
                cell1.setPhrase(jlRoomsFactoryRpt.getRptFont(type + " Total", true));
                table.addCell(cell1);
                cell0.setPhrase(jlRoomsFactoryRpt.getRptFont(this.getDollarFormat(amt2)));
            table.addCell(cell0);  
            }
            
            cell1.setPhrase(jlRoomsFactoryRpt.getRptFont("Grand Total", true));
            table.addCell(cell1);
            cell0.setPhrase(jlRoomsFactoryRpt.getRptFont(this.getDollarFormat(tot)));
            table.addCell(cell0);
            
            (new JlRoomsDataObjects()).docPgBreak(doc,null);
            doc.add(table);

        }

    }
}
