package iducs.springboot.board.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import iducs.springboot.board.domain.ClothesSize;
import iducs.springboot.board.domain.ProductInfo;

@Entity
@Table(name = "productinfo")
public class ProductInfoEntity {
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
	
	@ManyToOne
	@JoinColumn(name = "section_no", nullable=false, unique = false)
	private SectionEntity section;
	
	@Column(name="name", nullable=false, length=100, unique=true)
	private String name;
	
	@Column(name="size1", nullable=false, length=50, unique=false)
	private String size1;
	
	@Column(name="size2", nullable=true, length=50, unique=false)
	private String size2;
	
	@Column(name="size3", nullable=true, length=50, unique=false)
	private String size3;
	
	@Column(name="size4", nullable=true, length=50, unique=false)
	private String size4;
	
	@Column(name="material", nullable=false, length=50)
	private String material;
	
	@Column(name="color", nullable=false, length=50)
	private String color;
	
	@Column(name="madeby", nullable=false, length=30)
	private String madeby;
	
	@Column(name="madein", nullable=false, length=50)
	private String madein;
	
	@Column(name="caution", nullable=false, length=100)
	private String caution;
	
	@Column(name="date", nullable=false, length=30)
	private String date;
	
	@Column(name="pic1", nullable=false, length=4096)
	private String pic1;
	
	@Column(name="pic2", nullable=true, length=4096)
	private String pic2;
	
	@Column(name="pic3", nullable=true, length=4096)
	private String pic3;
	
	@Column(name="pic4", nullable=true, length=4096)
	private String pic4;
	
	@Column(name="pic5", nullable=true, length=4096)
	private String pic5;
	
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

	public SectionEntity getSection() {
		return section;
	}

	public void setSection(SectionEntity section) {
		this.section = section;
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

	public ProductInfo buildDomain() {
		ProductInfo productinfo = new ProductInfo();
		productinfo.setNo(no);
		productinfo.setCategory(category.buildDomain());
		productinfo.setDivision(division.buildDomain());
		productinfo.setSection(section.buildDomain());
		productinfo.setName(name);
		productinfo.setSize1(size1);
		productinfo.setSize2(size2);
		productinfo.setSize3(size3);
		productinfo.setSize4(size4);
		productinfo.setMaterial(material);
		productinfo.setColor(color);
		productinfo.setMadeby(madeby);
		productinfo.setMadein(madein);
		productinfo.setCaution(caution);
		productinfo.setDate(date);
		productinfo.setPic1(pic1);
		productinfo.setPic2(pic2);
		productinfo.setPic3(pic3);
		productinfo.setPic4(pic4);
		productinfo.setPic5(pic5);
		return productinfo;
	}
	
	public void buildEntity(ProductInfo productinfo) {
		no = productinfo.getNo(); 
		
		CategoryEntity categoryEntity = new CategoryEntity();
		categoryEntity.buildEntity(productinfo.getCategory());
		category = categoryEntity;

		DivisionEntity divisionEntity = new DivisionEntity();
		divisionEntity.buildEntity(productinfo.getDivision());
		division = divisionEntity;
		
		name = productinfo.getName();
		
		
		size1 = productinfo.getSize1();
		size2 = productinfo.getSize2();
		size3 = productinfo.getSize3();
		size4 = productinfo.getSize4();
		material = productinfo.getMaterial();
		color = productinfo.getColor();
		madeby = productinfo.getMadeby();
		madein = productinfo.getMadein();
		caution = productinfo.getCaution();
		date = productinfo.getDate();
		pic1 = productinfo.getPic1();
		pic2 = productinfo.getPic2();
		pic3 = productinfo.getPic3();
		pic4 = productinfo.getPic4();
		pic5 = productinfo.getPic5();
	}

}
