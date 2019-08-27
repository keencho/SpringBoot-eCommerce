package iducs.springboot.board.domain;

public class Notice {
	private long no;			// primary key
	private String title;		// 공지사항 제목 
	private String contents;	// 공지사항 내용
	private String date;		// 등록일
	private long views;			// 조회수
	private String attach;		// 첨부파일
	
	public Notice() {}
	public Notice(String title, String contents, String date, long views, String attach) {
		super();
		this.title = title;
		this.contents = contents;
		this.date = date;
		this.views = views;
		this.attach = attach;
	}
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public long getViews() {
		return views;
	}
	public void setViews(long views) {
		this.views = views;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	
	
}
