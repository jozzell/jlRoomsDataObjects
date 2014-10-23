/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsCommon.rpt;

import jlRoomsCommon.objMgr;

/**
 *
 * @author lmeans
 */
public class rptView_customer_payment_summarySql extends objMgr {
    private String test000="";
    public  String rptView_customer_payment_summaryZZZ(String web) {
        //#1)order  #2)cust #3)spon #4)cust_room_type #5)name #6)amt #7)" " #8)last_actity #9) booking #10)vendor #11)E-mail
        return "select * from rptView_customer_payment_summary "
                + " where sponsor_id in (?) " + (web == null ? "" : this.getEMailKeyEquals(web))
                + " order by 5,2,1 ";
    }

    

    public  String rptView_customer_payment_summary_sponsor(String web) {
        return "select * from rptView_customer_payment_summary "
                + " where sponsor_id in (?)  "
                + (web == null ? "" : this.getEMailKeyEquals(web))
                + " order by  5,2,1";
    }

    public  String rptView_customer_payment_summary_sponsor_customer(String web) {
        return "select * from rptView_customer_payment_summary "
                + " where sponsor_id in (?) and cust_id in ( ?) "
                + (web == null ? "" : this.getEMailKeyEquals(web))
                + " order by  5,2,1";
    }

    
    // ======================================================================
    public  String rptView_customer_payment_summary_dateRange(String web) {
        return "select * from rptView_customer_payment_summary "
                + " where db_timestamp >= ? and db_timestamp <= ? "
                + (web == null ? "" : this.getEMailKeyEquals(web))
                + " order by  5,2,1";

    }
    public  String rptView_customer_payment_summary_cust_date(String web) {
        return "select * from rptView_customer_payment_summary "
                + " where cust_id in ( ?) and db_timestamp >= ? and db_timestamp <= ? "
                + (web == null ? "" : this.getEMailKeyEquals(web))
                + " order by  5,2,1";
    }
}
