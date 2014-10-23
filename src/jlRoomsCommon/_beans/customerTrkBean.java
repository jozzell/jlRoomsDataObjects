package jlRoomsCommon._beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;

/**
 * Title:        Room Tracking Web Applicaion (rtwa)
 * Description:
 * Copyright:    Copyright (c) 2003
 * Company:      Jozzell, llc
 * @author Lloyd Means
 * @version 1.0
 */
@ManagedBean(name = "customerTrkBean")
public class customerTrkBean implements Serializable{

  public customerTrkBean() {
  }
  public void setRegionId(int i) {
    region = i;
  }
  public int getRegionId() {
    return region;
  }
  public void setSponsorId(int i) {
    sponsor = i;
  }
  public int getSponsorId() {
    return sponsor;
  }
  public void setCustomer(int i) {
    customer  = i;
  }
  public int getCustomer() {
    return customer;
  }
  
  private int
    region,sponsor,customer;
}