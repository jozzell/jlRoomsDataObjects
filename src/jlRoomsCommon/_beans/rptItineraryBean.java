
package jlRoomsCommon._beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author lmeans
 */
@ManagedBean(name = "rptItineraryBean")
public class rptItineraryBean implements Serializable{
    private String effDate;
    private String endDate;
    private double rmCost;
    private double custRmCnt;
    private String rmComment;
    private String balancedDueDate;
    private String balanceComment;
    private int roommdateSplit;
    private String vendorName;
    private String sysDesc;
    private int vendorType;
    private int roommdateId;
    private double totalCost;
    private int flt_id;
    private String balanceDueBy;
    private String cutoffDate;
    private String cancelDate;
    private String checkDueBy;
    private String roomDesc;
    private String addr1;
    private String addr2;
    private String city;
    private String state;
    private String zip;
    private String phone;
    private int custRoomType;
    private int days;
    private String rptDesc;
    private String fltNumber;
    private String fltComment;
    private String fltArrDate;
    private String fltArrTime;
    private String fltArrPlace;
    private String fltDepDate;
    private String fltDepTime;
    private String fltDepPlace;
    private String vendorTypeDesc;
    private int id;
    /**
     * @return the effDate
     */
    public String getEffDate() {
        return effDate;
    }

    /**
     * @param effDate the effDate to set
     */
    public void setEffDate(String effDate) {
        this.effDate = effDate;
    }

    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
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

    /**
     * @return the custRmCnt
     */
    public double getCustRmCnt() {
        return custRmCnt;
    }

    /**
     * @param custRmCnt the custRmCnt to set
     */
    public void setCustRmCnt(double custRmCnt) {
        this.custRmCnt = custRmCnt;
    }

    /**
     * @return the rmComment
     */
    public String getRmComment() {
        return rmComment;
    }

    /**
     * @param rmComment the rmComment to set
     */
    public void setRmComment(String rmComment) {
        this.rmComment = rmComment;
    }

    /**
     * @return the bbalancedDueDate
     */
    public String getBalancedDueDate() {
        return balancedDueDate;
    }

    /**
     * @param bbalancedDueDate the bbalancedDueDate to set
     */
    public void setBalancedDueDate(String balancedDueDate) {
        this.balancedDueDate = balancedDueDate;
    }

    /**
     * @return the balanceComment
     */
    public String getBalanceComment() {
        return balanceComment;
    }

    /**
     * @param balanceComment the balanceComment to set
     */
    public void setBalanceComment(String balanceComment) {
        this.balanceComment = balanceComment;
    }

    /**
     * @return the roommdateSplit
     */
    public int getRoommdateSplit() {
        return roommdateSplit;
    }

    /**
     * @param roommdateSplit the roommdateSplit to set
     */
    public void setRoommdateSplit(int roommdateSplit) {
        this.roommdateSplit = roommdateSplit;
    }

    /**
     * @return the vendorName
     */
    public String getVendorName() {
        return vendorName;
    }

    /**
     * @param vendorName the vendorName to set
     */
    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    /**
     * @return the sysDesc
     */
    public String getSysDesc() {
        return sysDesc;
    }

    /**
     * @param sysDesc the sysDesc to set
     */
    public void setSysDesc(String sysDesc) {
        this.sysDesc = sysDesc;
    }

    /**
     * @return the vendorType
     */
    public int getVendorType() {
        return vendorType;
    }

    /**
     * @param vendorType the vendorType to set
     */
    public void setVendorType(int vendorType) {
        this.vendorType = vendorType;
    }

    /**
     * @return the roommdateId
     */
    public int getRoommdateId() {
        return roommdateId;
    }

    /**
     * @param roommdateId the roommdateId to set
     */
    public void setRoommdateId(int roommdateId) {
        this.roommdateId = roommdateId;
    }

    /**
     * @return the totalCost
     */
    public double getTotalCost() {
        return totalCost;
    }

    /**
     * @param totalCost the totalCost to set
     */
    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
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
     * @return the balanceDueBy
     */
    public String getBalanceDueBy() {
        return balanceDueBy;
    }

    /**
     * @param balanceDueBy the balanceDueBy to set
     */
    public void setBalanceDueBy(String balanceDueBy) {
        this.balanceDueBy = balanceDueBy;
    }

    /**
     * @return the cutoffDate
     */
    public String getCutoffDate() {
        return cutoffDate;
    }

    /**
     * @param cutoffDate the cutoffDate to set
     */
    public void setCutoffDate(String cutoffDate) {
        this.cutoffDate = cutoffDate;
    }

    /**
     * @return the cancelDate
     */
    public String getCancelDate() {
        return cancelDate;
    }

    /**
     * @param cancelDate the cancelDate to set
     */
    public void setCancelDate(String cancelDate) {
        this.cancelDate = cancelDate;
    }

    /**
     * @return the checkDueBy
     */
    public String getCheckDueBy() {
        return checkDueBy;
    }

    /**
     * @param checkDueBy the checkDueBy to set
     */
    public void setCheckDueBy(String checkDueBy) {
        this.checkDueBy = checkDueBy;
    }

    /**
     * @return the roomDesc
     */
    public String getRoomDesc() {
        return roomDesc;
    }

    /**
     * @param roomDesc the roomDesc to set
     */
    public void setRoomDesc(String roomDesc) {
        this.roomDesc = roomDesc;
    }

    /**
     * @return the addr1
     */
    public String getAddr1() {
        return addr1;
    }

    /**
     * @param addr1 the addr1 to set
     */
    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    /**
     * @return the addr2
     */
    public String getAddr2() {
        return addr2;
    }

    /**
     * @param addr2 the addr2 to set
     */
    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * @param zip the zip to set
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the custRoomType
     */
    public int getCustRoomType() {
        return custRoomType;
    }

    /**
     * @param custRoomType the custRoomType to set
     */
    public void setCustRoomType(int custRoomType) {
        this.custRoomType = custRoomType;
    }

    /**
     * @return the days
     */
    public int getDays() {
        return days;
    }

    /**
     * @param days the days to set
     */
    public void setDays(int days) {
        this.days = days;
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
     * @return the fltComment
     */
    public String getFltComment() {
        return fltComment;
    }

    /**
     * @param fltComment the fltComment to set
     */
    public void setFltComment(String fltComment) {
        this.fltComment = fltComment;
    }

    /**
     * @return the fltArrDate
     */
    public String getFltArrDate() {
        return fltArrDate;
    }

    /**
     * @param fltArrDate the fltArrDate to set
     */
    public void setFltArrDate(String fltArrDate) {
        this.fltArrDate = fltArrDate;
    }

    /**
     * @return the fltArrTime
     */
    public String getFltArrTime() {
        return fltArrTime;
    }

    /**
     * @param fltArrTime the fltArrTime to set
     */
    public void setFltArrTime(String fltArrTime) {
        this.fltArrTime = fltArrTime;
    }

    /**
     * @return the fltArrPlace
     */
    public String getFltArrPlace() {
        return fltArrPlace;
    }

    /**
     * @param fltArrPlace the fltArrPlace to set
     */
    public void setFltArrPlace(String fltArrPlace) {
        this.fltArrPlace = fltArrPlace;
    }

    /**
     * @return the fltDepDate
     */
    public String getFltDepDate() {
        return fltDepDate;
    }

    /**
     * @param fltDepDate the fltDepDate to set
     */
    public void setFltDepDate(String fltDepDate) {
        this.fltDepDate = fltDepDate;
    }

    /**
     * @return the fltDepTime
     */
    public String getFltDepTime() {
        return fltDepTime;
    }

    /**
     * @param fltDepTime the fltDepTime to set
     */
    public void setFltDepTime(String fltDepTime) {
        this.fltDepTime = fltDepTime;
    }

    /**
     * @return the fltDepPlace
     */
    public String getFltDepPlace() {
        return fltDepPlace;
    }

    /**
     * @param fltDepPlace the fltDepPlace to set
     */
    public void setFltDepPlace(String fltDepPlace) {
        this.fltDepPlace = fltDepPlace;
    }

    /**
     * @return the vendorTypeDesc
     */
    public String getVendorTypeDesc() {
        return vendorTypeDesc;
    }

    /**
     * @param vendorTypeDesc the vendorTypeDesc to set
     */
    public void setVendorTypeDesc(String vendorTypeDesc) {
        this.vendorTypeDesc = vendorTypeDesc;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

}
