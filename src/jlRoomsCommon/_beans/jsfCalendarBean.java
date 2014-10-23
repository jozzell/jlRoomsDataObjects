/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsCommon._beans;

import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author lmeans
 */
@ManagedBean(name = "jsfCalendarBean")
public class jsfCalendarBean implements Serializable{
    private String Desc;
    private Date dateStart;
    private Date dateEnd;
    public jsfCalendarBean(String desc,Date date){
        this.Desc = desc;
        this.dateStart = date;
    }
    public jsfCalendarBean(String desc,Date date,Date end){
        this.Desc = desc;
        this.dateStart = date;
        this.dateEnd = end;
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
     * @return the dateStart
     */
    public Date getDateStart() {
        return dateStart;
    }

    /**
     * @param dateStart the dateStart to set
     */
    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    /**
     * @return the dateEnd
     */
    public Date getDateEnd() {
        if (dateEnd == null) return dateStart;
        return dateEnd;
    }

    /**
     * @param dateEnd the dateEnd to set
     */
    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }
    public boolean isvalidDates(){
        if (this.getDateStart() != null){
            return true;
        } else if (this.getDateEnd() != null){
            this.setDateStart(this.getDateEnd());
            return true;
        }
        return false;
    }
}
