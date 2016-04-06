/**
 * 
 */
package util;

import java.util.ArrayList;

import model.bean.Disc;

/**
 * @author NguyenBaAnh
 *
 */
public interface IDisc {
	public Disc getDisc(int discId);
	public ArrayList<Disc> getListDisc(int discSeriesId);
	public boolean addNewDisc(Disc disc);
	public boolean updateDisc(Disc disc);
	public boolean removeDisc(int discId);
}
