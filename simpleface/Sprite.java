package simpleface.simpleface;


import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import simpleface.simpleface.Sprite;


/**
 * JavaFX App
 */
public class Sprite extends Group {
	
	
	  private Image image;
	  private ImageView imgView = new ImageView();
	  private double positionX = 0;
	  private double positionY = 0;
	  private double velocityX = 0;
	  private double velocityY = 0;
	  private double width = 0;
	  private double height = 0;
	 
	  public Sprite() {
			this.getChildren().add(imgView);	
	  }
		
	  public Sprite(Image i ) {
		  image = i;
			width = (i != null)? i.getWidth() : 0;
			height = (i != null)? i.getHeight() : 0;
			imgView.setImage(i);
			
			this.getChildren().add(imgView);
		  
	  }
	  public void setImage(Image i) {
			image = i;
			width = (i != null)? i.getWidth() : 0;
			height = (i != null)? i.getHeight() : 0;
			imgView.setImage(i);
	  }
	/**
	 * @return the posistionX
	 */
	public double getPositionX() {
		return positionX;
	}
	/**
	 * @param posistionX the posistionX to set
	 */
	public void setPositionX(double positionX) {
		this.positionX = positionX;
		this.setLayoutX(positionX);
	}
	
	/**
	 * @return the posisitionY
	 */
	public double getPositionY() {
		return positionY;
	}
	/**
	 * @param posisitionY the posisitionY to set
	 */
	public void setPositionY(double positionY) {
		this.positionY = positionY;
		this.setLayoutY(positionY);
	}
	/**
	 * @return the velocityX
	 */
	public double getVelocityX() {
		return velocityX;
	}
	/**
	 * @param velocityX the velocityX to set
	 */
	public void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}
	/**
	 * @return the velocityY
	 */
	public double getVelocityY() {
		return velocityY;
	}
	/**
	 * @param velocityY the velocityY to set
	 */
	public void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}
	/**
	 * @return the width
	 */
	public double getWidth() {
		return width;
	}
	/**
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}
	public void setPosition(double x, double y) {
		positionX = x;
		positionY = y;
		this.relocate(positionX, positionY);
	}
	public void setVelocity(double x, double y) {
		velocityX = x;
		velocityY  = y;
	}
	// add the speed of the sprite in both directions
	public void  addVelocity (double x, double y) {
	velocityX += x;
	velocityY += y;
	}
	// update the position of the sprite based on an elapsed time in seconds
	public void update(double elapsedTime) {

		positionX = positionX + velocityX * elapsedTime;
		positionY = positionY + velocityY * elapsedTime;
		this.relocate(positionX, positionY);
		
	}
	public Rectangle2D getBoundary() {
		return new Rectangle2D(positionX, positionY, width, height);
	}
	// determine if th fiven sprite intersects witht this sprite 
	// the other sprite test 
	//true is the two sprites are touching ,false otherwise
	public boolean intersect(Sprite that) {
		return this.getBoundary().intersects(that.getBoundary());
		
	}

}
