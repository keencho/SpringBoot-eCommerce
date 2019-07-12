package iducs.springboot.board.entity;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import iducs.springboot.board.domain.Category;

@Entity
@Table(name = "category")
public class CategoryEntity {
	@Id
	@Column(name="no")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long no;
	
	@Column(name="name", nullable=false, length=50, unique=true)
	private String name;
	
	@Column(name="english_name", nullable=false, length=50, unique=true)
	private String englishName;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DivisionEntity> division = new ArrayList<DivisionEntity>();
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
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
	public List<DivisionEntity> getDivision() {
		return division;
	}
	public void setDivision(List<DivisionEntity> division) {
		this.division = division;
	}
	public Category buildDomain() {
		Category category = new Category();
		category.setNo(no);
		category.setName(name);
		category.setEnglishName(englishName);
		return  category;
	}
	public void buildEntity(Category category) {
		no = category.getNo();
		name = category.getName();
		englishName = category.getEnglishName();
	}

}
