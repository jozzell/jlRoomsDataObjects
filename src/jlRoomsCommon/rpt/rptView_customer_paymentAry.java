/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsCommon.rpt;

import java.util.ArrayList;
import java.util.List;
import jlRoomsCommon._beans.rptBeanColumes;
import jlRoomsCommon._beans.rptBeanColumesAmtList;
import jlRoomsCommon.objMgr;
import sun.jdbc.rowset.CachedRowSet;

/**
 *
 * @author lmeans
 */
public class rptView_customer_paymentAry extends objMgr {
     public  rptBeanColumesAmtList get_rptView_customer_payment(CachedRowSet cr){
        List<rptBeanColumesAmtList> list = new ArrayList<rptBeanColumesAmtList>();
        if (cr == null) return new rptBeanColumesAmtList();
        double amt = 0, total = 0;
        try {
            //rptBeanColumesAmtList bean = null;
            List<rptBeanColumes> rptList = null;
            String curr=null,last=null;
            while (cr.next()) {
                curr = cr.getString(12);
                if (last != null && !last.equals(curr) ) {
                    list.add(new rptBeanColumesAmtList(last,amt,rptList));
                    amt = 0;
                    rptList = null;
                }
                //if (bean == null) bean = new rptBeanColumesAmtList();
                //bean.setHeader(curr);
                rptBeanColumes x = new rptBeanColumes();
                x.setCol01(curr);
                x.setCol02(cr.getString(5));
                x.setCol03( cr.getString(7) );
                x.setCol04((cr.getInt(4) == -5 ?  cr.getString(9)+" " :"")+cr.getString(10));
                x.setCol05(this.getDollarFormat(cr.getDouble(6)));
                
                if (cr.getInt(4) == -6) {
                    amt += cr.getDouble(6);
                    total += cr.getDouble(6);
                }
                if (rptList == null) rptList = new ArrayList<rptBeanColumes>();
                rptList.add(x);
                last = curr;
            }
            if (rptList != null){
                 list.add(new rptBeanColumesAmtList(last,amt,rptList));
            }
           
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        return new rptBeanColumesAmtList(total,list);
    }
}
