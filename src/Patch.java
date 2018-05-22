import java.util.ArrayList;

public class Patch {
	//coordinate in the world
	int xCoord;
	int yCoord;
	//the spot on this patch
	Spot spot;
	//the pollution level of the patch
	int pollution;
	//the calculated temperature on this patch
	double temperature;

	//the constructor for patch
	public Patch(int xCoord, int yCoord, Spot spot, int pollution){
		this.xCoord=xCoord;
		this.yCoord=yCoord;
		this.spot=spot;
		this.pollution=pollution;
	}

	/**
	 * calculate the temperature on this patch
	 * the energy absorbed by the daisy or the surface on this patch
	 * and the diffusion of 50% of the temperature between its neighbors.
	 * @param neighborTemp all the temperature of surround patch
	 */
	public void calculateTemp(double [] neighborTemp){
		temperature= World.luminosity*this.spot.getAlbedo();
		for(int i=0; i<neighborTemp.length-1; i++){
			temperature =temperature+neighborTemp[i]*0.5;
		}
	}

	/**
	 * check whether or not the daisy on this patch can survival
	 * currentAge = maxAge skill the daisy immediately
	 * pollution is 0, no influence
	 * pollution is 1, reduce maxAge each tick
	 * pollution is 2, skill the daisy immediately
	 * temperature not in the survival range, skill the daisy immediately
	 */
	public void checkSurvival(){
		if(spot.getCurrentAge()==spot.maxAge && (spot.getType()=="blackDaisy" || spot.getType()=="whiteDaisy")){
			this.spot = new Spot(Simulator.albedoSurface, "open");
		}else if(temperature<spot.getMinTempe() && temperature>spot.getMaxTempe()){
			this.spot =new Spot(Simulator.albedoSurface,"open");
		}else if(pollution==1){
			this.spot.setMaxAge(this.spot.getMaxAge()-1);
		}else if(pollution==2){
			this.spot =new Spot(Simulator.albedoSurface,"open");
		}
	}

	/**
	 * pollution is 2, can not reproduce daisy
	 * check if the open spot can reproduce daisy
	 * within the suitable temp can randomly plant blackdaisy or whitedaisy both with 50% chance
	 * if the temperature between 22.5 and 44.5 the spot has 20% chance to produce whiteDaisy
	 * if the temperature between 2.5 and 22.5 the spot has 20& chance to produce blackDaisy
	 */
	public void checkReproduce(){
		if(pollution!=2){
			if(spot.getType()=="open"){
				if(temperature==22.5){
					if(randomrate()<5){
						this.spot=new Spot(Simulator.albedoBalck,"blackDaisy",Simulator.randomAge(),2.5,22.5);
					}else{
						this.spot=new Spot(Simulator.albedoWhite,"whiteDaisy",Simulator.randomAge(),22.5,42.5);
					}
				}
				if(22.5<temperature && temperature<42.5){
					if(randomrate()<2){
						this.spot=new Spot(Simulator.albedoWhite,"whiteDaisy",Simulator.randomAge(),22.5,42.5);
					}
				}
				if(2.5<temperature && temperature<22.5){
					if(randomrate()<2){
						this.spot=new Spot(Simulator.albedoBalck,"blackDaisy",Simulator.randomAge(),2.5,22.5);
					}
				}
			}
		}

	}

	/**
	 * spot with daisy has 10% chance to recover from pollution( from 1 to 0)
	 * spot with pollution=1 has 10 % chance to change to pollution=2
	 * spot with pollution=2 has no chance to recover
	 * with pollution=0 has 10% change to change to pollution=1
	 */
	public void pollutionChange(){
		if(pollution==1){
			if(0<=randomrate() && randomrate()<1){
				pollution=2;
			}
			if(1<=randomrate() && randomrate()<2 && spot.getType()!="open"){
				pollution=0;
			}
		}
		if(pollution==0){
			if(0<=randomrate() && randomrate()<1){
				pollution=1;
			}
		}
	}

	public int randomrate(){
		java.util.Random random=new java.util.Random();
		int result=random.nextInt(9);
		return result;
	}



	public int getxCoord() {
		return xCoord;
	}

	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	public int getyCoord() {
		return yCoord;
	}

	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}

	public int getPollution() {
		return pollution;
	}

	public void setPollution(int pollution) {
		this.pollution = pollution;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
}
