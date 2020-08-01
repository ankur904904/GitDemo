package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

      @Before("@DeletePlace")
      public void beforeSceanario() throws IOException
      {
    	  StepDefination m = new StepDefination();
    	  if(StepDefination.place_id==null)
    	  {
    	  m.add_Place_PayLoad_with("Ankur", "En", "fff");
    	  m.user_calls_with_http_request("AddPlaceAPI", "POST");
    	  m.verify_place_id_created_maps_to_using("Ankur", "GetPlaceAPI");
    	  }
      }
}