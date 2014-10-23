/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsCommon.rpt;

import java.io.Serializable;

public class rpt_EventPaymentDetailBookedBean implements Serializable{
    private String Status;
private String Desc;
private double Cost;
private double cnt;
private int Days;
private double Total;

    /**
     * @return the Status
     */
    public String getStatus() {
        return Status;
    }

    /**
     * @param Status the Status to set
     */
    public void setStatus(String Status) {
        this.Status = Status;
    }

    /**
     * @return the Desc
     */
    public String getDesc() {
        return Desc;
    }

    /**
     * @param Desc the Desc to set
     */
    public void setDesc(String Desc) {
        this.Desc = Desc;
    }

    /**
     * @return the Cost
     */
    public double getCost() {
        return Cost;
    }

    /**
     * @param Cost the Cost to set
     */
    public void setCost(double Cost) {
        this.Cost = Cost;
    }

    /**
     * @return the cnt
     */
    public double getCnt() {
        return cnt;
    }

    /**
     * @param cnt the cnt to set
     */
    public void setCnt(double cnt) {
        this.cnt = cnt;
    }

    /**
     * @return the Days
     */
    public int getDays() {
        return Days;
    }

    /**
     * @param Days the Days to set
     */
    public void setDays(int Days) {
        this.Days = Days;
    }

    /**
     * @return the Total
     */
    public double getTotal() {
        return Total;
    }

    /**
     * @param Total the Total to set
     */
    public void setTotal(double Total) {
        this.Total = Total;
    }
}
