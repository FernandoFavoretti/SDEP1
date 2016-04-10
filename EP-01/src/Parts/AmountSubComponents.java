package Parts;

import java.io.Serializable;

public class AmountSubComponents implements Serializable {

	public Part subComponent = null;
	public int amount = 0;
	public String serverName = null;
	
	public AmountSubComponents(Part subComponent, int amount, String serverName) {
		this.subComponent = subComponent;
		this.amount = amount;
		this.serverName = serverName;
	}
	
	public Part getSubComponent() {
		return this.subComponent;		
	}
	
	public int getAmount() {
		return this.amount;		
	}
	public String getServer(){
		return this.serverName;
	}
}
