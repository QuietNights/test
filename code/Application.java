package code;

import java.awt.EventQueue;
import javax.swing.JFrame;


public class Application extends JFrame {
    
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 320;
	private static final int HEIGHT = 240;

	public Application() {

        initUI();
    }

    private void initUI() {


        //Board board = new Board();

        //add(new Board());

        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }    
    
    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Application ex = new Application();
                ex.setVisible(true);
            }
        });
    }
}