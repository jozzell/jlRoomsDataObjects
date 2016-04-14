/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsCommon._beans;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.faces.bean.ManagedBean;
import jlRoomsCommon.JlRoomsDataObjects;
import jlRoomsCommon.vendorObjTypesENum;
import obj.reusableObj;
/**
 *
 * @author lmeans
 */
@ManagedBean(name = "custRmBean")
public class custRmBean implements Serializable {
private int custRoomId,custId,blockId,custRoomType,flag=0,roomPaid,loginId;
  private int region,sponsor,roommateSplit=1,vendorType;
  private double custRoomCnt=1,rmCost,blockServiceCharge,blockProcessFee,blockHotelFee;
  private String dueComment,roomComment,rmTypeDesc,rmDesc,vendor,fullName,status,lookupDesc;
  private Date effDate,endDate,due;
  private String dbTimeStamp;
  private boolean applyProcFee=true;

  private int days=1,picked ;
  private int roommateId;
  private double totalCost,blockCostCnt;
  private int lookupId;
  private int vendorId;
  private int fltId;
  private int sponsorHotelId;
  private String display1,display2,dspStr1,ticket_info,effDateStr;
  public custRmBean() {

  }
  public String backButton(){
      if (this.custRoomId == 0) return "true";
      return "false";
              
  }
  public String getRptDateDisplay(){
      if (dspStr1 != null && dspStr1.trim().length() > 0){
          return dspStr1;
      } else {
          return effDateStr;
      }
      
  }
  public String displayRmSplit(){
     if (     vendorType == vendorObjTypesENum.HOTEL.getType() || 
              vendorType == vendorObjTypesENum.CRUISE.getType() ||
              vendorType == -18){
        return "true";
    }else {
        return "false";
    }
  }
  public String getSplitDisp(){
      if (this.roommateSplit <= 1){
          return "";
         
      } else {
          return " (Split 1/"+roommateSplit+")";
      }
  }
  public void setCustRmBean(custRmBean bean) {
    vendor = bean.getVendor();
    rmTypeDesc = bean.getRmTypeDesc();
    rmDesc = bean.getRmDesc();
  }
  public int getBlockId() {
    return blockId;
  }
  public int getCustId() {
    return custId;
  }
  public double getCustRoomCnt() {
      
    return (new reusableObj()).round(custRoomCnt,2);
  }
  public int getCustRoomId() {
    return custRoomId;
  }
  public int getCustRoomType() {
    return custRoomType;
  }
  public Date getDue() {
    return due;
  }
  public String getDueComment() {
    return dueComment;
  }
  public Date getEffDate() {
    return effDate;
  }
  public Date getEndDate() {
    return endDate;
  }

  public int getFlag() {
    return flag;
  }
  public String getFullName() {
    return fullName;
  }
  public int getRegion() {
    return region;
  }
  public double getRmCost() {
      return (new reusableObj()).round(rmCost,2);
    
  }
  public String getRmDesc() {
    return rmDesc;
  }
  public String getRmTypeDesc() {
    return rmTypeDesc;
  }
  public String getRoomComment() {
    return roomComment;
  }
  public int getRoomPaid() {
    return roomPaid;
  }
  //public String getRoomType() {
    //return roomType;
  //}
  public int getSponsor() {
    return sponsor;
  }
  public String getStatus() {
    return status;
  }
  public String getVendor() {
    return vendor;
  }

  public void setVendor(String vendor) {
    this.vendor = vendor;
  }
  public void setStatus(String status) {
    this.status = status;
  }
  public void setSponsor(int sponsor) {
    this.sponsor = sponsor;
  }
 // public void setRoomType(String roomType) {
    //this.roomType = roomType;
  //}
  public void setRoomPaid(int roomPaid) {
    this.roomPaid = roomPaid;
  }
  public void setRoomComment(String roomComment) {
    this.roomComment = roomComment;
  }
  public void setRmTypeDesc(String rmTypeDesc) {
    this.rmTypeDesc = rmTypeDesc;
  }
  public void setRmDesc(String rmDesc) {
    this.rmDesc = rmDesc;
  }
  public void setRmCost(double rmCost) {
    this.rmCost = rmCost;
  }
  public void setRegion(int region) {
    this.region = region;
  }
  public void setFullName(String fullName) {
    this.fullName = fullName;
  }
  public void setFlag(int flag) {
    this.flag = flag;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }
  public void setEffDate(Date effDate) {
    this.effDate = effDate;
    if (effDate != null){
        effDateStr = (new SimpleDateFormat("EEE MMM dd yyyy hh:mm a")).format(effDate);
    }  else {
        effDateStr = "";
    }
  }
  public void setDueComment(String dueComment) {
    this.dueComment = dueComment;
  }
  public void setDue(Date due) {
    this.due = due;
  }
  public void setCustRoomType(int custRoomType) {
    this.custRoomType = custRoomType;
  }
  public void setCustRoomId(int custRoomId) {
    this.custRoomId = custRoomId;
  }
  public void setCustRoomCnt(double custRoomCnt) {
    this.custRoomCnt = custRoomCnt;
  }
  public void setCustId(int custId) {
    this.custId = custId;
  }
  public void setBlockId(int blockId) {
    this.blockId = blockId;
  }


  public int getVendorType() {
    return vendorType;
  }
  public void setVendorType(int vendorType) {
      String str = "";
        switch(vendorType){
            case -2:
                str = "Cost/Night";
                break;
             case -13:
                 str = "Cost/Day";
                 break;
            default:
                str = "Total Cost";
                break;
        }
       
        setDisplay1(str);
        setDisplay2("# of " +  vendorObjTypesENum.DEFAULT.getENUMPicked(vendorType).getRoomDesc());
    this.vendorType = vendorType;
  }
  public int getDays() {
    return days;
  }
  public void setDays(int days) {
    this.days = days;
  }
  public int getRoommateId() {
    return roommateId;
  }
  public void setRoommateId(int roommateId) {
    this.roommateId = roommateId;
  }
  public double getTotalCost() {
      return (new reusableObj()).round(totalCost,2);
   
  }
  public void setTotalCost(double totalCost) {
    this.totalCost = totalCost;
  }
  public int getRoommateSplit() {
    return roommateSplit;
  }
  public void setRoommateSplit(int roommateSplit) {
    this.roommateSplit = roommateSplit;
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
  public int getFltId() {
    return fltId;
  }
  public void setFltId(int fltId) {
    this.fltId = fltId;
  }
  public String getDbTimeStamp() {
    if (dbTimeStamp == null) return "";
    return dbTimeStamp;
  }
  public void setDbTimeStamp(String dbTimeStamp) {
    this.dbTimeStamp = dbTimeStamp;
  }
  public double getBlockProcessFee() {
    return blockProcessFee;
  }
  public double getBlockServiceCharge() {
    return blockServiceCharge;
  }
  public double getBlockHotelFee() {
    return blockHotelFee;
  }
  public void setBlockServiceCharge(double blockServiceCharge) {
    this.blockServiceCharge = blockServiceCharge;
  }
  public void setBlockProcessFee(double blockProcessFee) {
    this.blockProcessFee = blockProcessFee;
  }
  public void setBlockHotelFee(double blockHotelFee) {
    this.blockHotelFee = blockHotelFee;
  }
  public int getSponsorHotelId() {
    return sponsorHotelId;
  }

  public double getBlockCostCnt() {
      return (new reusableObj()).round(blockCostCnt,2);
  }

  public void setSponsorHotelId(int sponsorHotelId) {
    this.sponsorHotelId = sponsorHotelId;
  }

  public void setBlockCostCnt(double blockCostCnt) {
    this.blockCostCnt = blockCostCnt;
  }


    /**
     * @return the lookupDesc
     */
    public String getLookupDesc() {
        return lookupDesc;
    }

    /**
     * @param lookupDesc the lookupDesc to set
     */
    public void setLookupDesc(String lookupDesc) {
        this.lookupDesc = lookupDesc;
    }

    /**
     * @return the display1
     */
    public String getDisplay1() {
        return display1;
    }

    /**
     * @param display1 the display1 to set
     */
    public void setDisplay1(String display1) {
        this.display1 = display1;
    }

    /**
     * @return the display2
     */
    public String getDisplay2() {
        return display2;
    }

    /**
     * @param display2 the display2 to set
     */
    public void setDisplay2(String display2) {
        this.display2 = display2;
    }

    /**
     * @return the loginId
     */
    public int getLoginId() {
        return loginId;
    }

    /**
     * @param loginId the loginId to set
     */
    public void setLoginId(int loginId) {
        this.loginId = loginId;
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
     * @return the picked
     */
    public int getPicked() {
        return picked;
    }

    /**
     * @param picked the picked to set
     */
    public void setPicked(int picked) {
        this.picked = picked;
    }

    /**
     * @return the applyProcFee
     */
    public boolean isApplyProcFee() {
        return applyProcFee;
    }

    /**
     * @param applyProcFee the applyProcFee to set
     */
    public void setApplyProcFee(boolean applyProcFee) {
        this.applyProcFee = applyProcFee;
    }

    /**
     * @return the ticket_info
     */
    public String getTicket_info() {
        return ticket_info;
    }

    /**
     * @param ticket_info the ticket_info to set
     */
    public void setTicket_info(String ticket_info) {
        this.ticket_info = ticket_info;
    }
}
