/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsCommon.rpt;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import jlRoomsCommon._beans.rptBeanColumesAmtList;
import jlRoomsCommon._objects.jlRoomsDbConnIinterface;
import jlRoomsCommon.rpt.db.rptviewObj;
import jlRoomsCommon.rpt.db.rptviewSql;
import obj.db.v1.dbMgrInterface;
import sun.jdbc.rowset.CachedRowSet;

/**
 *
 * @author lmeans
 */
public class rptView_payment_booked_type_summary implements Serializable{
    rptView_payment_booked_type_summarySql rptView_payment_booked_type_summarySql;
    rptView_payment_booked_type_summaryAry rptView_payment_booked_type_summaryAry;
    private jlRoomsDbConnIinterface jlRoomsFactoryDB;
    public rptView_payment_booked_type_summary(){
        rptView_payment_booked_type_summarySql = new rptView_payment_booked_type_summarySql();
        rptView_payment_booked_type_summaryAry = new rptView_payment_booked_type_summaryAry();
    }
    public rptView_payment_booked_type_summary(jlRoomsDbConnIinterface x) {
        this();
        this.jlRoomsFactoryDB = x;

    }

     //private CachedRowSet rptview_payment_booked_type_summary(int spon) {
     //   return crs_rptview_payment_booked_type_summary(spon,null,this.jlRoomsFactoryDB);
   // }
    private  CachedRowSet crs_rptview_payment_booked_type_summary(int spon,String email,dbMgrInterface db) {
        CachedRowSet rs = null;
        try {
            rs = db.getCachedRowSet(rptView_payment_booked_type_summarySql.rptView_payment_booked_type_summary_V2(email), new Object[]{new Integer(spon)});
        } catch (Exception e) {
            Logger.getLogger(rptviewObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return rs;
    }
    public rptBeanColumesAmtList rptview_payment_booked_type_summary(int spon,String email,dbMgrInterface db) {
        return rptView_payment_booked_type_summaryAry.get_rptview_payment_type(
                crs_rptview_payment_booked_type_summary(spon,email,db));
    }

   }

