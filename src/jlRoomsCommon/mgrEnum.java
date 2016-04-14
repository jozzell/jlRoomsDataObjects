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
public enum mgrEnum {
    Default(947),
    sysVendor_LookupType(1),
            PAYMENT_TYPE_VENDOR(-110),
            PAYMENT_TYPE_CUSTOMER(-111),
            PAYMENT_TYPE_SPONSOR(-112),
            SYS_PAYMENT(10),
            SYS_PROC_FEE(9);
    private int type;
   public final int
            iPAYMENT_TYPE_VENDOR=-110,
            iPAYMENT_TYPE_CUSTOMER=-111,
            iPAYMENT_TYPE_SPONSOR=-112,
            iSYS_PAYMENT=10,
            iSYS_PROC_FEE=9;
    private mgrEnum(int i){
        this.type = i;
    }
    public int getType(){
        return type;
    }
    public mgrEnum getENum(int type){
        switch(type){
           
            case 1:
                return sysVendor_LookupType;
            case -110:
                return PAYMENT_TYPE_VENDOR;
            case -111:
                return PAYMENT_TYPE_CUSTOMER;
            case -112:
                return PAYMENT_TYPE_SPONSOR;
            case 10:
                return SYS_PAYMENT;
            case 9:
                return SYS_PROC_FEE;
                
            default:
                return Default;
        }
    }
    
}
