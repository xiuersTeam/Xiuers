package ts.daoImpl;

import java.util.List;

import org.springframework.util.Assert;

import ts.daoBase.BaseDao;
import ts.model.TransHistory;

public class TransHistoryDao extends BaseDao<TransHistory,Integer> {
	public TransHistoryDao(){
		super(TransHistory.class);
	}
	
	public List<TransHistory> getTransHistoryByID(String id){
		System.out.println("i have into dao,be ready");
		//Assert.hasText(id);
		System.out.println("i have into dao");
		return findBy("PackageID", id, "actTime", true);
	}
}
