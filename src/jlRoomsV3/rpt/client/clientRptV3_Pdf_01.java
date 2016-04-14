/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsV3.rpt.client;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.awt.Color;
import java.util.List;
import jlRoomsCommon._beans.rptBeanColumes;
import jlRoomsCommon._beans.rptBeanColumesAmtList;
import jlRoomsCommon.jlRoomsFactoryRpt;
import jlRoomsCommon.objMgr;

/**
 *
 * @author lmeans
 */
public class clientRptV3_Pdf_01 extends objMgr {

    
    public void getRoomateRpt(String header, List<rptBeanColumesAmtList> list,Document doc){
        brk(doc, header);
        brk(doc);
        for(rptBeanColumesAmtList rpt : list){
            getRoomateRpt(rpt,doc);
        }
    }
    public void getRoomateRpt(rptBeanColumesAmtList rpt,Document doc){
        List<rptBeanColumes> list = rpt.getRptList();
        jlRoomsFactoryRpt jfac = new jlRoomsFactoryRpt();

        float[] widths = {0.30f, 0.40f, 0.10f, 0.10f, 0.10f};
        PdfPTable table = new PdfPTable(widths);
        table.setWidthPercentage(100); // percentage
        //PdfPCell cell = new PdfPCell();
        //cell.setColspan(widths.length);
        //cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        jfac.setRptHeaderCell(new String[]{"Name", "Address", "E-Mail", "Home #", "Work #"}, table);
        PdfPCell 
                head = new PdfPCell(),
                disp = new PdfPCell();
        head.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        head.setColspan(5);
        head.setBackgroundColor(BaseColor.LIGHT_GRAY);
        head.setPhrase(jfac.getRptFont(rpt.getHeader(),true));
        table.addCell(head);
        
        for (rptBeanColumes c : list) {
            disp.setPhrase(jfac.getRptFont(c.getCol06()));
            table.addCell(disp);
            disp.setPhrase(jfac.getRptFont(c.getCol07()));
            table.addCell(disp);
            disp.setPhrase(jfac.getRptFont(c.getCol08()));
            table.addCell(disp);
            disp.setPhrase(jfac.getRptFont(c.getCol09()));
            table.addCell(disp);
            disp.setPhrase(jfac.getRptFont(c.getCol10()));
            table.addCell(disp);
        }
        try {
            doc.add(table);
        } catch (DocumentException ex) {
            ex.printStackTrace();
        }
        
    }
    // -----------------------------------------------------------------------------------------------
    public void getBookingRpt(String header, double total, List<rptBeanColumes> list, Document doc) {
        brk(doc, header);
        brk(doc);
        jlRoomsFactoryRpt jfac = new jlRoomsFactoryRpt();

        float[] widths = {0.10f, 0.25f, 0.20f, 0.10f, 0.05f, 0.05f, 0.05f, 0.10f, 0.10f};
        PdfPTable table = new PdfPTable(widths);
        table.setWidthPercentage(100); // percentage
        //PdfPCell cell = new PdfPCell();
        //cell.setColspan(widths.length);
        //cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        
        PdfPCell disp = new PdfPCell(),
                amt = new PdfPCell();
        amt.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        jfac.setRptHeaderCell(new String[]{"Status", "Client", "Vendor", "Booking", "Days", "Split", "Cnt", "Cost", "Total"}, table);
        for (rptBeanColumes c : list) {
            disp.setPhrase(jfac.getRptFont(c.getCol03()));
            table.addCell(disp);
            disp.setPhrase(jfac.getRptFont(c.getCol01()));
            table.addCell(disp);
            disp.setPhrase(jfac.getRptFont(c.getCol04()));
            table.addCell(disp);
            disp.setPhrase(jfac.getRptFont(c.getCol02()));
            table.addCell(disp);
            amt.setPhrase(jfac.getRptFont(c.getCol10()));
            table.addCell(amt);
            amt.setPhrase(jfac.getRptFont(c.getCol18()));
            table.addCell(amt);
            amt.setPhrase(jfac.getRptFont(c.getCol11()));
            table.addCell(amt);
            amt.setPhrase(jfac.getRptFont(c.getCol09()));
            table.addCell(amt);
            amt.setPhrase(jfac.getRptFont(c.getCol08()));
            table.addCell(amt);

        }
        disp.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        disp.setColspan(8);
        disp.setPhrase(jfac.getRptFont(header + " Total"));
        table.addCell(disp);
        
        amt.setPhrase(jfac.getRptFont(this.getDollarFormat(total)));
        table.addCell(amt);
        try {
            doc.add(table);
        } catch (DocumentException ex) {
            ex.printStackTrace();
        }

    }

    public void get1StNight(String header, double cnt, double total, List<rptBeanColumes> list, Document doc) {
        brk(doc, header);
        brk(doc);
        jlRoomsFactoryRpt jfac = new jlRoomsFactoryRpt();

        float[] widths = {0.10f, 0.17f, 0.14f, 0.17f, 0.25f, 0.10f, 0.07f, 0.10f};
        PdfPTable table = new PdfPTable(widths);
        table.setWidthPercentage(100); // percentage
        //PdfPCell cell = new PdfPCell();
        //cell.setColspan(widths.length);
        //cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        PdfPCell disp = new PdfPCell(),
                amt = new PdfPCell();
        amt.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        jfac.setRptHeaderCell(new String[]{"Status", "Vendor", "Booking", "Client", "Contact", "Cost", "Cnt", "Total"}, table);
        for (rptBeanColumes c : list) {
            disp.setPhrase(jfac.getRptFont(c.getCol01()));
            table.addCell(disp);
            disp.setPhrase(jfac.getRptFont(c.getCol02()));
            table.addCell(disp);
            disp.setPhrase(jfac.getRptFont(c.getCol03()));
            table.addCell(disp);
            disp.setPhrase(jfac.getRptFont(c.getCol04()));
            table.addCell(disp);
            disp.setPhrase(jfac.getRptFont(c.getCol06() + " " + c.getCol07()));
            table.addCell(disp);
            amt.setPhrase(jfac.getRptFont(c.getCol05()));
            table.addCell(amt);
            String str = "";
            if (c.getCol10() != null && c.getCol10().trim().length() > 0) {
                str = " (" + c.getCol10() + ")";
            }

            amt.setPhrase(jfac.getRptFont(c.getCol08() + str));
            table.addCell(amt);
            amt.setPhrase(jfac.getRptFont(c.getCol11()));
            table.addCell(amt);

        }
        disp.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        disp.setColspan(6);
        disp.setPhrase(jfac.getRptFont(header + " Total"));
        table.addCell(disp);
        amt.setPhrase(jfac.getRptFont("" + cnt));
        table.addCell(amt);
        amt.setPhrase(jfac.getRptFont(this.getDollarFormat(total)));
        table.addCell(amt);
        try {
            doc.add(table);
        } catch (DocumentException ex) {
            ex.printStackTrace();
        }
    }
}
