/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsCommon.customerRoom.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jlRoomsCommon._beans.custRmBean;
import jlRoomsCommon._beans.roommateBean;
import obj.db.v1.dbMgrInterface;
import sun.jdbc.rowset.CachedRowSet;

/**
 *
 * @author lmeans
 */
public class roommateObj  implements Serializable {
    private String webID = null;
    roommateSql roommateSql;
    customerRmSql customerRmSql;
    public roommateObj(){
        roommateSql = new roommateSql();
        customerRmSql = new customerRmSql();
    }
    public roommateObj(String s){
        this();
        this.webID = s;
    }
    // *************************************************  dbMgrInterface db
    public int addRoommate(custRmBean bean,int cust_id,int sponsor_id,dbMgrInterface db){
        int rm_id = getRoommateCust_RmId( cust_id,  bean.getSponsorHotelId(),  bean.getSponsor(), db);
        
        int rm = getRoommateCust_RmId( bean.getCustId(),  bean.getSponsorHotelId(),  bean.getSponsor(), db);
        bean.setRoommateId(rm);
        
        if (bean.getRoommateId() == 0){
            if (rm_id == 0){
                rm_id = insertRoommate(
                        bean.getCustId(), 
                        bean.getSponsorHotelId(), 
                        bean.getSponsor(), 
                        bean.getVendorId(), 
                        bean.getVendorType(), 
                        db);
                insertRoommateCust(bean.getCustId(), bean.getSponsorHotelId(),bean.getSponsor(), bean.getVendorId(), bean.getVendorType(),rm_id,db);
                insertRoommateCust(cust_id, bean.getSponsorHotelId(),bean.getSponsor(), bean.getVendorId(), bean.getVendorType(),rm_id,db);
            } else {
                insertRoommateCust(bean.getCustId(), bean.getSponsorHotelId(),bean.getSponsor(), bean.getVendorId(), bean.getVendorType(),rm_id,db);
            }
        } else {
            if (rm_id == 0 || rm_id != bean.getRoommateId()){
                insertRoommateCust(cust_id, bean.getSponsorHotelId(),bean.getSponsor(), bean.getVendorId(), bean.getVendorType(),bean.getRoommateId(),db);
            }
        }
        return rm_id;
    }
    // ****************************************************************
    public int getRoommateCust_RmId(int cust, int hotel, int spon,dbMgrInterface db){
        int id = 0;
        try {
           
            CachedRowSet rs;
            rs = db.getCachedRowSet(roommateSql.sqlGetRoommateCust_RmId(this.webID), new Object[]{
                cust, spon, hotel});
                while (rs.next()) {
                    if (rs.getInt(1) > 0) {
                        id = rs.getInt(1);
                    }
                }
        } catch (Exception ex) {
            Logger.getLogger(customerRmObj.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
            
    }
    public int getRoommate_RmId(int cust, int hotel, int spon,dbMgrInterface db){
        int id = 0;
        try {
           
            CachedRowSet rs;
            rs = db.getCachedRowSet(roommateSql.sqlGetRoommate_RmId(this.webID), new Object[]{
                cust, spon, hotel});
                while (rs.next()) {
                    if (rs.getInt(1) > 0) {
                        id = rs.getInt(1);
                    }
                }
        } catch (Exception ex) {
            Logger.getLogger(customerRmObj.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
            
    }
    // *******************************************************************************
    public void insertRoommateCust(int cust, int hotel, int spon, int vendor, int sys, int rm_id,dbMgrInterface db){
        Object obj[] = new Object[]{
            new Integer(rm_id),
            new Integer(spon),
            new Integer(cust),
            new Integer(hotel),
            new Integer(vendor),
            new Integer(sys)
        };
        try {
            db.updateDatabase(roommateSql.sqlInsertRoommateCust(this.webID), obj);
        } catch (Exception ex) {
            Logger.getLogger(roommateObj.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //
     public int insertRoommate(int cust, int hotel, int spon, int vendor, int sys,dbMgrInterface db){
        int id = 0;
         Object obj[] = new Object[]{
                new Integer(cust),
                new Integer(spon),
                new Integer(hotel),
                new Integer(vendor),
                new Integer(sys)};
        try {
            db.updateDatabase(roommateSql.sqlInsertRoommate(this.webID), obj);
        } catch (Exception ex) {
            Logger.getLogger(customerRmObj.class.getName()).log(Level.SEVERE, null, ex);
        }
          id = getRoommate_RmId( cust,  hotel,  spon, db);
         return id;
    }
     // *******************************************************************************
     public List<roommateBean> getRoommateRptVendorXXX(int spon, int ven, dbMgrInterface db) {
        List<roommateBean> ary = new ArrayList<roommateBean>();
        CachedRowSet rs = null;

        try {
            rs = db.getCachedRowSet(customerRmSql.sqlGetRoommateRptVendor(this.webID),
                    new Object[]{
                new Integer(spon),
                new Integer(ven)
            });
            while (rs.next()) {
                ///ary.add(getRoomateBean(rs, false));
            }
        } catch (Exception e) {
            Logger.getLogger(customerRmObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return ary;
    }
}
