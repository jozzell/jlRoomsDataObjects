/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jlRoomsCommon._objects;
import sun.jdbc.rowset.*;
import obj.db.v1.dbMgrInterface;


/**
 *
 * @author lmeans
 */
public interface jlRoomsDbConnIinterface extends dbMgrInterface{
    
    public String getDollarFormat(double d);
    public int getLoginId() ;
    public String getAirTextSub(int type);
    
    

}
