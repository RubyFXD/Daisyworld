/*
 * This class represents the world
 *
 */

public class World {

	// The width and length of the whole world
	int width;
	int length;

	// The world is comprised of several patches
	Patch patch;

	// The global temperature of the world
	double globalTempe;

	// The amount of incident energy on each pathch from sunlight
	double luminosity;

	// The percentage of the pathces that will be occupied by daisies
	double blackProportion;
	double whiteProportion;

	// The method to set the width of the world
	void setWidth (int width) {
		this.width = width;
	}

	// The method to set the length of the world
	void setLength (int length) {
		this.length = length;
	}


	// The method to set the global temperature
	void setGlobalTempe (double temperature) {
		globalTempe = temperature;
	}

	// The method to set the lumionsity on each patch
	void setLuminosity (int lum) {
		luminosity = lum;
	}

	// The method to set the percentage of balck daisies
	void setBlackProportion (double proportion) {
		blackProportion = proportion;
	}

	// The method to set the percentage of white daisies
	void setWhitepProportion (double proportion) {
		whiteProportion = proportion;
	}


	// The method to get the width of the world
	int getWidth () {
		return this.width;
	}

	// The method to get the length of the world
	int getLength () {
		return this.length;
	}

	// The method to get the global temperature
	double getGlobalTempe () {
		return globalTempe;
	}

	// The method to get the luminosity on each patch
	double getLuminosity () {
		return luminosity;
	}

	// The method to get the percentage of black daisies
	double getBlackProportion () {
		return blackProportion;
	}

	// The method to get the percentage of white daisies
	double getWhiteProportion () {
		return whiteProportion;
	}
}
