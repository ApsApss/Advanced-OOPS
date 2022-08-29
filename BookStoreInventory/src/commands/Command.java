package commands;

import inventory.Inventory;

public abstract class Command {

	public abstract boolean execute(Inventory currentInventory);

}
