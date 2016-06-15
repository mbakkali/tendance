package test1;

public class Tenue {
	
	private String coast;
	private String clothesUp;
	private String clothesDown;
	private String shoes;



	public  Tenue(String coast, String clothesUp, String clothesDown, String shoes) {
		this.coast = coast;
		this.clothesUp = clothesUp;
		this.clothesDown = clothesDown;
		this.shoes = shoes;
	}
	
	
	public  Tenue(String clothesUp, String clothesDown, String shoes) {
		this.clothesUp = clothesUp;
		this.clothesDown = clothesDown;
		this.shoes = shoes;

	}
	
	@Override
	public String toString() {
		if (coast!=null){
			return "Tenue{" + coast + "," + clothesUp + "," + clothesDown + "," + shoes +"}";
		}else{
			return "Tenue{" + clothesUp + "," + clothesDown + "," + shoes +"}";
			
		}
	}
	

}
