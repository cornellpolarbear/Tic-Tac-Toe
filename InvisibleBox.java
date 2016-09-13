import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;


/*Box that defines location that an X or Circle can be drawn
 * 
 */
public class InvisibleBox{
	public Point2D topLeft;
	public Point2D bottomRight;
	public boolean drewX = false;
	public boolean drewO = false;
	

	public InvisibleBox( Point2D topCorner, Point2D bottomCorner) {
		//top left point and bottom right point
		topLeft = topCorner;
		bottomRight = bottomCorner;
		
	}
	
	//Used to check whether a point lies in an invisible box
	public Boolean containsPoint(Point2D point){
		if( ((int)point.getX() >= topLeft.getX())&&((int)point.getX() <= bottomRight.getX())){
			if( ((int)point.getY() >= topLeft.getY())&&((int)point.getY() <= bottomRight.getY())){
				return true;
				
			}
		}
		return false;
	}
	
	//determines whether the same symbol has been drawn in a different box
	public boolean sameSymbol(InvisibleBox b){
		
		//checks if same symbol is x
		if (this.drewX == true){
			if(b.drewX == true) return true;
			else return false;
		}
		//checks if symbol is O
		else if(this.drewO == true){
			if(b.drewO == true) return true;
		}
		return false;
	}
	
	//same as previous method only for 2 other boxes
	public boolean sameSymbol(InvisibleBox b, InvisibleBox c){
		
		return ((this.sameSymbol(b) == b.sameSymbol(c))&& (b.sameSymbol(c)== true));
		
	}
	
	public void drawXOrO (Graphics g2, int xOrO){
		Graphics2D g = (Graphics2D) g2;
		g.setColor(new Color(0,182,155));
	    g.setStroke(new BasicStroke( 20, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
	    int min = Math.min((int)(bottomRight.getX() - topLeft.getX()), (int)(bottomRight.getY() - topLeft.getY()));
		int halfX = (int) (bottomRight.getX()- (topLeft.getX()+ min))/2;
		int halfY = (int) (bottomRight.getY()- (topLeft.getY() + min))/2;
		if(xOrO == 0){
			g.drawLine((int)topLeft.getX()+ halfX, (int)topLeft.getY()+ halfY + 20, (int)bottomRight.getX()-halfX, (int)bottomRight.getY()- halfY - 20);
			g.drawLine((int)bottomRight.getX()- halfX, (int)topLeft.getY()+ halfY + 20, (int)topLeft.getX()+ halfX, (int)bottomRight.getY()- halfY - 20);
		drewX = true;
		}
		else{
			//draws a circle in the box
			drewO = true;
			g.drawOval((int)(halfX +20 + topLeft.getX()),(int)(halfY+ 20 +topLeft.getY()),min-40, min-40);
		}
		
	}
	
	public void drawIt (Graphics g2, int x, int y, int x2, int y2, String name){
		Graphics2D g = (Graphics2D) g2;
		g.setColor(Color.RED);
	    g.setStroke(new BasicStroke( 20, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
	    g.drawLine(x, y, x2,y2 );
	    g.setFont(new Font("TimesRoman", Font.PLAIN, 50)); 
	    g.drawString(name + " Wins!", (int)((x)+(x2-x)/2), (int)((y)+(y2-y)/2) +40);
	}
	

	
}
