package iducs.springboot.board.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import iducs.springboot.board.domain.Notice;



@Entity
@Table(name = "notice")
public class NoticeEntity {
	@Id
	@Column(name="no")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long no;
	
	@Column(name = "title",nullable=false, length=100)
	private String title;
	
	@Column(name = "contents",nullable=false, length=2048)
	private String contents;
	
	@Column(name = "date",nullable=false, length=15)
	private String date;
	
	@Column(name = "views",nullable=false, length=10)
	private Long views;
	
	@Column(name = "attach",nullable=true, length=2048)
	private String attach;
	
	@Column(name = "attach_original",nullable=true, length=100)
	private String attach_original;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
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
	public Long getViews() {
		return views;
	}
	public void setViews(Long views) {
		this.views = views;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	
	public String getAttach_original() {
		return attach_original;
	}
	public void setAttach_original(String attach_original) {
		this.attach_original = attach_original;
	}
	public Notice buildDomain() {
		Notice notice = new Notice();
		notice.setNo(no);
		notice.setTitle(title);
		notice.setContents(contents);
		notice.setDate(date);
		notice.setViews(views);
		notice.setAttach(attach);
		notice.setAttach_original(attach_original);
		return notice;
	}
	public void buildEntity(Notice notice) {
		no = notice.getNo();
		title = notice.getTitle();
		contents = notice.getContents();
		date = notice.getDate();
		views = notice.getViews();
		attach = notice.getAttach();
		attach_original = notice.getAttach_original();
	}
}

