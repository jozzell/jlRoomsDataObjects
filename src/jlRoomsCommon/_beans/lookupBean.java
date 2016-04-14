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
private int region,LOOKUP_ID,LOOKUP_TYPE,LOOKUP_ROLLUP_ID,FLAG_IND,CREDIT,vendor_id;
  private String LOOKUP_DESC,miscDesc,rptDesc;
  private String maintScreenDisplay=null;
  private Date date,endDate;
  private int block_id;
  double bookedCnt,rmCnt;
    private double rmCost;
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
  public String getNewBlockDesc(){
      if (this.block_id == 0) return " [New Reserve Block]";
      return "";
  }
  public double getCntZero(){
      if (getCnt() <0) return 0;
      return getCnt();
  }
  public double getCnt(){
      return this.rmCnt - this.bookedCnt;
  }
  public String renderBlock(){
      if (block_id > 0) return "true";
      return "false";
              
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
  public int getLookupId(boolean ok){
     if (LOOKUP_ID < 0) LOOKUP_ID = LOOKUP_ID * -1;
     return LOOKUP_ID;
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

    /**
     * @return the vendor_id
     */
    public int getVendor_id() {
        return vendor_id;
    }

    /**
     * @param vendor_id the vendor_id to set
     */
    public void setVendor_id(int vendor_id) {
        this.vendor_id = vendor_id;
    }

    /**
     * @return the block_id
     */
    public int getBlock_id() {
        return block_id;
    }

    /**
     * @param block_id the block_id to set
     */
    public void setBlock_id(int block_id) {
        this.block_id = block_id;
    }

    /**
     * @return the rmCnt
     */
    public double getRmCnt() {
        return rmCnt;
    }

    /**
     * @param rmCnt the rmCnt to set
     */
    public void setRmCnt(double rmCnt) {
        this.rmCnt = rmCnt;
    }

    /**
     * @return the bookedCnt
     */
    public double getBookedCnt() {
        return bookedCnt;
    }

    /**
     * @param bookedCnt the bookedCnt to set
     */
    public void setBookedCnt(double bookedCnt) {
        this.bookedCnt = bookedCnt;
    }

    /**
     * @return the rmCost
     */
    public double getRmCost() {
        return rmCost;
    }

    /**
     * @param rmCost the rmCost to set
     */
    public void setRmCost(double rmCost) {
        this.rmCost = rmCost;
    }
}