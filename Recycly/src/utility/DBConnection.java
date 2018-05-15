package utility;

import java.util.Calendar;
import java.util.Date;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBConnection {
	static SessionFactory factory;
	static long minutes = 0;
	static Date last = new Date();
	private DBConnection(){}
	
	private static SessionFactory getSessionFactoryInstance(){
		if(factory == null){
			try{
				factory = new Configuration().configure().buildSessionFactory();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		return factory;
	}
	
	public static SessionFactory getSessionFactory(){
		if(factory==null||isExpired(last)){
			last = new Date();
			return getSessionFactoryInstance();
		}
		else{
			return factory;
		}
	}
	
	public static boolean isExpired(Date timestamp){
		boolean isExpired = false;
		int diff = (int) (new Date().getTime() - last.getTime())/1000;
		int[] time = {31536000,2592000,604800,86400,3600,60,1};
		for(int x=0;x<time.length;x++){
			 if (diff < time[x]){
				 continue;
			 }
			 else{
			     return diff>20*60;
			 }
		}
		return isExpired;
	}
	
	public static void closeSessionFactory(){
		factory.close();
		factory = null;
	}
}