package iducs.springboot.board.domain;

public class UserAddress {
	private long no; // primary key
	private User user_no;
	private String zipcode;
	private String address;
	private String detailaddress;
	private String reference;
	private int basic;
	
	public UserAddress() {}

	public UserAddress(User user_no, String zipcode, String address, String detailaddress, String reference, int basic) {
		super();
		this.user_no = user_no;
		this.zipcode = zipcode;
		this.address = address;
		this.detailaddress = detailaddress;
		this.reference = reference;
		this.basic = basic;
	}

	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public User getUser_no() {
		return user_no;
	}

	public void setUser_no(User user_no) {
		this.user_no = user_no;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDetailaddress() {
		return detailaddress;
	}

	public void setDetailaddress(String detailaddress) {
		this.detailaddress = detailaddress;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public int getBasic() {
		return basic;
	}

	public void setBasic(int basic) {
		this.basic = basic;
	}
	
	
}
