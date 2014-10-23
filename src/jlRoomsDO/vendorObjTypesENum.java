/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsDO;

/**
 *
 * @author lmeans
 */
public enum vendorObjTypesENum {
    BUS(JlRoomsDataObjects.VENDOR_BUS, "Bus Tickets", "Tickeets","Cost"),
    RAILROAD(JlRoomsDataObjects.VENDOR_RAILROAD, "RailRoad Tickets", "Seat Class","Cost"),
    CRUISE(JlRoomsDataObjects.VENDOR_CRUISES, "Cruise Cabins", "Cabin Size","Cost"),
    AIRLINE(JlRoomsDataObjects.VENDOR_AIRLINES, "AirLine Tickets", "Seat Class","Cost"),
    TICKETS(JlRoomsDataObjects.VENDOR_TICKETS, "Tickets", "Section/Location","Cost"),
    CARRENTAL(JlRoomsDataObjects.VENDOR_CAR_RENTAL, "Cars Rentals", "Car Size","Cost/Day"),
    HOTEL(JlRoomsDataObjects.VENDOR_HOTEL, "Hotel Rooms", "Room Type","Cost/Night"),
    SPONDOR(JlRoomsDataObjects.VENDOR_SPONSOR, "", "","Cost"),
    DEFAULT(0, "Category", "Category","Cost");
    private int type;
    private String roomDesc, maintnaceType,costDesc;

    private vendorObjTypesENum(int type, String roomDesc, String maintnaceType,String costDesc) {
        this.type = type;
        this.roomDesc = roomDesc;
        this.maintnaceType = maintnaceType;
        this.costDesc = costDesc;
        
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

    public int getVendorType() {
        return type;
    }

    public static vendorObjTypesENum getENUM(int type) {
        switch (type) {
            case 0:
                return DEFAULT;
            case JlRoomsDataObjects.VENDOR_BUS:
                return BUS;
            case JlRoomsDataObjects.VENDOR_RAILROAD:
                return RAILROAD;
            case JlRoomsDataObjects.VENDOR_CRUISES:
                return CRUISE;
            case JlRoomsDataObjects.VENDOR_AIRLINES:
                return AIRLINE;
            case JlRoomsDataObjects.VENDOR_TICKETS:
                return TICKETS;
            case JlRoomsDataObjects.VENDOR_CAR_RENTAL:
                return CARRENTAL;
            default:
                return HOTEL;//VENDOR_HOTEL;
            }
    }
}
