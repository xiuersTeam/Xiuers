package ts.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Restrictions;

import ts.daoBase.BaseDao;
import ts.model.Appointment;
import ts.model.CustomerInfo;
import ts.model.ExpressSheet;

public class AppointmentDao extends BaseDao<Appointment, Integer>{

	private RegionDao regionDao;
	public RegionDao getRegionDao() {
		return regionDao;
	}
	public void setRegionDao(RegionDao dao) {
		this.regionDao = dao;
	}
	
	public AppointmentDao() {
		super(Appointment.class);
	}
	
	//��д��get����,���ͻ��������ַ�������
		public Appointment get(int id) {
			Appointment app = super.get(id);
			CustomerInfo ci = app.getRecever();
			if(ci!= null)
				ci.setRegionString(regionDao.getRegionNameByID(ci.getRegionCode()));	//��ȡ����������ַ���
			CustomerInfo cs = app.getSender();
			if(cs != null) 
				cs.setRegionString(regionDao.getRegionNameByID(cs.getRegionCode()));	//��ȡ����������ַ���
			return app;
		}
		
		/**
		 * ����ԤԼ����(id)��ȡԤԼ������Ϣ
		 */
		public Appointment getAppointmentById(Integer id) {
			Appointment app=new Appointment();
			app= super.get(id);
			return app;
		}
		/**
		 * ���ݿͻ�(cid)��ȡԤԼ������Ϣ
		 */
		public List<Appointment> getAppointmentByCId(Integer cid) {
			String sql = "{alias}.ID in (select id from appointment where  cid = '"+cid+"')";
			List<Appointment> list = new ArrayList<>();
			list = findBy("ID", true, Restrictions.sqlRestriction(sql));		
			return list;
		}
}
