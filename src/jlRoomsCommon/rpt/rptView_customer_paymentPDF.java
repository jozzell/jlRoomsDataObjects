/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsCommon.rpt;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
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
public class rptView_customer_paymentPDF extends objMgr {
     public  void get_rptView_customer_payment(Document doc,CachedRowSet cr){
        
        jlRoomsFactoryRpt jlRoomsFactoryRpt =  new jlRoomsFactoryRpt();
        double amt=0,total=0;
        try {
            float[] widths = {0.17f, 0.23f,0.20f,0.20f,0.20f};
            PdfPTable table = new PdfPTable(widths);
            table.setWidthPercentage(100); // percentage
            
            PdfPCell cell = new PdfPCell();
        cell.setColspan(widths.length);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setPhrase(jlRoomsFactoryRpt.getRptFont("Customer Payment (Detail)", true,14));
        table.addCell(cell);
            
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
            
            
            jlRoomsFactoryRpt.setRptHeaderCell(new String[] {"Date", "Name","Desc","Booking", "Amount"}, table);
            
            //Date d=null;
            //Calendar cal = Calendar.getInstance();
            //int last = 0,curr=0;
            String last = null,curr=null;
            while (cr.next()){
                //curr = cal.setTime(cr.getDate(8));
                //cal.setTime(cr.getDate(8));
                curr  = cr.getString(12);
                //curr = cal.get(Calendar.DATE);
                if (last !=null && !curr.endsWith(last)){
                    cell3.setPhrase(jlRoomsFactoryRpt.getRptFont(last+" Total", true));
                    cell3.setColspan(4);
                    table.addCell(cell3);
                    cell1.setPhrase(jlRoomsFactoryRpt.getRptFont(this.getDollarFormat(amt)));
                    table.addCell(cell1);
                    //table.addCell(jlRoomsFactoryRpt.getRptFont(jlRoomsFactory.getDollarFormat(amt)));
                    amt = 0;
                }
                //d = cr.getDate(8);
                table.addCell(jlRoomsFactoryRpt.getRptFont(curr));
                table.addCell(jlRoomsFactoryRpt.getRptFont(cr.getString(5)));
                table.addCell(jlRoomsFactoryRpt.getRptFont( cr.getString(7) ));
                table.addCell(jlRoomsFactoryRpt.getRptFont((cr.getInt(4) == -5 ?  cr.getString(9)+" " :"")+cr.getString(10)));
                cell2.setPhrase(jlRoomsFactoryRpt.getRptFont(this.getDollarFormat(cr.getDouble(6))));
                table.addCell(cell2);
                //table.addCell(jlRoomsFactoryRpt.getRptFont(jlRoomsFactory.getDollarFormat(cr.getDouble(6))));
                if (cr.getInt(4) == -6) {
                    amt += cr.getDouble(6);
                    total += cr.getDouble(6);
                }
                last = curr;
            }
            if (amt != 0){
               cell3.setPhrase(jlRoomsFactoryRpt.getRptFont(curr+" Total", true));
               cell3.setColspan(4);
               table.addCell(cell3);
               //table.addCell(jlRoomsFactoryRpt.getRptFont(jlRoomsFactory.getDollarFormat(amt)));
               cell1.setPhrase(jlRoomsFactoryRpt.getRptFont(this.getDollarFormat(amt)));
               table.addCell(cell1);

            }
            if (total != amt){
               cell3.setPhrase(jlRoomsFactoryRpt.getRptFont("Grand Total", true));
               cell3.setColspan(4);
               table.addCell(cell3);
               //table.addCell(jlRoomsFactoryRpt.getRptFont(jlRoomsFactory.getDollarFormat(total)));
               cell1.setPhrase(jlRoomsFactoryRpt.getRptFont(this.getDollarFormat(total)));
               table.addCell(cell1);

            }
            (new JlRoomsDataObjects()).docPgBreak(doc,null);
            doc.add(table);
        } catch(Exception ex){
            Logger.getLogger(rptView_customer_payment.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
