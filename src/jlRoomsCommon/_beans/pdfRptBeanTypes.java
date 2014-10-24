/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jlRoomsCommon._beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author lmeans
 */
@ManagedBean(name = "pdfRptBeanTypes")
public class pdfRptBeanTypes implements Serializable{
    public final int RPT_1ST_NIGHT = 0,  RPT_SUMMARY = 1,
            RPT = 3,RPT_BEAN=4,
            PAYMENT_OVERVIEW_CUSTOMER = 100,
            PAYMENT_OVERVIEW_VENDOR = 0,
            DEPOSIT_CUSTOMER = 101,
            DEPOSIT_VENDOR = 1,
            DETAIL_VENDOR = 102,
            DETAIL_VENDOR_SUMMARY = 2,
            DETAIL_CUSTOMER = 103,
            DETAIL_CUSTOMER_SUMMARY = 3,
            ROOMMATE_LISTING = 200,
            FIRST_NIGHT_DEPOSIT = 201,
            VENDOR_DAILY_CNT = 202,
            CUSTOMER_ARRIVAL = 203,
            rptView_Vendor_payment_summary = 204,
            rptView_Vendor_payment = 205,
            rptView_customer_payment = 206,
            rptView_customer_payment_summary = 207,
            rptview_payment_type = 208,
            rptview_payment_booking_type = 209,
            rptview_payment_type_summary = 210,
            rptview_payment_booked_type_summary = 211,
            rptview_event = 212,
            rptview_event_other = 213
            ;
    public pdfRptBeanTypes(){
        
    }
}
