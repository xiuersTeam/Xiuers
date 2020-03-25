package ts.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import ts.daoImpl.AppointmentDao;
import ts.daoImpl.ClientInfoDao;
import ts.daoImpl.CustomerInfoDao;
import ts.daoImpl.ExpressSheetDao;
import ts.daoImpl.RegionDao;
import ts.daoImpl.TransHistoryDao;
import ts.daoImpl.TransNodeDao;
import ts.model.Appointment;
import ts.model.CodeNamePair;
import ts.model.CustomerInfo;
import ts.model.ExpressSheet;
import ts.model.Region;
import ts.model.TransHistory;
import ts.model.TransNode;
import ts.serviceInterface.IMiscService;

public class MiscService implements IMiscService{
	//TransNodeCatalog nodes;	//自己做的缓存和重定向先不要了,用Hibernate缓存对付一下，以后加上去
	//RegionCatalog regions;
	private TransNodeDao transNodeDao;
	private RegionDao regionDao;
	private CustomerInfoDao customerInfoDao;
	private ExpressSheetDao expressSheetDao;
	private AppointmentDao appointmentDao;
	private TransHistoryDao transHistoryDao;
	private ClientInfoDao clientInfoDao;
	public ExpressSheetDao getExpressSheetDao() {
		return expressSheetDao;
	}

	public void setExpressSheetDao(ExpressSheetDao expressSheetDao) {
		this.expressSheetDao = expressSheetDao;
	}

	public TransHistoryDao getTransHistoryDao() {
		return transHistoryDao;
	}

	public void setTransHistoryDao(TransHistoryDao transHistoryDao) {
		this.transHistoryDao = transHistoryDao;
	}

	public ClientInfoDao getClientInfoDao() {
		return clientInfoDao;
	}

	public void setClientInfoDao(ClientInfoDao clientInfoDao) {
		this.clientInfoDao = clientInfoDao;
	}

	public AppointmentDao getAppointmentDao() {
		return appointmentDao;
	}

	public void setAppointmentDao(AppointmentDao appointmentDao) {
		this.appointmentDao = appointmentDao;
	}

/**
 * 创建一个预约单
 */
	@Override
	public Response saveAppointment(Appointment app) {
		try{
			appointmentDao.save(app);			
			return Response.ok(app).header("EntityClass", "R_Appointment").build(); 
		}
		catch(Exception e)
		{
			return Response.serverError().entity(e.getMessage()).build(); 
		}
	}

	//recever
		@Override
		public List<ExpressSheet> getMyReceiveExpressSheet(Integer value) {
			List<ExpressSheet> list = new ArrayList<ExpressSheet>();
			list = expressSheetDao.getReceverListExpressSheet(value);
			return list;
		}
		
		//nder
		@Override
		public List<ExpressSheet> getMySentExpressSheet(Integer value) {
			List<ExpressSheet> list = new ArrayList<ExpressSheet>();
			list = expressSheetDao.getSentListExpressSheet(value);
			return list;
		}
	
	
	@Override
	public Response updateAppointment(Appointment app) {
		try{
			appointmentDao.update(app);			
			return Response.ok(app).header("EntityClass", "U_Appointment").build(); 
		}
		catch(Exception e)
		{
			return Response.serverError().entity(e.getMessage()).build(); 
		}
	}

	@Override
	public Response deleteAppointment(int id) {
		appointmentDao.removeById(id);
		return Response.ok("Deleted").header("EntityClass", "D_Appointment").build(); 
	}

	@Override
	public List<Appointment> AppointmentListByClient(Integer cid) {
		List<Appointment> list = new ArrayList<>();
		list = appointmentDao.getAppointmentByCId(cid);
		return list;	
	}

	
	public TransNodeDao getTransNodeDao() {
		return transNodeDao;
	}

	public void setTransNodeDao(TransNodeDao dao) {
		this.transNodeDao = dao;
	}

	public RegionDao getRegionDao() {
		return regionDao;
	}

	public void setRegionDao(RegionDao dao) {
		this.regionDao = dao;
	}

	public CustomerInfoDao getCustomerInfoDao() {
		return customerInfoDao;
	}

	public void setCustomerInfoDao(CustomerInfoDao dao) {
		this.customerInfoDao = dao;
	}

	public MiscService(){
//		nodes = new TransNodeCatalog();
//		nodes.Load();
//		regions = new RegionCatalog();
//		regions.Load();
	}

	@Override
	public TransNode getNode(String code) {
		// TODO Auto-generated method stub
		return transNodeDao.findByCode(code);
	}

	@Override
	public List<TransNode> getNodesList(String regionCode, int type) {
		// TODO Auto-generated method stub
		return transNodeDao.findByRegionCode(regionCode);
	}

	@Override
	public List<CustomerInfo> getCustomerListByName(String name) {
//		List<CustomerInfo> listci = customerInfoDao.findByName(name);
//		List<CodeNamePair> listCN = new ArrayList<CodeNamePair>();
//		for(CustomerInfo ci : listci){
//			CodeNamePair cn = new CodeNamePair(String.valueOf(ci.getID()),ci.getName());
//			listCN.add(cn);
//		}
//		return listCN;
		return customerInfoDao.findByName(name);
	}

	@Override
	public List<CustomerInfo> getCustomerListByTelCode(String TelCode) {
//		List<CustomerInfo> listci = customerInfoDao.findByTelCode(TelCode);
//		List<CodeNamePair> listCN = new ArrayList<CodeNamePair>();
//		for(CustomerInfo ci : listci){
//			CodeNamePair cn = new CodeNamePair(String.valueOf(ci.getID()),ci.getName());
//			listCN.add(cn);
//		}
//		return listCN;
		return customerInfoDao.findByTelCode(TelCode);
	}

	@Override
	public Response getCustomerInfo(String id) {
		CustomerInfo cstm = customerInfoDao.get(Integer.parseInt(id));
//		try{
//			cstm.setRegionString(regionDao.getRegionNameByID(cstm.getRegionCode()));	//这部分功能放到DAO里去了
//		}catch(Exception e){}
		return Response.ok(cstm).header("EntityClass", "CustomerInfo").build(); 
	}
	
	@Override
	public Response deleteCustomerInfo(int id) {
		customerInfoDao.removeById(id);
		return Response.ok("Deleted").header("EntityClass", "D_CustomerInfo").build(); 
	}

	@Override
	public Response saveCustomerInfo(CustomerInfo obj) {
		try{
			customerInfoDao.save(obj);			
			return Response.ok(obj).header("EntityClass", "R_CustomerInfo").build(); 
		}
		catch(Exception e)
		{
			return Response.serverError().entity(e.getMessage()).build(); 
		}
	}

	@Override
	public List<CodeNamePair> getProvinceList() {		
		List<Region> listrg = regionDao.getProvinceList();
		List<CodeNamePair> listCN = new ArrayList<CodeNamePair>();
		for(Region rg : listrg){
			CodeNamePair cn = new CodeNamePair(rg.getORMID(),rg.getPrv());
			listCN.add(cn);
		}
		return listCN;
	}

	@Override
	public List<CodeNamePair> getCityList(String prv) {
		List<Region> listrg = regionDao.getCityList(prv);
		List<CodeNamePair> listCN = new ArrayList<CodeNamePair>();
		for(Region rg : listrg){
			CodeNamePair cn = new CodeNamePair(rg.getORMID(),rg.getCty());
			listCN.add(cn);
		}
		return listCN;
	}

	@Override
	public List<CodeNamePair> getTownList(String city) {
		List<Region> listrg = regionDao.getTownList(city);
		List<CodeNamePair> listCN = new ArrayList<CodeNamePair>();
		for(Region rg : listrg){
			CodeNamePair cn = new CodeNamePair(rg.getORMID(),rg.getTwn());
			listCN.add(cn);
		}
		return listCN;
	}

	@Override
	public String getRegionString(String code) {
		return regionDao.getRegionNameByID(code);
	}

	@Override
	public Region getRegion(String code) {
		return regionDao.getFullNameRegionByID(code);
	}

	@Override
	public void CreateWorkSession(int uid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean doLogin(int uid, String pwd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void doLogOut(int uid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void RefreshSessionList() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<TransHistory> getTransHistoryList(String packageID) {
		// TODO Auto-generated method stub
		System.out.println("i have into impl");
		transHistoryDao=new TransHistoryDao();
		return transHistoryDao.getTransHistoryByID(packageID);
	}
}
