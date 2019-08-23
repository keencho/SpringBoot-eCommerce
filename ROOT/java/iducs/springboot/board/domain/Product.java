package iducs.springboot.board.domain;

import java.util.List;

public class Product {
	private long no;	// Primaray Key
	private Category category;	// 카테고리 번호
	private Division division;	// 디비전 번호
	private Section section;	// 섹션 번호
	private String name;	// 판매명
	private String modelname;	// 모델명
	private String price; // 원가
	private String discount;
	private String listprice; // 정가
	private String size;	// 존재하는 사이즈
	private String color;	// 표기용 컬러
	private String material;	// 재질
	private String madeby;	// 제조회사
	private String madein;	// 제조원산지
	private String caution;	// 주의사항
	private String date;	// 제조년월
	private String pic1;	//사진
	private String pic2;
	private String pic3;
	private String pic4;
	private String pic5;
	private String explainpic;	// 상품 설명 이미지
	private String regdate;	//상품 등록일
	private String cartSize; // 앞에 cart가 붙으면 장바구니용 변수임
	private String cartColor;
	private String cartSizeNo;
	private String cartColorNo;
	private int cartQty;
	private int cartPrice;
	private int cartNo;
	private double score; // 상품평 평균
	private List<ProductSize> productsize;
	private List<ProductStock> productstock;
	
	public Product() {}
	
	public Product(Category category, Division division, Section section, String name, String modelname,
			String price, String discount, String listprice, String size, String color, String material, String madeby,
			String madein, String caution, String date, String pic1, String pic2, String pic3, String pic4, String pic5,
			String explainpic, String regdate) {
		super();
		this.category = category;
		this.division = division;
		this.section = section;
		this.name = name;
		this.modelname = modelname;
		this.price = price;
		this.discount = discount;
		this.listprice = listprice;
		this.size = size;
		this.color = color;
		this.material = material;
		this.madeby = madeby;
		this.madein = madein;
		this.caution = caution;
		this.date = date;
		this.pic1 = pic1;
		this.pic2 = pic2;
		this.pic3 = pic3;
		this.pic4 = pic4;
		this.pic5 = pic5;
		this.explainpic = explainpic;
		this.regdate = regdate;
	}

	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Division getDivision() {
		return division;
	}

	public void setDivision(Division division) {
		this.division = division;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModelname() {
		return modelname;
	}

	public void setModelname(String modelname) {
		this.modelname = modelname;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getListprice() {
		return listprice;
	}

	public void setListprice(String listprice) {
		this.listprice = listprice;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getMadeby() {
		return madeby;
	}

	public void setMadeby(String madeby) {
		this.madeby = madeby;
	}

	public String getMadein() {
		return madein;
	}

	public void setMadein(String madein) {
		this.madein = madein;
	}

	public String getCaution() {
		return caution;
	}

	public void setCaution(String caution) {
		this.caution = caution;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	public String getPic4() {
		return pic4;
	}

	public void setPic4(String pic4) {
		this.pic4 = pic4;
	}

	public String getPic5() {
		return pic5;
	}

	public void setPic5(String pic5) {
		this.pic5 = pic5;
	}

	public String getExplainpic() {
		return explainpic;
	}

	public void setExplainpic(String explainpic) {
		this.explainpic = explainpic;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public List<ProductSize> getProductsize() {
		return productsize;
	}

	public void setProductsize(List<ProductSize> productsize) {
		this.productsize = productsize;
	}

	public List<ProductStock> getProductstock() {
		return productstock;
	}

	public void setProductstock(List<ProductStock> productstock) {
		this.productstock = productstock;
	}

	public String getCartSize() {
		return cartSize;
	}

	public void setCartSize(String cartSize) {
		this.cartSize = cartSize;
	}

	public String getCartColor() {
		return cartColor;
	}

	public void setCartColor(String cartColor) {
		this.cartColor = cartColor;
	}

	public int getCartQty() {
		return cartQty;
	}

	public void setCartQty(int cartQty) {
		this.cartQty = cartQty;
	}

	public int getCartPrice() {
		return cartPrice;
	}

	public void setCartPrice(int cartPrice) {
		this.cartPrice = cartPrice;
	}

	public int getCartNo() {
		return cartNo;
	}

	public void setCartNo(int cartNo) {
		this.cartNo = cartNo;
	}

	public String getCartSizeNo() {
		return cartSizeNo;
	}

	public void setCartSizeNo(String cartSizeNo) {
		this.cartSizeNo = cartSizeNo;
	}

	public String getCartColorNo() {
		return cartColorNo;
	}

	public void setCartColorNo(String cartColorNo) {
		this.cartColorNo = cartColorNo;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double d) {
		this.score = d;
	}

	
}
