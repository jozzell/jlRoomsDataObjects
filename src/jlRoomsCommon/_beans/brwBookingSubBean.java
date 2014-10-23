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
@ManagedBean(name = "brwBookingSubBean")
public class brwBookingSubBean implements Serializable {
private String category;
    private int blockId;
    private String vendorName;
    private String bookType;
    private double rmCost;
    private String comment;
    private double rmAllocated;
    private int ventorType;
    private int vendorId;
    private double roomCnt;

    public brwBookingSubBean(String category,int blockId,String vendorName,String bookType,double rmCost,
           String comment, double roomCnt,int ventorType,int vendorId,double rmAllocated) {

        this.category = category;
        this.blockId = blockId;
        this.vendorName = vendorName;
        this.bookType = bookType;
        this.rmCost = rmCost;
        this.comment = comment;
        this.roomCnt = roomCnt;
        this.ventorType = ventorType;
        this.vendorId = vendorId;
        this.rmAllocated = rmAllocated;

    }
    public brwBookingSubBean() {

    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setBlockId(int blockId) {
        this.blockId = blockId;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public void setRmCost(double rmCost) {
        this.rmCost = rmCost;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setRmAllocated(double rmAllocated) {
        this.rmAllocated = rmAllocated;
    }

    public void setVentorType(int ventorType) {
        this.ventorType = ventorType;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public void setRoomCnt(double roomCnt) {
        this.roomCnt = roomCnt;
    }

    public String getCategory() {
        return category;
    }

    public int getBlockId() {
        return blockId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public String getBookType() {
        return bookType;
    }

    public double getRmCost() {
        return rmCost;
    }

    public String getComment() {
        return comment;
    }

    public double getRmAllocated() {
        return rmAllocated;
    }

    public int getVentorType() {
        return ventorType;
    }

    public int getVendorId() {
        return vendorId;
    }

    public double getRoomCnt() {
        return roomCnt;
    }
}
