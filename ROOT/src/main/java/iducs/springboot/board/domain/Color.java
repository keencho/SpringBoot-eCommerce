package iducs.springboot.board.domain;

public class Color {
	private long no;
	private String name;
	
	public Color() {}
	public Color(String name) {
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
