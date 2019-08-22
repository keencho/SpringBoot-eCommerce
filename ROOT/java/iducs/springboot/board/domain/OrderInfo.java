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
	private int status;			// 상품평을 한번 작성후 삭제할시 다시 작성할수 없게 상태 지정(0 - 작성전, 1 - 작성후) 리뷰를 작성하면 1로 변경되고, 이후 삭제해도 다시 0으로 되돌아 가지 않음
	private int int_price;		// 가격을 int형으로 변환
	
	public OrderInfo() {}
	public OrderInfo(Order order, Product product, Color color, ClothesSize size, int qty, String price, int status) {
		super();
		this.order = order;
		this.product = product;
		this.color = color;
		this.size = size;
		this.qty = qty;
		this.price = price;
		this.status = status;
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
	public int getInt_price() {
		return int_price;
	}
	public void setInt_price(int int_price) {
		this.int_price = int_price;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
