package resources;

public enum api_res {
	
	AddPlaceApi("maps/api/place/add/json"),
	getplaceapi("maps/api/place/get/json"),
	delplaceapi("maps/api/place/delete/json");
	private String resource;
api_res(String resource) {
		this.resource=resource;
	}
public String getresource() {
	
	return resource;
}
}
