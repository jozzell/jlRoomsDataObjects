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
@ManagedBean(name = "rptFirstNightBean")
public class rptFirstNightBean implements Serializable{
public rptFirstNightBean() {
    
  }
   public rptFirstNightBean(boolean days,int cnt,int[] x) {
    this(days,cnt);
    this.setIn(x);

   }
  public rptFirstNightBean(boolean days,int cnt) {
    this();
    this.setDays(days);
    this.setDayCount(cnt);

  }

  private float[] width;
  private String display;
  private int colSpan,in[];
  private String[] header;
  private String rptHeader;
  private String sql;
  private Object[] object;
  private boolean firstNight;
  private int colOffSet;
  private boolean days=true;
  private int dayCount=1;
  private boolean customer;
  public float[] getWidth() {
    return width;
  }

  public String getDisplay() {
    return display;
  }

  public int getColSpan() {
    return colSpan;
  }

  public String[] getHeader() {
    return header;
  }

  public String getRptHeader() {
    return rptHeader;
  }

  public String getSql() {
    return sql;
  }

  public Object[] getObject() {
    return object;
  }

  public boolean isFirstNight() {
    return firstNight;
  }

  public int getColOffSet() {
    return colOffSet;
  }

  public boolean isDays() {
    return days;
  }

  public int getDayCount() {
    return dayCount;
  }

  public boolean isCustomer() {
    return customer;
  }

  public int[] getIn() {
    return in;
  }

  public void setWidth(float[] width) {
    this.width = width;
  }

  public void setDisplay(String display) {
    this.display = display;
  }

  public void setColSpan(int colSpan) {
    this.colSpan = colSpan;
  }

  public void setHeader(String[] header) {
    this.header = header;
  }

  public void setRptHeader(String rptHeader) {
    this.rptHeader = rptHeader;
  }

  public void setSql(String sql) {
    this.sql = sql;
  }

  public void setObject(Object[] object) {
    this.object = object;
  }

  public void setFirstNight(boolean firstNight) {
    this.firstNight = firstNight;
  }

  public void setColOffSet(int colOffSet) {
    this.colOffSet = colOffSet;
  }

  public void setDays(boolean days) {
    this.days = days;
  }

  public void setDayCount(int dayCount) {
    this.dayCount = dayCount;
  }

  public void setCustomer(boolean customer) {
    this.customer = customer;
  }

  public void setIn(int[] in) {
    this.in = in;
  }

}
