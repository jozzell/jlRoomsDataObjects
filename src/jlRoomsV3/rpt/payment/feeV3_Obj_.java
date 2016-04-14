/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsV3.rpt.payment;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jlRoomsCommon._beans.rptBeanColumesAmtList;
import obj.db.v1.dbMgrInterface;
import sun.jdbc.rowset.CachedRowSet;

/**
 *
 * @author Lloyd
 */
public class feeV3_Obj_ extends paymentV3_Obj_ implements Serializable{
    public List<rptBeanColumesAmtList> getFee_All(int spondorId, String key, dbMgrInterface x) {
        CachedRowSet cr=null;
        try {
            cr = x.getCachedRowSet((new paymentV3_Sql()).getCustPaymentV3_fees(key,false),new Object[]{ spondorId});
            return getCustPaymentAll(  key, 12,6,  cr,7);
        } catch (Exception ex) {
            Logger.getLogger(paymentV3_Obj_Rpt.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            x.closeCachedRowSet(cr);
        }
        return null;
    }
}
