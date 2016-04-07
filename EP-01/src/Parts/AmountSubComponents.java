package Parts;

public class AmountSubComponents {

	public Part subComponent = null;
	public int amount = 0;
	
	public AmountSubComponents(Part subComponent, int amount) {
		this.subComponent = subComponent;
		this.amount = amount;
	}
	
	public Part getSubComponent() {
		return this.subComponent;		
	}
	
	public int getAmount() {
		return this.amount;		
	}
}
