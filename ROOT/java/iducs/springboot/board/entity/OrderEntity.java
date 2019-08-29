package iducs.springboot.board.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import iducs.springboot.board.domain.Order;

@Entity
@Table(name = "order_sheet")
public class OrderEntity {
	@Id
	@Column(name="no")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long no;
	
	@Column(name="orderno", nullable=false, length=100, unique=false)
	private String orderno;
	
	@Column(name="order_name", nullable=false, length=64, unique=false)
	private String order_name;
	
	@Column(name="order_phone", nullable=false, length=15, unique=false)
	private String order_phone;
	
	@Column(name="order_address", nullable=false, length=128, unique=false)
	private String order_address;
	
	@Column(name="check_user", nullable=false, length=5, unique=false)
	private int check_user;
	
	@Column(name="order_password", nullable=true, length=32, unique=false)
	private String orderpassword;
	
	@ManyToOne
	@JoinColumn(name = "user_no", nullable=true, unique = false)
	private UserEntity user;
	
	@Column(name="order_message", nullable=true, length=128, unique=false)
	private String order_message;
	
	@Column(name="pay_type", nullable=false, length=5, unique=false)
	private int pay_type;
	
	@Column(name="card_id", nullable=true, length=128, unique=false)
	private String card_id;
	
	@Column(name="card_shopid", nullable=true, length=128, unique=false)
	private String card_shopid;
	
	@Column(name="card_applyno", nullable=true, length=128, unique=false)
	private String card_applyno;
	
	@Column(name="account_bank", nullable=true, length=5, unique=false)
	private int account_bank;
	
	@Column(name="account_name", nullable=true, length=64, unique=false)
	private String account_name;
	
	@Column(name="status", nullable=false, length=5, unique=false)
	private int status;
	
	@Column(name="date", nullable=false, length=64, unique=false)
	private String date;
	
	@Column(name="price", nullable=false, length=64, unique=false)
	private String price;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public String getOrder_name() {
		return order_name;
	}

	public void setOrder_name(String order_name) {
		this.order_name = order_name;
	}

	public String getOrder_phone() {
		return order_phone;
	}

	public void setOrder_phone(String order_phone) {
		this.order_phone = order_phone;
	}

	public String getOrder_address() {
		return order_address;
	}

	public void setOrder_address(String order_address) {
		this.order_address = order_address;
	}

	public int getCheck_user() {
		return check_user;
	}

	public void setCheck_user(int check_user) {
		this.check_user = check_user;
	}

	public String getOrderpassword() {
		return orderpassword;
	}

	public void setOrderpassword(String orderpassword) {
		this.orderpassword = orderpassword;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public String getOrder_message() {
		return order_message;
	}

	public void setOrder_message(String order_message) {
		this.order_message = order_message;
	}

	public void setPay_type(int pay_type) {
		this.pay_type = pay_type;
	}

	public String getCard_id() {
		return card_id;
	}

	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}

	public String getCard_shopid() {
		return card_shopid;
	}

	public void setCard_shopid(String card_shopid) {
		this.card_shopid = card_shopid;
	}

	public String getCard_applyno() {
		return card_applyno;
	}

	public void setCard_applyno(String card_applyno) {
		this.card_applyno = card_applyno;
	}

	public int getAccount_bank() {
		return account_bank;
	}

	public void setAccount_bank(int account_bank) {
		this.account_bank = account_bank;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public int getPay_type() {
		return pay_type;
	}

	public Order buildDomain() {
		Order order = new Order();
		order.setNo(no);
		order.setOrderno(orderno);
		order.setOrder_name(order_name);
		order.setOrder_phone(order_phone);
		order.setOrder_address(order_address);
		order.setCheck_user(check_user);
		order.setOrder_password(orderpassword);
		order.setUser(user.buildDomain());
		order.setOrder_message(order_message);
		order.setPay_type(pay_type);
		order.setCard_id(card_id);
		order.setCard_shopid(card_shopid);
		order.setCard_applyno(card_applyno);
		order.setAccount_bank(account_bank);
		order.setAccount_name(account_name);
		order.setStatus(status);
		order.setDate(date);
		order.setPrice(price);
		return order;
	}
	
	public void buildEntity(Order order) {
		no = order.getNo();
		
		UserEntity userEntity = new  UserEntity();
		userEntity.buildEntity(order.getUser());
		user = userEntity;
		
		orderno = order.getOrderno();
		order_name = order.getOrder_name();
		order_phone = order.getOrder_phone();
		order_address = order.getOrder_address();
		check_user = order.getCheck_user();
		orderpassword = order.getOrder_password();
		order_message = order.getOrder_message();
		pay_type = order.getPay_type();
		card_id = order.getCard_id();
		card_shopid = order.getCard_shopid();
		card_applyno = order.getCard_applyno();
		account_bank = order.getAccount_bank();
		account_name = order.getAccount_name();
		status = order.getStatus();
		date = order.getDate();
		price = order.getPrice();
	}
	
	public void buildEntityNonUser(Order order) {
		no = order.getNo();		
		orderno = order.getOrderno();
		order_name = order.getOrder_name();
		order_phone = order.getOrder_phone();
		order_address = order.getOrder_address();
		check_user = order.getCheck_user();
		orderpassword = order.getOrder_password();
		order_message = order.getOrder_message();
		pay_type = order.getPay_type();
		card_id = order.getCard_id();
		card_shopid = order.getCard_shopid();
		card_applyno = order.getCard_applyno();
		account_bank = order.getAccount_bank();
		account_name = order.getAccount_name();
		status = order.getStatus();
		date = order.getDate();
		price = order.getPrice();
	}

}
