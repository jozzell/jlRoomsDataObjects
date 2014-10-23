/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsCommon.rpt;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jlRoomsCommon._beans.rptBeanColumes;
import jlRoomsCommon._beans.rptBeanColumesAmtList;
import jlRoomsCommon.objMgr;
import obj.db.v1.dbMgrInterface;
import sun.jdbc.rowset.CachedRowSet;

/**
 *
 * @author lmeans
 */
public class rptView_customer_payment_summaryAry extends objMgr {
     public  rptBeanColumesAmtList get_rptView_customer_payment_summary(CachedRowSet cr) { //int spon, String web, dbMgrInterface db
        //CachedRowSet cr = rptView_customer_payment_summary(spon, web, db);
        List<rptBeanColumesAmtList> list = new ArrayList<rptBeanColumesAmtList>();
        if (cr == null) {
            return new rptBeanColumesAmtList();
        }

        String name = null, str;
        int curr = 0, last = 0;
        double amt = 0, total = 0;

        try {
            rptBeanColumesAmtList bean = null;
            List<rptBeanColumes> rptList = null;
            while (cr.next()) {
                curr = cr.getInt(2);
                if (bean != null && curr != last) {
                    list.add(new rptBeanColumesAmtList(name, amt, rptList));
                    amt = 0;
                    rptList = null;
                }
                if (bean == null) {
                    bean = new rptBeanColumesAmtList();
                }
                bean.setHeader(cr.getString(5));
                rptBeanColumes x = new rptBeanColumes();

                switch (cr.getInt(1)) {
                    case 3:
                        str = "Payments";
                        break;
                    case 2:
                        str = "Fees";
                        break;
                    default:
                        str = cr.getString(9);
                        break;
                }
                //cr.getTimestamp(8).getTime();
                //new java.util.Date(cr.getTimestamp(8).getTime());
                //x.setCol01(new SimpleDateFormat("yyyy-MM-ddTHH:mm:ss.SSS").format(cr.getTimestamp(8))); // cr.getTimestamp(8));


                x.setCol01(cr.getString(12)); // cr.getTime
                x.setCol02(str);
                x.setCol03(cr.getString(10));
                x.setCol04(cr.getString(9));
                x.setCol05(this.getDollarFormat(cr.getDouble(6)));

                name = cr.getString(5);
                if (cr.getInt(4) == -6) {
                    amt += cr.getDouble(6);
                    total += cr.getDouble(6);
                }
                if (rptList == null) {
                    rptList = new ArrayList<rptBeanColumes>();
                }
                rptList.add(x);
                last = curr;

            }
            if (rptList != null) {
                list.add(new rptBeanColumesAmtList(name, amt, rptList));
            }
        } catch (Exception ex) {
            Logger.getLogger(rptView_customer_payment_summary.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new rptBeanColumesAmtList(total, list);
    }
}
