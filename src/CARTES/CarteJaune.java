package CARTES;


import JEU.Card;
import JEU.Grille;
import TUILE.Turtle;

public class CarteJaune extends Card{
	
	/*Gauche*/
	@Override
	public void action(Turtle t,Board g) {
		switch(t.getDir()) {
		case 'S':
			t.setDir('E');
			break;
		case 'N':
			t.setDir('O');
			break;
		case 'E':
			t.setDir('N');
			break;
		case 'O':
			t.setDir('S');
			break;
		}		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "jaune";
	}

}