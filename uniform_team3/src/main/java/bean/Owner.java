package bean;

public class Owner {
	private String ownerid;
	private String password;

	public Owner() {
		this.ownerid = null;
		this.password = null;
	}

	public String getOwnerid() {
		return ownerid;
	}

	public void setOwnerid(String ownerid) {
		this.ownerid = ownerid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
