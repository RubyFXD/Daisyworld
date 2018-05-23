/*
 * This class contains all parameters in the world for initialization
 *
 * */

public class World {
	// Albedo of the open patch
	private static double albedoSurface;

	// Albedo of white daisies
	private static double albedoWhite;

	// Albedo of black daisies
	private static double albedoBlack;

	// The percentage of the patches that will be occupied by daisies
	private static double blackProportion;
	private static double whiteProportion;

	// The amount of incident energy on each patch from sunlight
	private static double luminosity;

	// The method to set the albedo of open ground
	public static void setAlbedoSurface(double albedo) {
		albedoSurface = albedo;
	}

	// The method to get the albedo of open ground
	public static double getAlbedoSurface() {
		return albedoSurface;
	}

	// The method to set the albedo of white daisies
	public static void setAlbedoWhite(double albedo) {
		albedoWhite = albedo;
	}

	// The method to get the albedo of white daisies
	public static double getAlbedoWhite() {
		return albedoWhite;
	}

	// The method to set the albedo of black daisies
	public static void setAlbedoBlack(double albedo) {
		albedoBlack = albedo;
	}

	// The method to get the albedo of black daisies
	public static double getAlbedoBlack() {
		return albedoBlack;
	}

	// The method to set the proportion of black daisies
	public static void setBlackProportion(double proportion) {
		blackProportion = proportion;
	}

	// The method to get the proportion of black daisies
	public static double getBlackProportion() {
		return blackProportion;
	}

	// The method to set the proportion of white daisies
	public static void setWhiteProportion(double proportion) {
		whiteProportion = proportion;
	}

	// The method to get the proportion of white daisies
	public static double getWhiteProportion() {
		return whiteProportion;
	}

	// The method to set the luminosity on each patch
	public static void setLuminosity(double luminosity) {
		World.luminosity = luminosity;
	}

	// The method to get the luminosity on each patch
	public static double getLuminosity() {
		return luminosity;
	}
}
