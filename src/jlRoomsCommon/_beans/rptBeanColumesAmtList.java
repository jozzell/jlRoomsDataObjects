/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsCommon._beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import jlRoomsCommon.objMgr;

/**
 *
 * @author lmeans
 */
@ManagedBean(name = "rptBeanColumesAmtList")
public class rptBeanColumesAmtList implements Serializable{
    private List<rptBeanColumes> rptList;
    private List<rptBeanColumesAmtList> rpt;
     private double[] total;
    private double amt;
    private String header;
    private int id;
    objMgr objMgr;
    /**
     * @return the list
     */
    public rptBeanColumesAmtList(){
       objMgr = new objMgr();
    }
    public rptBeanColumesAmtList(double amt,List<rptBeanColumesAmtList> rpt){
        this();
       this.amt = amt;
       this.rpt = rpt;
    }
    public rptBeanColumesAmtList(String header,double amt,List<rptBeanColumes> rptList){
        this();
       this.header = header;
       this.amt = amt;
       this.rptList = rptList;
    }
    public String getAmtStr() {
        return objMgr.getDollarFormat(amt);
    }
    public double getAmt() {
       
        return amt;
    }
    public void setAmt(double amt) {
        this.amt = amt;
    }

    /**
     * @return the rptList
     */
    public List<rptBeanColumes> getRptList() {
        if (rptList == null) rptList = new ArrayList<rptBeanColumes>();
        return rptList;
    }

    /**
     * @param rptList the rptList to set
     */
    public void setRptList(List<rptBeanColumes> rptList) {
        this.rptList = rptList;
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
     * @return the rpt
     */
    public List<rptBeanColumesAmtList> getRpt() {
        if (rpt == null) rpt= new ArrayList<rptBeanColumesAmtList>();
        return rpt;
    }

   
    public void setRpt(List<rptBeanColumesAmtList> rpt) {
        this.rpt = rpt;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the total
     */
    public double[] getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(double[] total) {
        this.total = total;
    }

    /**
     * @return the list
     */
    
}
