/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jlRoomsCommon._beans;
import java.io.*;
import java.util.*;
import javax.faces.bean.ManagedBean;
/**
 *
 * @author lmeans
 */
@ManagedBean(name = "eventBean")
public class eventBean implements Serializable{
private custRmBean custRmBean;
    private sponsorBean sponsorBean;
    private custBean custBean = new custBean();
    private Collection aryClient,aryRm,aryBooking,aryTmp;
    private int size = 0;
    private  final String
        SUCCESS = "success",
        JSP_EVENT = "/brwEvents/eventdisplay/event.faces",
        JSP_MAINTENANCE_CUSTOMER="/mantenance/customer.faces",
        JSP_MAINTENACCE_BRW_EVENTS="/brwEvents/brwevents.faces",
        JSP_EDIT_CUST_ROOM="/booking/custRm.faces",
        JSP_EDIT_FLIGHT = "/booking/custFlt.faces";
  private boolean showTab=false;

  public eventBean() {
      try {
        jbInit();
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
    /*
         ***********************************************************************
         ***********************************************************************
    */
   public void editBooking() {
     /*
     String jsp = JSP_EDIT_CUST_ROOM;
     if (jlRoomsFactory.debug()) System.out.println( "eventBean: String editBooking()");
     javax.faces.context.FacesContext context =  javax.faces.context.FacesContext.getCurrentInstance();
     javax.faces.application.Application app = context.getApplication();
     //Map requestMap = context.getExternalContext().getRequestMap();
     this.setCustRmBean((custRmBean)   app.createValueBinding("#{custRmBean}").getValue(context));
     lookupObj  lookupObj = new lookupObj();

     //lookupObj.getSelectedItems(5,this.getSponsorBean().getRegion());

     //---this.getCustRmBean().setItems(lookupObj.getSelectedItems(5,this.getSponsorBean().getRegion()));
     //custRmBean custRmBean = (custRmBean) requestMap.get("custRmBean");
     //if (custRmBean == null) custRmBean = new custRmBean();

     //custRmBean = (custRmBean)   app.createValueBinding("#{custRmBean}").getValue(context);
     if (jlRoomsFactory.debug()) System.out.println("1) "+custRmBean.getBlockId());
     if (jlRoomsFactory.debug()) System.out.println("2) "+custRmBean.getVendor());
     if (jlRoomsFactory.debug()) System.out.println("3) "+custRmBean.getCustRoomType());
     switch(custRmBean.getCustRoomType()) {
       case -6:
         flightObj obj = new flightObj();
         this.setAryTmp(obj.getFligthInfo(custRmBean.getCustRoomId(),0));
         System.out.print(aryTmp.size());
         Object[] x = aryTmp.toArray();
         for(int i = 0; i < aryTmp.size(); i++) {
           flightInfoBean bean  =(flightInfoBean)x[i];
           System.out.println(bean.getFltArrivalDate()+bean.getFltArrivalPlace());
         }
         jsp = this.JSP_EDIT_FLIGHT;
         break;
        default:
         break;
     }

     UIViewRoot view = app.getViewHandler().createView(context, jsp);
     context.setViewRoot(view);
     context.renderResponse();



     return SUCCESS;
*/
   }
   /*
        ***********************************************************************
        ***********************************************************************
   */
   public void browseEvents() {

     /*
     if (jlRoomsFactory.debug()) System.out.println( "eventBean: String browseEvents()");
     javax.faces.context.FacesContext context =  javax.faces.context.FacesContext.getCurrentInstance();
     javax.faces.application.Application app = context.getApplication();
     UIViewRoot view = app.getViewHandler().createView(context, JSP_MAINTENACCE_BRW_EVENTS);
     context.setViewRoot(view);
     context.renderResponse();

     return SUCCESS;
*/
   }
    /*
         ***********************************************************************
         ***********************************************************************
    */
   public void editCustomer() {
     /*
     if (jlRoomsFactory.debug()) System.out.println( "eventBean: String editCustomer()");
     javax.faces.context.FacesContext context =  javax.faces.context.FacesContext.getCurrentInstance();
     javax.faces.application.Application app = context.getApplication();
     //Map requestMap = context.getExternalContext().getRequestMap();

     //if (jlRoomsFactory.debug()) System.out.println("eventBean: String clientPicked() "+this.getCustBean().getLastName());
     //this.setCustBean((custBean)   app.createValueBinding("#{custBean}").getValue(context));
     //setBlock((blockBean) requestMap.get("blockBean"),requestMap);

     UIViewRoot view = app.getViewHandler().createView(context, JSP_MAINTENANCE_CUSTOMER);
     context.setViewRoot(view);
     context.renderResponse();

     return SUCCESS; */
   }
   /*
        ***********************************************************************
        ***********************************************************************
   */

    public void clientPicked() {/*
        if (jdlRoomsFactory.debug()) System.out.println( "eventBean: String clientPicked()");
        javax.faces.context.FacesContext context =  javax.faces.context.FacesContext.getCurrentInstance();
        javax.faces.application.Application app = context.getApplication();
        Map requestMap = context.getExternalContext().getRequestMap();

        if (jlRoomsFactory.debug()) System.out.println("eventBean: String clientPicked() "+this.getCustBean().getLastName());
        this.setCustBean((custBean)   app.createValueBinding("#{custBean}").getValue(context));
        setBlock(requestMap);
        this.setCustRmBean(
            this.getCustBean().getCustId(),
            this.sponsorBean.getSponsorId(),
            jlRoomsFactory.SponorLinkId);
        UIViewRoot view = app.getViewHandler().createView(context, JSP_EVENT);
        context.setViewRoot(view);
        context.renderResponse();
        return SUCCESS; */
    }
    /*
         ***********************************************************************
         ***********************************************************************
    */
   
    /*
         ***********************************************************************
         ***********************************************************************
    */
    public void displayCustSearch() { /*
      if (jlRoomsFactory.debug()) System.out.println( "eventBean: String doAction()");


     javax.faces.context.FacesContext context =  javax.faces.context.FacesContext.getCurrentInstance();
     javax.faces.application.Application app = context.getApplication();
     searchBean cBean = (searchBean)   app.createValueBinding("#{searchBean}").getValue(context);
     if (jlRoomsFactory.debug()) System.out.println("eventBean: String doAction() "+cBean.getText());

     Map requestMap = context.getExternalContext().getRequestMap();
     custBean cust = (custBean) requestMap.get("custBean");
     this.setCustBean((custBean) requestMap.get("custBean"),requestMap,cBean.getText());

     UIViewRoot view = app.getViewHandler().createView(context, "/brwEvents/seek/seekCustomer.faces");
     context.setViewRoot(view);

     context.renderResponse();

     return SUCCESS; */
 }

 /*
      ***********************************************************************
      ***********************************************************************
 */
    public void displayEvents() { /*
        if (jlRoomsFactory.debug()) System.out.println( "eventBean: String doAction()");

        javax.faces.context.FacesContext context =  javax.faces.context.FacesContext.getCurrentInstance();
        javax.faces.application.Application app = context.getApplication();
        Map requestMap = context.getExternalContext().getRequestMap();
        this.setSponsorBean((sponsorBean)   app.createValueBinding("#{sponsorBean}").getValue(context));
        if (jlRoomsFactory.debug()) System.out.println(this.sponsorBean.getSponsorDesc());
        setBlock(requestMap);

        //--- this works

        //--- brwEvents/eventdisplay/
        UIViewRoot view = app.getViewHandler().createView(context, JSP_EVENT);
        context.setViewRoot(view);
        context.renderResponse();

        //context.getServletContext().getRequestDispatcher().forward("second page");
        //countryDetails = cBean.getCountryDetails();

        return SUCCESS; */
    }

    /*
         ***********************************************************************
         ***********************************************************************
    */
   
    /*
         ***********************************************************************
         ***********************************************************************
    */
   private void readObject(ObjectInputStream ois) throws IOException,
       ClassNotFoundException {
     ois.defaultReadObject();
   }

   private void writeObject(ObjectOutputStream oos) throws IOException {
     oos.defaultWriteObject();
   }

   

   public sponsorBean getSponsorBean() {
     return sponsorBean;
   }

   public custBean getCustBean() {
     return custBean;
   }

  public boolean isShowTab() {
    return showTab;
  }

  

   public void setSponsorBean(sponsorBean sponsorBean) {
     this.sponsorBean = sponsorBean;
   }

   public void setCustBean(custBean custBean) {
     this.custBean = custBean;
   }

  public void setShowTab(boolean showTab) {
    this.showTab = showTab;
  }

  public void setAryBooking(Collection aryBooking) {
    this.aryBooking = aryBooking;
  }

  public void setCustRmBean(custRmBean custRmBean) {
    this.custRmBean = custRmBean;
  }

  public void setAryTmp(Collection aryTmp) {
    this.aryTmp = aryTmp;
  }

  private void jbInit() throws Exception {
   }
  public Collection getAryClient() {

     return aryClient;
   }

   public Collection getAryRm() {

     return aryRm;
   }
   public Collection getAryBooking() {
    return aryBooking;
  }

  public custRmBean getCustRmBean() {
    return custRmBean;
  }

  public Collection getAryTmp() {
    return aryTmp;
  }
   public void setAryClient(Collection aryClient) {
     this.aryClient = aryClient;
   }

   public void setAryRm(Collection ary) {
     showTab = (ary.size() > 0 ? true : false);
     this.aryRm = ary;
   }
}
