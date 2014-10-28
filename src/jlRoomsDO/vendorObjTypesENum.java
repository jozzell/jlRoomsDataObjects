/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsDO;

import jlRoomsCommon._beans.sponsorBean;

/**
 *
 * @author lmeans
 */
public enum vendorObjTypesENum {
    BUS(-14, "Bus Tickets", "Tickeets","Cost","Bus"),
    VENDOR_BUS(-14,"Bus Tickets", "Tickeets","Cost","Bus"),
    
    RAILROAD(-32, "RailRoad Tickets", "Seat Class","Cost","Train"),
    VENDOR_RAILROAD(-32,"RailRoad Tickets", "Seat Class","Cost","Train"),
    
    CRUISE(-31, "Cruise Cabins", "Cabin Size","Cost","Boat"),
    VENDOR_CRUISES(-31,"Cruise Cabins", "Cabin Size","Cost","Boat"),
    
    AIRLINE(-12, "AirLine Tickets", "Seat Class","Cost","Flight"),
    VENDOR_AIRLINES(-12,"AirLine Tickets", "Seat Class","Cost","Flight"),
    
    TICKETS(33, "Tickets", "Section/Location","Cost","Tickets"),
    VENDOR_TICKETS(33,"Tickets","Tickets","Tickets","Tickets"),
    
    CARRENTAL(-13, "Cars Rentals", "Car Size","Cost/Day",""),
    VENDOR_CAR_RENTAL(-13,"Cars Rentals", "Car Size","Cost/Day",""),
    
    HOTEL(-2, "Hotel Rooms", "Room Type","Cost/Night",""),
    VENDOR_HOTEL(-2,"Hotel Rooms", "Room Type","Cost/Night",""),
    
    SPONDOR(-3, "", "","Cost",""),
    VENDOR_SPONSOR(-3,"Tickets","Tickets","Tickets",""),
    DEFAULT(0, "Category", "Category","Cost",""),
    
    VENDOR_BOAT(-15,"Boat Tickets","Boat","Boat","Boat");
    private int type;
    private String roomDesc, maintnaceType,costDesc,subRmDesc;

    private vendorObjTypesENum(int type, String roomDesc, String maintnaceType,String costDesc,String subRmDesc) {
        this.type = type;
        this.roomDesc = roomDesc;
        this.maintnaceType = maintnaceType;
        this.costDesc = costDesc;
        this.subRmDesc = subRmDesc;
        
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
        return vendor_type == VENDOR_AIRLINES.getType() || 
                vendor_type == VENDOR_RAILROAD.getType() || 
                vendor_type == VENDOR_BUS.getType() || 
                vendor_type == VENDOR_BOAT.getType();
    }
    public double getProcFee(sponsorBean b) {
        double amt = 0;
        if (this == VENDOR_HOTEL){
            amt = b.getProcFeeHotel();
        } else if (this == VENDOR_CAR_RENTAL){
             amt = b.getProcFeeCar();
        } else if (this == VENDOR_TICKETS){
            amt = b.getProcFeeTicket();
        } else if (this == VENDOR_AIRLINES){
            amt = b.getProcFeeAir();
        } else if (this == VENDOR_CRUISES){
            amt = b.getProcFeeCruise();
        } else if (this == VENDOR_RAILROAD){
             amt = b.getProcFeeTrain();
        } else if (this ==VENDOR_BUS){
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
