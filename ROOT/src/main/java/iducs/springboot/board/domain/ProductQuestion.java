package iducs.springboot.board.domain;

import java.util.List;

public class ProductQuestion {
	private long no;
	private Product product_no;
	private long personal_no;
	private int type;
	private User user_no;
	private String name;
	private String contents;
	private int status;
	private String regdate;
	
	public ProductQuestion() {}

	public ProductQuestion(Product product_no, long personal_no, int type, User user_no, String name,
			String contents, int status, String regdate) {
		super();
		this.product_no = product_no;
		this.personal_no = personal_no;
		this.type = type;
		this.user_no = user_no;
		this.name = name;
		this.contents = contents;
		this.status = status;
		this.regdate = regdate;
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

	public long getPersonal_no() {
		return personal_no;
	}

	public void setPersonal_no(long personal_no) {
		this.personal_no = personal_no;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public User getUser_no() {
		return user_no;
	}

	public void setUser_no(User user_no) {
		this.user_no = user_no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	
}
