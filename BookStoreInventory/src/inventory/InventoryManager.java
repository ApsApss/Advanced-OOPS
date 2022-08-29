package inventory;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 * This class manages book store inventory
 *
 */
public class InventoryManager implements Inventory {

	private HashMap<String, Book> bookCollection = new HashMap<String, Book>();
	private int bookCounter = 0;

	public InventoryManager() {
	}

	/**
	 * this method adds book in the inventory if the book name doesn't already exist
	 * in the inventory
	 * 
	 * @return true if inventory doesn't contains book with bookName otherwise false
	 */
	public boolean addBook(Book book) {
		if (!bookCollection.containsKey(book.getName())) {
			book.setUniqueId(++bookCounter);
			bookCollection.put(book.getName(), book);
			return true;
		} else
			return false;
	}

	/**
	 * this method sell copies of existing books from the inventory based on (@param
	 * bookName) it will search for that book in the inventory and updates the
	 * quantity of that book to quantity-1
	 * 
	 * @return true if inventory contains book with bookName otherwise false
	 */
	public boolean sellBook(String bookName) {
		if (bookCollection.containsKey(bookName)) {
			Book book = bookCollection.get(bookName);
			if (book.getQuantity() > 0) {
				book.updateQuantity(-1);
				return true;
			} else
				return false;
		} else
			return false;
	}

	/**
	 * this method add copies of existing book in the inventory based on (@param
	 * bookName) it will search for that book in the inventory and updates the
	 * number of copies of that book with (@param numberOfCopies) value
	 * 
	 * @return true if inventory contains book with bookName otherwise false
	 */
	public boolean addCopy(String bookName, int numberOfCopies) {
		if (bookCollection.containsKey(bookName) && numberOfCopies > 0) {
			Book book = bookCollection.get(bookName);
			book.updateQuantity(numberOfCopies);
			return true;
		} else
			return false;
	}

	/**
	 * this method changes price of existing book in the inventory based on (@param
	 * bookName) it will search for that book in the inventory and changes the old
	 * price with (@param newPrice) value
	 * 
	 * @return true if inventory contains book with bookName otherwise false
	 */
	public boolean changePrice(String bookName, double newPrice) {
		if (bookCollection.containsKey(bookName) && newPrice > 0) {
			Book book = bookCollection.get(bookName);
			book.setPrice(newPrice);
			return true;
		} else
			return false;
	}

	/**
	 * This method returns price of a book if name matches with (@param bookName)
	 * 
	 * @return book price if the bookName exists otherwise null
	 */
	public Double findPriceByName(String bookName) {
		if (bookCollection.containsKey(bookName)) {
			Book book = bookCollection.get(bookName);
			return book.getPrice();
		}
		return null;
	}

	/**
	 * This method returns price of a book if id matches with (@param bookId)
	 * 
	 * @return book price if the bookId exists otherwise null
	 */
	public Double findPriceById(int bookId) {
		for (Entry<String, Book> entry : bookCollection.entrySet()) {
			Book book = entry.getValue();
			if (book.getUniqueId().equals(bookId)) {
				return book.getPrice();
			}
		}
		return null;
	}

	/**
	 * This method returns number of copies of a book if name matches with (@param
	 * bookName)
	 * 
	 * @return number copies if the bookName exists otherwise null
	 */
	public Integer findQuantityByName(String bookName) {
		if (bookCollection.containsKey(bookName)) {
			Book book = bookCollection.get(bookName);
			return book.getQuantity();
		}
		return null;
	}

	/**
	 * This method returns number of copies of a book if id matches with (@param
	 * bookId)
	 * 
	 * @return number copies if the bookId exists otherwise null
	 */
	public Integer findQuantityById(int bookId) {
		for (Entry<String, Book> entry : bookCollection.entrySet()) {
			Book book = entry.getValue();
			if (book.getUniqueId().equals(bookId)) {
				return book.getQuantity();
			}
		}
		return null;
	}

	/**
	 * This method saves the inventory manager state to Memento
	 * 
	 * @return Memento object with saved inventory manager state
	 */

	public Memento createMemento() {
		return new Memento(bookCollection);
	}

	/**
	 * This restores inventory manager state to previous state and also sets counter
	 * for book uniqueId
	 * 
	 * @param oldState
	 */
	public void restoreState(Memento oldState) {
		bookCollection = oldState.getState();
		bookCounter = bookCollection.size();
	}
}