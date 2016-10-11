package business.session;

public class PendingDisc {
	private String discSeriesName;
	private int discId;
	private int rentingWeeks;
	
	public PendingDisc() {
		
	}
	
	public PendingDisc(String discSeriesName, int discId, int rentingWeeks) {
		this.discSeriesName = discSeriesName;
		this.discId = discId;
		this.rentingWeeks = rentingWeeks;
	}

	public String getDiscSeriesName() {
		return discSeriesName;
	}

	public void setDiscSeriesName(String discSeriesName) {
		this.discSeriesName = discSeriesName;
	}

	public int getDiscId() {
		return discId;
	}

	public void setDiscId(int discId) {
		this.discId = discId;
	}

	public int getRentingWeeks() {
		return rentingWeeks;
	}

	public void setRentingWeeks(int rentingWeeks) {
		this.rentingWeeks = rentingWeeks;
	}
}
