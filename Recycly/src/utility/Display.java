package utility;

import com.google.gson.GsonBuilder;

public class Display {
	public static void printJSONDetails(Object obj) {
		System.out.println(new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).setPrettyPrinting().disableHtmlEscaping().create().toJson(obj));
	}
	public static String getJSON(Object obj) {
		return new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).setPrettyPrinting().disableHtmlEscaping().create().toJson(obj);
	}
}
