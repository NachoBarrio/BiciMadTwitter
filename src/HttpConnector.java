import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HttpConnector {
	public HttpURLConnection conn;
	private static String ApiUser = "WEB.SERV.ignaciobarriosantos@gmail.com";
	private static String ApiPsw = "A26B7741-6B7B-4D99-9247-9FE713449430";
	
	public void SetConnection(String surl) {
		URL url;
		try {
			url = new URL(surl+ ApiUser +"/"+ApiPsw);
			this.conn = (HttpURLConnection) url.openConnection();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public BufferedReader GetMethod() throws IOException {
		
			this.conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
			

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));
			return br;
		
	}
	
}
