/**
 * 
 */
package model.dao;

import java.util.ArrayList;

import model.bean.Disc;
import util.IDisc;

/**
 * @author NguyenBaAnh
 *
 */
public class DiscDAO extends DatabaseFactory implements IDisc {

	/**
	 * 
	 */
	public DiscDAO() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see util.IDisc#getDisc(int)
	 */
	@Override
	public Disc getDisc(int discId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see util.IDisc#getListDisc(int)
	 */
	@Override
	public ArrayList<Disc> getListDisc(int discSeriesId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see util.IDisc#addNewDisc(model.bean.Disc)
	 */
	@Override
	public boolean addNewDisc(Disc disc) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see util.IDisc#updateDisc(model.bean.Disc)
	 */
	@Override
	public boolean updateDisc(Disc disc) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see util.IDisc#removeDisc(int)
	 */
	@Override
	public boolean removeDisc(int discId) {
		// TODO Auto-generated method stub
		return false;
	}

}
