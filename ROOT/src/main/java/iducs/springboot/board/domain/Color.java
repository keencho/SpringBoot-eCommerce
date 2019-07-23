package iducs.springboot.board.domain;

public class Color {
	private long no;
	private String name;
	private String rgb;
	
	public Color() {}
	public Color(String name, String rgb) {
		super();
		this.name = name;
		this.rgb = rgb;
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
	public String getRgb() {
		return rgb;
	}
	public void setRgb(String rgb) {
		this.rgb = rgb;
	}
	
	
}
