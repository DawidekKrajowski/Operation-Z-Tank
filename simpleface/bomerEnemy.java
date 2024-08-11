package simpleface.simpleface;


import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class bomerEnemy extends enemies {
	public static final int poison1 = 0; // the deflected bullet from the deflector enemy
	//public static final int poison1 = 1;
	
	
	public int coins;
	
	public int lives;
	
	public int Score = 0;
	
	public boolean velocitySetBullet = false;
	

	
	public static final Random random = new Random();
	
	public static final Image[] OTHER_ZOMBS = {
		//	new Image("File:Resources/poison.png"),
			new Image (bomerEnemy.class.getClassLoader().getResource("poison.png").toString())
			
	
	};
	
//	private static final Image EXPLOSION_IMAGE = new Image("file:Resources/explosion0.png");
	private static final Image EXPLOSION_IMAGE;
	static {
		EXPLOSION_IMAGE = new Image(bomerEnemy.class.getClassLoader().getResource("explosion0.png").toString());
	}
	private ImageView explosion = new ImageView(EXPLOSION_IMAGE);
	
	private double deathDelay = 1;
	
	public static final Random RNG = new Random(); 
	
	private int type = 0;
	
	private boolean isDead = false;
	
//	public boolean isNewlySpawned() {
//		return newlySpawned;
//	}
//	public void setNewlySpawned (boolean newlySpawed) {
//		this.newlySpawned = newlySpawed;
//	}
//	
	public bomerEnemy() {
		super();
		
		int randomNumber = RNG.nextInt(100);
		//	System.out.println(randomNumber1);
		
		
		if (randomNumber <= 100 ) {  // basic poision
            type = poison1;
		}
		
		if (type == poison1 ) {
			//basic bullet
			lives =100;
		}
		
		
		
		
			
		super.getChildren().add(explosion);
		explosion.setVisible(false);
		explosion.setLayoutX(0);
		explosion.setLayoutY(0);

		setImage(OTHER_ZOMBS[type]);
		
	}
	

	public double getEnemyX() {
		return getLayoutX();
	}
	public double getEnemyY() {
		return getLayoutY();
	}
	public int getCoins() {
		return coins;
	}
	public int getType() {
		return type;
	}
	public void hit() {
		if (lives >= 0){
		lives--;
		}
	//	System.out.println("hit()"+ lives);
		if (lives <= 0) {
			kill();
		}
		
		
	}
	
	public void kill() {
		setVelocity(0, 0);	
		isDead = true;
		explosion.setVisible(true);
	//	setRotate(0);
//		System.out.println("kill()");
		
		}

	public boolean isBomb() {
		return true;
	}
	
	
	public boolean isDead() {
		return isDead;
	}
	public boolean isReadyForCleanUp() {
		return deathDelay < 0;
	}
//	public void setVelocitySetBullet(boolean val) {
//		this.velocitySet = val;
//	}
//	public boolean isVelocitySetBullet() {
//		return velocitySetBullet;
//	}
	
	@Override             
	public void update(double time) {
		super.update(time);
		
		if(isDead) {
			deathDelay -= time;
			
		}
	}


	
}
