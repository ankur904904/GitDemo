package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.Addplace;
import pojo.Location;

public class TestDataBuild {

	
	public Addplace addLoadPayLoad(String name, String language, String address)
	{
		Addplace p = new Addplace();
	    p.setAccuracy(50);
	    p.setAddress(address);
	    p.setLanguage(language);
	    p.setName(name);
	    p.setPhone_number("94646641");
	    p.setWebsite("www.google.com1");
	    List<String> myList=  new ArrayList<String>();
	    myList.add("shoe park1");
	    myList.add("sho1p");
	    p.setTypes(myList);  
	    Location loc = new Location();
	    loc.setLng(-78);
	    loc.setLng(-97.0);
	    p.setLocation(loc);
	    return p;
	}
	
		public String deletePlacePayLoad1(String place_id)
	{
		String value = "{\r\n    \"place_id\":\""+place_id+"\"\r\n}\r\n";
		return value;
	}
	
	
}
