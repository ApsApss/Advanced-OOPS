import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import inventory.Inventory;
import inventory.InventoryManager;
import inventory.InventoryProxy;

public class InventoryManagerTest2 {
	private Inventory bookInventory = new InventoryProxy(new InventoryManager());

	/**
	 * This test verifies if the retrieved Memento restored the state of inventory
	 * by performing find operations
	 * 
	 * After executing less than 10 commands, this test verifies if the command file
	 * is created in the file system or not
	 */
	@Test
	public void retrieveMementoFromDisksaveCommandToDiskTest() {
		Integer expectedResult = 5;
		assertEquals(expectedResult, bookInventory.findQuantityByName("Fault in our Stars"));
		assertEquals(7.25, bookInventory.findPriceByName("Harry Potter"), 0.0);
		assertFalse(new File("Command.ser").exists());
		bookInventory.sellBook("AlChemist");
		bookInventory.changePrice("Harry Potter", 10.0);
		bookInventory.addCopy("Monk Who Sold His Ferrari", 1);
		bookInventory.sellBook("Harry Potter");
		assertTrue(new File("Command.ser").exists());
	}
}
