package simpleface.simpleface;

import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;

//import ninjaTurtle.ninjaTurtle.Bullet;
//import ninjaTurtle.ninjaTurtle.Sprite;

public class driver extends Sprite {

//	private static final Image EXPLOSION_IMAGE = new Image("file:Resources/fireball2.png");
	private static final Image EXPLOSION_IMAGE ;
	
			static {
				EXPLOSION_IMAGE = new Image(driver.class.getClassLoader().getResource("Tank.png").toString());
	}
	
	private ImageView explosion = new ImageView(EXPLOSION_IMAGE);
	private boolean isDeadDriver = false;
	private boolean isShooting = false;
	private boolean isShootingMissile = false;
	private double mouseX;
	private double mouseY;
	private int lives = 3;
	private long lastShotTime = 0;
	private final long shootCooldown = 10_000_000_000L; // 10 seconds
	
	private boolean isDashing = false;
	private double dashDistance = 100;
	private double dashSpeed = 500;
	private double dashCooldown = 3;
	private double dashCooldownTimer = 0;
	public int driverSpeed = 300;
	
	
	private Group bullets;
	private Group missiles;
	private Group sniperBullet;
	
	Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

	private final int PLAYER_GAME_WIDTH = (int) screenBounds.getWidth();
	private final int PLAYER_GAME_HEIGHT = (int) screenBounds.getHeight() + 40;

	
	public driver(Image i,Group bullets, Group missiles) {
		super(i);
		this.missiles = missiles;
		this.bullets = bullets;
		super.getChildren().add(explosion);
		explosion.setVisible(false);
		explosion.setLayoutX(10);
		explosion.setLayoutY(5);
		
		setOnMousePressed(this::handleMousePressed);
		setOnMouseReleased(this::handleMouseReleased);
		
		}
	
	public void handleMousePressed(MouseEvent event) {
		
		    System.out.println("Button: " + event.getButton());
		for (MouseButton c : MouseButton.values())
		    System.out.println(c);
		
		if (event.getButton() == MouseButton.PRIMARY ) {
			isShooting = true;
			mouseX = event.getX();
			mouseY = event.getY();
			shoot(mouseX,mouseY);
			
		}
		
		if (event.getButton() == MouseButton.SECONDARY ) {
			isShootingMissile = true;
			mouseX = event.getX();
			mouseY = event.getY();
			shootMissile(mouseX, mouseY);
			
		}
	}
	
	
	public void update(double elapsedTime){
		super.update(elapsedTime);
		
		if (isShooting ){
			
			double angle = Math.atan2(mouseY-getPositionY(), mouseX - getPositionX());
			
			setRotate(Math.toDegrees(angle)+ 90);
			shoot(mouseX,mouseY);
			
			
	}
	if (isShootingMissile ){
			
			double angle = Math.atan2(mouseY-getPositionY(), mouseX - getPositionX());
			
			setRotate(Math.toDegrees(angle)+90);
			shootMissile(mouseX, mouseY);
			
			
	}
	if (isDashing) {
		double dashDistanceRemaining = dashSpeed * elapsedTime;
		double deltaX = Math.cos(Math.toRadians(getRotate())) * dashDistanceRemaining;
		double deltaY = Math.sin(Math.toRadians(getRotate()))* dashDistanceRemaining;
		
		move(dashDistanceRemaining, deltaX, deltaY);
		
		dashDistance -= dashDistanceRemaining;
		
		if (dashDistance <=0) {
			isDashing = false;
			dashCooldownTimer = dashCooldown;
		}else if (dashCooldownTimer > 0) {
			dashCooldownTimer -= elapsedTime;
		}
		 
		
	}
	}
	
	public void move(double distance, double deltaX,double deltaY) {
		double newX = getPositionX() + deltaX;
		double newY = getPositionY() + deltaY;
		
		if (newX >= 0 && newX + getWidth() <= 1920 && newY >= 0	&& newY + getHeight() <= 1080) {
			setPosition(newX,newY);
		}
			}
	public void dash(double elapsedTime) {
		if (!isDashing && dashCooldownTimer <= 0) {
			isDashing = true;
			dashDistance = 100;
		}
	}
	private void handleMouseReleased(MouseEvent event) {
		System.out.println("Mouse Released Event Triggered");
		
		 System.out.println("Mouse Released: " + event.getButton());
		if (event.getButton() == MouseButton.PRIMARY) {
			isShooting = false;
		}
		if (event.getButton() == MouseButton.SECONDARY) {
			isShootingMissile = false;
		}
	}

	public void updateTankDirection(double mouseX, double mouseY) {
		double angle = Math.atan2(mouseY - getPositionY(), mouseX - getPositionX());
		setRotate(Math.toDegrees(angle)+90);
	}
//	public boolean canShoot() {
//		long currentTime = System.currentTimeMillis() * 1_000_000;
//		return (currentTime - lastShotTime) >= shootCooldown ;
//	}
//	public boolean canShootMissile() {
//		long currentTime = System.currentTimeMillis() * 1_000_000;
//		return (currentTime - lastShotTime) >= shootCooldown ;
//	}
	public void driveLeft(double time) {
		super.setVelocityX(-driverSpeed);
		super.update(time);
		super.setVelocityX(0);

		if (getPositionX() < 00) {
			setPositionX(0);
		}
	}

	public void driveRight(double time) {
		super.setVelocityX(driverSpeed);
		super.update(time);
		super.setVelocityX(0);

		if (getPositionX() + getWidth() > PLAYER_GAME_WIDTH) {
			setPositionX(PLAYER_GAME_WIDTH - getWidth());
		}
	}
	public void driveUp(double time) {
		super.setVelocityY(-driverSpeed);
		super.update(time);
		super.setVelocityY(0);

		if (getPositionY() + getHeight() < 50) {
			setPositionY(50 - getHeight());
		}
	}
	public void driveDown(double time) {
		super.setVelocityY(driverSpeed);
		super.update(time);
		super.setVelocityY(0);

		if (getPositionY() + getHeight() > PLAYER_GAME_HEIGHT) {
			setPositionY(PLAYER_GAME_HEIGHT - getHeight());
		}
	}
	
		public void shoot(double mouseX, double mouseY) {
		
		bullet b = new bullet(bullets);
		b.setPosition(getPositionX() + getWidth() / 2, getPositionY() );
		
		double angle = Math.atan2(mouseY - b.getPositionY(), mouseX - b.getPositionX());
		double speed = 1100; //1200
		b.setVelocityX(speed * Math.cos(angle));
		b.setVelocityY(speed * Math.sin(angle));	
		System.out.println( "velx " + speed * Math.cos(angle) + " vely " + speed * Math.sin(angle));
		b.setRotation(angle);
		bullets.getChildren().add(b);
		

	}
	public void shootMissile(double mouseX, double mouseY) {
		
		missile M = new missile(missiles);
		M.setPosition(getPositionX() + getWidth() / 2, getPositionY() );
		
		double angle = Math.atan2(mouseY - M.getPositionY(), mouseX - M.getPositionX());
		double speed = 800;
		M.setVelocityX(speed * Math.cos(angle));
		M.setVelocityY(speed * Math.sin(angle));	
		M.setRotation(angle);
		missiles.getChildren().add(M);
		

	}


	public void kill() {
		isDeadDriver = true;
		explosion.setVisible(true);
		
	}
	public boolean isDead() {
		return isDeadDriver;
	}
	public void revive() {
		isDeadDriver = false;
		explosion.setVisible(false);
	}
	public boolean isShooting() {
		return isShooting;
	}
	
}