package MemberVO;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class MemberVO {
	private long mem_num;
	private String mem_id;
	private String mem_pw;
	private String mem_name;
	private String mem_email;
	private String mem_phone;
	private String mem_nick;
	private Timestamp mem_regi;
	
	private String comm_star;
	private String rest_name;
	private String comm_review;

	
	
	public MemberVO(String comm_star, String rest_name, String comm_review,String mem_nick) {
		super();
		this.comm_star = comm_star;
		this.rest_name = rest_name;
		this.comm_review = comm_review;
		this.mem_nick = mem_nick;
	}

	public MemberVO() {
	
	}

	public MemberVO(long mem_num, String mem_id, String mem_pw, String mem_name, String mem_email, String mem_phone,
			String mem_nick, Timestamp mem_regi,String comm_star, String rest_name, String comm_review) {
		super();
		this.mem_num = mem_num;
		this.mem_id = mem_id;
		this.mem_pw = mem_pw;
		this.mem_name = mem_name;
		this.mem_email = mem_email;
		this.mem_phone = mem_phone;
		this.mem_nick = mem_nick;
		this.mem_regi = mem_regi;
		this.comm_star = comm_star;
		this.rest_name = rest_name;
		this.comm_review = comm_review;
	}

	public long getMem_num() {
		return mem_num;
	}

	public void setMem_num(long mem_num) {
		this.mem_num = mem_num;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getMem_pw() {
		return mem_pw;
	}

	public void setMem_pw(String mem_pw) {
		this.mem_pw = mem_pw;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getMem_email() {
		return mem_email;
	}

	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}

	public String getMem_phone() {
		return mem_phone;
	}

	public void setMem_phone(String mem_phone) {
		this.mem_phone = mem_phone;
	}

	public String getMem_nick() {
		return mem_nick;
	}

	public void setMem_nick(String mem_nick) {
		this.mem_nick = mem_nick;
	}

	public Timestamp getMem_regi() {
		return mem_regi;
	}

	public void setMem_regi(Timestamp mem_regi) {
		this.mem_regi = mem_regi;
	}
	public String getComm_star() {
		return comm_star;
	}

	public void setComm_star(String comm_star) {
		this.comm_star = comm_star;
	}

	public String getRest_name() {
		return rest_name;
	}

	public void setRest_name(String rest_name) {
		this.rest_name = rest_name;
	}

	public String getComm_review() {
		return comm_review;
	}

	public void setComm_review(String comm_review) {
		this.comm_review = comm_review;
	}


}
