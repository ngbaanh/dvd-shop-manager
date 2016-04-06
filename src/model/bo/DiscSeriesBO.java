/**
 * 
 */
package model.bo;

import java.util.ArrayList;

import model.bean.Disc;
import model.bean.DiscSeries;
import model.dao.DiscSeriesDAO;
import util.IDiscSeries;

/**
 * @author NguyenBaAnh
 *
 */
public class DiscSeriesBO implements IDiscSeries {
	DiscSeriesDAO discSeriesDAO;
	public DiscSeriesBO() {
		discSeriesDAO = new DiscSeriesDAO();
	}

	/* (non-Javadoc)
	 * @see util.IDiscSeries#getDiscSeries(int)
	 */
	@Override
	public DiscSeries getDiscSeries(int discSeriesId) {
		if (discSeriesId > 0) {
			return discSeriesDAO.getDiscSeries(discSeriesId);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see util.IDiscSeries#getDiscSeriesList(java.lang.String, int, int)
	 */
	@Override
	public ArrayList<DiscSeries> getDiscSeriesList(String searchQuery, int catId, int page) {
		// Xử lí sự hợp lệ của searchQuery, catId, page
		// ... TODO ...
		// sau khi thỏa mãn, đẩy cho DAO
		return discSeriesDAO.getDiscSeriesList(searchQuery, catId, page);
	}

	/* (non-Javadoc)
	 * @see util.IDiscSeries#addNewDiscSeries(model.bean.DiscSeries)
	 */
	@Override
	public boolean addNewDiscSeries(DiscSeries discSeries) {
		// TODO Auto-generated method stub
		// ... 
		return discSeriesDAO.addNewDiscSeries(discSeries);
	}

	/* (non-Javadoc)
	 * @see util.IDiscSeries#updateNewDiscSeries(model.bean.DiscSeries)
	 */
	@Override
	public boolean updateDiscSeries(DiscSeries discSeries) {
		// TODO Auto-generated method stub
		// ...
		return discSeriesDAO.updateDiscSeries(discSeries);
	}

	/* (non-Javadoc)
	 * @see util.IDiscSeries#validateDiscSeries(java.lang.String)
	 */
	@Override
	public boolean validateDiscSeries(String dsName) {
		if ("".equals(dsName.trim())) {
			return false;
		}
		return discSeriesDAO.validateDiscSeries(dsName);
	}

	/* (non-Javadoc)
	 * @see util.IDiscSeries#removeDiscSeries(java.lang.String)
	 */
	@Override
	public boolean removeDiscSeries(int discSeriesId) {
		if (this.isFreeToDelete(discSeriesId)) {
			return discSeriesDAO.removeDiscSeries(discSeriesId);
		} else {
			return false;
		}
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
	
	//=============================================================
	// Các hàm bổ trợ
	
	/**
	 * Kiểm tra 1 bộ đĩa có thể xóa hay không?
	 * @return true nếu có thể xóa, false nếu không thể.
	 */
	public boolean isFreeToDelete(int discSeriesId) {
		if (discSeriesId <= 0) {
			return false;
		}
		DiscSeries discSeries = this.getDiscSeries(discSeriesId);
		ArrayList<Disc> listDisc = discSeries.getListDisc();
		if (listDisc.isEmpty()) { // Bộ đĩa mà không có đĩa nào
			return true; // thì cho xóa
		}
		for (Disc disc : listDisc) { // kiểm tra từng đĩa
			if (!disc.isAvailable()) { // nếu đĩa không sẵn sàng
				return false; // không cho xóa
			} else { // nếu đĩa này sẵn sàng
				continue; // tiếp tục dò cho đến hết đĩa
			}
		}
		return false;
	}

}
