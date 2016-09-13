import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.geom.Point2D;
import java.net.MalformedURLException;
import java.net.URL;

import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.omg.CORBA.portable.InputStream;
import  sun.audio.*;    //import the sun.audio package
import  java.io.*;

/*ALLOW TO get bounding box
 * player writes on screen
 * player clicks and it draws in boundingbox that they clicked
 */
public class GameBoard{

	public static JFrame framer;

	public GameBoard() {
		// TODO Auto-generated constructor stub


	}


	public static void main(String[] args) {
			
		try {
            // Set cross-platform Java L&F (also called "Metal")
        UIManager.setLookAndFeel(
        		UIManager.getSystemLookAndFeelClassName());
    } 
    catch (UnsupportedLookAndFeelException e) {
       // handle exception
    }
    catch (ClassNotFoundException e) {
       // handle exception
    }
    catch (InstantiationException e) {
       // handle exception
    }
    catch (IllegalAccessException e) {
       // handle exception
    }
		//creates tictactoe board
		GameBoard play = new GameBoard();
		createGameBoard();
        
		//plays audio
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/Sounds/035.wav").getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch(Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	

	}

	//creates gameboard and adds players
	private static void createGameBoard(){
		
		JFrame frame = new JFrame("Tic Tac Toe");
		framer = frame;
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Dimension d = (Toolkit.getDefaultToolkit().getScreenSize());
		d = new Dimension(((int)d.getWidth()), ((int)d.getHeight() -30));
		frame.setPreferredSize(d);
		frame.setBackground(new Color(255, 192, 203));
		frame.pack();

		GameBoard.Lines ticTac = new GameBoard.Lines();

		
		frame.add(ticTac);
		frame.revalidate();
		frame.repaint();
		
		frame.setVisible(true);

		//create boxes where x and o can be drawn
		int lineHorizontal = (int) frame.getContentPane().getSize().getWidth()/3;
		int lineVertical = (int) frame.getContentPane().getSize().getHeight()/3;

		InvisibleBox[] boxes = new InvisibleBox[9];
		for( int i = 0; i < 3; i++){
			for( int k = 0; k < 3; k++){

				Point topLeft = new Point(k*lineHorizontal, i*lineVertical);
				Point bottomRight = new Point((k+1)*lineHorizontal, (i+1)*lineVertical);
				boxes[k*3 +i] = new InvisibleBox(topLeft, bottomRight);
			}
			//boxes[k*3 +i] = new InvisibleBox(topLeft, bottomRight);

		}


		Player[] players = Player.whoGoesFirst();
		Player.boxes = boxes;
		Player.frame = frame.getContentPane();
		frame.getContentPane().addMouseListener(players[0]);

	}

	public static class Lines extends JPanel{

		public void paintComponent(Graphics g)
		{
			this.setOpaque(false);
			Container contain = framer.getContentPane();
			super.paintComponent(g);
			Graphics2D g2 =(Graphics2D)g;
			g2.setColor(Color.BLACK);
			g2.setStroke(new BasicStroke( 20, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));

			int lineHorizontal = (int) contain.getSize().getWidth();
			int lineVertical = (int) contain.getSize().getHeight();

			g2.drawLine(lineHorizontal/3, 0,lineHorizontal/3, lineVertical ); 
			g2.drawLine(2*lineHorizontal/3, 0, 2*lineHorizontal/3,lineVertical);
			g2.drawLine(0,lineVertical/3, lineHorizontal, lineVertical/3);
			g2.drawLine(0, 2*lineVertical/3,lineHorizontal, 2*lineVertical/3);
		}

	}
}
