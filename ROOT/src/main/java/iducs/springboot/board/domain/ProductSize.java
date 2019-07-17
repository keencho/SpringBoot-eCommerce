package iducs.springboot.board.domain;

import java.util.List;

public class ProductSize {
	private long no;	// primary key
	private Product product_no;	// 상품 번호
	private ClothesSize size_no;	// 사이즈 번호
	
	private String top_length;	// 상의_총장
	private String top_shoulder;	// 상의_어깨
	private String top_sleeve;	// 상의_소매
	private String top_chest;	// 상의_가슴
	
	private String bot_length;	// 하의_총장
	private String bot_under;	// 하의_밑단
	private String bot_thigh;	// 하의_허벅지
	private String bot_hip;		// 하의_엉덩이
	private String bot_rise;	// 하의_앞밑위
	private String bot_waist;	// 하의_허리둘레
	
	public ProductSize() {}

	public ProductSize(Product product_no, ClothesSize size_no, String top_length, String top_shoulder,
			String top_sleeve, String top_chest, String bot_length, String bot_under, String bot_thigh, String bot_hip,
			String bot_rise, String bot_waist) {
		super();
		this.product_no = product_no;
		this.size_no = size_no;
		this.top_length = top_length;
		this.top_shoulder = top_shoulder;
		this.top_sleeve = top_sleeve;
		this.top_chest = top_chest;
		this.bot_length = bot_length;
		this.bot_under = bot_under;
		this.bot_thigh = bot_thigh;
		this.bot_hip = bot_hip;
		this.bot_rise = bot_rise;
		this.bot_waist = bot_waist;
	}

	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public Product getProduct_no() {
		return product_no;
	}

	public void setProduct_no(Product product_no) {
		this.product_no = product_no;
	}

	public ClothesSize getSize_no() {
		return size_no;
	}

	public void setSize_no(ClothesSize size_no) {
		this.size_no = size_no;
	}

	public String getTop_length() {
		return top_length;
	}

	public void setTop_length(String top_length) {
		this.top_length = top_length;
	}

	public String getTop_shoulder() {
		return top_shoulder;
	}

	public void setTop_shoulder(String top_shoulder) {
		this.top_shoulder = top_shoulder;
	}

	public String getTop_sleeve() {
		return top_sleeve;
	}

	public void setTop_sleeve(String top_sleeve) {
		this.top_sleeve = top_sleeve;
	}

	public String getTop_chest() {
		return top_chest;
	}

	public void setTop_chest(String top_chest) {
		this.top_chest = top_chest;
	}

	public String getBot_length() {
		return bot_length;
	}

	public void setBot_length(String bot_length) {
		this.bot_length = bot_length;
	}

	public String getBot_under() {
		return bot_under;
	}

	public void setBot_under(String bot_under) {
		this.bot_under = bot_under;
	}

	public String getBot_thigh() {
		return bot_thigh;
	}

	public void setBot_thigh(String bot_thigh) {
		this.bot_thigh = bot_thigh;
	}

	public String getBot_hip() {
		return bot_hip;
	}

	public void setBot_hip(String bot_hip) {
		this.bot_hip = bot_hip;
	}

	public String getBot_rise() {
		return bot_rise;
	}

	public void setBot_rise(String bot_rise) {
		this.bot_rise = bot_rise;
	}

	public String getBot_waist() {
		return bot_waist;
	}

	public void setBot_waist(String bot_waist) {
		this.bot_waist = bot_waist;
	}
	
	
	
}
