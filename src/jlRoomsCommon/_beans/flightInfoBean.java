/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jlRoomsCommon._beans;
import java.io.Serializable;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.faces.bean.ManagedBean;
/**
 *
 * @author lmeans
 */
@ManagedBean(name = "flightInfoBean")
public class flightInfoBean implements Serializable{
 private Time fltDepartureTime,fltArrivalTime;
  private Date fltDepartureDate,fltArrivalDate;
  
  private int fltId,region,sponsorHotelId,hotelId; //,flgIdRollup;
  private double fltType;
  private String fltNumber,fltDeparturePlace,fltArrivalPlace,fltComment,vendor;
  private int rollupId;
  private int sponsorId;
  private int connFlt;
  private int vendor_id;
  private String vendor_name;
  private int flagInd;
  private int vendorType;

  private String created;
  private SimpleDateFormat sdf;
  // public int getFlgIdRollup() {
    //Calendar cal = Calendar.getInstance();
   // return flgIdRollup;
  //}
   public flightInfoBean() {
   
  }
   private String getSdf(Date date){
       if (sdf == null){
           sdf = new SimpleDateFormat("EEE MMM dd yyyy hh:mm a");
       }
       
       return sdf.format(date);
   }
   public String getFltArrivalDateString() {
       return getSdf(getFltArrivalDate() );
   }
  public Date getFltArrivalDate() {
      if (this.fltArrivalDate == null) return Calendar.getInstance().getTime();
    return fltArrivalDate;
  }
  public String getFltArrivalPlace() {
    return fltArrivalPlace;
  }
  public String getFltComment() {
    return fltComment;
  }
  public String getFltDepartureDateString() {
       return getSdf(getFltDepartureDate() );
  }
  public Date getFltDepartureDate() {
      if (this.fltDepartureDate == null) return Calendar.getInstance().getTime();
    return fltDepartureDate;
  }
  public String getFltDeparturePlace() {
    return fltDeparturePlace;
  }
  public int getFltId() {
    return fltId;
  }
  public String getFltNumber() {
    return fltNumber;
  }
  public double getFltType() {
    return fltType;
  }
  public int getHotelId() {
    return hotelId;
  }
  public int getRegion() {
    return region;
  }

  public int getSponsorHotelId() {
    return sponsorHotelId;
  }
  public String getVendor() {
    return vendor;
  }

  public Time getFltArrivalTime() {
     
    return fltArrivalTime;
  }

  public Time getFltDepartureTime() {
      
    return fltDepartureTime;
  }

  public void setVendor(String vendor) {
    this.vendor = vendor;
  }
  public void setSponsorHotelId(int sponsorHotelId) {
    this.sponsorHotelId = sponsorHotelId;
  }

  public void setRegion(int region) {
    this.region = region;
  }
  public void setHotelId(int hotelId) {
    this.hotelId = hotelId;
  }
  public void setFltType(double fltType) {
    this.fltType = fltType;
  }
  public void setFltNumber(String fltNumber) {
    this.fltNumber = fltNumber;
  }
  public void setFltId(int fltId) {
    this.fltId = fltId;
  }
  public void setFltDeparturePlace(String fltDeparturePlace) {
    this.fltDeparturePlace = fltDeparturePlace;
  }
  public void setFltDepartureDate(Date fltDepartureDate) {
    this.fltDepartureDate = fltDepartureDate;
  }
  public void setFltComment(String fltComment) {
    this.fltComment = fltComment;
  }
  public void setFltArrivalPlace(String fltArrivalPlace) {
    this.fltArrivalPlace = fltArrivalPlace;
  }
  public void setFltArrivalDate(Date fltArrivalDate) {
    this.fltArrivalDate = fltArrivalDate;
  }
  //public void setFlgIdRollup(int flgIdRollup) {
    //this.flgIdRollup = flgIdRollup;
  //}

  public void setFltArrivalTime(Time fltArrivalTime) {
    this.fltArrivalTime = fltArrivalTime;
  }

  public void setFltDepartureTime(Time fltDepartureTime) {
    this.fltDepartureTime = fltDepartureTime;
  }

  private void jbInit() throws Exception {
  }
  public int getRollupId() {
    return rollupId;
  }
  public void setRollupId(int rollupId) {
    this.rollupId = rollupId;
  }
  public int getSponsorId() {
    return sponsorId;
  }
  public void setSponsorId(int sponsorId) {
    this.sponsorId = sponsorId;
  }
  public int getConnFlt() {
    return connFlt;
  }
  public void setConnFlt(int connFlt) {
    this.connFlt = connFlt;
  }
  public int getVendorId() {
    return vendor_id;
  }
  public void setVendorId(int vendor_id) {
    this.vendor_id = vendor_id;
  }
  public String getVendorName() {
    return vendor_name;
  }
  public void setVendorName(String vendor_name) {
    this.vendor_name = vendor_name;
  }
  public int getFlagInd() {
    return flagInd;
  }
  public void setFlagInd(int flagInd) {
    this.flagInd = flagInd;
  }
  public int getVendorType() {
    return vendorType;
  }



  public String getCreated() {
    return created;
  }

  public void setVendorType(int vendorType) {
    this.vendorType = vendorType;
  }



  public void setCreated(String created) {
    this.created = created;
  }
  public String DST(){
      return 
              (this.getFltDeparturePlace() == null ? "":this.getFltDeparturePlace() )+
              " - "+
              (this.getFltArrivalPlace() == null ? "":this.getFltArrivalPlace())
              ;
  }
   

}
