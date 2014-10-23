package jlRoomsCommon.flights.db;

import java.io.Serializable;
import sun.jdbc.rowset.*;
import java.text.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jlRoomsCommon._beans.flightInfoBean;
import jlRoomsCommon._objects.jlRoomsDbConnIinterface;
import jlRoomsCommon.sponsorHotel.db.sponsorHotelSql;
import jlRoomsDO.JlRoomsDataObjects;
import obj.db.v1.dbMgrInterface;

/**
 * Title: Room Tracking Web Applicaion (rtwa) Description: Copyright: Copyright
 * (c) 2003 Company: Jozzell, llc
 *
 * @author Lloyd Means
 * @version 1.0
 */
public class flightObj  implements Serializable{
    private sponsorHotelSql sponsorHotelSql;
    private jlRoomsDbConnIinterface jlRoomsFactory;
    private String webID;
    flightSql flightSql;
    private flightObj(){
        sponsorHotelSql = new sponsorHotelSql();
    }
//    /sponsorHotelSql
    public flightObj(String web) {
        this();
        this.webID = web;
    }
    
    public flightObj(jlRoomsDbConnIinterface x) {
        this();
        this.jlRoomsFactory = x;
    }
    public boolean isWeb() {
        return webID == null ? false : true;
    }

   public flightInfoBean getFlightInfoBean(int flt_id,dbMgrInterface db) {
        List<flightInfoBean> l = getFlightInfo(flightSql.sqlGetFlightBean(webID),new Object[]{ new Integer(flt_id)}, db);;
        if (l.size()>0) return l.get(0);
        return null;
    }
// ********************************************************************
    public List<flightInfoBean> getFlightAll(int sponHotelId, int cust) {
        return getFlightInfo(flightSql.sqlFltAll(this.webID), new Object[]{new Integer(sponHotelId), new Integer(cust)});
        //return getFlightInfo(flightSql.sqlFltAll2,new Object[]{new Integer(sponHotelId),new Integer(cust)});
    }
     public List<flightInfoBean> getFlightAll(int sponHotelId, int cust,dbMgrInterface db) {
        return getFlightInfo(flightSql.sqlFltAll(this.webID), new Object[]{new Integer(sponHotelId), new Integer(cust)});
        //return getFlightInfo(flightSql.sqlFltAll2,new Object[]{new Integer(sponHotelId),new Integer(cust)});
    }
// ********************************************************************
    public List<flightInfoBean> getFlightAllEvent(int sponsor, int cust) {

        return getFlightInfo(flightSql.sqlFltAll2(this.webID), new Object[]{new Integer(sponsor), new Integer(cust)});
    }
    public List<flightInfoBean> getFlightAllEvent(int sponsor, int cust,dbMgrInterface db) {

        return getFlightInfo(flightSql.sqlFltAll2(this.webID), new Object[]{new Integer(sponsor), new Integer(cust)});
    }
// ********************************************************************
    public List<flightInfoBean> getFlightWithConnectionSponsorHotelId(int sponHotelId) {
        return getFlightInfo(flightSql.sqlPickFligtWithConnSponHotelId(this.webID), new Object[]{new Integer(sponHotelId)});
    }
    public List<flightInfoBean> getFlightWithConnectionSponsorHotelId(int sponHotelId,dbMgrInterface db) {
        return getFlightInfo(flightSql.sqlPickFligtWithConnSponHotelId(this.webID), new Object[]{new Integer(sponHotelId)});
    }
// ********************************************************************
    public List<flightInfoBean> getFlightWithConnection(int fltId) {
        return getFlightInfo(flightSql.sqlPickFligtWithConn(this.webID), new Object[]{new Integer(fltId), new Integer(fltId)});
    }
     public List<flightInfoBean> getFlightWithConnection(int fltId,dbMgrInterface db) {
        return getFlightInfo(flightSql.sqlPickFligtWithConn(this.webID), new Object[]{new Integer(fltId), new Integer(fltId)});
    }
// ********************************************************************
    public List<flightInfoBean> getFlightConnection(int fltId) {
        return getFlightInfo(flightSql.sqlPickFligtConn(this.webID), new Object[]{new Integer(fltId)});
    }
    public List<flightInfoBean> getFlightConnection(int fltId,dbMgrInterface db) {
        return getFlightInfo(flightSql.sqlPickFligtConn(this.webID), new Object[]{new Integer(fltId)});
    }
// ********************************************************************
    public List<flightInfoBean> getSponsorFlightInfo(int sponsor) {

        return getFlightInfo(flightSql.sqlPickFlight(this.webID), new Object[]{new Integer(sponsor)});
    }
public List<flightInfoBean> getSponsorFlightInfo(int sponsor,dbMgrInterface db) {

        return getFlightInfo(flightSql.sqlPickFlight(this.webID), new Object[]{new Integer(sponsor)});
    }
    // ********************************************************************
    private List<flightInfoBean> getFlightInfo(String sql, Object obj[]) {
        return getFlightInfo(sql,obj,this.jlRoomsFactory);
    }
    private List<flightInfoBean> getFlightInfo(String sql, Object obj[],dbMgrInterface db) {
        List<flightInfoBean> v = new ArrayList<flightInfoBean>();
        CachedRowSet rs;
        try {
            rs = db.getCachedRowSet(sql, obj);
            while (rs.next()) {
                v.add(getFlightInfoBean(rs));
            }
        } catch (Exception e) {
            Logger.getLogger(flightObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return v;
    }

    public  flightInfoBean getFlightInfoBean(CachedRowSet rs) {
        flightInfoBean bean = new flightInfoBean();
        try {
            bean.setFltId(rs.getInt(1));

            bean.setFltArrivalDate(rs.getDate(2));
            //bean.setFltArrivalTime(rs.getTime(17));
            bean.setFltArrivalPlace(rs.getString(3));

            bean.setFltComment(rs.getString(4));

            bean.setFltDepartureDate(rs.getDate(5));
            
           //bean.setFltDepartureTime(rs.getTime(18));
            
            bean.setFltDeparturePlace(rs.getString(6));

            bean.setFltNumber(rs.getString(7));
            bean.setFltType(rs.getDouble(8));
            bean.setHotelId(rs.getInt(9));
            bean.setSponsorHotelId(rs.getInt(10));

            bean.setRollupId(rs.getInt(11));
            bean.setSponsorId(rs.getInt(12));
            bean.setConnFlt(rs.getInt(13));
            bean.setVendorId(rs.getInt(14));
            bean.setVendorName(rs.getString(15));
            bean.setFlagInd(rs.getInt(16));
            //bean.setFltArrivalTime(rs.getTime(17));
//            bean.setFltDepartureTime(rs.getTime(18));
            bean.setVendorType(rs.getInt(19));

        } catch (Exception e) {
            Logger.getLogger(flightObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return bean;
    }
    // ********************************************************************
    public int getFltId(flightInfoBean bean) {
        return getFltId(bean,this.jlRoomsFactory);
    }
    public int getFltId(flightInfoBean bean,dbMgrInterface db) {
        CachedRowSet rs = null;

        int i = 0;
        try {
            rs = db.getCachedRowSet(flightSql.sqlFltId(this.webID),
                    new Object[]{
                        new Integer(bean.getVendorId()),
                        new Integer(bean.getSponsorId()),
                        bean.getCreated()
                    });
            while (rs.next()) {
                i = rs.getInt(1);
            }
        } catch (Exception e) {
            Logger.getLogger(flightObj.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            db.closeCachedRowSet(rs);
        }
        return i;
    }
    // ********************************************************************
    public int updateFlight(flightInfoBean bean) {
        return updateFlight(bean,this.jlRoomsFactory);
    }
    public int updateFlight(flightInfoBean bean,dbMgrInterface db) {
        bean.setCreated(JlRoomsDataObjects.getTimeStampString());
        boolean insert = bean.getFltId() == 0;
        String sql = (insert ? flightSql.sqlInsert(this.webID) : flightSql.sqlUpdate(this.webID));
        try {
            db.updateDatabase(sql, getFlightInfoObject(bean));
            if (bean.getRollupId() > 0) {
                db.updateDatabase(flightSql.sqlUpdateConnFlight(this.webID), new Object[]{new Integer(bean.getRollupId())});
            } else {
                int x;
                if (bean.getFltId() == 0) {
                    x = getFltId(bean,db);
                } else {
                    x = bean.getFltId();
                }
                db.updateDatabase(sponsorHotelSql.sqlUpdate_dsp_str1(this.webID),
                        new Object[]{
                            JlRoomsDataObjects.getAirTextSub(bean.getVendorType())
                            + " # " + bean.getFltNumber()
                            + " Departing: " + new SimpleDateFormat("EEE MMM dd yyyy hh:mm a").format(bean.getFltDepartureDate())
                            + " From " + bean.getFltDeparturePlace(),
                            new Integer(x),
                            new Integer(bean.getSponsorHotelId())});

               
                return x;
            }

        } catch (Exception e) {
            Logger.getLogger(flightObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return bean.getFltId();

    }

    public  java.sql.Time getTime(java.sql.Time t) {
        if (t == null) {
            return new java.sql.Time(0);
        }
        return t;
    }

    

    public  Object[] getFlightInfoObject(flightInfoBean bean) {
        //Calendar.getInstance().getTime().getTime();
        
        if (bean.getFltId() == 0) {
            
            
            return new Object[]{
                        JlRoomsDataObjects.getDate(bean.getFltArrivalDate()),
                        bean.getFltArrivalPlace() == null ? "" : bean.getFltArrivalPlace(),
                        bean.getFltComment() == null ? "" : bean.getFltComment(),
                        JlRoomsDataObjects.getDate(bean.getFltDepartureDate()),
                        bean.getFltDeparturePlace() == null ? "" : bean.getFltDeparturePlace(),
                        bean.getFltNumber() == null ? "" : bean.getFltNumber(),
                        new Double(bean.getFltType()),
                        new Integer(bean.getHotelId()),
                        new Integer(bean.getSponsorHotelId()),
                        new Integer(bean.getRollupId()),
                        new Integer(bean.getSponsorId()),
                        new Integer(bean.getConnFlt()),
                        new Integer(bean.getVendorId()),
                        new Integer(bean.getFlagInd()),
                        "", //bean.getFltArrivalTime().toString(),
                        "", //bean.getFltDepartureTime().toString(),
                        bean.getCreated(),
                        new Integer(bean.getFlagInd())
                    };

        } else {
            return new Object[]{
                        JlRoomsDataObjects.getDate(bean.getFltArrivalDate()),
                        bean.getFltArrivalPlace() == null ? "" : bean.getFltArrivalPlace(),
                        bean.getFltComment() == null ? "" : bean.getFltComment(),
                        JlRoomsDataObjects.getDate(bean.getFltDepartureDate()),
                        bean.getFltDeparturePlace() == null ? "" : bean.getFltDeparturePlace(),
                        bean.getFltNumber() == null ? "" : bean.getFltNumber(),
                        new Double(bean.getFltType()),
                        new Integer(bean.getConnFlt()),
                        new Integer(bean.getFlagInd()),
                        "",
                        "",
                        new Integer(bean.getFltId())
                    };
        }
    }
    /*
     *
     *
     * /*
     ************************************************************
     ************************************************************
     */

    public flightInfoBean getFlightInfoBeanXXX(int cust) {
        Object ary[] = new Object[1];
        ary[0] = new Integer(cust);
        //return getFlightInfoBean(ary, sqlFlight.sqlFlight);
        return null;
    }
    // **************************************************************************
     public flightInfoBean getFlightInfoBeanXXX(Object[] ary, String sql) {
         return getFlightInfoBeanXXX(ary,sql,this.jlRoomsFactory);
     }
    public flightInfoBean getFlightInfoBeanXXX(Object[] ary, String sql,dbMgrInterface db) {
        flightInfoBean bean = new flightInfoBean();
        CachedRowSet rs = null;
        try {
            rs = null;
            while (rs.next()) {
                if (bean == null) {
                    bean = getFlightInfoBean(rs);
                }
            }
        } catch (Exception e) {
            Logger.getLogger(flightObj.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            db.closeCachedRowSet(rs);
        }

        return bean;
    }
    public List<flightInfoBean> getFligthInfoXXX(int cust, int reg) {
        List<flightInfoBean> ary = new ArrayList();
        //jlRoomsFactory jlRoomsFactory = new jlRoomsFactory();
        CachedRowSet rs = null;
        try {
            //CachedRowSet rs = jlRoomsFactory.getCachedRowSet(sqlFlight.sqlFlightBooked, new Object[] {new Integer(cust), new Integer(reg)});

            if (rs == null) {
                ary.add(new flightInfoBean());
            } else {
                while (rs.next()) {
                    ary.add(getFlightInfoBean(rs));
                }
            }
        } catch (Exception e) {
            Logger.getLogger(flightObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return ary;
    }
}
