/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsCommon._beans;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author lmeans
 */
@ManagedBean(name = "v2RptClientRmMateBeanList")
public class v2RptClientRmMateBeanList implements Serializable{
    private List<v2RptClientRmMateBean> list;
    private String desc;
    private String vendor;
    private String lookupDesc;
    private int custRmId;
   
    

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

    /**
     * @return the list
     */
    public List<v2RptClientRmMateBean> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(List<v2RptClientRmMateBean> list) {
        this.list = list;
    }

    
}
