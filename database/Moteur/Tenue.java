package test1;

public class Tenue {
	
	private String coast;
	private String clothesUp;
	private String clothesDown;
	private String shoes;
	private String clothesDressUp;



	public  Tenue(String coast, String clothesUp, String clothesDown, String shoes) {
		this.coast = coast;
		this.clothesUp = clothesUp;
		this.clothesDown = clothesDown;
		this.shoes = shoes;
	}
	
	
	public  Tenue(String coast, String clothesDressUp, String shoes) {
		this.coast = coast;
		this.clothesDressUp = clothesDressUp;
		this.shoes = shoes;

	}
	

}
