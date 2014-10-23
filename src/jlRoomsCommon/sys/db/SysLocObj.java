package jlRoomsCommon.sys.db;

import java.io.Serializable;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import jlRoomsCommon._beans.vendorBean;
import jlRoomsCommon._objects.jlRoomsDbConnIinterface;
import obj.db.v1.dbMgrInterface;

import sun.jdbc.rowset.*;

public class SysLocObj  implements Serializable {
    private SysLocSql SysLocSql;
    private jlRoomsDbConnIinterface jlRoomsFactory;

    
    public SysLocObj(){
        SysLocSql = new SysLocSql();
    }
    public SysLocObj(jlRoomsDbConnIinterface x) {
        this();
        this.jlRoomsFactory = x;
    }
    // ********************************************************************

    public vendorBean getVendorBean(int id) {
        return getVendorBean(id, this.jlRoomsFactory);
    }

    public vendorBean getVendorBean(int id, dbMgrInterface db) {
        CachedRowSet rs = null;
        try {
            rs = db.getCachedRowSet(SysLocSql.sqlGetSys, new Object[]{new Integer(id)});
            while (rs.next()) {
                return getVendorBean(rs);
            }

        } catch (Exception e) {
            Logger.getLogger(SysLocObj.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            db.closeCachedRowSet(rs);
        }
        return null;
    }
    // *************************************************  

    public vendorBean getVendorBeanSysType(int id) {
        return getVendorBeanSysType(id, this.jlRoomsFactory);
    }

    public vendorBean getVendorBeanSysType(int id, dbMgrInterface db) {
        CachedRowSet rs = null;
        try {
            rs = db.getCachedRowSet(SysLocSql.sqlGetSysType, new Object[]{new Integer(id)});
            while (rs.next()) {
                return getVendorBean(rs);
            }

        } catch (Exception e) {
            Logger.getLogger(SysLocObj.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            db.closeCachedRowSet(rs);
        }
        return null;
    }

// *************************************************  dbMgrInterface db
    public List<vendorBean> getVendorBean(String str) {
        return getVendorBean(SysLocSql.sqlSysSeekAll, new Object[]{"%" + str + "%"});
    }

    public List<vendorBean> getVendorBean(String str, dbMgrInterface db) {
        return getVendorBean(SysLocSql.sqlSysSeekAll, new Object[]{"%" + str + "%"}, db);
    }
// *************************************************  dbMgrInterface db

    public List<vendorBean> getVendorBean(String str, int type) {
        return getVendorBean(SysLocSql.sqlSysSeek, new Object[]{"%" + str + "%", new Integer(type)});
    }

    public List<vendorBean> getVendorBean(String str, int type, dbMgrInterface db) {
        return getVendorBean(SysLocSql.sqlSysSeek, new Object[]{"%" + str + "%", new Integer(type)});
    }
    // *************************************************  

    public List<vendorBean> getVendorBean(String sql, Object[] obj) {
        return getVendorBean(sql, obj, this.jlRoomsFactory);
    }

    public List<vendorBean> getVendorBean(String sql, Object[] obj, dbMgrInterface db) {
        List<vendorBean> v = new ArrayList<vendorBean>();
        CachedRowSet rs = null;
        try {
            rs = db.getCachedRowSet(sql, obj);
            while (rs.next()) {
                v.add(getVendorBean(rs));
            }
        } catch (Exception e) {
            Logger.getLogger(SysLocObj.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            db.closeCachedRowSet(rs);
        }


        return v;
    }

    // *************************************************  
    public int updateVendor(vendorBean bean) {
        return updateVendor(bean, this.jlRoomsFactory);
    }

    public int updateVendor(vendorBean bean, dbMgrInterface db) {
        String sql = bean.getVendorId() == 0 ? SysLocSql.sqlSysInsert : SysLocSql.sqlSysUpdate;
        CachedRowSet rs = null;
        try {
            db.updateDatabase(sql, updateVendorData(bean));
            if (bean.getVendorId() == 0) {
                rs = db.getCachedRowSet(SysLocSql.sqlSysGetId, new Object[]{bean.getVendorName()});
                while (rs.next()) {
                    bean.setVendorId(rs.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.closeCachedRowSet(rs);
        }
        return bean.getVendorId();
    }

    // *************************************************  
    private Object[] updateVendorData(vendorBean bean) {
        java.sql.Timestamp ts = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        return new Object[]{
            bean.getVendorName(),
            bean.getVendor_code() == null ? "" : bean.getVendor_code(),
            new Integer(bean.getVendorInd()),
            new Integer(bean.getVendorType()),
            new Integer(bean.getFlag()),
            bean.getAddr() == null ? "" : bean.getAddr(),
            bean.getAddr2nd() == null ? "" : bean.getAddr2nd(),
            bean.getCity() == null ? "" : bean.getCity(),
            bean.getState() == null ? "" : bean.getState(),
            bean.getZip() == null ? "" : bean.getZip(),
            bean.getZipPlus() == null ? "" : bean.getZipPlus(),
            bean.getPhone() == null ? "" : bean.getPhone(),
            bean.getFax() == null ? "" : bean.getFax(),
            bean.getEMail() == null ? "" : bean.getEMail(),
            bean.getPhoneExt() == null ? "" : bean.getPhoneExt(),
            bean.getVendorId() == 0 ? null : new Integer(bean.getVendorId())
        };
    }

    private vendorBean getVendorBean(CachedRowSet cr) throws Exception {
        vendorBean bean = new vendorBean();
        try {
            bean.setVendorId(cr.getInt(1));
            bean.setVendorName(cr.getString(2));
            bean.setVendor_code(cr.getString(3));
            bean.setVendorInd(cr.getInt(4));
            bean.setVendorType(cr.getInt(5));
            bean.setFlag(cr.getInt(6));
            bean.setAddr(cr.getString(7));
            bean.setAddr2nd(cr.getString(8));
            bean.setCity(cr.getString(9));
            bean.setState(cr.getString(10));
            bean.setZip(cr.getString(11));
            bean.setZipPlus(cr.getString(12));
            bean.setPhone(cr.getString(13));
            bean.setFax(cr.getString(14));
            bean.setEMail(cr.getString(15));
            bean.setPhoneExt(cr.getString(20));

            // rollup 16
            //vendor_first 17
            // region id 18
            // parking 19


            // contact person 21

        } catch (Exception e) {
            throw new Exception(e);
        }
        return bean;
    }
}
