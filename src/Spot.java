/*
 * This class represents the spot type of the patch in the world
 *
 * It can be open, a white daisy, or a black daisy
 */
public class Spot {

	// The percentage of energy this spot absorbs as heat from sunlight
	private double albedo;

	// The type of this spot: open ground, black daisy, white daisy
	private String type;

	// The daisy will eventually die of old age
	private int maxAge;
	private int currentAge;

	public Spot() {
		albedo = 0;
		type = "";
		currentAge = 0;
		maxAge = 0;
	}

	// Constructor for empty spot (the patch is open)
	public Spot(double albedo, String type) {
		this.albedo = albedo;
		this.type = type;
	}

	// Constructor for black or white daisy
	public Spot(double albedo, String type, int age) {
		this.albedo = albedo;
		this.type = type;
		maxAge = age;
	}

	// The method to set the albedo
	public void setAlbedo(double albedo) {
		this.albedo = albedo;
	}

	// The method to get the albedo
	public double getAlbedo() {
		return albedo;
	}

	// The method to set the type of a patch: empty, black daisy or white daisy
	public void setType(String type) {
		this.type = type;
	}

	// The method to get the type of a patch
	public String getType() {
		return type;
	}

	// The method to set the age of a daisy
	public void setCurrentAge(int age) {
		currentAge = age;
	}

	// The method to get the age of a daisy
	public int getCurrentAge() {
		return currentAge;
	}

	// The method to set the maximum age of a daisy (pollution)
	public void setMaxAge(int age) {
		maxAge = age;
	}

	// The method to get the maximum age of a daisy (pollution)
	public int getMaxAge() {
		return maxAge;
	}



}
