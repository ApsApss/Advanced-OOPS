package commands;

import java.io.Serializable;

import inventory.Inventory;

/**
 * This class executes addCopy method on inventory manager
 */

@SuppressWarnings("serial")
public class AddCopy extends Command implements Serializable {
	private String bookName;
	private Integer numberOfCopies;

	public AddCopy(String bookName, int numberOfCopies) {
		this.bookName = bookName;
		this.numberOfCopies = numberOfCopies;
	}

	@Override
	public boolean execute(Inventory inventoryManager) {
		return inventoryManager.addCopy(bookName, numberOfCopies);
	}
}
