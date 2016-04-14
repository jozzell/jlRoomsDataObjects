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
import java.util.Calendar;
import java.util.Date;
import jlRoomsCommon.jlRoomsFactoryRpt;
import jlRoomsCommon.objMgr;
import sun.jdbc.rowset.CachedRowSet;

/**
 *
 * @author lmeans
 */
public class rptView_payment_booked_type_summaryPDF extends objMgr {
     public  double get_rptview_payment_type(Document doc, CachedRowSet cr, boolean brk, boolean comment) throws Exception {
jlRoomsFactoryRpt jlRoomsFactoryRpt =  new jlRoomsFactoryRpt();

        double amt = 0, total = 0;
        try {
            PdfPCell cell1 = new PdfPCell(),
                    cell2 = new PdfPCell(),
                    cell3 = new PdfPCell();
            cell2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            cell1.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);

            cell3.setPhrase(jlRoomsFactoryRpt.getRptFont("Total", true));
            cell3.setColspan(comment ? 4 : 3);
            cell3.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            cell3.setBackgroundColor(BaseColor.LIGHT_GRAY);

            float[] widths; //= {0.20f, 0.20f,0.15f,0.35f,0.10f};
            if (comment) {
                widths = new float[]{0.20f, 0.20f, 0.15f, 0.35f, 0.10f};
            } else {
                widths = new float[]{0.30f, 0.30f, 0.25f, 0.15f};
            }
            PdfPTable table = new PdfPTable(widths);
            table.setWidthPercentage(100); // percentage
            if (comment) {
                jlRoomsFactoryRpt.setRptHeaderCell(new String[]{"Date", "Category", "Type", "Comment", "Amount"}, table);

            } else {
                jlRoomsFactoryRpt.setRptHeaderCell(new String[]{"Date", "Category", "Type", "Amount"}, table);
            }

            Date d = null;
            Calendar cal = Calendar.getInstance();
            int last = 0, curr = 0;
            while (cr.next()) {
                //curr = cal.setTime(cr.getDate(8));
                cal.setTime(cr.getDate(1));
                curr = cal.get(Calendar.DATE);
                if (brk && last > 0 && curr != last) {
                    cell3.setPhrase(jlRoomsFactoryRpt.getRptFont(this.getDateFormat(d) + " Total", true));
                    cell3.setColspan(4);
                    table.addCell(cell3);
                    cell1.setPhrase(jlRoomsFactoryRpt.getRptFont(this.getDollarFormat(amt)));
                    table.addCell(cell1);
                    //table.addCell(jlRoomsFactoryRpt.getRptFont(jlRoomsFactory.getDollarFormat(amt)));
                    amt = 0;
                }
                
                d = cr.getDate(1);
                table.addCell(jlRoomsFactoryRpt.getRptFont(this.getDateFormatFull(cr.getTimestamp(1))));
                table.addCell(jlRoomsFactoryRpt.getRptFont(cr.getString(3)));
                table.addCell(jlRoomsFactoryRpt.getRptFont(cr.getString(7)));
                if (comment) {
                    table.addCell(jlRoomsFactoryRpt.getRptFont(cr.getString(5)));
                }
                cell2.setPhrase(jlRoomsFactoryRpt.getRptFont(this.getDollarFormat(cr.getDouble(2))));
                table.addCell(cell2);
                //table.addCell(jlRoomsFactoryRpt.getRptFont(jlRoomsFactory.getDollarFormat(cr.getDouble(6))));

                amt += cr.getDouble(2);
                total += cr.getDouble(2);

                last = curr;
            }
            if (brk && total != 0) {
                cell3.setPhrase(jlRoomsFactoryRpt.getRptFont(this.getDateFormat(cal.getTime()) + " Total", true));
                cell3.setColspan(4);
                table.addCell(cell3);
                //table.addCell(jlRoomsFactoryRpt.getRptFont(jlRoomsFactory.getDollarFormat(amt)));
                cell1.setPhrase(jlRoomsFactoryRpt.getRptFont(this.getDollarFormat(amt)));
                table.addCell(cell1);

            }

            cell3.setPhrase(jlRoomsFactoryRpt.getRptFont("Grand Total", true));
            cell3.setColspan(comment ? 4 : 3);
            table.addCell(cell3);
            //table.addCell(jlRoomsFactoryRpt.getRptFont(jlRoomsFactory.getDollarFormat(total)));
            cell1.setPhrase(jlRoomsFactoryRpt.getRptFont(this.getDollarFormat(total)));
            table.addCell(cell1);


            doc.add(table);
        } catch (Exception ex) {
            throw ex;
        }
        return total;

    }

}
