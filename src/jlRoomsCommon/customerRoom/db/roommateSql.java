/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsCommon.customerRoom.db;

import jlRoomsCommon.objMgr;




/**
 *
 * @author lmeans
 */
public class roommateSql extends  objMgr{
    private  final String sqlInsertRoommate =
            "insert into roommate (cust_id,sponsor_id,sponsor_hotel_id,vendor_id,sys_id) values (?,?,?,?,?)";

    public  final String sqlInsertRoommate(String web) {
        if (web == null) {
            return sqlInsertRoommate;
        } else {
            return
              "insert into roommate (cust_id,sponsor_id,sponsor_hotel_id,vendor_id,sys_id,email_key) values (?,?,?,?,?,"+this.getEMailKeyStr(web) +" )";
        }
    }
    // *********************************************************
     private  final String sqlGetRoommate_RmId =
            "select max(rm_id) from roommate where cust_id = ? and sponsor_id = ? and sponsor_hotel_id = ? ";

    public  final String sqlGetRoommate_RmId(String web) {
        if (web == null) {
            return sqlGetRoommate_RmId;
        } else {
            return sqlGetRoommate_RmId+this.getEMailKeyEquals(web);
        }
    }
    // *********************************************************
    private  final String sqlUpdateRoomatePrimary = "update roommate set cust_id = ? where rm_id = ?";

    public  final String sqlUpdateRoomatePrimary(String web) {
        if (web == null) {
            return sqlUpdateRoomatePrimary;
        } else {
            return sqlUpdateRoomatePrimary+this.getEMailKeyEquals(web);
        }
    }
    // *********************************************************    
    private  final String sqlDeleteCust_01 =
            "delete from roommate where sponsor_id = ? and sponsor_hotel_id = ? and cust_id = ?";

    public  final String sqlDeleteCust_01(String web) {
        if (web == null) {
            return sqlDeleteCust_01;
        } else {
            return sqlDeleteCust_01+this.getEMailKeyEquals(web);
    }
    }
    
    // =====================================================================
    private  final String sqlInsertRoommateCust =
            "insert into roommate_cust (rm_id,sponsor_id,cust_id,sponsor_hotel_id,vendor_id,sys_id) values (?,?,?,?,?,?)";

    public  final String sqlInsertRoommateCust(String web) {
        if (web == null) {
            return sqlInsertRoommateCust;
        } else {
            return 
              "insert into roommate_cust (rm_id,sponsor_id,cust_id,sponsor_hotel_id,vendor_id,sys_id,email_key) values (?,?,?,?,?,?,"+this.getEMailKeyStr(web) +")";
        }
    }
 
     // *********************************************************
    private  final String sqlCheckRoommateCust = "select max(rm_id) from roommate_cust where rm_id = ? and sponsor_id = ? and cust_id = ? and "
            + "sponsor_hotel_id = ? and vendor_id= ? and sys_id=?";

    public  final String sqlCheckRoommateCust(String web) {
        if (web == null) {
            return sqlCheckRoommateCust;
        } else {
            return sqlCheckRoommateCust+this.getEMailKeyEquals(web);
        }
    }
    // *********************************************************
    private  final String sqlGetRoommateCust_RmId = "select max(rm_id) from roommate_cust where cust_id = ? and sponsor_id = ?  and "
            + "sponsor_hotel_id = ? ";
    
    public  final String sqlGetRoommateCust_RmId(String web) {
        if (web == null) {
            return sqlGetRoommateCust_RmId;
        } else {
            return sqlGetRoommateCust_RmId+this.getEMailKeyEquals(web);
        }
    }
     // *********************************************************
        
    private  final String sqlDeleteCust_02 =
            " delete from roommate_cust where sponsor_id = ? and sponsor_hotel_id = ? and cust_id = ?";
    
    
    public  final String sqlDeleteCust_02(String web) {
        if (web == null) {
            return sqlDeleteCust_02;
        } else {
            return sqlDeleteCust_02+this.getEMailKeyEquals(web);
        }
    }
    
    // ************************************************************************************
    
     public   String sqlGetRoommates_V2(String web){
          return  sqlGetRmList_V2(web) + " and r.rm_id in (select rm_id from roommate_cust where sponsor_id = ? and cust_room_id = ? ) "
            + "  order by 7,6,8,5,3,2 ";
    }
    
     public   String sqlGetRmList_V2(String web) {
        return " select c.cust_id,c.first_name,c.last_name,r.rm_id,x.cust_id,r.sponsor_hotel_id ,v.vendor_name,c1.first_name+' '+c1.last_name  "
            + ",v.addr1,v.city"
            + " from customer c ,roommate_cust  r,roommate x ,vendor v,customer c1 "
            + " where c.cust_id = r.cust_id and x.rm_id = r.rm_id and c1.cust_id = x.cust_id and"
            + " v.vendor_id = r.vendor_id " +sqlGetRmListRoommate_V2(web);

    }
    private  final String sqlGetRmListRoommate_V2(String web) {
        if (web == null) return null;
        return " and "+ 
             	    "  c.email_key = "+this.getEMailKeyStr(web)+" and "+
            "  r.email_key = "+this.getEMailKeyStr(web)+" and "+
            "  x.email_key = "+this.getEMailKeyStr(web)+" and "+
            "  v.email_key = "+this.getEMailKeyStr(web)+" and "+
            "  c1.email_key = "+this.getEMailKeyStr(web)+" ";
    }
    
}
