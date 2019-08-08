package iducs.springboot.board.domain;

public class ProductStock {
	private long no;	// primary key
	private Product product_no;	// 상품 번호
	private ClothesSize size_no;	// 사이즈 번호
	private Color color_no;	// 컬러 번호
	private String stock; // 수량
	
	public ProductStock() {}

	public ProductStock(Product product_no, ClothesSize size_no, Color color_no, String stock) {
		super();
		this.product_no = product_no;
		this.size_no = size_no;
		this.color_no = color_no;
		this.stock = stock;
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

	public ClothesSize getSize_no() {
		return size_no;
	}

	public void setSize_no(ClothesSize size_no) {
		this.size_no = size_no;
	}

	public Color getColor_no() {
		return color_no;
	}

	public void setColor_no(Color color_no) {
		this.color_no = color_no;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}
	
	
}
