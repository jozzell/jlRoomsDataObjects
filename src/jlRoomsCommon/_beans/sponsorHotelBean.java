/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jlRoomsCommon._beans;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
/**
 *
 * @author lmeans
 */
@ManagedBean(name = "sponsorHotelBean")
public class sponsorHotelBean implements Serializable{
private int sponsor_hotel_id, sponsor_id,vendor_id,flag_Ind,walkin,flt_id;
  private String created;
  private java.util.Date eff_date=null,end_date=null,balance_due_by=null,
      cutoff_date=null,cancel_date=null,check_due_by=null;
  private double hotel_fee,service_charge, process_fee;
  private String report_comment=null,location=null, name=null,addr=null,city=null,state=null,zip=null,
      vendorTypeDesc,DSP_STR1,DSP_STR2,DSP_STR3;

  public sponsorHotelBean() {
    
  }
  public java.util.Date getBalance_due_by() {
    return balance_due_by;
  }
  public java.util.Date getCancel_date() {
    return cancel_date;
  }
  public java.util.Date getCheck_due_by() {
    return check_due_by;
  }
  public java.util.Date getCutoff_date() {
    return cutoff_date;
  }
  public java.util.Date getEff_date() {
    return eff_date;
  }
  public java.util.Date getEnd_date() {
    return end_date;
  }
  public int getFlag_Ind() {
    return flag_Ind;
  }
  public double getHotel_fee() {
    return hotel_fee;
  }
  public String getLocation() {
    return location;
  }
  public double getProcess_fee() {
    return process_fee;
  }
  public String getReport_comment() {
    return report_comment;
  }
  public double getService_charge() {
    return service_charge;
  }
  public int getSponsor_hotel_id() {
    return sponsor_hotel_id;
  }
  public int getSponsor_id() {
    return sponsor_id;
  }
  public int getVendor_id() {
    return vendor_id;
  }
  public int getWalkin() {
    return walkin;
  }
  public void setWalkin(int walkin) {
    this.walkin = walkin;
  }
  public void setVendor_id(int vendor_id) {
    this.vendor_id = vendor_id;
  }
  public void setSponsor_id(int sponsor_id) {
    this.sponsor_id = sponsor_id;
  }
  public void setSponsor_hotel_id(int sponsor_hotel_id) {
    this.sponsor_hotel_id = sponsor_hotel_id;
  }
  public void setService_charge(double service_charge) {
    this.service_charge = service_charge;
  }
  public void setReport_comment(String report_comment) {
    this.report_comment = report_comment;
  }
  public void setProcess_fee(double process_fee) {
    this.process_fee = process_fee;
  }
  public void setLocation(String location) {
    this.location = location;
  }
  public void setHotel_fee(double hotel_fee) {
    this.hotel_fee = hotel_fee;
  }
  public void setFlag_Ind(int flag_Ind) {
    this.flag_Ind = flag_Ind;
  }
  public void setEnd_date(java.util.Date end_date) {
    this.end_date = end_date;
  }
  public void setEff_date(java.util.Date eff_date) {
    this.eff_date = eff_date;
  }
  public void setCutoff_date(java.util.Date cutoff_date) {
    this.cutoff_date = cutoff_date;
  }
  public void setCheck_due_by(java.util.Date check_due_by) {
    this.check_due_by = check_due_by;
  }
  public void setCancel_date(java.util.Date cancel_date) {
    this.cancel_date = cancel_date;
  }
  public void setBalance_due_by(java.util.Date balance_due_by) {
    this.balance_due_by = balance_due_by;
  }
  public String getState() {
    return state;
  }
  public String getZip() {
    return zip;
  }
  public String getVendorName() {
    return name;
  }
  public String getCity() {
    return city;
  }
  public String getAddr() {
    return addr;
  }
  public void setAddr(String addr) {
    this.addr = addr;
  }
  public void setCity(String city) {
    this.city = city;
  }
  public void setVendorName(String name) {
    this.name = name;
  }
  public void setState(String state) {
    this.state = state;
  }
  public void setZip(String zip) {
    this.zip = zip;
  }
  public int getVendorType() {
    return vendorType;
  }
  public void setVendorType(int vendorType) {
    this.vendorType = vendorType;
  }
  private int vendorType;
  
  public String getBeanInfo() {
      return getInfo(this);
  }
  public  String getInfo(sponsorHotelBean b) {
      String str = (b.getFlt_id() == 0 ? "" : " "+b.getDSP_STR1());

   boolean
           adr = b.getAddr() == null || b.getAddr().trim().length() == 0 ? false : true,
           cty = b.getCity() == null || b.getCity().trim().length() == 0 ? false : true;
    return
        ((adr || cty ? " @ ":"")+
        (adr ? " "+b.getAddr() : "")+
        (cty ? " "+b.getCity() : "")+
        (b.getFlt_id() == 0 ? "" : " "+b.getDSP_STR1())).trim()
        ;
    // returnt
     //   ((b.getAddr() != null || b.getCity() != null ? " @ ":"")+
     //   (b.getAddr() != null ? " "+b.getAddr() : "")+
     //   (b.getCity() != null ? " "+b.getCity() : "")).trim()
     //   ;
  }
  public String getVendorTypeDesc() {
    return vendorTypeDesc;
  }

  public String getCreated() {
    return created;
  }

  public void setVendorTypeDesc(String vendorTypeDesc) {
    this.vendorTypeDesc = vendorTypeDesc;
  }

  public void setCreated(String created) {
    this.created = created;
  }

 

    /**
     * @return the flt_id
     */
    public int getFlt_id() {
        return flt_id;
    }

    /**
     * @param flt_id the flt_id to set
     */
    public void setFlt_id(int flt_id) {
        this.flt_id = flt_id;
    }

    /**
     * @return the DSP_STR1
     */
    public String getDSP_STR1() {
        return DSP_STR1;
    }

    /**
     * @param DSP_STR1 the DSP_STR1 to set
     */
    public void setDSP_STR1(String DSP_STR1) {
        this.DSP_STR1 = DSP_STR1;
    }

    /**
     * @return the DSP_STR2
     */
    public String getDSP_STR2() {
        return DSP_STR2;
    }

    /**
     * @param DSP_STR2 the DSP_STR2 to set
     */
    public void setDSP_STR2(String DSP_STR2) {
        this.DSP_STR2 = DSP_STR2;
    }

    /**
     * @return the DSP_STR3
     */
    public String getDSP_STR3() {
        return DSP_STR3;
    }

    /**
     * @param DSP_STR3 the DSP_STR3 to set
     */
    public void setDSP_STR3(String DSP_STR3) {
        this.DSP_STR3 = DSP_STR3;
    }

}
