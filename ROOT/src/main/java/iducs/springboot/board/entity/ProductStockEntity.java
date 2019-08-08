package iducs.springboot.board.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import iducs.springboot.board.domain.ProductStock;

@Entity
@Table(name = "product_stock")
public class ProductStockEntity {
	@Id
	@Column(name="no")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long no;
	
	@ManyToOne
	@JoinColumn(name = "product_no", nullable=false, unique = false)
	private ProductEntity productNo;
	
	@ManyToOne
	@JoinColumn(name = "size_no", nullable=false, unique = false)
	private ClothesSizeEntity sizeNo;
	
	@ManyToOne
	@JoinColumn(name = "color_no", nullable=false, unique = false)
	private ColorEntity colorNo;
	
	@Column(name = "stock",nullable=false, length=20)
	private String stock;
	
	public ProductStock buildDomain() {
		ProductStock productstock = new ProductStock();
		productstock.setNo(no);
		productstock.setProduct_no(productNo.buildDomain());
		productstock.setSize_no(sizeNo.buildDomain());
		productstock.setColor_no(colorNo.buildDomain());
		productstock.setStock(stock);
		return productstock;
	}
	public void buildEntity(ProductStock productstock) {
		no = productstock.getNo();
		
		ProductEntity productEntity = new ProductEntity();
		productEntity.buildEntity(productstock.getProduct_no());
		productNo = productEntity;
		
		ClothesSizeEntity clothessizeEntity = new ClothesSizeEntity();
		clothessizeEntity.buildEntity(productstock.getSize_no());
		sizeNo = clothessizeEntity;
		
		ColorEntity colorEntity = new ColorEntity();
		colorEntity.buildEntity(productstock.getColor_no());
		colorNo = colorEntity;
		
		stock = productstock.getStock();
	}
}

