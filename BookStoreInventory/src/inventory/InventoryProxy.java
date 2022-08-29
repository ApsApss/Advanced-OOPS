package inventory;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Logger;

import commands.AddBook;
import commands.AddCopy;
import commands.ChangePrice;
import commands.Command;
import commands.SellBook;

/**
 * This is a decorator of inventory manager to execute, save each command to
 * file, to save inventory manager state after executing 10 commands and restore
 * inventory manager state when decorator is instantiated, replay saved commands
 * on the restored inventory manager
 */
public class InventoryProxy implements Inventory {
	private InventoryManager inventoryManager;
	private static final String STATEFILENAME = "InventoryState.ser";
	private Command command;
	private File commandFile = new File("Command.ser");
	private int numberOfCommands = 0, maxNumberToSave = 10;
	private Logger exceptionLogger = Logger.getLogger("InventoryProxy");

	/**
	 * when InventoryProxy object is instantiated, it deserializes memento from disk
	 * to restore memento object and replay the deserialized commands on restored
	 * memento object to restore inventory state
	 * 
	 * @param inventoryManager
	 */
	public InventoryProxy(InventoryManager inventoryManager) {
		this.inventoryManager = inventoryManager;
		restoreState(restoreMementoFromDisk());
		replayCommands();
	}

	/**
	 * This method performs additional functionality of creating and executing
	 * command to perform addBook operation on Inventory Manager class, if add is
	 * successful then this method writes command to file by using saveCommandToDisk
	 * method
	 * 
	 * @return true/false based on the return value of addBook method in inventory
	 *         manager class
	 */
	@Override
	public boolean addBook(Book book) {
		command = new AddBook(book);
		boolean isOperationSuccessful = command.execute(inventoryManager);
		if (isOperationSuccessful) {
			saveCommandToDisk();
			numberOfCommands++;
		}
		if (numberOfCommands == maxNumberToSave) {
			saveMementoToDisk(createMemento());
			commandFile.delete();
			numberOfCommands = 0;
		}
		return isOperationSuccessful;
	}

	/**
	 * This method performs additional functionality of creating and executing
	 * command to perform sellBook operation on Inventory Manager class, if sell
	 * book is success then this method writes command to file by using
	 * saveCommandToDisk method
	 * 
	 * @return true/false based on the return value of sellBook method in inventory
	 *         manager class
	 */
	@Override
	public boolean sellBook(String bookName) {
		command = new SellBook(bookName);
		boolean isOperationSuccessful = command.execute(inventoryManager);
		if (isOperationSuccessful) {
			saveCommandToDisk();
			numberOfCommands++;
		}
		if (numberOfCommands == maxNumberToSave) {
			saveMementoToDisk(createMemento());
			commandFile.delete();
			numberOfCommands = 0;
		}
		return isOperationSuccessful;
	}

	/**
	 * This method performs additional functionality of creating and executing
	 * command to perform addCopy operation on Inventory Manager class, if the add
	 * copy is success then this method writes command to file by using
	 * saveCommandToDisk method
	 * 
	 * @return true/false based on the return value of addCopy method in inventory
	 *         manager class
	 */
	@Override
	public boolean addCopy(String bookName, int numberOfCopies) {
		command = new AddCopy(bookName, numberOfCopies);
		boolean isOperationSuccessful = command.execute(inventoryManager);
		if (isOperationSuccessful) {
			saveCommandToDisk();
			numberOfCommands++;
		}
		if (numberOfCommands == maxNumberToSave) {
			saveMementoToDisk(createMemento());
			commandFile.delete();
			numberOfCommands = 0;
		}
		return isOperationSuccessful;
	}

	/**
	 * This method performs additional functionality of creating and executing
	 * command to perform changePrice operation on Inventory Manager class, if the
	 * changePrice is success then this method writes command to file by using
	 * saveCommandToDisk method
	 * 
	 * @return true/false based on the return value of changePrice method in
	 *         inventory manager class
	 */
	@Override
	public boolean changePrice(String bookName, double newPrice) {
		command = new ChangePrice(bookName, newPrice);
		boolean isOperationSuccessful = command.execute(inventoryManager);
		if (isOperationSuccessful) {
			saveCommandToDisk();
			numberOfCommands++;
		}
		if (numberOfCommands == maxNumberToSave) {
			saveMementoToDisk(createMemento());
			commandFile.delete();
			numberOfCommands = 0;
		}
		return isOperationSuccessful;
	}

	@Override
	public Double findPriceByName(String bookName) {
		return inventoryManager.findPriceByName(bookName);
	}

	@Override
	public Double findPriceById(int bookId) {
		return inventoryManager.findPriceById(bookId);
	}

	@Override
	public Integer findQuantityByName(String bookName) {
		return inventoryManager.findQuantityByName(bookName);
	}

	@Override
	public Integer findQuantityById(int bookId) {
		return inventoryManager.findQuantityById(bookId);
	}

	/**
	 * This method calls createMemento method in Inventory Manager class and creates
	 * the Memento with current state
	 * 
	 * @return Memento object with current state
	 */
	@Override
	public Memento createMemento() {
		return inventoryManager.createMemento();
	}

	/**
	 * This method calls restoreMemento method in Inventory Manager class if
	 * previous state of the Memento exists
	 */
	@Override
	public void restoreState(Memento oldState) {
		if (oldState != null) {
			inventoryManager.restoreState(oldState);
		}
	}

	/**
	 * This method serializes the command object to Command file and saves it to the
	 * disk
	 */
	private void saveCommandToDisk() {
		try {
			FileOutputStream commandFileOutStream = new FileOutputStream(commandFile, true);
			ObjectOutputStream commandObjectOutStream = new ObjectOutputStream(commandFileOutStream);
			commandObjectOutStream.writeObject(command);
			commandObjectOutStream.close();
			commandFileOutStream.close();
		} catch (IOException ioException) {
			exceptionLogger.info("Unable to open or write file");
		}
	}

	/**
	 * This method serializes the memento object to InventoryState file and saves it
	 * to the disk
	 */
	private void saveMementoToDisk(Memento state) {
		try {
			FileOutputStream inventoryStateFileOutStream = new FileOutputStream(STATEFILENAME, false);
			ObjectOutputStream stateObjectFileStream = new ObjectOutputStream(inventoryStateFileOutStream);
			stateObjectFileStream.writeObject(state);
			stateObjectFileStream.close();
			inventoryStateFileOutStream.close();
		} catch (IOException ioException) {
			exceptionLogger.info("Unable to open or write file");
		}
	}

	/**
	 * This method deserializes each command object from command file and calls
	 * execute operation of each command
	 */
	private void replayCommands() {
		FileInputStream commandsFileInStream = null;
		if (commandFile.exists()) {
			try {
				commandsFileInStream = new FileInputStream(commandFile);
				while (true) {
					ObjectInputStream commandObjectInStream = new ObjectInputStream(commandsFileInStream);
					Command command = ((Command) commandObjectInStream.readObject());
					command.execute(inventoryManager);
					numberOfCommands++;
				}
			} catch (EOFException e) {
				try {
					commandsFileInStream.close();
				} catch (IOException ioException) {
					exceptionLogger.info("Unable to open/read file or read object");
				}
			} catch (IOException | ClassNotFoundException exception) {
				exceptionLogger.info("Unable to open/read file or read object or class not found");
			}
		}
	}

	/**
	 * This method deserializes the memento object from the disk
	 * 
	 * @return Memento if inventory state file exists otherwise null
	 */
	private Memento restoreMementoFromDisk() {
		Object previousState;
		File inventoryStateFile = new File(STATEFILENAME);
		try {
			if (!inventoryStateFile.exists()) {
				return null;
			}
			FileInputStream inventoryStateFileInStream = new FileInputStream(STATEFILENAME);
			ObjectInputStream stateObjectInStream = new ObjectInputStream(inventoryStateFileInStream);
			previousState = stateObjectInStream.readObject();
			stateObjectInStream.close();
			inventoryStateFileInStream.close();
			return (Memento) previousState;
		} catch (IOException | ClassNotFoundException exception) {
			exceptionLogger.info("Unable to open/read file or read object or class not found");
			return null;
		}
	}
}