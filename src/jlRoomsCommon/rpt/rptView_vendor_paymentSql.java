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
public class rptView_vendor_paymentSql extends objMgr {
    private String test000="";
    public  String rptView_Vendor_DateRange(String web){
        return "select * from rptView_Vendor_payment "+
            " where db_timestamp >= ? and db_timestamp < ? "+
                 (web == null ? "":this.getEMailKeyEquals(web))+
                " order by 5,2";
    }
    public  String rptView_VendorID_DateRange(String web){
        return "select * from rptView_Vendor_payment "+
            " where db_timestamp >= ? and db_timestamp < ? and vendor_id = ?"+
                 (web == null ? "":this.getEMailKeyEquals(web))+
                " order by 5,2";
    }
    // *****************************************************************
     public  String rptView_Vendor_Sponsor_payment(String web){
        return "select * from rptView_Vendor_payment "+
            " where sponsor_id in (?) and vendor_id in (?) "+
                 (web == null ? "":this.getEMailKeyEquals(web))+
                " order by 9 desc";
    }
    
    public  String rptView_Vendor_Sponsor_DateRange_payment(String web){
        return "select * from rptView_Vendor_payment "+
            " where sponsor_id in (?) and vendor_id in (?) and db_timestamp >= ? and db_timestamp < ? "+
                 (web == null ? "":this.getEMailKeyEquals(web))+
                " order by 9";
    }
    public  String rptView_Vendor_spondor_payment_daterange(String web){
        return "select * from rptView_Vendor_payment "+
            " where sponsor_id in (?) and db_timestamp >= ? and db_timestamp < ? "+
                 (web == null ? "":this.getEMailKeyEquals(web))+
                " order by 9";
    }
    public  String rptView_Vendor_payment_sponsor(String web){
        return  "select * from rptView_Vendor_payment "+
            " where sponsor_id in (?) "+
                (web == null ? "":this.getEMailKeyEquals(web))+
                " order by 9 desc";
    }
}
