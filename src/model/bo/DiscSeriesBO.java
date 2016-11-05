/**
 * 
 */
package model.bo;

import java.util.ArrayList;

import model.bean.DiscSeries;
import model.dao.DiscSeriesDAO;
import util.Const;
import util.IDiscSeries;

/**
 * @author NguyenBaAnh
 * @author NguyenVanQuang
 *
 */
public class DiscSeriesBO implements IDiscSeries {
	DiscSeriesDAO discSeriesDAO;

	public DiscSeriesBO() {
		discSeriesDAO = new DiscSeriesDAO();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.IDiscSeries#getDiscSeries(int)
	 */
	@Override
	public DiscSeries getDiscSeries(int discSeriesId) {
		if (discSeriesId > 0) {
			return discSeriesDAO.getDiscSeries(discSeriesId);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.IDiscSeries#getDiscSeriesList(java.lang.String, int, int)
	 */
	@Override
	public ArrayList<DiscSeries> getDiscSeriesList(String searchQuery, int catId, int page) {
		// Xử lí sự hợp lệ của searchQuery, catId, page
		if (searchQuery == null || catId < 0 || page < 1) {
			return null;
		}
		// sau khi thỏa mãn, đẩy cho DAO
		return discSeriesDAO.getDiscSeriesList(searchQuery, catId, page);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.IDiscSeries#addNewDiscSeries(model.bean.DiscSeries)
	 */
	@Override
	public boolean addNewDiscSeries(DiscSeries discSeries) {
		if ("".equals(discSeries.getDiscSeriesName().trim())) {
			return false;
		} else if (discSeries.getTotalDisc() < 1 || discSeries.getTotalDisc() > 100) {
			return false;
		} else if ("".equals(discSeries.getListDisc().get(0).getPlace().trim())) {
			return false;
		} else if (Const.MAXLENGTH_NAME < discSeries.getDiscSeriesName().length()) {
			return false;
		} else if (Const.MAXLENGTH_DESCRIPTION < discSeries.getDescription().length()) {
			return false;
		} else if (discSeries.getCategory() == null) {
			return false;
		}
		return discSeriesDAO.addNewDiscSeries(discSeries);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.IDiscSeries#updateNewDiscSeries(model.bean.DiscSeries)
	 */
	@Override
	public boolean updateDiscSeries(DiscSeries discSeries) {
		if ("".equals(discSeries.getDiscSeriesName().trim())) {
			return false;
		} else if (Const.MAXLENGTH_NAME < discSeries.getDiscSeriesName().length()) {
			return false;
		} else if (Const.MAXLENGTH_DESCRIPTION < discSeries.getDescription().length()) {
			return false;
		} else if (discSeries.getCategory() == null) {
			return false;
		}
		return discSeriesDAO.updateDiscSeries(discSeries);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.IDiscSeries#validateDiscSeries(java.lang.String)
	 */
	@SuppressWarnings("deprecation")
	@Override
	public boolean validateDiscSeries(String dsName) {
		if ("".equals(dsName.trim())) {
			return false;
		}
		return discSeriesDAO.validateDiscSeries(dsName);
	}

	/*
	 * (non-Javadoc)
	 * 
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.IDiscSeries#getOverallDiscNumber()
	 */
	@Override
	public int getOverallDiscNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.IDiscSeries#getOverallDiscSeriesNumber()
	 */
	@Override
	public int getOverallDiscSeriesNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

	// =============================================================
	// Các hàm bổ trợ

	/**
	 * Kiểm tra 1 bộ đĩa có thể xóa hay không?
	 * 
	 * @return true nếu có thể xóa, false nếu không thể.
	 */
	public boolean isFreeToDelete(int discSeriesId) {
		if (discSeriesId <= 0) {
			return false;
		}
		DiscSeries discSeries = this.getDiscSeries(discSeriesId);
		/* ràng buộc mạnh
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
		}*/
		// ràng buộc yếu
		if (discSeries.getRemainingDisc() == discSeries.getTotalDisc()) {
			return true;
		}
		return false;
	}

	/**
	 * Lấy mã bộ đĩa theo tên
	 * 
	 * @param discSeriesName
	 * @return
	 */
	public int getIdByName(String discSeriesName) {
		if (discSeriesName == null || "".equals(discSeriesName.trim())) {
			return 0;
		} else if (discSeriesName.length() > Const.MAXLENGTH_NAME) {
			return 0;
		} else {
			return discSeriesDAO.getIdByName(discSeriesName);
		}
	}

	// =============================================
	/**
	 * Tính tổng số trang
	 * 
	 * @param catId
	 *            mã thể loại
	 * @return toàn bộ số trang khi catId=0, toàn bộ số trang cho riêng mã thể
	 *         loại catId khi catId>0
	 */
	public int getMaxPage(int catId) {
		return discSeriesDAO.getMaxPage(catId); //ok
	}
	
	/**
	 * Tính tổng số trang
	 * Bổ sung số trang theo nội dung tìm kiếm
	 * 
	 * @param catId
	 *            mã thể loại
	 * @param searchQuery
	 *            nội dung tìm kiếm
	 * @return toàn bộ số trang khi catId=0, toàn bộ số trang cho riêng mã thể
	 *         loại catId khi catId>0
	 */
	public int getMaxPage(int catId, String searchQuery) {
		return discSeriesDAO.getMaxPage(catId, searchQuery);
	}

	/**
	 * Kiểm tra xem tên một bộ đĩa đã tồn tại trong hệ thống hay chưa?
	 * 
	 * @param discSeriesName
	 *            tên bộ đĩa
	 * @return true nếu tồn tại, false nếu chưa.
	 */
	public boolean isExist(String discSeriesName) {
		if (discSeriesName == null || "".equals(discSeriesName.trim())) {
			return false;
		} else {
			return discSeriesDAO.isExist(discSeriesName);
		}

	}

	/**
	 * Kiểm tra xem bộ đĩa này đã tồn tại trong hệ thống hay chưa?
	 * 
	 * @param discSeries
	 *            bộ đĩa
	 * @return true nếu có 1 bộ đĩa khác có cùng tên nhưng khác mã số. Ngược lại
	 *         false
	 */
	public boolean hasExistence(DiscSeries discSeries) {
		if (discSeries == null) {
			return false;
		} else if ((this.getIdByName(discSeries.getDiscSeriesName()) > 0)
				&& (discSeries.getDiscSeriesId() != this.getIdByName(discSeries.getDiscSeriesName()))) {
			return true;
		} else {
			return false;
		}
	}

	public int numDiscSeries() {
		return discSeriesDAO.numDiscSeries();
	}

}
