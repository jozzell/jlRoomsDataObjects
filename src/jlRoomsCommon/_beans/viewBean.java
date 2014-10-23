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
@ManagedBean(name = "viewBean")
public class viewBean implements Serializable{
private String disp;
  public int id;
  public int status;
  public viewBean() {
  }
  public viewBean(int id,String disp,int status) {
      this.disp = disp;
      this.id = id;
      this.status = status;

  }
  public String getDisp() {
    return disp;
  }
  public void setDisp(String disp) {
    this.disp = disp;
  }
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public int getStatus() {
    return status;
  }
  public void setStatus(int status) {
    this.status = status;
  }
}
