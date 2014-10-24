/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsCommon.rpt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import jlRoomsCommon._beans.custVendorPaymentBean;
import jlRoomsCommon.rpt.db.sql.htmlPaymentCustVendorObj;
import jlRoomsCommon.rpt.db.sql.htmlPaymentCustVendorSql;
import obj.db.v1.dbMgrInterface;
import obj.reusableObj;
import sun.jdbc.rowset.CachedRowSet;

/**
 *
 * @author lmeans
 */
public class rpt_Event implements Serializable{
    htmlPaymentCustVendorSql htmlPaymentCustVendorSql;
    double amt;
    String email;
    //List<rpt_EventPaymentDetailBookedBean> list;
    public rpt_Event(){
        htmlPaymentCustVendorSql = new htmlPaymentCustVendorSql();
    }
    public rpt_Event(String s) {
        this.email = s;
    }

    public double getAmt() {
        return amt;
    }

    public List<rpt_EventPaymentDetailBookedBean> getEventPaymentDetailBookedBean(int spon, int id, boolean cust, dbMgrInterface db) {
        CachedRowSet cr = null;
        List<rpt_EventPaymentDetailBookedBean> list = new ArrayList<rpt_EventPaymentDetailBookedBean>();
        amt = 0;
        try {
            if (cust) {
                cr = db.getCachedRowSet(htmlPaymentCustVendorSql.sqlBookingCost(true,email),
                        new Object[]{new Integer(spon),
                    new Integer(id)});
            } else {
                cr = db.getCachedRowSet(htmlPaymentCustVendorSql.sqlBookingCostVendor(true,email),
                        new Object[]{new Integer(spon),
                    new Integer(id)});
            }
            return getEventPaymentDetailBookedBean(cr,cust);
        } catch (Exception e) {
            Logger.getLogger(htmlPaymentCustVendorObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }
    
    public List<rpt_EventPaymentDetailBookedBean> getEventPaymentDetailBookedBean(int spon,  boolean cust, dbMgrInterface db) {
        CachedRowSet cr = null;
        List<rpt_EventPaymentDetailBookedBean> list = new ArrayList<rpt_EventPaymentDetailBookedBean>();
        amt = 0;
        try {
            if (cust) {
                cr = db.getCachedRowSet(htmlPaymentCustVendorSql.sqlBookingCost(false,email),
                        new Object[]{new Integer(spon)});
            } else {
                cr = db.getCachedRowSet(htmlPaymentCustVendorSql.sqlBookingCostVendor(false,email),
                        new Object[]{new Integer(spon)});
            }
            return getEventPaymentDetailBookedBean(cr,cust);
        } catch (Exception e) {
            Logger.getLogger(htmlPaymentCustVendorObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }
    
    
    public List<rpt_EventPaymentDetailBookedBean> getEventPaymentDetailBookedBean(CachedRowSet cr, boolean cust) {
        
        List<rpt_EventPaymentDetailBookedBean> list = new ArrayList<rpt_EventPaymentDetailBookedBean>();
        amt = 0;
        try {
            reusableObj reusableObj = new reusableObj();
            while (cr.next()) {
                rpt_EventPaymentDetailBookedBean bean = new rpt_EventPaymentDetailBookedBean();
                bean.setStatus(cr.getString(5));
                bean.setDesc(cr.getString(6) + " " + cr.getString(7));
                bean.setCost(cr.getDouble(1));
                bean.setCnt(reusableObj.round(cr.getDouble(10) / (cr.getDouble(8) ), 2));
                bean.setDays(cr.getInt(3));
                bean.setTotal(cr.getDouble(2));
                amt += cr.getDouble(2);
                list.add(bean);
            }
        } catch (Exception e) {
            Logger.getLogger(htmlPaymentCustVendorObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }
    
    
    
    // ******************************************************************************
    public List<custVendorPaymentBean> eventPaymentDetailBean(int spon, int id, boolean cust, dbMgrInterface db) {
        CachedRowSet cr ;
        List<custVendorPaymentBean> v = new ArrayList<custVendorPaymentBean>();
        try {
            cr = db.getCachedRowSet(
                    htmlPaymentCustVendorSql.getPaymentDetail(false, cust,email) + " union "
                    + htmlPaymentCustVendorSql.getPaymentDetail(true, cust,email) + " order by 3 ,4,2,7,9",
                    new Object[]{new Integer(spon), new Integer(id), new Integer(spon), new Integer(id)});
            return eventPaymentDetailBean(cr,cust);
        } catch (Exception e) {
            Logger.getLogger(htmlPaymentCustVendorObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return v;
    }
    
     public List<custVendorPaymentBean> eventPaymentDetailBeanBooked(int spon, int id, boolean cust, dbMgrInterface db) {
        CachedRowSet cr ;
        List<custVendorPaymentBean> v = new ArrayList<custVendorPaymentBean>();
        //custVendorPaymentBean bean;
        try {
            cr = db.getCachedRowSet(
                    //htmlPaymentCustVendorSql.getPaymentDetail(false, cust) + " union "+
                    htmlPaymentCustVendorSql.getPaymentDetail(true, cust,email) + " order by 3 ,4,2,7,9",
                    new Object[]{new Integer(spon), new Integer(id)});
            return eventPaymentDetailBean(cr,cust);
        } catch (Exception e) {
            Logger.getLogger(htmlPaymentCustVendorObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return v;
    }
     public List<custVendorPaymentBean> eventPaymentDetailBeanBooked(int spon, boolean cust, dbMgrInterface db) {
        CachedRowSet cr ;
        List<custVendorPaymentBean> v = new ArrayList<custVendorPaymentBean>();
        //custVendorPaymentBean bean;
         //htmlPaymentCustVendorSql.getPaymentDetail(false, cust) + " union "+
        String x = htmlPaymentCustVendorSql.getPaymentDetail(true,email) + " order by 3 ,4,2,7,9";
        try {
            cr = db.getCachedRowSet(
                   
                    htmlPaymentCustVendorSql.getPaymentDetail(true,email) + " order by 3 ,4,2,7,9",
                    new Object[]{new Integer(spon)});
            return eventPaymentDetailBean(cr,cust);
        } catch (Exception e) {
            Logger.getLogger(htmlPaymentCustVendorObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return v;
    }
    public List<custVendorPaymentBean> eventPaymentDetailBean(CachedRowSet cr,boolean cust) {
        List<custVendorPaymentBean> v = new ArrayList<custVendorPaymentBean>();
        custVendorPaymentBean bean;
        amt = 0;
        try {

            while (cr.next()) {
                bean = new custVendorPaymentBean();
                bean.setAmt(cr.getDouble(2));
                amt += cr.getDouble(2);
                bean.setTimeStamp(cr.getTimestamp(3));
                if (cust) {
                    bean.setCustVendorName(cr.getString(12) + "," + cr.getString(13));
                } else {
                    bean.setCustVendorName(cr.getString(6));
                }
                bean.setPaymentMethod(cr.getString(14));
                bean.setPaymentType(cr.getString(8));
                bean.setBooked(cr.getInt(15) == 2 ? true : false);
                bean.setId(cr.getInt(16));
                v.add(bean);
            }
        } catch (Exception e) {
            Logger.getLogger(htmlPaymentCustVendorObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return v;
    }

    
}
