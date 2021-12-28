package launcher;
import GUI.TetrisGUI;

public class Launcher {

	public static void main(String [] args) {

        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	try {
            		@SuppressWarnings("unused")
					TetrisGUI frame = new TetrisGUI();
            		
            	}
            	catch(Exception e) {
            		e.printStackTrace();
            	}
            }
        });
    }
}