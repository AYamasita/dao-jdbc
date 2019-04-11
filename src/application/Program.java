package application;

import java.util.Date;

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
		   Call o pattern Factory -> evitando que o progrma não conhece a implementação,
		   apenas conhece a interface
		*/
		SellerDao sellerDao = DaoFactory.createSellerDao();
	}

}
