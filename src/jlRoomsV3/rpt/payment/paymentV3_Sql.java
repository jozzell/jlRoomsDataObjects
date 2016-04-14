/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsV3.rpt.payment;

import java.io.Serializable;
import jlRoomsCommon.objMgr;

/**
 *
 * @author Lloyd
 */
public class paymentV3_Sql extends objMgr implements Serializable {

    public String get01CustomerRoom_Cust(String web, String where) {
        return (new StringBuilder())
                .append(" select 1,CR.DB_TIMESTAMP, -1 * cr.total_cost * ")
                .append(" CASE  cr.cust_room_type ")
                .append(" when -5 then 1 ")
                .append(" when -6 then 1 ")
                .append(" else 0 ")
                .append(" END AMT, date_format(cR.db_timestamp,'%a %b %d %Y %r'), CONCAT(c.last_name, ', ', c.first_name), ")
                .append(" l.lookup_desc,CONCAT(rt.sys_desc,': ',v.vendor_name, ")
                .append(" ifnull(CONCAT(' #',f.flt_number),'')) STAT,rt.amt,rt.sys_id,cr.cust_id,v.vendor_id ")
                .append(",-747")
                .append(" from customer_room cr ")
                .append(" left outer join flight_information f on cr.flt_id = f.flt_id ")
                        .append(getEMailKeyEquals(web, "f."))
                        .append(",")
                .append(" lookup l,vendor v,lookup_sys rt,customer c ")
                .append(" where cr.lookup_id = l.lookup_id and v.vendor_id = cr.vendor_id and cr.cust_room_type = rt.sys_id and  ")
                .append(" c.cust_id = cr.cust_id ")
                .append(getEMailKeyEquals(web, "cr."))
                
                .append(getEMailKeyEquals(web, "l."))
                .append(getEMailKeyEquals(web, "c."))
                .append(getEMailKeyEquals(web, "v."))
                .append(where == null ? "" : " " + where)
                .toString();

    }
    public String get01CustomerRoom_Vendor(String web, String where) {
        return (new StringBuilder())
                .append(" select 4,max(CR.DB_TIMESTAMP), sum(cr.total_cost) * -1 , date_format(max(cR.db_timestamp),'%a %b %d %Y %r'), v.vendor_name, ")
                .append(" 'Booked','',0,0,count(*),v.vendor_id ")
                .append(",-747")
                .append(" from customer_room cr ")
                .append(" left outer join flight_information f on cr.flt_id = f.flt_id ")
                        .append(getEMailKeyEquals(web, "f."))
                        .append(",")
                .append(" lookup l,vendor v,lookup_sys rt  ")
                .append(" where cr.lookup_id = l.lookup_id and v.vendor_id = cr.vendor_id and cr.cust_room_type = rt.sys_id and cr.cust_room_type in (-5,-6) ")
                .append(getEMailKeyEquals(web, "cr."))
                .append(getEMailKeyEquals(web, "l."))
                .append(getEMailKeyEquals(web, "v."))
                
                .append(where == null ? "" : " " + where)
                .append(" group by v.vendor_name,v.vendor_id  ")
                
                .toString();
    }
    public String get02_CustomerPaymentBOOKED_CustOnly(String web, String where) {
        return (new StringBuilder())
                .append("  select 2,cp.db_timestamp,cp.amt_rec ,date_format(cp.db_timestamp,'%a %b %d %Y %r') dateStr , ")
                .append("  CONCAT(c.last_name, ', ', c.first_name),ifnull(l.lookup_desc,sys.sys_desc) fee,ifnull(ck.lookup_desc,h.sys_desc)  ")
                .append("  ,sys.amt,sys.sys_id,cp.cust_id,cp.vendor_id ")
                .append(",if(h.sys_id = 0,l.lookup_id,h.sys_id * .01)")
                .append("  from ")
                .append("  customer c, ")
                .append("  customer_payment_BOOKED cp ")
                .append("  left outer join lookup l on cp.lookup_id = l.lookup_id  ")
                        .append(getEMailKeyEquals(web, "l."))
                       
                .append("  left outer join lookup ck on cp.chk_type= ck.lookup_id ")
                        .append(getEMailKeyEquals(web, "ck."))
                .append("  left outer join lookup_sys h on cp.vendor_type = h.sys_id, ")
                .append("  lookup_sys sys ")
                .append("  where ")
                .append("  c.cust_id = cp.cust_id and sys.sys_id = cp.payment_type ")
                .append(getEMailKeyEquals(web, "cp."))
                .append(getEMailKeyEquals(web, "c."))
                
                
                .append(where == null ? "" : " " + where)
                .toString();

    }
public String get03_customePaymentBOTH(String web, String where,boolean client) {
    return (new StringBuilder()).append("  select 3,cp.db_timestamp,cp.amt_rec").append(client ? "":" * -1")
            .append(",date_format(cp.db_timestamp,'%a %b %d %Y') dateStr , ")
            .append("  ifnull(CONCAT(c.last_name, ', ', c.first_name),v.vendor_name) nm , ")
            .append("  l.lookup_desc,ck.lookup_desc,sys.amt,sys.sys_id,cp.cust_id,cp.vendor_id ")
            .append(",-747")
            .append("  from  ")
            .append("  customer_payment cp ")
            .append("  left outer join customer c on cp.cust_id = c.cust_id ")
                    .append(getEMailKeyEquals(web, "c."))
            .append("  left outer join Vendor v on cp.vendor_id = v.vendor_id ")
                    .append(getEMailKeyEquals(web, "v."))
                    .append(",")
            .append("  lookup ck,lookup l,lookup_sys sys ")
            .append("  where cp.chk_type = ck.lookup_id and cp.lookup_id = l.lookup_id ")
            .append("  and sys.sys_id = cp.payment_type ")
            .append(getEMailKeyEquals(web, "cp."))
            .append(getEMailKeyEquals(web, "l."))
            .append(getEMailKeyEquals(web, "ck."))
            .append(where == null ? "" : " " + where)
            .toString();

    }
    // ========================================================================
    public String getVendorPaymentV3(String web) {
        return 
                get03_customePaymentBOTH(web, " and cp.vendor_id = ? and cp.sponsor_id =?",false) + " union"
                + get01CustomerRoom_Vendor(web, " and cr.vendor_id = ? and cr.sponsor_id =?")
                + " order by 5,11,2";
    }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
    public String getVendorPaymentV3_All(String web) {
        return 
                get03_customePaymentBOTH(web, " and cp.cust_id = 0 and cp.sponsor_id =?",false) + " union"
                + get01CustomerRoom_Vendor(web, "  and cr.sponsor_id =?")
                + " order by 2";
    }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
    public String getCustPaymentV3_fees(String web, boolean cust) {
        String order=" order by 6,7,9,5";
        if (cust) {
            return get02_CustomerPaymentBOOKED_CustOnly(web, " and c.cust_id = ? and cp.sponsor_id =?")+order;
        } else {
            return  get02_CustomerPaymentBOOKED_CustOnly(web, " and cp.sponsor_id = ?")+order;
        }

    }
    public String getCustPaymentV3(String web) {
        return get01CustomerRoom_Cust(web, " and c.cust_id = ? and cr.sponsor_id = ?") + " union"
                + get02_CustomerPaymentBOOKED_CustOnly(web, " and c.cust_id = ? and cp.sponsor_id =?") + " union"
                + get03_customePaymentBOTH(web, " and c.cust_id = ? and cp.sponsor_id =?",true)
                + " order by 2";

    }
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
    public String getCustPaymentV3_All(String web) {
        return get01CustomerRoom_Cust(web, " and cr.sponsor_id = ?") + " union"
                + get02_CustomerPaymentBOOKED_CustOnly(web, " and cp.sponsor_id = ?") + " union"
                + get03_customePaymentBOTH(web, " and cp.vendor_id = 0 and cp.sponsor_id = ?",true)
                + " order by 5,10,2";

    }
    

}
/*

private String getSql_1(String web, String where) {
        if (where == null) {
            where = "";
        }
        return " select 1 ord,c.cust_id as cust_id,cr.sponsor_id as sponsor_id,cr.cust_room_type , "
                + " c.last_name+', '+c.first_name cust_name, "
                + " sum(cr.total_cost) as total_cost,l.lookup_desc, "
                + " cr.db_timestamp,s.sys_desc,v.vendor_name,date_format(cr.db_timestamp,'%a %b %d %y %r') "
                + " from       customer_room cr,  customer c,lookup l,lookup_sys s,vendor v "
                + " where cr.cust_id = c.cust_id and  cr.cust_room_type in (-5,-6) and l.lookup_id = cr.lookup_id and "
                + " s.sys_id = cr.cust_room_type and v.vendor_id = cr.vendor_id "
                + getEMailKeyEquals(web, "cr.")
                + getEMailKeyEquals(web, "c.")
                + getEMailKeyEquals(web, "l.")
                + getEMailKeyEquals(web, "v.") + " " + where
                + " group by cr.sponsor_id,c.cust_id, c.last_name,c.first_name,cr.db_timestamp,cr.cust_room_type,"
                + " l.lookup_desc,s.sys_desc,v.vendor_name ";

    }

    private String getSql_2(String web, String where) {
        if (where == null) {
            where = "";
        }
        return " select 2 ord,c.cust_id as cust_id,cp.sponsor_id,-6 as cust_room_type, "
                + " c.last_name+' '+c.first_name,sum(cp.amt_rec) * -1,s.sys_desc, "
                + " cp.db_timestamp,l.lookup_desc ,ck.lookup_desc ,date_format(cp.db_timestamp,'%a %b %d %Y %r')  "
                + " from customer c, lookup_sys s, "
                + " customer_payment_BOOKED cp "
                + " left outer join lookup l on cp.lookup_id = l.lookup_id "
                + getEMailKeyEquals(web, "l.")
                + " left outer join lookup ck on cp.chk_type = ck.lookup_id "
                + getEMailKeyEquals(web, "ck.")
                + " where    "
                + " s.sys_id = cp.payment_type  AND c.cust_id = cp.cust_id  "
                + getEMailKeyEquals(web, "c.")
                + getEMailKeyEquals(web, "cp.") + " " + where
                + " group by cp.sponsor_id,c.cust_id,c.last_name,c.first_name,s.sys_desc,cp.db_timestamp ";

    }

    private String getSql_3(String web, String where) {
        if (where == null) {
            where = "";
        }
        return " select 3 ord,c.cust_id as cust_id,cp.sponsor_id,-6 as cust_room_type, "
                + " c.last_name+' '+c.first_name,sum(cp.amt_rec) * -1,l.lookup_desc, "
                + " cp.db_timestamp,ct.lookup_desc  ,'',date_format(cp.db_timestamp,'%a %b %d %y %r')  "
                + " from customer c, lookup l, "
                + " customer_payment cp "
                + " left outer join lookup ct on cp.chk_type = ct.lookup_id "
                + getEMailKeyEquals(web, "ct.")
                + "  where    "
                + " l.lookup_id = cp.lookup_id  AND c.cust_id = cp.cust_id  "
                + getEMailKeyEquals(web, "c.")
                + getEMailKeyEquals(web, "l.")
                + getEMailKeyEquals(web, "cp.") + " " + where
                + "  group by cp.sponsor_id,c.cust_id,c.last_name,c.first_name,l.lookup_desc,cp.db_timestamp ";

    }

    private String getSql_3_Vendor(String web, String where) {
        if (where == null) {
            where = "";
        }
        return " select 4 ord,'',cp.sponsor_id,-6 as cust_room_type, "
                + " '',sum(cp.amt_rec) * -1,l.lookup_desc, "
                + " cp.db_timestamp,ct.lookup_desc  ,'',date_format(cp.db_timestamp,'%a %b %d %y %r')  "
                + " from lookup l, "
                + " customer_payment cp "
                + " left outer join lookup ct on cp.chk_type = ct.lookup_id "
                + getEMailKeyEquals(web, "ct.")
                + "  where    "
                + " l.lookup_id = cp.lookup_id  AND c.cust_id = cp.cust_id  "
                + getEMailKeyEquals(web, "l.")
                + getEMailKeyEquals(web, "cp.") + " " + where
                + "  group by cp.sponsor_id,l.lookup_desc,cp.db_timestamp ";

    }
 SELECT cr.db_timestamp, cr.amt_rec,date_format(cr.db_timestamp,'%a %b %d %Y'),
 ls.amt,chk.lookup_desc,mth.lookup_desc,c.last_name,c.first_name,v.vendor_name,v.state
 FROM customer_payment cr 
 left outer join vendor v on cr.vendor_id = v.vendor_id
 left outer join customer c on cr.cust_id = c.cust_id,
 lookup_sys ls,
 lookup chk,
 lookup mth
 where ls.sys_id = cr.payment_type and cr.chk_type = chk.lookup_id and cr.lookup_id = mth.lookup_id
 ;
 */
