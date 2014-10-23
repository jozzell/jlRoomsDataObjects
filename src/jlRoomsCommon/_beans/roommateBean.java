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
@ManagedBean(name = "roommateBean")
public class roommateBean implements Serializable{
private boolean primary=false;
        private String name,desc,vendorName,addr1,city;
        private int custId,rmId,sysId,hotelId,primId,custRoomId;
        public roommateBean(){
        }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
 public String color(){
     if (this.primary) return ";color: red";
     return "";
 }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
     * @return the rmId
     */
    public int getRmId() {
        return rmId;
    }

    /**
     * @param rmId the rmId to set
     */
    public void setRmId(int rmId) {
        this.rmId = rmId;
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
     * @return the sysId
     */
    public int getSysId() {
        return sysId;
    }

    /**
     * @param sysId the sysId to set
     */
    public void setSysId(int sysId) {
        this.sysId = sysId;
    }

    /**
     * @return the primary
     */
    public boolean isPrimary() {
        return primary;
    }

    /**
     * @param primary the primary to set
     */
    public void setPrimary(boolean primary) {
        this.primary = primary;
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
     * @return the hotelId
     */
    public int getHotelId() {
        return hotelId;
    }

    /**
     * @param hotelId the hotelId to set
     */
    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    /**
     * @return the primId
     */
    public int getPrimId() {
        return primId;
    }

    /**
     * @param primId the primId to set
     */
    public void setPrimId(int primId) {
        this.primId = primId;
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
     * @return the custRoomId
     */
    public int getCustRoomId() {
        return custRoomId;
    }

    /**
     * @param custRoomId the custRoomId to set
     */
    public void setCustRoomId(int custRoomId) {
        this.custRoomId = custRoomId;
    }
}
