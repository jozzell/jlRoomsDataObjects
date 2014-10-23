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
@ManagedBean(name = "blockBean")
public class blockBean implements Serializable{
  private boolean vesion2=false;
  private Date effDate,endDate;
  private String blockComment,lookupDesc,vendor,city,effDateStr,endDateStr,fltDispStr,fltNumber;
  private double rmCost,rmCnt,serviceCharge,processFee,hotelFee,bookCnt;
  private int region,sponsor,blockId,sponsorHotelId,lookupId,flagInd;
  private int vendorId,noFeeAppyied;
  private int fltId;
  private int vendorType;
  private String DB_TIMESTAMP,dspStr1;
  private boolean applyPrcFee=true;

  public blockBean(){
      
  }
  public blockBean(boolean ok){
      this.vesion2 = ok;
  }
  public String lookupDescPlus(){
      return lookupDesc+(vesion2 && dspStr1 ==  null? "":" "+dspStr1);
     
  }
  public boolean isFlt(){
      return !(this.fltId == 0);
  }
  public String getBlockComment() {
    return blockComment;
  }
  public int getBlockId() {
    return blockId;
  }
  public String getCity() {
    return city;
  }
  public Date getEffDate() {
    return effDate;
  }
  public String getEffDateStr() {
    return effDateStr;
  }
  public Date getEndDate() {
    return endDate;
  }
  public String getEndDateStr() {
    return endDateStr;
  }
  public int getFlagInd() {
    return flagInd;
  }
  public String getLookupDesc() {
    return lookupDesc;
  }
  public int getLookupId() {
    return lookupId;
  }
  public int getRegion() {
    return region;
  }
  public double getRmCnt() {
    return rmCnt;
  }
  public double getRmCost() {
    return rmCost;
  }
  public int getSponsor() {
    return sponsor;
  }
  public int getSponsorHotelId() {
    return sponsorHotelId;
  }
  public String getVendor() {
    return vendor;
  }



  public void setVendor(String vendor) {
    this.vendor = vendor;
  }
  public void setSponsorHotelId(int sponsorHotelId) {
    this.sponsorHotelId = sponsorHotelId;
  }
  public void setSponsor(int sponsor) {
    this.sponsor = sponsor;
  }
  public void setRmCost(double rmCost) {
    this.rmCost = rmCost;
  }
  public void setRmCnt(double rmCnt) {
    this.rmCnt = rmCnt;
  }
  public void setRegion(int region) {
    this.region = region;
  }
  public void setLookupId(int lookupId) {
    this.lookupId = lookupId;
  }
  public void setLookupDesc(String lookupDesc) {
    this.lookupDesc = lookupDesc;
  }
  public void setFlagInd(int flagInd) {
    this.flagInd = flagInd;
  }
  public void setEndDateStr(String endDateStr) {
    this.endDateStr = endDateStr;
  }
  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }
  public void setEffDateStr(String effDateStr) {
    this.effDateStr = effDateStr;
  }
  public void setEffDate(Date effDate) {
    this.effDate = effDate;
  }
  public void setCity(String city) {
    this.city = city;
  }
  public void setBlockId(int blockId) {
    this.blockId = blockId;
  }
  public void setBlockComment(String blockComment) {
    this.blockComment = blockComment;
  }
  public int getVendorId() {
    return vendorId;
  }
  public void setVendorId(int vendorId) {
    this.vendorId = vendorId;
  }
  public int getFltId() {
    return fltId;
  }
  public void setFltId(int fltId) {
    this.fltId = fltId;
  }
  public int getVendorType() {
    return vendorType;
  }
  public void setVendorType(int vendorType) {
    this.vendorType = vendorType;
  }
  public double getHotelFee() {
    return hotelFee;
  }
  public void setHotelFee(double hotelFee) {
    this.hotelFee = hotelFee;
  }
  public double getProcessFee() {
    return processFee;
  }
  public void setProcessFee(double processFee) {
    this.processFee = processFee;
  }
  public int getNoFeeAppyied() {
    return noFeeAppyied;
  }
  public void setNoFeeAppyied(int noFeeAppyied) {
    this.noFeeAppyied = noFeeAppyied;
  }
  public double getServiceCharge() {
    return serviceCharge;
  }

  public String getDB_TIMESTAMP() {
    return DB_TIMESTAMP;
  }

  public void setServiceCharge(double serviceCharge) {
    this.serviceCharge = serviceCharge;
  }

  public void setDB_TIMESTAMP(String DB_TIMESTAMP) {
    this.DB_TIMESTAMP = DB_TIMESTAMP;
  }

    /**
     * @return the dspStr1
     */
    public String getDspStr1() {
        return dspStr1;
    }

    /**
     * @param dspStr1 the dspStr1 to set
     */
    public void setDspStr1(String dspStr1) {
        this.dspStr1 = dspStr1;
    }

    /**
     * @return the fltDispStr
     */
    public String getFltDispStr() {
        if (fltDispStr == null) return "";
        return fltDispStr;
    }

    /**
     * @param fltDispStr the fltDispStr to set
     */
    public void setFltDispStr(String fltDispStr) {
        this.fltDispStr = fltDispStr;
    }

    /**
     * @return the fltNumber
     */
    public String getFltNumber() {
        return fltNumber;
    }

    /**
     * @param fltNumber the fltNumber to set
     */
    public void setFltNumber(String fltNumber) {
        this.fltNumber = fltNumber;
    }

    /**
     * @return the bookCnt
     */
    public double getBookCnt() {
        return bookCnt;
    }

    /**
     * @param bookCnt the bookCnt to set
     */
    public void setBookCnt(double bookCnt) {
        this.bookCnt = bookCnt;
    }

    /**
     * @return the applyPrcFee
     */
    public boolean isApplyPrcFee() {
        return applyPrcFee;
    }

    /**
     * @param applyPrcFee the applyPrcFee to set
     */
    public void setApplyPrcFee(boolean applyPrcFee) {
        this.applyPrcFee = applyPrcFee;
    }

   
}
