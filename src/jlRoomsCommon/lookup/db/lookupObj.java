package jlRoomsCommon.lookup.db;

import java.io.Serializable;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import sun.jdbc.rowset.*;
import jlRoomsCommon._beans.lookupBean;
import jlRoomsCommon._objects.jlRoomsDbConnIinterface;
import obj.db.v1.dbMgrInterface;

/**
 * Title:        Room Tracking Web Applicaion (rtwa)
 * Description:
 * Copyright:    Copyright (c) 2003
 * Company:      Jozzell, llc
 * @author Lloyd Means
 * @version 1.0
 */
public class lookupObj  implements Serializable {

    private jlRoomsDbConnIinterface jlRoomsFactory;
    private String webID;
    lookupSql lookupSql;
    public lookupObj(){
        lookupSql = new lookupSql();
    }
    public lookupObj(String x){
        this();
        this.webID= x;
    }
    public lookupObj(jlRoomsDbConnIinterface x) {
        this();
        this.jlRoomsFactory = x;

    }
   
    public void updateLookup(lookupBean bean) {
        updateLookup(bean,jlRoomsFactory);
    }
    public void updateLookup(lookupBean bean, dbMgrInterface dbMgr) {
        Object obj[];
        String sql;
        if (bean.getLookupId() == 0) {
            sql = lookupSql.sqlInsert(this.webID);
            obj = new Object[]{
                        new Integer(bean.getLookupType()),
                        new Integer(bean.getLookupRollupId()),
                        bean.getDesc(),
                        new Integer(bean.getFlag())
                    };
        } else {
            sql = lookupSql.sqlUpdate(this.webID);
            obj = new Object[]{
                        bean.getDesc(),
                        new Integer(bean.getFlag()),
                        new Integer(bean.getLookupId())
                    };

        }
        try {
            dbMgr.updateDatabase(sql, obj);
        } catch (Exception e) {
            Logger.getLogger(lookupObj.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    // ******************************************************************************
    public List<lookupBean> getLookupTypeRollupList(int type, int rollup) {
        return getLookupTypeRollupList(type,rollup,jlRoomsFactory);
    }
    public List<lookupBean> getLookupTypeRollupList(int type, int rollup,dbMgrInterface dbMgr) {
        return getLookup(lookupSql.sqlLookupTypeRollup(this.webID), new Object[]{new Integer(type), new Integer(rollup),},dbMgr);
    }
    // ******************************************************************************
    public List<lookupBean> getLookupRollupList(int rollup) {
        return getLookupRollupList(rollup,jlRoomsFactory);
    }
    public List<lookupBean> getLookupRollupList(int rollup,dbMgrInterface dbMgr) {
        return getLookup(lookupSql.sqlLookupRollup(this.webID), new Object[]{new Integer(rollup)},dbMgr);
    }
    // ******************************************************************************
    public List<lookupBean> getLookupTypeList(int type) {
        return getLookupTypeList(type,jlRoomsFactory);
    }
    public List<lookupBean> getLookupTypeList(int type,dbMgrInterface dbMgr) {
        return getLookup(lookupSql.sqlLookupType(this.webID), new Object[]{new Integer(type)},dbMgr);
    }
    // ******************************************************************************
    
//dbMgrInterface
    //rs = jlRoomsFactory.getCachedRowSet(sql, obj);
    private  List<lookupBean> getLookup(String sql, Object[] obj, dbMgrInterface dbMgr) {
        CachedRowSet rs = null;
         List<lookupBean> ary = new ArrayList<lookupBean>();
        try {
            rs = dbMgr.getCachedRowSet(sql, obj);
            while (rs.next()) {
                ary.add(getLookupBean(rs));
            }
        } catch (Exception e) {
            Logger.getLogger(lookupObj.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            dbMgr.closeCachedRowSet(rs);
        }
        return ary;
    }
    public lookupBean getLookupBean(int id) {
        return getLookupBean(id,jlRoomsFactory);
    }
    public lookupBean getLookupBean(int id,dbMgrInterface dbMgr) {
        CachedRowSet rs = null;
        //Vector ary = new Vector();
        lookupBean lookupBean = null;
        try {
            rs = dbMgr.getCachedRowSet(lookupSql.sqlLookupId(this.webID), new Object[]{new Integer(id)});
            while (rs.next()) {
                lookupBean = getLookupBean(rs);
            }
        } catch (Exception e) {
            Logger.getLogger(lookupObj.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            dbMgr.closeCachedRowSet(rs);
        }
        return lookupBean;
    }
    /*
     *****************************************************
     *****************************************************
     */

    private  List<lookupBean> getLookupBeanVector(CachedRowSet rs) {
        List<lookupBean> v = new ArrayList<lookupBean>();
        try {
            while (rs.next()) {
                v.add(getLookupBean(rs));
            }
        } catch (Exception e) {
            Logger.getLogger(lookupObj.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
            }
            ;
        }
       
        return v;
    }
    /*
     *****************************************************
    select lookup_id,lookup_type,lookup_rollup_id,lookup_Desc,
    flag_ind,credit,sponsor_link_id
    from lookup

     *****************************************************
     */

    private lookupBean getLookupBean(CachedRowSet rs) {
        lookupBean bean = new lookupBean();
        try {
            bean.setLookupId(rs.getInt(1));
            bean.setLookupType(rs.getInt(2));
            bean.setLookupRollupId(rs.getInt(3));
            bean.setDesc(rs.getString(4));
            bean.setFlag(rs.getInt(5));
            bean.setSign(rs.getInt(6));
            bean.setRegion(rs.getInt(7));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }
}
