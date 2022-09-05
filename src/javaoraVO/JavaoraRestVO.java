package javaoraVO;

/*
    rest_num varchar2(200) 
    rest_name varchar2(20),
    rest_part varchar2(20),
    rest_phone varchar2(20),
    rest_opent varchar2(20),
    rest_closed varchar2(20),
    rest_address varchar2(20)
 
 	mem_num varchar2(200),
    mem_name varchar2(20),
    mem_nick varchar2(20),
    mem_id varchar2(20),
    mem_pw varchar2(20),
    mem_phone varchar2(20),
    mem_email varchar2(20),
    mem_regi date
 */

public class JavaoraRestVO {
	private String rest_num;
	private String rest_name;
	private String rest_part;
	private String rest_phone;
	private String rest_opent;
	private String rest_closed;
	private String rest_address;
	
	public JavaoraRestVO(String rest_num, String rest_name, String rest_part, String rest_phone, String rest_opent,
			String rest_closed, String rest_address) {
		//super();
		this.rest_num = rest_num;
		this.rest_name = rest_name;
		this.rest_part = rest_part;
		this.rest_phone = rest_phone;
		this.rest_opent = rest_opent;
		this.rest_closed = rest_closed;
		this.rest_address = rest_address;
	}
	
	public JavaoraRestVO() {}

	public String getRest_num() {
		return rest_num;
	}

	public void setRest_num(String rest_num) {
		this.rest_num = rest_num;
	}

	public String getRest_name() {
		return rest_name;
	}

	public void setRest_name(String rest_name) {
		this.rest_name = rest_name;
	}

	public String getRest_part() {
		return rest_part;
	}

	public void setRest_part(String rest_part) {
		this.rest_part = rest_part;
	}

	public String getRest_phone() {
		return rest_phone;
	}

	public void setRest_phone(String rest_phone) {
		this.rest_phone = rest_phone;
	}

	public String getRest_opent() {
		return rest_opent;
	}

	public void setRest_opent(String rest_opent) {
		this.rest_opent = rest_opent;
	}

	public String getRest_closed() {
		return rest_closed;
	}

	public void setRest_closed(String rest_closed) {
		this.rest_closed = rest_closed;
	}

	public String getRest_address() {
		return rest_address;
	}

	public void setRest_address(String rest_address) {
		this.rest_address = rest_address;
	}
	
	
}
