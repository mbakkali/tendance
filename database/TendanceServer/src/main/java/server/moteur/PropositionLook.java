package server.moteur;

import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

public class PropositionLook {

	private long date;
	private int sex;
	private int event;
	private int M = 1;
	private int weather;
	private float seuil = 0.05f;
	// liste de vetements hommes
	private final String ManShoes[] = { "sneakers", "boots", "ChaussuresVille", "tennis" };
	private final String ManClothesUp[] = { "chemise", "t-shirt", "polo" };
	private final String ManClothesDown[] = { "jeans", "pantalon", "short", };
	private final String ManClothesCoats[] = { "sweat", "pulls", "doudoune" };
	private final String ManClothesDressUp[] = { "costume", "blazer" };

	// liste de vetements femme
	private final String WoManShoes[] = { "talon", "ballerine", "chaussuresOuvertes", "ChaussuresVille", "sneakers" };
	private final String WoManClothesUp[] = { "chemise", "t-shirt", "polo" };
	private final String WoManClothesDown[] = { "pantalon", "jeans", "short", "jupe" };
	private final String WoManClothesCoats[] = { "sweat", "pulls", "doudoune" };
	private final String WoManClothesDressUp[] = { "robe", "combinaison" };
	private final String WoManClothesCDressUp[] = { "blazer" };

	private final String events[][] = { { "0", "1", "2", "3", "4","5" },
			{ "Casual", "GalaCocktail", "Enterement", "MariageBaptem","EntretienReunion","SoireeAmis" },
			{ "0", "1", "2", "3", "4", "5" } }; // association evenement a un
												// int dans la base de donn�e

	// Tableau d'analyse automatique des couts de liens remplir par l'algo de
	// machine learning
	// private final float tabMCCC[][] = new
	// float[ManClothesDown.length][events[0].length];
	private final float tabMCCC[][] = { { 0.33f, 0.17f, 0.07f, 0.12f, 0.23f, 0.22f },
										{ 0.27f, 0.13f, 0.13f, 0.1f, 0.22f, 0.21f },
										{ 0.08f, 0.0f, 0.0f, 0.0f, 0.0f, 0.19f } };

	private final float tabMCCT[][] = { { 0.21f, 0.23f, 0.0f, 0.16f, 0.1f, 0.22f },
			                            { 0.23f, 0.17f, 0.1f, 0.15f, 0.2f, 0.21f },
                                        { 0.19f, 0.0f, 0.0f, 0.0f, 0.0f, 0.18f } }; // ...

	private final float tabMCCP[][] = { { 0.23f, 0.25f, 0.0f, 0.17f, 0.1f, 0.23f },
			{ 0.19f, 0.15f, 0.1f, 0.16f, 0.2f, 0.22f }, { 0.2f, 0.0f, 0.0f, 0.0f, 0.0f, 0.18f } };

	private final float tabMCDrUp[][] = { { 0.12f, 0.4f, 0.6f, 0.58f, 0.45f, 0.18f },
			{ 0.17f, 0.28f, 0.4f, 0.55f, 0.4f, 0.19f }, { 0.15f, 0.27f, 0.4f, 0.54f, 0.4f, 0.17f },
			{ 0.2f, 0.3f, 0.3f, 0.3f, 0.1f, 0.2f },
			{ 0.2f, 0.32f, 0.57f, 0.24f, 0.3f, 0.2f }, { 0.21f, 0.33f, 0.5f, 0.23f, 0.3f, 0.2f }};

	private final float tabMS[][] = {
                                    { 0.33f, 0.12f, 0.1f, 0.1f, 0.1f, 0.5f },
                                    { 0.32f, 0.4f, 0.3f, 0.3f, 0.3f, 0.3f },
                                    { 0.2f, 0.4f, 0.6f, 0.6f, 0.6f, 0.1f },
			                        { 0.15f, 0.8f, 0.0f, 0.0f, 0.0f, 0.1f },
			                        { 0.3f, 0.32f, 0.1f, 0.25f, 0.25f, 0.27f },
			                        { 0.1f, 0.15f, 0.1f, 0.15f, 0.15f, 0.1f },
                                    { 0.3f, 0.33f, 0.8f, 0.6f, 0.6f, 0.4f },
                                    { 0.3f, 0.2f, 0.0f, 0.0f, 0.0f, 0.23f },
                                    { 0.55f, 0.0f, 0.0f, 0.0f, 0.0f, 0.6f },
                                    { 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f },
                                    { 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f },
                                    { 0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.4f },
			                        { 0.2f, 0.1f, 0.1f, 0.1f, 0.2f, 0.3f },
			                        { 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f },
                                    { 0.8f, 0.9f, 0.9f, 0.9f, 0.7f, 0.6f },
                                    { 0.0f, 0.0f, 0.0f, 0.0f, 0.1f, 0.1f }};

	private final float tabMC[][] = { {0.3f, 0.3f, 0.3f, 0.3f, 0.3f, 0.3f },
			{ 0.3f, 0.3f, 0.3f, 0.3f, 0.3f, 0.3f }, { 0.3f, 0.3f, 0.3f, 0.3f, 0.3f, 0.3f }};
	// MCCC0manclothescouplechemise,polo,t-shirt
	private final float tabWCCC[][] = new float[WoManClothesDown.length][events[0].length];
	private final float tabWCCT[][] = new float[WoManClothesDown.length][events[0].length]; // ...
	private final float tabWCCP[][] = new float[WoManClothesDown.length][events[0].length]; // ...
	private final float tabWCDrUp[][] = new float[WoManClothesUp.length][events[0].length]; // dressUp

	private final float tabWS[][] = new float[WoManShoes.length][events[0].length];
	private final float tabWC[][] = new float[WoManClothesCoats.length][events[0].length]; // ...

	// tabl resultat proba et associe� au look

	public long getDate() {
		return date;
	}

	public Map<Float, Tenue> proposerLook(int sex, int event, int weather) {

        final Map<Float, Tenue> items = new TreeMap<Float, Tenue>();
        final Map<Float, Tenue> finalItems = new TreeMap<Float, Tenue>();

		int index = 0;
		float produit = 0;
		float somme = 0;
		float res = 0;
		float res2 = 0;
		float temp2 = 0;

		for (int i = 0; i < events[0].length; i++) {

			if (Integer.parseInt(events[0][i]) == event) {
				index = i;
				break;
			}
		}

		if (sex == M) {

			for (int k = 0; k < ManClothesUp.length; k++) {
				for (int i = 0; i < ManClothesDown.length; i++) {

					for (int j = 0; j < ManShoes.length; j++) {

						if (k == 0) { // chemise
							produit = tabMCCC[i][index] * tabMS[(i*ManShoes.length)+j][index];
							somme = tabMCCC[i][index] + tabMS[(i*ManShoes.length)+j][index];
							res = produit * somme;
						} else if (k == 1) {// t-shirt
							produit = tabMCCT[i][index] * tabMS[(i*ManShoes.length)+j][index] ;
							somme = tabMCCT[i][index] + tabMS[(i*ManShoes.length)+j][index];
							res = produit * somme;

						} else { // polos
							produit = tabMCCP[i][index] * tabMS[(i*ManShoes.length)+j][index];
							somme = tabMCCP[i][index] + tabMS[(i*ManShoes.length)+j][index];
							res = produit * somme;

						}
						if (weather > 15) {
							Tenue LookEvents = new Tenue(ManClothesUp[k], ManClothesDown[i], ManShoes[j]);

							items.put((float) res, LookEvents);
							for (int l = 0; l < tabMCDrUp.length-3; l++) {
								temp2 = produit * tabMCDrUp[3+l][index];
								res2 = temp2 * (somme + tabMCDrUp[l+3][index]);
								Tenue LookEvents2 = new Tenue(ManClothesDressUp[1], ManClothesUp[k], ManClothesDown[i],
										ManShoes[j]);

								items.put((float) res2, LookEvents2);


							}
							for (int l = 0; l < tabMC.length - 2; l++) {
								temp2 = produit * tabMC[l][index];
								res2 = temp2 * (somme + tabMC[l][index]);
								Tenue LookEvents2 = new Tenue(ManClothesCoats[l], ManClothesUp[k], ManClothesDown[i],
										ManShoes[j]);

								items.put((float) res2, LookEvents2);
							}

						} else {

							
							temp2 = produit * tabMC[2][index];
							res2 = temp2 * (somme + tabMC[2][index]);
							Tenue LookEvents = new Tenue(ManClothesCoats[2], ManClothesUp[k], ManClothesDown[i],
									ManShoes[j]);

							items.put((float) temp2, LookEvents);

							
						}
						produit = 0;
						res = 0;
						temp2 = 0;
						res2 = 0;
						somme=0;
					}


				}

			}

			// les tenue habill� costume
			for (int k = 0; k < ManClothesDressUp.length-1; k++) {
				for (int i = 0; i < ManClothesUp.length; i++) {

					for (int j = 0; j < ManShoes.length; j++) {

						if (weather > 18) {
							/*System.out.println((3*ManShoes.length)+j);
							System.out.println(tabMS[(3*ManShoes.length)+j][index]);
							System.out.println(ManClothesUp[i]);
							System.out.println(tabMCDrUp[i][index]);*/
							produit = tabMCDrUp[i][index] * tabMS[(3*ManShoes.length)+j][index] ;
							res = produit * (tabMCDrUp[i][index] + tabMS[(3*ManShoes.length)+j][index]);
							Tenue LookEvents = new Tenue(ManClothesUp[i], ManClothesDressUp[k], ManShoes[j]);
							items.put((float) res, LookEvents);
							//System.out.println(LookEvents);
							//System.out.println(res);

						} else {

							produit = tabMCDrUp[i][index] * tabMS[(3*ManShoes.length)+j][index] * tabMC[3][index];
							res = produit * (tabMCDrUp[i][index] + tabMS[(3*ManShoes.length)+j][index] + tabMC[3][index]);
							Tenue LookEvents = new Tenue(ManClothesCoats[3], ManClothesUp[i], ManClothesDressUp[k],
									ManShoes[j]);

							items.put((float) res, LookEvents);

						}
					}

				}
			}
		} else {

			for (int k = 0; k < WoManClothesUp.length; k++) {
				for (int i = 0; i < tabWCCC.length; i++) {

					for (int j = 0; j < tabWS.length; j++) {

						if (k == 0) { // chemise
							produit = tabWCCC[i][index] * tabWS[j][index] * 10; // couples
							somme = tabWCCC[i][index] + tabWS[j][index];
							res = produit * somme;

						} else if (k == 1) {// t-shirt
							produit = tabWCCT[i][index] * tabWS[j][index] * 10;
							somme = tabWCCT[i][index] + tabWS[j][index];
							res = produit * somme;

						} else { // polos
							produit = tabWCCP[i][index] * tabWS[j][index] * 10;
							somme = tabWCCP[i][index] + tabWS[j][index];
							res = produit * somme;

						}
						if (weather > 15) {
							Tenue LookEventsF = new Tenue(WoManClothesUp[k], WoManClothesDown[i], WoManShoes[j]);
							items.put((float) res, LookEventsF);

							for (int l = 0; l < tabWC.length - 1; l++) {
								temp2 = produit * tabWC[l][index] * 10;
								res2 = temp2 * (somme + tabWC[l][index]);
								Tenue LookEvents2 = new Tenue(ManClothesCoats[l], ManClothesUp[k], ManClothesDown[i],
										ManShoes[j]);

								items.put((float) res2, LookEvents2);

							}
						} else {

							// for (int l = 0; l < tabWC.length; l++) {
							temp2 = produit * tabWC[3][index] * 10;
							res2 = temp2 * (somme + tabWC[3][index]);
							res = temp2 * somme;

							Tenue LookEventsF = new Tenue(WoManClothesCoats[3], WoManClothesUp[k], WoManClothesDown[i],
									WoManShoes[j]);

							items.put((float) temp2, LookEventsF);

							// }
						}
						produit = 0;
						res = 0;
						temp2 = 0;
						res2 = 0;
						somme=0;
					}
					

				}

			}

			// les tenue habill�es
			for (int k = 0; k < WoManClothesDressUp.length; k++) {
				for (int i = 0; i < tabWCDrUp.length; i++) {

					for (int j = 0; j < tabWS.length; j++) {

						if (weather > 18) {
							produit = tabWCDrUp[i][index] * tabWS[j][index] * 10;
							res = produit * (tabWCDrUp[i][index] + tabWS[j][index]);
							Tenue LookEventsF = new Tenue(null, WoManClothesUp[i], WoManClothesDressUp[k],
									WoManShoes[j]);
							items.put((float) res, LookEventsF);

							// blazer prit en compte
							produit = tabWCDrUp[i][index] * tabWS[j][index] * tabWC[2][index] * 100;
							res = produit * (tabWCDrUp[i][index] + tabWS[j][index] + tabWC[2][index]);
							Tenue LookEventsF2 = new Tenue(WoManClothesCoats[2], WoManClothesUp[i],
									WoManClothesDressUp[k], WoManShoes[j]);
							items.put((float) res, LookEventsF2);

						} else {

							produit = tabWCDrUp[i][index] * tabWS[j][index] * tabWC[4][index] * 100;
							res = produit * (tabWCDrUp[i][index] + tabWS[j][index] + tabWC[4][index]);
							Tenue LookEventsF = new Tenue(WoManClothesCoats[4], WoManClothesUp[i],
									WoManClothesDressUp[k], WoManShoes[j]);

							items.put((float) temp2, LookEventsF);

						}
					}

				}
			}

		}

		// Parcours les keys et affiche celle superieur a seuil
		int kk = 0;
        List<Float> keys = (List<Float>) items.keySet();
        Collections.reverse(keys);
		for (float key : keys) {
			if (key > seuil) {
				System.out.println("Cle : " + key);
				//System.out.println("Cl� : " + items.get(key));
				finalItems.put(key, items.get(key));

				kk++;
			}
            if(kk == 3)
                break;
		}

		return finalItems;
	}

	@Override
	public String toString() {
		return "Parametre{" + "date=" + date + ", sex='" + sex + '\'' + ", event='" + event + '\'' + ", weather="
				+ weather + '}';
	}

}
