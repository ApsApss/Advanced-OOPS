package commands;

import java.io.Serializable;

import inventory.Inventory;

/**
 * This class executes sellBook method on inventory manager
 */
@SuppressWarnings("serial")
public class SellBook extends Command implements Serializable {
	private String bookName;

	public SellBook(String bookName) {
		this.bookName = bookName;
	}

	@Override
	public boolean execute(Inventory inventoryManager) {
		return inventoryManager.sellBook(bookName);
	}
}
