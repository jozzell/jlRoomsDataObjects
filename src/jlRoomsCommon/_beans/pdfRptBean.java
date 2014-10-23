/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsCommon._beans;


import java.io.Serializable;
import java.sql.*;
import javax.faces.bean.ManagedBean;
import jlRoomsCommon.rpt.db.rptFirstNightSql;
/**
 *
 * @author lmeans
 */
@ManagedBean(name = "pdfRptBean")
public class pdfRptBean extends pdfRptBeanTypes implements Serializable{
  private Date rptDateStart;
  private Date rptDateEnd;
  private int depositType;
  private int depositNum;

  private boolean rptDate=false;
  private int reportType;
  private String reportName;
  private boolean reportEvent=false;
  private String whereClause;
  private int rptCategory;
  
    private boolean dateRange = false;
    private boolean sponsor = false;
    private boolean customer = false;
    private boolean vendor=false;
    public pdfRptBean() {
        
    }
    public pdfRptBean(int cat,int type,String heading,boolean ven,boolean cust,boolean date) {
    rptCategory = cat;
    reportType = type;
    reportName = heading;
    this.vendor = ven;
    this.customer = cust;
    this.dateRange = date;
   }
 private int whereClauseType=rptFirstNightSql.WHERE_SPONSOR;

    public void setWhereClauseType(int whereClauseType) {
        this.whereClauseType = whereClauseType;
    }
    public boolean isDeposit() {
    switch(getReportType()) {

      case DEPOSIT_CUSTOMER: case DEPOSIT_VENDOR:
        return true;

      default:
        return false;
    }

  }
  public int getWhereClauseType() {
    return whereClauseType;
  }
  public void setRptDateStart(Date rptDateStart) {

    this.rptDateStart = rptDateStart;
  }

  public void setRptDateEnd(Date rptDateEnd) {

    this.rptDateEnd = rptDateEnd;
  }

  public void setDepositType(int depositType) {
    this.depositType = depositType;

  }

  public void setDepositNum(int depositNum) {

    this.depositNum = depositNum;
  }



  public void setRptDate(boolean rptDate) {
    this.rptDate = rptDate;
  }

  public void setReportType(int reportType) {
    this.reportType = reportType;
  }

  public void setReportName(String reportName) {
    this.reportName = reportName;
  }

  public void setReportEvent(boolean reportEvent) {
    this.reportEvent = reportEvent;
  }

  public void setWhereClause(String whereClause) {
    this.whereClause = whereClause;
  }

  public void setRptCategory(int rptCategory) {
    this.rptCategory = rptCategory;
  }

  

  public Date getRptDateStart() {

    return rptDateStart;
  }

  public Date getRptDateEnd() {

    return rptDateEnd;
  }

  public int getDepositType() {

    return depositType;
  }

  public int getDepositNum() {

    return depositNum;
  }

  

  public boolean isRptDate() {
    return rptDate;
  }

  public int getReportType() {
    return reportType;
  }

  public String getReportName() {
    return reportName;
  }
  public boolean isReportEvent() {
    return reportEvent;
  }

  public String getWhereClause() {
    return whereClause;
  }

  public int getRptCategory() {
    return rptCategory;
  }

  


    /**
     * @return the dateRange
     */
    public boolean isDateRange() {
        return dateRange;
    }

    /**
     * @return the sponsor
     */
    public boolean isSponsor() {
        return sponsor;
    }

    /**
     * @return the customer
     */
    public boolean isCustomer() {
        return customer;
    }

    /**
     * @return the vendor
     */
    public boolean isVendor() {
        return vendor;
    }
}
