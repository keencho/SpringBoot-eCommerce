package iducs.springboot.board.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import iducs.springboot.board.domain.User;

@Entity
@Table(name = "user")
public class UserEntity {
	@Id
	@Column(name="no")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long no;
	
	@Column(name="id", nullable=false, length=20, unique=true)
	private String id;
	
	@Column(name="password",nullable=false)
	private String password;
	
	@Column(name="name", nullable=false, length=20, unique=false)
	private String name;
	
	@Column(name="email", nullable=false, length=64, unique=false)
	private String email;
	
	@Column(name="phone", nullable=false, length=64, unique=false)
	private String phone;
	
	@Column(name="ranking", nullable=false, length=5, unique=false)
	private String rank;
	
	@Column(name="joinday", nullable=false, length=64, unique=false)
	private String joinday;
	
	@Column(name="point", nullable=false, length=64, unique=false)
	private String point;
		
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
	
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public User buildDomain() {
		User user = new User();
		user.setNo(no);
		user.setId(id);
		user.setPassword(password);
		user.setName(name);
		user.setEmail(email);
		user.setPhone(phone);
		user.setRank(rank);
		user.setJoinday(joinday);
		user.setPoint(point);
		return user;
	}	
	public void buildEntity(User user) {
		no = user.getNo();
		id = user.getId();
		password= user.getPassword();
		name = user.getName();
		email = user.getEmail();
		phone = user.getPhone();
		rank = user.getRank();
		joinday = user.getJoinday();
		point = user.getPoint();
	}
}