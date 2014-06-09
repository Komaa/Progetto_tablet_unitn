package tablet_unitn.treasurehunt;

public class Wiki {
	Double lat = 0.0, //latitudine
			lng = 0.0; //longitudine
	
	String id = "",
			type ="",
			title="",
			url_mobile="",
			distance="";

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl_mobile() {
		return url_mobile;
	}

	public void setUrl_mobile(String url_mobile) {
		this.url_mobile = url_mobile;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}
	
}