package iducs.springboot.board.domain;

import java.util.List;

public class Category {
	private long no;
	private String name;
	private String englishName;
	private List<Division> division;
	private List<Section> section;
	private List<Product> product;
	
	public Category() {}
	public Category(String name, String englishName) {
		super();
		this.name = name;
		this.englishName = englishName;
	}
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEnglishName() {
		return englishName;
	}
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	public List<Division> getDivision() {
		return division;
	}
	public void setDivision(List<Division> division) {
		this.division = division;
	}
	public List<Section> getSection() {
		return section;
	}
	public void setSection(List<Section> section) {
		this.section = section;
	}
	public List<Product> getProduct() {
		return product;
	}
	public void setProduct(List<Product> product) {
		this.product = product;
	}

	
}
