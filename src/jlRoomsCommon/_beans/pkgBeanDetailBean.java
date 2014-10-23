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
@ManagedBean(name = "pkgBeanDetailBean")
public class pkgBeanDetailBean implements Serializable{
private int custPkgDetailId,pkgDetailId,custId,pkgId,pkgType,pkgCat,sponsorId,vendorId,split,pkgNight;
    private double pkgAmt;
    private String pkgComment,pkgParm1,pkgParm2,pkgParm3,pkgAmtComment,pkgTypeDesc,pkgCatDesc;
    /**
     * @return the custPkgDetailId
     */
    public pkgBeanDetailBean(){
        
    }
    public int getCustPkgDetailId() {
        return custPkgDetailId;
    }

    /**
     * @param custPkgDetailId the custPkgDetailId to set
     */
    public void setCustPkgDetailId(int custPkgDetailId) {
        this.custPkgDetailId = custPkgDetailId;
    }

    /**
     * @return the pkgDetailId
     */
    public int getPkgDetailId() {
        return pkgDetailId;
    }

    /**
     * @param pkgDetailId the pkgDetailId to set
     */
    public void setPkgDetailId(int pkgDetailId) {
        this.pkgDetailId = pkgDetailId;
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
     * @return the pkgId
     */
    public int getPkgId() {
        return pkgId;
    }

    /**
     * @param pkgId the pkgId to set
     */
    public void setPkgId(int pkgId) {
        this.pkgId = pkgId;
    }

    /**
     * @return the pkgType
     */
    public int getPkgType() {
        return pkgType;
    }

    /**
     * @param pkgType the pkgType to set
     */
    public void setPkgType(int pkgType) {
        this.pkgType = pkgType;
    }

    /**
     * @return the pkgCat
     */
    public int getPkgCat() {
        return pkgCat;
    }

    /**
     * @param pkgCat the pkgCat to set
     */
    public void setPkgCat(int pkgCat) {
        this.pkgCat = pkgCat;
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
     * @return the pkgNight
     */
    public int getPkgNight() {
        return pkgNight;
    }

    /**
     * @param pkgNight the pkgNight to set
     */
    public void setPkgNight(int pkgNight) {
        this.pkgNight = pkgNight;
    }

    /**
     * @return the pkgComment
     */
    public String getPkgComment() {
        return pkgComment;
    }

    /**
     * @param pkgComment the pkgComment to set
     */
    public void setPkgComment(String pkgComment) {
        this.pkgComment = pkgComment;
    }

    /**
     * @return the pkgParm1
     */
    public String getPkgParm1() {
        return pkgParm1;
    }

    /**
     * @param pkgParm1 the pkgParm1 to set
     */
    public void setPkgParm1(String pkgParm1) {
        this.pkgParm1 = pkgParm1;
    }

    /**
     * @return the pkgParm2
     */
    public String getPkgParm2() {
        return pkgParm2;
    }

    /**
     * @param pkgParm2 the pkgParm2 to set
     */
    public void setPkgParm2(String pkgParm2) {
        this.pkgParm2 = pkgParm2;
    }

    /**
     * @return the pkgParm3
     */
    public String getPkgParm3() {
        return pkgParm3;
    }

    /**
     * @param pkgParm3 the pkgParm3 to set
     */
    public void setPkgParm3(String pkgParm3) {
        this.pkgParm3 = pkgParm3;
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
     * @return the pkgAmt
     */
    public double getPkgAmt() {
        return pkgAmt;
    }

    /**
     * @param pkgAmt the pkgAmt to set
     */
    public void setPkgAmt(double pkgAmt) {
        this.pkgAmt = pkgAmt;
    }

    /**
     * @return the split
     */
    public int getSplit() {
        return split;
    }

    /**
     * @param split the split to set
     */
    public void setSplit(int split) {
        this.split = split;
    }

    /**
     * @return the pkgAmtComment
     */
    public String getPkgAmtComment() {
        return pkgAmtComment;
    }

    /**
     * @param pkgAmtComment the pkgAmtComment to set
     */
    public void setPkgAmtComment(String pkgAmtComment) {
        this.pkgAmtComment = pkgAmtComment;
    }

    /**
     * @return the pkgTypeDesc
     */
    public String getPkgTypeDesc() {
        return pkgTypeDesc;
    }

    /**
     * @param pkgTypeDesc the pkgTypeDesc to set
     */
    public void setPkgTypeDesc(String pkgTypeDesc) {
        this.pkgTypeDesc = pkgTypeDesc;
    }

    /**
     * @return the pkgCatDesc
     */
    public String getPkgCatDesc() {
        return pkgCatDesc;
    }

    /**
     * @param pkgCatDesc the pkgCatDesc to set
     */
    public void setPkgCatDesc(String pkgCatDesc) {
        this.pkgCatDesc = pkgCatDesc;
    }
}
