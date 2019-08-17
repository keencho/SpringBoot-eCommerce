package iducs.springboot.board.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import iducs.springboot.board.domain.OrderInfo;

@Entity
@Table(name = "order_productinfo")
public class OrderInfoEntity {
	@Id
	@Column(name="no")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long no;
	
	@ManyToOne
	@JoinColumn(name = "order_no", nullable=true, unique = false)
	private OrderEntity order;
	
	@ManyToOne
	@JoinColumn(name = "product_no", nullable=true, unique = false)
	private ProductEntity product;
	
	@ManyToOne
	@JoinColumn(name = "color_no", nullable=true, unique = false)
	private ColorEntity color;
	
	@ManyToOne
	@JoinColumn(name = "size_no", nullable=true, unique = false)
	private ClothesSizeEntity size;
	
	@Column(name="qty", nullable=false, length=5, unique=false)
	private int qty;
	
	@Column(name="price", nullable=true, length=32, unique=false)
	private String price;

	

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public OrderEntity getOrder() {
		return order;
	}

	public void setOrder(OrderEntity order) {
		this.order = order;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	public ColorEntity getColor() {
		return color;
	}

	public void setColor(ColorEntity color) {
		this.color = color;
	}

	public ClothesSizeEntity getSize() {
		return size;
	}

	public void setSize(ClothesSizeEntity size) {
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

	public OrderInfo buildDomain() {
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setNo(no);
		orderInfo.setOrder(order.buildDomain());
		orderInfo.setProduct(product.buildDomain());
		orderInfo.setColor(color.buildDomain());
		orderInfo.setSize(size.buildDomain());
		orderInfo.setQty(qty);
		orderInfo.setPrice(price);
		
		return orderInfo;
	}
	
	public void buildEntity(OrderInfo orderInfo) {
		no = orderInfo.getNo();
		
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.buildEntity(orderInfo.getOrder());
		order = orderEntity;
		
		ProductEntity productEntity = new ProductEntity();
		productEntity.buildEntity(orderInfo.getProduct());
		product = productEntity;
		
		ColorEntity colorEntity = new ColorEntity();
		colorEntity .buildEntity(orderInfo.getColor());
		color = colorEntity ;
		
		ClothesSizeEntity clothesSizeEntity = new ClothesSizeEntity();
		clothesSizeEntity .buildEntity(orderInfo.getSize());
		size = clothesSizeEntity ;
		
		qty = orderInfo.getQty();
		price = orderInfo.getPrice();
	}

}
