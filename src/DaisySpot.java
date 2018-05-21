/*
 * This DaisySpot class extends from EmptySpot class.
 * There are two types of daisies, black daisies and white daisies.
 * 
 * There is only a certain temperature range in which daisies can reproduce.
 * Every Daisy has an age attribute.
 * 
 */
public class DaisySpot extends EmptySpot {
	
	// The daisy will eventually die of old age
	int maxAge;
	int currentAge;
	
	// The certain temperature range of a daisy
	double minTempe;
	double highTempe;
	
	// The method to set the current age of a daisy
	void setCurrentAge(int age) {
		currentAge = age;
	}
	
	// The method to set the minimum temperature 
	void setMinTempe(double temperature) {
		minTempe = temperature;
	}
	
	// The method to set the maxmum temperature
	void setMaxTempe(double temperature) {
		maxTempe = temperature;
	}
	
	// The method to get the current age of a daisy
	int getCurrentAge() {
		return currentAge;
	}
	
	// The method to get the minimum temperature
	double getMinTempe() {
		return minTempe;
	}
	
	// The method to get the maxmum temperature
	double getMaxTempe() {
		return maxTempe;
	}
}
