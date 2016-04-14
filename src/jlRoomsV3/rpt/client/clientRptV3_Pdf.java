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
import java.io.Serializable;
import java.util.List;
import jlRoomsCommon._beans.rptItineraryBean;
import jlRoomsCommon.jlRoomsFactoryRpt;
import jlRoomsCommon.objMgr;

/**
 *
 * @author Lloyd
 */
public class clientRptV3_Pdf extends objMgr implements Serializable{
    
    // =========================================================
    public void genTicket(String header,List<rptItineraryBean> list, Document doc) {
        brk(doc,header);
        brk(doc);
        jlRoomsFactoryRpt jfac = new jlRoomsFactoryRpt();
        float[] widths = { 0.40f, 0.10f, 0.10f, 0.10f, 0.15f, 0.20f};
        PdfPTable table = new PdfPTable(widths);
        table.setWidthPercentage(100); // percentage
        jfac.setRptHeaderCell(new String[]{ "Desc", "Days", "Count", "Cost $", "Total $", "Starting"}, table);
        PdfPCell disp = new PdfPCell(),
                date = new PdfPCell(),
                amt = new PdfPCell();
        amt.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        date.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        for (rptItineraryBean r : list) {
            //disp.setPhrase(jfac.getRptFont(r.getSysDesc()));
            //table.addCell(disp);
            
            disp.setPhrase(jfac.getRptFont(r.getSysDesc()+" "+r.getVendorName() + " " + r.getRoomDesc() + " " + r.getTicketInfo()));
            table.addCell(disp);
            amt.setPhrase(jfac.getRptFont("" + r.getDays()));
            table.addCell(amt);
            amt.setPhrase(jfac.getRptFont(r.getCustRmCnt() + " " + r.split()));
            table.addCell(amt);
            amt.setPhrase(jfac.getRptFont("" + r.getRmCost()));
            table.addCell(amt);
            amt.setPhrase(jfac.getRptFont("" + r.getTotalCost()));
            table.addCell(amt);
            date.setPhrase(jfac.getRptFont(r.getEffDate()));
            table.addCell(date);
            //disp.setPhrase(jfac.getRptFont(r.getBalancedDueDate()));
            //table.addCell(disp);
            try {
                doc.add(table);
            } catch (DocumentException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    // ================================================================
    public void genAir(List<rptItineraryBean> list,String type,Document doc){
         brk(doc,type);
        jlRoomsFactoryRpt jfac = new jlRoomsFactoryRpt();
        for(rptItineraryBean r: list){
            genAir(r,jfac,doc);
        }
    }
    private void genAir(rptItineraryBean r, jlRoomsFactoryRpt jfac,Document doc) {
        brk(doc);
        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100); // percentage
        PdfPCell disp = new PdfPCell(),
                date = new PdfPCell(),
                head = new PdfPCell(),
                cell = new PdfPCell();
        date.setColspan(3);
        
        disp.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        disp.setBackgroundColor(BaseColor.LIGHT_GRAY);
        
        head.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        head.setBackgroundColor(BaseColor.LIGHT_GRAY);
        head.setColspan(8);
        head.setPhrase(jfac.getRptFont(r.getVendorName()+" "+r.getVendorTypeDesc(),true));
        table.addCell(head);
        //--------------------------------------------------------------------------------------------
        disp.setPhrase(jfac.getRptFont(r.getVendorObjTypesENum().getSubRmDesc(), true));
        table.addCell(disp);
        cell.setPhrase(jfac.getRptFont(r.getFltNumber()));
        table.addCell(cell);
        //---------------------------------------------
        disp.setPhrase(jfac.getRptFont("Status", true));
        table.addCell(disp);
        cell.setPhrase(jfac.getRptFont(r.getSysDesc()));
        table.addCell(cell);
        //---------------------------------------------
        disp.setPhrase(jfac.getRptFont(r.getRptDesc(), true));
        table.addCell(disp);
        date.setPhrase(jfac.getRptFont(r.getRoomDesc()));
        table.addCell(date);
        //-----------------    Next Row ----------------------------
        disp.setPhrase(jfac.getRptFont("Count", true));
        table.addCell(disp);
        cell.setPhrase(jfac.getRptFont(r.getCustRmCnt()+" "+r.split()));
        table.addCell(cell);
        //---------------------------------------------
        disp.setPhrase(jfac.getRptFont("Cost $", true));
        table.addCell(disp);
        cell.setPhrase(jfac.getRptFont(""+r.getRmCost()));
        table.addCell(cell);
        //---------------------------------------------
        disp.setPhrase(jfac.getRptFont("Total Cost $", true));
        table.addCell(disp);
        date.setPhrase(jfac.getRptFont(""+r.getTotalCost()));
        table.addCell(date);
        //-----------------    Next Row ----------------------------
        disp.setPhrase(jfac.getRptFont("Departing", true));
        table.addCell(disp);
        date.setPhrase(jfac.getRptFont(""+r.getFltDepPlace()));
        table.addCell(date);
        
       disp.setPhrase(jfac.getRptFont("Date/Time", true));
        table.addCell(disp);
        date.setPhrase(jfac.getRptFont(""+r.getFltDepDate()));
        table.addCell(date);
         //-----------------    Next Row ----------------------------
        disp.setPhrase(jfac.getRptFont("Arriving", true));
        table.addCell(disp);
        date.setPhrase(jfac.getRptFont(""+r.getFltArrPlace()));
        table.addCell(date);
        
       disp.setPhrase(jfac.getRptFont("Date/Time", true));
        table.addCell(disp);
        date.setPhrase(jfac.getRptFont(""+r.getFltArrDate()));
        table.addCell(date);
        
        //-----------------    Next Row ----------------------------
        disp.setPhrase(jfac.getRptFont("Comment", true));
        table.addCell(disp);
        
        head.setColspan(7);
        head.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        //head.setBackgroundColor(Color.WHITE);
        head.setBackgroundColor(BaseColor.WHITE);
        head.setPhrase(jfac.getRptFont(r.getBalanceComment()));
        table.addCell(head);
        
        try {
            doc.add(table);
        } catch (DocumentException ex) {
            ex.printStackTrace();
        }
    }
    // ================================================================
    public void genHotel(List<rptItineraryBean> list,String type,Document doc){
        brk(doc,type);
        jlRoomsFactoryRpt jfac = new jlRoomsFactoryRpt();
        for(rptItineraryBean r: list){
            genHotel(r,jfac,doc);
        }
    }
    private void genHotel(rptItineraryBean r, jlRoomsFactoryRpt jfac,Document doc) {
         brk(doc);
        //jlRoomsFactoryRpt jfac = new jlRoomsFactoryRpt();
        //float[] widths = {0.13f, 0.12f, 0.12f, 0.12f, 0.13f, 0.12f, 0.12f, 0.12f};
        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100); // percentage
        PdfPCell disp = new PdfPCell(),
                date = new PdfPCell(),
                head = new PdfPCell(),
                cell = new PdfPCell();
        date.setColspan(3);
        
        disp.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        disp.setBackgroundColor(BaseColor.LIGHT_GRAY);
        
        head.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        head.setBackgroundColor(BaseColor.LIGHT_GRAY);
        head.setColspan(8);
        head.setPhrase(jfac.getRptFont(r.getVendorName()+" "+r.getVendorTypeDesc(),true));
        table.addCell(head);
        //--------------------------------------------------------------------------------------------
        disp.setPhrase(jfac.getRptFont("Days", true));
        table.addCell(disp);
        cell.setPhrase(jfac.getRptFont(""+r.getDays()));
        table.addCell(cell);
        //---------------------------------------------
        disp.setPhrase(jfac.getRptFont("Count", true));
        table.addCell(disp);
        cell.setPhrase(jfac.getRptFont(r.getCustRmCnt()+" "+r.split()));
        table.addCell(cell);
        //---------------------------------------------
        disp.setPhrase(jfac.getRptFont("Cost $", true));
        table.addCell(disp);
        cell.setPhrase(jfac.getRptFont(""+r.getRmCost()));
        table.addCell(cell);
        //---------------------------------------------
        disp.setPhrase(jfac.getRptFont("Total Cost $	", true));
        table.addCell(disp);
        cell.setPhrase(jfac.getRptFont(""+r.getTotalCost()));
        table.addCell(cell);
        //-----------------    Next Row ----------------------------
       disp.setPhrase(jfac.getRptFont("Status", true));
        table.addCell(disp);
        cell.setPhrase(jfac.getRptFont(""+r.getSysDesc()));
        table.addCell(cell);
        //---------------------------------------------
        disp.setPhrase(jfac.getRptFont(r.getRptDesc(), true));
        table.addCell(disp);
        cell.setPhrase(jfac.getRptFont(r.getRptDesc()));
        table.addCell(cell);
        // ----------------------------------------------------------
        disp.setPhrase(jfac.getRptFont("Balance Due", true));
        table.addCell(disp);
        date.setPhrase(jfac.getRptFont(""+r.getBalancedDueDate()));
        table.addCell(date);
        //-----------------    Next Row ----------------------------
         disp.setPhrase(jfac.getRptFont("Starting Date", true));
        table.addCell(disp);
        date.setPhrase(jfac.getRptFont(""+r.getEffDate()));
        table.addCell(date);
        // ----------------------------------------------------------
        disp.setPhrase(jfac.getRptFont("Ending Date", true));
        table.addCell(disp);
        date.setPhrase(jfac.getRptFont(""+r.getEndDate()));
        table.addCell(date);
        //-----------------    Next Row ----------------------------
        disp.setPhrase(jfac.getRptFont("Comment", true));
        table.addCell(disp);
        
        head.setColspan(7);
        head.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
        head.setBackgroundColor(BaseColor.WHITE);
        head.setPhrase(jfac.getRptFont(r.getBalanceComment()));
        table.addCell(head);
        
        try {
            doc.add(table);
        } catch (DocumentException ex) {
            ex.printStackTrace();
        }
    }
}
