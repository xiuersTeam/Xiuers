package ts.daoImpl;

import ts.daoBase.BaseDao;
import ts.model.ClientInfo;

public class ClientInfoDao extends BaseDao<ClientInfo, Integer>{

	public ClientInfoDao() {
		super(ClientInfo.class);
	}
	
	
}
