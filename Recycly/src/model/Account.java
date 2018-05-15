package model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import design.DatabaseItem;
import utility.Display;
import utility.Encryptor;

@Entity
@Table(name = "account")
public class Account extends DatabaseItem{
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer accountID;
	private String name, username, password;
	@Column
	@CreationTimestamp
	private Date dateCreated;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private AccountType type;
	
	public Integer getAccountID() { return accountID; }
	public void setAccountID(Integer accountID) { this.accountID = accountID; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	public Date getDateCreated() { return dateCreated; }
	public void setDateCreated(Date dateCreated) { this.dateCreated = dateCreated; }
	public AccountType getType() { return type; }
	public void setType(AccountType type) { this.type = type; }
	
	public Account() {}
	private Account(AccountBuilder builder) {
		setName(builder.name);
		setUsername(builder.username);
		setPassword(Encryptor.hash(builder.password));
		setType(builder.type);
	}
	
	public static class AccountBuilder{
		private String name, username, password;
		private AccountType type;
		public AccountBuilder name(String name) {
			this.name = name;
			return this;
		}
		public AccountBuilder username(String username) {
			this.username = username;
			return this;
		}
		public AccountBuilder password(String password) {
			this.password = password;
			return this;
		}
		public AccountBuilder type(String type) {
			this.type = (AccountType) new AccountType().find("type", type);
			return this;
		}
		public Account build() {
			return new Account(this);
		}
	}
	
	
	
	public String login(String username, String password) {
		Account acc = (Account) find("username", username);
		if(acc==null) return "Username Does Not Exist";
		else {
			if(acc.getPassword().equals(Encryptor.hash(password))) return acc.toString();
			else return "Incorrect Password";
		}
	}
	
	
	@Override
	public String toString() {
		return Display.getJSON(this);
	}
}
