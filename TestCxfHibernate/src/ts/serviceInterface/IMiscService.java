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
	 * 靳振良
	 * @param cid
	 * @return
	 */
	@GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getTransHistoryList/{packageid}") 
	public List<TransHistory> getTransHistoryList(@PathParam("packageid")String packageID);

	/*
	 * 迟娜写接口
	 * getExpressSheet（不修改）
	 * getMydispatchExpressSheet/cid(自己添加)
	 * getMyReceiveExpressSheet/cid(自己添加)
	 * */
	//nder客户查询本人的寄件
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getMyReceiveExpressSheet/{cid}") 
   	public List<ExpressSheet> getMyReceiveExpressSheet(@PathParam("cid")Integer cid);

    
    //receive客户查询本人的收件
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getMySentExpressSheet/{cid}") 
   	public List<ExpressSheet> getMySentExpressSheet(@PathParam("cid")Integer cid);
	
    
    /**
     * 王茜
     * @param app
     * @return
     */
	//创建预约单并保存
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("saveAppointment") 
	public Response saveAppointment(Appointment app);
	
  //修改预约单信息
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/updateAppointment") 
	public Response updateAppointment(Appointment app);
	
  //通过预约单号删除预约单(规定快件状态后可用数据库的触发器删除)
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/deleteAppointment/{id}") 
	public Response deleteAppointment(@PathParam("id")int id);
    
  //通过客户获取预约单列表
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getAppointmentListByClient/{cid}") 
	public List<Appointment> AppointmentListByClient(@PathParam("cid")Integer cid);

	
	//通过NodeCode网点码来获取某个网点,ID
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getNode/{NodeCode}") 
	public TransNode getNode(@PathParam("NodeCode")String code);
    
    //通过regionCode和type来获取某一地点的某类网点，其实是根据regionCode获取了region集合，数据库中type都是0
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getNodesList/{RegionCode}/{Type}") 
	public List<TransNode> getNodesList(@PathParam("RegionCode")String regionCode, @PathParam("Type")int type);

    //===============================================================================================
    //通过name获取客户列表（模糊查询？）
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getCustomerListByName/{name}") 
	public List<CustomerInfo> getCustomerListByName(@PathParam("name")String name);
    
    //通过TelCode获取客户列表（模糊查询？）
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getCustomerListByTelCode/{TelCode}") 
	public List<CustomerInfo> getCustomerListByTelCode(@PathParam("TelCode")String TelCode);
    
    //通过id获取客户
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getCustomerInfo/{id}") 
	public Response getCustomerInfo(@PathParam("id")String id);
    
    //通过id删除客户
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/deleteCustomerInfo/{id}") 
	public Response deleteCustomerInfo(@PathParam("id")int id);
    
    //保存客户信息
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/saveCustomerInfo") 
	public Response saveCustomerInfo(CustomerInfo obj);
    
    //===============================================================================================
    //获取省列表s
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getProvinceList") 
	public List<CodeNamePair> getProvinceList();
    
    //获取城市列表
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getCityList/{prv}") 
	public List<CodeNamePair> getCityList(@PathParam("prv")String prv);
    
    //获取城镇列表
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getTownList/{city}") 
	public List<CodeNamePair> getTownList(@PathParam("city")String city);
    
    //通过id获取地域的String
    @GET
    @Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
    @Path("/getRegionString/{id}") 
	public String getRegionString(@PathParam("id")String id);
    
    //通过id获取地域对象
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/getRegion/{id}") 
	public Region getRegion(@PathParam("id")String id);
    
    //===============================================================================================
    //创建工作Session
	public void CreateWorkSession(int uid);
    
	//登录（使用用户id和密码）
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/doLogin/{uid}/{pwd}") 
	public boolean doLogin(@PathParam("uid") int uid, @PathParam("pwd") String pwd);
    
    //退出（使用用户id）
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/doLogOut/{uid}") 
	public void doLogOut(@PathParam("uid") int uid);

	public void RefreshSessionList();
}
