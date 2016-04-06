/**
 * 
 */
package util;

import java.util.ArrayList;

import model.bean.Price;

/**
 * @author NguyenBaAnh
 *
 */
public interface IPrice {
	public Price getPrice(byte qualityId);
	public ArrayList<Price> getListPrices();
	public boolean updatePrice(Price price);
}
