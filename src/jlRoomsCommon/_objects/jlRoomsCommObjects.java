/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jlRoomsCommon._objects;

import jlRoomsCommon.block.db.blockObj;
import jlRoomsCommon.booking.sponsorCustomer.db.bookingObj;
import jlRoomsCommon.customer.db.custObj;
import jlRoomsCommon.customerPayment.db.customerPaymentObj;
import jlRoomsCommon.customerRoom.db.customerRmObj;
import jlRoomsCommon.flights.db.flightObj;
import jlRoomsCommon.login.loginObj;
import jlRoomsCommon.lookup.db.lookupObj;
import jlRoomsCommon.lookup_sys.db.lookupSysObj;
import jlRoomsCommon.pkg.pkgDbObj;
import jlRoomsCommon.rpt.db.event.EventOverViewObj;
import jlRoomsCommon.rpt.db.rptFirstNightObj;
import jlRoomsCommon.rpt.db.rptviewObj;
import jlRoomsCommon.rpt.db.sql.htmlPaymentCustVendorObj;
import jlRoomsCommon.sponsor.db.sponsorObj;
import jlRoomsCommon.sponsorHotel.db.sponsorHotelObj;
import jlRoomsCommon.sys.db.SysLocObj;
import jlRoomsCommon.vendor.db.vendorObj;

/**
 *
 * @author lmeans
 */
public abstract class jlRoomsCommObjects {
    // ********************************************************
    //->>2012-02-13 public abstract void connToDb(int type);
    // ********************************************************
    private  jlRoomsDbConnIinterface jlRoomsDbConn=null;
    public void setDbConnectionObj(jlRoomsDbConnIinterface x) {
        jlRoomsDbConn = x;
    }
    // ********************************************************
    private  blockObj blockObj;
    public synchronized   blockObj get_blockObj() {
        if (blockObj == null){
            blockObj = new blockObj(jlRoomsDbConn);
        }
        return blockObj;
    }
    // ********************************************************
    private   bookingObj bookingObj;
    public synchronized  bookingObj get_bookingObj() {
        if (bookingObj == null){
            bookingObj = new bookingObj(jlRoomsDbConn);
        }
        return bookingObj;
    }
    // ********************************************************
    private  custObj custObj;
    public synchronized  custObj get_custObj() {
        if (custObj == null){
            custObj = new custObj(jlRoomsDbConn);
        }
        return custObj;
    }
    // ********************************************************
    private  customerPaymentObj customerPaymentObj;
    public synchronized   customerPaymentObj get_customerPaymentObj() {
        if (customerPaymentObj == null){
            customerPaymentObj = new customerPaymentObj(jlRoomsDbConn);
        }
        return customerPaymentObj;
    }
    // ********************************************************
    private  customerRmObj customerRmObj;
    public synchronized  customerRmObj get_customerRmObj() {
        if (customerRmObj == null ){
            customerRmObj = new customerRmObj(jlRoomsDbConn);
        }
        return customerRmObj;
    }
    // ********************************************************
    private  flightObj flightObj;
    public synchronized  flightObj get_flightObj() {
        if (flightObj == null){
            flightObj = new flightObj(jlRoomsDbConn);
        }
        return flightObj;
    }

    // ********************************************************
    private  loginObj loginObj;
    public synchronized  loginObj get_loginObj() {
        if (loginObj == null){
            loginObj = new loginObj(jlRoomsDbConn);
        }
        return loginObj;
    }
    // ********************************************************
    private  lookupObj lookupObj;
    public synchronized  lookupObj get_lookupObj() {
        if (lookupObj == null){
            lookupObj = new lookupObj(jlRoomsDbConn);
        }
        return lookupObj;
    }
    // ********************************************************
    private  lookupSysObj sysObj;
    public synchronized  lookupSysObj get_sysObj() {
        if (sysObj == null){
            sysObj = new lookupSysObj(jlRoomsDbConn);
        }
        return sysObj;
    }
    // ********************************************************
    private  pkgDbObj pkgDbObj;
    public synchronized  pkgDbObj get_pkgDbObj() {
        if (pkgDbObj == null){
            pkgDbObj = new pkgDbObj(jlRoomsDbConn);
        }
        return pkgDbObj;
    }
    
    // ********************************************************
    private  rptFirstNightObj rptFirstNightObj;
    public synchronized  rptFirstNightObj get_rptFirstNightObj() {
        if (rptFirstNightObj == null){
            rptFirstNightObj = new rptFirstNightObj(jlRoomsDbConn);
        }
        return rptFirstNightObj;
    }
    // ********************************************************
    private  rptviewObj rptviewObj;
    public synchronized  rptviewObj get_rptviewObj() {
        if (rptviewObj == null){
            rptviewObj = new rptviewObj(jlRoomsDbConn);
        }
        return rptviewObj;
    }
    // ********************************************************
    private  EventOverViewObj EventOverViewObj;
    public synchronized  EventOverViewObj get_EventOverViewObj() {
        if (EventOverViewObj == null){
            EventOverViewObj = new EventOverViewObj(jlRoomsDbConn);
        }
        return EventOverViewObj;
    }
    // ********************************************************
    private  htmlPaymentCustVendorObj htmlPaymentCustVendorObj;
    public synchronized  htmlPaymentCustVendorObj get_htmlPaymentCustVendorObj() {
        if (htmlPaymentCustVendorObj == null){
            htmlPaymentCustVendorObj = new htmlPaymentCustVendorObj(jlRoomsDbConn);
        }
        return htmlPaymentCustVendorObj;
    }
    // ********************************************************
    private  sponsorObj sponsorObj;
    public synchronized  sponsorObj get_sponsorObj(){
        if (sponsorObj == null){
            sponsorObj = new sponsorObj(jlRoomsDbConn);
        }
        return sponsorObj;
    }
    // ********************************************************
    private  sponsorHotelObj sponsorHotelObj;
    public synchronized  sponsorHotelObj get_sponsorHotelObj() {
        if (sponsorHotelObj == null){
            sponsorHotelObj = new sponsorHotelObj(jlRoomsDbConn);
        }
        return sponsorHotelObj;
    }
    // ********************************************************
    private  SysLocObj SysLocObj;
    public synchronized  SysLocObj get_SysLocObj() {
        if (SysLocObj == null){
            SysLocObj = new SysLocObj(jlRoomsDbConn);
        }
        return SysLocObj;
    }
    // ********************************************************
    private  vendorObj vendorObj;
    public synchronized  vendorObj get_vendorObj() {
        if (vendorObj == null){
            vendorObj = new vendorObj(jlRoomsDbConn);
        }
        return vendorObj;
    }
    // ********************************************************

    // ********************************************************
    // ********************************************************
    // ********************************************************
    // ********************************************************
    // ********************************************************

}
