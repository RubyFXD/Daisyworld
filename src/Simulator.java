import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Simulator {

	// Maximum age that all daisies live to
	private static final int MAXAGE = 25;

	// The width and length of the whole world (assume width = length = SIZE)
	private static final int SIZE = 50;

	// The world is comprised of several patches
	private static Patch[][] patch;

	// The global temperature of the world
	public static double globalTempe = 0;

	//The global pollution of the world
	public static double globalPollution=0;

	private static ArrayList<Spot> blackDaisy;
	private static ArrayList<Spot> whiteDaisy;

	public static void initial() {
		blackDaisy = new ArrayList<Spot>();
		whiteDaisy = new ArrayList<Spot>();
		patch = new Patch[SIZE][SIZE];

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				patch[i][j] = new Patch();
				initializePollution(patch[i][j]);
			}
		}

	}

	// The method to get the number of black daisies
	private static int numBlacks() {
		return blackDaisy.size();
	}

	// The method to get the number of white daisies
	private static int numWhites() {
		return whiteDaisy.size();
	}

	// The method to set the global temperature
	void setGlobalTempe(double temperature) {
		globalTempe = temperature;
	}

	// The method to get the global temperature
	double getGlobalTempe() {
		return globalTempe;
	}

	/**
	 * The diffusion of 50% of the temperature value at that patch between its
	 * neighbors
	 */
	public static void diffuse() {
		// save the new temperature of every patch
		double[][] tempAfterDiffuse = new double[SIZE][SIZE];

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				ArrayList<Patch> neighbour = getNeighbor(i, j);
				double numOfNeighbor = (double) neighbour.size();
				double tempFromNeighbor = 0;

				for ( Iterator<Patch> it = neighbour.iterator(); it.hasNext(); ) {
					//Patch tempPatch = neighbour.get(index);
					Patch tempPatch = it.next();
					tempFromNeighbor = tempFromNeighbor + tempPatch.getTemperature() * 0.5 / 8;
				}
				tempAfterDiffuse[i][j] = patch[i][j].getTemperature()
						// this patch will diffuse temperature to its neighbors
						- patch[i][j].getTemperature() * 0.5 * (numOfNeighbor / 8) + tempFromNeighbor;
			}
		}

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				patch[i][j].setTemperature(tempAfterDiffuse[i][j]);
			}
		}

	}

	/**
	 * set initial pollution on each spot
	 */
	public static void initializePollution(Patch patch){
		double temp= Math.random();
		if(temp>=0.0 && temp<0.3){
			patch.setPollution(2);
		}else if(temp>=0.3 && temp<0.8){
			patch.setPollution(1);
		}else{
			patch.setPollution(0);
		}
	}
	/**
	 * calculate global pollution
	 */
	public static void calPollution(){
		int tempPollution=0;
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				tempPollution=patch[i][j].getPollution()+tempPollution;
			}
		}
		globalPollution=(double)tempPollution/(SIZE*SIZE);
	}
	/**
	 * pollution =0 the spot has 20% chance change to 1
	 * pollution =10 the spot has 20% chance change to 0, 20% change to 2
	 * pollution =20 the spot has 20% chance change to 1
	 */

	public static void  pollutionChange() {
		int [][] tempPollution = new int[SIZE][SIZE];
		double temp=Math.random();
//		int pollution_0_count=0;
//		int pollution_1_count=0;
//		int pollution_2_count=0;

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if(patch[i][j].getPollution()==0){
					if(temp>=0.0 && temp<0.2){
						patch[i][j].setPollution(1);
					}
				}else if(patch[i][j].getPollution()==1){
					if(temp>=0.0 && temp<0.2){
						patch[i][j].setPollution(0);
					}else if(temp>=0.2 && temp<0.4){
						patch[i][j].setPollution(2);
					}
				}else{
					if(temp>=0.0 && temp<0.2){
						patch[i][j].setPollution(1);
					}
				}


//				ArrayList<Patch> neighbour = getNeighbor(i, j);
//
//				for ( Iterator<Patch> it = neighbour.iterator(); it.hasNext(); ) {
//					Patch tempPatch = it.next();
//					if(tempPatch.getPollution()==0){
//						pollution_0_count++;
//					}else if(tempPatch.getPollution()==10){
//						pollution_1_count++;
//					}else{
//						pollution_2_count++;
//					}
//				}
//				if(pollution_0_count > pollution_1_count && pollution_0_count>pollution_2_count){
//					tempPollution[i][j]=0;
//				}else if(pollution_1_count>pollution_0_count && pollution_1_count>pollution_2_count){
//					tempPollution[i][j]=10;
//				}else if(pollution_2_count>pollution_0_count && pollution_2_count>pollution_1_count){
//					tempPollution[i][j]=20;
//				}else if(pollution_0_count == pollution_1_count && pollution_0_count >pollution_2_count){
//					tempPollution[i][j]=0;
//				}else if(pollution_1_count == pollution_2_count && pollution_1_count>pollution_0_count){
//					tempPollution[i][j]=10;
//				}else if(pollution_0_count == pollution_2_count && pollution_0_count >pollution_1_count){
//					tempPollution[i][j]=0;
//				}

			}
		}

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				patch[i][j].setPollution(tempPollution[i][j]);
			}
		}
	}

	/**
	 * pollution=1 slight pollution decline the maxAge of the daisy
	 * pollution=2 severe pollution kill the daisy immediately
	 */
	public static void checkPollution(){
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if(patch[i][j].getSpot().getType()!="open"){
					if(patch[i][j].getPollution()==1){
						int maxAge=patch[i][j].getSpot().getMaxAge();
						patch[i][j].getSpot().setMaxAge(maxAge--);
					}else if(patch[i][j].getPollution()==2){
						int maxAge=patch[i][j].getSpot().getMaxAge();
						patch[i][j].getSpot().setCurrentAge(maxAge);
					}
				}
			}
		}
	}

	/**
	 * check whether or not the daisy on this patch can survival and sprout a new daisy in neighbor
	 * currentAge = MAXAGE kill the daisy immediately
	 * pollution is 0, no influence
	 * pollution is 1, reduce maxAge each tick
	 * pollution is 2, skill the daisy immediately
	 * temperature not in the survival range, kill the daisy immediately
	 */
	public static void checkSurvival() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {

				// Get relevant parameters of current patch to check survival
				Spot spot = patch[i][j].getSpot();
				String type = spot.getType();
				int age = spot.getCurrentAge();
				double localTemp = patch[i][j].getTemperature();

				// current patch is occupied by a daisy
				if ((!type.equals("open")) && (age < MAXAGE)) {
					// probability calculation
					double seedThreshold = (0.1457 * localTemp) - (0.0032 * Math.pow(localTemp, 2)) - 0.6443;

					// offspring
					if (Math.random() < seedThreshold) {
						Patch seedingPlace = findOpenNeighbor(i, j);
						if (seedingPlace != null) {
							// black
							if (type.equals("blackDaisy")) {
								Spot newSpot = new Spot(World.getAlbedoBlack(), "blackDaisy", randomAge());
								blackDaisy.add(newSpot);
								newSpot.setType("blackDaisy");
								seedingPlace.setSpot(newSpot);
							}
							// white
							else if (type.equals("whiteDaisy")) {
								Spot newSpot = new Spot(World.getAlbedoWhite(), "whiteDaisy", randomAge());
								whiteDaisy.add(newSpot);
								newSpot.setType("whiteDaisy");
								seedingPlace.setSpot(newSpot);
							}
						}
					}
				}

				// age = age + 1;
				// spot.setCurrentAge(age);

			}
		}
	}

	// The method to generate a random age of a daisy ( 1 ~ MAXAGE)
	public static int randomAge() {
		Random random = new Random();
		int age = random.nextInt(MAXAGE - 1);
		return age + 1;
	}

	public static void main(String[] args) {

		initial();

		// get the input and initialize the world
		Scanner scanner = new Scanner(System.in);
		System.out.println("please input the luminosity of the world(e.g. 0.8): ");
		System.out.println("please input the proportion of black daisy(e.g. 0.4): ");
		System.out.println("please input the proportion of white daisy(e.g. 0.4): ");
		System.out.println("please input the albedo of surface(e.g. 0.4): ");
		System.out.println("please input the albedo of black daisy(e.g. 0.25): ");
		System.out.println("please input the albedo of white daisy(e.g. 0.75): ");
		if (scanner.hasNext()) {
			World.setLuminosity(Double.valueOf(scanner.next()));
			World.setBlackProportion(Double.valueOf(scanner.next()));
			World.setWhiteProportion(Double.valueOf(scanner.next()));
			World.setAlbedoSurface(Double.valueOf(scanner.next()));
			World.setAlbedoBlack(Double.valueOf(scanner.next()));
			World.setAlbedoWhite(Double.valueOf(scanner.next()));
		}
		scanner.close();

		int blackDaisyNumTotal = (int) (SIZE * SIZE * World.getBlackProportion());
		int whiteDaisyNumTotal = (int) (SIZE * SIZE * World.getWhiteProportion());

		// Save the position of every daisy
		HashSet<Integer> position = new HashSet<Integer>();

		// Seed black daisies randomly
		// There are still some black daisies need to be generate
		while (blackDaisyNumTotal > position.size()) {
			int tempCount = position.size();
			// set the position of this daisy randomly
			int pos = (int) (Math.random() * (SIZE * SIZE));
			position.add(pos);

			// This random position is unoccupied by a daisy (add successfully)
			if (tempCount < position.size()) {
				Spot black = new Spot(World.getAlbedoBlack(), "blackDaisy", randomAge());
				blackDaisy.add(black);

				// Set the daisy spot on patch
				int row = pos / SIZE;
				int col = pos % SIZE;
				black.setType("blackDaisy");
				patch[row][col].setSpot(black);
			}
		}

		// Seed white daisies randomly
		while (whiteDaisyNumTotal > (position.size() - blackDaisyNumTotal)) {
			int tempCount = position.size();
			int pos = (int) (Math.random() * (SIZE * SIZE));
			position.add(pos);

			if (tempCount < position.size()) {
				Spot white = new Spot(World.getAlbedoWhite(), "whiteDaisy", randomAge());
				whiteDaisy.add(white);

				// Set the daisy spot on patch
				int row = pos / SIZE;
				int col = pos % SIZE;
				white.setType("whiteDaisy");
				patch[row][col].setSpot(white);
			}
		}

		//everyPatchTemp(); // ???
		//calculateGlobalTemp();  // add this, does this useful?

		// file operation
		int tick = 0;
		// Use Object instead of String, the function exportCsv(Object, buffer)
		Object[] titles = new String[] { "tick", "num_white", "num_black", "global_temp","global_pollution", "luminosity" };
		List<Object> titleList = Arrays.asList(titles);

		File file = null;
		BufferedWriter bufferWriter = null;
		try {
			file = new File("Daisyworld.csv");
			OutputStreamWriter ow = new OutputStreamWriter(new FileOutputStream(file), "gbk");
			bufferWriter = new BufferedWriter(ow);

			// write the title (first line) to the file
			exportCsv(titleList, bufferWriter);



		} catch (Exception e) {
			e.printStackTrace();
		}

		boolean notAllDead = true;
		int test = 200;

		while (test != 0) {
			tick++;



			everyPatchTemp();
			diffuse();
			ageWithTick();
			checkPollution();
			checkSurvival();
			calculateGlobalTemp();
			calPollution();
			pollutionChange();


			List<Object> list = new ArrayList<Object>();
			list.add(Integer.toString(tick));
			list.add(numWhites());
			list.add(numBlacks());
			calculateGlobalTemp();
			list.add(globalTempe);
			list.add(globalPollution);
			list.add(World.getLuminosity());
			try {
				exportCsv(list,bufferWriter);
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (numBlacks() == 0 && numWhites() == 0) {
				notAllDead = false;
			}

			test--;
		}

	}

	/**
	 * Increase the age of all daisies,
	 * and check whether it reaches the maximum age
	 */
	public static void ageWithTick() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				Spot tempSpot = patch[i][j].getSpot();
				String type = tempSpot.getType();

				// the patch is occupied by a daisy
				if (!type.equals("open")) {
					// increase the age
					int age = tempSpot.getCurrentAge();
					age = age + 1;
					tempSpot.setCurrentAge(age);

					if (tempSpot.getCurrentAge() == MAXAGE) {
						if (type.equals("blackDaisy")) {
							blackDaisy.remove(tempSpot);
						}else if (type.equals("whiteDaisy")) {
							whiteDaisy.remove(tempSpot);
						}
						// set this patch as open ground
						tempSpot.setType("open");
						patch[i][j].setSpot(tempSpot);
					}
				}
			}
		}
	}

	// Invoke the calculate method in Patch to calculate temperature on every patch
	public static void everyPatchTemp() {
		for (int i = 0; i < SIZE; i++){
			for (int j = 0; j < SIZE; j++){
				patch[i][j].calculateTemp();
			}
		}
	}

	// Get the neighbors of current patch
	public static ArrayList<Patch> getNeighbor(int row, int col) {

		// The array list to save the neighbors
		ArrayList<Patch> neighbors = new ArrayList<Patch>();

		// Top left corner
		if (row == 0 && col == 0) {
			neighbors.add(patch[row][col + 1]);
			neighbors.add(patch[row + 1][col]);
			neighbors.add(patch[row + 1][col + 1]);
		}
		// Top right corner
		else if (row == 0 && col == SIZE - 1) {
			neighbors.add(patch[row][col - 1]);
			neighbors.add(patch[row + 1][col]);
			neighbors.add(patch[row + 1][col - 1]);
		}
		// Bottom left corner
		else if (row == SIZE - 1 && col == 0) {
			neighbors.add(patch[row - 1][col]);
			neighbors.add(patch[row - 1][col + 1]);
			neighbors.add(patch[row][col + 1]);
		}
		// Bottom right corner
		else if (row == SIZE - 1 && col == SIZE - 1) {
			neighbors.add(patch[row][col - 1]);
			neighbors.add(patch[row - 1][col - 1]);
			neighbors.add(patch[row - 1][col]);
		}
		// Top line
		else if (row == 0) {
			neighbors.add(patch[row][col - 1]);
			neighbors.add(patch[row + 1][col - 1]);
			neighbors.add(patch[row + 1][col]);
			neighbors.add(patch[row + 1][col + 1]);
			neighbors.add(patch[row][col + 1]);
		}
		// Bottom line
		else if (row == SIZE - 1) {
			neighbors.add(patch[row][col - 1]);
			neighbors.add(patch[row - 1][col - 1]);
			neighbors.add(patch[row - 1][col]);
			neighbors.add(patch[row - 1][col + 1]);
			neighbors.add(patch[row][col + 1]);
		}
		// Left line
		else if (col == 0) {
			neighbors.add(patch[row - 1][col]);
			neighbors.add(patch[row - 1][col + 1]);
			neighbors.add(patch[row][col + 1]);
			neighbors.add(patch[row + 1][col + 1]);
			neighbors.add(patch[row + 1][col]);
		}
		// Right line
		else if (col == SIZE - 1) {
			neighbors.add(patch[row - 1][col]);
			neighbors.add(patch[row - 1][col - 1]);
			neighbors.add(patch[row][col - 1]);
			neighbors.add(patch[row + 1][col - 1]);
			neighbors.add(patch[row + 1][col]);
		}
		// Middle
		else {
			neighbors.add(patch[row - 1][col - 1]);
			neighbors.add(patch[row - 1][col]);
			neighbors.add(patch[row - 1][col + 1]);
			neighbors.add(patch[row][col - 1]);
			neighbors.add(patch[row][col + 1]);
			neighbors.add(patch[row + 1][col - 1]);
			neighbors.add(patch[row + 1][col]);
			neighbors.add(patch[row + 1][col + 1]);
		}

		return neighbors;
	}


	// Search an open ground in neighbors
	public static Patch findOpenNeighbor(int row, int col) {
		ArrayList<Patch> neighbors = getNeighbor(row, col);

		// There might be more than one empty neighbor
		ArrayList<Patch> emptyNeighbors = new ArrayList<Patch>();
		for (Iterator <Patch> it = neighbors.iterator(); it.hasNext();) {
			Patch tempPatch = it.next(); // get the current patch
			// The spot on this patch is open ground
			if (tempPatch.getSpot().getType().equals("open")) {
				emptyNeighbors.add(tempPatch);
			}
		}

		// choose one empty neighbor randomly
		int randomIndex = (int) (emptyNeighbors.size() * Math.random());
		//Patch empty = emptyNeighbors.get(randomIndex);
		int i = 0;
		Patch empty = null;
		for (Iterator <Patch> it = emptyNeighbors.iterator(); it.hasNext();) {
			Patch p = it.next();
			if(randomIndex == i)
				empty = p;
			i++;
		}
		return empty;
	}

	// Whether all neighbors are open ground, no possible to sprout
	public boolean anyDaisyInNeighbor(int row, int col) {
		boolean anyDaisy = false;
		ArrayList<Patch> neighbor = getNeighbor(row, col);
		for (int i = 0; i < neighbor.size(); i++) {
			String type = neighbor.get(i).getSpot().getType();
			if (type.equals("blackDaisy") || type.equals("whiteDaisy")) {
				anyDaisy = true;
				break;
			}
		}
		return anyDaisy;
	}

	// Calculate global temperature
	public static void calculateGlobalTemp() {
		double temp = 0;
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				temp = temp + patch[i][j].getTemperature();
			}
		}
		// global temperature is the mean temperature of all patches
		globalTempe = temp / (SIZE * SIZE);
	}

	// Output a csv file
	public static void exportCsv(List<Object> list, BufferedWriter bufferWriter) throws IOException {
		// write the content
		for (Object obj : list) {
			StringBuffer buf = new StringBuffer();
			String item = buf.append("\"").append(obj).append("\",").toString();
			bufferWriter.write(item);
		}
		bufferWriter.newLine();
		bufferWriter.flush();
	}
}
