/**
 * 
 */
package model.bo;

import java.util.ArrayList;

import model.bean.DiscSeries;
import util.IDiscSeries;

/**
 * @author NguyenBaAnh
 *
 */
public class DiscSeriesBO implements IDiscSeries {

	/**
	 * 
	 */
	public DiscSeriesBO() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see util.IDiscSeries#getDiscSeries(int)
	 */
	@Override
	public DiscSeries getDiscSeries(int DicSeriesId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see util.IDiscSeries#getDiscSeriesList(java.lang.String, int, int)
	 */
	@Override
	public ArrayList<DiscSeries> getDiscSeriesList(String searchQuery, int catId, int page) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see util.IDiscSeries#addNewDiscSeries(model.bean.DiscSeries)
	 */
	@Override
	public boolean addNewDiscSeries(DiscSeries discSeries) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see util.IDiscSeries#updateNewDiscSeries(model.bean.DiscSeries)
	 */
	@Override
	public boolean updateDiscSeries(DiscSeries discSeries) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see util.IDiscSeries#validateDiscSeries(java.lang.String)
	 */
	@Override
	public boolean validateDiscSeries(String dsName) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see util.IDiscSeries#removeDiscSeries(java.lang.String)
	 */
	@Override
	public boolean removeDiscSeries(String discSeriesId) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see util.IDiscSeries#getOverallDiscNumber()
	 */
	@Override
	public int getOverallDiscNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see util.IDiscSeries#getOverallDiscSeriesNumber()
	 */
	@Override
	public int getOverallDiscSeriesNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

}
