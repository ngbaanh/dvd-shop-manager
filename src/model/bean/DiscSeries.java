/**
 * Lớp đóng gói Bộ Đĩa
 */
package model.bean;

import java.util.ArrayList;

/**
 * @author NguyenBaAnh
 * @author NguyenVanQuang
 *
 */
public class DiscSeries {
	private int discSeriesId;
	private String discSeriesName;
	private String description;
	private int totalDisc;
	private int remainingDisc;
	private ArrayList<Disc> listDisc;
	private Category category;
	
	public DiscSeries() {
		super();
	}

	/**
	 * @return the discSeriesId
	 */
	public int getDiscSeriesId() {
		return discSeriesId;
	}

	/**
	 * @param discSeriesId the discSeriesId to set
	 */
	public void setDiscSeriesId(int discSeriesId) {
		this.discSeriesId = discSeriesId;
	}

	/**
	 * @return the discSeriesName
	 */
	public String getDiscSeriesName() {
		return discSeriesName;
	}

	/**
	 * @param discSeriesName the discSeriesName to set
	 */
	public void setDiscSeriesName(String discSeriesName) {
		this.discSeriesName = discSeriesName;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the totalDisc
	 */
	public int getTotalDisc() {
		return totalDisc;
	}

	/**
	 * @param totalDisc the totalDisc to set
	 */
	public void setTotalDisc(int totalDisc) {
		this.totalDisc = totalDisc;
	}

	/**
	 * @return the remainingDisc
	 */
	public int getRemainingDisc() {
		return remainingDisc;
	}

	/**
	 * @param remainingDisc the remainingDisc to set
	 */
	public void setRemainingDisc(int remainingDisc) {
		this.remainingDisc = remainingDisc;
	}

	/**
	 * @return the listDisc
	 */
	public ArrayList<Disc> getListDisc() {
		return listDisc;
	}

	/**
	 * @param listDisc the listDisc to set
	 */
	public void setListDisc(ArrayList<Disc> listDisc) {
		this.listDisc = listDisc;
	}

	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}
	
	
	
}
