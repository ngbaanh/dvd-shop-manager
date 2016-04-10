/**
 * 
 */
package model.bo;

import java.util.ArrayList;

import model.bean.Disc;
import model.dao.DiscDAO;
import util.IDisc;

/**
 * @author NguyenBaAnh
 *
 */
public class DiscBO implements IDisc {
	DiscDAO discDAO;

	public DiscBO() {
		discDAO = new DiscDAO();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.IDisc#getDisc(int)
	 */
	@Override
	public Disc getDisc(int discId) {
		if (discId > 0) {
			return discDAO.getDisc(discId);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.IDisc#getListDisc(int)
	 */
	@Override
	public ArrayList<Disc> getListDisc(int discSeriesId) {
		if (discSeriesId > 0) {
			return discDAO.getListDisc(discSeriesId);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.IDisc#addNewDisc(model.bean.Disc)
	 */
	@Override
	public boolean addNewDisc(Disc disc) {
		if (disc.getPlace() == null || "".equals(disc.getPlace().trim())) {
			return false;
		} else {
			return discDAO.addNewDisc(disc);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.IDisc#updateDisc(model.bean.Disc)
	 */
	@Override
	public boolean updateDisc(Disc disc) {
		if (disc.getPlace() == null || "".equals(disc.getPlace().trim())) {
			return false;
		} else if (disc.getQualityId() < 0 || disc.getQualityId() > 3) {
			return false;
		} else {
			return discDAO.updateDisc(disc);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.IDisc#removeDisc(int)
	 */
	@Override
	public boolean removeDisc(int discId) {
		if (this.isFreeToChange(discId)) {
			return discDAO.removeDisc(discId);
		}
		return false;
	}

	// ============================================================
	// Hàm bổ trợ

	/**
	 * Kiểm tra xem 1 đĩa có thể xóa hay không
	 * 
	 * @param discId
	 *            mã đĩa
	 * @return true nếu có thể, false nếu không thể
	 */
	public boolean isFreeToChange(int discId) {
		if (discId <= 0) {
			return false;
		} else {
			Disc disc = this.getDisc(discId);
			return disc.isAvailable();
		}
	}

}
