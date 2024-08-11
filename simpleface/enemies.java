package simpleface.simpleface;


import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class enemies extends Sprite {
	
	public static final int zombie1 = 9;//basic zombie
	public static final int zombie2 = 1;//medium skeleton zombie
	public static final int zombie3 = 2;// big lady zombie
	public static final int zombie4 = 3; // fast mouse
	public static final int coinZombie5 = 4; // gives out lots of coins 
	public static final int missleZombie6 = 5;// missle zombie that you dodge 
	public static final int golemZombie7 = 6; // super tanky ennemy
	public static final int sourcerZombie8 = 7;// will deflect bullets on impact
	public static final int deflectorEnemy9 = 8; // will deflect the players bullets 
//	public static final int deflectedBullet10 = 9; // the deflected bullet from the deflector enemy
	public static final int hearts11 = 10; // add one heart to player 
	public static final int poisonSourcer12 = 11; // when hit will randomly spawn a bomb for the player to avoid
	public static final int poisonSourcerMiniBoss13 = 12;
	public static final int sourcerMiniBoss14 = 13;
	public static final int ninjaMiniBoss15 = 14;
	public static final int flyingBomb16 = 15;
	
	public int coins ;
	
	public int lives;
	
	public int poisonSourcerMiniBoss13lives = 1200;
	public int sourcerMiniBoss14lives = 800;
	public int ninjaMiniBoss15lives = 1000;
	
	
	
	public boolean velocitySet = false;
	
	//private boolean newlySpawned = true;
	
	public static final Random random = new Random();
	
	int randomNumber1 = random.nextInt(9) + 1;
	
	int randomNumber2 = random.nextInt(3) + 1;
	
	public static final Image[] OTHER_ZOMBS = {
		
//			new Image("File:Resources/zombie.png"),
//			new Image("File:Resources/skeletonZombie.png"),
//			new Image("File:Resources/ladyZombie.png"),
//			new Image("File:Resources/rat.png"),
//			new Image("File:Resources/coinEnemy.png"),
//			new Image("File:Resources/enemyMissle.png"),
//			new Image("File:Resources/Golem.png"),
//			new Image("File:Resources/sourcer.png"),
//			new Image("File:Resources/deflector.png"),
//			new Image("File:Resources/zombie.png"),
//			new Image("File:Resources/hearts.png"),
//			new Image("File:Resources/poisonSourcer.png")
			new Image (enemies.class.getClassLoader().getResource("zombie.png").toString()),
			new Image (enemies.class.getClassLoader().getResource("skeletonZombie.png").toString()),
			new Image (enemies.class.getClassLoader().getResource("ladyZombie.png").toString()),
			new Image (enemies.class.getClassLoader().getResource("rat.png").toString()),
			new Image (enemies.class.getClassLoader().getResource("coinEnemy.png").toString()),
			new Image (enemies.class.getClassLoader().getResource("enemyMissle.png").toString()),
			new Image (enemies.class.getClassLoader().getResource("Golem.png").toString()),
			new Image (enemies.class.getClassLoader().getResource("sourcer.png").toString()),
			new Image (enemies.class.getClassLoader().getResource("ninja.png").toString()),
			new Image (enemies.class.getClassLoader().getResource("zombie.png").toString()),
			new Image (enemies.class.getClassLoader().getResource("hearts.png").toString()),
			new Image (enemies.class.getClassLoader().getResource("poisonSourcer.png").toString()),
			new Image (enemies.class.getClassLoader().getResource("poisonSourcerBoss.png").toString()),
			new Image(enemies.class.getClassLoader().getResource("sourcerBoss.png").toString()),
			new Image(enemies.class.getClassLoader().getResource("ninjaBoss.png").toString()),
			new Image(enemies.class.getClassLoader().getResource("bomb.png").toString()),
			
	
	};
	
	//private static final Image EXPLOSION_IMAGE = new Image("file:Resources/explosion0.png");
	
	private static final Image EXPLOSION_IMAGE;
	static {
		EXPLOSION_IMAGE = new Image(enemies.class.getClassLoader().getResource("explosion0.png").toString());
 
	}
	
	private ImageView explosion = new ImageView(EXPLOSION_IMAGE);
	
	private double deathDelay = .5;
	
	public static final Random RNG = new Random(); 
	
	private int type = 0;
	
	private boolean isDead = false;
	
//	public boolean isNewlySpawned() {
//		return newlySpawned;
//	}
//	public void setNewlySpawned (boolean newlySpawed) {
//		this.newlySpawned = newlySpawed;
//	}
//	Sound soundFX = new Sound();
//	
	public enemies() {
		super();
		
		double randomNumber = RNG.nextInt(11000);
			

		
		if (randomNumber < 2500 ) {  // basic zombie
            type = zombie1;
        }
		else if (randomNumber < 2750) { // missle;
        	type = missleZombie6;
        }else if (randomNumber < 2800) {
        	type = flyingBomb16;
        }
        else if(randomNumber < 3100) {  // coin
        	type = coinZombie5;
        }
        else if(randomNumber < 3500) { // heart
        	type = hearts11;
        }
        else if(randomNumber < 4100) { // sourcer
        	type = sourcerZombie8;
        }
        else if(randomNumber < 4900) { // delfector
        	type = deflectorEnemy9;
        }
        else if(randomNumber < 5700) {
        	type = poisonSourcer12; // poison sourcer
        }
        else if(randomNumber < 6000) { // golem
        	type = golemZombie7;
        }
        else if (randomNumber < 7800) {  //  medium zombie
            type = zombie2;
        }
        else if (randomNumber <  9000) { // big lady zombie
            type = zombie3;
        }
        else if (randomNumber < 9900) { //  fast mouse 
            type = zombie4;
        }
        else if(randomNumber < 11133) {//9933
        	type = poisonSourcerMiniBoss13;
        }
        else if (randomNumber < 10166) {//9966
        	type = sourcerMiniBoss14;
        	}
        else if( randomNumber < 11000) {
        	type = ninjaMiniBoss15;
        }
	//	System.out.println("type"+ type);
        
		//for tmrr try to impelment coin into game !!!
		
		if (type == zombie1 ) {
			//basic zombie
			coins = 1 + randomNumber1;//1-10 coins
			
			lives =1;
		} else if ( type == zombie2 ) {
			lives =3;
			coins = 1 + randomNumber1;
			//coins = 10+ randomNumber2;//10-50 coins
			//medium zombie
		}
		else if( type == zombie3) {
			lives=5;
			coins = 1 + randomNumber1;
			//coins = 50 + randomNUmber3;
			//tough zombie
		}
		else if (type == zombie4 ) {
			lives=1;
			coins = 1 + randomNumber1;
			//fast mouse
		}
		else if (type == coinZombie5) {
			lives =1;
			coins = 0;
		}
		else if (type == missleZombie6) {
			lives = 100;
			coins = 0;
		}
		else if(type == golemZombie7) {
			lives = 101;
			coins = 1+ randomNumber1;
			
		}
		else if(type == sourcerZombie8) {
			lives = 20;
			coins = 0;
		}
		else if(type == deflectorEnemy9) {
			lives = 20;
			coins = 1+ randomNumber1;
		}
		else if(type == hearts11) {
			lives = 1;
			coins = 0;
		}
		else if(type == poisonSourcer12) {
			lives = 20;
			coins = 1 + randomNumber1;
		
		}
		else if ( type == poisonSourcerMiniBoss13) {
			lives = poisonSourcerMiniBoss13lives;
			coins = 1 + randomNumber2;
		}else if( type == sourcerMiniBoss14) {
			lives = sourcerMiniBoss14lives;
			coins = 1 ;
		}else if(type == ninjaMiniBoss15) {
			lives = ninjaMiniBoss15lives;
			coins = 1 + randomNumber2;
		}else if(type == flyingBomb16) {
			lives = 1;
			coins = 0;
		}
		
		
		
			
		super.getChildren().add(explosion);
		explosion.setVisible(false);
		explosion.setLayoutX(0);
		explosion.setLayoutY(0);

		setImage(OTHER_ZOMBS[type]);
		
	}
	
	public int  getninjaMiniBoss15lives() {
		return ninjaMiniBoss15lives;
	}
	public int getsourcerMiniBoss14lives(){
		return sourcerMiniBoss14lives;
	}
	public int getpoisonSourcerMiniBoss13lives() {
		return poisonSourcerMiniBoss13lives;
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
		if(lives > 0) {
			//playSoundFX(1);
		
		}
		if (lives <= 0) {
			kill();
		}
				
	}
	
	public void kill() {
		setVelocity(0, 0);	
		isDead = true;
		explosion.setVisible(true);
	//	playSoundFX(2);
		
	//	setRotate(0);
//		System.out.println("kill()");
		
		}

	public int getEnemyType() {
		return type;
	}
	public int getLives() {
		return lives;
	}
	
	public boolean isBomb() {
		return isBomb();
	}
	public int getBombtype() {
		return getBombtype();
	}
	public boolean isDead() {
		return isDead;
	}
	public boolean isReadyForCleanUp() {
		return deathDelay < 0;
	}
	public void setVelocitySet(boolean val) {
		this.velocitySet = val;
	}
	public boolean isVelocitySet() {
		return velocitySet;
	}
	
	@Override             
	public void update(double time) {
		super.update(time);
		if(isDead) {
			deathDelay -= time;
			
		}
	}
}
