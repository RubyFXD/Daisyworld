public class Spot {

	// The percentage of energy this spot absorbs as heat from sunlight
	double albedo = 0;

	// The type of this spot: open ground, black daisy, white daisy
	String type = "open";

	// The daisy will eventually die of old age
	int maxAge;
	int currentAge=0;

	// The certain temperature range of a daisy
	double minTempe;
	double maxTempe;

	//constructor for empty spot
	public Spot(double albedo, String type){
		this.albedo=albedo;
		this.type=type;
	}
	//constructor for black or white daisy
	public Spot(double albedo, String type, int maxAge, double minTempe, double maxTempe){
		this.albedo=albedo;
		this.type=type;
		this.maxAge=maxAge;
		this.minTempe=minTempe;
		this.maxTempe=maxTempe;
	}

	public double getAlbedo() {
		return albedo;
	}

	public void setAlbedo(double albedo) {
		this.albedo = albedo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}

	public int getCurrentAge() {
		return currentAge;
	}

	public void setCurrentAge(int currentAge) {
		this.currentAge = currentAge;
	}

	public double getMinTempe() {
		return minTempe;
	}

	public void setMinTempe(double minTempe) {
		this.minTempe = minTempe;
	}

	public double getMaxTempe() {
		return maxTempe;
	}

	public void setMaxTempe(double maxTempe) {
		this.maxTempe = maxTempe;
	}
}
