package iducs.springboot.board.domain;

import java.util.List;

public class Order {
	private long no;				// primary key
	private String orderno;			// 주문번호
	private String order_name;		// 주문자명
	private String order_phone;		// 주문자 휴대폰 번호
	private String order_address;	// 주문자 주소
	private int check_user;			// 회원 여부
	private String order_password;	// 비회원일시 비밀번호	,nullable
	private User user;				// 회원일시 회원번호		,nullable
	private String order_message;	// 배송 메시지			,nullable
	private int pay_type;			// 결제 방식
	private String card_id;			// 카드 - 고유 id		,nullable
	private String card_shopid;		// 카드 - 거래 id		,nullable
	private String card_applyno;	// 카드 - 승인번호		,nullable
	private int account_bank;		// 계좌이체 - 은행		,nullable
	private String account_name;	// 계좌이체 - 입금자명	,nullable
	private int status;				// 현재 상태
	private String date;			// 결제 완료일
	private String price;			// 결제 순간의 총 가격
	public Order() {}
	

	public Order(String orderno, String order_name, String order_phone, String order_address, int check_user,
			String order_password, User user, String order_message, int pay_type, String card_id, String card_shopid,
			String card_applyno, int account_bank, String account_name, int status, String date, String price) {
		super();
		this.orderno = orderno;
		this.order_name = order_name;
		this.order_phone = order_phone;
		this.order_address = order_address;
		this.check_user = check_user;
		this.order_password = order_password;
		this.user = user;
		this.order_message = order_message;
		this.pay_type = pay_type;
		this.card_id = card_id;
		this.card_shopid = card_shopid;
		this.card_applyno = card_applyno;
		this.account_bank = account_bank;
		this.account_name = account_name;
		this.status = status;
		this.date = date;
		this.price = price;
	}


	public long getNo() {
		return no;
	}

	public void setNo(long no) {
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

	public String getOrder_password() {
		return order_password;
	}

	public void setOrder_password(String order_password) {
		this.order_password = order_password;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getOrder_message() {
		return order_message;
	}

	public void setOrder_message(String order_message) {
		this.order_message = order_message;
	}

	public int getPay_type() {
		return pay_type;
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
	
	
}
