/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsCommon.rpt;

import java.io.Serializable;
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
public class _rpt implements Serializable{
  public  rptBeanColumesAmtList get_rptview_payment_type(CachedRowSet cr)  {
        List<rptBeanColumesAmtList> list = new ArrayList<rptBeanColumesAmtList>();
        if (cr == null) return new rptBeanColumesAmtList();
        double amt = 0, total = 0;
        try {
            rptBeanColumesAmtList bean = null;
            List<rptBeanColumes> rptList = null;
            String last=null ,curr=null;
            while (cr.next()) {
                if (bean != null && !last.endsWith(curr) ) {
                    list.add(new rptBeanColumesAmtList(last,amt,rptList));
                    amt = 0;
                    rptList = null;
                }
                if (bean == null) bean = new rptBeanColumesAmtList();
                bean.setHeader(last);
                rptBeanColumes x = new rptBeanColumes();
                x.setCol01(cr.getString(3));
               
                amt += cr.getDouble(2);
                total += cr.getDouble(2);
                if (rptList == null) rptList = new ArrayList<rptBeanColumes>();
                rptList.add(x);
               
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
