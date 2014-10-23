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
public class rptView_payment_booked_type_summarySql extends objMgr  {
    private String test000="";
     public  String rptView_payment_booked_type_summary_V2(String web){
      return "select * from rptview_payment_booked_type_summary "+
            " where sponsor_id in (?) "+(web == null ? "":this.getEMailKeyEquals(web))+
              " order by 1";
    }
}
