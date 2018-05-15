package admin;

import java.util.ArrayList;
import java.util.List;

import model.RecycleItem;
import utility.Display;

public class TestSearch {

	public static void main(String[] args) {
		System.out.println(search("Wine Bottle"));
	}
	
	public static String search(String searchString){
		List<RecycleItem> list = new ArrayList<RecycleItem>();
		for(Object obj : new RecycleItem().findList("name", searchString)){
			 RecycleItem rec = (RecycleItem) obj;
		}
		for(Object obj : new RecycleItem().findChild(searchString)){
			 RecycleItem rec = (RecycleItem) obj;
					 list.add(rec);
		}
		
		return Display.getJSON(list);
	}

}
