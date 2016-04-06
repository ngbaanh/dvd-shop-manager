/**
 * 
 */
package util;

import java.util.ArrayList;

import model.bean.DiscSeries;

/**
 * @author NguyenBaAnh
 *
 */
public interface IDiscSeries {
	public DiscSeries getDiscSeries(int discSeriesId);
	public ArrayList<DiscSeries> getDiscSeriesList(String searchQuery, int catId, int page);
	public boolean addNewDiscSeries(DiscSeries discSeries);
	public boolean updateDiscSeries(DiscSeries discSeries);
	public boolean validateDiscSeries(String dsName);
	public boolean removeDiscSeries(int discSeriesId);
	public int getOverallDiscNumber();
	public int getOverallDiscSeriesNumber();
	
}
