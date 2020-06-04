package CARTES;

import JEU.Card;
import JEU.Grille;
import TUILE.Turtle;

public class CarteViolette extends Card{

	/*Droite*/
	@Override
	public void action(Turtle t,Grille g) {
		switch(t.getDir()) {
		case 'S':
			t.setDir('O');
			break;
		case 'N':
			t.setDir('E');
			break;
		case 'E':
			t.setDir('S');
			break;
		case 'O':
			t.setDir('N');
			break;
		}		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "violette";
	}

}