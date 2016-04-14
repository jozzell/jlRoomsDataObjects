/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsV3.rpt.payment;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.awt.Color;
import java.io.Serializable;
import java.util.List;
import jlRoomsCommon._beans.rptBeanColumes;
import jlRoomsCommon._beans.rptBeanColumesAmtList;
import jlRoomsCommon.jlRoomsFactoryRpt;
import jlRoomsCommon.objMgr;

/**
 *
 * @author Lloyd
 */
public class feeV3_PDF extends objMgr implements Serializable {

    double amt;

    public void getPayment(List<rptBeanColumesAmtList> rpt, Document doc) throws DocumentException {

        for (rptBeanColumesAmtList list : rpt) {
            getPayment(list, doc);
        }
        if (rpt.size() > 0) {
            jlRoomsFactoryRpt jfac = new jlRoomsFactoryRpt();
            float[] widths = {0.20f, 0.40f, 0.30f, 0.10f};
            PdfPTable table = new PdfPTable(widths);
            table.setWidthPercentage(100); // percentage
            PdfPCell cell1 = new PdfPCell(),
                    cell3 = new PdfPCell();

            cell1.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);

            cell3.setPhrase(jfac.getRptFont("Total", true));
            cell3.setColspan(3);
            cell3.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            cell3.setBackgroundColor(BaseColor.LIGHT_GRAY);

            cell3.setPhrase(jfac.getRptFont(" Grand Total", true));
            cell3.setColspan(3);
            table.addCell(cell3);
            //table.addCell(jlRoomsFactoryRpt.getRptFont(jlRoomsFactory.getDollarFormat(amt)));
            cell1.setPhrase(jfac.getRptFont(getDollarFormat(amt)));
            table.addCell(cell1);
            doc.add(table);
        }

    }

    public void getPayment(rptBeanColumesAmtList rpt, Document doc) throws DocumentException {
        amt += rpt.getAmt();
        jlRoomsFactoryRpt jfac = new jlRoomsFactoryRpt();
        List<rptBeanColumes> ary = rpt.getRptList();
        float[] widths = {0.20f, 0.40f, 0.30f, 0.10f};
        PdfPTable table = new PdfPTable(widths);
        table.setWidthPercentage(100); // percentage
        PdfPCell cell = new PdfPCell();
        cell.setColspan(widths.length);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);

        jfac.setRptHeaderCell(new String[]{"Date", "Description", "Booking/Method", "Amount"}, table);
        cell.setPhrase(jfac.getRptFont(rpt.getHeader(), true, 14));
        table.addCell(cell);

        PdfPCell cell1 = new PdfPCell(),
                cell2 = new PdfPCell(),
                cell3 = new PdfPCell();
        cell2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell1.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);

        cell3.setPhrase(jfac.getRptFont("Total", true));
        cell3.setColspan(3);
        cell3.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        cell3.setBackgroundColor(BaseColor.LIGHT_GRAY);
        for (rptBeanColumes rptBeanColumes : ary) {
            table.addCell(jfac.getRptFont(rptBeanColumes.getCol02()));
            table.addCell(jfac.getRptFont(rptBeanColumes.getCol03()));
            table.addCell(jfac.getRptFont(rptBeanColumes.getCol05()));
            cell2.setPhrase(jfac.getRptFont(rptBeanColumes.getCol01()));
            table.addCell(cell2);
        }
        if (rpt.getRptList().size() > 0) {
            cell3.setPhrase(jfac.getRptFont(rpt.getHeader() + " Total", true));
            cell3.setColspan(3);
            table.addCell(cell3);
            //table.addCell(jlRoomsFactoryRpt.getRptFont(jlRoomsFactory.getDollarFormat(amt)));
            cell1.setPhrase(jfac.getRptFont(rpt.getAmtStr()));
            table.addCell(cell1);
            doc.add(table);
        }
    }
}
