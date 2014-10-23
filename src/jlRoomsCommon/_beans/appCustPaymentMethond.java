/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsCommon._beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;

/**
 * assistant
 * @author lmeans
 */
@ManagedBean(name = "appCustPaymentMethond")
public class appCustPaymentMethond implements Serializable{
     public static final int
            JLROOMS_TRYBUY=-1,
            JLROOMS_EVENT=1,
            JLROOMS_UNLIMITED=2;
    private String  
            _DISABLED_BROWSE_EVENT_PRINT = "true",
            _DISABLED_BROWSE_EVENT_NEXT = "true",
            _DISABLED_BROWSE_EVENT_ADD= "true";
    private int cntr_id=0;
    private int cntr_type=JLROOMS_TRYBUY;
    private int cntr=3;
    private String date="";
    //private int _JLROOMS_PAYMENT_TYPE=JLROOMS_TRYBUY;
    public appCustPaymentMethond(){
        
    }
    public appCustPaymentMethond(int type){
        cntr_type = type;
    }
    public String disableEditButton(){
        if (cntr_id == 0) return "true";
        return "false";
    }
    public String getExpDate(String siteId){
        
        switch (cntr_type){
            case JLROOMS_TRYBUY:
                if (cntr_id > 0)  {
                    return "Site ID:"+siteId+" Expire: "+date;
                } else {
                    return "Working demo";
                }
            case JLROOMS_UNLIMITED:
                if (cntr <= 45) return "Site ID:"+siteId+" Expire: "+date;
                break;
            
        }
      return "Site ID: "+siteId;
    }
    public void setEnableBrwEventAddFlag(int rowCnt){
         switch(cntr_type){
             case JLROOMS_TRYBUY:
                 _DISABLED_BROWSE_EVENT_PRINT = "true";
                 if (rowCnt >= 1){
                     _DISABLED_BROWSE_EVENT_ADD = "true";
                     if (rowCnt > 1 || this.cntr < 0) {
                         _DISABLED_BROWSE_EVENT_NEXT = "true";
                     } else {
                         _DISABLED_BROWSE_EVENT_NEXT = "false";
                     }
                 } else {
                     _DISABLED_BROWSE_EVENT_ADD = "false";
                     _DISABLED_BROWSE_EVENT_NEXT = "false";
                 }
                 break;
             case JLROOMS_UNLIMITED:
                
                 if (this.cntr < 0){
                    _DISABLED_BROWSE_EVENT_ADD = "true";
                    _DISABLED_BROWSE_EVENT_NEXT = "false";
                    _DISABLED_BROWSE_EVENT_PRINT = "true";
                 } else {
                    _DISABLED_BROWSE_EVENT_ADD = "false";
                    _DISABLED_BROWSE_EVENT_NEXT = "false";
                    _DISABLED_BROWSE_EVENT_PRINT = "false";
                 }
                 
                 break;
             default:
                 _DISABLED_BROWSE_EVENT_ADD = "true";
                 _DISABLED_BROWSE_EVENT_NEXT = "false";
                 _DISABLED_BROWSE_EVENT_PRINT = "false";
                 break;
             
         }
    }
    /**
     * @return the cntr_id
     */
    public int getCntr_id() {
        return cntr_id;
    }

    /**
     * @param cntr_id the cntr_id to set
     */
    public void setCntr_id(int cntr_id) {
        this.cntr_id = cntr_id;
    }

    /**
     * @return the cntr_type
     */
    public int getCntr_type() {
        return cntr_type;
    }

    /**
     * @param cntr_type the cntr_type to set
     */
    public void setCntr_type(int cntr_type) {
        this.cntr_type = cntr_type;
    }

    /**
     * @return the cntr
     */
    public int getCntr() {
        return cntr;
    }

    /**
     * @param cntr the cntr to set
     */
    public void setCntr(int cntr) {
        this.cntr = cntr;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the _DISABLED_BROWSE_EVENT_PRINT
     */
    public String getDISABLED_BROWSE_EVENT_PRINT() {
        return _DISABLED_BROWSE_EVENT_PRINT;
    }

    /**
     * @return the _DISABLED_BROWSE_EVENT_NEXT
     */
    public String getDISABLED_BROWSE_EVENT_NEXT() {
        return _DISABLED_BROWSE_EVENT_NEXT;
    }

    /**
     * @return the _DISABLED_BROWSE_EVENT_ADD
     */
    public String getDISABLED_BROWSE_EVENT_ADD() {
        return _DISABLED_BROWSE_EVENT_ADD;
    }

    /**
     * @return the _JLROOMS_PAYMENT_TYPE
     */
    public int getJLROOMS_PAYMENT_TYPE() {
        return cntr_type;
    }

    /**
     * @return the _ENABLED_BROWSE_EVENT_PRINT
     */
    
    
}
