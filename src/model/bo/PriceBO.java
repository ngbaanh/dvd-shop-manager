/**
 * 
 */
package model.bo;

import java.util.ArrayList;

import model.bean.Price;
import model.dao.PriceDAO;
import util.IPrice;

/**
 * @author ngbaanh
 *
 */
public class PriceBO implements IPrice {
	PriceDAO priceDAO;
	/**
	 * 
	 */
	public PriceBO() {
		priceDAO = new PriceDAO();
	}

	@Override
	public Price getPrice(byte qualityId) {
		if (qualityId > 0 && qualityId <= 3) {
			return priceDAO.getPrice(qualityId);
		}
		return null;
	}

	@Override
	public ArrayList<Price> getListPrices() {
		return priceDAO.getListPrices();
	}

	@Override
	public boolean updatePrice(Price price) {
		return priceDAO.updatePrice(price);
	}

}
