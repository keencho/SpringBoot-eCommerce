package iducs.springboot.board.domain;

import java.util.List;

public class ProductReview {
	private long no;
	private int score;
	private Product product;
	private User user;
	private ClothesSize size;
	private Color color;
	private String contents;
	private OrderInfo info;
	private String pic1;
	private String pic2;
	private String pic3;
	private String date;
	private int type;
	private String contents_split;	// 리뷰 내용을 특정 사이즈로 잘라 저장핧 변수
	
	public ProductReview() {}

	public ProductReview(int score, Product product, User user, ClothesSize size, Color color, String contents,
			OrderInfo info, String pic1, String pic2, String pic3, String date, int type) {
		super();
		this.score = score;
		this.product = product;
		this.user = user;
		this.size = size;
		this.color = color;
		this.contents = contents;
		this.info = info;
		this.pic1 = pic1;
		this.pic2 = pic2;
		this.pic3 = pic3;
		this.date = date;
		this.type = type;
	}

	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ClothesSize getSize() {
		return size;
	}

	public void setSize(ClothesSize size) {
		this.size = size;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public OrderInfo getInfo() {
		return info;
	}

	public void setInfo(OrderInfo info) {
		this.info = info;
	}

	public String getPic1() {
		return pic1;
	}

	public void setPic1(String pic1) {
		this.pic1 = pic1;
	}

	public String getPic2() {
		return pic2;
	}

	public void setPic2(String pic2) {
		this.pic2 = pic2;
	}

	public String getPic3() {
		return pic3;
	}

	public void setPic3(String pic3) {
		this.pic3 = pic3;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getContents_split() {
		return contents_split;
	}

	public void setContents_split(String contents_split) {
		this.contents_split = contents_split;
	}
	
	
}
