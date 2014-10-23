/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsCommon.pdf;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import jlRoomsCommon._beans.custBean;
import jlRoomsCommon._beans.jsfRptBean;
import jlRoomsCommon._beans.propertiesBean;
import jlRoomsCommon._beans.rptBeanColumes;
import jlRoomsCommon._beans.rptBeanColumesAmtList;
import jlRoomsCommon._beans.sponsorBean;
import jlRoomsCommon._beans.v2RptClientRmMateBean;
import jlRoomsCommon._beans.v2RptClientRmMateBeanList;
import jlRoomsCommon._beans.vendorBean;
import jlRoomsCommon.jlRoomsFactoryRpt;
import obj.pdf.pdfRptMgr;


/**
 *
 * @author lmeans
 */
public class bookingTable {
    Document document = null;
    File f;
    String fileName;
    //public void close(propertiesBean bean,String header,Stri){
    //    if (document != null ) document.close();
        
   // }
    public String close(){
        if (document != null ) document.close();
       System.out.println( f.getAbsolutePath());
        return f.getAbsolutePath();
        
    }
    public void close(propertiesBean bean,String[] to,String rpt) throws Exception {
         //if (document != null ) document.close();
        if (document != null ) document.close();
        if (bean == null) return;
        try {
            InternetAddress x[] = new InternetAddress[to.length];
            for (int i = 0; i < to.length; i++){
                x[i] = new InternetAddress(to[i]);
            }
           
            //x[1] = new InternetAddress("lmeans@cv.net");
            Properties props = new Properties();
            props.put(bean.getSmtp(), bean.getHost());
            Session session = Session.getDefaultInstance(props, null);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("info@jlrooms.com"));
            InternetAddress[] address = x;
            message.setRecipients(Message.RecipientType.TO, address);
            message.setSubject(rpt);
            message.setSentDate(new Date());
            message.setText("See Attachment regarding: "+rpt);
            //MimeBodyPart mbp1 = new MimeBodyPart();
                //mbp1.setText("<h1> See Attachment<h1/> ","text/html");

                //Multipart multipart = new MimeMultipart();

                MimeBodyPart messageBodyPart = new MimeBodyPart();

                DataSource source = new FileDataSource(f);
                messageBodyPart.setDataHandler( new DataHandler(source));
                messageBodyPart.setFileName(fileName+".pdf");
                //multipart.addBodyPart(messageBodyPart);
               // message.setContent(multipart);


                Multipart mp = new MimeMultipart();
                //mp.addBodyPart(mbp1);
                mp.addBodyPart(messageBodyPart);

                // add the Multipart to the message
                message.setContent(mp);

                // send the message
                Transport.send(message);
                
        } catch (MessagingException ex) {
            Logger.getLogger(bookingTable.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } 
    }
    public Paragraph paragraph(String str){
        Paragraph pg = new Paragraph(str);
        pg.setAlignment(Paragraph.ALIGN_CENTER);
        pg.setFont(FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD));
        return pg;
    }
    public void setVendor(vendorBean v) {
        try {
            document.add(paragraph(v.getVendorName()));
            String str="";
            if (v.getADDR1() != null  && v.getADDR1().trim().length() > 0){
                 str = v.getADDR1().trim();
            }
            
            if (v.getADDR2() != null && v.getADDR2().trim().length() > 0){
                str += " "+v.getADDR2().trim();
                //document.add(paragraph(v.getADDR2().trim()));
            }
            if (v.getCITY() != null && v.getCITY().trim().length() > 0){
                str += " "+v.getCITY().trim();
            }
            if(v.getState() != null && v.getState().trim().length() > 0){
                str+= " "+v.getState();
            }
            if (v.getZIP() != null && v.getZIP().trim().length() > 0){
                str+= " "+v.getZIP();
            }
            if (v.getZipPlus() != null && v.getZipPlus().trim().length() > 0){
                str += " "+v.getZipPlus();
            }
            if (str.trim().length() > 0){
                 document.add(paragraph(str.trim()));
            }
            // ==========================================================
            str = "";
            if (v.getPhone() != null && v.getPhone().trim().length() > 0){
                str+= v.getPhone().trim();
            }
            if (v.getPhoneExt() != null && v.getPhoneExt().trim().length() > 0){
                str+=" Ext: "+v.getPhoneExt();
            }
            if (v.getFax() != null && v.getFax().trim().length() > 0){
                str += " Fax: "+v.getFax();
            }
            if (str.trim().length() > 0){
                document.add(paragraph(str.trim()));
            }
            if (v.getEMail() != null && v.getEMAIL().trim().length() > 0){
                document.add(paragraph(v.getEMail()));
            }
            document.add(paragraph("\n\r"));
        } catch (DocumentException ex) {
            Logger.getLogger(bookingTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setCustBean(custBean custBean) throws Exception {
        Paragraph pg = new Paragraph("Welcome to JLRooms\n\r");
        pg.setAlignment(Paragraph.ALIGN_CENTER);
        pg.setFont(FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD));
        document.add(pg);
        float[] widths = {0.20f, 0.40f};
        PdfPTable table = new PdfPTable(widths);
        PdfPCell //cellX  = new PdfPCell(),
                       cell1 = new PdfPCell(),
                       cell2 = new PdfPCell();
        cell1.setPhrase(pdfRptMgr.getRptFont("Name"));
        cell2.setPhrase(pdfRptMgr.getRptFont(custBean.getDisplayFullName()));
        table.addCell(cell1);
        table.addCell(cell2);
         cell1.setPhrase(pdfRptMgr.getRptFont("Site ID"));
        cell2.setPhrase(pdfRptMgr.getRptFont(custBean.getKeyStr()));
        table.addCell(cell1);
        table.addCell(cell2);
        cell1.setPhrase(pdfRptMgr.getRptFont("E-Mail"));
        cell2.setPhrase(pdfRptMgr.getRptFont(custBean.getEMail()));
        table.addCell(cell1);
        table.addCell(cell2);
        cell1.setPhrase(pdfRptMgr.getRptFont("User Name"));
        cell2.setPhrase(pdfRptMgr.getRptFont(custBean.getUserName()));
        table.addCell(cell1);
        table.addCell(cell2);
       
        cell1.setPhrase(pdfRptMgr.getRptFont("Password"));
        cell2.setPhrase(pdfRptMgr.getRptFont(custBean.getPassWord()));
        table.addCell(cell1);
        table.addCell(cell2);
        this.document.add(table);
        
    }
    // ================================================================
     public void openWithFile(String fName) {
        try {
            document = new Document(PageSize.A4, 20, 20, 20, 20);
            f = new File(fName);
             PdfWriter.getInstance(document, new FileOutputStream(f));
               //PdfWriter.getInstance(document, new FileOutputStream(filename));
               //PdfWriter.getInstance(document, new File(filename));
               document.open();
        } catch (Exception ex) {
            Logger.getLogger(bookingTable.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    public void open(String fName) {
        this.fileName = fName;
         document = new Document(PageSize.A4, 20, 20, 20, 20);
         //String filename = "test.pdf";
        
        try {
            //http://stackoverflow.com/questions/21360932/fail-to-access-a-file-inside-tomcat-temp-folder
            f = File.createTempFile(this.fileName, ".pdf");    
            


    
            System.out.println(f.getAbsoluteFile());

            System.out.println(f.getAbsolutePath());
            System.out.println(f.getPath());
            System.out.println(f.getCanonicalPath());
            System.out.println(f.getParentFile());
            
            
            PdfWriter.getInstance(document, new FileOutputStream(f));
            //PdfWriter.getInstance(document, new FileOutputStream(filename));
            //PdfWriter.getInstance(document, new File(filename));
            document.open();
            
            
        } catch (Exception ex) {
            Logger.getLogger(bookingTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public Document getDocument(){
        if (document == null) open("rpt");
        return document;
    }
    public void header(){
        
    }
    public void genReservation(List<jsfRptBean> resList){
        try {
            if (resList == null || resList.size() <= 0) return;
             Paragraph pg = new Paragraph("Reservation\n\n");
             pg.setAlignment(Paragraph.ALIGN_CENTER);
             pg.setFont(FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD));
             document.add(pg);
             float[] widths = {0.10f,0.15f,0.41f,0.10f, 0.06f, 0.06f, 0.12f};
            PdfPTable table = new PdfPTable(widths);
            
             //PdfPTable table = new PdfPTable(7);
             table.setWidthPercentage(100); // percentage			
             jlRoomsFactoryRpt.setRptHeaderCell(new String[] {"Status","Balance Due", "Description","Cost","Cnt","Days","Total"}, table);
              PdfPCell //cellX  = new PdfPCell(),
                       cell1 = new PdfPCell(),
                       cell2 = new PdfPCell(),
                       cell3 = new PdfPCell(),
                       cell4 = new PdfPCell(),
                       cell5 = new PdfPCell(),
                       cell6 = new PdfPCell(),
                       cell7 = new PdfPCell();
             cell4.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
             cell5.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
             cell6.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
             cell7.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
             jsfRptBean w;
             for (int i = 0; i < resList.size(); i++){
               w = resList.get(i);
               cell1.setPhrase(pdfRptMgr.getRptFont(w.getC1()));
               cell2.setPhrase(pdfRptMgr.getRptFont(w.getC2()));
               cell3.setPhrase(pdfRptMgr.getRptFont(w.getC3()));
               cell4.setPhrase(pdfRptMgr.getRptFont(w.getC4()));
               cell5.setPhrase(pdfRptMgr.getRptFont(w.getC5()));
               cell6.setPhrase(pdfRptMgr.getRptFont(w.getC6()));
               cell7.setPhrase(pdfRptMgr.getRptFont(w.getC7()));
                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);
                table.addCell(cell4);
                table.addCell(cell5);
                table.addCell(cell6);
                table.addCell(cell7);
             }
             this.document.add(table);
        } catch (DocumentException ex) {
            Logger.getLogger(bookingTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void genPaymentTable(rptBeanColumesAmtList bean){
        try {
            if (bean == null) return;
             Paragraph pg = new Paragraph("Payment's \n\n");
              pg.setAlignment(Paragraph.ALIGN_CENTER);
         pg.setFont(FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD));
        document.add(pg);
             List<rptBeanColumesAmtList> list = bean.getRpt();
             rptBeanColumesAmtList x;
              float[] widths = {
                    0.15f,
                  0.28f,
                  0.30f,
                  0.15f, 
                    0.12f};
            PdfPTable table = new PdfPTable(widths);
             //PdfPTable table = new PdfPTable(5);
             table.setWidthPercentage(100); // percentage
             jlRoomsFactoryRpt.setRptHeaderCell(new String[] {"Date","Name", "Desc","Booking","Amt"}, table);
              
             for (int i = 0; i < list.size(); i++){
               x = list.get(i);
               genPaymentTable(x.getRptList(),x.getHeader(),x.getAmtStr(),table);
               
             }
             PdfPCell //cellX  = new PdfPCell(),
                       cell1 = new PdfPCell(),
                       cell2 = new PdfPCell();
             cell1.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
             cell1.setColspan(4);
             cell1.setPhrase(pdfRptMgr.getRptFont("Total"));
             table.addCell(cell1);
             cell2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
             cell2.setColspan(1);
             cell2.setPhrase(pdfRptMgr.getRptFont(bean.getAmtStr()));
             table.addCell(cell2);
           //cellX.setBackgroundColor(Color.LIGHT_GRAY);
             this.document.add(table);
        } catch (DocumentException ex) {
            Logger.getLogger(bookingTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void genPaymentTable( List<rptBeanColumes> x,String header,String amt, PdfPTable table){
        try {
            PdfPCell //cellX  = new PdfPCell(),
                       cell1 = new PdfPCell(),
                       cell2 = new PdfPCell(),
                       cell3 = new PdfPCell(),
                       cell4 = new PdfPCell(),
                        cell5 = new PdfPCell();
           rptBeanColumes w;
          cell5.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
           //table.setWidthPercentage(100); // percentage
           //cellX.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
           //cellX.setBackgroundColor(Color.LIGHT_GRAY);
           //cellX.setColspan(4);
           //cellX.setPhrase(pdfRptMgr.getRptFont(header));
           //table.addCell(cellX);
           for (int i = 0; i < x.size();  i++){
               w = x.get(i);
               cell1.setPhrase(pdfRptMgr.getRptFont(w.getCol01()));
               cell2.setPhrase(pdfRptMgr.getRptFont(w.getCol02()));
               cell3.setPhrase(pdfRptMgr.getRptFont(w.getCol03()));
               cell4.setPhrase(pdfRptMgr.getRptFont(w.getCol04()));
               cell5.setPhrase(pdfRptMgr.getRptFont(w.getCol05()));
                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);
                table.addCell(cell4);
                table.addCell(cell5);
           }
           //cellX.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
           //cellX.setBackgroundColor(Color.LIGHT_GRAY);
           //cellX.setColspan(3);
           //cellX.setPhrase(pdfRptMgr.getRptFont(header+" Total"));
           //table.addCell(cellX);
           
           //cellX.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
           //cellX.setBackgroundColor(Color.LIGHT_GRAY);
           //cellX.setColspan(1);
           //cellX.setPhrase(pdfRptMgr.getRptFont(amt));
           //table.addCell(cellX);
           
        } catch (Exception ex) {
            Logger.getLogger(bookingTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void genRoommateTable(List<v2RptClientRmMateBeanList> rptListArray){
       if (rptListArray == null || rptListArray.size() <= 0) return;
        PdfPCell 
                    cellX = new PdfPCell(),
                    cell0 = new PdfPCell(),
                    cell1 = new PdfPCell(),
                    cell2 = new PdfPCell(),
                    cell3 = new PdfPCell(),
                    cell4 = new PdfPCell();
        
        try {
             Paragraph pg = new Paragraph("Roommate/Group List \n\n");
              pg.setAlignment(Paragraph.ALIGN_CENTER);
         pg.setFont(FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD));
        document.add(pg);
        v2RptClientRmMateBeanList x;
        
         cellX.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
             cellX.setBackgroundColor(Color.LIGHT_GRAY);
             cellX.setColspan(5);
             
        
        for (int i = 0;i < rptListArray.size(); i++){
           x = rptListArray.get(i);
           List<v2RptClientRmMateBean> y = x.getList();
           PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100); // percentage
           cellX.setPhrase(pdfRptMgr.getRptFont(x.getDesc()+" ("+x.getLookupDesc()+")"+"\n"+x.getVendor(),true));
           table.addCell(cellX);
           jlRoomsFactoryRpt.setRptHeaderCell(new String[] {"Name", "E-Maile","Phone #","Addr", "City/State"}, table);
           for (int j = 0; j < y.size(); j++){
               v2RptClientRmMateBean b = y.get(j);
                cell0.setPhrase(pdfRptMgr.getRptFont(b.getFirstName()+" "+b.getFirstName()));
                cell1.setPhrase(pdfRptMgr.getRptFont(b.getEMail()));
                cell2.setPhrase(pdfRptMgr.getRptFont(b.getHmPhone()));
                cell3.setPhrase(pdfRptMgr.getRptFont((b.getAddr1()+" "+b.getAddr2()).trim()));
                cell4.setPhrase(pdfRptMgr.getRptFont((b.getCity()+" "+b.getState()).trim()));
                table.addCell(cell0);
                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);
                table.addCell(cell4);
           }
           this.document.add(table);
            pg = new Paragraph("\n");
            document.add(pg);
        
        }
        } catch (Exception e){
            Logger.getLogger(bookingTable.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    public void genBookingTable( List<jsfRptBean> list,String header,String footer){
        Paragraph pg = new Paragraph("\n");
         pg.setAlignment(Paragraph.ALIGN_CENTER);
         pg.setFont(FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD));
        try {
             document.add(pg);
            
             PdfPCell 
                    cell0 = new PdfPCell(),
                    cell1 = new PdfPCell(),
                    cell2 = new PdfPCell(),
                    cell3 = new PdfPCell(),
                    cell4 = new PdfPCell();
             
             
             
             cell1.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
             cell1.setBackgroundColor(Color.lightGray);
             
             cell3.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
             cell3.setBackgroundColor(Color.lightGray);
             
            float[] widths = {0.15f, 0.35f, 0.15f, 0.35f};
            PdfPTable table = new PdfPTable(widths);
            table.setWidthPercentage(100); // percentage
            cell0.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
             cell0.setBackgroundColor(Color.LIGHT_GRAY);
             cell0.setColspan(4);
             cell0.setPhrase(pdfRptMgr.getRptFont(header,true));
             table.addCell(cell0);
            
            for (int i = 0; i < list.size(); i++){
                cell1.setPhrase(pdfRptMgr.getRptFont(list.get(i).getC1(),true));
                cell2.setPhrase(pdfRptMgr.getRptFont(list.get(i).getC2()));
                cell3.setPhrase(pdfRptMgr.getRptFont(list.get(i).getC3(),true));
                cell4.setPhrase(pdfRptMgr.getRptFont(list.get(i).getC4()));
                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);
                table.addCell(cell4);
            }
            cell0.setColspan(1);
            cell0.setPhrase(pdfRptMgr.getRptFont("Comment",true));
            
            cell1.setColspan(3);
            cell1.setPhrase(pdfRptMgr.getRptFont(footer));
            
            table.addCell(cell0);
            table.addCell(cell1);
            document.add(table);
            
          
        } catch (DocumentException ex) {
            Logger.getLogger(bookingTable.class.getName()).log(Level.SEVERE, null, ex);
        }  catch (Exception e){
            Logger.getLogger(bookingTable.class.getName()).log(Level.SEVERE, null, e);
        } 
    }
    
    // ==========================================================================
     private  String pad(int x){
        int y = (""+x).length() + 1;
        if (y < 5) y = 5;
        return String.format("%0"+y+"d", x);
    }
     public void genInvHeader(sponsorBean sponsorBean, custBean custBean){
        try {
            PdfPTable table = new PdfPTable(2);
               table.setWidthPercentage(100); // percentage
              
               table.addCell(getInvoiceCust(custBean));
               table.addCell(getInvoiceTable(custBean.getCustId(), sponsorBean,custBean.getKeyStr()));

               document.add(table);
        } catch (DocumentException ex) {
            Logger.getLogger(bookingTable.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    private  PdfPTable getInvoiceTable(int cust,sponsorBean sponsorBean,String key ) {
        
        float[] widths = {
            0.20f, .80f};
        PdfPTable table = new PdfPTable(widths);
        table.setWidthPercentage(100); // percentage
        PdfPCell cell = new PdfPCell();
        cell.setBorder(0);
        cell.setPhrase(jlRoomsFactoryRpt.getRptFont("Invoice"));
        table.addCell(cell);
       
        cell.setPhrase(jlRoomsFactoryRpt.getRptFont((key == null ? "":key.toUpperCase()+"-") + pad(cust)));
        table.addCell(cell);

        cell.setPhrase(jlRoomsFactoryRpt.getRptFont("Inv. Date"));
        table.addCell(cell);

        cell.setPhrase(jlRoomsFactoryRpt.getRptFont(new SimpleDateFormat("EEE MMMM dd yyyy").format(Calendar.getInstance().getTime())));
        table.addCell(cell);
        try {
            
             //sponsorBean = jlRoomsFactoryObj.get_sponsorObj().sponsorGetEvent(spon);
            //if (sponsorBean.getCustId() == 0) {
            cell.setPhrase(jlRoomsFactoryRpt.getRptFont("Event"));
            table.addCell(cell);
            cell.setPhrase(jlRoomsFactoryRpt.getRptFont(sponsorBean.getSponsorDesc()));
            table.addCell(cell);
            cell.setPhrase(jlRoomsFactoryRpt.getRptFont("Sponsor"));
            table.addCell(cell);
            cell.setPhrase(jlRoomsFactoryRpt.getRptFont(sponsorBean.getVendorName()));
            table.addCell(cell);

        //}



        } catch (Exception e) {
            Logger.getLogger(bookingTable.class.getName()).log(Level.SEVERE, null, e);
        }

        return table;
    }
    private  PdfPTable getInvoiceCust(custBean bean) {
        
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100); // percentage
        PdfPCell cell = new PdfPCell();
        cell.setBorder(0);

        cell.setPhrase(jlRoomsFactoryRpt.getRptFont(bean.getDisplayFullName()));
        table.addCell(cell);

        if (bean.getAddr1() != null && bean.getAddr1().trim().length() > 0) {
            cell.setPhrase(jlRoomsFactoryRpt.getRptFont(bean.getAddr1()));
            table.addCell(cell);
        }
        if (bean.getAddr2() != null && bean.getAddr2().trim().length() > 0) {
            cell.setPhrase(jlRoomsFactoryRpt.getRptFont(bean.getAddr2()));
            table.addCell(cell);
        }
        cell.setPhrase(jlRoomsFactoryRpt.getRptFont(bean.getDisplayTown()));
        table.addCell(cell);




        return table;

    }
    public static void main(String args[]){
        bookingTable bookingTable = new bookingTable();
        
    }
}
