package model.dao;

import db.DB;
import model.dao.impl.DepartmentDaoJDBC;
import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
	
	//A inje��o de depend�ncia pode ser feita por meio do padr�o de projeto Factory 
	
	public static SellerDao createSellerDao()
	{
		return new SellerDaoJDBC(DB.getConnection());
		
	}
	
	public static DepartmentDao createDepartmentDao()
	{
		return new DepartmentDaoJDBC(DB.getConnection());
	}
}
