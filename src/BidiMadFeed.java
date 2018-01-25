
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONArray;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javassist.compiler.Parser;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import org.apache.commons.*;




public class BidiMadFeed {
	
		
	public static Stations getStations() throws JSONException, SAXException, ParserConfigurationException {	
		
		
		try {
			
			
			
			HttpConnector hConnector = new HttpConnector();
			hConnector.SetConnection("https://rbdata.emtmadrid.es:8443/BiciMad/get_stations/");
			
			BufferedReader br = hConnector.GetMethod();
			
			StringBuilder sb = new StringBuilder();

		    String line;
		    while ((line = br.readLine()) != null) {
		        sb.append(line);
		    }
		    JSONObject json = new JSONObject(sb.toString());
		    JsonObject root = new JsonParser().parse(json.toString()).getAsJsonObject();
	        //JsonObject jo = root.getAsJsonObject();
	        JsonElement stationsJSON = root.get("data");
	       // JsonObject jo2 = header.getAsJsonObject();
	        String JsonFormated = stationsJSON.toString().replace("\\", "");
	        json = new JSONObject(JsonFormated.substring(1, JsonFormated.length()-1));
		    JSONArray StationsJSON = json.getJSONArray("stations");
		    Stations stations = new Stations();
		    for (int i=0;i<2 ;i++) {
		    		JSONObject st = StationsJSON.getJSONObject(i+(i*10));
		    		Station station = new Station();
		    		station.setId(st.getInt("id"));
		    		station.setName(st.getString("name"));
		    		station.setActive(st.getInt("activate"));
		    		station.setNo_Available(st.getInt("no_available"));
		    		station.setTotal_Bases(st.getInt("total_bases"));
		    		station.setDock_Bases(st.getInt("dock_bikes"));
		    		station.setFree_Bases(st.getInt("free_bases"));
		    		station.setJsonStation(st.toString());
		    		
		    		/** save stations from BICIMAD **/
		    		stations.stations.add(station);
		    		
		    }
		    
		    
			hConnector.conn.disconnect();
			
			return stations;
			
		}catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  }
		return null;
	}
	
}
