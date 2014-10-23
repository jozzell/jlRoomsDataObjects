/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jlRoomsCommon._beans;
import java.io.Serializable;
import java.util.Calendar;
import javax.faces.bean.ManagedBean;
/**
 *
 * @author lmeans
 */
@ManagedBean(name = "custPayBean")
public class custPayBean implements Serializable{
private java.util.Date date = Calendar.getInstance().getTime();
  private String note,vendor;
  private String comment,paymentTypeDesc,paymentRollupDesc;
  private double amount;
  private int paymentType,paymentRollup,paymentCategory;
  private boolean paymentBlock;
  public custPayBean() {
  }
  public String getNote() {
    return note;
  }
  public void setNote(String note) {
    this.note = note;
  }
  public String getComment() {
    return comment;
  }
  public void setComment(String comment) {
    this.comment = comment;
  }
  public double getAmount() {
    return amount;
  }
  public void setAmount(double amount) {
    this.amount = amount;
  }
  public int getPaymentType() {
    return paymentType;
  }
  public void setPaymentType(int paymentType) {
    this.paymentType = paymentType;
  }
  public java.util.Date getDate() {
    return date;
  }
  public void setDate(java.util.Date date) {
    this.date = date;
  }
  public int getPaymentRollup() {
    return paymentRollup;
  }
  public void setPaymentRollup(int paymentRollup) {
    this.paymentRollup = paymentRollup;
  }



    /**
     * @return the vendor
     */
    public String getVendor() {
        return vendor;
    }

    /**
     * @param vendor the vendor to set
     */
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    /**
     * @return the paymentTypeDesc
     */
    public String getPaymentTypeDesc() {
        return paymentTypeDesc;
    }

    /**
     * @param paymentTypeDesc the paymentTypeDesc to set
     */
    public void setPaymentTypeDesc(String paymentTypeDesc) {
        this.paymentTypeDesc = paymentTypeDesc;
    }

    /**
     * @return the paymentRollupDesc
     */
    public String getPaymentRollupDesc() {
        return paymentRollupDesc;
    }

    /**
     * @param paymentRollupDesc the paymentRollupDesc to set
     */
    public void setPaymentRollupDesc(String paymentRollupDesc) {
        this.paymentRollupDesc = paymentRollupDesc;
    }

    /**
     * @return the paymentBlock
     */
    public boolean isPaymentBlock() {
        return paymentBlock;
    }

    /**
     * @param paymentBlock the paymentBlock to set
     */
    public void setPaymentBlock(boolean paymentBlock) {
        this.paymentBlock = paymentBlock;
    }

    /**
     * @return the paymentCategory
     */
    public int getPaymentCategory() {
        return paymentCategory;
    }

    /**
     * @param paymentCategory the paymentCategory to set
     */
    public void setPaymentCategory(int paymentCategory) {
        this.paymentCategory = paymentCategory;
    }
}
