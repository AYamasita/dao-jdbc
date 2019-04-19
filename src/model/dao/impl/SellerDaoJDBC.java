package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		String sql = "SELECT seller.*, department.Name as DepName " +
                "FROM seller INNER JOIN department " +
			     "ON seller.DepartmentId = departmentId " +
                "WHERE seller.Id= ?";
		
		try {		
			
			st = conn.prepareStatement(sql);
			st.setInt(1,id);
			rs = st.executeQuery();
			if(rs.next())
			{
				Department dep = instantiateDepartment(rs);
				
				Seller sel = instantiateSeller(rs,dep);
				
				return sel;
			}
			return null;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}		
		finally
		{
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}

	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller sel = new Seller();
		sel.setId(rs.getInt("Id"));
		sel.setName(rs.getString("Name"));
		sel.setEmail(rs.getString("Email"));
		sel.setBaseSalary(rs.getDouble("BaseSalary"));
		sel.setBirthDate(rs.getDate("BirthDate"));
		sel.setDepartment(dep);
		return sel;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException
	{
	    Department dep = new Department();	
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		Statement st = null;
		ResultSet rs = null;
		
		String sql = "SELECT seller.*, department.Name as DepName " +
		             "FROM seller INNER JOIN department " + 
				     "on seller.DepartmentId = department.Id " + 
		             "ORDER BY Name";			
		try {
			
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			List<Seller> listSeller = new ArrayList<Seller>();
			Map<Integer,Department> map = new HashMap<>();
			while(rs.next())
			{
				Department dep = map.get(rs.getInt("DepartmentId"));
				if(dep == null)
				{
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				Seller sel = instantiateSeller(rs, dep);
				listSeller.add(sel);
			}
			return listSeller;
			
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally
		{
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null;
	
		
		String sql= "SELECT seller.*, department.Name as DepName " + 
		            "FROM seller INNER JOIN department " + 
				    "on seller.DepartmentId = department.Id " +
		            "WHERE DepartmentId= ? " + 
		            "ORDER BY Name";
		try {
			
				st = conn.prepareStatement(sql);
				st.setInt(1,department.getId());
				rs  = st.executeQuery();
				
				List<Seller> listSeller = new ArrayList<Seller>();
				Map<Integer,Department> map = new HashMap<>();
				
				while(rs.next())				
				{
					Department dep = map.get(rs.getInt("DepartmentId"));
					if(dep == null)
					{
						dep = instantiateDepartment(rs);
						map.put(rs.getInt("DepartmentId"), dep);
					}					
					
					Seller sel = instantiateSeller(rs, dep);						
					listSeller.add(sel);	
				}
				return listSeller;
				
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally
		{
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}
