package commands;

import java.io.Serializable;

import inventory.Inventory;

/**
 * This class executes changePrice method on inventory manager
 */

@SuppressWarnings("serial")
public class ChangePrice extends Command implements Serializable {
	private String bookName;
	private double newPrice;

	public ChangePrice(String bookName, double newPrice) {
		this.bookName = bookName;
		this.newPrice = newPrice;
	}

	@Override
	public boolean execute(Inventory inventoryManager) {
		return inventoryManager.changePrice(bookName, newPrice);
	}
}
