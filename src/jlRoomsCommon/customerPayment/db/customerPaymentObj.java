package jlRoomsCommon.customerPayment.db;

import java.io.Serializable;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import jlRoomsCommon._beans.custPaymentBean;
import jlRoomsCommon._beans.custRmBean;
import jlRoomsCommon._objects.jlRoomsDbConnIinterface;
import jlRoomsDO.JlRoomsDataObjects;
import obj.db.v1.dbMgrInterface;

import sun.jdbc.rowset.*;

/**
 * Title: Room Tracking Web Applicaion (rtwa) Description: Copyright: Copyright
 * (c) 2003 Company: Jozzell, llc
 *
 * @author Lloyd Means
 * @version 1.0
 */
public class customerPaymentObj  implements Serializable{
    customerPaymentSql customerPaymentSql;
    private jlRoomsDbConnIinterface jlRoomsFactory;
    private String webID = null;
    public customerPaymentObj(){
        customerPaymentSql = new customerPaymentSql();
    }
    public customerPaymentObj(String x) {
        this.webID = x;
    }

    public customerPaymentObj(jlRoomsDbConnIinterface x) {
        this.jlRoomsFactory = x;

    }

    // *************************************************  
    public void addPayment(custPaymentBean bean) {
        addPayment(bean, this.jlRoomsFactory);
    }

    public void addPayment(custPaymentBean bean, dbMgrInterface db) {

        try {
            db.updateDatabase(customerPaymentSql.sqlInsertPayment(this.webID),
                    getValuePayment(bean));
        } catch (Exception e) {
            Logger.getLogger(customerPaymentObj.class.getName()).log(Level.SEVERE, null, e);
        }
    }
// ************************************************************************
    
    public void addPaymentBlock(custPaymentBean bean) {
        addPaymentBlock(bean, this.jlRoomsFactory);
    }
   
    public void addPaymentBlock(custPaymentBean bean, dbMgrInterface db) {
        if (bean.getPaymentType() == 0 && bean.getAmtRec() == 0) {
            return;
        }
        try {
            
            db.updateDatabase(customerPaymentSql.sqlInsertBooked(this.webID),
                    getValueBooked(bean));
        } catch (Exception e) {
            Logger.getLogger(customerPaymentObj.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    // ************************************************************************

    public void addDefaultPayment(custRmBean bean) {
        addDefaultPayment(bean, jlRoomsFactory.getLoginId(), this.jlRoomsFactory);
    }

    public void addDefaultPayment(custRmBean bean, int id, dbMgrInterface db) {
        try {

            db.updateDatabase(customerPaymentSql.sqlInsertBookedFirst(this.webID),
                    new Object[]{bean.getCustRoomId()});

            if (bean.getCustRoomType() == -23 || bean.getCustRoomType() == -30 || (bean.getRmCost()== 0)) {
                return;
            }

            //for (int i = 0; i <= 2; i++) {
            custPaymentBean b = new custPaymentBean();
            b.setCustRoomId(bean.getCustRoomId());

            b.setBlockId(bean.getBlockId());
            b.setCustId(bean.getCustId());
            b.setSponsor(bean.getSponsor());
            b.setVendorType(bean.getVendorType());
            b.setUserId(id);

            if (bean.getBlockHotelFee() != 0) {
                b.setAmtRec(bean.getBlockHotelFee()
                        * bean.getCustRoomCnt());
                b.setPaymentType(-102);
            }
            addPaymentBlock(b, db);
            //}

        } catch (Exception e) {
            Logger.getLogger(customerPaymentObj.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private Object[] getValueBooked(custPaymentBean bean) {
        if (bean.getDbTimestamp() == null) {
            bean.setDbTimestamp(Calendar.getInstance().getTime());
        }
        return new Object[]{
            new Integer(bean.getCustRoomId()),
            new Integer(bean.getCustId()),
            new Integer(bean.getUserId()),
            new Integer(bean.getPaymentType()),
            new Double(bean.getAmtRec()),
            new Integer(bean.getBlockId()),
            new Integer(bean.getVendorType()),
            new Integer(bean.getSponsor()),
            new Integer(0),
            new Integer(bean.getVendorId()),
            new Integer(bean.getLookupId()),
            new Integer(bean.getChkType())//,
            //JlRoomsDataObjects.getDate(bean.getDbTimestamp())
        };
    }

    private Object[] getValuePayment(custPaymentBean bean) {
        if (bean.getDbTimestamp() == null) {
            bean.setDbTimestamp(Calendar.getInstance().getTime());
        }
        return new Object[]{
            new Integer(bean.getCustRoomId()),
            new Integer(bean.getCustId()),
            new Integer(bean.getUserId()),
            new Integer(bean.getPaymentType()),
            new Double(bean.getAmtRec()),
            //bean.getPayDesc() == null ? "" : bean.getPayDesc(),
            // bean.getChkNumber() == null ? "" : bean.getChkNumber(),
            // new Integer(bean.getChkType()),
            new Integer(bean.getBlockId()),
            new Integer(bean.getVendorType()),
            new Integer(bean.getSponsor()),
            new Integer(0),
            new Integer(bean.getVendorId()),
            new Integer(bean.getLookupId()),
            bean.getComment() == null ? "" : bean.getComment(),
            bean.getNote() == null ? "" : bean.getNote(),
            (new JlRoomsDataObjects()).getDate(bean.getDbTimestamp()),
            //new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()),
            new Integer(bean.getChkType())
        //new java.sql.Date( bean.getDbTimestamp().getTime())
        };

    }

    // ********************************************************************************************
    // ********************************************************************************************
    public custPaymentBean getCustPayment(int payId) {
        return getCustPayment(payId, this.jlRoomsFactory);
    }

    public custPaymentBean getCustPayment(int payId, dbMgrInterface db) {
        return getCustPayment(new Object[]{new Integer(payId)}, customerPaymentSql.sqlPayment(this.webID), db);
    }

    /*
     ****************************************************
     ****************************************************
     */
    private custPaymentBean getCustomerPayment(CachedRowSet rs) {
        custPaymentBean bean = new custPaymentBean();
        try {
            bean.setCustPayment(rs.getInt(1));
            //bean.setCustRmId(rs.getInt(2));
            bean.setPaymentType(rs.getInt(3));
            //bean.setPayment(rs.getDouble(4));
            //bean.setChkNumber(rs.getString(5));
            bean.setPaymentType(rs.getInt(6));
            bean.setRegion(rs.getInt(7));
            //bean.setTimestamp(rs.getDate(8));
            bean.setRoommateId(rs.getInt(9));
            bean.setSponsor(rs.getInt(10));
            //bean.setPaymentTypeDesc(rs.getString(11));
            //bean.setCheckTypeDesc(rs.getString(12));
        } catch (Exception e) {
            Logger.getLogger(customerPaymentObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return bean;
    }

    /*
     ****************************************************
     ****************************************************
     */
    public custPaymentBean getCustPayment(Object ary[], String sql) {
        return getCustPayment(ary, sql, this.jlRoomsFactory);
    }

    public custPaymentBean getCustPayment(Object ary[], String sql, dbMgrInterface db) {
        custPaymentBean bean = new custPaymentBean();
        CachedRowSet rs = null;

        try {
            rs = db.getCachedRowSet(sql, ary);
            while (rs.next()) {
                bean = getCustomerPayment(rs);
            }
        } catch (Exception e) {
            Logger.getLogger(customerPaymentObj.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
            }
            ;
        }
        return bean;
    }

    /*
     ****************************************************
     ****************************************************
     */
}
