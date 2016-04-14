/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsCommon;

//---WEB uncomment >>>>> import jlRooms.rpt.pdf.CustomerFlightEventOverViewPDF;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author lmeans
 */
public class objMgr {
    public String chkDate(String str){
        if (str.contains("1969") || str.contains("1970")){
            return "";
        } 
        return str;
    }
     public  String getEMailKeyEquals(String id) {
         if (id == null) return "";
         return " and email_key =  '" + id + "' ";
    }
     public  String getEMailKeyEquals(String id,String pre) {
         if (id == null) return "";
         return " and "+pre+"email_key =  '" + id + "' ";
    }
    public    String getEMailKeyIn(String id) {
         if (id == null) return "";
         return " and email_key in  ('" + id + "') ";
    }
    public  String getEMailKeyStr(String id) {
        if (id == null) return "";
        return " '" + id.trim() + "' ";
    }
    public  String getDollarFormat(double d) {
        return NumberFormat.getCurrencyInstance().format(d);
    }
    
    public  String getTimeFormat(Timestamp d, String format) {
        return d == null ? "" : new SimpleDateFormat(format).format(d);
    }

    public  String getTimeFormat(Timestamp ts) {
        return getTimeFormat(ts, "hh:mm a");
    }


    // -------------------------------------------------------------------------
    public  String getTimeFormat(Time d, String format) {
        //jlRooms.jlRoomsFactory.getTimeFormat(d)
        //jlRooms.jlRoomsFactory.getDateFormat(d)
        //jlRooms.jlRoomsFactory.getDateFormatFull
        return d == null ? "" : new SimpleDateFormat(format).format(d);
    }

    public  String getTimeFormat(Time t) {
        return getTimeFormat(t, "hh:mm a");
    }
    // -------------------------------------------------------------------------

    public  String getTimeFormat(Date d, String format) {
        return d == null ? "" : new SimpleDateFormat(format).format(d);
    }

    public  String getTimeFormat(Date d) {

        return getTimeFormat(d, "hh:mm a");
    }
    // -------------------------------------------------------------------------

    public  String getDateFormat(Date d, String format) {
        if (d == null) {
            return "";
        }
        return d == null ? "" : new SimpleDateFormat(format).format(d);
    }

    // -------------------------------------------------------------------------
    public  String getDateFormat(Date d) {
        if (d == null) {
            return "";
        }
        return new SimpleDateFormat("EEE MMM dd yyyy").format(d);
    }
    // -------------------------------------------------------------------------

    public  String getDateFormatFull(Time t) {
        if (t == null) {
            return "";
        }
        return new SimpleDateFormat("EEE MMM dd yyyy hh:mm a").format(t);
    }

    public  String getDateFormatFull(Timestamp t) {
        if (t == null) {
            return "";
        }
        return new SimpleDateFormat("EEE MMM dd yyyy hh:mm a").format(t);
    }

    public  String getDate(Date d, String format) {
        return d == null ? "" : new SimpleDateFormat(format).format(d);
    }
    public void brk(Document doc){
        brk(doc,null);
    }
    public void brk(Document doc,String str){
        Paragraph pg;
        int i = 10;
        if(str == null){
            pg = new Paragraph("\n");
        } else {
            pg = new Paragraph(str+"\n");
            i = 16;
        }
        
         pg.setAlignment(Paragraph.ALIGN_CENTER);
         pg.setFont(FontFactory.getFont(FontFactory.HELVETICA, i, Font.BOLD));
       
        try {
            doc.add(pg);
        } catch (DocumentException ex) {
           ex.printStackTrace();
        }
    }
}
