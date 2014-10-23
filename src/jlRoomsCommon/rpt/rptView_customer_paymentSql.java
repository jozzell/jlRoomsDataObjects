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
public class rptView_customer_paymentSql extends objMgr  {
    private String test000="";
    
    
   
    // ==============================================================
   public  String rptView_customer_payment_sponsor(String web){
       return "select * from rptView_customer_payment "+
            " where sponsor_id in (?)  "+
               (web == null ? "":this.getEMailKeyEquals(web))+
               " order by 8 desc,5";
   }
   public  String rptView_customer_payment_sponsor_customer(String web){
       return "select * from rptView_customer_payment "+
            " where sponsor_id in (?) and cust_id in ( ?) "+
               (web == null ? "":this.getEMailKeyEquals(web))+
               " order by 8 desc,5";
   }
    // ==============================================================
   public  String rptView_customer_payment_dateRange(String web){
    return  "select * from rptView_customer_payment "+
            " where db_timestamp >= ? and db_timestamp <= ? "+
            (web == null ? "":this.getEMailKeyEquals(web))+
            " order by 8 desc,5";
             
   }     
  
    public  String rptView_customer_payment(String web){
       return "select * from rptView_customer_payment "+
            " where cust_id in ( ?) and db_timestamp >= ? and db_timestamp <= ? "+
               (web == null ? "":this.getEMailKeyEquals(web))+
               " order by 8 desc,5";
   }
}
