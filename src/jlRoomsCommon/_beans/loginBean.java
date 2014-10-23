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
@ManagedBean(name = "loginBean")
public class loginBean implements Serializable{
 private int userId=-1;
  private int sponsorId=-1;
  private int sponsoLinkId=-1;
  private int customeId=-1,accessLevel=0,flagInd;
  private String passWord,passWordCheck,email,hostName,hostIP,lastName,firstName,emailKey,key,userName;
  private java.util.Date loginDate;
  public loginBean() {
  }

 
  public int getCustomeId() {
    return customeId;
  }
  public int getSponsoLinkId() {
    return sponsoLinkId;
  }
  public int getSponsorId() {
    return sponsorId;
  }
  public int getUserId() {
    return userId;
  }
  public void setCustomeId(int customeId) {
    this.customeId = customeId;
  }
  public void setSponsoLinkId(int sponsoLinkId) {
    this.sponsoLinkId = sponsoLinkId;
  }
  public void setSponsorId(int sponsorId) {
    this.sponsorId = sponsorId;
  }
  public void setUserId(int userId) {
    this.userId = userId;
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
    public void setAccessLevel(int x) {

        //if (accessLevel > jlRoomsFactory.accessAryString.length) accessLevel = 0;
        this.accessLevel = x;
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
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the hostName
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * @param hostName the hostName to set
     */
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    /**
     * @return the hostIP
     */
    public String getHostIP() {
        return hostIP;
    }

    /**
     * @param hostIP the hostIP to set
     */
    public void setHostIP(String hostIP) {
        this.hostIP = hostIP;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the flagInd
     */
    public int getFlagInd() {
        return flagInd;
    }

    /**
     * @param flagInd the flagInd to set
     */
    public void setFlagInd(int flagInd) {
        this.flagInd = flagInd;
    }

    /**
     * @return the loginDate
     */
    public java.util.Date getLoginDate() {
        return loginDate;
    }

    /**
     * @param loginDate the loginDate to set
     */
    public void setLoginDate(java.util.Date loginDate) {
        this.loginDate = loginDate;
    }

    public boolean expiredDate() {

        return false;
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
        if (emailKey != null) this.emailKey = emailKey.toLowerCase().trim();
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
        if (this.key != null) this.key = this.key.toLowerCase().trim();
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
     * @return the passWordCheck
     */
    public String getPassWordCheck() {
        return passWordCheck;
    }

    /**
     * @param passWordCheck the passWordCheck to set
     */
    public void setPassWordCheck(String passWordCheck) {
        this.passWordCheck = passWordCheck;
    }

    /**
     * @return the strVal
     */
   

}
