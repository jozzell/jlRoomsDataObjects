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
public class rptView_payment_typeSql extends objMgr {
   private String test000="";
public  String rptview_payment_type_dateRange(String web) {
        return "select * from rptview_payment_type "
                + " where db_timestamp >= ? and db_timestamp < ? "
                + (web == null ? "" : this.getEMailKeyEquals(web))
                + " order by  7,3";

    }
// ==========================================================================
    public  String rptview_payment_type_cust_date(String web) {
        return "select * from rptview_payment_type "
                + " where cust_id in ( ?) and db_timestamp >= ? and db_timestamp < ? "
                + (web == null ? "" : this.getEMailKeyEquals(web))
                + " order by  5,2,1";
    }

    public  String rptview_payment_type_sponsor(String web) {
        return "select * from rptview_payment_type "
                + " where sponsor_id in (?)  "
                + (web == null ? "" : this.getEMailKeyEquals(web))
                + " order by  5,2,1";
    }

    public  String rptview_payment_type_sponsor_customer(String web) {
        return "select * from rptview_payment_type "
                + " where sponsor_id in (?) and cust_id in ( ?) "
                + (web == null ? "" : this.getEMailKeyEquals(web))
                + " order by  5,2,1";
    }

    
}
