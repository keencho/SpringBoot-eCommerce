package iducs.springboot.board.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import iducs.springboot.board.domain.UserAddress;

@Entity
@Table(name = "user_address")
public class UserAddressEntity {
	@Id
	@Column(name="no")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long no;
	
	@ManyToOne
	@JoinColumn(name = "user_no", nullable=false, unique = false)
	private UserEntity user;
	
	private String zipcode;
	private String address;
	private String detailaddress;
	private String reference;
	private int basic;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
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
	
	public int getBasic() {
		return basic;
	}
	public void setBasic(int basic) {
		this.basic = basic;
	}
	public UserAddress buildDomain() {
		UserAddress userAddress = new UserAddress();
		userAddress.setNo(no);
		userAddress.setUser_no(user.buildDomain());
		userAddress.setZipcode(zipcode);
		userAddress.setAddress(address);
		userAddress.setDetailaddress(detailaddress);
		userAddress.setReference(reference);
		userAddress.setBasic(basic);
		return userAddress;
	}
	public void buildEntity(UserAddress userAddress) {
		no = userAddress.getNo();
		zipcode = userAddress.getZipcode();
		address = userAddress.getAddress();
		detailaddress = userAddress.getDetailaddress();
		reference = userAddress.getReference();
		basic = userAddress.getBasic();
		
		UserEntity userEntity = new UserEntity();
		userEntity.buildEntity(userAddress.getUser_no());
		user = userEntity;
	}
}

