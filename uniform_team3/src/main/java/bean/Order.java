package bean;

import java.sql.Timestamp;

public class Order {
	
	private int orderid;
	private String name;
	private String address;
	private String email;
	private int itemid;
	private int quantity;
	private Timestamp order_date;
	private String note;
	private int shipment_status;
	private int deposit_status;
	
	public Order() {
		orderid = 0;
		name = null;
		address = null;
		email = null;
		itemid = 0;
		quantity = 0;
		order_date = null;
		note = null;
		shipment_status = 0;
		deposit_status = 0;
	}

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getItemid() {
		return itemid;
	}

	public void setItemid(int itemid) {
		this.itemid = itemid;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Timestamp getOrder_date() {
		return order_date;
	}

	public void setOrder_date(Timestamp order_date) {
		this.order_date = order_date;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getShipment_status() {
		return shipment_status;
	}

	public void setShipment_status(int shipment_status) {
		this.shipment_status = shipment_status;
	}

	public int getDeposit_status() {
		return deposit_status;
	}

	public void setDeposit_status(int deposit_status) {
		this.deposit_status = deposit_status;
	}

}
