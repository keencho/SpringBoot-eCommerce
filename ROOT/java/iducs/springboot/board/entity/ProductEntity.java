package iducs.springboot.board.entity;

import java.util.ArrayList;
import java.util.List;

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

import iducs.springboot.board.domain.Product;

@Entity
@Table(name = "product")
public class ProductEntity {
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
	
	@Column(name="modelname", nullable=false, length=100, unique=false)
	private String modelname;
	
	@Column(name="price", nullable=false, length=50, unique=false)
	private String price;
	
	@Column(name="discount", nullable=true, length=50, unique=false)
	private String discount;
	
	@Column(name="listprice", nullable=true, length=50, unique=false)
	private String listprice;
	
	@Column(name="size", nullable=false, length=50, unique=false)
	private String size;
	
	@Column(name="material", nullable=false, length=50)
	private String material;
	
	@Column(name="color", nullable=false, length=50)
	private String color;
	
	@Column(name="madeby", nullable=false, length=128)
	private String madeby;
	
	@Column(name="madein", nullable=false, length=50)
	private String madein;
	
	@Column(name="caution", nullable=false, length=1024)
	private String caution;
	
	@Column(name="date", nullable=false, length=30)
	private String date;
	
	@Column(name="pic1", nullable=false, length=1024)
	private String pic1;
	
	@Column(name="pic2", nullable=true, length=1024)
	private String pic2;
	
	@Column(name="pic3", nullable=true, length=1024)
	private String pic3;
	
	@Column(name="pic4", nullable=true, length=1024)
	private String pic4;
	
	@Column(name="pic5", nullable=true, length=1024)
	private String pic5;
	
	@Column(name="explainpic", nullable=true, length=1024)
	private String explainpic;
	
	@Column(name="regdate", nullable=true, length=50)
	private String regdate;
	
	@OneToMany(mappedBy="productNo", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProductSizeEntity> productsize = new ArrayList<ProductSizeEntity>();
	
	@OneToMany(mappedBy="productNo", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProductStockEntity> productstock = new ArrayList<ProductStockEntity>();
	
	
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

	public List<ProductSizeEntity> getProductsize() {
		return productsize;
	}

	public void setProductsize(List<ProductSizeEntity> productsize) {
		this.productsize = productsize;
	}
	

	public List<ProductStockEntity> getProductstock() {
		return productstock;
	}

	public void setProductstock(List<ProductStockEntity> productstock) {
		this.productstock = productstock;
	}

	public Product buildDomain() {
		Product product = new Product();
		product.setNo(no);
		product.setCategory(category.buildDomain());
		product.setDivision(division.buildDomain());
		product.setSection(section.buildDomain());
		product.setName(name);
		product.setModelname(modelname);
		product.setPrice(price);
		product.setDiscount(discount);
		product.setListprice(listprice);
		product.setSize(size);
		product.setMaterial(material);
		product.setColor(color);
		product.setMadeby(madeby);
		product.setMadein(madein);
		product.setCaution(caution);
		product.setDate(date);
		product.setPic1(pic1);
		product.setPic2(pic2);
		product.setPic3(pic3);
		product.setPic4(pic4);
		product.setPic5(pic5);
		product.setExplainpic(explainpic);
		product.setRegdate(regdate);
		return product;
	}
	
	public void buildEntity(Product product) {
		no = product.getNo(); 
		
		CategoryEntity categoryEntity = new CategoryEntity();
		categoryEntity.buildEntity(product.getCategory());
		category = categoryEntity;

		DivisionEntity divisionEntity = new DivisionEntity();
		divisionEntity.buildEntity(product.getDivision());
		division = divisionEntity;
		
		SectionEntity sectionEntity = new SectionEntity();
		sectionEntity.buildEntity(product.getSection());
		section = sectionEntity;
		
		name = product.getName();
		modelname = product.getModelname();
		price = product.getPrice();
		discount = product.getDiscount();
		listprice = product.getListprice();
		size = product.getSize();
		material = product.getMaterial();
		color = product.getColor();
		madeby = product.getMadeby();
		madein = product.getMadein();
		caution = product.getCaution();
		date = product.getDate();
		pic1 = product.getPic1();
		pic2 = product.getPic2();
		pic3 = product.getPic3();
		pic4 = product.getPic4();
		pic5 = product.getPic5();
		explainpic = product.getExplainpic();
		regdate = product.getRegdate();
	}

}
