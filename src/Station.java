
public class Station {
	
	public int id;
	public float latitude;
	public float longitude;
	public String name;
	public int active;
	public int no_Available;
	public int total_Bases;
	public int dock_Bases;
	public int free_Bases;
	public String JsonStation;
	
	
	public Station() {
		
	}
	
	public Station(String id,float latitude,float longitude,String name,int active,
			int no_Available,int total_Bases,int dock_Bases,int free_bases) {
		id = id;
		latitude = latitude;
		longitude = longitude;
		name = name;
		active = active;
		no_Available = no_Available;
		total_Bases = total_Bases;
		dock_Bases = dock_Bases;
		free_bases = free_bases;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public float getLatitude() {
		return latitude;
	}


	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}


	public float getLongitude() {
		return longitude;
	}


	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getActive() {
		return active;
	}


	public void setActive(int active) {
		this.active = active;
	}


	public int getNo_Available() {
		return no_Available;
	}


	public void setNo_Available(int no_Available) {
		this.no_Available = no_Available;
	}


	public int getTotal_Bases() {
		return total_Bases;
	}


	public void setTotal_Bases(int total_Bases) {
		this.total_Bases = total_Bases;
	}


	public int getDock_Bases() {
		return dock_Bases;
	}


	public void setDock_Bases(int dock_Bases) {
		this.dock_Bases = dock_Bases;
	}


	public int getFree_Bases() {
		return free_Bases;
	}


	public void setFree_Bases(int free_Bases) {
		this.free_Bases = free_Bases;
	}
	
	public String getJsonStation() {
		return this.JsonStation;
	}
	
	public void setJsonStation(String JsonStation) {
		this.JsonStation = JsonStation;
	}
	
	
}
