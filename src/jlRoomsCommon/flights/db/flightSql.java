package jlRoomsCommon.flights.db;

import jlRoomsCommon.objMgr;

public class flightSql extends objMgr{

    private  final String sql =
            " select f.flt_id,f.flt_arrival_date,flt_arrival_place,f.flt_comment, "
            + " f.flt_departure_date,f.flt_departure_place,f.flt_number,f.flt_type,f.hotel_id, "
            + " f.sponsor_hotel_id,F.FLT_ROLLUP_ID,F.SPONSOR_ID,f.conn_flt,v.vendor_id,v.vendor_name,f.flag_ind,  "
            + " f.flt_arrival_time,f.flt_departure_time,v.vendor_type ";
    
    
    private  String sqlColumnSub(String web){
        return sql + " from flight_information f, vendor v where v.vendor_id = f.vendor_id "+sqlCol(web);
    }
    private  String sqlCol(String web){
        if(web == null) return "";
        return " and "+
             " v.email_key = "+this.getEMailKeyStr(web)+" and "+
             " f.email_key = "+this.getEMailKeyStr(web)+" ";
    }
    // ****************************************************
    private  String sqlColumnSub2(String web){
        return sql + "from flight_information f, vendor v ,sponsor s where v.vendor_id = f.vendor_id and s.sponsor_id = f.sponsor_id"+sqlCol2(web);
    }
    
    private  String sqlCol2(String web){
        if(web == null) return "";
        return " and "+
             " v.email_key = "+this.getEMailKeyStr(web)+" and "+
             " s.email_key = "+this.getEMailKeyStr(web)+" and "+
             " f.email_key = "+this.getEMailKeyStr(web)+" ";
    }
    // ****************************************************
    
    
    public  String sqlFltId(String web) {
        return "Select max(flt_id) from flight_information where vendor_id = ? and sponsor_id = ? and CREATED = ?" +(web == null ? "": this.getEMailKeyEquals(web));
    }

    public  String sqlUpdateConnFlight(String web) {
        return " update flight_information set conn_flt = 1 where flt_id = ?" +(web == null ? "": this.getEMailKeyEquals(web));
    }

    public  String sqlUpdate(String web) {
        return "update flight_information set"
                + " flt_arrival_date=?,flt_arrival_place=?,flt_comment=?, "
                + " flt_departure_date=?,flt_departure_place=?,flt_number=?,flt_type=?, "
                + " conn_flt=?,flag_ind=?, flt_arrival_time=?,flt_departure_time=? "
                + " where flt_id = ?" +(web == null ? "": this.getEMailKeyEquals(web));
    }

    
    public  String sqlFltAll(String web) {
        return sqlColumnSub2(web) + "  and F.SPONSOR_ID = ? and s.cust_id = ? order by f.flt_departure_date,f.flt_departure_time";
    }

    

    public  String sqlPickFlight(String web) {
        return sqlColumnSub(web) + " and F.SPONSOR_ID = ? and F.FLT_ROLLUP_ID in (0,-1) order by v.vendor_name,f.flt_number";
    }

    public  String sqlPickFligtConn(String web) {
        return sqlColumnSub(web) + " and f.FLT_ROLLUP_ID = ? order by f.flt_departure_date,f.flt_departure_time";
    }

    public  String sqlPickFligtWithConn(String web) {
        return sqlColumnSub(web) + " and (f.flt_id = ? or f.FLT_ROLLUP_ID = ?) order by f.FLT_ROLLUP_ID,f.flt_departure_date";
    }
    public  String sqlGetFlightBean(String web) {
        return sqlColumnSub(web) + " and f.flt_id = ? order by f.FLT_ROLLUP_ID,f.flt_departure_date";
    }
    public  String sqlPickFligtWithConnSponHotelId(String web) {
        return sqlColumnSub(web) + " and f.sponsor_hotel_id=? order by f.flt_departure_date,f.flt_departure_time";
    }
    
    public  String sqlGetVendorFlights(String web) {
        return sqlColumnSub(web) + " and v.vendor_id=? and f.sponsor_id =? order by f.flt_departure_date,f.flt_departure_time";
    }
    
    
    
    public  String sqlFltAll2(String web) {
        return sql + " from flight_information f, vendor v ,customer_room r "
                + " where v.vendor_id = f.vendor_id and f.sponsor_id = ? and r.cust_id = ? and "+sqlFltAll2_sub(web)
                + " (r.flt_id = f.flt_id or r.flt_id = F.FLT_ROLLUP_ID) and r.flt_id > 0  order by f.flt_departure_date,f.flt_departure_time";
    }
    private  String sqlFltAll2_sub(String web){
        if(web == null) return "";
        return 
             " v.email_key = "+this.getEMailKeyStr(web)+" and "+
             " r.email_key = "+this.getEMailKeyStr(web)+" and "+
             " f.email_key = "+this.getEMailKeyStr(web)+" and ";
    }
    
    private  final String sqlInsert =
         "insert into flight_information"
                + "(flt_arrival_date,flt_arrival_place,flt_comment, "
                + " flt_departure_date,flt_departure_place,flt_number,flt_type,hotel_id, "
                + " sponsor_hotel_id,FLT_ROLLUP_ID,SPONSOR_ID,conn_flt,VENDOR_ID,flag_ind,flt_arrival_time,flt_departure_time,CREATED,flg_ind)"
                + " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    public   String sqlInsert(String web){
        if (web == null) {
            return sqlInsert;
        } else {
            return "insert into flight_information"
                + "(flt_arrival_date,flt_arrival_place,flt_comment, "
                + " flt_departure_date,flt_departure_place,flt_number,flt_type,hotel_id, "
                + " sponsor_hotel_id,FLT_ROLLUP_ID,SPONSOR_ID,conn_flt,VENDOR_ID,flag_ind,flt_arrival_time,flt_departure_time,CREATED,flg_ind,email_key)"
                + " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"+this.getEMailKeyStr(web)+" )";
        }
    }

    //sql_onlyFlight =
    //" from flight_information f ";
    /*
    private  final String sqlColumns = sql
            + " from flight_information f, flight_information_customer i "
            + " where i.flt_id = f.flt_id  ",
            sqlFlight = sqlColumns + " where flt_id = 111 ",
            sqlFlightBooked = sqlColumns + " and i.cust_room_id = ? and i.region_id = ? order by f.flt_type";
            * */
            
}
