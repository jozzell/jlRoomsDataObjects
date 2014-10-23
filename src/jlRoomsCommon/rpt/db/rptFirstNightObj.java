/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jlRoomsCommon.rpt.db;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.jdbc.rowset.CachedRowSet;
import jlRoomsCommon._beans.rptBean;
import jlRoomsCommon._objects.jlRoomsDbConnIinterface;

/**
 *
 * @author lmeans
 */
public class rptFirstNightObj implements Serializable {
    private jlRoomsDbConnIinterface jlRoomsFactory;
    rptFirstNightSql rptFirstNightSql;
    public rptFirstNightObj(){
        rptFirstNightSql = new rptFirstNightSql();
    }
    public rptFirstNightObj(jlRoomsDbConnIinterface x) {
        this();
      this.jlRoomsFactory = x;

    }

    public  CachedRowSet getCustArrivalRpt(int spon){
         CachedRowSet rs = null;
        try {
            rs = jlRoomsFactory.getCachedRowSet(rptFirstNightSql.sql_rpt_arrival,new Object[]{new Integer(spon)});
        } catch(Exception e){
            Logger.getLogger(rptFirstNightObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return rs;
    }

    public  CachedRowSet getCustArrival(int spon){
         CachedRowSet rs = null;
        try {
            rs = jlRoomsFactory.getCachedRowSet(rptFirstNightSql.sql_rpt_arrival,new Object[]{new Integer(spon)});
        } catch(Exception e){
            Logger.getLogger(rptFirstNightObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return rs;
    }

    
    public  rptBean  getList(int spon){
         
        CachedRowSet rs = null;
        rptBean  rptBean = null;
        try {
            rs = jlRoomsFactory.getCachedRowSet(rptFirstNightSql.sql_rpt_get_day_cnt_id,new Object[]{new Integer(spon)});
            if (rs.size() > 0) {
                rptBean = new rptBean();
                rptBean.setArySize(rs.size());
            }
            while(rs.next()){
               rptBean.setAryValue(rs.getString(2),rs.getInt(1));
            }
        } catch(Exception e){
            Logger.getLogger(rptFirstNightObj.class.getName()).log(Level.SEVERE, null, e);
        }
         return rptBean;
    }
    public  rptBean getLastPayment(int spon){
        rptBean rptBean = new rptBean();
        CachedRowSet rs = null;
        try {
            rs = jlRoomsFactory.getCachedRowSet(rptFirstNightSql.sql_rpt_get_payments,new Object[]{new Integer(spon)});
            while(rs.next()){
               rptBean.setAmt(rs.getDouble(1));
               rptBean.setLastPayment(rs.getTimestamp(2));
            }
        } catch(Exception e){
            Logger.getLogger(rptFirstNightObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return rptBean;
    }
    
}
