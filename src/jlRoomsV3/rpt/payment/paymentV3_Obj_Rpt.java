/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsV3.rpt.payment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jlRoomsCommon._beans.rptBeanColumes;
import jlRoomsCommon._beans.rptBeanColumesAmtList;
import obj.db.v1.dbMgrInterface;
import sun.jdbc.rowset.CachedRowSet;

/**
 *
 * @author Lloyd
 */
public class paymentV3_Obj_Rpt extends paymentV3_Obj_ implements Serializable {
    
    
    public void genVendorPayment(int vendorId, int spondorId, String key, dbMgrInterface x) {
        CachedRowSet cr = null;
        try {
            Object[] obj = new Object[]{vendorId, spondorId, vendorId, spondorId}; //
            cr = x.getCachedRowSet((new paymentV3_Sql()).getVendorPaymentV3(key), obj);
            genRptView_customer_payment(cr);
        } catch (Exception ex) {
            Logger.getLogger(paymentV3_Obj_Rpt.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            x.closeCachedRowSet(cr);
        }
    }
    public void genCustPayment(int custId, int spondorId, String key, dbMgrInterface x) {
        CachedRowSet cr = null;
        try {
            Object[] obj = new Object[]{custId, spondorId, custId, spondorId, custId, spondorId};
            cr = x.getCachedRowSet((new paymentV3_Sql()).getCustPaymentV3(key), obj);
            genRptView_customer_payment(cr);
        } catch (Exception ex) {
            Logger.getLogger(paymentV3_Obj_Rpt.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            x.closeCachedRowSet(cr);
        }
    }

    private void genRptView_customer_payment(CachedRowSet cr) {
        setRptList(new ArrayList<rptBeanColumes>());
        try {
            while (cr.next()) {
                getRptList().add(getrptBeanColumes(5,cr));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    public List<rptBeanColumesAmtList> getCustPaymentAll(int spondorId, String key, dbMgrInterface x) {
        CachedRowSet cr=null;
        try {
            cr = x.getCachedRowSet((new paymentV3_Sql()).getCustPaymentV3_All(key),new Object[]{spondorId, spondorId,  spondorId});
            return getCustPaymentAll(  key, 10,5,  cr);
        } catch (Exception ex) {
            Logger.getLogger(paymentV3_Obj_Rpt.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            x.closeCachedRowSet(cr);
        }
        return null;
    }
    public List<rptBeanColumesAmtList> getVendorPaymentAll(int spondorId, String key, dbMgrInterface x) {
        CachedRowSet cr=null;
        try {
            cr = x.getCachedRowSet((new paymentV3_Sql()).getVendorPaymentV3_All(key),new Object[]{spondorId,  spondorId});
            return getCustPaymentAll(   key, 10,5,  cr);
        } catch (Exception ex) {
            Logger.getLogger(paymentV3_Obj_Rpt.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            x.closeCachedRowSet(cr);
        }
        return null;
    }
    
    
   
}
