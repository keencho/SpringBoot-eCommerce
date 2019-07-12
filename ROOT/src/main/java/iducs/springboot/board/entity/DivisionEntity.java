package iducs.springboot.board.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import iducs.springboot.board.domain.Category;
import iducs.springboot.board.domain.Division;



@Entity
@Table(name = "division")
public class DivisionEntity {
	@Id
	@Column(name="no")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long no;
	
	@ManyToOne
	@JoinColumn(name = "category_no", nullable=false, unique = false)
	private CategoryEntity category;
	
	@Column(name = "name",nullable=false, length=50)
	private String name;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}
	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Division buildDomain() {
		Division division = new Division();
		division.setNo(no);
		division.setCategory(category.buildDomain());
		division.setName(name);
		return division;
	}
	public void buildEntity(Division division) {
		no = division.getNo();
		name = division.getName();
		
		CategoryEntity categoryEntity = new CategoryEntity();
		categoryEntity.buildEntity(division.getCategory());
		category = categoryEntity;
	}
}

