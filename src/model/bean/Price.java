/**
 * Lớp đóng gói Giá thuê đĩa
 */
package model.bean;

/**
 * @author NguyenBaAnh
 *
 */
public class Price {
	private byte qualityId;
	private int price;
	public Price() {
		super();
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
	
}
