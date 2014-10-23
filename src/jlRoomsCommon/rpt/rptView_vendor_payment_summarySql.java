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

   
public class rptView_vendor_payment_summarySql extends objMgr {
    private String test000="";
    public  String rptView_vendor_payment_summary_DateRange(String web){
        return "select * from rptView_Vendor_payment_summary "+
            " where db_timestamp >= ? and db_timestamp < ? "+
                 (web == null ? "":this.getEMailKeyEquals(web))+
                " order by 5,2";
    }
    public  String rptView_vendorID_payment_summary_DateRange(String web){
        return "select * from rptView_Vendor_payment_summary "+
            " where db_timestamp >= ? and db_timestamp < ? and vendor_id = ?"+
                 (web == null ? "":this.getEMailKeyEquals(web))+
                " order by 5,2";
    }
    // *****************************************************************
     public  String rptView_vendor_payment_summary_sponsor_vendor(String web){
        return "select * from rptView_Vendor_payment_summary "+
            " where sponsor_id in (?) and vendor_id in (?) "+
                 (web == null ? "":this.getEMailKeyEquals(web))+
                " order by 9 desc";
    }
    
    public  String rptView_vendor_payment_summary_vendor_DateRange(String web){
        return "select * from rptView_Vendor_payment_summary "+
            " where sponsor_id in (?) and vendor_id in (?) and db_timestamp >= ? and db_timestamp < ? "+
                 (web == null ? "":this.getEMailKeyEquals(web))+
                " order by 9";
    }
    public  String rptView_vendor_payment_summary_sponsor_daterange(String web){
        return "select * from rptView_Vendor_payment_summary "+
            " where sponsor_id in (?) and db_timestamp >= ? and db_timestamp < ? "+
                 (web == null ? "":this.getEMailKeyEquals(web))+
                " order by 9";
    }
    public  String rptView_vendor_payment_summary_sponsor(String web){
        return  "select * from rptView_Vendor_payment_summary "+
            " where sponsor_id in (?) "+
                (web == null ? "":this.getEMailKeyEquals(web))+
                " order by 9 desc";
    }
}


