package inventory;

public interface Inventory {

	public boolean addBook(Book book);

	public boolean sellBook(String bookName);

	public boolean addCopy(String bookName, int numberOfCopies);

	public boolean changePrice(String bookName, double newPrice);

	public Double findPriceByName(String bookName);

	public Double findPriceById(int bookId);

	public Integer findQuantityByName(String bookName);

	public Integer findQuantityById(int bookId);

	public Memento createMemento();

	public void restoreState(Memento oldState);
}
