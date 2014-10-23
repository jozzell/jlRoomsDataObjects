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
@ManagedBean(name = "lookupBean")
public class lookupBean implements Serializable {
private int region,LOOKUP_ID,LOOKUP_TYPE,LOOKUP_ROLLUP_ID,FLAG_IND,CREDIT;
  private String LOOKUP_DESC,miscDesc,rptDesc;
  private String maintScreenDisplay=null;
  private Date date,endDate;
  public lookupBean() {
  }
  public lookupBean(String desc,int value) {
      this.LOOKUP_DESC = desc;
      this.LOOKUP_ID = value;
  }
  public lookupBean(String desc,int value,String misc) {
      this.LOOKUP_DESC = desc;
      this.LOOKUP_ID = value;
      this.miscDesc = misc;
  }
  public String getEnabledFlag(){
      if (this.FLAG_IND >= 1){
          return "Not Usable";
      } else {
          return "Usable";
      }
  }
  /*
    *******************************************
    *******************************************
  */
  public void setRegion(int i) {
    region = i;
  }
  public int getRegion() {
    return region;
  }
  /*
    *******************************************
    *******************************************
  */
  public void setLookupId(int i) {
    LOOKUP_ID = i;
  }
  public int getLookupId() {
    return LOOKUP_ID;
  }
  /*
    *******************************************
    *******************************************
  */
  public void setLookupType(int i) {
    LOOKUP_TYPE = i;
  }
  public int getLookupType() {
    return LOOKUP_TYPE;
  }
  /*
    *******************************************
    *******************************************
  */
  public void setLookupRollupId(int i) {
    LOOKUP_ROLLUP_ID = i;
  }
  public int getLookupRollupId() {
    return LOOKUP_ROLLUP_ID;
  }
  /*
    *******************************************
    *******************************************
  */
  public void setFlag(int i) {
    FLAG_IND = i;
  }
  public int getFlag() {
    return FLAG_IND;
  }
  /*
    *******************************************
    *******************************************
  */
  public void setSign(int i) {
    CREDIT = i;
  }
  public int getSign() {
    return CREDIT;
  }
  /*
    *******************************************
    *******************************************
  */
  public void setDesc(String s) {
    LOOKUP_DESC = s;
  }
  public String getDesc() {
    return LOOKUP_DESC;
  }
  /*
    *******************************************
    *******************************************
  */

    /**
     * @return the miscDesc
     */
    public String getMiscDesc() {
        return miscDesc;
    }

    /**
     * @param miscDesc the miscDesc to set
     */
    public void setMiscDesc(String miscDesc) {
        this.miscDesc = miscDesc;
    }

    /**
     * @return the rptDesc
     */
    public String getRptDesc() {
        return rptDesc;
    }

    /**
     * @param rptDesc the rptDesc to set
     */
    public void setRptDesc(String rptDesc) {
        this.rptDesc = rptDesc;
    }

    /**
     * @return the maintScreenDisplay
     */
    public String getMaintScreenDisplay() {
        if (maintScreenDisplay == null) return "Add/Edit Type Maintenance";
        return maintScreenDisplay;
    }

    /**
     * @param maintScreenDisplay the maintScreenDisplay to set
     */
    public void setMaintScreenDisplay(String maintScreenDisplay) {
        this.maintScreenDisplay = maintScreenDisplay;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}