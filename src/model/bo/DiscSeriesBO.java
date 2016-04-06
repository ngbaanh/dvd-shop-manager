/**
 * 
 */
package model.bo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.DiscSeries;
import model.dao.DatabaseFactory;

/**
 * @author Tran Thanh Sang
 *
 */
public class DiscSeriesBO {

	/**
	 * 
	 */
	private DatabaseFactory db = new DatabaseFactory();
	public DiscSeriesBO() {
		// TODO Auto-generated constructor stub
	}
	public ArrayList<DiscSeries>getDiscSeriesList(){
		ArrayList<DiscSeries> alDisc=new ArrayList<>();
		DiscSeries discSeriesList = null;
		String sql = "select * from disc_series";
		Statement st;	
		try {
			st =  db.getConnectMySQL().createStatement();
			ResultSet rs =  st.executeQuery(sql);
			while(rs.next()){
				discSeriesList = new DiscSeries();
				discSeriesList.setDiscSeriesId(rs.getInt(1));
				discSeriesList.setDiscSeriesName(rs.getString(2));
				discSeriesList.setDescription(rs.getString(3));
				discSeriesList.setTotalDisc(rs.getInt(4));
				discSeriesList.setRemainingDisc(rs.getInt(5));
				discSeriesList.setCategoryId(rs.getInt(6));
				alDisc.add(discSeriesList);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alDisc;
	}
}
