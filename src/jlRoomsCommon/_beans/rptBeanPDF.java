/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsCommon._beans;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author lmeans
 */
@ManagedBean(name = "rptBeanPDF")
public class rptBeanPDF implements Serializable{
    private List<String> selectedRpts;  
    private rptBeanPdfInterface rptBeanPdfInterface;
    private String emailKey,seekStr;
    private String fileName="/jlRoomsWebApp/sample.pdf";
    private int rptType;
    private int sponId;
    private int custId;
    private int rptPage;
    private int vendorId;
    private Date startDt=Calendar.getInstance().getTime();
    private Date endDate;

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
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the rptType
     */
    public int getRptType() {
        return rptType;
    }

    /**
     * @param rptType the rptType to set
     */
    public void setRptType(int rptType) {
        this.rptType = rptType;
    }

    /**
     * @return the sponId
     */
    public int getSponId() {
        return sponId;
    }

    /**
     * @param sponId the sponId to set
     */
    public void setSponId(int sponId) {
        this.sponId = sponId;
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
     * @return the startDt
     */
    public Date getStartDt() {
        return startDt;
    }

    /**
     * @param startDt the startDt to set
     */
    public void setStartDt(Date startDt) {
        this.startDt = startDt;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        if (endDate == null) endDate = this.startDt;
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the seekStr
     */
    public String getSeekStr() {
        return seekStr;
    }

    /**
     * @param seekStr the seekStr to set
     */
    public void setSeekStr(String seekStr) {
        this.seekStr = seekStr;
    }

    /**
     * @return the selectedRpts
     */
    public List<String> getSelectedRpts() {
        return selectedRpts;
    }

    /**
     * @param selectedRpts the selectedRpts to set
     */
    public void setSelectedRpts(List<String> selectedRpts) {
        this.selectedRpts = selectedRpts;
    }

    /**
     * @return the rptPage
     */
    public int getRptPage() {
        return rptPage;
    }

    /**
     * @param rptPage the rptPage to set
     */
    public void setRptPage(int rptPage) {
        this.rptPage = rptPage;
    }
}
