package jlRoomsCommon.rpt.db.event;

import java.io.Serializable;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import jlRoomsCommon._objects.jlRoomsDbConnIinterface;
import sun.jdbc.rowset.*;

public class EventOverViewObj implements Serializable {
    EventOverViewSql EventOverViewSql;
    private jlRoomsDbConnIinterface jlRoomsFactory;
    public EventOverViewObj(){
        EventOverViewSql = new EventOverViewSql();
    }
    public EventOverViewObj(jlRoomsDbConnIinterface x) {
        this();
        this.jlRoomsFactory = x;

    }

    public CachedRowSet getCRSCustomer(int cust, int sponsor) {
        return getCashedRowSet(EventOverViewSql.sqlPg01CustSummary,
                new Object[]{new Integer(cust), new Integer(sponsor)});
    }

    public int getValue(CachedRowSet cr) {
        try {
            return cr.getInt(1);
        } catch (Exception e) {
            Logger.getLogger(EventOverViewObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return 1;
    }

    public CachedRowSet getCRSOverView(int sponsor) {
        return getCashedRowSet(EventOverViewSql.sqlPg01Summary, new Object[]{new Integer(sponsor)});
    }

    public CachedRowSet getCashedRowSet(String sql, Object[] ary) {
        //Vector v = new Vector();
        CachedRowSet rs = null;
        try {
            return jlRoomsFactory.getCachedRowSet(sql, ary);

        } catch (Exception e) {
            Logger.getLogger(EventOverViewObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return rs;
    }

}
