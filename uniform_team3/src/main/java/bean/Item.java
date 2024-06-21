package bean;

public class Item {
	private int itemid;
	private String itemName;
	private int price;
	private int stock;
	private String image;

	public Item() {
		this.itemid = 0;
		this.itemName = "";
		this.price = 0;
		this.stock = 0;
		this.image = "";
	}

	public int getItemid() {
		return itemid;
	}

	public void setItemid(int itemid) {
		this.itemid = itemid;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
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
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
