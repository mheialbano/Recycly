package admin;
import java.util.ArrayList;
import java.util.List;

import model.*;
public class FillData {

	public static void main(String[] args) {
		/*String[] accountTypes = {"Admin","User"};
		for(String s : accountTypes) {
			AccountType accType = new AccountType();
			accType.setType(s);
			accType.save();
		}
		new Account.AccountBuilder()
		.name("Bryce Deyto")
		.username("bryce27923")
		.password("123123")
		.type("Admin")
		.build().save();*/
		String[] materials = {"2 Plastic Bottle","Zipper", "Glue Gun", "Scissors"};
		String[] directions = {"1. Cut off the bottom of the plastic bottle to any length desired.",
				"2. Cut zipper unti lit fits the hole of the cut bottom of plastic bottle.",
				"3. Glue zipper on both the edges of bottom of plastic bottle."};
		List<Materials> list = new ArrayList<Materials>();
		for(String s : materials) {
			Materials m1 = new Materials();
			m1.setName(s);
			list.add(m1);
		}
		List<Directions> list2 = new ArrayList<Directions>();
		for(int x=0;x<directions.length;x++){
			Directions d = new Directions();
			d.setStepNumber(x+1);
			d.setDescription(directions[x]);
			list2.add(d);
		}
		RecycleItem r = new RecycleItem.RecycleItemBuilder()
		.name("Bottle end purse")
		.picture("picture.jpg")
		.directions(list2)
		.materials(list)
		.account(1)
		.build();
		r.save();
		System.out.println(r);
	}

}
