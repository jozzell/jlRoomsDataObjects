/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jlRoomsCommon._beans;
import java.io.Serializable;
import java.util.*;
import java.sql.Timestamp;
import javax.faces.bean.ManagedBean;
/**
 *
 * @author lmeans
 */
@ManagedBean(name = "rptBean")
public class rptBean implements Serializable{
private Vector v;
  private double amt;
  private String ary[];
  private LinkedList list;
  private int[] pick;
  private Timestamp lastPayment;


  private Date date;
  public rptBean() {

  }
  public void setArySize(int i){
      ary = new String[i];
      pick = new int[i];
  }
  public void setAryValue(String val,int i){
      if (list == null) list = new LinkedList();

      list.add(new Integer(i));
      int x = list.indexOf(new Integer(i));
      ary[x] = val;
  }
  public void settValue() {
      for (int i = 0; i < pick.length ;i++) pick[i] = 0;
  }
  public void setValue(int col,int value,Date d){
      int x = list.indexOf(new Integer(col));
      pick[x] = value;
      this.date = d;
  }
  public int getValue(int ptr){
      return pick[ptr];
  }
  public Date getDate() {
      return this.date;
  }

  public String getDisplay(int ptr){
    return ary[ptr];
      //return (String)list.get(ptr);

  }
  public int getDisplaySize() {
      return list.size();
  }


  public rptBean(Vector v, double amt) {
    this.v = v;
    this.amt = amt;
  }
  public Vector getVector() {
    return v;
  }
  public double getAmount() {
    return getAmt();
  }

    /**
     * @return the amt
     */
    public double getAmt() {
        return amt;
    }

    /**
     * @param amt the amt to set
     */
    public void setAmt(double amt) {
        this.amt = amt;
    }

    /**
     * @return the lastPayment
     */
    public Timestamp getLastPayment() {
        return lastPayment;
    }

    /**
     * @param lastPayment the lastPayment to set
     */
    public void setLastPayment(Timestamp lastPayment) {
        this.lastPayment = lastPayment;
    }

}
