package inventory;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Book implements Serializable {

	private int quantity, uniqueId;
	private double price;
	private String name;

	public Book(String name, double price, int quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public double getPrice() {

		return price;
	}

	public void setPrice(double price) {

		this.price = price;
	}

	public Integer getUniqueId() {

		return uniqueId;
	}

	public void setUniqueId(int uniqueId) {

		this.uniqueId = uniqueId;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public int getQuantity() {

		return quantity;
	}

	public void updateQuantity(int quantity) {

		this.quantity += quantity;
	}

}
