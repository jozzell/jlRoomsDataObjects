/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jlRoomsCommon._beans;
import java.io.Serializable;
import java.util.*;
import java.util.Date;
import javax.faces.bean.ManagedBean;
/**
 *
 * @author lmeans
 */
@ManagedBean(name = "sponsorBean")
public class sponsorBean implements Serializable{
  private String keyStr;
  private Date effDate, endDate,timeStamp;
  private int custId,sponsorId, region, vendorId, flagId, processFeeType,noFeeApplied;
  private String sponsorDesc="New Event", invDesc, vendorName,html=null,comment;
  private double procFee, procFeeHotel, procFeeCar,
      procFeeAir,procBusFee,procBoatFee,
      procFeeTicket, procFeeCruise, procFeeTrain;
  private Collection aryCollection;
    private String effDateStr;
    private String endDateStr;
   public sponsorBean() {
  }
public int getID() {
    return sponsorId;
  }
public Date getEffDate() {
    return effDate;
  }
  public Date getEndDate() {
    return endDate;
  }
  public int getFlagId() {
    return flagId;
  }
  public String getHtml() {
    return html;
  }
  public String getInvDesc() {
    return invDesc;
  }
  public int getProcessFeeType() {
    return processFeeType;
  }
  public double getProcFee() {
    return procFee;
  }
  public double getProcFeeAir() {
    return procFeeAir;
  }
  public double getProcFeeCar() {
    return procFeeCar;
  }
  public double getProcFeeCruise() {
    return procFeeCruise;
  }
  public double getProcFeeHotel() {
    return procFeeHotel;
  }
  public double getProcFeeTicket() {
    return procFeeTicket;
  }
  public double getProcFeeTrain() {
    return procFeeTrain;
  }
  public int getRegion() {
    return region;
  }
  public String getSponsorDesc() {
    if (sponsorDesc != null && sponsorDesc.length() > 60)sponsorDesc = sponsorDesc.substring(1, 58);
    return sponsorDesc;
  }
  public int getSponsorId() {
    return sponsorId;
  }
  public int getVendorId() {
    return vendorId;
  }
  public String getVendorName() {
    return vendorName;
  }
  public void setVendorName(String vendorName) {
    this.vendorName = vendorName;
  }
  public void setVendorId(int vendorId) {
    this.vendorId = vendorId;
  }
  public void setSponsorId(int sponsorId) {
    this.sponsorId = sponsorId;
  }
  public void setSponsorDesc(String sponsorDesc) {
    this.sponsorDesc = sponsorDesc;
  }
  public void setRegion(int region) {
    this.region = region;
  }
  public void setProcFeeTrain(double procFeeTrain) {
    this.procFeeTrain = procFeeTrain;
  }
  public void setProcFeeTicket(double procFeeTicket) {
    this.procFeeTicket = procFeeTicket;
  }
  public void setProcFeeHotel(double procFeeHotel) {
    this.procFeeHotel = procFeeHotel;
  }
  public void setProcFeeCruise(double procFeeCruise) {
    this.procFeeCruise = procFeeCruise;
  }
  public void setProcFeeCar(double procFeeCar) {
    this.procFeeCar = procFeeCar;
  }
  public void setProcFeeAir(double procFeeAir) {
    this.procFeeAir = procFeeAir;
  }
  public void setProcFee(double procFee) {
    this.procFee = procFee;
  }
  public void setProcessFeeType(int processFeeType) {
    this.processFeeType = processFeeType;
  }
  public void setInvDesc(String invDesc) {
    this.invDesc = invDesc;
  }
  public void setHtml(String html) {
    this.html = html;
  }
  public void setFlagId(int flagId) {
    this.flagId = flagId;
  }
  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }
  public void setEffDate(Date effDate) {
    this.effDate = effDate;
  }
  public void setDays(int days) {
    //this.days = days;
  }
  public String getComment() {
      if (comment != null && comment.length() > 250) comment = comment.substring(1, 250);
    return comment;
  }

  public Collection getAryCollection() {
    return aryCollection;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public void setAryCollection(Collection aryCollection) {
    this.aryCollection = aryCollection;
  }


  public int getNoFeeApplied() {
    return noFeeApplied;
  }
 public Date getTimeStamp() {
     
    return timeStamp;
  }

  public int getCustId() {
    return custId;
  }

  public void setNoFeeApplied(int noFeeApplied) {
    this.noFeeApplied = noFeeApplied;
  }

  public void setTimeStamp(Date timeStamp) {
    this.timeStamp = timeStamp;
  }

  public void setCustId(int custId) {
    this.custId = custId;
  }
    public double getProcBusFee() {
        return procBusFee;
    }

    /**
     * @param procBusFee the procBusFee to set
     */
    public void setProcBusFee(double procBusFee) {
        this.procBusFee = procBusFee;
    }

    /**
     * @return the procBoatFee
     */
    public double getProcBoatFee() {
        return procBoatFee;
    }
    public void setProcBoatFee(double procBoatFee) {
        this.procBoatFee = procBoatFee;
    }
     public void setSponsorBean(sponsorBean bean) {
    this.setSponsorId(bean.getSponsorId());
    this.setEffDate(bean.getEffDate());
    this.setSponsorDesc(bean.getSponsorDesc());
    this.setEndDate(bean.getEndDate());
    this.setFlagId(bean.getFlagId());
    this.setProcFee(bean.getProcFee());
    this.setProcFeeHotel(bean.getProcFeeHotel());
    this.setProcFeeCar(bean.getProcFeeCar());
    this.setProcFeeAir(bean.getProcFeeAir());
    this.setProcFeeTicket(bean.getProcFeeTicket());
    this.setProcessFeeType(bean.getProcessFeeType());
    this.setProcFeeTrain(bean.getProcFeeTrain());
    this.setComment(bean.getComment());
    this.setVendorId(bean.getVendorId());
    this.setVendorName(bean.getVendorName());
    this.setProcBusFee(bean.getProcBusFee());

  }
  public int getDays() {
    return 0;
  }
   public String getRptDisplay(){
    return getSponsorDesc()+(getSponsorId() <= 0 ? "" :  " @ "+getVendorName());
  }
  public String getHeaderDisplay() {
      return getSponsorDesc();
  }

    /**
     * @return the effDateStr
     */
    public String getEffDateStr() {
        return effDateStr;
    }

    /**
     * @param effDateStr the effDateStr to set
     */
    public void setEffDateStr(String effDateStr) {
        this.effDateStr = effDateStr;
    }

    /**
     * @return the endDateSt
     */
    public String getEndDateStr() {
        return endDateStr;
    }

    /**
     * @param endDateSt the endDateSt to set
     */
    public void setEndDateStr(String endDateSt) {
        this.endDateStr = endDateSt;
    }
    public String getFlagIdString(){
        switch(flagId){
            case 1:
                return "Non-Active";
            default:
                return "Active";
        }
    }

    /**
     * @return the keyStr
     */
    public String getKeyStr() {
        return keyStr;
    }

    /**
     * @param keyStr the keyStr to set
     */
    public void setKeyStr(String keyStr) {
        this.keyStr = keyStr;
    }

}
