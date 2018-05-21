/* 
 * This class represents the state of a spot.
 * 
 * A spot can be a open ground, a black daisy, or a white daisy.
 * These three different types of spots have different albedo, 
 * 	  which is how much energy they abosob as heat from sunlight.
 */
public class EmptySpot {
	
	// The percentage of energy this spot absorbs as heat from sunlight
	double albedo = 0;
	
	// The type of this spot: open ground, black daisy, white daisy
	String type = "open";
	
	// The method to set the type
	void setType(string str) {
		type = str;
	}
	
	// The method to set the albedo
	void setAlbedo(double percentage) {
		albedo = percentage;
	}
	
	// The method to get the type
	String getType() {
		return type;
	}
	
	// The method to get the albedo
	double getAlbedo() {
		return albedo;
	}
}
