package iducs.springboot.board.domain;

import java.util.List;

public class OrderInfo {
	private long no;			// 기본키
	private Order order;		// 주문테이블 정보 가져오기
	private Product product;	// 상품 정보
	private Color color;		// 옵션 - 색상
	private ClothesSize size;	// 옵션 - 사이즈
	private int qty;			// 수량
	private String price;		// 가격 변동이 있으므로 결제 순간의 가격을 기준으로 한다.
	
	public OrderInfo() {}
	public OrderInfo(Order order, Product product, Color color, ClothesSize size, int qty, String price) {
		super();
		this.order = order;
		this.product = product;
		this.color = color;
		this.size = size;
		this.qty = qty;
		this.price = price;
	}
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public ClothesSize getSize() {
		return size;
	}
	public void setSize(ClothesSize size) {
		this.size = size;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
}
