package iducs.springboot.board.domain;

public class ProductInfo {
	private long no;
	private Category category;
	private Division division;
	private Section section;
	private String name;
	private ClothesSize size;
	private String size1;
	private String size2;
	private String size3;
	private String size4;
	private String material;
	private String color; // 여기에서의 색상은 실제 판매용이 아닌 사용자 화면 출력용
	private String madeby;
	private String madein;
	private String caution;
	private String date;
	private String pic1;
	private String pic2;
	private String pic3;
	private String pic4;
	private String pic5;
	
	public ProductInfo() {}
	public ProductInfo(long no, Category category, Division division, Section section, String name, ClothesSize size,
			String size1, String size2, String size3, String size4, String material, String color, String madeby,
			String madein, String caution, String date, String pic1, String pic2, String pic3, String pic4,
			String pic5) {
		super();
		this.no = no;
		this.category = category;
		this.division = division;
		this.section = section;
		this.name = name;
		this.size = size;
		this.size1 = size1;
		this.size2 = size2;
		this.size3 = size3;
		this.size4 = size4;
		this.material = material;
		this.color = color;
		this.madeby = madeby;
		this.madein = madein;
		this.caution = caution;
		this.date = date;
		this.pic1 = pic1;
		this.pic2 = pic2;
		this.pic3 = pic3;
		this.pic4 = pic4;
		this.pic5 = pic5;
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
	public ClothesSize getSize() {
		return size;
	}
	public void setSize(ClothesSize size) {
		this.size = size;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSize1() {
		return size1;
	}
	public void setSize1(String size1) {
		this.size1 = size1;
	}
	public String getSize2() {
		return size2;
	}
	public void setSize2(String size2) {
		this.size2 = size2;
	}
	public String getSize3() {
		return size3;
	}
	public void setSize3(String size3) {
		this.size3 = size3;
	}
	public String getSize4() {
		return size4;
	}
	public void setSize4(String size4) {
		this.size4 = size4;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
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
	
}
