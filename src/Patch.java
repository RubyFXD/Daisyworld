import java.util.ArrayList;
import java.util.Random;

/*
 * The Patch class contains spot ( a daisy or open ground),
 *     pollution, and temperature of this patch
 *
 */

public class Patch {

	// The spot on this patch
	Spot spot;

	// The pollution level of the patch
	int pollution;

	// The calculated temperature on this patch
	double temperature;

	public Patch() {
		spot = new Spot();
		pollution = 0;
		temperature = 0;
	}

	// The constructor for patch with attributes
	public Patch(Spot spot, int pollution) {
		this.spot = spot;
		this.pollution = pollution;
	}

	/**
	 * calculate the temperature on this patch the energy absorbed by the daisy or
	 * the surface on this patch
	 *
	 * the temperature at this patch is the average of the current temperature and
	 * heating effect
	 *
	 */
	public void calculateTemp() {

		double absorbedLuminosity = 0;
		double localHeating = 0;

		// The percentage of absorbed energy
		absorbedLuminosity = ((1 - spot.getAlbedo()) * World.getLuminosity());

		/*
		 * local heating is calculated as logarithmic function of solar luminosity where
		 * a absorbed luminosity of 1 yields a local heating of 80 degrees C and an
		 * absorbed-luminosity of .5 yields a local-heating of approximately 30 C and a
		 * absorbed-luminosity of 0.01 yields a local-heating of approximately -273 C
		 */
		if (absorbedLuminosity > 0) {
			localHeating = (double) 72 * Math.log(absorbedLuminosity) + 80;
		} else {
			localHeating = 80;
		}

		temperature = (temperature + localHeating) / 2;

	}

	/**
	 * spot with daisy has 10% chance to recover from pollution( from 1 to 0) spot
	 * with pollution=1 has 10 % chance to change to pollution=2 spot with
	 * pollution=2 has no chance to recover with pollution=0 has 10% change to
	 * change to pollution=1
	 */
	public void pollutionChange(ArrayList<Patch> Neighbor) {

	}

	public int getPollution() {
		return pollution;
	}

	public void setPollution(int pollution) {
		this.pollution = pollution;
	}

	// The method to get the temperature of this patch
	public double getTemperature() {
		return temperature;
	}

	// The method to set the temperature of this patch
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	// The method to set the spot on this patch
	public void setSpot(Spot spot) {
		this.spot = spot;
	}

	// the method to get the spot on this patch
	public Spot getSpot() {
		return spot;
	}

}
