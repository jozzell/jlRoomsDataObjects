/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jlRoomsCommon.rpt;

import jlRoomsCommon.objMgr;

/**
 *
 * @author lmeans
 */
public class rptView_payment_type_summarySql extends objMgr {
    private String test000="";
    public  String rptview_payment_type_summary_dateRange(String web) {
        return "select * from rptview_payment_type_summary  "
                + " where db_timestamp >= ? and db_timestamp < ? "
                + (web == null ? "" : this.getEMailKeyEquals(web))
                + " order by  7,3";

    }
}
