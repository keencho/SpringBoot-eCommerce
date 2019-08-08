package iducs.springboot.board.domain;

public class Wishlist {
	private long no;
	private Product product_no;
	private User user_no;
	
	public Wishlist() {}

	public Wishlist(Product product_no, User user_no) {
		super();
		this.product_no = product_no;
		this.user_no = user_no;
	}

	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public Product getProduct_no() {
		return product_no;
	}

	public void setProduct_no(Product product_no) {
		this.product_no = product_no;
	}

	public User getUser_no() {
		return user_no;
	}

	public void setUser_no(User user_no) {
		this.user_no = user_no;
	}
	
	
}
