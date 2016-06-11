package test1;

import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

public class PropositionLook {

	private final long date;
	private final int sex;
	private final int event;
	private final int weather;
	private final float seuil = 0.4f;
	private String coast;
	private String clothesUp;
	private String clothesDown;
	private String shoes;
	private String clothesDressUp;

	// liste de vetements hommes
	private final String ManShoes[] = { "tennis", "sneakers", "boots", "ChaussuresVille" };
	private final String ManClothesUp[] = { "chemise", "t-shirt", "polos" };
	private final String ManClothesDown[] = { "pantalon", "jeans", "short", };
	private final String ManClothesCoats[] = { "sweat", "blazer", "doudoune", "pulls" };
	private final String ManClothesDressUp[] = { "costume" };

	// liste de vetements femme
	private final String WoManShoes[] = { "talon", "ballerine", "chaussuresOuvertes", "ChaussuresVille", "sneakers" };
	private final String WoManClothesUp[] = { "chemise", "t-shirt" };
	private final String WoManClothesDown[] = { "pantalon", "jeans", "short", "jupe" };
	private final String WoManClothesCoats[] = { "sweat", "blazer", "doudoune", "pulls" };
	private final String WoManClothesDressUp[] = { "robe", "combinaison" };


	private final String events[][] = { { "5", "3", "2", "4", "1" },
			{ "Casual", "GalaCocktail", "Enterement", "MariageBaptem", "SoireeAmis", "EntretienReunion" },
			{ "1", "2", "3", "4", "5", "6" } }; // association evenement a un
												// int dans la base de donnée

	// Tableau d'analyse automatique des couts de liens remplir par l'algo de
	// machine learning
	private final float tabMCCC[][] = new float[ManClothesDown.length][events[0].length]; // tableau
																							// de
																							// coupe
																							// hauts
																							// et
																							// bas
																							// le
																							// dernier
																							// C
																							// pour
																							// chmise
	private final float tabMCCT[][] = new float[ManClothesDown.length][events[0].length]; // ...
																							// T
																							// pour
																							// tshit
	private final float tabMCCP[][] = new float[ManClothesDown.length][events[0].length]; // ...
																							// P
																							// pour
																							// polos
	private final float tabMCDrUp[][] = new float[ManClothesUp.length][events[0].length]; // dressUp

	private final float tabMS[][] = new float[ManShoes.length][events[0].length]; // couts
																					// de
																					// chaussure
																					// par
	private final float tabMC[][] = new float[ManClothesCoats.length][events[0].length]; // ...

	// MCCC0manclothescouplechemise,polo,t-shirt
	private final float tabWCCC[][] = new float[WoManClothesDown.length][events[0].length];
	private final float tabWCCT[][] = new float[WoManClothesDown.length][events[0].length]; // ...
	private final float tabWCCP[][] = new float[WoManClothesDown.length][events[0].length]; // ...
	private final float tabWCDrUp[][] = new float[WoManClothesUp.length][events[0].length]; // dressUp

	private final float tabWS[][] = new float[WoManShoes.length][events[0].length];
	private final float tabWC[][] = new float[WoManClothesCoats.length][events[0].length]; // ...

	// tabl resultat proba et associeé au look
	private final List<Float> res = new ArrayList<Float>(); // test plus
															// tuiliser enfin a
															// supprimer

	private final static Map<Float, Tenue> items = new TreeMap<Float, Tenue>(); // map
																				// class
																				// tenue
																				// a
																				// une
																				// key=proba
																				// final

	public long getDate() { // pas utile
		return date;
	}

	
	/* Todo ce weeknd apres reunion
	 * faire la meme pour sex feminin
	 * retourner la liste dans le main
	 * proposer 1 a 1 les hasbits de la liste et faire que l'utilisateur choissise sa prefere sur 3 look
	 *
	 */
	
	/*TODO plus
	 * ameliorer l'algo pour meilleurs prise en main meteo trouvé formule adequat produit etant null et pas representatif
	 * comparer a l'armoire
	 * voir couleur
	 */
	public PropositionLook(int sex, int event, int weather) {

		this.sex = sex;
		this.event = event;
		this.weather = weather;
		this.date = System.currentTimeMillis();
		int index = 0;
		float temp = 0;
		float temp2 = 0;

		if (this.sex == 1) { // je suis un homme

			for (int i = 0; i < events[0].length; i++) {

				if (Integer.parseInt(events[0][i]) == this.event) {
					index = i; // index contient le numero de la colonne associer a l'evenement
					i = events[0].length; //sort de la boucle une fois trouvé
				}
			}
			// Afficher le résultat
			System.out.println("Valeur trouvé à l'index: " + index); 
																		

			for (int k = 0; k < ManClothesUp.length; k++) {
				System.out.println(ManClothesUp[k]);
				for (int i = 0; i < tabMCCC.length; i++) {

					for (int j = 0; j < tabMS.length; j++) {

						if (k == 0) { // chemise
							temp = tabMCCC[i][index] * tabMS[j][index]; // couples
																		// (chemise,clothesdown[i])*clothesshoes[j]
						} else if (k == 1) {// t-shirt
							temp = tabMCCT[i][index] * tabMS[j][index];

						} else { // polos
							temp = tabMCCP[i][index] * tabMS[j][index];

						}
						if (this.weather > 22) {
							Tenue LookEvents = new Tenue(null, ManClothesUp[k], ManClothesDown[i], ManShoes[j]); // si
																													// meteo
																													// superieur
																													// a
																													// 22
																													// degré
																													// pas
																													// de
																													// pull
							res.add(temp);
							items.put((float) temp, LookEvents); // map key et
																	// tenue
							System.out.println(LookEvents);
							System.out.println(items); // stocket nom chelou
														// mais normalent ca
														// psse
						} else {

							for (int l = 0; l < tabMC.length; l++) {
								temp2 = temp * tabMC[l][index]; // s'il fait
																// froid on
																// ajoute un
																// coast
								Tenue LookEvents = new Tenue(ManClothesCoats[l], ManClothesUp[k], ManClothesDown[i],
										ManShoes[j]);

								items.put((float) temp2, LookEvents);

								res.add(temp2);
							}
						}
					}
					temp = 0; // me rassure que la valeur de temp est
								// reinitialiser a la fin de chaque calcule

				}

			}

			// la meme avec les tenue habillé costume
			for (int k = 0; k < ManClothesDressUp.length; k++) {
				System.out.println(ManClothesDressUp[k]);
				for (int i = 0; i < tabMCDrUp.length; i++) {

					for (int j = 0; j < tabMS.length; j++) {

						if (this.weather > 22) {
							temp = tabMCDrUp[i][index] * tabMS[j][index];
							Tenue LookEvents = new Tenue(null, ManClothesUp[i], ManClothesDressUp[k], ManShoes[j]);
							res.add(temp);
							items.put((float) temp, LookEvents);
							System.out.println(items);
						} else {
							for (int l = 0; l < tabMC.length; l++) {
								temp = tabMCDrUp[i][index] * tabMS[j][index] * tabMC[l][index];
								Tenue LookEvents = new Tenue(ManClothesCoats[l], ManClothesUp[i], ManClothesDressUp[k],
										ManShoes[j]);

								items.put((float) temp, LookEvents);

								res.add(temp);

							}
						}
					}

				}
			}
		}
		// fin de la partie homme faire la meme femme
		// ajout d'une tenue en dure pour test

		Tenue LookEvents1 = new Tenue(ManClothesCoats[0], ManClothesUp[1], ManClothesDressUp[0], ManShoes[0]);
		items.put((float) 0.6, LookEvents1);
		// Parcours les keys et affiche celle superieur a seuil
		for (float key : items.keySet()) {
			if (key > seuil)
				// faire en sorte que la classe retourne la liste de tenue
				System.out.println("Clé : " + key);
		}
		res.add(0.4f);
		res.add(0.9f);
		res.add(0.5f);
		System.out.println(res);

		Collections.sort(res);
		Collections.reverse(res);
		System.out.println(res);

	}

	@Override
	public String toString() {
		return "Parametre{" + "date=" + date + ", sex='" + sex + '\'' + ", event='" + event + '\'' + ", weather="
				+ weather + '}';
	}

}
