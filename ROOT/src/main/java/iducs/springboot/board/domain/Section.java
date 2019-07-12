package iducs.springboot.board.domain;

import java.util.List;

public class Section {
	private long no;
	private Category category_no;
	private Division division_no;
	private String name;
	
	public Section() {}
	public Section(Category category, Division division, String name) {
		super();
		this.category_no = category;
		this.division_no = division;
		this.name = name;
	}
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public Category getCategory() {
		return category_no;
	}
	public void setCategory(Category category) {
		this.category_no = category;
	}
	public Division getDivision() {
		return division_no;
	}
	public void setDivision(Division division) {
		this.division_no = division;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
