package model.bo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Category;
import model.dao.DatabaseFactory;

public class CategoryBO {
	private DatabaseFactory db=new DatabaseFactory();
	private Statement st;
	private ResultSet rs;
	public ArrayList<Category> getListCat() {
		ArrayList<Category> alCat=new ArrayList<>();
		Category cat;
		String sql="Select * from category";
		try {
			st=db.getConnectMySQL().createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()){
				cat=new Category(rs.getInt(1), rs.getString(2));
				alCat.add(cat);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return alCat;
	}

}
