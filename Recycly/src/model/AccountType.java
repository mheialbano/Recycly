package model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import design.DatabaseItem;
import utility.Display;

@Entity
@Table(name = "account_type")
public class AccountType extends DatabaseItem{
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer accountTypeID;
	private String type;
	public Integer getAccountTypeID() { return accountTypeID; }
	public void setAccountTypeID(Integer accountID) { this.accountTypeID = accountTypeID; }
	public String getType() { return type; }
	public void setType(String type) { this.type = type; }
	
	@Override
	public String toString() {
		return Display.getJSON(this);
	}
}