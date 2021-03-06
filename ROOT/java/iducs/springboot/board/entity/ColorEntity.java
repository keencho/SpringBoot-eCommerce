package iducs.springboot.board.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import iducs.springboot.board.domain.Color;

@Entity
@Table(name = "color")
public class ColorEntity {
	@Id
	@Column(name="no")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long no;
	
	@Column(name="name", nullable=false, length=20, unique=true)
	private String name;
	
	@Column(name="rgb", nullable=false, length=50, unique=true)
	private String rgb;

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
	
	public String getRgb() {
		return rgb;
	}

	public void setRgb(String rgb) {
		this.rgb = rgb;
	}

	public Color buildDomain() {
		Color color = new Color();
		color.setNo(no);
		color.setName(name);
		color.setRgb(rgb);
		return color;
	}
	
	public void buildEntity(Color color) {
		no = color.getNo();
		name = color.getName();
		rgb = color.getRgb();
	}
}
