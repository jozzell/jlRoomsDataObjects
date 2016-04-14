/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsCommon;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import jlRoomsCommon._beans.propertiesBean;
import jlRoomsCommon._beans.sponsorBean;
import jlRoomsCommon._beans.vendorBean;
import obj.pdf.pdfRptMgr;
import obj.reusableObj;


/**
 *
 * @author lmeans
 */
public class bookingTable {
    private Document document = null;
    private File f;
    private pdfRptMgr pdfRptMgr;
    private vendorBean vendorBean;
    private custBean custBean;
    private sponsorBean sponsorBean;
    private String fileName;
  
    private String unixPath;
   
   // }
    public bookingTable(){
        
    }
    public bookingTable(vendorBean v){
        this(v,null,null);
    }
    public bookingTable(custBean b,sponsorBean s){
        this(null,b,s);
    }
    public bookingTable(final vendorBean v,final custBean b,sponsorBean s){
        pdfRptMgr = new pdfRptMgr();
        this.vendorBean = v;
        this.custBean = b;
        this.sponsorBean = s;
    }
    public String close(){
        if (document != null ) document.close();
       System.out.println( f.getAbsolutePath());
        return f.getAbsolutePath();
        
    }
    public void sendEmail(propertiesBean bean,String[] to,String rpt,String subject) throws Exception {
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
            message.setSubject(subject);
            message.setSentDate(new Date());
            message.setText("See Attachment regarding: "+rpt);
            //MimeBodyPart mbp1 = new MimeBodyPart();
                //mbp1.setText("<h1> See Attachment<h1/> ","text/html");

                //Multipart multipart = new MimeMultipart();

                MimeBodyPart messageBodyPart = new MimeBodyPart();

                DataSource source = new FileDataSource(f);
                messageBodyPart.setDataHandler( new DataHandler(source));
                messageBodyPart.setFileName(getFileName()+".pdf");
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
    private void setVendor() {
        setVendor(this.vendorBean);
    }
    public void setVendor(vendorBean v) {
        StringBuilder sb = new StringBuilder();
        try {
            document.add(paragraph(v.getVendorName()));
            //String str="";
            if (v.getADDR1() != null  && v.getADDR1().trim().length() > 0){
                sb.append(v.getADDR1().trim()).append(" ");
                 //str = v.getADDR1().trim();
            }
            
            if (v.getADDR2() != null && v.getADDR2().trim().length() > 0){
                sb.append(v.getADDR2().trim()).append(" ");
                //str += " "+v.getADDR2().trim();
                //document.add(paragraph(v.getADDR2().trim()));
            }
            
            if (v.getCITY() != null && v.getCITY().trim().length() > 0){
                sb.append(v.getCITY().trim()).append(" ");
                //str += " "+v.getCITY().trim();
            }
            if(v.getState() != null && v.getState().trim().length() > 0){
                sb.append(v.getState().trim()).append(" ");
                //str+= " "+v.getState();
            }
            if (v.getZIP() != null && v.getZIP().trim().length() > 0){
                sb.append(v.getZIP().trim()).append(" ");
                if (v.getZipPlus() != null && v.getZipPlus().trim().length() > 0){
                     sb.append("-").append(v.getZIP().trim()).append(" ");
                    //str += " "+v.getZipPlus();
                }
                //str+= " "+v.getZIP();
            }
            if (sb.length() > 0){
                document.add(paragraph(sb.toString().trim()));
            }
            
            //---------------------------------------------
            sb = new StringBuilder();
            // ==========================================================
            
            if (v.getPhone() != null && v.getPhone().trim().length() > 0){
                sb.append(v.getPhone().trim()).append(" ");
                if (v.getPhoneExt() != null && v.getPhoneExt().trim().length() > 0){
                    sb.append(" Ext ").append(v.getPhoneExt().trim()).append(" ");
                    //str+=" Ext: "+v.getPhoneExt();
                }
                //str+= v.getPhone().trim();
            }
            
            if (v.getFax() != null && v.getFax().trim().length() > 0){
                sb.append(" Fax# ").append(v.getFax().trim()).append(" ");
                //str += " Fax: "+v.getFax();
            }
            if (sb.length() > 0){
                document.add(paragraph(sb.toString().trim()));
            }
            
            if (v.getEMail() != null && v.getEMAIL().trim().length() > 0){
                document.add(paragraph(v.getEMail().trim()));
            }
            document.add(paragraph("\n\r"));
        } catch (DocumentException ex) {
            Logger.getLogger(bookingTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setCustBean(custBean custBean) throws Exception {
        Paragraph pg = new Paragraph("JLRooms.com\n\r");
        pg.setAlignment(Paragraph.ALIGN_CENTER);
        pg.setFont(FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD));
        document.add(pg);
        float[] widths = {0.20f, 0.40f};
        PdfPTable table = new PdfPTable(widths);
        PdfPCell //cellX  = new PdfPCell(),
                       cell1 = new PdfPCell(),
                       cell2 = new PdfPCell();
        cell1.setPhrase(getPdfRptMgr().getRptFont("Name"));
        cell2.setPhrase(getPdfRptMgr().getRptFont(custBean.getDisplayFullName()));
        table.addCell(cell1);
        table.addCell(cell2);
         cell1.setPhrase(getPdfRptMgr().getRptFont("Site ID"));
        cell2.setPhrase(getPdfRptMgr().getRptFont(custBean.getKeyStr()));
        table.addCell(cell1);
        table.addCell(cell2);
        cell1.setPhrase(getPdfRptMgr().getRptFont("E-Mail"));
        cell2.setPhrase(getPdfRptMgr().getRptFont(custBean.getEMail()));
        table.addCell(cell1);
        table.addCell(cell2);
        cell1.setPhrase(getPdfRptMgr().getRptFont("User Name"));
        cell2.setPhrase(getPdfRptMgr().getRptFont(custBean.getUserName()));
        table.addCell(cell1);
        table.addCell(cell2);
       
        cell1.setPhrase(getPdfRptMgr().getRptFont("Password"));
        cell2.setPhrase(getPdfRptMgr().getRptFont(custBean.getPassWord()));
        table.addCell(cell1);
        table.addCell(cell2);
        this.document.add(table);
        
    }
    // ================================================================
    //public void openWithFile(String fName) {
    //    openWithFile(fName,null,null,false);
    //}
     public void openWithFile(String fName,String path,String unixPath,boolean landscape) {
         if (unixPath == null) unixPath = "";
         if (path == null) path = "";
        
            fileName = (new StringBuilder())
               .append(path)
               .append("/")
               .append((new SimpleDateFormat("yyyyMMdd")).format(Calendar.getInstance().getTime()))
               .append("_")
               .append((new reusableObj()).getUnixName(fName, "_"))
               .append(".pdf").toString();
         this.unixPath = unixPath;
         
       //this.fileName = path+"/"+(new reusableObj()).getUnixName(fName, "_")+(new SimpleDateFormat("yyyyMMdd")).format(Calendar.getInstance().getTime())+".pdf";
       
        try {
            //if (landscape){
                //document = new Document(PageSize.A4.rotate());
            //} else {
                document = new Document(PageSize.A4);
            //}
            //document = new Document(PageSize.A4.rotate());
            //document = new Document(PageSize.A4, 20, 20, 20, 20);
            if (path != null){
                new File(unixPath+"/"+path).mkdirs();
                f = new File(unixPath+"/"+fileName);
            } else {
                f = new File(fName);
            }
           
             PdfWriter.getInstance(document, new FileOutputStream(f));
               //PdfWriter.getInstance(document, new FileOutputStream(filename));
               //PdfWriter.getInstance(document, new File(filename));
              document.open();
              if (this.vendorBean != null) this.setVendor();
              if (this.custBean != null) genInvHeader();
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
            f = File.createTempFile(this.getFileName(), ".pdf");    
            


    
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
  
    
    // ==========================================================================
     private  String pad(int x){
        int y = (""+x).length() + 1;
        if (y < 5) y = 5;
        return String.format("%0"+y+"d", x);
    }
     private void genInvHeader(){
         genInvHeader(sponsorBean,custBean);
     }
     public void genInvHeader(sponsorBean sponsorBean, custBean custBean){
        try {
            PdfPTable table = new PdfPTable(2);
               table.setWidthPercentage(100); // percentage
              
               table.addCell(getInvoiceCust(custBean));
               table.addCell(getInvoiceTable(custBean.getCustId(), sponsorBean,custBean.getKeyStr()));

               document.add(table);
               document.add(paragraph("\n\r"));
        } catch (DocumentException ex) {
            Logger.getLogger(bookingTable.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    private  PdfPTable getInvoiceTable(int cust,sponsorBean sponsorBean,String key ) {
        jlRoomsFactoryRpt jlRoomsFactoryRpt =  new jlRoomsFactoryRpt();
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
        jlRoomsFactoryRpt jlRoomsFactoryRpt =  new jlRoomsFactoryRpt();
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
        bookingTable bookingTable = new bookingTable(null,null);
        
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @return the unixPath
     */
    public String getUnixPath() {
        return unixPath;
    }

    /**
     * @return the pdfRptMgr
     */
    public pdfRptMgr getPdfRptMgr() {
        if (pdfRptMgr == null) pdfRptMgr = new pdfRptMgr();
        return pdfRptMgr;
    }
}
/*
  public void genReservationXXX(List<jsfRptBean> resList){
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
             (new jlRoomsFactoryRpt()).setRptHeaderCell(new String[] {"Status","Balance Due", "Description","Cost","Cnt","Days","Total"}, table);
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
               cell1.setPhrase(getPdfRptMgr().getRptFont(w.getC1()));
               cell2.setPhrase(getPdfRptMgr().getRptFont(w.getC2()));
               cell3.setPhrase(getPdfRptMgr().getRptFont(w.getC3()));
               cell4.setPhrase(getPdfRptMgr().getRptFont(w.getC4()));
               cell5.setPhrase(getPdfRptMgr().getRptFont(w.getC5()));
               cell6.setPhrase(getPdfRptMgr().getRptFont(w.getC6()));
               cell7.setPhrase(getPdfRptMgr().getRptFont(w.getC7()));
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
    
    
    public void genRoommateTableXXX(List<v2RptClientRmMateBeanList> rptListArray){
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
           cellX.setPhrase(getPdfRptMgr().getRptFont(x.getDesc()+" ("+x.getLookupDesc()+")"+"\n"+x.getVendor(),true));
           table.addCell(cellX);
           (new jlRoomsFactoryRpt()).setRptHeaderCell(new String[] {"Name", "E-Maile","Phone #","Addr", "City/State"}, table);
           for (int j = 0; j < y.size(); j++){
               v2RptClientRmMateBean b = y.get(j);
                cell0.setPhrase(getPdfRptMgr().getRptFont(b.getFirstName()+" "+b.getFirstName()));
                cell1.setPhrase(getPdfRptMgr().getRptFont(b.getEMail()));
                cell2.setPhrase(getPdfRptMgr().getRptFont(b.getHmPhone()));
                cell3.setPhrase(getPdfRptMgr().getRptFont((b.getAddr1()+" "+b.getAddr2()).trim()));
                cell4.setPhrase(getPdfRptMgr().getRptFont((b.getCity()+" "+b.getState()).trim()));
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
    public void genBookingTableXXX( List<jsfRptBean> list,String header,String footer){
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
             cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
             
             cell3.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
             cell3.setBackgroundColor(BaseColor.LIGHT_GRAY);
             
            float[] widths = {0.15f, 0.35f, 0.15f, 0.35f};
            PdfPTable table = new PdfPTable(widths);
            table.setWidthPercentage(100); // percentage
            cell0.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
             cell0.setBackgroundColor(Color.LIGHT_GRAY);
             cell0.setColspan(4);
             cell0.setPhrase(getPdfRptMgr().getRptFont(header,true));
             table.addCell(cell0);
            
            for (int i = 0; i < list.size(); i++){
                cell1.setPhrase(getPdfRptMgr().getRptFont(list.get(i).getC1(),true));
                cell2.setPhrase(getPdfRptMgr().getRptFont(list.get(i).getC2()));
                cell3.setPhrase(getPdfRptMgr().getRptFont(list.get(i).getC3(),true));
                cell4.setPhrase(getPdfRptMgr().getRptFont(list.get(i).getC4()));
                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);
                table.addCell(cell4);
            }
            cell0.setColspan(1);
            cell0.setPhrase(getPdfRptMgr().getRptFont("Comment",true));
            
            cell1.setColspan(3);
            cell1.setPhrase(getPdfRptMgr().getRptFont(footer));
            
            table.addCell(cell0);
            table.addCell(cell1);
            document.add(table);
            
          
        } catch (DocumentException ex) {
            Logger.getLogger(bookingTable.class.getName()).log(Level.SEVERE, null, ex);
        }  catch (Exception e){
            Logger.getLogger(bookingTable.class.getName()).log(Level.SEVERE, null, e);
        } 
    }
*/