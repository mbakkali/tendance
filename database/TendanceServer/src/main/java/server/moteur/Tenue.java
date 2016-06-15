package server.moteur;

import java.util.ArrayList;
import java.util.List;

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

	public List<String> toJson() {
        List<String> json = new ArrayList<>();
        if(coast != null)
            json.add(coast);
        json.add(clothesUp);
        json.add(clothesDown);
        json.add(shoes);
        return json;
	}
	

}
