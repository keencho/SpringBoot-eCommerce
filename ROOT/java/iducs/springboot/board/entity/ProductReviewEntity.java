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
import iducs.springboot.board.domain.ProductQuestion;
import iducs.springboot.board.domain.ProductReview;

@Entity
@Table(name = "product_review")
public class ProductReviewEntity {
	@Id
	@Column(name="no")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long no;
	
	@Column(name = "score",nullable=false, length=5)
	private int score;
	
	@ManyToOne
	@JoinColumn(name = "product_no", nullable=false, unique = false)
	private ProductEntity product;
	
	@ManyToOne
	@JoinColumn(name = "user_no", nullable=false, unique = false)
	private UserEntity user;
	
	@ManyToOne
	@JoinColumn(name = "size_no", nullable=false, unique = false)
	private ClothesSizeEntity size;
	
	@ManyToOne
	@JoinColumn(name = "color_no", nullable=false, unique = false)
	private ColorEntity color;
	
	@ManyToOne
	@JoinColumn(name = "info_no", nullable=false, unique = false)
	private OrderInfoEntity info;
	
	@Column(name = "contents", nullable=false, length=2048)
	private String contents;
	
	@Column(name="pic1", nullable=true, length=1024)
	private String pic1;
	
	@Column(name="pic2", nullable=true, length=1024)
	private String pic2;
	
	@Column(name="pic3", nullable=true, length=1024)
	private String pic3;
	
	@Column(name="date", nullable=false, length=30)
	private String date;
	
	@Column(name = "type",nullable=false, length=5)
	private int type;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public ClothesSizeEntity getSize() {
		return size;
	}

	public void setSize(ClothesSizeEntity size) {
		this.size = size;
	}

	public ColorEntity getColor() {
		return color;
	}

	public void setColor(ColorEntity color) {
		this.color = color;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
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

	

	public OrderInfoEntity getInfo() {
		return info;
	}

	public void setInfo(OrderInfoEntity info) {
		this.info = info;
	}

	public ProductReview buildDomain() {
		ProductReview review = new ProductReview();
		review.setNo(no);
		review.setScore(score);
		review.setProduct(product.buildDomain());
		review.setUser(user.buildDomain());
		review.setSize(size.buildDomain());
		review.setColor(color.buildDomain());
		review.setInfo(info.buildDomain());
		review.setContents(contents);
		review.setPic1(pic1);
		review.setPic2(pic2);
		review.setPic3(pic3);
		review.setType(type);
		review.setDate(date);
		return review;
	}
	
	public void buildEntity(ProductReview review) {
		no = review.getNo();
		score = review.getScore();
		contents = review.getContents();
		pic1 = review.getPic1();
		pic2 = review.getPic2();
		pic3 = review.getPic3();
		type = review.getType();
		date = review.getDate();
		
		ProductEntity productEntity = new ProductEntity();
		productEntity.buildEntity(review.getProduct());
		product = productEntity;
		
		UserEntity userEntity = new UserEntity();
		userEntity.buildEntity(review.getUser());
		user = userEntity;
		
		ClothesSizeEntity sizeEntity = new ClothesSizeEntity();
		sizeEntity.buildEntity(review.getSize());
		size = sizeEntity;
		
		ColorEntity colorEntity = new ColorEntity();
		colorEntity.buildEntity(review.getColor());
		color = colorEntity;
		
		OrderInfoEntity infoEntity = new OrderInfoEntity();
		infoEntity.buildEntity(review.getInfo());
		info = infoEntity;
	}
	
}

