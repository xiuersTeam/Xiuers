package ts.serviceInterface;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ts.model.Appointment;
import ts.model.CodeNamePair;
import ts.model.CustomerInfo;
import ts.model.ExpressSheet;
import ts.model.Region;
import ts.model.TransHistory;
import ts.model.TransNode;

@Path("/Misc")
public interface IMiscService {

	/*
	 * ������
	 * @param cid
	 * @return
	 */
	@GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getTransHistoryList/{packageid}") 
	public List<TransHistory> getTransHistoryList(@PathParam("packageid")String packageID);

	/*
	 * ����д�ӿ�
	 * getExpressSheet�����޸ģ�
	 * getMydispatchExpressSheet/cid(�Լ����)
	 * getMyReceiveExpressSheet/cid(�Լ����)
	 * */
	//nder�ͻ���ѯ���˵ļļ�
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getMyReceiveExpressSheet/{cid}") 
   	public List<ExpressSheet> getMyReceiveExpressSheet(@PathParam("cid")Integer cid);

    
    //receive�ͻ���ѯ���˵��ռ�
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getMySentExpressSheet/{cid}") 
   	public List<ExpressSheet> getMySentExpressSheet(@PathParam("cid")Integer cid);
	
    
    /**
     * ����
     * @param app
     * @return
     */
	//����ԤԼ��������
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("saveAppointment") 
	public Response saveAppointment(Appointment app);
	
  //�޸�ԤԼ����Ϣ
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/updateAppointment") 
	public Response updateAppointment(Appointment app);
	
  //ͨ��ԤԼ����ɾ��ԤԼ��(�涨���״̬��������ݿ�Ĵ�����ɾ��)
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/deleteAppointment/{id}") 
	public Response deleteAppointment(@PathParam("id")int id);
    
  //ͨ���ͻ���ȡԤԼ���б�
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getAppointmentListByClient/{cid}") 
	public List<Appointment> AppointmentListByClient(@PathParam("cid")Integer cid);

	
	//ͨ��NodeCode����������ȡĳ������,ID
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getNode/{NodeCode}") 
	public TransNode getNode(@PathParam("NodeCode")String code);
    
    //ͨ��regionCode��type����ȡĳһ�ص��ĳ�����㣬��ʵ�Ǹ���regionCode��ȡ��region���ϣ����ݿ���type����0
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getNodesList/{RegionCode}/{Type}") 
	public List<TransNode> getNodesList(@PathParam("RegionCode")String regionCode, @PathParam("Type")int type);

    //===============================================================================================
    //ͨ��name��ȡ�ͻ��б�ģ����ѯ����
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getCustomerListByName/{name}") 
	public List<CustomerInfo> getCustomerListByName(@PathParam("name")String name);
    
    //ͨ��TelCode��ȡ�ͻ��б�ģ����ѯ����
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getCustomerListByTelCode/{TelCode}") 
	public List<CustomerInfo> getCustomerListByTelCode(@PathParam("TelCode")String TelCode);
    
    //ͨ��id��ȡ�ͻ�
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getCustomerInfo/{id}") 
	public Response getCustomerInfo(@PathParam("id")String id);
    
    //ͨ��idɾ���ͻ�
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/deleteCustomerInfo/{id}") 
	public Response deleteCustomerInfo(@PathParam("id")int id);
    
    //����ͻ���Ϣ
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/saveCustomerInfo") 
	public Response saveCustomerInfo(CustomerInfo obj);
    
    //===============================================================================================
    //��ȡʡ�б�s
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getProvinceList") 
	public List<CodeNamePair> getProvinceList();
    
    //��ȡ�����б�
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getCityList/{prv}") 
	public List<CodeNamePair> getCityList(@PathParam("prv")String prv);
    
    //��ȡ�����б�
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getTownList/{city}") 
	public List<CodeNamePair> getTownList(@PathParam("city")String city);
    
    //ͨ��id��ȡ�����String
    @GET
    @Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
    @Path("/getRegionString/{id}") 
	public String getRegionString(@PathParam("id")String id);
    
    //ͨ��id��ȡ�������
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getRegion/{id}") 
	public Region getRegion(@PathParam("id")String id);
    
    //===============================================================================================
    //��������Session
	public void CreateWorkSession(int uid);
    
	//��¼��ʹ���û�id�����룩
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/doLogin/{uid}/{pwd}") 
	public boolean doLogin(@PathParam("uid") int uid, @PathParam("pwd") String pwd);
    
    //�˳���ʹ���û�id��
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/doLogOut/{uid}") 
	public void doLogOut(@PathParam("uid") int uid);

	public void RefreshSessionList();
}
