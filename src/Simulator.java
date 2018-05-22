import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.Scanner;

public class Simulator {
	static double  blackProportion=0;
	static double whiteProportion=0;
	static double albedoSurface=0;
	static double albedoBalck=0;
	static double albedoWhite=0;
	static World world;

	public static void main(String[] args) {

		Scanner scanner =new Scanner(System.in);
		System.out.println("please input the luminosity of the world(e.g. 0.8): ");
		System.out.println("please input the proportion of black daisy(e.g. 0.4): ");
		System.out.println("please input the proportion of white daisy(e.g. 0.4): ");
		System.out.println("please input the albedo of surface(e.g. 0.4): ");
		System.out.println("please input the albedo of black daisy(e.g. 0.25): ");
		System.out.println("please input the albedo of white daisy(e.g. 0.75): ");
		if(scanner.hasNext()){
			World.luminosity=Double.valueOf(scanner.next());
			blackProportion=Double.valueOf(scanner.next());
			whiteProportion=Double.valueOf(scanner.next());
			albedoSurface=Double.valueOf(scanner.next());
			albedoBalck=Double.valueOf(scanner.next());
			albedoWhite=Double.valueOf(scanner.next());
		}

		world = new World(20,20, blackProportion,whiteProportion);
		world.patch =new Patch[20][20];
		for(int wid=0; wid<20; wid++){
			for (int leng=0; leng<20; leng++){
				Spot spot = generateSpot();
				int pollution =getPollution();
				world.patch[wid][leng] = new Patch(wid,leng,spot,pollution);
				world.patch[wid][leng].setTemperature(World.luminosity*world.patch[wid][leng].spot.getAlbedo());
			}
		}

		int totalPath=20*20;
		int tick=0;
		int blackDaisyNum=0;
		int whiteDaisyNum=0;
		int openSpotNum=0;
		double averagePollutionLevel=0;
		double averageTemp=0;
		boolean notalldead=true;
		while(notalldead){
			tick++;
			for(int wid=0; wid<20; wid++){
				for (int leng=0; leng<20; leng++){
					//calculate temperature on each path
					double[] neighborTemp =new double[4];
					if(wid-1>=0){
						neighborTemp[0]=world.patch[wid-1][leng].getTemperature();
					}
					if(wid+1<20){
						neighborTemp[1]=world.patch[wid+1][leng].getTemperature();
					}
					if(leng-1>=0){
						neighborTemp[2]=world.patch[wid][leng-1].getTemperature();
					}
					if(leng+1<20){
						neighborTemp[3]=world.patch[wid][leng+1].getTemperature();
					}
					world.patch[wid][leng].calculateTemp(neighborTemp);
					//check survival on each path
					world.patch[wid][leng].checkSurvival();
					//check reproduce on each path
					world.patch[wid][leng].checkReproduce();

					averagePollutionLevel=averagePollutionLevel+world.patch[wid][leng].getPollution();
					averageTemp=averageTemp+world.patch[wid][leng].getTemperature();
					if(world.patch[wid][leng].spot.type=="blackDaisy"){
						blackDaisyNum++;
						world.patch[wid][leng].spot.currentAge++;
					}
					else if(world.patch[wid][leng].spot.type=="whiteDaisy"){
						whiteDaisyNum++;
						world.patch[wid][leng].spot.currentAge++;
					}
					else{
						openSpotNum++;
					}

					world.patch[wid][leng].pollutionChange();
				}
			}
			averagePollutionLevel=averagePollutionLevel/totalPath;
			averageTemp=averageTemp/totalPath;
			System.out.println("tick"+tick);
			System.out.println("blackDaisyNum: "+blackDaisyNum);
			System.out.println("whiteDaisyNum: "+whiteDaisyNum);
			System.out.println("openSpotNum: "+openSpotNum);
			System.out.println("average pollution level: "+averagePollutionLevel);
			System.out.println("average temperature of the world: "+averageTemp);

			if(blackDaisyNum==0 && whiteDaisyNum==0){
				notalldead=false;
			}
			openSpotNum=0;
			blackDaisyNum=0;
			whiteDaisyNum=0;
			averagePollutionLevel=0;
			averageTemp=0;
		}
	}

	//return random number(1-10)
	public static int randomAge(){
		java.util.Random random=new java.util.Random();
		int result=random.nextInt(9);
		return result+1;
	}
	//return random type of spot
	public static Spot generateSpot(){
		double black = blackProportion*10;
		double white = whiteProportion*10;
		java.util.Random random=new java.util.Random();
		int result=random.nextInt(9);
		//generate black daisy instance
		if(0<=(double)result && (double)result<black){
			//System.out.println("generate black");
			return new Spot(albedoBalck,"blackDaisy",randomAge(),15,45);
		}
		//generate white daisy instance
		else if(black<=(double)result && (double)result<black+white){
			//System.out.println("generate white");
			return new Spot(albedoWhite,"whiteDaisy",randomAge(),45,75);
		}
		//generate open spot instance
		else{
			//System.out.println("generate open");
			return new Spot(albedoSurface,"open");
		}
	}
	//return the pollution level
	public static int getPollution(){
		java.util.Random random=new java.util.Random();
		int result=random.nextInt(2);
		return result;
	}
}
