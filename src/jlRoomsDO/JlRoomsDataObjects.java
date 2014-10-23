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

import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import jlRoomsCommon._beans.sponsorBean;

/**
 *
 * @author lmeans
 */
public class JlRoomsDataObjects implements Serializable {
    private static final String EMAIL_PATTERN = 
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static Hashtable hTable;
    public static final int 
            VENDOR_BOAT = -15,
            VENDOR_BUS = -14,
            VENDOR_RAILROAD = -32,
            VENDOR_CRUISES = -31,
            VENDOR_AIRLINES = -12,
            VENDOR_TICKETS = -33,
            VENDOR_CAR_RENTAL = -13,
            VENDOR_HOTEL = -2,
            VENDOR_SPONSOR = -3;

    
    /*
     * hTable.put(new Integer(JlRoomsDataObjects.VENDOR_SPONSOR - x), "Sponsor");
     hTable.put(new Integer(-2 - x), "Hotel");
     hTable.put(new Integer(-13 - x), "Car Rental");
     hTable.put(new Integer(-33 - x), "Tickets");
     hTable.put(new Integer(-12 - x), "Airlines");
     hTable.put(new Integer(-31 - x), "Cruises");
     hTable.put(new Integer(-32 - x), "RailRoad");
     hTable.put(new Integer(-14 - x), "Bus");
     hTable.put(new Integer(-15 - x), "Boat");
     */
    public static void docPgBreak( Document document,String summary){
        try {
            Paragraph pg = new Paragraph("\n\r"+(summary != null ? summary+"\n\r" : ""));
            
            pg.setAlignment(Paragraph.ALIGN_CENTER);
            pg.setFont(FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD));
        
            document.add(pg);
        } catch (DocumentException ex) {
            Logger.getLogger(JlRoomsDataObjects.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static final int PAYMENT_TYPE_VENDOR = -110,
            PAYMENT_TYPE_CUSTOMER = -111,
            PAYMENT_TYPE_SPONSOR = -112,
            SYS_PAYMENT = 10,
            SYS_PROC_FEE = 9;

    public static String getUnixName(String str) {
        return str.replaceAll("[^A-Za-z0-9]", "_");
    }
    public static boolean emailOk(String str){
        return Pattern.compile(EMAIL_PATTERN).matcher(str).matches();
    }
    public static final int idHotel = JlRoomsDataObjects.VENDOR_HOTEL,
            idCarRentel = JlRoomsDataObjects.VENDOR_CAR_RENTAL, idTickets = JlRoomsDataObjects.VENDOR_TICKETS, iAirLine = JlRoomsDataObjects.VENDOR_AIRLINES,
            iCruise = JlRoomsDataObjects.VENDOR_RAILROAD, iRailRoad = JlRoomsDataObjects.VENDOR_RAILROAD, iBus = JlRoomsDataObjects.VENDOR_BUS, iBoat = JlRoomsDataObjects.VENDOR_BOAT;
    public static final int[] typeAryValue = new int[]{
        idHotel,
        idCarRentel,
        idTickets,
        iAirLine, iCruise, iRailRoad, iBus, iBoat}; //,-19,-18,-20
    public static final String[] typeAryString = new String[]{
        "Hotel Rooms", "Cars Rentals",
        "Tickets",
        "AirLine Tickets", "Cruise Cabins", "RailRoad Tickets", "Bus Tickets", "Boat Tickets"}, //,"B&B","Rentals","Misc"
            accessAryString = new String[]{"None", "Staff", "Supervisor", "Manager", "Owner", "Administrator"};

    public static int daysBetween(Date d1, Date d2) {
        if (d1 == null || d2 == null) {
            return 0;
        }
        return (int) (Math.abs(d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }

    public static String getBookingTypeDesc(int pick) {
        for (int i = 0; i < typeAryValue.length; i++) {
            if (pick == typeAryValue[i]) {
                return typeAryString[i];
            }
        }
        return "";
    }

    public static int getBookingType(int pick) {
        return typeAryValue[pick];
    }

    public static Object getDate(java.util.Date d) {
        if (d == null) {
            System.out.println("DATE == NULL_DATE");
            return new NULL_DATE();
        }
        System.out.println("DATE == " + d.toString());

        return new java.sql.Date(d.getTime());
    }

    public static java.sql.Date getDateXXX(java.util.Date d) {
        if (d == null) {
            return new java.sql.Date(Calendar.getInstance().getTime().getTime());
        }
        return new java.sql.Date(d.getTime());
    }
    //NULL_DATE

    public static String getMaintLookup(int type) {

        if (getMaintLookup().get(new Integer(type)) == null) {
            System.out.println(type + "XXX missing XXX getMaintLookup(int type) = ");

            return type + "XXX missing XXX getMaintLookup(int type) = ";
        }
        return (String) getMaintLookup().get(new Integer(type));
    }

    public static String getAirTextSub(int type) {
        switch (type) {
            case JlRoomsDataObjects.VENDOR_BOAT:
                return "Boat";
            case JlRoomsDataObjects.VENDOR_RAILROAD:
                return "Train";
            case JlRoomsDataObjects.VENDOR_AIRLINES:
                return "Flight";
            case JlRoomsDataObjects.VENDOR_BUS:
                return "Bus";
            default:
                return "-";
        }
    }

    public static String getAirText(int type) {
        switch (type) {
            case JlRoomsDataObjects.VENDOR_CAR_RENTAL:
                return "Car Rentel";
            case JlRoomsDataObjects.VENDOR_HOTEL:
                return "Hotel";
            case JlRoomsDataObjects.VENDOR_BOAT:
                return "Boat";
            case JlRoomsDataObjects.VENDOR_RAILROAD:
                return "Train";
            case JlRoomsDataObjects.VENDOR_AIRLINES:
                return "Flight";
            case JlRoomsDataObjects.VENDOR_BUS:
                return "Bus";
            case JlRoomsDataObjects.VENDOR_CRUISES:
                return "Cruise";
            default:
                return "-";
        }
    }

    public static String getMaintVendor(int x) {
        return getMaintLookup(x - 100);
    }

    private static void getMaintVendor() {
        int x = 100;
        hTable.put(new Integer(JlRoomsDataObjects.VENDOR_SPONSOR - x), "Sponsor");
        hTable.put(new Integer(JlRoomsDataObjects.VENDOR_HOTEL - x), "Hotel");
        hTable.put(new Integer(JlRoomsDataObjects.VENDOR_CAR_RENTAL - x), "Car Rental");
        hTable.put(new Integer(JlRoomsDataObjects.VENDOR_TICKETS - x), "Tickets");
        hTable.put(new Integer(JlRoomsDataObjects.VENDOR_AIRLINES - x), "Airlines");
        hTable.put(new Integer(JlRoomsDataObjects.VENDOR_CRUISES - x), "Cruises");
        hTable.put(new Integer(JlRoomsDataObjects.VENDOR_RAILROAD - x), "RailRoad");
        hTable.put(new Integer(JlRoomsDataObjects.VENDOR_BUS - x), "Bus");
        hTable.put(new Integer(JlRoomsDataObjects.VENDOR_BOAT - x), "Boat");


    }

    public static String getMaintBlock(int x) {
        return getMaintLookup(x - 200);
    }

    private static void getMaintBlock() {
        int x = 200;

        hTable.put(new Integer(JlRoomsDataObjects.VENDOR_HOTEL - x), "Hotel Rooms");
        hTable.put(new Integer(JlRoomsDataObjects.VENDOR_CAR_RENTAL - x), "Car Rental");
        hTable.put(new Integer(JlRoomsDataObjects.VENDOR_TICKETS - x), "Tickets");
        hTable.put(new Integer(JlRoomsDataObjects.VENDOR_AIRLINES - x), "Airline Tickets");
        hTable.put(new Integer(JlRoomsDataObjects.VENDOR_RAILROAD - x), "Cruise Cabin");
        hTable.put(new Integer(JlRoomsDataObjects.VENDOR_CRUISES - x), "RailRoad Tickets");
        hTable.put(new Integer(JlRoomsDataObjects.VENDOR_BUS - x), "Bus Tickets");
        hTable.put(new Integer(JlRoomsDataObjects.VENDOR_BOAT - x), "Boat Tickets");

    }

    public static final String htmlItalic(String s) {
        return "<em>" + s + "</em>";
    }

    public static final String htmlBold(String s) {
        return "<b>" + s + "</b>";
    }

    public static boolean checkFlt(int vendor_type) {
        return vendor_type == JlRoomsDataObjects.VENDOR_AIRLINES || vendor_type == JlRoomsDataObjects.VENDOR_RAILROAD || vendor_type == JlRoomsDataObjects.VENDOR_BUS || vendor_type == JlRoomsDataObjects.VENDOR_BOAT;
    }
    
    public static double getProcFee(sponsorBean b, int type) {
        double amt;
        switch (type) {
            case JlRoomsDataObjects.VENDOR_HOTEL:
                amt = b.getProcFeeHotel();
                break;
            case JlRoomsDataObjects.VENDOR_CAR_RENTAL:
                amt = b.getProcFeeCar();
                break;
            case JlRoomsDataObjects.VENDOR_TICKETS:
                amt = b.getProcFeeTicket();
                break;
            case JlRoomsDataObjects.VENDOR_AIRLINES:
                amt = b.getProcFeeAir();
                break;
            case JlRoomsDataObjects.VENDOR_CRUISES:
                amt = b.getProcFeeCruise();
                break;
            case JlRoomsDataObjects.VENDOR_RAILROAD:
                amt = b.getProcFeeTrain();
                break;
            case JlRoomsDataObjects.VENDOR_BUS:
                amt = b.getProcBusFee();
                break;
            case JlRoomsDataObjects.VENDOR_BOAT:
                amt = b.getProcBoatFee();
                break;
            default:
                amt = b.getProcFee();
        }
        if (amt == 0) {
            amt = b.getProcFee();
        }
        return amt * -1;
    }

    /**
     * @param args the command line arguments
     */
    public static String getTimeStampString() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(Calendar.getInstance().getTime());
    }

    public static void main(String[] args) {
        // TODO code application logic here
    }

    private static Hashtable getMaintLookup() {
        if (hTable == null) {
            hTable = new Hashtable();
            hTable.put(new Integer(JlRoomsDataObjects.VENDOR_SPONSOR), "Event Sponsor");
            hTable.put(new Integer(JlRoomsDataObjects.VENDOR_HOTEL), "Hotel Room Type");
            hTable.put(new Integer(JlRoomsDataObjects.VENDOR_CAR_RENTAL), "Car Size");
            hTable.put(new Integer(JlRoomsDataObjects.VENDOR_TICKETS), "Tickets Category");
            hTable.put(new Integer(JlRoomsDataObjects.VENDOR_AIRLINES), "Airline Seat Class");
            hTable.put(new Integer(JlRoomsDataObjects.VENDOR_CRUISES), "Cruise Cabin Types");
            hTable.put(new Integer(JlRoomsDataObjects.VENDOR_RAILROAD), "Travel Class");
            hTable.put(new Integer(JlRoomsDataObjects.VENDOR_BUS), "Bus Class");
            hTable.put(new Integer(JlRoomsDataObjects.VENDOR_BOAT), "Boat Class");
            hTable.put(new Integer(-17), "Payment Method");
            hTable.put(new Integer(10), "Payment Received Types");
            hTable.put(new Integer(-10), "Payment Paid Types");
            hTable.put(new Integer(9), "Deduction Fees Types");
            hTable.put(new Integer(-9), "Applied Fees Types");

            getMaintVendor();
            getMaintBlock();
        }
        return hTable;

    }
}
