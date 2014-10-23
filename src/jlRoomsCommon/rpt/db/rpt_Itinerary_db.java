/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsCommon.rpt.db;

import java.io.Serializable;
import jlRoomsCommon._beans.rptItineraryBean;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import obj.db.v1.dbMgrInterface;
import sun.jdbc.rowset.CachedRowSet;

/**
 *
 * @author lmeans
 */
public class rpt_Itinerary_db implements Serializable{ 
    String email;
    rpt_Itinerary_sql rpt_Itinerary_sql;
    public rpt_Itinerary_db(){
        rpt_Itinerary_sql = new rpt_Itinerary_sql();
    }
    public rpt_Itinerary_db(String email){
            this();
            this.email = email;
        }
    public List<rptItineraryBean> getCustomerItinerary_Booking(int cust,int sponsor,dbMgrInterface db){
       List<rptItineraryBean> list = new ArrayList<rptItineraryBean>();
        CachedRowSet rs = null;
        int cnt = 0;
        try {
            
            rs = db.getCachedRowSet(rpt_Itinerary_sql.getItinerary_CustomerBooking(email), new Object[]{new Integer(cust),new Integer(sponsor)});
            while(rs.next()){
                
                 rptItineraryBean b = new  rptItineraryBean();
                 b.setId(cnt++);
                  
                 b.setEffDate(rs.getString(1));
                 b.setEndDate(rs.getString(2));
                 b.setRmCost(rs.getDouble(3));
                 b.setCustRmCnt(rs.getDouble(4));
                 b.setRmComment(rs.getString(5));
                 
                 b.setBalancedDueDate(rs.getString(6));
                 b.setBalanceComment(rs.getString(7));
                 b.setRoommdateSplit(rs.getInt(8));
                 b.setVendorName(rs.getString(9));
                 b.setSysDesc(rs.getString(10));
                 
                 b.setVendorType(rs.getInt(11));
                 b.setRoommdateId(rs.getInt(12));
                 b.setTotalCost(rs.getDouble(13));
                 b.setFlt_id(rs.getInt(14));
                 b.setBalanceDueBy(rs.getString(15));
                 
                 b.setCutoffDate(rs.getString(16));
                 b.setCancelDate(rs.getString(17));
                 b.setCheckDueBy(rs.getString(18));
                 b.setRoomDesc(rs.getString(19));
                 b.setAddr1(rs.getString(20));
                 
                 b.setAddr2(rs.getString(21));
                 b.setCity(rs.getString(22));
                 b.setState(rs.getString(23));
                 b.setZip(rs.getString(24));
                 b.setPhone(rs.getString(25));
                 
                 b.setCustRoomType(rs.getInt(26));
                 b.setDays(rs.getInt(27));
                 b.setRptDesc(rs.getString(28));
                 b.setFltNumber(rs.getString(29));
                 b.setFltComment(rs.getString(30));
                 
                 b.setFltArrDate(rs.getString(31));
                 //b.setFltArrTime(rs.getString(32));
                 b.setFltArrPlace(rs.getString(33));
                 
                 b.setFltDepDate(rs.getString(34));
                 b.setFltDepTime(rs.getString(35));
                 b.setFltDepPlace(rs.getString(36));
                 b.setVendorTypeDesc(rs.getString(37));
                
                 
                
                 list.add(b);
            }
        } catch (Exception ex) {
            Logger.getLogger(rpt_Itinerary_db.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
