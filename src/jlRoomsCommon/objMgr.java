/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsCommon;

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
     public  String getEMailKeyEquals(String id) {
         if (id == null) return "";
         return " and email_key =  '" + id + "' ";
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

}
