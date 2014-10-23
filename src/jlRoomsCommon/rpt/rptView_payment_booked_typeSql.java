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
public class rptView_payment_booked_typeSql extends objMgr  {
    private String test000="";
    public  String rptview_payment_booked_type_daterange(String web) {
        return "select * from rptview_payment_booked_type "
                + " where db_timestamp >= ? and db_timestamp < ? "
                + (web == null ? "" : this.getEMailKeyEquals(web))
                + " order by 1 desc";
    }
    // ====================================================================
     
     public   String rptview_payment_booked_type_sponsor(String web) {
        return "select * from rptview_payment_booked_type "
                + " where sponsor_id in (?) "
                + (web == null ? "" : this.getEMailKeyEquals(web))
                + "order by 1 desc";
    }
      public    String rptview_payment_booked_type_sponsor_cust(String web) {
        return "select * from rptview_payment_booked_type "
                + " where sponsor_id in (?) and cust_id in (?) "
                + (web == null ? "" : this.getEMailKeyEquals(web))
                + "order by 1 desc";
    }
}
