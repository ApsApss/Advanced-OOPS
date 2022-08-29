package inventory;

import java.io.Serializable;
import java.util.HashMap;

/**
 * This is used to save or retreive inventoryManager state
 */
@SuppressWarnings("serial")
class Memento implements Serializable {
	private HashMap<String, Book> state;

	protected Memento() {

	}

	protected Memento(HashMap<String, Book> state) {
		this.state = state;
	}

	protected HashMap<String, Book> getState() {
		return state;
	}
}