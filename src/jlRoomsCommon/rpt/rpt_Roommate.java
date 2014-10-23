/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsCommon.rpt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jlRoomsCommon._beans.roommateBean;
import jlRoomsCommon._beans.rptBeanColumes;

/**
 *
 * @author lmeans
 */
public class rpt_Roommate implements Serializable{

    private String email;

    public rpt_Roommate(String s) {
        this.email = s;
    }

    public  List<rptBeanColumes> genRoomateRptGui(List<roommateBean> list) {
        List<rptBeanColumes> rpt = new ArrayList<rptBeanColumes>();
        if (list == null) {
            return rpt;
        }
        int rm_id = -1;
        rptBeanColumes rptBeanColumes = new rptBeanColumes();
        
        for (int i = 0; i < list.size(); i++) {
            roommateBean bean = list.get(i);
            if (rm_id != -1 && rm_id != bean.getRmId()) {
                rpt.add(rptBeanColumes);
                rptBeanColumes = new rptBeanColumes();
               

            }
            if (bean.isPrimary()) {
                rptBeanColumes.setCol01(bean.getVendorName());
                rptBeanColumes.setCol02(bean.getName());
                rptBeanColumes.setId(bean.getCustId());
            } else {
                if ( rptBeanColumes.getCol03() == null){
                    rptBeanColumes.setCol03(bean.getName());
                }else {
                    rptBeanColumes.setCol03(rptBeanColumes.getCol03() + ", " + bean.getName());
                }
                
            }
            rm_id = bean.getRmId();
        }
        if (rm_id != -1)rpt.add(rptBeanColumes);
        return rpt;

    }
}
