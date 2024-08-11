package simpleface.simpleface;

import javafx.scene.Group;
import javafx.scene.image.Image;

public class missile extends Sprite {
	
private static final Image MISSILE_IMAGE;
	
	static {
		MISSILE_IMAGE = new Image(missile.class.getClassLoader().getResource("missle.png").toString());
		
	}
	
//	private static final Image MISSILE_IMAGE = new Image("file:Resources/missle.png");
	private boolean isDead = false;


	public missile(Group missiles) {
		super(MISSILE_IMAGE);
		

	}
//	public void update(double elapsedTime) {
//		super.update(elapsedTime);
//	}
	
	public void setRotation(double angle) {
		setRotate(Math.toDegrees(angle)+180 );
	}
	public boolean isDead() {
		return isDead;

	}
	public boolean isReadyForCleanUp() {
		return isDead;
	}

	public void kill() {
		//System.out.println("misslekilll()");
		isDead = true;
		
	}

 	public boolean intersect(Sprite s) {
		if (isDead) {
			return false;
		} else {
			return super.intersect(s);
		}
	}

}
