package commands;

import java.io.Serializable;

import inventory.Book;
import inventory.Inventory;

/**
 * This class executes addBook method on inventory manager
 */
@SuppressWarnings("serial")
public class AddBook extends Command implements Serializable {
	private Book book;

	public AddBook(Book book) {
		this.book = book;
	}

	@Override
	public boolean execute(Inventory inventoryManager) {
		return inventoryManager.addBook(book);
	}
}
