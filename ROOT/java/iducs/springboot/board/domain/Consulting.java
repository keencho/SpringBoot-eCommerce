package iducs.springboot.board.domain;

public class Consulting {
	private long no;				// primary key
	private User user;
	private String email;
	private String title;
	private String contents;
	private String answer;
	private String date_q;
	private String date_a;
	private String attach;
	private int status;
	private String type;
	
	public Consulting() {}
	
	public Consulting(User user, String email, String title, String contents, String answer, String date_q,
			String date_a, String attach, int status, String type) {
		super();
		this.user = user;
		this.email = email;
		this.title = title;
		this.contents = contents;
		this.answer = answer;
		this.date_q = date_q;
		this.date_a = date_a;
		this.attach = attach;
		this.status = status;
		this.type = type;
	}

	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getDate_q() {
		return date_q;
	}

	public void setDate_q(String date_q) {
		this.date_q = date_q;
	}

	public String getDate_a() {
		return date_a;
	}

	public void setDate_a(String date_a) {
		this.date_a = date_a;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
