package iducs.springboot.board.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import iducs.springboot.board.domain.User;

@Entity
@Table(name = "user")
public class UserEntity {
	@Id
	@Column(name="no")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long no; // database에서 sequence number, primary key 역할
	@Column(nullable=false, length=20, unique=true) // null 하용 안함, 유일키
	private String id;	
	@Column(nullable=false)
	private String password;
	private String name;
	private String email;
	private String phone;
	private String zipcode;
	private String address;
	private String detailaddress;
	private String reference;
	private String rank;
	private String joinday;
		
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDetailaddress() {
		return detailaddress;
	}
	public void setDetailaddress(String detailaddress) {
		this.detailaddress = detailaddress;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getJoinday() {
		return joinday;
	}
	public void setJoinday(String joinday) {
		this.joinday = joinday;
	}
	public User buildDomain() {
		User user = new User();
		user.setNo(no);
		user.setId(id);
		user.setPassword(password);
		user.setName(name);
		user.setEmail(email);
		user.setPhone(phone);
		user.setZipcode(zipcode);
		user.setAddress(address);
		user.setDetailaddress(detailaddress);
		user.setReference(reference);
		user.setRank(rank);
		user.setJoinday(joinday);
		return user;
	}	
	public void buildEntity(User user) {
		no = user.getNo();
		id = user.getId();
		password= user.getPassword();
		name = user.getName();
		email = user.getEmail();
		phone = user.getPhone();
		zipcode = user.getZipcode();
		address = user.getAddress();
		detailaddress = user.getDetailaddress();
		reference = user.getReference();
		rank = user.getRank();
		joinday = user.getJoinday();
	}
}