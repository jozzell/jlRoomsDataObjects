/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jlRoomsCommon._beans;
import java.io.Serializable;
import java.util.*;
import javax.faces.bean.ManagedBean;
import jlRoomsCommon.sponsor.db.sponsorObj;


/**
 *
 * @author lmeans
 */
@ManagedBean(name = "sponsorListBean")
public class sponsorListBean implements Serializable{
 private sponsorObj sponsorObj = null; //new sponsorObj();
  private Collection SponsorList;
   private String property;
   public void sponsorListBean() {

  }
public Collection getSponsorList() {
    System.out.println("sponsorListBean: Collection getGetSponsorList()");
    //->if (this.sponsorObj == null) sponsorObj = new sponsorObj();

    try {
      //setSponsorList(sponsorObj.BrwSponsorEvent(0, 1));
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("sponsorListBean: Collection getGetSponsorList() done");
    //return jlRooms.obj.sponsorObj.BrwSponsorEvent(0,1);
    return SponsorList;
  }
public void setSponsorList(Collection list) {
    System.out.println("sponsorListBean: setGetSponsorList(Collection getSponsorList) : " + list.size());
    this.SponsorList = list;
  }

  public String eventSelected() {

    return "";
  }

  public void setProperty(String property) {
    this.property = property;
  }

  public String getProperty() {
    return property;
  }

 

  

  
}
