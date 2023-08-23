package board.security;


public class User {
	
	/**
	 * 
	 */
	private Long memSeq;
	private String id;
	private String name;
	private String pass;
	private String email;
	private String role;
	private Object detail;
	
	
	public User(Long memSeq, String id, String username, String password, String email, String role) {
		this.memSeq= memSeq;
		this.id = id;
		this.name = username;
		this.pass = password;
		this.email = email;
		this.role = role;
	}
	

	public Long getMemSeq() {
		return memSeq;
	}


	public void setMemSeq(Long memSeq) {
		this.memSeq = memSeq;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public Object getDetail() {
		return detail;
	}


	public void setDetail(Object detail) {
		this.detail = detail;
	}

	
}
