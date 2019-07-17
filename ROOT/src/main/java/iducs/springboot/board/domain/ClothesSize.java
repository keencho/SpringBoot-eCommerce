package iducs.springboot.board.domain;

import java.util.List;

public class ClothesSize {
	private long no;
	private String name;

	
	public ClothesSize() {}
	public ClothesSize(String name) {
		super();
		this.name = name;
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
	
	
}
