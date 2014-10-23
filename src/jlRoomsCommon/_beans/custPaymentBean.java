/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jlRoomsCommon._beans;
import java.io.Serializable;
import java.util.*;
import javax.faces.bean.ManagedBean;
/**
 *
 * @author lmeans
 */
@ManagedBean(name = "custPaymentBean")
public class custPaymentBean implements Serializable{
public custPaymentBean() {
  }

  /*
    *******************************************
      p.roommate_id,
 p.sponsor_id, pt.lookup_desc pay, pt.lookup_desc chk
    *******************************************
  */

  private String note, comment;
  private double  amtRec;
  private int region,sponsor,custPayment,custRoomId,roommateId,blockId,custId,vendorType;
  private int paymentType,chkType,userId,vendorId,lookupId;
  private Date dbTimestamp;



  public double getAmtRec() {
    return amtRec;
  }

  public int getChkType() {
    return chkType;
  }
  public int getCustPayment() {
    return custPayment;
  }
  public int getCustRoomId() {
    return custRoomId;
  }
  public Date getDbTimestamp() {
    return dbTimestamp;
  }

  public int getPaymentType() {
    return paymentType;
  }
  public int getRegion() {
    return region;
  }
  public int getRoommateId() {
    return roommateId;
  }
  public int getSponsor() {
    return sponsor;
  }
  public int getUserId() {
    return userId;
  }
  public void setUserId(int userId) {
    this.userId = userId;
  }
  public void setSponsor(int sponsor) {
    this.sponsor = sponsor;
  }
  public void setRoommateId(int roommateId) {
    this.roommateId = roommateId;
  }
  public void setRegion(int region) {
    this.region = region;
  }
  public void setPaymentType(int paymentType) {
    this.paymentType = paymentType;
  }

  public void setDbTimestamp(Date dbTimestamp) {
    this.dbTimestamp = dbTimestamp;
  }
  public void setCustRoomId(int custRoomId) {
    this.custRoomId = custRoomId;
  }
  public void setCustPayment(int custPayment) {
    this.custPayment = custPayment;
  }
  public void setChkType(int chkType) {
    this.chkType = chkType;
  }

  public void setAmtRec(double amtRec) {
    this.amtRec = amtRec;
  }
  public int getBlockId() {
    return blockId;
  }
  public void setBlockId(int blockId) {
    this.blockId = blockId;
  }
  public int getCustId() {
    return custId;
  }
  public void setCustId(int custId) {
    this.custId = custId;
  }
  public int getVendorType() {
    return vendorType;
  }
  public void setVendorType(int vendorType) {
    this.vendorType = vendorType;
  }
  public int getLookupId() {
    return lookupId;
  }
  public void setLookupId(int lookupId) {
    this.lookupId = lookupId;
  }
  public int getVendorId() {
    return vendorId;
  }
  public void setVendorId(int vendorId) {
    this.vendorId = vendorId;
  }
  public String getComment() {
    return comment;
  }
  public void setComment(String comment) {
    this.comment = comment;
  }
  public String getNote() {
    return note;
  }
  public void setNote(String note) {
    this.note = note;
  }
}
