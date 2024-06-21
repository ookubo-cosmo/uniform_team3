package bean;

public class Item {
	private int itemid;
	private int price;
	private int stock;

	public Item() {
		this.itemid = 0;
		this.price = 0;
		this.stock = 0;
	}

	public int getItemid() {
		return itemid;
	}

	public void setItemid(int itemid) {
		this.itemid = itemid;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
}
