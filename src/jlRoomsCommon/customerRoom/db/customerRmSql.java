package jlRoomsCommon.customerRoom.db;

import jlRoomsCommon.objMgr;



public class customerRmSql  extends objMgr {
    
    private   String sql =
            " select r.cust_room_id, r.cust_id, r.block_id, r.eff_date, r.end_date, r.rm_cost,   "
            + " r.sponsor_id, r.cust_room_cnt, r.room_comment, r.balance_due_date, r.balance_comment,      "
            + "  r.cust_room_type, r.room_paid ,r.ROOMMATE_SPLIT,v.vendor_name, "
            + " (select sys_desc from lookup_sys where sys_id = r.cust_room_type)  "
            + " ,'y',v.vendor_type,    "
            + "   r.ROOMMATE_ID,r.TOTAL_COST,r.lookup_id,v.vendor_id,r.flt_id,b.sponsor_hotel_id,   "
            + "    s.NO_FEE_APPLYIED,s.service_charge,s.process_fee,s.HOTEL_FEE,r.block_cost_cnt, "
            + " (select lookup_desc from lookup where lookup_id = r.lookup_id),r.NUM_DAYS ,s.dsp_str1"
            + "    from customer_room r,vendor v ,block b,sponsor_hotel s "
            + "    where v.vendor_id = r.vendor_id and b.block_id = r.block_id and "
            + "   b.sponsor_hotel_id = s.sponsor_hotel_id   ";
    
    ;
  
  public   String sqlCustomerBooking(String web) {
        return sql
                + " and r.cust_id = ?  and r.sponsor_id = ? "
                + (web == null ? "" : sqlGetRmList_sub(web))
                + " order by v.vendor_type,v.vendor_name ";
    }
    // *********************************************************
    private   String sqlCustomerRoom = sql + " and r.cust_room_id = ? ";

    public   String sqlCustomerRoom(String web) {
        if (web == null) {
            return sqlCustomerRoom;
        } else {
            return sqlCustomerRoom+sqlGetRmList_sub(web);
        }
    }
       public   String  sqlGetRoommateList(String web) {
       return sqlGetRmList(web) + " and r.rm_id in (select rm_id from roommate_cust where cust_id = ? and sponsor_id = ? and sponsor_hotel_id = ? ) "
            + "  order by 7,6,8,5,3,2 ";
    }
    public   String sqlGetRoommate(String web) {
        return sqlGetRmList(web) + " and r.rm_id in (select rm_id from roommate_cust where cust_id = ? and sponsor_id = ? ) "
            + "  order by 7,6,8,b5,3,2 ";
    }
    public   String sqlGetRoommateRpt(String web) {
           return sqlGetRmList(web) + " and r.rm_id in (select rm_id from roommate_cust where sponsor_id = ? ) "
            + "  order by 7,6,8,5,3,2 ";
    }
    
    public   String sqlGetRoommateRptVendor(String web){
          return  sqlGetRmList(web) + " and r.rm_id in (select rm_id from roommate_cust where sponsor_id = ? and Vendor_id = ? ) "
            + "  order by 7,6,8,5,3,2 ";
    }
   
    // *********************************************************
    
            
    public   String sqlGetRmList(String web) {
        return " select c.cust_id,c.first_name,c.last_name,r.rm_id,x.cust_id,r.sponsor_hotel_id ,v.vendor_name,c1.first_name+' '+c1.last_name  "
            + ",v.addr1,v.city"
            + " from customer c ,roommate_cust  r,roommate x ,vendor v,customer c1 "
            + " where c.cust_id = r.cust_id and x.rm_id = r.rm_id and c1.cust_id = x.cust_id and"
            + " v.vendor_id = r.vendor_id " +sqlGetRmListRoommate(web);

    }
    private   String sqlGetRmList_sub(String web) {
        if (web == null) return null;
        return " and "+ 
            //"  c.email_key = "+this.getEMailKeyStr(web)+" and "+
            "  r.email_key = "+this.getEMailKeyStr(web)+" and "+
            "  b.email_key = "+this.getEMailKeyStr(web)+" and "+
            "  v.email_key = "+this.getEMailKeyStr(web)+" and "+
            "  s.email_key = "+this.getEMailKeyStr(web)+" ";
    }
 private   String sqlGetRmListRoommate(String web) {
        if (web == null) return null;
        return " and "+ 
             	    "  c.email_key = "+this.getEMailKeyStr(web)+" and "+
            "  r.email_key = "+this.getEMailKeyStr(web)+" and "+
            "  x.email_key = "+this.getEMailKeyStr(web)+" and "+
            "  v.email_key = "+this.getEMailKeyStr(web)+" and "+
            "  c1.email_key = "+this.getEMailKeyStr(web)+" ";
    }

   
    
    // *****************************************************************************
    private   String sqlDelete_booking_payment_1 = "delete from CUSTOMER_PAYMENT where CUST_ROOM_ID=? ";

    public   String sqlDelete_booking_payment_1(String web) {
        if (web == null) {
            return sqlDelete_booking_payment_1;
        } else {
            return sqlDelete_booking_payment_1+this.getEMailKeyEquals(web);
        }
    }
    // *********************************************************
    private   String sqlDelete_booking_payment_2 = "delete from CUSTOMER_PAYMENT_BOOKED where CUST_ROOM_ID = ?";

    public   String sqlDelete_booking_payment_2(String web) {
        if (web == null) {
            return sqlDelete_booking_payment_2;
        } else {
            return sqlDelete_booking_payment_2+this.getEMailKeyEquals(web);
        }
    }
    // *********************************************************
     private   String sqlDeleteCustomerRoom_RmId = "delete from ROOMMATE_CUST where CUST_ID = ? and rm_id = ?";
    public   String sqlDeleteCustomerRoom_RmId(String web) {
        if (web == null) {
            return sqlDeleteCustomerRoom_RmId;
        } else {
            return sqlDeleteCustomerRoom_RmId+this.getEMailKeyEquals(web);
        }
    }
     // *********************************************************
    private   String sqlDelete_booking = "delete from customer_room where cust_room_id = ?";

    public   String sqlDelete_booking(String web) {
        if (web == null) {
            return sqlDelete_booking;
        } else {
            return sqlDelete_booking+this.getEMailKeyEquals(web);
        }
    }
    // *********************************************************
    private   String sqlCurrCustRm = "select max(cust_room_id) from customer_room "
            + " where cust_id=? and block_id = ? and DB_TIMESTAMP = ? ";

    public   String sqlCurrCustRm(String web) {
        if (web == null) {
            return sqlCurrCustRm;
        } else {
            return sqlCurrCustRm+this.getEMailKeyEquals(web);
        }
    }
    // *********************************************************
    private   String sqlInsertCustRoom =
            " insert into customer_room "
            + " (cust_id, block_id, eff_date, end_date, rm_cost,  "
            + " sponsor_id, cust_room_cnt, room_comment, balance_due_date, balance_comment,   "
            + " cust_room_type, room_paid ,ROOMMATE_SPLIT,ROOMMATE_ID,TOTAL_COST,NUM_DAYS,lookup_id,vendor_id,"
            + " flt_id,DB_TIMESTAMP,block_cost_cnt) "
            + " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    public   String sqlInsertCustRoom(String web) {
        if (web == null) {
            return  sqlInsertCustRoom;
        } else {
            return " insert into customer_room "
            + " (cust_id, block_id, eff_date, end_date, rm_cost,  "
            + " sponsor_id, cust_room_cnt, room_comment, balance_due_date, balance_comment,   "
            + " cust_room_type, room_paid ,ROOMMATE_SPLIT,ROOMMATE_ID,TOTAL_COST,NUM_DAYS,lookup_id,vendor_id,"
            + " flt_id,DB_TIMESTAMP,block_cost_cnt,email_key) "
            + " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"+ this.getEMailKeyStr(web) + " )";
        }
    }
    // *********************************************************    
    public   String sqlUpdateCustRoom (String web){
       
    return 
            " update customer_room set "
            + " cust_id=?, block_id=?, eff_date=?, end_date=?, rm_cost=?, "
            + " sponsor_id=?, cust_room_cnt=?, room_comment=?, balance_due_date=?, balance_comment=?,   "
            + " cust_room_type=?, room_paid=? ,ROOMMATE_SPLIT=?,ROOMMATE_ID=?,TOTAL_COST=?,NUM_DAYS=?, "
            + " lookup_id = ?,block_cost_cnt = ? where cust_room_id=? "+(web == null ? "" :this.getEMailKeyEquals(web));
    }
}

/*
 * ,
    sqlX =
       " select r.cust_room_id, r.cust_id, r.block_id, r.eff_date, r.end_date, r.rm_cost,  "+
            " r.sponsor_id, r.cust_room_cnt, r.room_comment, r.balance_due_date, r.balance_comment,      "+
            " r.cust_room_type, r.room_paid ,r.ROOMMATE_SPLIT,v.vendor_name,l.sys_desc,'y',v.vendor_type,   "+
            " r.ROOMMATE_ID,r.TOTAL_COST,r.lookup_id,v.vendor_id,r.flt_id,b.sponsor_hotel_id,  "+
           " s.NO_FEE_APPLYIED,s.service_charge,s.process_fee,s.HOTEL_FEE,r.block_cost_cnt,"+
           "(select lookup_desc from lookup where lookup_id = r.lookup_id),r.NUM_DAYS"+
            " from customer_room r   "+
            " right outer join lookup_sys l on r.cust_room_type = l.sys_id   "+
            " right outer join lookup x on r.lookup_id = x.lookup_id ,   "+
            " vendor v ,block b,sponsor_hotel s   "+
            " where v.vendor_id = r.vendor_id and b.block_id = r.block_id and b.sponsor_hotel_id = s.sponsor_hotel_id  ",
    sqlWW=
    " select r.cust_room_id, r.cust_id, r.block_id, r.eff_date, r.end_date, r.rm_cost, r.sponsor_id, r.cust_room_cnt, r.room_comment, r.balance_due_date, r.balance_comment, " +
    " r.cust_room_type, r.room_paid ,r.ROOMMATE_SPLIT,v.vendor_name,l.sys_desc,'y',v.vendor_type," +
    " r.ROOMMATE_ID,r.TOTAL_COST,r.lookup_id,v.vendor_id,r.flt_id,b.sponsor_hotel_id,s.NO_FEE_APPLYIED,s.service_charge,s.process_fee,s.HOTEL_FEE,r.block_cost_cnt,x.lookup_desc "+
    " from customer_room r , lookup_sys l ,lookup x  , vendor v ,block b,sponsor_hotel s" +
    " where  r.lookup_id = x.lookup_id and r.cust_room_type = l.sys_id and v.vendor_id = r.vendor_id and b.block_id = r.block_id and b.sponsor_hotel_id = s.sponsor_hotel_id";

* 
* sqlGetAllRoommateXX =
    "select c.cust_id,c.last_name,c.first_name,r.rm_id,l.sys_desc, l.sys_id"+
    " from customer c, roommate_cust r,lookup_sys l "+
    " where c.cust_id = r.cust_id and l.sys_id = r.sys_id and "+
    " r.rm_id in (select max(rm_id) from roommate_cust where cust_id = ? and sponsor_id = ? and sponsor_hotel_id = ?) "+
    " order by 6,1,2",
 */
