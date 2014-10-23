/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsCommon.rpt;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import jlRoomsCommon._beans.rptBeanColumes;
import jlRoomsCommon._beans.rptBeanColumesAmtList;
import jlRoomsCommon.objMgr;
import sun.jdbc.rowset.CachedRowSet;

/**
 *
 * @author lmeans
 */
public class rptView_vendor_paymentAry extends objMgr {
     public  rptBeanColumesAmtList getRptBeanColumesAmtList(CachedRowSet cr)throws Exception {
        List<rptBeanColumesAmtList> list = new ArrayList<rptBeanColumesAmtList>();

        double amt = 0, total = 0;
        try {
            
            List<rptBeanColumes> rptList = null;
           
            Calendar cal = Calendar.getInstance();
            
            String  str = "",lastStr=null,currStr=null;
            
            while (cr.next()) {
                currStr = cr.getString(12);
                
                if (lastStr != null && !currStr.endsWith(lastStr)) {
                    list.add(new rptBeanColumesAmtList(lastStr,amt,rptList));
                    amt = 0;
                    rptList = null;
                }
                 rptBeanColumes x = new rptBeanColumes();
                 x.setCol01(currStr);
                 x.setCol02(cr.getString(5));
                 x.setCol03(cr.getString(7));
                 x.setCol04(this.getDollarFormat(cr.getDouble(8)));
                if (cr.getInt(4) == -6) {
                    amt += cr.getDouble(8);
                    total += cr.getDouble(8);
                }
                if (rptList == null) rptList = new ArrayList<rptBeanColumes>();
                rptList.add(x);
                lastStr = currStr;
            }
            if (rptList != null){
                 list.add(new rptBeanColumesAmtList(lastStr,amt,rptList));
            }
        } catch (Exception ex) {
            throw ex;
        }
        return new rptBeanColumesAmtList(total,list);

    }
}
