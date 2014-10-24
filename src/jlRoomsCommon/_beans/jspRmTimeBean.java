package jlRoomsCommon._beans;
import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import obj.bean.time.jspTimeBean;

/**
 * Title:        Room Tracking Web Applicaion (rtwa)
 * Description:
 * Copyright:    Copyright (c) 2003
 * Company:      Jozzell, llc
 * @author Lloyd Means
 * @version 1.0
 */
@ManagedBean(name = "jspRmTimeBean")
public class jspRmTimeBean extends jspTimeBean implements Serializable{
 
  public jspRmTimeBean() {
  }
  public jspRmTimeBean(Date d, int i) {
      super(d,i);
  }
}