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
@ManagedBean(name = "clientRmBean")
public class clientRmBean implements Serializable{

    private String desc,who,vendorName,sysName;
    private String emailKey;
    private String dbTimestamp;
    private int clientRmID,count;
    private int sponsorId;
    private int lookupRollupId;
    private int lookupId;
    private int vendorId;
    
            

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
     * @return the dbTimestamp
     */
    public String getDbTimestamp() {
        return dbTimestamp;
    }

    /**
     * @param dbTimestamp the dbTimestamp to set
     */
    public void setDbTimestamp(String dbTimestamp) {
        this.dbTimestamp = dbTimestamp;
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
     * @return the lookupRollupId
     */
    public int getLookupRollupId() {
        return lookupRollupId;
    }

    /**
     * @param lookupRollupId the lookupRollupId to set
     */
    public void setLookupRollupId(int lookupRollupId) {
        this.lookupRollupId = lookupRollupId;
    }

    /**
     * @return the lookupId
     */
    public int getLookupId() {
        return lookupId;
    }

    /**
     * @param lookupId the lookupId to set
     */
    public void setLookupId(int lookupId) {
        this.lookupId = lookupId;
    }

    /**
     * @return the vendorId
     */
    public int getVendorId() {
        return vendorId;
    }

    /**
     * @param vendorId the vendorId to set
     */
    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    /**
     * @return the who
     */
    public String getWho() {
        return who;
    }

    /**
     * @param who the who to set
     */
    public void setWho(String who) {
        this.who = who;
    }

    /**
     * @return the vendorName
     */
    public String getVendorName() {
        return vendorName;
    }

    /**
     * @param vendorName the vendorName to set
     */
    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    /**
     * @return the sysName
     */
    public String getSysName() {
        return sysName;
    }

    /**
     * @param sysName the sysName to set
     */
    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }

   
}
