/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsCommon;

import jlRoomsCommon._beans.sponsorBean;

/**
 *
 * @author lmeans
 */
public enum vendorObjTypesENum {
    BUS(-14, "Bus Tickets", "Tickeets","Cost","Bus","Bus Route"),
    //VENDOR_BUS(-14,"Bus Tickets", "Tickeets","Cost","Bus","Bus Route"),
    
    RAILROAD(-32, "RailRoad Tickets", "Seat Class","Cost","Train #","Train Route"),
    //VENDOR_RAILROAD(-32,"RailRoad Tickets", "Seat Class","Cost","Train","Train Route"),
    
    CRUISE(-31, "Cruise Cabins", "Cabin Size","Cost","Cruise Shipname","Cruise"),
    //VENDOR_CRUISES(-31,"Cruise Cabins", "Cabin Size","Cost","Cruise","Cruise"),
    
    AIRLINE(-12, "AirLine Tickets", "Seat Class","Cost","Flight #","Flight"),
    //VENDOR_AIRLINES(-12,"AirLine Tickets", "Seat Class","Cost","Flight","Flight"),
    
    TICKETS(-33, "Tickets", "Section/Location","Cost","Tickets","Tickets to Purchase "),
    //VENDOR_TICKETS(-33,"Tickets","Tickets","Tickets","Tickets","tickets to purchase "),
    
    
    merchandise(-34, "Merchandise", "Merchandise","Cost","Merchandise","Merchandise to purchase"),
    //merchandise_TICKETS(-34,"Merchandise","Merchandise","Merchandise","Merchandise","Merchandise to purchase"),
    
    
    
    CARRENTAL(-13, "Cars Rentals", "Car Size","Cost/Day","","Cars Rentals"),
    //VENDOR_CAR_RENTAL(-13,"Cars Rentals", "Car Size","Cost/Day","","Cars Rentals"),
    
    HOTEL(-2, "Hotel Rooms", "Room Type","Cost/Night","Rooms","Hotel Rooms"),
    //VENDOR_HOTEL(-2,"Hotel Rooms", "Room Type","Cost/Night","","Hotel Rooms"),
    
    SPONDOR(-3, "", "","Cost","",""),
    //VENDOR_SPONSOR(-3,"Tickets","Tickets","Tickets","",""),
    DEFAULT(0, "Category", "Category","Cost","",""),
    
    VENDOR_BOAT(-15,"Boat Tickets","Boat","Boat","Boat","Boats");
    private final int type;
    private final String roomDesc, maintnaceType,costDesc,subRmDesc,header;

    private vendorObjTypesENum(int type, String roomDesc, String maintnaceType,String costDesc,String subRmDesc,String hdr) {
        this.type = type;
        this.roomDesc = roomDesc;
        this.maintnaceType = maintnaceType;
        this.costDesc = costDesc;
        this.subRmDesc = subRmDesc;
        this.header = hdr;
        
    }

    public String getCostDesc(){
        return this.costDesc;
    }
    public String getMaintnaceType() {
        return maintnaceType;
    }

    public String getRoomDesc() {
        return roomDesc;
    }
    public int getType(){
        return getVendorType();
    }
    public int getVendorType() {
        return type;
    }

    public  vendorObjTypesENum getENUM(int type) {
        switch (type) {
            case 0:
                return DEFAULT;
            case  -14:
                return BUS; //VENDOR_BUS = -14,
            case -32:
                return RAILROAD; //VENDOR_RAILROAD = -32,
            case -31:
                return CRUISE; //VENDOR_CRUISES = -31,
            case -12:
                return AIRLINE; //VENDOR_AIRLINES = -12,
            case -33:
                return TICKETS; //VENDOR_TICKETS = -33,
            case -13:
                return CARRENTAL; //VENDOR_CAR_RENTAL = -13,
            case -34:
                return merchandise;
                case -2:
                return HOTEL;
            default:
                return HOTEL;//VENDOR_HOTEL = -2,
            }
    }
    public  vendorObjTypesENum getENUMPicked(int type) {
        switch (type) {
            case 7:
                return VENDOR_BOAT;
                
            case  6:
                return BUS; //VENDOR_BUS = -14,
            case 5:
                return RAILROAD; //VENDOR_RAILROAD = -32,
            case 4:
                return CRUISE; //VENDOR_CRUISES = -31,
            case 3:
                return AIRLINE; //VENDOR_AIRLINES = -12,
            case 2:
                return TICKETS; //VENDOR_TICKETS = -33,
            case 1:
                return CARRENTAL; //VENDOR_CAR_RENTAL = -13,
            case 0:
                return HOTEL;//VENDOR_HOTEL = -2,
            default:
                return DEFAULT;
            }
    }

    /**
     * @return the subRmDesc
     */
    public String getSubRmDesc() {
        return subRmDesc;
    }
    public boolean checkFlt(int vendor_type){
        return vendor_type == AIRLINE.getType() || 
                vendor_type == RAILROAD.getType() || 
                vendor_type == BUS.getType() || 
                vendor_type == VENDOR_BOAT.getType();
    }
    public double getProcFee(sponsorBean b) {
        double amt = 0;
        if (this == HOTEL){
            amt = b.getProcFeeHotel();
        } else if (this == CARRENTAL){
             amt = b.getProcFeeCar();
        } else if (this == TICKETS){
            amt = b.getProcFeeTicket();
        } else if (this == AIRLINE){
            amt = b.getProcFeeAir();
        } else if (this == CRUISE){
            amt = b.getProcFeeCruise();
        } else if (this == RAILROAD){
             amt = b.getProcFeeTrain();
        } else if (this ==BUS){
            amt = b.getProcBusFee();
        } else if (this == VENDOR_BOAT){
            amt = b.getProcBoatFee();
        } else {
             amt = b.getProcFee();
        }  
            
        if (amt == 0) {
            amt = b.getProcFee();
        }
        return amt * -1;
    }

    /**
     * @return the header
     */
    public String getHeader() {
        return header;
    }
}
/*
public  final int 
            VENDOR_BOAT = -15,
            VENDOR_BUS = -14,
            VENDOR_RAILROAD = -32,
            VENDOR_CRUISES = -31,
            VENDOR_AIRLINES = -12,
            VENDOR_TICKETS = -33,
            VENDOR_CAR_RENTAL = -13,
            VENDOR_HOTEL = -2,
            VENDOR_SPONSOR = -3;

public  final int 
            VENDOR_BOAT = -15,
            VENDOR_BUS = -14,
            VENDOR_RAILROAD = -32,
            VENDOR_CRUISES = -31,
            VENDOR_AIRLINES = -12,
            VENDOR_TICKETS = -33,
            VENDOR_CAR_RENTAL = -13,
            VENDOR_HOTEL = -2,
            VENDOR_SPONSOR = -3;
*/
