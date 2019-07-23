package iducs.springboot.board.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import iducs.springboot.board.domain.ClothesSize;
@Entity
@Table(name = "size")
public class ClothesSizeEntity {
	@Id
	@Column(name="no")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long no;
	
	@Column(name="name", nullable=false, length=10, unique=true)
	private String name;

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

	public ClothesSize buildDomain() {
		ClothesSize clothessize = new ClothesSize();
		clothessize.setNo(no);
		clothessize.setName(name);
		return clothessize;
	}
	
	public void buildEntity(ClothesSize clothessize) {
		no = clothessize.getNo();
		name = clothessize.getName();
	}
}
