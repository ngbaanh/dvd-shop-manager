package business.session;

public class PendingDisc {
	private int discId;
	private int numOfWeeks;
	
	public PendingDisc() {
		
	}
	
	public PendingDisc(int discId, int numOfWeeks) {
		this.discId = discId;
		this.numOfWeeks = numOfWeeks;
	}

	public void setDiscId(int discId) {
		this.discId = discId;
	}

	public void setNumOfWeeks(int numOfWeeks) {
		this.numOfWeeks = numOfWeeks;
	}

	public int getDiscId() {
		return discId;
	}

	public int getNumOfWeeks() {
		return numOfWeeks;
	}
}
