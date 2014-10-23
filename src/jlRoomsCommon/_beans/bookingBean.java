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
@ManagedBean(name = "bookingBean")
public class bookingBean implements Serializable {
public bookingBean() {
  }
  /*
    *************************************************
    *************************************************
  */
  public void setBlockId(int i) {
    block = i;
  }
  public int getBlockId() {
    return block;
  }
  /*
    *************************************************
    *************************************************
  */
  public void setVendorTypeDesc(String s) {
    vDesc = s;
  }
  public String getVendorTypeDesc() {
    return vDesc;
  }
  /*
    *************************************************
    *************************************************
  */
  public void setVendorId(int i) {
    vendor = i;
  }
  public int getVendorId() {
    return vendor;
  }
  /*
    *************************************************
    *************************************************
  */
  public void setVendorName(String s) {
    vName = s;
  }
  public String getVendorName() {
    return vName;
  }
  /*
    *************************************************
    *************************************************
  */
  public void setRmTypeDesc(String s) {
    rType = s;
  }
  public String getRmTypeDesc() {
    return rType;
  }
  /*
    *************************************************
    *************************************************
  */
  public void setRoomsBooked(double d) {
    booked = d;
  }
  public double getRoomsBooked() {
    return booked;
  }
  /*
    *************************************************
    *************************************************
  */
  public void setAvailableRooms(double d) {
    avl =d;
  }
  public double getAvailableRooms() {
    return avl;
  }
  /*
    *************************************************
    *************************************************
  */
  public void setRoomCost(double d) {
    cost = d;
  }
  public double getRoomCost() {
    return cost;
  }
  /*
    *************************************************
    *************************************************
  */
  public void setFlag(int i) {
    flag = i;
  }
  public int getFlag() {
    return flag;
  }
  /*
    *************************************************
    *************************************************
  */
  public void setCutoffDate(Date d) {
    cutoff = d;
  }
  public Date getCutoffDate() {
    return cutoff;
  }
  public double getTotalCost() {
    return totalCost;
  }
  public void setTotalCost(double totalCost) {
    this.totalCost = totalCost;
  }
  /*
    *************************************************
    *************************************************
  */


  private double
    booked,avl,cost;
  private Date cutoff;
  private String
    vDesc,vName,rType;
  private int
    block,vendor,flag;
  private double totalCost;
}
