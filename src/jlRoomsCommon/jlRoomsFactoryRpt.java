package jlRoomsCommon;

import obj.pdf.pdfRptMgr;
import java.io.*;
//---WEB uncomment >>>>> import jlRooms.rpt.pdf.CustomerFlightEventOverViewPDF;
import javax.swing.*;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import jlRoomsCommon._beans.*;
//---WEB uncomment >>>>> import jlRooms.rpt.*;
import java.util.Calendar;
//---WEB uncomment >>>>> import jlRooms.jlRoomsFactory;
//import jlRooms.jlRoomsFactory;
//---WEB uncomment >>>>> import jlRooms.jlRoomsFactoryObj;
//---WEB uncomment >>>>> import jlRooms.jlRoomsFactoryObj;

public class jlRoomsFactoryRpt extends pdfRptMgr {
    public  JFrame jlFrame = null;
    public  JFileChooser jFileChooser;
    public  final int iOffice = 1;
    public  final String loc = "c:\\Cell Widths.pdf", sPeaceeXXX = "";
    private  HeaderFooter footer;
    private  HeaderFooter header;
    objMgr objMgr;
    jlRoomsFactoryRpt jlRoomsFactoryRpt;
    public jlRoomsFactoryRpt() {
        objMgr = new objMgr();
        jlRoomsFactoryRpt = new jlRoomsFactoryRpt();
        try {
            jbInit();
        } catch (Exception e) {
            Logger.getLogger(jlRoomsFactoryRpt.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    private void jbInit() throws Exception {
    }

    public  void setFooter(Document doc) {
        if (footer == null) {
            try {
                if (true){ //---WEB uncomment >>>>> jlRoomsFactory.getAppType() == jlRoomsFactoryObj.connAsSingleTryBy) {
                    header = new HeaderFooter(
                            new Phrase("JLRooms - Group Travel Itinerary Software", FontFactory.getFont(FontFactory.HELVETICA, 8, Font.ITALIC)), false);
                    header.setBorder(Rectangle.NO_BORDER);
                    header.setAlignment(Element.ALIGN_CENTER);
                    footer = new HeaderFooter(
                            new Phrase("www.JLRooms.com Pg #", FontFactory.getFont(FontFactory.HELVETICA, 8, Font.ITALIC)), true);
                    footer.setBorder(Rectangle.NO_BORDER);
                    footer.setAlignment(Element.ALIGN_CENTER);
                } else {
                    header = null;
                    footer = new HeaderFooter(
                            new Phrase("Pg #", FontFactory.getFont(FontFactory.HELVETICA, 8, Font.ITALIC)), true);
                    footer.setBorder(Rectangle.NO_BORDER);
                    footer.setAlignment(Element.ALIGN_CENTER);
                }


            } catch (Exception e) {
                Logger.getLogger(jlRoomsFactoryRpt.class.getName()).log(Level.SEVERE, null, e);
            }

        }

        if (header != null) {
            doc.setHeader(header);
        }
        doc.setFooter(footer);

    }

    public  void setDate(Document doc) throws Exception {
        Calendar.getInstance().getTime();
        Paragraph pg = new Paragraph(objMgr.getDateFormat(Calendar.getInstance().getTime()));
        pg.setAlignment(Paragraph.ALIGN_LEFT);
        pg.setFont(FontFactory.getFont(FontFactory.HELVETICA, 8, Font.ITALIC));

        try {
            doc.add(pg);

        } catch (Exception e) {
            throw e;
        }
    }

    public  void getOffice(Document doc) throws Exception {
        try {
            getOffice(doc, true, 0, "");
        } catch (Exception e) {
            throw e;
        }
    }

    public  void getOffice(Document doc, boolean newLine) throws Exception {

        try {
            getOffice(doc, newLine, 0, "");
        } catch (Exception e) {
            throw e;
        }
    }

    public  void getOffice(Document doc, String value) throws Exception {
        Paragraph pg = null;
        try {
            pg = new Paragraph(value + "\n");
            pg.setAlignment(Paragraph.ALIGN_CENTER);
            pg.setFont(FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD));
            doc.add(pg);
        } catch (Exception e) {
            throw e;
        }
    }

    public  void getOffice(Document doc, boolean newLine, int spon, String header) throws Exception {

        vendorBean vendorBean =new vendorBean(); //---WEB uncomment >>>>>  = jlRoomsFactoryObj.get_SysLocObj().getVendorBean(jlRoomsFactoryRpt.iOffice);
        Paragraph pg = null;
        String o = "", x = "", line = newLine ? "\n" : " ";
        //---WEB uncomment >>>>> if (vendorBean == null) {
        //---WEB uncomment >>>>>     vendorBean = new vendorBean();
        //---WEB uncomment >>>>> }
        try {
            pg = new Paragraph(vendorBean.getVendorName());
            pg.setAlignment(Paragraph.ALIGN_CENTER);
            pg.setFont(FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD));
            doc.add(pg);
            x = vendorBean.getDisplayAddress();
            if (x != null) {
                o += x + line;
            }
            x = vendorBean.getDisplayTown();
            if (x != null) {
                o += x + line;
            }
            x = vendorBean.getDisplayPhone();
            if (x != null) {
                o += x + line;
            }
            if (vendorBean.getEMail() != null && vendorBean.getEMail().length() > 0) {
                o += vendorBean.getEMail() + line;
            }



            if (spon > 0) {
                sponsorBean sponsorBean;//---WEB uncomment >>>>>  = jlRoomsFactoryObj.get_sponsorObj().sponsorGetEvent(spon);
                //---WEB uncomment >>>>> o += "\n" + sponsorBean.getSponsorDesc() + " " + header;
            } else if (header != null) {
                o += "\n" + header;
            }
            if (!newLine) {
                o += "\n";
            }
            pg = new Paragraph(o + line);
            pg.setAlignment(Paragraph.ALIGN_CENTER);
            pg.setFont(FontFactory.getFont(FontFactory.HELVETICA, 10));
            doc.add(pg);
        } catch (Exception e) {
            throw e;
        }
        //setFooter(doc);

    }

    public  int crateCustomerPdf(int spon, int cust, String location) {
        int ok = 0;
        Document document = new Document(PageSize.A4, 30, 30, 30, 30);
        try {

            PdfWriter.getInstance(document, new FileOutputStream(location));
            jlRoomsFactoryRpt.setFooter(document);
            document.open();

            //---WEB uncomment >>>>> CustomerEventOverViewPDF.getCustomerInfo(spon, cust, document);

            //---WEB uncomment >>>>> htmlPaymentCustVendorPDF.getEventPayBookDetail(spon, cust, true, document);

            //---WEB uncomment >>>>> CustomerEventOverViewPDF.getCustomerOverView(spon, cust, document);
            //---WEB uncomment >>>>> RoommateEventOverViewPDF.getRoommate(spon, cust, document);
            //---WEB uncomment >>>>> CustomerFlightEventOverViewPDF.getFlighAllPdfTable(document, spon, cust);

        } catch (java.io.FileNotFoundException x) {
            JOptionPane.showMessageDialog(null, "WARNING: FILE IS CURRENTLY OPEN");
            ok = -1;
        } catch (Exception e) {
            Logger.getLogger(jlRoomsFactoryRpt.class.getName()).log(Level.SEVERE, null, e);
            ok = -2;
        } finally {
            try {
                document.close();
                if (ok == 0) {
                    pdfFileOpen(location);
                }
            } catch (Exception e) {
                Logger.getLogger(jlRoomsFactoryRpt.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return ok;

    }

    public  int createEventPDF(int spon, String location) {
        int ok = 0;
        Document document = new Document(PageSize.A4, 30, 30, 30, 30);
        try {

            PdfWriter.getInstance(document, new FileOutputStream(location));
            jlRoomsFactoryRpt.setFooter(document);
            document.open();
            //---WEB uncomment >>>>> jlRoomsFactoryObj.get_sponsorObj();
            sponsorBean sponsorBean; //---WEB uncomment >>>>>  = jlRoomsFactoryObj.get_sponsorObj().sponsorGetEvent(spon);

            //---WEB uncomment >>>>> EventOverViewHtmlPDF.genOverViewRptXX(sponsorBean, document);
            //---WEB uncomment >>>>> htmlPaymentEventPDF.eventPaymentSummary(sponsorBean.getSponsorId(), document);
            //---WEB uncomment >>>>> PaymentOverviewPDF.getEventPaymentOverview(spon, document);
        } catch (java.io.FileNotFoundException x) {

            JOptionPane.showMessageDialog(null, "WARNING: FILE IS CURRENTLY OPEN");
            ok = -1;
        } catch (Exception e) {
            Logger.getLogger(jlRoomsFactoryRpt.class.getName()).log(Level.SEVERE, null, e);
            ok = -2;
        } finally {
            try {
                document.close();
                if (ok == 0) {
                    pdfFileOpen(location);
                }
            } catch (Exception e) {
                Logger.getLogger(jlRoomsFactoryRpt.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return ok;

    }

    public  int create1StNightPDFX(int spon, String location) {
        int ok = 0;
        Document document = new Document(PageSize.A4, 30, 30, 30, 30);

        try {

            PdfWriter.getInstance(document, new FileOutputStream(location));
            jlRoomsFactoryRpt.setFooter(document);
            document.open();
            // rptFirstNightPDF.genSummaryRpt(1, spon, document, new rptFirstNightBean(false, 60),rptFirstNightSql.WHERE_SPONSOR);
            //  rptFirstNightPDF.genSummaryRpt(2, spon, document, new rptFirstNightBean(),rptFirstNightSql.WHERE_SPONSOR);
            //  rptFirstNightPDF.genSummaryRpt(3, spon, document, new rptFirstNightBean(false, 60),rptFirstNightSql.WHERE_SPONSOR);
            //  rptFirstNightPDF.genSummaryRpt(4, spon, document, new rptFirstNightBean(),rptFirstNightSql.WHERE_SPONSOR);

        } catch (java.io.FileNotFoundException x) {

            JOptionPane.showMessageDialog(null, "WARNING: FILE IS CURRENTLY OPEN");
            ok = -1;
        } catch (Exception e) {
            Logger.getLogger(jlRoomsFactoryRpt.class.getName()).log(Level.SEVERE, null, e);
            ok = -2;
        } finally {
            try {
                document.close();
                if (ok == 0) {
                    pdfFileOpen(location);
                }
            } catch (Exception e) {
                Logger.getLogger(jlRoomsFactoryRpt.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return ok;

    }

    public  int createSummaryPDFXX(int spon, String location) {
        int ok = 0;
       
        Document document = new Document(PageSize.A4, 30, 30, 30, 30);
        try {


            PdfWriter.getInstance(document, new FileOutputStream(location));
            jlRoomsFactoryRpt.setFooter(document);

            document.open();


        } catch (java.io.FileNotFoundException x) {

            JOptionPane.showMessageDialog(null, "WARNING: FILE IS CURRENTLY OPEN");
            ok = -1;
        } catch (Exception e) {
            Logger.getLogger(jlRoomsFactoryRpt.class.getName()).log(Level.SEVERE, null, e);
            ok = -2;
        } finally {
            try {
                document.close();
                if (ok == 0) {
                    pdfFileOpen(location);
                }
            } catch (Exception e) {
                Logger.getLogger(jlRoomsFactoryRpt.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return ok;

    }

    public  String getPdfFileName(String name, String path) throws Exception {
        if (path == null) {
            path = "";
        }
        cleanPath(name);
        if (jFileChooser == null) {
            jFileChooser = new JFileChooser(createDirectory(path)) {
            }; //new File("demo.pdf")
            jFileChooser.addChoosableFileFilter(new PdfFileFilter());
        }
        try {

            jFileChooser.setSelectedFile(new File(name));
            int i = jFileChooser.showSaveDialog(jlFrame);
            if (i != JFileChooser.APPROVE_OPTION) {
                return null;
            }
            return jFileChooser.getSelectedFile().getAbsolutePath();
        } catch (Exception e) {
            throw e;
        }
    }

    public  String cleanPath(String x) {
        if (x == null) {
            x = "";
        }
        x = x.replace(".", "_");
        x = x.replace(" ", "");
        x = x.replace(",", "_");
        x = x.trim();
        return x;
    }

    public  String createDirectory(String path) {
        String str = "JLRoomsPdfFiles/"; //---WEB uncomment >>>>>  + jlRoomsFactory.getDate(Calendar.getInstance().getTime(), "yyyy/MMMM/EEE MMM dd yyyy");
        if (path == null) {
            path = "";
        }
        path = cleanPath(path);



        if (path.length() > 0) {
            str += "/" + path;
        }


        if (!new File(str).isDirectory()) {
            boolean status = new File(str).mkdirs();
            if (!status) {
                return "";
            }
        }

        return str + "/";
    }

    public  void pdfFileOpen(String file) {
        pdfFileOpen(file, true);
    }

    public  void pdfFileOpen(String file, boolean showOptionPane) {
        try {
            if (file == null) {
                return;
            }
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + file);
            if (showOptionPane) {
                JOptionPane.showMessageDialog(null, file + " Created");
            }
        } catch (java.io.IOException i) {
            i.printStackTrace();
        } catch (Exception e) {
            Logger.getLogger(jlRoomsFactoryRpt.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public  int test() {
        int ok = 0;



        Document document = new Document(PageSize.A4.rotate(), 30, 30, 30, 30);
        try {
            getPdfFileName("demo.pdf", "");

            PdfWriter.getInstance(document, new FileOutputStream("c:\test.pdf"));
            jlRoomsFactoryRpt.setFooter(document);
            document.open();
            // rptFirstNightPDF.getSummaryOverviewRpt(1,spon,document,new rptFirstNightBean(),rptFirstNightSql.WHERE_SPONSOR);
            //rptFirstNightPDF.getSummaryOverviewRpt(3,spon,document,new rptFirstNightBean(),rptFirstNightSql.WHERE_SPONSOR);
            // rptFirstNightPDF.getSummaryOverviewRpt(2,spon,document,new rptFirstNightBean(),rptFirstNightSql.WHERE_SPONSOR);
            // rptFirstNightPDF.getSummaryOverviewRpt(4,spon,document,new rptFirstNightBean(),rptFirstNightSql.WHERE_SPONSOR);
        } catch (java.io.FileNotFoundException x) {

            JOptionPane.showMessageDialog(null, "WARNING: FILE IS CURRENTLY OPEN");
            ok = -1;
        } catch (Exception e) {
            Logger.getLogger(jlRoomsFactoryRpt.class.getName()).log(Level.SEVERE, null, e);
            ok = -2;
        } finally {
            try {
                document.close();
                if (ok == 0) {
                    pdfFileOpen("c:\test.pdf");
                }
            } catch (Exception e) {
                Logger.getLogger(jlRoomsFactoryRpt.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return ok;

    }

    public  void main(String[] args) {
        String str;
        Calendar start = Calendar.getInstance(),
                end = Calendar.getInstance();
        start.add(Calendar.DATE, -120);
        
        str = createDirectory("") + "test.pdf";
       
        Document document = new Document(PageSize.A4.rotate(), 20, 20, 20, 20);
        try {
            PdfWriter.getInstance(document, new FileOutputStream(str));
            jlRoomsFactoryRpt.setFooter(document);
            document.open();
            
            jlRoomsFactoryRpt.getOffice(document);
            //---WEB uncomment >>>>> jlRooms.rpt.RoommateEventOverViewPDF.getRoommate(188, 1086, document);
            
        } catch (Exception e) {
            Logger.getLogger(jlRoomsFactoryRpt.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                document.close();
                pdfFileOpen(str, false);
            } catch (Exception e) {
                Logger.getLogger(jlRoomsFactoryRpt.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        //---WEB uncomment >>>>> jlRoomsFactoryObj.shutDown();
        System.exit(1);

    }
}

class PdfFileFilter extends javax.swing.filechooser.FileFilter {

    public boolean accept(File f) {
        return f.isDirectory() || f.getName().toLowerCase().endsWith(".pdf");
    }

    public String getDescription() {
        return "*.pdf";
    }
}
