package model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import design.DatabaseItem;
import model.Account.AccountBuilder;
import utility.DBConnection;
import utility.Display;
import utility.Encryptor;

@Entity
@Table(name = "recycle")
public class RecycleItem extends DatabaseItem{
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer recycleID;
	private String name,picture;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Account account;
	@Column
	@CreationTimestamp
	private Date dateCreated;
	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
	@Fetch(FetchMode.SUBSELECT)
	private List<Directions> directions;
	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
	@Fetch(FetchMode.SUBSELECT)
	private List<Materials> materials;
	public Integer getRecycleID() { return recycleID; }
	public void setRecycleID(Integer recycleID) { this.recycleID = recycleID; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public List<Materials> getMaterials() { return materials; }
	public void setMaterials(List<Materials> materials) { this.materials = materials; }
	public String getPicture() { return picture; }
	public void setPicture(String picture) { this.picture = picture; }
	public Account getAccount() { return account; }
	public void setAccount(Account account) { this.account = account; }
	public Date getDateCreated() { return dateCreated; }
	public void setDateCreated(Date dateCreated) { this.dateCreated = dateCreated; }
	public List<Directions> getDirections() { return directions; }
	public void setDirections(List<Directions> directions) { this.directions = directions; }
	public void addDirection(Directions direction) {
		if(directions==null)
			directions = new ArrayList<Directions>();
		directions.add(direction);
	}
	
	public RecycleItem() {}
	private RecycleItem(RecycleItemBuilder builder) {
		setName(builder.name);
		setAccount(builder.account);
		setPicture(builder.picture);
		setMaterials(builder.materials);
		setDirections(builder.directions);
	}
	
	public static class RecycleItemBuilder{
		private String name, picture;
		private Account account;
		private List<Directions> directions = new ArrayList<Directions>();
		private List<Materials> materials = new ArrayList<Materials>();
		public RecycleItemBuilder name(String name) {
			this.name = name;
			return this;
		}
		public RecycleItemBuilder picture(String picture) {
			this.picture = picture;
			return this;
		}
		public RecycleItemBuilder account(int id) {
			this.account = (Account) new Account().getModel(id);
			return this;
		}
		public RecycleItemBuilder directions(List<Directions> directions) {
			this.directions = directions;
			return this;
		}
		public RecycleItemBuilder addDirection(Directions direction) {
			directions.add(direction);
			return this;
		}
		public RecycleItemBuilder materials(List<Materials> materials) {
			this.materials = materials;
			return this;
		}
		public RecycleItemBuilder addMaterials(Materials material) {
			materials.add(material);
			return this;
		}
		public RecycleItem build() {
			return new RecycleItem(this);
		}
	}
	
	@Override
	public void delete() {
		setDirections(null);
		setAccount(null);
		Session session = DBConnection.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(this);
			transaction.commit();
		} catch(Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		session.close();
	}
	
	@Override
	public String toString() {
		return Display.getJSON(this);
	}
	public List<Object> search(String word) {
		Session session = DBConnection.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(model.RecycleItem.class);
		List<Object> list = criteria.list();
		session.close();
		List<Object> list2 = new ArrayList<Object>();
		for(Object obj : list) {
			RecycleItem recipe = (RecycleItem) obj;
			if(recipe.getName().toLowerCase().contains(word.toLowerCase()))
				list2.add(obj);
		}
		return list2;
	}
}
