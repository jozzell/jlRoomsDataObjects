/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsCommon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jlRoomsCommon._beans.jsfRptBean;

/**
 *
 * @author lmeans
 */
public class jsfRptBeanMgr implements Serializable {
    private List<jsfRptBean> list;
    private String header;
    private String footer;

    /**
     * @return the list
     */
    public List<jsfRptBean> getList() {
        if (list == null) list = new ArrayList<jsfRptBean>();
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(List<jsfRptBean> list) {
        if (list == null) list = new ArrayList<jsfRptBean>();
        this.list = list;
    }
    public void setList(jsfRptBean bean) {
       this.getList().add(bean);
    }

    /**
     * @return the header
     */
    public String getHeader() {
        return header;
    }

    /**
     * @param header the header to set
     */
    public void setHeader(String header) {
        this.header = header;
    }

    /**
     * @return the footer
     */
    public String getFooter() {
        return footer;
    }

    /**
     * @param footer the footer to set
     */
    public void setFooter(String footer) {
        this.footer = footer;
    }
}
