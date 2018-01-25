import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import kafka.utils.Json;
import scala.Array;

public class Stations {
 String code;
 String description;
 String whoAmI;
 String version;
 String time;
 List<Station> stations; 
 
 public Stations() {
	 this.stations = new ArrayList<Station>();
 }
 
public List<Station> getStations() {
	return stations;
}
public void setStations(List<Station> stations) {
	this.stations = stations;
}
}
