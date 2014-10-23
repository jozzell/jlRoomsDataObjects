/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jlRoomsCommon.rpt.db.sql;
import java.io.Serializable;
import jlRoomsCommon._objects.jlRoomsDbConnIinterface;
/**
 *
 * @author lmeans
 */

public class htmlPaymentCustVendorObj  implements Serializable{
    private jlRoomsDbConnIinterface jlRoomsFactory;
    public htmlPaymentCustVendorObj(jlRoomsDbConnIinterface x) {
      this.jlRoomsFactory = x;

    }
    
}
