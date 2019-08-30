package iducs.springboot.board.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import iducs.springboot.board.domain.Consulting;

@Entity
@Table(name = "consulting")
public class ConsultingEntity {
	@Id
	@Column(name="no")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long no;
	
	@ManyToOne
	@JoinColumn(name = "user_no", nullable=false, unique = false)
	private UserEntity user;
	
	@Column(name = "email",nullable=false, length=50)
	private String email;
	
	@Column(name = "title",nullable=false, length=200)
	private String title;
	
	@Column(name = "contents",nullable=false, length=1500)
	private String contents;
	
	@Column(name = "answer",nullable=true, length=1500)
	private String answer;
	
	@Column(name = "date_q",nullable=false, length=15)
	private String dateq;
	
	@Column(name = "date_a",nullable=true, length=15)
	private String datea;
	
	@Column(name = "attach",nullable=true, length=1024)
	private String attach;
	
	@Column(name = "status",nullable=false, length=5)
	private int status;
	
	@Column(name = "type", nullable=false, length=20)
	private String type;
	
	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
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

	public String getDateq() {
		return dateq;
	}

	public void setDateq(String dateq) {
		this.dateq = dateq;
	}

	public String getDatea() {
		return datea;
	}

	public void setDatea(String datea) {
		this.datea = datea;
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

	public Consulting buildDomain() {
		Consulting consulting = new Consulting();
		consulting.setNo(no);
		consulting.setUser(user.buildDomain());
		consulting.setEmail(email);
		consulting.setTitle(title);
		consulting.setContents(contents);
		consulting.setAttach(attach);
		consulting.setAnswer(answer);
		consulting.setDate_a(datea);
		consulting.setDate_q(dateq);
		consulting.setStatus(status);
		consulting.setType(type);
		return consulting;
	}
	
	public void buildEntity(Consulting consulting) {
		no = consulting.getNo();
		email = consulting.getEmail();
		title = consulting.getTitle();
		contents = consulting.getContents();
		attach = consulting.getAttach();
		answer = consulting.getAnswer();
		datea = consulting.getDate_a();
		dateq = consulting.getDate_q();
		status = consulting.getStatus();
		type = consulting.getType();
		
		UserEntity userEntity = new UserEntity();
		userEntity.buildEntity(consulting.getUser());
		user = userEntity;
	}
	
}

