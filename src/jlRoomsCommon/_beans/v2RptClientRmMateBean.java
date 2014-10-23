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
@ManagedBean(name = "v2RptClientRmMateBean")
public class v2RptClientRmMateBean  implements Serializable{
     private String firstName, lastName, addr1, addr2, city, state, hmPhone,
            wkPhone, zip, EMail;
  private String wkPhoneExt;
  private int custId,custRmMateId;
   private String desc;
    private String vendor;
    private String lookupDesc;
    private int custRmId;
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
     * @return the addr1
     */
    public String getAddr1() {
        return addr1;
    }

    /**
     * @param addr1 the addr1 to set
     */
    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    /**
     * @return the addr2
     */
    public String getAddr2() {
        return addr2;
    }

    /**
     * @param addr2 the addr2 to set
     */
    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the hmPhone
     */
    public String getHmPhone() {
        return hmPhone;
    }

    /**
     * @param hmPhone the hmPhone to set
     */
    public void setHmPhone(String hmPhone) {
        this.hmPhone = hmPhone;
    }

    /**
     * @return the wkPhone
     */
    public String getWkPhone() {
        return wkPhone;
    }

    /**
     * @param wkPhone the wkPhone to set
     */
    public void setWkPhone(String wkPhone) {
        this.wkPhone = wkPhone;
    }

    /**
     * @return the zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * @param zip the zip to set
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * @return the eMail
     */
   
    /**
     * @return the wkPhoneExt
     */
    public String getWkPhoneExt() {
        return wkPhoneExt;
    }

    /**
     * @param wkPhoneExt the wkPhoneExt to set
     */
    public void setWkPhoneExt(String wkPhoneExt) {
        this.wkPhoneExt = wkPhoneExt;
    }

    /**
     * @return the custId
     */
    public int getCustId() {
        return custId;
    }

    /**
     * @param custId the custId to set
     */
    public void setCustId(int custId) {
        this.custId = custId;
    }

    /**
     * @return the EMail
     */
    public String getEMail() {
        return EMail;
    }

    /**
     * @param EMail the EMail to set
     */
    public void setEMail(String EMail) {
        this.EMail = EMail;
    }

    /**
     * @return the custRmMateId
     */
    public int getCustRmMateId() {
        return custRmMateId;
    }

    /**
     * @param custRmMateId the custRmMateId to set
     */
    public void setCustRmMateId(int custRmMateId) {
        this.custRmMateId = custRmMateId;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
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
     * @return the lookupDesc
     */
    public String getLookupDesc() {
        return lookupDesc;
    }

    /**
     * @param lookupDesc the lookupDesc to set
     */
    public void setLookupDesc(String lookupDesc) {
        this.lookupDesc = lookupDesc;
    }

    /**
     * @return the custRmId
     */
    public int getCustRmId() {
        return custRmId;
    }

    /**
     * @param custRmId the custRmId to set
     */
    public void setCustRmId(int custRmId) {
        this.custRmId = custRmId;
    }
}
