package bg.uni_sofia.fmi.javaee.provider;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonFactory {
	
	public static Gson build() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		return gsonBuilder.setDateFormat("MM/dd/yy").create();
	}
	
}
