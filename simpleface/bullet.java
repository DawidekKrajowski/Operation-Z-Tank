package simpleface.simpleface;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class bullet extends Sprite {
	
//	private static Image BULLET_IMAGE = new Image(getClass().getClassLoader().getResource("bullet.png").toString());
	//private ImageView bulletImage = new ImageView(bulletImageImage);
	
	private static final Image BULLET_IMAGE;
	
	static {
		BULLET_IMAGE = new Image(bullet.class.getClassLoader().getResource("bullet.png").toString());
		
	}
	
//	private static final Image BULLET_IMAGE = new Image("file:Resources/bullet.png");
	private boolean isDead = false;


	public bullet(Group bullets) {
		super(BULLET_IMAGE);
	//	bullets.getChildren().add(this);

	}
//	public void update(double elapsedTime) {
//		super.update(elapsedTime);
//	}
	
	public void setRotation(double angle) {
		setRotate(Math.toDegrees(angle) + 90);
	}
	public boolean isDead() {
		return isDead;

	}
	public boolean isReadyForCleanUp() {
		return isDead;
	}

	public void kill() {
	
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
