package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification res;
	
	
	public RequestSpecification reqspecification() throws IOException {
		if(res==null) {
		PrintStream log =new PrintStream(new FileOutputStream("logging.txt"));
		 res=new RequestSpecBuilder().setBaseUri(getGlobalvalues("baseurl"))
				.addQueryParam("key", "qaclick123").setContentType("application/json")
				.addFilter(RequestLoggingFilter.logRequestTo(log)).
				addFilter(ResponseLoggingFilter.logResponseTo(log)).
				build();
		 return res;		
		}
		return res;
	}
	public static String getGlobalvalues(String Key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("D:\\rashi\\workspace\\API\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(Key);
		
	}
	public static String getJsonPath(Response string, String key) {
		String  resStr= string.asString();
		JsonPath js = new JsonPath(resStr);
		return js.get(key).toString();
	}
}
