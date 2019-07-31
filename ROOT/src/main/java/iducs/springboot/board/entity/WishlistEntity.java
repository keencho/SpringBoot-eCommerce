package iducs.springboot.board.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import iducs.springboot.board.domain.Wishlist;



@Entity
@Table(name = "wishlist")
public class WishlistEntity {
	@Id
	@Column(name="no")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long no;
	
	@ManyToOne
	@JoinColumn(name = "product_no", nullable=false, unique = false)
	private ProductEntity product;
	
	@ManyToOne
	@JoinColumn(name = "user_no", nullable=false, unique = false)
	private UserEntity user;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
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
	public Wishlist buildDomain() {
		Wishlist wishlist = new Wishlist();
		wishlist.setNo(no);
		wishlist.setProduct_no(product.buildDomain());
		wishlist.setUser_no(user.buildDomain());
		return wishlist;
	}
	public void buildEntity(Wishlist wishlist) {
		no = wishlist.getNo();
		
		ProductEntity productEntity = new ProductEntity();
		productEntity.buildEntity(wishlist.getProduct_no());
		product = productEntity;
		
		UserEntity userEntity = new UserEntity();
		userEntity.buildEntity(wishlist.getUser_no());
		user = userEntity;
		
	}
}

