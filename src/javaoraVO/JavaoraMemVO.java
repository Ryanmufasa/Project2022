package javaoraVO;

import java.sql.Timestamp;

public class JavaoraMemVO {
	private long mem_num;
	private String mem_name;
	private String mem_nick;
	private String mem_id;
	private String mem_pw;
	private String mem_phone;
	private String mem_email;
	private Timestamp mem_regi;
	
	public JavaoraMemVO(long mem_num, String mem_name, String mem_nick, String mem_id, String mem_pw,
			String mem_phone, String mem_email, Timestamp mem_regi) {
		super();
		this.mem_num = mem_num;
		this.mem_name = mem_name;
		this.mem_nick = mem_nick;
		this.mem_id = mem_id;
		this.mem_pw = mem_pw;
		this.mem_phone = mem_phone;
		this.mem_email = mem_email;
		this.mem_regi = mem_regi;
	}
	
	public JavaoraMemVO () {}

	public long getMem_num() {
		return mem_num;
	}

	public void setMem_num(long mem_num) {
		this.mem_num = mem_num;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getMem_nick() {
		return mem_nick;
	}

	public void setMem_nick(String mem_nick) {
		this.mem_nick = mem_nick;
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

	public String getMem_phone() {
		return mem_phone;
	}

	public void setMem_phone(String mem_phone) {
		this.mem_phone = mem_phone;
	}

	public String getMem_email() {
		return mem_email;
	}

	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}

	public Timestamp getMem_regi() {
		return mem_regi;
	}

	public void setMem_regi(Timestamp mem_regi) {
		this.mem_regi = mem_regi;
	}
	
	
	
}
