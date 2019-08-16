package iducs.springboot.board.domain;

import java.util.List;

public class Division {
	private long no;
	private Category category_no;
	private String name;
	private List<Section> section;
	private List<Product> product;
	
	public Division() {}
	public Division(Category category, String name) {
		super();
		this.category_no = category;
		this.name = name;
	}
	public long getNo() {
		return no;
	}
	public Category getCategory() {
		return category_no;
	}
	public void setCategory(Category category) {
		this.category_no = category;
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
