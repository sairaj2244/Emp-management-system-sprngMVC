package com.demo4;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class EmpDao {

	JdbcTemplate template;

	public JdbcTemplate getTemplate() {
		return template;
	}

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	
	public int save(Emp e) {
		
		String sql="insert into mvc1(name,salary,designation) values('"+e.getName()+"',"+e.getSalary()+",'"+e.getDesignation()+"')";  
		return template.update(sql);
	}
	
	public List<Emp> getEmployees(){
		
		return template.query("select * from mvc1", new RowMapper<Emp>() {

			public Emp mapRow(ResultSet rs, int rowNum) throws SQLException {
				Emp e = new Emp();
				
				e.setId(rs.getInt(1));    
	            e.setName(rs.getString(2));    
	            e.setSalary(rs.getFloat(3));    
	            e.setDesignation(rs.getString(4));    
	            return e;    
			}
		});
	}
	
	public Emp getEmpById(int id) {
		String sql = "select * from mvc1 where id=?";
		return template.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<Emp>(Emp.class));
	}
	
	public int update(Emp p){    
	    String sql="update mvc1 set name='"+p.getName()+"', salary="+p.getSalary()+",designation='"+p.getDesignation()+"' where id="+p.getId()+"";    
	    return template.update(sql);    
	}  
	
	
	public int delete(int id){    
	    String sql="delete from mvc1 where id="+id+"";    
	    return template.update(sql);    
	}   
	
}
