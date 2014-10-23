/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlRoomsCommon._jlroot;

import java.io.Serializable;

/**
 *
 * @author lmeans
 */
public class serialsBean implements Serializable {
    private int id;
    private String key,emailKey;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
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
}
