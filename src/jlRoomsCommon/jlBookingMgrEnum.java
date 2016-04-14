/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsCommon;

/**
 *
 * @author lmeans
 */

public enum jlBookingMgrEnum {
    FORWARD_SPONSOR(0), 
    FOWARD_CLIENT(1),
    FOWARD_CLIENT_START(-1),
    FOWARD_VENDOR(2),
    FOWARD_VENDOR_START(-2),
    FOWARD_BLOCK(3),
    JLROOMS_TRYBUY(1),
            JLROOMS_EVENT(1),
            JLROOMS_UNLIMITED(2);
    private final int type;
    
    
    private jlBookingMgrEnum(int i) {
        type = i;
    }
    public int getType(){
        return type;
    }
    /*
    public jlBookingMgrEnum getType(int i){
        switch (i){
            case iFORWARD_SPONSOR:
                return FORWARD_SPONSOR;
            case iFOWARD_CLIENT:
                return FOWARD_CLIENT;
            case iFOWARD_CLIENT_START:
                return FOWARD_CLIENT_START;
            case iFOWARD_VENDOR:
                return FOWARD_VENDOR;
            case iFOWARD_VENDOR_START:
                return FOWARD_VENDOR_START;
            default:
                return FOWARD_BLOCK;
        }
    
    }
    */
}
