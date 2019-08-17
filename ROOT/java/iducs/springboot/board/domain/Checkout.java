package iducs.springboot.board.domain;

public class Checkout {
	private Product no;	// Primaray Key
	private String size;
	private String color;
	private long colorNo;
	private long sizeNo;
	private long price;
	private long qty;
	
	public Product getNo() {
		return no;
	}
	public void setNo(Product no) {
		this.no = no;
	}
	
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public long getQty() {
		return qty;
	}
	public void setQty(long qty) {
		this.qty = qty;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public long getColorNo() {
		return colorNo;
	}
	public void setColorNo(long colorNo) {
		this.colorNo = colorNo;
	}
	public long getSizeNo() {
		return sizeNo;
	}
	public void setSizeNo(long sizeNo) {
		this.sizeNo = sizeNo;
	}
	
}
