import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import inventory.Inventory;
import inventory.InventoryManager;
import inventory.InventoryProxy;

public class InventoryManagerTest3 {
	private Inventory bookInventory = new InventoryProxy(new InventoryManager());

	/**
	 * This test verifies if the commands file is deleted after memento file
	 * creation i.e., after executing 10 commands
	 */
	@Test
	public void restoreCommandsAndRestoreMementoTest() {
		bookInventory.sellBook("AlChemist");
		bookInventory.changePrice("Harry Potter", 11.0);
		bookInventory.addCopy("Monk Who Sold His Ferrari", 2);
		bookInventory.sellBook("Monk Who Sold His Ferrari");
		bookInventory.sellBook("Harry Potter");
		assertEquals(11.0, bookInventory.findPriceByName("Harry Potter"), 0.0);
		assertTrue(new File("Command.ser").exists());
		bookInventory.sellBook("AlChemist");
		assertFalse(new File("Command.ser").exists());
		bookInventory.changePrice("Harry Potter", 11.0);
		bookInventory.addCopy("Monk Who Sold His Ferrari", 2);
		bookInventory.sellBook("Monk Who Sold His Ferrari");
		bookInventory.sellBook("Harry Potter");
		bookInventory.sellBook("AlChemist");
		bookInventory.changePrice("Harry Potter", 12.0);
		bookInventory.addCopy("Monk Who Sold His Ferrari", 2);
		bookInventory.sellBook("Monk Who Sold His Ferrari");
		bookInventory.addCopy("Harry Potter", 3);
		assertEquals(12.0, bookInventory.findPriceByName("Harry Potter"), 0.0);
		assertTrue(new File("Command.ser").exists());
		assertTrue(new File("InventoryState.ser").exists());
	}
}
