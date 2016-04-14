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

import jlRoomsCommon.jlRoomsFactoryRpt;
import jlRoomsCommon.objMgr;
import jlRoomsCommon.JlRoomsDataObjects;
import sun.jdbc.rowset.CachedRowSet;

/**
 *
 * @author lmeans
 */
public class rptView_vendor_paymentPdf extends objMgr {
     public  void get_rptView_Vendor_payment(Document doc, CachedRowSet cr)throws Exception {
         get_rptView_Vendor_payment(doc,cr,1);
     }
    private  void get_rptView_Vendor_payment(Document doc, CachedRowSet cr, int type)throws Exception {
        jlRoomsFactoryRpt jlRoomsFactoryRpt =  new jlRoomsFactoryRpt();
         float[] widths = {0.25f, 0.40f, 0.2f, 0.15f};
         PdfPTable table = new PdfPTable(widths);
         table.setWidthPercentage(100); // percentage
            
        PdfPCell cell = new PdfPCell();
        cell.setColspan(4);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setPhrase(jlRoomsFactoryRpt.getRptFont("Vendor Payment (Detail)", true,14));
        table.addCell(cell);
        
        double amt = 0, total = 0;
        try {
            PdfPCell cell1 = new PdfPCell(),
                    cell2 = new PdfPCell(),
                    cell3 = new PdfPCell();
            cell2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            cell1.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);

            cell3.setPhrase(jlRoomsFactoryRpt.getRptFont("Total", true));
            cell3.setColspan(3);
            cell3.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            cell3.setBackgroundColor(BaseColor.LIGHT_GRAY);

           
            jlRoomsFactoryRpt.setRptHeaderCell(new String[]{"Date", "Vendor", "Type", "Amount"}, table);

            //Date d = null;
            //Calendar cal = Calendar.getInstance();
            String last = null, curr = null;
            String name = "", str = "";
            while (cr.next()) {
                //curr = cal.setTime(cr.getDate(8));
                if (type == 0) {
                    
                    curr = cr.getString(12);
                } else if (type == 1) {
                    curr = ""+cr.getInt(2);
                }
                if (last != null && !curr.endsWith(last)) {

                    if (type == 0) {
                        cell3.setPhrase(jlRoomsFactoryRpt.getRptFont(last + " Total", true));
                    } else if (type == 1) {
                        cell3.setPhrase(jlRoomsFactoryRpt.getRptFont(name + " Total", true));
                    }



                    table.addCell(cell3);
                    cell1.setPhrase(jlRoomsFactoryRpt.getRptFont(this.getDollarFormat(amt)));
                    table.addCell(cell1);

                    amt = 0;
                }


                table.addCell(jlRoomsFactoryRpt.getRptFont((cr.getString(12))));
                table.addCell(jlRoomsFactoryRpt.getRptFont(cr.getString(5)));

                if (type == 0) {
                    
                    table.addCell(jlRoomsFactoryRpt.getRptFont(cr.getString(7)));
                } else if (type == 1) {
                    name = cr.getString(5);
                    switch (cr.getInt(1)) {
                        case 3:
                            str = "Payments";
                            break;
                        case 2:
                            str = "Fees";
                            break;
                        default:
                            str = cr.getString(7);
                            break;
                    }
                    table.addCell(jlRoomsFactoryRpt.getRptFont(str));
                }


                //table.addCell(jlRoomsFactoryRpt.getRptFont(cr.getString(6) ));

                cell2.setPhrase(jlRoomsFactoryRpt.getRptFont(this.getDollarFormat(cr.getDouble(8))));
                table.addCell(cell2);
                //table.addCell(jlRoomsFactoryRpt.getRptFont(jlRoomsFactory.getDollarFormat(cr.getDouble(6))));
                if (cr.getInt(4) == -6) {
                    amt += cr.getDouble(8);
                    total += cr.getDouble(8);
                }
                last = curr;
            }
            if (amt != 0) {
                if (type == 0) {
                    cell3.setPhrase(jlRoomsFactoryRpt.getRptFont(curr + " Total", true));
                } else if (type == 1) {
                    cell3.setPhrase(jlRoomsFactoryRpt.getRptFont(name + " Total", true));
                }

                table.addCell(cell3);

                cell1.setPhrase(jlRoomsFactoryRpt.getRptFont(this.getDollarFormat(amt)));
                table.addCell(cell1);

            }
            if (total != amt) {
                cell3.setPhrase(jlRoomsFactoryRpt.getRptFont("Grand Total", true));
                //cell3.setColspan(4);
                table.addCell(cell3);

                cell1.setPhrase(jlRoomsFactoryRpt.getRptFont(this.getDollarFormat(total)));
                table.addCell(cell1);

            }
            (new JlRoomsDataObjects()).docPgBreak(doc,null);
            doc.add(table);
        } catch (Exception ex) {
            throw ex;
        }

    }
}
