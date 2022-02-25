package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	StepDefination stp = new StepDefination();
@Before("@deleteplace")
public void beforescenario() throws IOException {
	if(StepDefination.place==null) {
	stp.add_place_payload_with("Tiw", "Italian", "streetno 21");
	stp.user_calls_with_http_req("AddPlaceApi", "POST");
	stp.verify_placeid_created_maps_to_using("Tiw", "getplaceapi");
	
	}
}
}
