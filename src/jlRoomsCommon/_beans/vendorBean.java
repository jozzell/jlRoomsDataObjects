/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsCommon._beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import jlRoomsCommon._jlroot.serialsBean;

/**
 *
 * @author lmeans
 */
@ManagedBean(name = "vendorBean")
public class vendorBean implements Serializable {

    private serialsBean serialsBean;
    private String vendor_name, ADDR1, ADDR2, CITY, STATE, ZIP, EMAIL, ZIP_PLUS;
    private String phone, fax, ext, vendor_code;
    private String rptDesc;
    private int region, vendor_id, flag_ind, vendor_type, vendorInd, vendorRollupID,dbId ;
    private String keyStr, emailKey;
    private int paypalType;

    public vendorBean() {
    }

    public int getID() {
        return this.vendor_id;
    }

    public void setPhoneExt(String s) {
        ext = s;
    }

    public String getPhoneExt() {
        return ext;
    }

    public int getVendorInd() {
        return vendorInd;
    }

    public void setVendorInd(int vendorInd) {
        this.vendorInd = vendorInd;
    }

    public String getVendor_code() {
        return vendor_code;
    }

    public void setVendor_code(String vendor_code) {
        this.vendor_code = vendor_code;
    }

    public void setRegion(int i) {
        region = i;
    }

    public int getRegion() {
        return region;
    }
    /*
     ******************************************
     ******************************************
     */

    public void setVendorId(int i) {
        vendor_id = i;
    }

    public int getVendorId() {
        return vendor_id;
    }
    /*
     ******************************************
     ******************************************
     */

    public void setFlag(int i) {
        flag_ind = i;
    }

    public int getFlag() {
        return flag_ind;
    }
    /*
     ******************************************
     ******************************************
     */

    public void setVendorName(String s) {
        vendor_name = s;
    }

    public String getVendorName() {
        return vendor_name;
    }
    /*
     ******************************************
     ******************************************
     */

    public void setVendorType(int i) {
        vendor_type = i;
    }

    public int getVendorType() {
        return vendor_type;
    }
    /*
     ******************************************
     ******************************************
     */

    public void setAddr(String s) {
        ADDR1 = s;
    }

    public String getAddr() {
        return ADDR1;
    }
    /*
     ******************************************
     ******************************************
     */

    public void setAddr2nd(String s) {
        ADDR2 = s;
    }

    public String getAddr2nd() {
        return ADDR2;
    }
    /*
     ******************************************
     ******************************************
     */

    public void setCity(String s) {
        CITY = s;
    }

    public String getCity() {
        return CITY;
    }
    /*
     ******************************************
     ******************************************
     */

    public void setState(String s) {
        STATE = s;
    }

    public String getState() {
        return STATE;
    }
    /*
     ******************************************
     ******************************************
     */

    public void setZip(String s) {
        ZIP = s;
    }

    public String getZip() {
        return ZIP;
    }

    /*
     ******************************************
     ******************************************
     */
    public void setZipPlus(String s) {
        ZIP_PLUS = s;
    }

    public String getZipPlus() {
        return ZIP_PLUS;
    }
    /*
     ******************************************
     ******************************************
     */

    public void setEMail(String s) {
        EMAIL = s;
    }

    public String getEMail() {
        return EMAIL;
    }
    /*
     ******************************************
     ******************************************
     */

    public void setPhone(String s) {
        phone = s;
    }

    public String getPhone() {
        return phone;
    }
    /*
     ******************************************
     ******************************************
     */

    public void setFax(String s) {
        fax = s;
    }

    public String getFax() {
        return fax;
    }

    public String getRptDisplay() {
        return getVendorName() + getDisplay();
    }

    public String getHeaderDisplay() {
        return getVendorName();
    }
    /*
     ******************************************
     ******************************************
     */

    public String getDisplayPhone() {
        String s = "";
        if (this.phone != null) {
            s += this.phone + " ";
        }
        if (this.ext != null && this.ext.trim().length() > 0) {
            s += " Ext: " + this.ext + " ";
        }
        if (this.fax != null && this.fax.trim().length() > 0) {
            s += " Fax: " + this.fax;
        }
        if (s.trim().length() == 0) {
            return null;
        }
        return s.trim();
    }

    public String getDisplayTown() {
        String s = "";
        if (this.CITY != null) {
            s += this.CITY + " ";
        }
        if (this.STATE != null) {
            s += this.STATE + " ";
        }
        if (this.ZIP != null) {
            s += this.ZIP + " ";
        }
        if (this.ZIP_PLUS != null) {
            s += this.ZIP_PLUS;
        }
        if (s.trim().length() == 0) {
            return null;
        }
        return s.trim();
    }

    public String getDisplayAddress() {
        if (this.ADDR1 == null && this.ADDR2 == null) {
            return null;
        }
        String s = "";
        if (this.ADDR1 != null) {
            s += this.ADDR1 + " ";
        }
        if (this.ADDR2 != null) {
            s += this.ADDR2;
        }

        if (s.trim().length() == 0) {
            return null;
        }
        return s.trim();
    }

    public String getDisplay() {
        return (this.ADDR1 == null ? "" : " @ " + this.ADDR1)
                + (this.CITY == null ? "" : " " + this.CITY);
    }

    /**
     * @return the rptDesc
     */
    public String getRptDesc() {
        return rptDesc;
    }

    /**
     * @param rptDesc the rptDesc to set
     */
    public void setRptDesc(String rptDesc) {
        this.rptDesc = rptDesc;
    }

    /**
     * @return the vendor_name
     */
    public String getVendor_name() {
        return vendor_name;
    }

    /**
     * @param vendor_name the vendor_name to set
     */
    public void setVendor_name(String s) {
        this.vendor_name = s;
    }

    /**
     * @return the ADDR1
     */
    public String getADDR1() {
        return ADDR1;
    }

    /**
     * @param ADDR1 the ADDR1 to set
     */
    public void setADDR1(String ADDR1) {
        this.ADDR1 = ADDR1;
    }

    /**
     * @return the ADDR2
     */
    public String getADDR2() {
        return ADDR2;
    }

    /**
     * @param ADDR2 the ADDR2 to set
     */
    public void setADDR2(String ADDR2) {
        this.ADDR2 = ADDR2;
    }

    /**
     * @return the CITY
     */
    public String getCITY() {
        return CITY;
    }

    /**
     * @param CITY the CITY to set
     */
    public void setCITY(String CITY) {
        this.CITY = CITY;
    }

    /**
     * @return the STATE
     */
    public String getSTATE() {
        return STATE;
    }

    /**
     * @param STATE the STATE to set
     */
    public void setSTATE(String STATE) {
        this.STATE = STATE;
    }

    /**
     * @return the ZIP
     */
    public String getZIP() {
        return ZIP;
    }

    /**
     * @param ZIP the ZIP to set
     */
    public void setZIP(String ZIP) {
        this.ZIP = ZIP;
    }

    /**
     * @return the EMAIL
     */
    public String getEMAIL() {
        return EMAIL;
    }

    /**
     * @param EMAIL the EMAIL to set
     */
    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    /**
     * @return the ZIP_PLUS
     */
    public String getZIP_PLUS() {
        return ZIP_PLUS;
    }

    /**
     * @param ZIP_PLUS the ZIP_PLUS to set
     */
    public void setZIP_PLUS(String ZIP_PLUS) {
        this.ZIP_PLUS = ZIP_PLUS;
    }

    /**
     * @return the ext
     */
    public String getExt() {
        return ext;
    }

    /**
     * @param ext the ext to set
     */
    public void setExt(String ext) {
        this.ext = ext;
    }

    /**
     * @return the vendor_id
     */
    public int getVendor_id() {
        return vendor_id;
    }

    /**
     * @param vendor_id the vendor_id to set
     */
    public void setVendor_id(int vendor_id) {
        this.vendor_id = vendor_id;
    }

    /**
     * @return the flag_ind
     */
    public int getFlag_ind() {
        return flag_ind;
    }

    /**
     * @param flag_ind the flag_ind to set
     */
    public void setFlag_ind(int flag_ind) {
        this.flag_ind = flag_ind;
    }

    /**
     * @return the vendor_type
     */
    public int getVendor_type() {
        return vendor_type;
    }

    /**
     * @param vendor_type the vendor_type to set
     */
    public void setVendor_type(int vendor_type) {
        this.vendor_type = vendor_type;
    }

    /**
     * @return the vendorRollupID
     */
    public int getVendorRollupID() {
        return vendorRollupID;
    }

    /**
     * @param vendorRollupID the vendorRollupID to set
     */
    public void setVendorRollupID(int vendorRollupID) {
        this.vendorRollupID = vendorRollupID;
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
     * @return the paypalType
     */
    public int getPaypalType() {
        return paypalType;
    }

    /**
     * @param paypalType the paypalType to set
     */
    public void setPaypalType(int paypalType) {
        this.paypalType = paypalType;
    }

    /**
     * @return the serialsBean
     */
    public serialsBean getSerialsBean() {
        return serialsBean;
    }

    /**
     * @param serialsBean the serialsBean to set
     */
    public void setSerialsBean(serialsBean serialsBean) {
        this.serialsBean = serialsBean;
    }

    /**
     * @return the dbId
     */
    public int getDbId() {
        return dbId;
    }

    /**
     * @param dbId the dbId to set
     */
    public void setDbId(int dbId) {
        this.dbId = dbId;
    }
}
