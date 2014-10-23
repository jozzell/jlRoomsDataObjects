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
@ManagedBean(name = "custBean")
public class custBean implements Serializable{
 private int custId, region,vendorID,accessLevel ;
    private String firstName, lastName, addr1, addr2, city, state, hmPhone,
            wkPhone, zip, eMail,keyStr,userName,passWord,emailKey;

    private boolean isvalid;
  private String wkPhoneExt,passWordChk;
  public custBean(){
      
  }
  public int getID() {
    return this.custId;
  }
  public String getDisplayFullName() {
    String s = "";
    if (firstName != null && firstName.trim().length() > 0) s+= this.firstName+" ";
    if (lastName != null && lastName.trim().length() > 0) s+= this.lastName;
    if (s.trim().length() == 0) return "";
    return s;
  }

  public String getDisplayTown() {
    String s = "";
   if (this.city != null) s+=this.city+" ";
   if (this.state != null) s+= this.state+" ";
   if (this.zip != null) s+= this.zip+" ";
   if (s.trim().length() == 0) return "";
   return s.trim();

  }

    public String getAddr1() {
        return addr1;
    }

    public String getAddr2() {
        return addr2;
    }

    public String getCity() {
        return city;
    }

    public int getCustId() {
        return custId;
    }

    public String getEMail() {
        return eMail;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getHmPhone() {
        return hmPhone;
    }

    public String getLastName() {
        return lastName;
    }

    public int getRegion() {
        return region;
    }

    public String getState() {
        return state;
    }

    public String getWkPhone() {
        return wkPhone;
    }

    public String getZip() {
        return zip;
    }


    public boolean getIsvalid() {
        return (this.custId <=0 ? true :false);
    }





    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setWkPhone(String wkPhone) {
        this.wkPhone = wkPhone;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setHmPhone(String hmPhone) {
        this.hmPhone = hmPhone;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public void setCustId(int custid) {
        this.custId = custid;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }


    public void setIsvalid(boolean isvalid) {
        this.isvalid = isvalid;
    }



  public void setSize(int size) {

    }






  public String getWkPhoneExt() {
    return wkPhoneExt;
  }
  public void setWkPhoneExt(String wkPhoneExt) {
    this.wkPhoneExt = wkPhoneExt;
  }

    /**
     * @return the keyStr
     */
    public String getKeyStr() {
        return keyStr;
    }

    /**
     * @param keyStr the keyStr to set
     */
    public void setKeyStr(String keyStr) {
        this.keyStr = keyStr;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the passWord
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     * @param passWord the passWord to set
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    /**
     * @return the emailKey
     */
    public String getEmailKey() {
        return emailKey;
    }

    /**
     * @param emailKey the emailKey to set
     */
    public void setEmailKey(String emailKey) {
        this.emailKey = emailKey;
    }

    /**
     * @return the vendorID
     */
    public int getVendorID() {
        return vendorID;
    }

    /**
     * @param vendorID the vendorID to set
     */
    public void setVendorID(int vendorID) {
        this.vendorID = vendorID;
    }

    /**
     * @return the accessLevel
     */
    public int getAccessLevel() {
        return accessLevel;
    }

    /**
     * @param accessLevel the accessLevel to set
     */
    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    /**
     * @return the passWordChk
     */
    public String getPassWordChk() {
        return passWordChk;
    }

    /**
     * @param passWordChk the passWordChk to set
     */
    public void setPassWordChk(String passWordChk) {
        this.passWordChk = passWordChk;
    }
}
