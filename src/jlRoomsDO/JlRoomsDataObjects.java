/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsDO;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import cv.bisc.db.NULL_DATE;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author lmeans
 */
public class JlRoomsDataObjects implements Serializable {
    private  final String EMAIL_PATTERN = 
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    

    
   
    public  void docPgBreak( Document document,String summary){
        try {
            Paragraph pg = new Paragraph("\n\r"+(summary != null ? summary+"\n\r" : ""));
            
            pg.setAlignment(Paragraph.ALIGN_CENTER);
            pg.setFont(FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD));
        
            document.add(pg);
        } catch (DocumentException ex) {
            Logger.getLogger(JlRoomsDataObjects.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public  String getUnixName(String str) {
        return str.replaceAll("[^A-Za-z0-9]", "_");
    }
    public  boolean emailOk(String str){
        return Pattern.compile(EMAIL_PATTERN).matcher(str).matches();
    }
   
    public  final String[] accessAryString = new String[]{"None", "Staff", "Supervisor", "Manager", "Owner", "Administrator"};

    public  int daysBetween(Date d1, Date d2) {
        if (d1 == null || d2 == null) {
            return 0;
        }
        return (int) (Math.abs(d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }
    

    public  Object getDate(java.util.Date d) {
        if (d == null) {
            System.out.println("DATE == NULL_DATE");
            return new NULL_DATE();
        }
        System.out.println("DATE == " + d.toString());

        return new java.sql.Date(d.getTime());
    }

    public  java.sql.Date getDateXXX(java.util.Date d) {
        if (d == null) {
            return new java.sql.Date(Calendar.getInstance().getTime().getTime());
        }
        return new java.sql.Date(d.getTime());
    }

    public  final String htmlItalic(String s) {
        return "<em>" + s + "</em>";
    }

    public  final String htmlBold(String s) {
        return "<b>" + s + "</b>";
    }


    /**
     * @param args the command line arguments
     */
    public  String getTimeStampString() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(Calendar.getInstance().getTime());
    }

    public  static void main(String[] args) {
        // TODO code application logic here
    }
    
}
