package iducs.springboot.board.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import iducs.springboot.board.domain.Category;
import iducs.springboot.board.domain.Division;
import iducs.springboot.board.domain.Section;



@Entity
@Table(name = "section")
public class SectionEntity {
	@Id
	@Column(name="no")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long no;
	
	@ManyToOne
	@JoinColumn(name = "category_no", nullable=false, unique = false)
	private CategoryEntity category;
	
	@ManyToOne
	@JoinColumn(name = "division_no", nullable=false, unique = false)
	private DivisionEntity division;
	
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
	public DivisionEntity getDivision() {
		return division;
	}
	public void setDivision(DivisionEntity division) {
		this.division = division;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Section buildDomain() {
		Section section= new Section();
		section.setNo(no);
		section.setCategory(category.buildDomain());
		section.setDivision(division.buildDomain());
		section.setName(name);
		return section;
	}
	public void buildEntity(Section section) {
		no = section.getNo();
		name = section.getName();
		
		CategoryEntity categoryEntity = new CategoryEntity();
		categoryEntity.buildEntity(section.getCategory());
		category = categoryEntity;

		DivisionEntity divisionEntity = new DivisionEntity();
		divisionEntity.buildEntity(section.getDivision());
		division = divisionEntity;
	}
}

