/**
 * Lớp đóng gói Đĩa
 */
package model.bean;

/**
 * @author NguyenBaAnh
 *
 */
public class Disc {
	protected int discId;
	protected int discSeriesId;
	protected byte qualityId;
	protected int price; 
	protected String place;
	protected boolean available;
	
	public Disc() {
		super();
	}

	/**
	 * @return the discId
	 */
	public int getDiscId() {
		return discId;
	}

	/**
	 * @param discId the discId to set
	 */
	public void setDiscId(int discId) {
		this.discId = discId;
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
	 * @return the qualityId
	 */
	public byte getQualityId() {
		return qualityId;
	}

	/**
	 * @param qualityId the qualityId to set
	 */
	public void setQualityId(byte qualityId) {
		this.qualityId = qualityId;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return the place
	 */
	public String getPlace() {
		return place;
	}

	/**
	 * @param place the place to set
	 */
	public void setPlace(String place) {
		this.place = place;
	}

	/**
	 * @return the available
	 */
	public boolean isAvailable() {
		return available;
	}

	/**
	 * @param available the available to set
	 */
	public void setAvailable(boolean available) {
		this.available = available;
	} 
	
	
}
