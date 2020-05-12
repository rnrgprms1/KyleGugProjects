package UserContrl;

import javax.persistence.*;

@Entity
class Person {
	
	@Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
	Person Id_number;
	
	@Column
	String username;
	
	@Column
	String password;
	
	@Column
	String friend;
	
	public Person getId() { return Id_number; }
	//public Integer findOne() {return id; }
	public String getUser() { return username; }
	public String getpass() { return password; }
	public String getFriend() {return friend; }
	public void setpass(String password) { this.password = password; }
	
	
}
