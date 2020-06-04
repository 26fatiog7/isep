package JEU;

import java.lang.reflect.InvocationTargetException;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

public class ButtonEnable extends Thread {
	 
    private JButton[] lesBoutons;
    public ButtonEnable(JButton[] lesBoutons) {
       this.lesBoutons = lesBoutons;
    }
    public void buttunEnable(){ 
        //faire des trucs
       try {
		SwingUtilities.invokeAndWait(new Runnable(){
		       public void run(){
		          for (JButton bouton : lesBoutons)
		            bouton.setEnabled(false);
		       }
		   });
	} catch (InvocationTargetException | InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
}
