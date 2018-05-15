package model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import design.DatabaseItem;

@Entity
@Table(name = "favorite")
public class Favorite extends DatabaseItem{
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer favoriteID;
	private int account;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<RecycleItem> items;
	public Integer getFavoriteID() { return favoriteID; }
	public void setFavoriteID(Integer favoriteID) { this.favoriteID = favoriteID; }
	public int getAccount() { return account; }
	public void setAccount(int account) { this.account = account; }
	public List<RecycleItem> getItems() { return items; }
	public void setItems(List<RecycleItem> items) { this.items = items; }
	
	public boolean isFavorite(int user, int recycleID) {
		for(Object obj : getList()) {
			Favorite fav = (Favorite) obj;
			for(RecycleItem rec : fav.getItems()) {
				if(fav.getAccount()==user&&rec.getRecycleID()==recycleID) 
					return true;
			}
		}
		return false;
	}
	public boolean deleteFavorite(int recycleID) {
		for(Object obj : getList()) {
			List<RecycleItem> reclist = new ArrayList<RecycleItem>();
			Favorite fav = (Favorite) obj;
			for(Object ob : fav.getItems()) {
				RecycleItem rec = (RecycleItem) ob;
				if(rec.getRecycleID()!=recycleID)
					reclist.add(rec);
			}
			try {
				fav.setItems(reclist);
				update();
				if(fav.getItems().size()==0) {
					fav.delete();
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	public boolean deleteFavorite(int user, int recycleID) {
		List<Object> list = getList("account",Integer.toString(user));
		for(Object obj : list) {
			List<RecycleItem> reclist = new ArrayList<RecycleItem>();
			Favorite fav = (Favorite) obj;
			for(Object ob : fav.getItems()) {
				RecycleItem rec = (RecycleItem) ob;
				if(rec.getRecycleID()!=recycleID)
					reclist.add(rec);
			}
			try {
				fav.setItems(reclist);
				update();
				if(fav.getItems().size()==0) {
					fav.delete();
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	public List<Object> getFavorites(int user) {
		List<Object> list = getList("account",Integer.toString(user));
		if(list.size()==0||list.isEmpty())
			return null;
		List<Object> list2 = new ArrayList<Object>();
		for(Object obj : list) {
			Favorite fav = (Favorite) obj;
			for(RecycleItem rec : fav.getItems()) {
				list2.add(rec);
			}
		}
		return list2;
	}
}
