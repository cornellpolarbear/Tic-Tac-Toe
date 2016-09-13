import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

//import GameBoard.Lines;

public class Player implements MouseListener{
	public String name;
	public static InvisibleBox[] boxes;
	public static Container frame;
	//x = 0, O = 1
	public int xOrO;
	public static Player[] bothPlayer;
	public static int clicks;
	//public static Lines line;

	public Player(String named) {
		// TODO Auto-generated constructor stub
		name = named;
	}


	public static Player[] whoGoesFirst (){
		//Player.JOptionPaneMultiInput();
		JTextField field1 = new JTextField();
		JTextField field2 = new JTextField();
		JTextField field3 = new JTextField();

		Object[] message = {
				"Name of Player 1:", field1, 
				"X or O:", field2,
				"Name of Player 2:", field3,
		};
		int option = JOptionPane.showConfirmDialog(null, message, "Enter all your values", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION)
		{
			String value1 = field1.getText();
			String value2 = field2.getText();
			String value3 = field3.getText();

		}

		Player firstPlayer = new Player(field1.getText());
		String letter = field2.getText();

		letter = letter.toLowerCase();


		Player secondPlayer = new Player(field3.getText());
		if(letter.equals("x")){
			firstPlayer.xOrO = 0;
			secondPlayer.xOrO = 1;
		}

		else {
			firstPlayer.xOrO = 1;
			secondPlayer.xOrO = 0;
		}
		Player[] players = {firstPlayer , secondPlayer};
		bothPlayer = players;
		return players;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		// TODO Auto-generated method stub
		//Create static inner class to draw finishing line
		boolean finished = false;
		int i = 0;
		while( finished != true){
			//System.out.println("why");

			if(boxes[i].containsPoint(e.getPoint())){
				if(boxes[i].drewX == true || boxes[i].drewO == true){
                i = 0;
                break;
				}
				else{
					boxes[i].drawXOrO(frame.getGraphics(), this.xOrO);
					finished = true;
				}
           

			}
			i++;
		}


		clicks++;
		boolean done = false;
		if(clicks > 2){
			//boxes[0].drawIt(frame.getGraphics(),10, 100, 800, 100);

			done = this.finished();
		}

		if(done == false){
			if(this == bothPlayer[0]){
					System.out.println(frame.getMouseListeners().length);
				
				//frame.removeMouseListener(frame.getMouseListeners()[0]);
					frame.removeMouseListener(this);
				frame.addMouseListener(bothPlayer[1]);
			}
			else{
			
				frame.removeMouseListener(frame.getMouseListeners()[0]);
				frame.addMouseListener(bothPlayer[0]);
			}
		}
		else{
			//System.out.println(frame.getMouseListeners());
			frame.removeMouseListener(bothPlayer[1]);
			frame.removeMouseListener(bothPlayer[0]);
			//System.out.println(frame.getMouseListeners());
			//frame.removeMouseListener(frame.getMouseListeners()[0]);
			//bothPlayer[1].ge
		}

		/*
		clicks++;
		if(clicks > 2){
			if(boxes[0].drew == true && boxes[3].drew ==true && boxes[6].drew == true){
				/*TO DO
		 * Make draw finishing line mehtod in invisible boxes that takes 3 boxes as positions
		 * add dialog box

				System.out.println("drawing");
				InvisibleBox.drawing(frame.getGraphics(), (int)boxes[0].topLeft.getX(), (int)(boxes[0].topLeft.getY() - boxes[0].bottomRight.getY())/2,
						(int)boxes[2].bottomRight.getX(), (int)(boxes[0].topLeft.getY() - boxes[0].bottomRight.getY())/2);
			}
		}
		System.out.println("clicks" + clicks + "  drew"+ boxes[1].drew);
		 */
	}
/*
	public static int JOptionPaneMultiInput() {

		JTextField field1 = new JTextField();
		JTextField field2 = new JTextField();
		JTextField field3 = new JTextField();

		Object[] message = {
				"Name of Player 1:", field1, 
				"X or O:", field2,
				"Name of Player 2:", field3,
		};
		int option = JOptionPane.showConfirmDialog(null, message, "Enter all your values", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION)
		{
			String value1 = field1.getText();
			String value2 = field2.getText();
			String value3 = field3.getText();

		}
		return option;
	}
	*/
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	public boolean finished(){
		if(boxes[0].sameSymbol(boxes[3], boxes[6])){
			boxes[0].drawIt(frame.getGraphics(),(int)boxes[0].topLeft.getX(),
					(int)((boxes[0].topLeft.getY() - (int)boxes[6].bottomRight.getY())/-2), (int)boxes[6].bottomRight.getX(), (int)((boxes[0].topLeft.getY() - (int)boxes[6].bottomRight.getY())/-2), this.name);
			return true;
		}
		else if(boxes[1].sameSymbol(boxes[4], boxes[7])){
			boxes[1].drawIt(frame.getGraphics(),(int)boxes[1].topLeft.getX(), (int)(boxes[0].bottomRight.getY() + (boxes[1].topLeft.getY() - boxes[7].bottomRight.getY())/-2), 
					(int)boxes[7].bottomRight.getX(), (int)(boxes[0].bottomRight.getY() + (boxes[1].topLeft.getY() - (int)boxes[7].bottomRight.getY())/-2), this.name);
			return true;
		}
		else if(boxes[2].sameSymbol(boxes[5], boxes[8])){
			boxes[2].drawIt(frame.getGraphics(),(int)boxes[2].topLeft.getX(), (int)(boxes[1].bottomRight.getY() +(boxes[2].topLeft.getY() - (int)boxes[8].bottomRight.getY())/-2), 
					(int)boxes[8].bottomRight.getX(), (int)(boxes[1].bottomRight.getY() +(boxes[2].topLeft.getY() - (int)boxes[8].bottomRight.getY())/-2), this.name);
			return true;
		}
		else if(boxes[0].sameSymbol(boxes[1], boxes[2])){
			boxes[2].drawIt(frame.getGraphics(), (int)((boxes[0].topLeft.getX() - (int)boxes[0].bottomRight.getX())/-2),(int)boxes[0].topLeft.getY(), (int)((boxes[0].topLeft.getX() - (int)boxes[0].bottomRight.getX())/-2),
					(int)boxes[2].bottomRight.getY(), this.name);
			return true;
		}
		else if(boxes[3].sameSymbol(boxes[4], boxes[5])){
			boxes[2].drawIt(frame.getGraphics(), (int)(boxes[0].bottomRight.getX() +(boxes[3].topLeft.getX() - (int)boxes[3].bottomRight.getX())/-2),(int)boxes[3].topLeft.getY(), (int)(boxes[0].bottomRight.getX() +(boxes[3].topLeft.getX() - (int)boxes[3].bottomRight.getX())/-2),
					(int)boxes[5].bottomRight.getY(), this.name);
			return true;
		}
		else if(boxes[6].sameSymbol(boxes[7], boxes[8])){
			boxes[2].drawIt(frame.getGraphics(), (int)(boxes[3].bottomRight.getX() +(boxes[6].topLeft.getX() - (int)boxes[6].bottomRight.getX())/-2),(int)boxes[0].topLeft.getY(), (int)(boxes[3].bottomRight.getX() +(boxes[6].topLeft.getX() - (int)boxes[6].bottomRight.getX())/-2),
					(int)boxes[8].bottomRight.getY(), this.name);
			return true;
		}
		else if(boxes[2].sameSymbol(boxes[4], boxes[6])){
			boxes[2].drawIt(frame.getGraphics(), (int)(boxes[2].topLeft.getX()),(int)boxes[2].bottomRight.getY(), (int)(boxes[6].bottomRight.getX()),
					(int)boxes[6].topLeft.getY(), this.name);
			return true;
		}
		else if(boxes[0].sameSymbol(boxes[4], boxes[8])){
			boxes[2].drawIt(frame.getGraphics(), (int)(boxes[0].topLeft.getX()),(int)boxes[0].topLeft.getY(), (int)(boxes[8].bottomRight.getX()),
					(int)boxes[8].bottomRight.getY(), this.name);
			return true;
		}
		return false;
	}

}
