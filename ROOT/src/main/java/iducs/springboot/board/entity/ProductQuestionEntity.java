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
import iducs.springboot.board.domain.ProductQuestion;
import iducs.springboot.board.domain.Section;



@Entity
@Table(name = "productquestion")
public class ProductQuestionEntity {
	@Id
	@Column(name="no")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long no;
	
	@ManyToOne
	@JoinColumn(name = "product_no", nullable=false, unique = false)
	private ProductEntity product;
	
	@Column(name = "personal_no",nullable=false, length=10)
	private Long personal_no;
	
	@Column(name = "type",nullable=false, length=4)
	private int type;
	
	@ManyToOne
	@JoinColumn(name = "user_no", nullable=false, unique = false)
	private UserEntity user;
	
	@Column(name = "name",nullable=false, length=60)
	private String name;
	
	@Column(name = "contents",nullable=false, length=512)
	private String contents;
	
	@Column(name = "status",nullable=false, length=10)
	private int status;
	
	@Column(name = "regdate",nullable=false, length=128)
	private String regdate;
	
	@Column(name = "answer",nullable=true, length=512)
	private String answer;

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

	public Long getPersonal_no() {
		return personal_no;
	}

	public void setPersonal_no(Long personal_no) {
		this.personal_no = personal_no;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public ProductQuestion buildDomain() {
		ProductQuestion productquestion = new ProductQuestion();
		productquestion.setNo(no);
		productquestion.setProduct_no(product.buildDomain());
		productquestion.setUser_no(user.buildDomain());
		productquestion.setPersonal_no(personal_no);
		productquestion.setType(type);
		productquestion.setName(name);
		productquestion.setContents(contents);
		productquestion.setStatus(status);
		productquestion.setRegdate(regdate);
		productquestion.setAnswer(answer);
		return productquestion;
	}
	
	public void buildEntity(ProductQuestion productquestion) {
		no = productquestion.getNo();
		name = productquestion.getName();
		contents = productquestion.getContents();
		personal_no = productquestion.getPersonal_no();
		type = productquestion.getType();
		status = productquestion.getStatus();
		regdate = productquestion.getRegdate();
		answer = productquestion.getAnswer();
		
		ProductEntity productEntity = new ProductEntity();
		productEntity.buildEntity(productquestion.getProduct_no());
		product = productEntity;
		
		UserEntity userEntity = new UserEntity();
		userEntity.buildEntity(productquestion.getUser_no());
		user = userEntity;
	}
	
}

