package server.moteur;
import java.util.*;

public class Main {
				
		private static Scanner sc;

		public static void main(String[] args){
			
			System.out.println("Proposition look");
			
			System.out.println("Test Homme");
		
			PropositionLook propositionlook = new PropositionLook();
			
			Map<Float, Tenue> user1 = propositionlook.proposerLook(1, 1, 27);		
			
			System.out.println(user1);
			
			for (float key : user1.keySet()) {
				
					System.out.println("Cl� : " + key);
					System.out.println("une des tenues est : " + user1.get(key));
					System.out.println("Cette tenue vous convient telle ? 0 pour oui et 1 pour non");
					sc = new Scanner(System.in);
					String str = sc.nextLine();
					System.out.println(str);
					if (str=="0"){
						System.out.println("end");
						break;}		
			}
					
			System.out.println("Test Femme");
			
			//Map<Float, Tenue> user2 = propositionlook.proposerLook(0, 4, 20);		
			//PropositionLook user2 = new PropositionLook("feminin", "mariage", 7);

			//System.out.println(user2);
			
	
		}

}