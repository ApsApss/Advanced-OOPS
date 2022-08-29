import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;

import inventory.Book;
import inventory.Inventory;
import inventory.InventoryManager;
import inventory.InventoryProxy;

/**
 * This class tests the operations like addBook, sellBook, addCopy, changePrice,
 * findQuantityByName or Id, findPriceByName or Id of inventory class
 *
 */
public class InventoryManagerTest1 {
	private Inventory bookInventory = new InventoryProxy(new InventoryManager());

	@Before
	public void setUp() {
		bookInventory.addBook(new Book("AlChemist", 5.99, 10));
		bookInventory.addBook(new Book("Harry Potter", 7.25, 5));
		bookInventory.addBook(new Book("Monk Who Sold His Ferrari", 3.5, 5));
		bookInventory.addBook(new Book("Fault in our Stars", 6.5, 5));
		bookInventory.addBook(new Book("Game of Thrones", 12.99, 5));
	}

	@Test
	public void addBookAndAddCopyTest() {
		Book book = new Book("Tale of Two Cities", 15.50, 10);
		assertTrue(bookInventory.addBook(book));
		assertTrue(bookInventory.addCopy("Tale of Two Cities", 5));
		assertFalse(bookInventory.addBook(new Book("AlChemist", 5.0, 5)));
		assertFalse(bookInventory.addCopy("Lord of the Rings", 5));
		Integer expectedResult = 15;
		assertEquals(expectedResult, bookInventory.findQuantityById(6));
	}

	@Test
	public void findQuantityTest() {
		Integer expectedResult = 5;
		assertEquals(expectedResult, bookInventory.findQuantityByName("Game of Thrones"));
		assertEquals(expectedResult, bookInventory.findQuantityById(5));
		assertNull(bookInventory.findQuantityByName("Lord of the Rings"));
	}

	@Test
	public void findPriceTest() {
		assertEquals(7.25, bookInventory.findPriceByName("Harry Potter"), 0);
		assertEquals(3.5, bookInventory.findPriceById(3), 0);
		assertNull(bookInventory.findPriceByName("Lord of the Rings"));
	}

	@Test
	public void sellBookTest() {
		assertTrue(bookInventory.sellBook("AlChemist"));
		assertTrue(bookInventory.sellBook("Monk Who Sold His Ferrari"));
		assertFalse(bookInventory.sellBook("Lord of the Rings"));
		Integer expectedResult = 9;
		assertEquals(expectedResult, bookInventory.findQuantityByName("AlChemist"));
	}

	@Test
	public void changePriceTest() {
		assertTrue(bookInventory.changePrice("Monk Who Sold His Ferrari", 7.99));
		assertFalse(bookInventory.changePrice("Lord of the rings", 7.99));
		assertEquals(7.99, bookInventory.findPriceByName("Monk Who Sold His Ferrari"), 0);
	}

	/**
	 * This test verifies after executing 10 commands inventoryState file is created
	 * and Command file should be deleted from the file system
	 */
	@AfterAll
	public void saveMementoToDiskTest() {
		assertTrue(new File("InventoryState.ser").exists());
	}
}
