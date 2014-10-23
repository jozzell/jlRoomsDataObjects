/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsCommon.pkg;

import java.io.Serializable;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import jlRoomsCommon._beans.pkgBean;
import jlRoomsCommon._beans.pkgBeanDetailBean;
import jlRoomsCommon._objects.jlRoomsDbConnIinterface;

import sun.jdbc.rowset.CachedRowSet;
/**
 *
 * @author lmeans
 */
public class pkgDbObj  implements Serializable {
    private jlRoomsDbConnIinterface jlRoomsFactory;
    public String webID;
    pkgDbSQL pkgDbSQL;
    public pkgDbObj(){
        pkgDbSQL = new pkgDbSQL();
    }
    public pkgDbObj(String web){
        this();
        this.webID = web;
    }
    public pkgDbObj(jlRoomsDbConnIinterface x) {
        this();
      this.jlRoomsFactory = x;
        
    }
    public  List<pkgBeanDetailBean> getDetail(int i) {
        CachedRowSet cr;
        List<pkgBeanDetailBean> v = new ArrayList<pkgBeanDetailBean>();
        try {
            cr = jlRoomsFactory.getCachedRowSet(pkgDbSQL.sql_select_detail(this.webID), new Object[]{new Integer(i)});
            while (cr.next()) {
                v.add(getDetail(cr));
            }
        } catch (Exception e) {
            Logger.getLogger(pkgDbObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return v;
    }
    /*
     * select pkg_detail_id,pkg_id,	pkg_type,pkg_cat,pkg_night,pkg_comment , "+
    " pkg_parm1 ,pkg_parm2 ,pkg_parm3 ,SPONSOR_ID from pkg_detail ",
    sql =  " select pkg_id,pkg_desc,pkg_amt,pkg_fee,sponsor_id from pkg ";
     */

    private  pkgBeanDetailBean getDetail(CachedRowSet cr) {
        pkgBeanDetailBean b = new pkgBeanDetailBean();
        try {
            b.setPkgDetailId(cr.getInt(1));
            b.setPkgId(cr.getInt(2));
            b.setPkgType(cr.getInt(3));
            b.setPkgCat(cr.getInt(4));
            b.setPkgNight(cr.getInt(5));
            b.setPkgComment(cr.getString(6));
            b.setPkgParm1(cr.getString(7));
            b.setPkgParm2(cr.getString(8));
            b.setPkgParm3(cr.getString(9));
            b.setSponsorId(cr.getInt(10));
            b.setPkgTypeDesc(cr.getString(11));
            b.setPkgCatDesc(cr.getString(12));
            b.setSplit(cr.getInt(13));
        } catch (Exception e) {
            Logger.getLogger(pkgDbObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return b;
    }
    public  void updatePkgBeanDetail(pkgBeanDetailBean b) {
        if (b.getPkgParm1() == null) {
            b.setPkgParm1("");
        }
        if (b.getPkgParm2() == null) {
            b.setPkgParm2("");
        }
        if (b.getPkgParm3() == null) {
            b.setPkgParm3("");
        }
        if (b.getPkgComment() == null) {
            b.setPkgComment("");
        }


        Object[] obj;
        if (b.getPkgDetailId() == 0) {
            obj = new Object[]{
                        new Integer(b.getPkgId()),
                        new Integer(b.getPkgType()),
                        new Integer(b.getPkgCat()),
                        new Integer(b.getPkgNight()),
                        b.getPkgComment(),
                        b.getPkgParm1(),
                        b.getPkgParm2(),
                        b.getPkgParm3(),
                        new Integer(b.getSponsorId()),
                        new Integer(b.getSplit())
                    };
            try {
                jlRoomsFactory.updateDatabase(pkgDbSQL.sql_insert_pkg_detail(this.webID), obj);
            } catch (Exception ex) {
                Logger.getLogger(pkgDbObj.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            obj = new Object[]{

                        new Integer(b.getPkgCat()),
                        new Integer(b.getPkgNight()),
                        b.getPkgComment(),
                        b.getPkgParm1(),
                        b.getPkgParm2(),
                        b.getPkgParm3(),
                        new Integer(b.getSponsorId()),
                        new Integer(b.getSplit()),
                        new Integer(b.getPkgDetailId())
        };
            try {
                jlRoomsFactory.updateDatabase(pkgDbSQL.sql_update_pkg_detail(this.webID), obj);
            } catch (Exception ex) {
                Logger.getLogger(pkgDbObj.class.getName()).log(Level.SEVERE, null, ex);
            }

    }
    }
    // ***********************************************************************
    public  List<pkgBean> getPkg(int sponsor) {
        List<pkgBean> v = new ArrayList<pkgBean>();
        CachedRowSet cr;
        try {

            cr = jlRoomsFactory.getCachedRowSet(pkgDbSQL.sql_select_default(this.webID), new Object[]{new Integer(sponsor)});
            while (cr.next()) {
                v.add(getPkg(cr));
            }
        } catch (Exception e) {
            Logger.getLogger(pkgDbObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return v;
    }

    public  List<pkgBean> getPkg(String seek) {

        List<pkgBean> v = new ArrayList<pkgBean>();
        if (seek == null || seek.length() <= 2) return v;
        seek = seek.toLowerCase().trim()+"%";
        CachedRowSet cr;
        try {

            cr = jlRoomsFactory.getCachedRowSet(pkgDbSQL.sql_select_search(this.webID), new Object[]{seek});
            while (cr.next()) {
                v.add(getPkg(cr));
            }
        } catch (Exception e) {
            Logger.getLogger(pkgDbObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return v;
    }


    private  pkgBean getPkg(CachedRowSet cr) {
        pkgBean bean = new pkgBean();
        //select pkg_id,pkg_desc,pkg_amt,pkg_fee,sponsor_id
        try {
            bean.setPkgId(cr.getInt(1));
            bean.setPkgDesc(cr.getString(2));
            bean.setPkgAmt(cr.getDouble(3));
            bean.setPkgFee(cr.getDouble(4));
            bean.setSponsorId(cr.getInt(5));
            bean.setCnt(cr.getInt(6));
            bean.setCntKids(cr.getInt(7));
            bean.setCntSr(cr.getInt(8));
        } catch (Exception e) {
            Logger.getLogger(pkgDbObj.class.getName()).log(Level.SEVERE, null, e);
        }

        return bean;
    }
    
    public  int savePkgBean(pkgBean b) {
        int i = b.getPkgId();
        Calendar cal = Calendar.getInstance();

        java.sql.Date d = new java.sql.Date(cal.getTime().getTime());
        java.sql.Time t = new java.sql.Time(cal.getTime().getTime());
        if (b.getPkgId() == 0) {
            Object obj[] = new Object[]{
                b.getPkgDesc(),
                new Double(b.getPkgAmt()),
                new Double(b.getPkgFee()),
                d,
                t,
                new Integer(b.getFlagInd()),
                new Integer(b.getSponsorId()),
                new Integer(b.getCnt()),
                new Integer(b.getCntKids()),
                new Integer(b.getCntSr())
            };
            try {
                jlRoomsFactory.updateDatabase(pkgDbSQL.sql_insert_pkg(this.webID), obj);
            } catch (Exception ex) {
                Logger.getLogger(pkgDbObj.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                obj = new Object[]{b.getPkgDesc(), d, t};
                CachedRowSet cr = jlRoomsFactory.getCachedRowSet(pkgDbSQL.sql_insert_select(this.webID), obj);
                while (cr.next()) {
                    i = cr.getInt(1);
                }
            } catch (Exception e) {
                Logger.getLogger(pkgDbObj.class.getName()).log(Level.SEVERE, null, e);
            }
        } else {
            Object obj[] = new Object[]{
                b.getPkgDesc(),
                new Double(b.getPkgAmt()),
                new Double(b.getPkgFee()),
                new Integer(b.getFlagInd()),
                new Integer(b.getSponsorId()),
                new Integer(b.getCnt()),
                new Integer(b.getCntKids()),
                new Integer(b.getCntSr()),
                new Integer(b.getPkgId())
            };
            try {
                jlRoomsFactory.updateDatabase(pkgDbSQL.sql_update_pkg(this.webID), obj);
            } catch (Exception ex) {
                Logger.getLogger(pkgDbObj.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //insert into pkg (pkg_desc,pkg_amt,pkg_fee,pkg_date,flag_ind,sponsor_id) values (?,?,?,?,?,?)
        return i;
    }
}
