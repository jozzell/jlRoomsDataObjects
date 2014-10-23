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
@ManagedBean(name = "clientRmMateBean")
public class clientRmMateBean implements Serializable{
    private int clientRmMateId;
    private int clientRmID;
    private int sponsorId;
    private int custId;
    private String emailKey,customerName,address,email,phone,wkPhone;

    /**
     * @return the clientRmMateId
     */
    public int getClientRmMateId() {
        return clientRmMateId;
    }

    /**
     * @param clientRmMateId the clientRmMateId to set
     */
    public void setClientRmMateId(int clientRmMateId) {
        this.clientRmMateId = clientRmMateId;
    }

    /**
     * @return the clientRmID
     */
    public int getClientRmID() {
        return clientRmID;
    }

    /**
     * @param clientRmID the clientRmID to set
     */
    public void setClientRmID(int clientRmID) {
        this.clientRmID = clientRmID;
    }

    /**
     * @return the sponsorId
     */
    public int getSponsorId() {
        return sponsorId;
    }

    /**
     * @param sponsorId the sponsorId to set
     */
    public void setSponsorId(int sponsorId) {
        this.sponsorId = sponsorId;
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
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
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
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
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
}
