/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsCommon.login;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jlRoomsCommon._beans.loginBean;
import jlRoomsCommon._objects.jlRoomsDbConnIinterface;


import sun.jdbc.rowset.*;

/**
 *
 * @author lmeans
 */
public class loginObj  implements Serializable{
    loginSql loginSql;
    private jlRoomsDbConnIinterface jlRoomsFactory;
    private String webID;
    public loginObj(){
        
    }
    public loginObj(String web) {
        this();
        this.webID = web;
    }

    public loginObj(jlRoomsDbConnIinterface x) {
        this();
        this.jlRoomsFactory = x;

    }

    public void updateUserPassword(int id, String pw) {
        try {
            jlRoomsFactory.updateDatabase(loginSql.sql_update_pw(this.webID), new Object[]{pw, new Integer(id)});
        } catch (Exception ex) {
            Logger.getLogger(loginObj.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updateUser(loginBean b) {
        if (b.getUserId() <= 0) {
            try {
                jlRoomsFactory.updateDatabase(loginSql.sql_insert(this.webID), new Object[]{b.getLastName(), b.getFirstName(), b.getEmail(), b.getAccessLevel(), "welcome"});
            } catch (Exception ex) {
                Logger.getLogger(loginObj.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            try {
                jlRoomsFactory.updateDatabase(loginSql.sql_update(this.webID), new Object[]{b.getLastName(), b.getFirstName(), b.getEmail(), b.getAccessLevel(), new Integer(b.getUserId())});
            } catch (Exception ex) {
                Logger.getLogger(loginObj.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }

    public List<loginBean> getUserBean() {
        List<loginBean> v = new ArrayList<loginBean>();
        try {
            CachedRowSet rs;
            rs = jlRoomsFactory.getCachedRowSet(loginSql.sql_all_user(this.webID), new Object[]{});
            while (rs.next()) {
                v.add(getUserBean(rs));
            }
        } catch (Exception e) {
            Logger.getLogger(loginObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return v;
    }

    public loginBean getUserBean(int i) {
        loginBean loginBean = null;
        try {
            CachedRowSet rs = null;
            rs = jlRoomsFactory.getCachedRowSet(loginSql.sql_get_user(this.webID), new Object[]{new Integer(i)});
            while (rs.next()) {
                loginBean = getUserBean(rs);
            }
        } catch (Exception e) {
            Logger.getLogger(loginObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return loginBean;
    }

    public void updateLoginInfo() {
        Calendar cal = Calendar.getInstance();
        try {
            java.net.InetAddress i = java.net.InetAddress.getLocalHost();
            String host = i.getHostName() == null ? "127.1.1.0" : i.getHostName(),
                    ip = i.getHostAddress() == null ? "127.1.1.0" : i.getHostAddress();

            Object obj[] = new Object[]{
                host,
                ip,
                new java.sql.Date(cal.getTime().getTime()),
                new java.sql.Time(cal.getTime().getTime()),
                new Integer(jlRoomsFactory.getLoginId())
            };
            jlRoomsFactory.updateDatabase(loginSql.sql_login_user(this.webID), obj);
        } catch (Exception e) {
            Logger.getLogger(loginObj.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private  loginBean getUserBean(CachedRowSet rs) {
        loginBean bean = new loginBean();
        try {
            bean.setUserId(rs.getInt(1));
            bean.setLastName(rs.getString(2));
            bean.setFirstName(rs.getString(3));
            bean.setPassWord(rs.getString(4));
            bean.setEmail(rs.getString(5));

            bean.setAccessLevel(rs.getInt(6));
            bean.setLoginDate(rs.getDate(8));

        } catch (Exception e) {
            Logger.getLogger(loginObj.class.getName()).log(Level.SEVERE, null, e);
        }
        return bean;
    }
}
