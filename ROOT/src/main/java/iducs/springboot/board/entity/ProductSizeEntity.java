package iducs.springboot.board.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import iducs.springboot.board.domain.Category;
import iducs.springboot.board.domain.Division;
import iducs.springboot.board.domain.ProductSize;

@Entity
@Table(name = "product_size")
public class ProductSizeEntity {
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
	
	@Column(name = "top_length",nullable=true, length=20)
	private String top_length;
	
	@Column(name = "top_shoulder",nullable=true, length=20)
	private String top_shoulder;
	
	@Column(name = "top_sleeve",nullable=true, length=20)
	private String top_sleeve;
	
	@Column(name = "top_chest",nullable=true, length=20)
	private String top_chest;
	
	@Column(name = "bot_length",nullable=true, length=20)
	private String bot_length;
	
	@Column(name = "bot_under",nullable=true, length=20)
	private String bot_under;
	
	@Column(name = "bot_thigh",nullable=true, length=20)
	private String bot_thigh;
	
	@Column(name = "bot_hip",nullable=true, length=20)
	private String bot_hip;
	
	@Column(name = "bot_rise",nullable=true, length=20)
	private String bot_rise;
	
	@Column(name = "bot_waist",nullable=true, length=20)
	private String bot_waist;
	
	public ProductSize buildDomain() {
		ProductSize productsize = new ProductSize();
		productsize.setNo(no);
		productsize.setProduct_no(productNo.buildDomain());
		productsize.setSize_no(sizeNo.buildDomain());
		productsize.setTop_chest(top_chest);
		productsize.setTop_shoulder(top_shoulder);
		productsize.setTop_sleeve(top_sleeve);
		productsize.setTop_length(top_length);
		productsize.setBot_hip(bot_hip);
		productsize.setBot_length(bot_length);
		productsize.setBot_rise(bot_rise);
		productsize.setBot_thigh(bot_thigh);
		productsize.setBot_under(bot_under);
		productsize.setBot_waist(bot_waist);
		return productsize;
	}
	public void buildEntity(ProductSize productsize) {
		no = productsize.getNo();
		
		ProductEntity productEntity = new ProductEntity();
		productEntity.buildEntity(productsize.getProduct_no());
		productNo = productEntity;
		
		ClothesSizeEntity clothessizeEntity = new ClothesSizeEntity();
		clothessizeEntity.buildEntity(productsize.getSize_no());
		sizeNo = clothessizeEntity;
		
		top_chest = productsize.getTop_chest();
		top_shoulder = productsize.getTop_shoulder();
		top_sleeve = productsize.getTop_sleeve();
		top_length = productsize.getTop_length();
		
		bot_length = productsize.getBot_length();
		bot_hip = productsize.getBot_hip();
		bot_rise = productsize.getBot_rise();
		bot_thigh = productsize.getBot_thigh();
		bot_under = productsize.getBot_under();
		bot_waist = productsize.getBot_waist();
	}
}

