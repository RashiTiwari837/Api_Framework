package resources;

import java.util.ArrayList;

import io.restassured.RestAssured;
import pojo.lc;
import pojo.location;

public class TestDataBuild {
	public location addplacepayload(String name, String language, String address) {
		
		RestAssured.baseURI="https://rahulshettyacademy.com/";
		location loc = new location();
		lc lat = new lc();
		loc.setAccuracy(24);
		loc.setAddress(address);
		loc.setLanguage(language);
		lat.setLat(23.45);
		lat.setLng(45.32);
		loc.setLocation(lat);
		loc.setName(name);
		loc.setPhone_number("389398");
		ArrayList<String> ty = new ArrayList<String>();
		ty.add("don");
		ty.add("java");
		loc.setTypes(ty);
		loc.setWebsite("www.test.com");
		return loc;
	}
	public String deldata(String place) {
		return "{\r\n \"place_id\": \""+place+"\"\r\n}";
	}
}
