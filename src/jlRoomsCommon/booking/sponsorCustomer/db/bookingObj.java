package jlRoomsCommon.booking.sponsorCustomer.db;

import java.io.Serializable;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import jlRoomsCommon._beans.bookingBean;
import jlRoomsCommon._beans.custRmBean;
import jlRoomsCommon._beans.viewBean;
import obj.html.*;
import sun.jdbc.rowset.*;
import jlRoomsCommon._objects.jlRoomsDbConnIinterface;

/**
 * Title:        Room Tracking Web Applicaion (rtwa)
 * Description:
 * Copyright:    Copyright (c) 2003
 * Company:      Jozzell, llc
 * @author Lloyd Means
 * @version 1.0
 */

public class bookingObj   implements Serializable  {
    private jlRoomsDbConnIinterface jlRoomsFactory;
    private String webID = null;
    bookingSql bookingSql;
    public bookingObj(){
        bookingSql = new bookingSql();
    }
    public bookingObj(String x){
        this();
        this.webID = x;
    }
    public bookingObj(jlRoomsDbConnIinterface x) {
        this();
      this.jlRoomsFactory = x;
    }
  public  List<viewBean> getCustBookingList(int cust, int spon) {
    List<viewBean> v = new ArrayList<viewBean>();
    CachedRowSet rs = null;

    try {
      rs = jlRoomsFactory.getCachedRowSet(
          bookingSql.sqlCustBookingList(this.webID),
          new Object[] {new Integer(cust), new Integer(spon)});
      String s=null;
      while (rs.next()) {
        s = rs.getString(4)+": " + rs.getString(9)+" @ " + jlRoomsFactory.getDollarFormat(rs.getDouble(3))+
                " "+rs.getString(13);
        //String s = rs.getString(8) + " " + rs.getString(9) + " " +
            //rs.getString(10) + " @ ";
        //for (int i = 4; i <= 6; i++) s +=(rs.getString(i) == null ? "" : " " + rs.getString(i));
        //s += "[" + jlRoomsFactory.getDollarFormat(rs.getDouble(3)) + "] " +
            //jlRoomsFactory.getDateFormat(rs.getDate(2));
        v.add(new viewBean(rs.getInt(1), s, rs.getInt(11)));
      }
    } catch (Exception e) {
       
      Logger.getLogger(bookingObj.class.getName()).log(Level.SEVERE, null, e);
    }
    return v;
  }
  
  public  void main(String[] arg) {
    Hashtable list = new Hashtable();
    CachedRowSet rs = null;
    list.put(new Integer(44), new Integer(100));
    list.put(new Integer(11), new Integer(1000));
    list.put(new Integer(33), new Integer(1021));
    list.put(new Integer(22), new Integer(1322));
    System.out.println(list.get(new Integer(333)));

  }

 

  /*
   ********************************************************************
   */
 

  /*
   ********************************************************************
   */
  

  public List<bookingBean> getAvailableBooking(int region, int sponsor) throws Exception {
    List<bookingBean>  v = new ArrayList<bookingBean>();

    CachedRowSet rs = null;
    Object ary[] = new Object[2];
    ary[0] = new Integer(region);
    ary[1] = new Integer(sponsor);
    try {
      //rs = dbConn.getCachedRowSet(this.spf_qry_avl_rm_booking,ary);
      while (rs.next()) {
        

        bookingBean bean = new bookingBean();
        bean.setBlockId(rs.getInt(1));
        bean.setVendorTypeDesc(rs.getString(2));
        bean.setVendorId(rs.getInt(3));
        bean.setVendorName(rs.getString(4));
        bean.setRmTypeDesc(rs.getString(5));
        bean.setRoomsBooked(rs.getDouble(6));
        bean.setAvailableRooms(rs.getDouble(7));
        bean.setRoomCost(rs.getDouble(8));
        bean.setFlag(rs.getInt(9));
        bean.setCutoffDate(rs.getDate(10));

        v.add(bean);
      }
    } catch (Exception e) {
        Logger.getLogger(bookingObj.class.getName()).log(Level.SEVERE, null, e);
      throw (e);
    }
    //v.trimToSize();
    return v;
  }

  public List<custRmBean> getCustBooking(int sp, int cust) {
    List<custRmBean> v = new ArrayList<custRmBean>();

    CachedRowSet rs = null;
    Object ary[] = new Object[2];
    ary[0] = new Integer(sp);
    ary[1] = new Integer(cust);
    try {
      //rs = dbConn.getCachedRowSet(this.spf_qry_cust_booking,ary);
      while (rs.next()) {
        custRmBean bean = new custRmBean();
        bean.setCustRoomId(rs.getInt(1));
        bean.setRmTypeDesc(rs.getString(2));
        //bean.setCustRmDesc(rs.getString(3));
        //bean.setVendorDesc(rs.getString(4));
        //bean.setEffDateDesc(rs.getString(5));
        //bean.setEndDateDesc(rs.getString(6));
        bean.setRmCost(rs.getDouble(7));
        bean.setCustRoomCnt(rs.getDouble(8));
        v.add(bean);
      }
    } catch (Exception e) {
      Logger.getLogger(bookingObj.class.getName()).log(Level.SEVERE, null, e);
    }
   
    return v;
  }

  /*
   ****************************************************
   ****************************************************
   */
  public Vector XXXgetEventAvalBooking(int sp, String path) {
    htmlTableParm
        parm = new htmlTableParm(),
        edit = new htmlTableParm(),
        book = new htmlTableParm();
    Hashtable hash = this.getCurrBooking(sp);
    Vector v = new Vector();
    CachedRowSet rs = null;
    Object ary[] = new Object[1];
    ary[0] = new Integer(sp);
    double curr = 0, last = 0;
    try {
      Double i = null;
      double val;
      rs = jlRoomsFactory.getCachedRowSet(bookingSql.sql_rooms_avl(this.webID), ary);
      String href = "";
      while (rs.next()) {
        curr = rs.getDouble(8);
        i = (Double) hash.get(new Double(rs.getDouble(2)));
        val = 0;
        if (i != null) val = i.intValue();
        if (curr != last) v.add(getPgBreak(rs.getString(1), 8));
        href = "&block_id=" + rs.getInt(2) + "&lookup_id=" + rs.getInt(8);
        parm.setHRef(href);
        book.setHRef(path + "?appCat=200&subCat=2" + href);
        edit.setHRef(path + "?appCat=200&subCat=1" + href);

        v.add(
            htmlTable.getCellRow(
                htmlTable.getCell(rs.getString(3)) +
                htmlTable.getCell(rs.getString(4), parm) +
                htmlTable.getCell(rs.getDouble(5)) +
                htmlTable.getCell(rs.getDouble(7)) +
                htmlTable.getCell(val) +
                htmlTable.getCell("Book", book) +
                htmlTable.getCell("Edit", edit)
            ));
        last = curr;
      }
    } catch (Exception e) {
      Logger.getLogger(bookingObj.class.getName()).log(Level.SEVERE, null, e);
    }
    return v;
  }

  /*
   ****************************************************
   ****************************************************
   */
  public Vector XXXgetAvalHotelBooking(int sp, int hotel) {
    htmlTableParm parm = new htmlTableParm();
    Hashtable hash = this.getCurrBooking(sp);
    Vector v = new Vector();
    CachedRowSet rs = null;
    Object ary[] = new Object[2];
    ary[0] = new Integer(hotel);
    ary[1] = new Integer(sp);
    double curr = 0, last = 0;
    try {
      Double i = null;
      double val;
      rs = jlRoomsFactory.getCachedRowSet(bookingSql.sql_rooms_avl_hotel(this.webID), ary);
      String str = "";
      while (rs.next()) {
        curr = rs.getDouble(8);
        i = (Double) hash.get(new Double(rs.getDouble(2)));
        val = 0;
        if (i != null) val = i.intValue();
        if (curr != last) v.add(getPgBreak(rs.getString(1), 6));
        parm.setHRef("&block_id=" + rs.getInt(2));
        v.add(
            htmlTable.getCellRow(
                htmlTable.getCell(rs.getString(3)) +
                htmlTable.getCell(rs.getString(4), parm) +
                htmlTable.getCell(rs.getDouble(5)) +
                htmlTable.getCell(rs.getDouble(7)) +
                htmlTable.getCell(val)
            ));
        last = curr;
      }
    } catch (Exception e) {
      Logger.getLogger(bookingObj.class.getName()).log(Level.SEVERE, null, e);
    }
    return v;
  }

  /*
   ****************************************************
   ****************************************************
   */
  public String getPgBreak(String s, int cnt) {
    htmlTableParm parm = new htmlTableParm();
    parm.setHeader(true);
    parm.setColumnSpan(cnt);
    parm.setBackGroundColor(parm.color_light_gray);
    parm.setAlignment(parm.align_left);
    parm.setBold(true);
    return htmlTable.getCellRow(htmlTable.getCell(s, parm), parm);
  }

  /*
   ****************************************************
   ****************************************************
   */
  private Hashtable getCurrBooking(int sp) {
    Hashtable list = new Hashtable();
    CachedRowSet rs = null;
    Object ary[] = new Object[1];
    ary[0] = new Integer(sp);
    try {
      rs = jlRoomsFactory.getCachedRowSet(bookingSql.sql_rooms_booked(this.webID), ary);
      while (rs.next()) list.put(new Integer(rs.getInt(2)),
                                 new Double(rs.getDouble(1)));
    } catch (Exception e) {
      Logger.getLogger(bookingObj.class.getName()).log(Level.SEVERE, null, e);
    }
    return list;
  }

  public Vector XXXgetWizardHotel(int i) {
    htmlTableParm parm = new htmlTableParm();
    Vector v = new Vector();
    Object obj[] = new Object[1];
    obj[0] = new Integer(i);
    CachedRowSet rs = null;
    double curr, last = 0;
    try {
      rs = this.jlRoomsFactory.getCachedRowSet(bookingSql.sql_hotel(this.webID), obj);
      while (rs.next()) {
        curr = rs.getDouble(8);
        if (curr != last) v.add(this.getPgBreak(rs.getString(9), 4));
        parm.setHRef("&hotel_id=" + rs.getDouble(10));
        v.add(
            htmlTable.getCellRow(
                htmlTable.getCell(rs.getString(5), parm) +
                htmlTable.getCell(rs.getString(6)) +
                htmlTable.getCell(rs.getString(2) + " - " + rs.getString(3)) +
                htmlTable.getCell(rs.getString(4))
            ));
        last = curr;
      }
    } catch (Exception e) {
      Logger.getLogger(bookingObj.class.getName()).log(Level.SEVERE, null, e);
    }

    return v;
  }

}
