package application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Department obj = new Department(1,"Books");
		
		System.out.println(obj);
		
		Seller objSel = new Seller(20,"Bob","email@a.com.br",new Date(),2000.00,obj);
		
		System.out.println(objSel);
		
		/*
		   Call o pattern Factory -> evitando que o programa não conhece a implementação,
		   apenas conhece a interface
		*/
		
		System.out.println("");
		System.out.println("==== TEST1: seller findById ====");
		SellerDao sellerDao = DaoFactory.createSellerDao();
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
		
		System.out.println("\n==== TEST2: seller findByDepartment ====");
		Department department = new Department(2,null);
		List<Seller> list = sellerDao.findByDepartment(department);
		for(Seller sel: list)
		{
			System.out.println(sel);
		}
		
		System.out.println("\n==== TEST3: seller findAll ====");		
		list = sellerDao.findAll();
		for(Seller sel: list)
		{
			System.out.println(sel);
		}
		
		System.out.println("\n==== TEST4: seller Insert ====");	
		
		Seller newSeller = new Seller(null,"Greg","greg@email.com",new Date(),4000.00,department);
		sellerDao.insert(newSeller);	
		System.out.println("Inserted! New Id: " + newSeller.getId());
		
		
	}

}
