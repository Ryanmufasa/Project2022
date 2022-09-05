package javaoraVO;

public class JavaoraComVO {

	private String rest_name; // 식당 이름
	private String mem_nick; // 닉네임
	private String comm_star; // 별점
	private String comm_review;// 리뷰

	public JavaoraComVO(String rest_name, String mem_nick, String comm_star, String comm_review) {
		super();
		this.rest_name = rest_name;
		this.mem_nick = mem_nick;
		this.comm_star = comm_star;
		this.comm_review = comm_review;
	}

	public String getRest_name() {
		return rest_name;
	}

	public void setRest_name(String rest_name) {
		this.rest_name = rest_name;
	}

	public String getMem_nick() {
		return mem_nick;
	}

	public void setMem_nick(String mem_nick) {
		this.mem_nick = mem_nick;
	}

	public String getComm_star() {
		return comm_star;
	}

	public void setComm_star(String comm_star) {
		this.comm_star = comm_star;
	}

	public String getComm_review() {
		return comm_review;
	}

	public void setComm_review(String comm_review) {
		this.comm_review = comm_review;
	}

}
