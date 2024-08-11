package simpleface.simpleface;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

import simpleface.simpleface.enemies;

import java.awt.Cursor;
import java.util.ArrayList;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.text.*;
import javafx.scene.control.Button;

/**
 * JavaFX App
 */
// maybe make the mouse a different sprite like a target , feels not good shooting with athe pointer
public class Interface extends Application {

	private final int TITLE_SCREEN = 0;
	private final int PLAYING = 1;
	private final int UPGRADE_SCREEN = 2;
	private int Score = 0;
	private int TotalCoins = 0;
	private int highScore = 0;

	private boolean newHighScore = false;
	private double newEnemiesTimer = 1;
	private int divideBY = 3;
	private int difficultyScoreAmplifier = 35000 / divideBY;
	
	private boolean bossMode = false;

	private boolean difficultyModeisSelected = false;

	private int tot = 0;
	private double difficultyCurve = -0.1;

	private int difficutlyType = 4; // 0 = easy, 1 = normal 2 = hard, 3 = imposible, 4 neuatral background

	private int bulletUpgradeLevel = 0;
	private int bulletUpgradePrice = 3000;
	private int bulletUpgradeMultiplier = 1;

	private int rocketCost = 300;
	private int nukeCost = 1000;
	private int snipeCost = 100;
	private boolean playedBulletUpgradeSound = false;
	private boolean rocketUpgradeSound = false;
	private boolean nukeUpgradeSound = false;

	private boolean gameMusicOn = true;

	private boolean canSnipe = false;

	// setup buttoins
	private Group root;
	private Scene gameScene;
	private Color gameColor = Color.GREY;
	// actually this stuff was so clutch , made the game able to change colors ,
	// enemies when they can spawn
	private int basicEnemieScore = 0;
	private int mediumSkeletonScore = 1000 / (divideBY*50);
	private int largeLadyScore = 3000 / (divideBY*50)
			
			;
	private int FastRatScore = 25000 / (divideBY*50);
	private int missleScore = 7000 / (divideBY*50);
	private int flyingBombScore = 5000 / (divideBY*50);
	private int CoinsSpawnScore = 7000 / (divideBY*50);
	private int heartSpawnScore = 10000 / (divideBY*50);
	private int poisonSourcerSpawnScore = 10000 / divideBY*50;
	private int deflectorSpawnScore = 20000 / (divideBY *50);
	private int SourcerSpawnScore = 55000 / (divideBY *50);
	private int GolemSpawnScore = 35000 / (divideBY*50);
	private int bossPoisonSourcerSpawnScore = 30000 / (divideBY*50);
	private int bossSourcerSpanwScore = 30000/  (divideBY*50);
	private int bossNinjaSpawnScore = 30000 / (divideBY*50);

	private int enableImpossibleMode = 50000*50;
	private int lives;
	// screen. get get the screen size and replace the m..
	private final int GAME_WIDTH = 1920;
	private final int GAME_HEIGHT = 1080;

	Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

	private final int PLAYER_GAME_WIDTH = (int) screenBounds.getWidth();
	private final int PLAYER_GAME_HEIGHT = (int) screenBounds.getHeight() + 40;

	double scaleX = (double) PLAYER_GAME_WIDTH / GAME_WIDTH;
	double scaleY = (double) PLAYER_GAME_HEIGHT / GAME_HEIGHT;
	double scale = Math.min(scaleX, scaleY); // Use the smaller scale to maintain aspect ratio

	int PlayerScreenWidth = GAME_WIDTH / PLAYER_GAME_WIDTH;
	int PlayerScreenHeight = GAME_HEIGHT / PLAYER_GAME_HEIGHT;
	private final HashSet<KeyCode> keyboard = new HashSet<KeyCode>();

	public static final Random random = new Random();

	int randomNumber1 = random.nextInt(9) + 1;
	Random RNG = new Random();

	int randomNumber2 = RNG.nextInt(10) + 1;
	int randomNumber3 = RNG.nextInt(1) + 1;
	int randomNumber4 = RNG.nextInt(1) + 1;
	int randomNumber5 = RNG.nextInt(1) + 1;
	int randomNumber6 = RNG.nextInt(1) + 1;

	private Group enemies = new Group();
	private Group bullets = new Group();
	private Group missiles = new Group();

//	private Image tankImage = new Image(getClass().getClassLoader().getResource("hearts.png").toString());
	// private ImageView heart = new ImageView(tankImage);
	private driver tank = new driver(new Image(getClass().getClassLoader().getResource("Tank.png").toString()), bullets,
			missiles);

	private Image coinImage = new Image(getClass().getClassLoader().getResource("coin.png").toString());
	private ImageView coin = new ImageView(coinImage);

//	private ImageView coin = new ImageView("file:Resources/coin.png");

	private Text livesText = new Text("X " + lives);
	// private ImageView heart = new ImageView(new
	// Image("file:Resources/hearts.png"));
//	private ImageView heart2 = new ImageView(new Image("file:Resources/hearts.png"));
	// private ImageView heart3 = new ImageView(new
	// Image("file:Resources/hearts.png"));
	// private ImageView heart4 = new ImageView(new
	// Image("file:Resources/hearts.png"));
//	private ImageView heart5 = new ImageView(new Image("file:Resources/hearts.png"));
//	private ImageView heart6 = new ImageView(new Image("file:Resources/hearts.png"));
//	private ImageView heart7 = new ImageView(new Image("file:Resources/hearts.png"));
//	private ImageView heart8 = new ImageView(new Image("file:Resources/hearts.png"));
//	private ImageView heart9 = new ImageView(new Image("file:Resources/hearts.png"));
//	private ImageView heart10 = new ImageView(new Image("file:Resources/hearts.png"));

	private Image heartImage = new Image(getClass().getClassLoader().getResource("hearts.png").toString());
	private Image heartImage2 = new Image(getClass().getClassLoader().getResource("hearts.png").toString());// .getClassLoader()
	private ImageView heart = new ImageView(heartImage);
	private ImageView heart2 = new ImageView(heartImage);
	private ImageView heart3 = new ImageView(heartImage);
	private ImageView heart4 = new ImageView(heartImage);
	private ImageView heart5 = new ImageView(heartImage);
	private ImageView heart6 = new ImageView(heartImage);
	private ImageView heart7 = new ImageView(heartImage);
	private ImageView heart8 = new ImageView(heartImage);
	private ImageView heart9 = new ImageView(heartImage2);
	private ImageView heart10 = new ImageView(heartImage2);
	private ImageView heart11 = new ImageView(heartImage2);
	private ImageView heart12 = new ImageView(heartImage2);
	private ImageView heart13 = new ImageView(heartImage2);
	private ImageView heart14 = new ImageView(heartImage2);
	private ImageView heart15 = new ImageView(heartImage2);

	// private ImageView unknownEnemy1 = new ImageView(new
	// Image("file:Resources/unknownEnemy.png"));
	// private ImageView unknownEnemy2 = new ImageView(new
	// Image("file:Resources/unknownEnemy.png"));
	// private ImageView unknownEnemy3 = new ImageView(new
	// Image("file:Resources/unknownEnemy.png"));
	// private ImageView unknownEnemy4 = new ImageView(new
	// Image("file:Resources/unknownEnemy.png"));
//	private ImageView unknownEnemy5 = new ImageView(new Image("file:Resources/unknownEnemy.png"));
	// private ImageView unknownEnemy6 = new ImageView(new
	// Image("file:Resources/unknownEnemy.png"));
//	private ImageView unknownEnemy7 = new ImageView(new Image("file:Resources/unknownEnemy.png"));
//	private ImageView unknownEnemy8 = new ImageView(new Image("file:Resources/unknownEnemy.png"));
//	private ImageView unknownEnemy9 = new ImageView(new Image("file:Resources/unknownEnemy.png"));
//	private ImageView unknownEnemy10 = new ImageView(new Image("file:Resources/unknownEnemy.png"));

	private Image unknownEnemyImage = new Image(getClass().getClassLoader().getResource("unknownEnemy.png").toString());
	private ImageView unknownEnemy1 = new ImageView(unknownEnemyImage);
	private ImageView unknownEnemy2 = new ImageView(unknownEnemyImage);
	private ImageView unknownEnemy3 = new ImageView(unknownEnemyImage);
	private ImageView unknownEnemy4 = new ImageView(unknownEnemyImage);
	private ImageView unknownEnemy5 = new ImageView(unknownEnemyImage);
	private ImageView unknownEnemy6 = new ImageView(unknownEnemyImage);
	private ImageView unknownEnemy7 = new ImageView(unknownEnemyImage);
	private ImageView unknownEnemy8 = new ImageView(unknownEnemyImage);
	private ImageView unknownEnemy9 = new ImageView(unknownEnemyImage);
	private ImageView unknownEnemy10 = new ImageView(unknownEnemyImage);
	
	
	// boss health bar 
	private Image healthBarFull = new Image (getClass().getClassLoader().getResource("healthBarFull.png").toString());
	private ImageView FullhealthBarImg = new ImageView(healthBarFull);
	
	private Image healthBar1IMG = new Image (getClass().getClassLoader().getResource("healthbar1.png").toString());
	private ImageView healthBar1 = new ImageView(healthBar1IMG);
	
	private Image healthBar2IMG = new Image (getClass().getClassLoader().getResource("healthbar2.png").toString());
	private ImageView healthBar2 = new ImageView(healthBar2IMG);
	
	private Image healthBar3IMG = new Image (getClass().getClassLoader().getResource("healthbar3.png").toString());
	private ImageView healthBar3 = new ImageView(healthBar3IMG);
	
	private Image healthBar4IMG = new Image (getClass().getClassLoader().getResource("healthbar4.png").toString());
	private ImageView healthBar4 = new ImageView(healthBar4IMG);
	
	private Image healthBar5IMG = new Image (getClass().getClassLoader().getResource("healthbar5.png").toString());
	private ImageView healthBar5 = new ImageView(healthBar5IMG);
	
	private Image healthBar6IMG = new Image (getClass().getClassLoader().getResource("healthbar6.png").toString());
	private ImageView healthBar6 = new ImageView(healthBar6IMG);
	
	private Image healthBar7IMG = new Image (getClass().getClassLoader().getResource("healthbar7.png").toString());
	private ImageView healthBar7 = new ImageView(healthBar7IMG);
	
	private Image healthBar8IMG = new Image (getClass().getClassLoader().getResource("healthbar8.png").toString());
	private ImageView healthBar8 = new ImageView(healthBar8IMG);
	
	private Image healthBar9IMG = new Image (getClass().getClassLoader().getResource("healthbar9.png").toString());
	private ImageView healthBar9 = new ImageView(healthBar9IMG);
	
	private Image healthBar10IMG = new Image (getClass().getClassLoader().getResource("healthbar10.png").toString());
	private ImageView healthBar10 = new ImageView(healthBar10IMG);
	
	private Image healthBar11IMG = new Image (getClass().getClassLoader().getResource("healthbar11.png").toString());
	private ImageView healthBar11 = new ImageView(healthBar11IMG);
	
	private Image healthBar12IMG = new Image (getClass().getClassLoader().getResource("healthbar12.png").toString());
	private ImageView healthBar12 = new ImageView(healthBar12IMG);
	
	private Image healthBar13IMG = new Image (getClass().getClassLoader().getResource("healthbar13.png").toString());
	private ImageView healthBar13 = new ImageView(healthBar13IMG);
	
	private Image healthBar14IMG = new Image (getClass().getClassLoader().getResource("healthbar14.png").toString());
	private ImageView healthBar14 = new ImageView(healthBar14IMG);
	
	private Image healthBar15IMG = new Image (getClass().getClassLoader().getResource("healthbar15.png").toString());
	private ImageView healthBar15 = new ImageView(healthBar15IMG);
	
	private Image healthBar16IMG = new Image (getClass().getClassLoader().getResource("healthbar16.png").toString());
	private ImageView healthBar16 = new ImageView(healthBar16IMG);
	
	private Image healthBar17IMG = new Image (getClass().getClassLoader().getResource("healthbar17.png").toString());
	private ImageView healthBar17 = new ImageView(healthBar17IMG);
	
	private Image healthBar18IMG = new Image (getClass().getClassLoader().getResource("healthbar18.png").toString());
	private ImageView healthBar18 = new ImageView(healthBar18IMG);
	
	private Image healthBar19IMG = new Image (getClass().getClassLoader().getResource("healthbar19.png").toString());
	private ImageView healthBar19 = new ImageView(healthBar19IMG);
	
	private Image healthBar20IMG = new Image (getClass().getClassLoader().getResource("healthbar20.png").toString());
	private ImageView healthBar20 = new ImageView(healthBar20IMG);
	
	private Image healthBar21IMG = new Image (getClass().getClassLoader().getResource("healthbar21.png").toString());
	private ImageView healthBar21 = new ImageView(healthBar21IMG);
	
	private Image healthBar22IMG = new Image (getClass().getClassLoader().getResource("healthbar22.png").toString());
	private ImageView healthBar22 = new ImageView(healthBar22IMG);
	
	private Image healthBar23IMG = new Image (getClass().getClassLoader().getResource("healthbar23.png").toString());
	private ImageView healthBar23 = new ImageView(healthBar23IMG);
	
	private Image healthBar24IMG = new Image (getClass().getClassLoader().getResource("healthbar24.png").toString());
	private ImageView healthBar24 = new ImageView(healthBar24IMG);
	
	private Image healthBar25IMG = new Image (getClass().getClassLoader().getResource("healthbar25.png").toString());
	private ImageView healthBar25 = new ImageView(healthBar25IMG);
	
	private Image healthBar26IMG = new Image (getClass().getClassLoader().getResource("healthbar26.png").toString());
	private ImageView healthBar26 = new ImageView(healthBar26IMG);
	
	private Image healthBar27IMG = new Image (getClass().getClassLoader().getResource("healthbar27.png").toString());
	private ImageView healthBar27 = new ImageView(healthBar27IMG);
	
	private Image healthBar28IMG = new Image (getClass().getClassLoader().getResource("healthbar28.png").toString());
	private ImageView healthBar28 = new ImageView(healthBar28IMG);
	
	private Image healthBar29IMG = new Image (getClass().getClassLoader().getResource("healthbar29.png").toString());
	private ImageView healthBar29 = new ImageView(healthBar29IMG);
	
	private Image healthBar30IMG = new Image (getClass().getClassLoader().getResource("healthbar30.png").toString());
	private ImageView healthBar30 = new ImageView(healthBar30IMG);
	
	private Image healthBar31IMG = new Image (getClass().getClassLoader().getResource("healthbar31.png").toString());
	private ImageView healthBar31 = new ImageView(healthBar31IMG);
	
	private Image healthBar32IMG = new Image (getClass().getClassLoader().getResource("healthbar32.png").toString());
	private ImageView healthBar32 = new ImageView(healthBar32IMG);
	
	private Group bossHealthBar = new Group(healthBar1,healthBar2,healthBar3,healthBar4,healthBar5,healthBar6,healthBar7,healthBar8,healthBar9,healthBar10,healthBar11,healthBar12,healthBar13,healthBar14,healthBar15,healthBar16,healthBar17,healthBar18,healthBar19,healthBar20,healthBar21,healthBar22,healthBar23,healthBar24,healthBar25,healthBar26,healthBar27,healthBar28,healthBar29,healthBar30,healthBar31,healthBar32,FullhealthBarImg);
//	private ImageView basicZombieImg = new ImageView(new Image("file:Resources/zombie.png"));
//	private ImageView skeletonZombieImg = new ImageView(new Image("file:Resources/skeletonZombie.png"));
//	private ImageView ladyZombieImg = new ImageView(new Image("file:Resources/ladyZombie.png"));
//	private ImageView ratImg = new ImageView(new Image("file:Resources/rat.png"));
//	private ImageView missleImg = new ImageView(new Image("file:Resources/enemyMissle.png"));
//	private ImageView sourcerImg = new ImageView(new Image("file:Resources/sourcer.png"));
//	private ImageView GolemImg = new ImageView(new Image("file:Resources/Golem.png"));
//	private ImageView poisonSourcerImg = new ImageView(new Image("file:Resources/poisonSourcer.png"));
//	private ImageView deflectorImg = new ImageView(new Image("file:Resources/deflector.png"));

	private Image basicZombieImgImage = new Image(getClass().getClassLoader().getResource("zombie.png").toString());
	private ImageView basicZombieImg = new ImageView(basicZombieImgImage);

	private Image skeletonZombieImgImage = new Image(
			getClass().getClassLoader().getResource("skeletonZombie.png").toString());
	private ImageView skeletonZombieImg = new ImageView(skeletonZombieImgImage);

	private Image ladyZombieImgImage = new Image(getClass().getClassLoader().getResource("ladyZombie.png").toString());
	private ImageView ladyZombieImg = new ImageView(ladyZombieImgImage);

	private Image ratImgImage = new Image(getClass().getClassLoader().getResource("rat.png").toString());
	private ImageView ratImg = new ImageView(ratImgImage);

	private Image missleImgImage = new Image(getClass().getClassLoader().getResource("enemyMissle.png").toString());
	private ImageView missleImg = new ImageView(missleImgImage);

	private Image sourcerImgImage = new Image(getClass().getClassLoader().getResource("sourcer.png").toString());
	private ImageView sourcerImg = new ImageView(sourcerImgImage);

	private Image GolemImgImage = new Image(getClass().getClassLoader().getResource("Golem.png").toString());
	private ImageView GolemImg = new ImageView(GolemImgImage);

	private Image poisonSourcerImgImage = new Image(
			getClass().getClassLoader().getResource("poisonSourcer.png").toString());
	private ImageView poisonSourcerImg = new ImageView(poisonSourcerImgImage);

	private Image deflectorImgImage = new Image(getClass().getClassLoader().getResource("ninja.png").toString());
	private ImageView deflectorImg = new ImageView(deflectorImgImage);

	// group to show the enemy progression throughout the game
	private Group EnemyProgression = new Group(unknownEnemy1, unknownEnemy2, unknownEnemy3, unknownEnemy4,
			unknownEnemy5, unknownEnemy6, unknownEnemy7, unknownEnemy8, unknownEnemy9, unknownEnemy10, basicZombieImg,
			skeletonZombieImg, ladyZombieImg, ratImg, missleImg, sourcerImg, GolemImg, poisonSourcerImg, deflectorImg);

	private Image nukeImage = new Image(getClass().getClassLoader().getResource("nuke.png").toString());
	private ImageView nuke = new ImageView(nukeImage);

	// private ImageView nuke = new ImageView("file:Resources/nuke.png");
	private Text BombHint = new Text("press Space");

	private Image ultrashotImage = new Image(getClass().getClassLoader().getResource("missle.png").toString());
	private ImageView ultraShot = new ImageView(ultrashotImage);

//	private ImageView ultraShot = new ImageView("file:Resources/missle.png");
	private Text ultraShotText = new Text("right click");

	private Image bulletImageImage = new Image(getClass().getClassLoader().getResource("bullet.png").toString());
	private ImageView bulletImage = new ImageView(bulletImageImage);

//	private ImageView bulletImage = new ImageView("file:Resources/bullet.png");
	private Text bulletTextImage = new Text("press Q");
	private Text bulletUpgrade = new Text("+");
	private Text bulletUpgrade2 = new Text("+");

	private Button easy = new Button(" Easy ");
	private Button veryEasy = new Button(" very Easy ");
	private Button outrageouslyEasy = new Button(" outrageously easy");
	private Button impossiblyNormal = new Button(" IMPOSSIBLE ");

	private int newScoreChallange = 0;
	private Text ScoreChallangePrompt = new Text(
			"Good job soldier, but can you get to a score of " + newScoreChallange + "?");

	private Button questionButton = new Button(" ? ");
	private Button unQuestionButton = new Button("Back");

	private Text questionText = new Text(" < ----- Click me first ");

	private Button neutralBackGround = new Button(" neutral background ");

	private Group gameScreen = new Group(tank, enemies, bullets, nuke, coin, missiles);

	private Text title = new Text("Operation Z-Tank!");
	private Text subtitle = new Text(" press W,A,S,D to play ");
	private Text gameDifficultyText = new Text(" Easy ");
	private Group titleScreen = new Group(title, subtitle, easy, veryEasy, outrageouslyEasy, questionText,
			impossiblyNormal, questionButton, unQuestionButton, neutralBackGround, gameDifficultyText,
			ScoreChallangePrompt);

	private Text storyTxt = new Text(
			"Soldier your task is to survive the oncoming waves of zombies as the last tank alive!!\n"
					+ " Your mission is to hold the line and ensure survival until reinforcements arive\n"
					+ " The battlefield is full of enemies which you must navigate carefully\n"
					+ " Use W,A,S,D to move around the battleField\n"
					+ " Left click your mouse to fire bullets across the map\n"
					+ " Right click your mouse to fire a rocket which will obliterate anyone in its path\n"
					+ " Use your space bar to call in for a nuke and destroy all enemies on the map\n"
					+ " Collect coins to use abilities and upgrade your Tank, Collect hearts to regain health\n"
					+ " Press P during the battle to open the menu or pause the game\n"
					+ " Press Q to upgrade your canon during the fight ( tip 3 levels )\n"
					+ " Remember Soldier, the fate of our forces rests in your hands, Survive the swarm of enemies and help will come\n"
					+ " The enemy is relentless, but so are we. Good luck!");
	private Text rocketTextExplanation = new Text(
			" for " + rocketCost + " coins Right click to use the rocket ability ");
	private Text nukeTextExplanation = new Text(
			" for " + nukeCost + " coins press the spacebar to use the nuke ability ");
	private Text bulletUpgradeExplanaiton = new Text(
			" for " + bulletUpgradePrice + " coins press Q to upgrade your canon");

	private Text EnemiesToUnlockText = new Text(
			" these are the enemies which you may encounter                                            | Game Made by Dawid Kraj in java using javaFX |");
	private ImageView nukeExp = new ImageView(nukeImage);
	private Text BombHintExp = new Text("press Space");
	private ImageView ultraShotExp = new ImageView(ultrashotImage);
//	private ImageView ultraShot = new ImageView("file:Resources/missle.png");
	private Text ultraShotTextExp = new Text("right click");

	private ImageView bulletImageExp = new ImageView(bulletImageImage);

//	private ImageView bulletImage = new ImageView("file:Resources/bullet.png");
	private Text bulletTextImageExp = new Text("press Q");
	private Text bulletUpgradeExp = new Text("+");
	private Group menu = new Group(unQuestionButton, storyTxt, rocketTextExplanation, nukeTextExplanation,
			EnemiesToUnlockText, bulletUpgradeExplanaiton, nukeExp, BombHintExp, ultraShotExp, ultraShotTextExp,
			bulletImageExp, bulletTextImageExp, bulletUpgradeExp);

	private Text score = new Text("Score :");
	private Text coinText = new Text("X");
	private Text highscore = new Text("High score :");

	private AnimationTimer gameTimer;
	private long previousTime = -1;

	private int gameState = TITLE_SCREEN;

	// sound stuff
	Sound music = new Sound();
	Sound soundFX = new Sound();

	// curser image

	private Image cursorImage = new Image(getClass().getClassLoader().getResource("target.png").toString());
//	private ImageView cursorImageView = new ImageView(cursorImage);// dont think i need to bother with this well see 

	public Interface() {

		tank.setPosition(PLAYER_GAME_WIDTH / 2 - tank.getWidth() / 2, PLAYER_GAME_HEIGHT / 2);

		gameTimer = new AnimationTimer() {

			public void handle(long currentTime) {
				// calculate the elapsed time
				double elapsedTime = (currentTime - previousTime) / 1000000000.0;
				// call the update Game time
				updateGame(elapsedTime);

				// save the current time
				previousTime = currentTime;

			}

			public void start() {
				previousTime = System.nanoTime();
				super.start();

			}

			public void stop() {
				previousTime = -1;
				super.stop();

			}
		};

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		int PlayerScreenHeight;
		int PlayerScreenWidth;

		PlayerScreenWidth = GAME_WIDTH / PLAYER_GAME_WIDTH;
		PlayerScreenHeight = GAME_HEIGHT / PLAYER_GAME_HEIGHT;

		title.setFont(Font.font("Arial", 70 * scale));
		title.relocate(PLAYER_GAME_WIDTH / 2 - title.getBoundsInParent().getWidth() / 2, 400 * scale);
		title.setFill(Color.YELLOW);
		title.setStroke(Color.ROYALBLUE);
		title.setStrokeWidth(3 * scale);
		title.setEffect(new DropShadow());

		subtitle.setFont(Font.font("Arial", 30 * scale));
		subtitle.relocate(PLAYER_GAME_WIDTH / 2 - subtitle.getBoundsInParent().getWidth() / 2, scale * 850);
		subtitle.setFill(Color.YELLOW);
		subtitle.setStroke(Color.ROYALBLUE);
		subtitle.setStrokeWidth(1);
		subtitle.setEffect(new DropShadow());

		score.setFont(Font.font("Arial", 20 * scale));
		score.relocate(scale * 20, scale * 20);
		score.setFill(Color.YELLOW);
		score.setStroke(Color.ROYALBLUE);
		score.setStrokeWidth(1);

		ScoreChallangePrompt.setFont(Font.font("Arial", 30 * scale));
		ScoreChallangePrompt.relocate(PLAYER_GAME_WIDTH / 2 - subtitle.getBoundsInParent().getWidth() / 2 - 100 * scale,
				scale * 750);
		ScoreChallangePrompt.setFill(Color.YELLOW);
		ScoreChallangePrompt.setStroke(Color.ROYALBLUE);
		ScoreChallangePrompt.setStrokeWidth(1);
		ScoreChallangePrompt.setOpacity(0);

		livesText.setFont(Font.font("Arial", 20 * scale));
		livesText.relocate(scale * 180, scale * 20);
		livesText.setFill(Color.YELLOW);
		livesText.setStroke(Color.ROYALBLUE);
		livesText.setStrokeWidth(1);

		heart.relocate(scale * 140, scale * 15);
		heart.setOpacity(1);
		heart.setFitHeight(30);
		heart.setFitWidth(55);

		heart2.relocate(scale * 160, scale * 15);
		heart2.setOpacity(1);
		heart2.setFitHeight(30);
		heart2.setFitWidth(55);

		heart3.relocate(scale * 180, scale * 15);
		heart3.setOpacity(1);
		heart3.setFitHeight(30);
		heart3.setFitWidth(55);

		heart4.relocate(scale * 200, scale * 15);
		heart4.setOpacity(1);
		heart4.setFitHeight(30);
		heart4.setFitWidth(55);

		heart5.relocate(scale * 220, scale * 15);
		heart5.setOpacity(1);
		heart5.setFitHeight(30);
		heart5.setFitWidth(55);

		heart6.relocate(scale * 240, scale * 15);
		heart6.setOpacity(1);
		heart6.setFitHeight(30);
		heart6.setFitWidth(55);

		heart7.relocate(scale * 260, scale * 15);
		heart7.setOpacity(0);
		heart7.setFitHeight(30);
		heart7.setFitWidth(55);

		heart8.relocate(scale * 280, scale * 15);
		heart8.setOpacity(0);
		heart8.setFitHeight(30);
		heart8.setFitWidth(55);

		heart9.relocate(scale * 300, scale * 15);
		heart9.setOpacity(0);
		heart9.setFitHeight(30);
		heart9.setFitWidth(55);

		heart10.relocate(scale * 320, scale * 15);
		heart10.setOpacity(0);
		heart10.setFitHeight(30);
		heart10.setFitWidth(55);

		heart11.relocate(scale * 340, scale * 15);
		heart11.setOpacity(0);
		heart11.setFitHeight(30);
		heart11.setFitWidth(55);

		heart12.relocate(scale * 360, scale * 15);
		heart12.setOpacity(0);
		heart12.setFitHeight(30);
		heart12.setFitWidth(55);

		heart13.relocate(scale * 380, scale * 15);
		heart13.setOpacity(0);
		heart13.setFitHeight(30);
		heart13.setFitWidth(55);

		heart14.relocate(scale * 400, scale * 15);
		heart14.setOpacity(0);
		heart14.setFitHeight(30);
		heart14.setFitWidth(55);

		heart15.relocate(scale * 420, scale * 15);
		heart15.setOpacity(0);
		heart15.setFitHeight(30);
		heart15.setFitWidth(55);
		
		healthBar32.relocate(650 * scale,8 * scale);
		healthBar32.setOpacity(0);
		healthBar32.setFitHeight(80* scale);
		healthBar32.setFitWidth(600 * scale);
		
		healthBar31.relocate(650 * scale,8 * scale);
		healthBar31.setOpacity(0);
		healthBar31.setFitHeight(80* scale);
		healthBar31.setFitWidth(600 * scale);
		
		healthBar30.relocate(650 * scale,8 * scale);
		healthBar30.setOpacity(0);
		healthBar30.setFitHeight(80* scale);
		healthBar30.setFitWidth(600 * scale);
		
		healthBar29.relocate(650 * scale,8 * scale);
		healthBar29.setOpacity(0);
		healthBar29.setFitHeight(80* scale);
		healthBar29.setFitWidth(600 * scale);
		
		healthBar28.relocate(650 * scale,8 * scale);
		healthBar28.setOpacity(0);
		healthBar28.setFitHeight(80* scale);
		healthBar28.setFitWidth(600 * scale);
		
		healthBar27.relocate(650 * scale,8 * scale);
		healthBar27.setOpacity(0);
		healthBar27.setFitHeight(80* scale);
		healthBar27.setFitWidth(600 * scale);
		
		healthBar26.relocate(650 * scale,8 * scale);
		healthBar26.setOpacity(0);
		healthBar26.setFitHeight(80* scale);
		healthBar26.setFitWidth(600 * scale);
		
		healthBar25.relocate(650 * scale,8 * scale);
		healthBar25.setOpacity(0);
		healthBar25.setFitHeight(80* scale);
		healthBar25.setFitWidth(600 * scale);
		
		healthBar24.relocate(650 * scale,8 * scale);
		healthBar24.setOpacity(0);
		healthBar24.setFitHeight(80* scale);
		healthBar24.setFitWidth(600 * scale);
		
		healthBar23.relocate(650 * scale,8 * scale);
		healthBar23.setOpacity(0);
		healthBar23.setFitHeight(80* scale);
		healthBar23.setFitWidth(600 * scale);
		
		healthBar22.relocate(650 * scale,8 * scale);
		healthBar22.setOpacity(0);
		healthBar22.setFitHeight(80* scale);
		healthBar22.setFitWidth(600 * scale);
		
		healthBar21.relocate(650 * scale,8 * scale);
		healthBar21.setOpacity(0);
		healthBar21.setFitHeight(80* scale);
		healthBar21.setFitWidth(600 * scale);
		
		healthBar20.relocate(650 * scale,8 * scale);
		healthBar20.setOpacity(0);
		healthBar20.setFitHeight(80* scale);
		healthBar20.setFitWidth(600 * scale);
		
		healthBar19.relocate(650 * scale,8 * scale);
		healthBar19.setOpacity(0);
		healthBar19.setFitHeight(80* scale);
		healthBar19.setFitWidth(600 * scale);
		
		healthBar18.relocate(650 * scale,8 * scale);
		healthBar18.setOpacity(0);
		healthBar18.setFitHeight(80* scale);
		healthBar18.setFitWidth(600 * scale);
		
		healthBar17.relocate(650 * scale,8 * scale);
		healthBar17.setOpacity(0);
		healthBar17.setFitHeight(80* scale);
		healthBar17.setFitWidth(600 * scale);
		
		healthBar16.relocate(650 * scale,8 * scale);
		healthBar16.setOpacity(0);
		healthBar16.setFitHeight(80* scale);
		healthBar16.setFitWidth(600 * scale);
		
		healthBar15.relocate(650 * scale,8 * scale);
		healthBar15.setOpacity(0);
		healthBar15.setFitHeight(80* scale);
		healthBar15.setFitWidth(600 * scale);
		
		healthBar14.relocate(650 * scale,8 * scale);
		healthBar14.setOpacity(0);
		healthBar14.setFitHeight(80* scale);
		healthBar14.setFitWidth(600 * scale);
		
		healthBar13.relocate(650 * scale,8 * scale);
		healthBar13.setOpacity(0);
		healthBar13.setFitHeight(80* scale);
		healthBar13.setFitWidth(600 * scale);
		
		healthBar12.relocate(650 * scale,8 * scale);
		healthBar12.setOpacity(0);
		healthBar12.setFitHeight(80* scale);
		healthBar12.setFitWidth(600 * scale);
		
		healthBar11.relocate(650 * scale,8 * scale);
		healthBar11.setOpacity(0);
		healthBar11.setFitHeight(80* scale);
		healthBar11.setFitWidth(600 * scale);
		
		healthBar10.relocate(650 * scale,8 * scale);
		healthBar10.setOpacity(0);
		healthBar10.setFitHeight(80* scale);
		healthBar10.setFitWidth(600 * scale);
		
		healthBar9.relocate(650 * scale,8 * scale);
		healthBar9.setOpacity(0);
		healthBar9.setFitHeight(80* scale);
		healthBar9.setFitWidth(600 * scale);
		
		healthBar8.relocate(650 * scale,8 * scale);
		healthBar8.setOpacity(0);
		healthBar8.setFitHeight(80* scale);
		healthBar8.setFitWidth(600 * scale);
		
		healthBar7.relocate(650 * scale,8 * scale);
		healthBar7.setOpacity(0);
		healthBar7.setFitHeight(80* scale);
		healthBar7.setFitWidth(600 * scale);
		
		healthBar6.relocate(650 * scale,8 * scale);
		healthBar6.setOpacity(0);
		healthBar6.setFitHeight(80* scale);
		healthBar6.setFitWidth(600 * scale);
		
		healthBar5.relocate(650 * scale,8 * scale);
		healthBar5.setOpacity(0);
		healthBar5.setFitHeight(80* scale);
		healthBar5.setFitWidth(600 * scale);
		
		healthBar4.relocate(650 * scale,8 * scale);
		healthBar4.setOpacity(0);
		healthBar4.setFitHeight(80* scale);
		healthBar4.setFitWidth(600 * scale);
		
		healthBar3.relocate(650 * scale,8 * scale);
		healthBar3.setOpacity(0);
		healthBar3.setFitHeight(80* scale);
		healthBar3.setFitWidth(600 * scale);
		
		healthBar2.relocate(650 * scale,8 * scale);
		healthBar2.setOpacity(0);
		healthBar2.setFitHeight(80* scale);
		healthBar2.setFitWidth(600 * scale);
		
		healthBar1.relocate(650 * scale,8 * scale);
		healthBar1.setOpacity(0);
		healthBar1.setFitHeight(80* scale);
		healthBar1.setFitWidth(600 * scale);
		
		FullhealthBarImg.relocate(650 * scale,8 * scale);
		FullhealthBarImg.setOpacity(0);
		FullhealthBarImg.setFitHeight(80* scale);
		FullhealthBarImg.setFitWidth(600 * scale);
		
		
		
		


		coinText.setFont(Font.font("Arial", 20 * scale));
		coinText.relocate(scale * 60, scale * 55);
		coinText.setFill(Color.YELLOW);
		coinText.setStroke(Color.ROYALBLUE);
		coinText.setStrokeWidth(1);

		coin.relocate(scale * 20, scale * 50);
		coin.setOpacity(1);
		coin.setFitHeight(30* scale);
		coin.setFitWidth(30 * scale);

		highscore.setFont(Font.font("Arial", 20 * scale));
		highscore.relocate(scale * 1700, scale * 30);
		highscore.setFill(Color.YELLOW);
		highscore.setStroke(Color.ROYALBLUE);
		highscore.setStrokeWidth(1);

		nuke.relocate(scale * 125, scale * 105);
		nuke.setFitHeight(50 * scale);
		nuke.setFitWidth(30 * scale);
		nuke.setOpacity(0);

		BombHint.setFont(Font.font("Harlow Solid Italic", 20 * scale));
		BombHint.setFill(Color.WHITE);
		BombHint.setEffect(new DropShadow());
		BombHint.setStroke(Color.WHITE);
		BombHint.setStrokeWidth(1);
		BombHint.relocate(scale * 20, scale * 120);
		BombHint.setOpacity(0);

		ultraShot.relocate(scale * 115, scale * 77);
		ultraShot.setFitHeight(30);
		ultraShot.setFitWidth(50);
		ultraShot.setOpacity(0);

		ultraShotText.setFont(Font.font("Harlow Solid Italic", 20 * scale));
		ultraShotText.setFill(Color.WHITE);
		ultraShotText.setEffect(new DropShadow());
		ultraShotText.setStroke(Color.WHITE);
		ultraShotText.setStrokeWidth(1);
		ultraShotText.relocate(scale * 20, scale * 80);
		ultraShotText.setOpacity(0);

		bulletImage.relocate(scale * 100, scale * 155);
		bulletImage.setFitHeight(25);
		bulletImage.setFitWidth(12);
		bulletImage.setOpacity(0);

		bulletTextImage.setFont(Font.font("Harlow Solid Italic", 20 * scale));
		bulletTextImage.setFill(Color.WHITE);
		bulletTextImage.setEffect(new DropShadow());
		bulletTextImage.setStroke(Color.WHITE);
		bulletTextImage.setStrokeWidth(1);
		bulletTextImage.relocate(scale * 20, scale * 160);
		bulletTextImage.setOpacity(0);

		bulletUpgrade.setFont(Font.font("Harlow Solid Italic", 20 * scale));
		bulletUpgrade.setFill(Color.WHITE);
		bulletUpgrade.setEffect(new DropShadow());
		bulletUpgrade.setStroke(Color.WHITE);
		bulletUpgrade.setStrokeWidth(1);
		bulletUpgrade.relocate(scale * 113, scale * 155);
		bulletUpgrade.setOpacity(0);

		bulletUpgrade2.setFont(Font.font("Harlow Solid Italic", 20 * scale));
		bulletUpgrade2.setFill(Color.WHITE);
		bulletUpgrade2.setEffect(new DropShadow());
		bulletUpgrade2.setStroke(Color.WHITE);
		bulletUpgrade2.setStrokeWidth(1);
		bulletUpgrade2.relocate(scale * 123, scale * 155);
		bulletUpgrade2.setOpacity(0);

		// setup buttons for the title page
		easy.relocate(scale * 220, scale * 300);
		easy.setStyle("-fx-background-color: lightblue; -fx-text-fill: white;");
		easy.setOnMouseEntered(e -> easy.setStyle("-fx-background-color: blue; -fx-text-fill: white;"));
		easy.setOnMouseExited(e -> easy.setStyle("-fx-background-color: lightblue; -fx-text-fill: white;"));
		easy.setOnMousePressed(e -> easy.setStyle("-fx-background-color: darkblue; -fx-text-fill: white;"));
		easy.setOnMouseReleased(e -> easy.setStyle("-fx-background-color: blue; -fx-text-fill: white;"));
		easy.setPrefSize(200, 70);
		easy.setOnAction(e -> easyMode());

		veryEasy.relocate(scale * 220, scale * 400);
		veryEasy.setPrefSize(200, 70);
		veryEasy.setStyle("-fx-background-color: lightgreen; -fx-text-fill: black;");
		veryEasy.setOnMouseEntered(e -> veryEasy.setStyle("-fx-background-color: green; -fx-text-fill: black;"));
		veryEasy.setOnMouseExited(e -> veryEasy.setStyle("-fx-background-color: lightgreen; -fx-text-fill: black;"));
		veryEasy.setOnMousePressed(e -> veryEasy.setStyle("-fx-background-color: darkgreen; -fx-text-fill: black;"));
		veryEasy.setOnMouseReleased(e -> veryEasy.setStyle("-fx-background-color: green; -fx-text-fill: black;"));
		veryEasy.setOnAction(e -> normalMode());

		outrageouslyEasy.relocate(scale * 220, scale * 500);
		outrageouslyEasy.setPrefSize(200, 70);
		outrageouslyEasy.setStyle("-fx-background-color: indianred; -fx-text-fill: white;");
		outrageouslyEasy
				.setOnMouseEntered(e -> outrageouslyEasy.setStyle("-fx-background-color: red; -fx-text-fill: white;"));
		outrageouslyEasy.setOnMouseExited(
				e -> outrageouslyEasy.setStyle("-fx-background-color: indianred; -fx-text-fill: white;"));
		outrageouslyEasy.setOnMousePressed(
				e -> outrageouslyEasy.setStyle("-fx-background-color: darkred; -fx-text-fill: white;"));
		outrageouslyEasy
				.setOnMouseReleased(e -> outrageouslyEasy.setStyle("-fx-background-color: red; -fx-text-fill: white;"));
		outrageouslyEasy.setOnAction(e -> hardMode());

		impossiblyNormal.relocate(scale * 220, scale * 600);
		impossiblyNormal.setPrefSize(200, 70);
		impossiblyNormal.setStyle("-fx-background-color: black; -fx-text-fill: white;");
		impossiblyNormal.setOnMouseEntered(
				e -> impossiblyNormal.setStyle("-fx-background-color: black; -fx-text-fill: white;"));
		impossiblyNormal
				.setOnMouseExited(e -> impossiblyNormal.setStyle("-fx-background-color: black; -fx-text-fill: white;"));
		impossiblyNormal.setOnMousePressed(
				e -> impossiblyNormal.setStyle("-fx-background-color: white; -fx-text-fill: black;"));
		impossiblyNormal.setOnMouseReleased(
				e -> impossiblyNormal.setStyle("-fx-background-color: black; -fx-text-fill: white;"));
		impossiblyNormal.setVisible(true);
		impossiblyNormal.setOnAction(e -> ultraHardMode());
		impossiblyNormal.setVisible(false);

		// setup enemie progressions images and text

		unknownEnemy1.relocate(scale * 350, scale * 870);
		unknownEnemy1.setOpacity(0);
		unknownEnemy1.setFitHeight(80);
		unknownEnemy1.setFitWidth(80);

		unknownEnemy2.relocate(scale * 500, scale * 870);
		unknownEnemy2.setOpacity(0);
		unknownEnemy2.setFitHeight(80);
		unknownEnemy2.setFitWidth(80);

		unknownEnemy3.relocate(scale * 650, scale * 870);
		unknownEnemy3.setOpacity(0);
		unknownEnemy3.setFitHeight(80);
		unknownEnemy3.setFitWidth(80);

		unknownEnemy4.relocate(scale * 800, scale * 870);
		unknownEnemy4.setOpacity(0);
		unknownEnemy4.setFitHeight(80);
		unknownEnemy4.setFitWidth(80);

		unknownEnemy5.relocate(scale * 950, scale * 870);
		unknownEnemy5.setOpacity(0);
		unknownEnemy5.setFitHeight(80);
		unknownEnemy5.setFitWidth(80);

		unknownEnemy6.relocate(scale * 1100, scale * 870);
		unknownEnemy6.setOpacity(0);
		unknownEnemy6.setFitHeight(80);
		unknownEnemy6.setFitWidth(80);

		unknownEnemy7.relocate(scale * 1250, scale * 870);
		unknownEnemy7.setOpacity(0);
		unknownEnemy7.setFitHeight(80);
		unknownEnemy7.setFitWidth(80);

		unknownEnemy8.relocate(scale * 1400, scale * 870);
		unknownEnemy8.setOpacity(0);
		unknownEnemy8.setFitHeight(80);
		unknownEnemy8.setFitWidth(80);

		unknownEnemy9.relocate(scale * 1550, scale * 870);
		unknownEnemy9.setOpacity(0);
		unknownEnemy9.setFitHeight(80);
		unknownEnemy9.setFitWidth(80);

		unknownEnemy10.relocate(scale * 1700, scale * 870);
		unknownEnemy10.setOpacity(0);
		unknownEnemy10.setFitHeight(80);
		unknownEnemy10.setFitWidth(80);

		basicZombieImg.relocate(scale * 370, scale * 880);
		basicZombieImg.setOpacity(0);
		// basicZombieImg.setFitHeight(80);
		// basicZombieImg.setFitWidth(80);

		skeletonZombieImg.relocate(scale * 510, scale * 870);
		skeletonZombieImg.setOpacity(0);
		// skletonZombieImg.setFitHeight(80);
		// skletonZombieImg.setFitWidth(80);

		ladyZombieImg.relocate(scale * 655, scale * 870);
		ladyZombieImg.setOpacity(0);
		// ladyZombieImg.setFitHeight(80);
		// ladyZombieImg.setFitWidth(80);

		missleImg.relocate(scale * 800, scale * 890);
		missleImg.setOpacity(0);
		// missleImg.setFitHeight(80);
		// missleImg.setFitWidth(80);

		ratImg.relocate(scale * 970, scale * 890);
		ratImg.setOpacity(0);
		// ratImg.setFitHeight(80);
		// ratImg.setFitWidth(80);

		sourcerImg.relocate(scale * 1120, scale * 890);
		sourcerImg.setOpacity(0);
		// sourcerImg.setFitHeight(80);
		// sourcerImg.setFitWidth(80); 890

		poisonSourcerImg.relocate(scale * 1120, scale * 890);
		poisonSourcerImg.setOpacity(0);
		// poisonSourcerImg.setFitHeight(80);1420
		// poisonSourcerImg.setFitWidth(80)890

		deflectorImg.relocate(scale * 1260, scale * 885);
		deflectorImg.setOpacity(0);
		// deflectorImg.setFitHeight(80)1260
		// deflectorImg.setFitWidth(80);885

		GolemImg.relocate(scale * 1365, scale * 860);
		GolemImg.setOpacity(0);
		// GolemImg.setFitHeight(80);1520
		// GolemImg.setFitWidth(80); 860

		sourcerImg.relocate(scale * 1570, scale * 900);
		sourcerImg.setOpacity(0);
		// sourcerImg.setFitHeight(80);
		// sourcerImg.setFitWidth(80);
//		Stage questionMarkStage = new Stage();

		questionButton.relocate(scale * 150, scale * 870);
		questionButton.setPrefSize(scale * 100, scale * 100);
		questionButton.setStyle("-fx-font-size: 35px;");
		questionButton.setOnAction(e -> questionButton());

		unQuestionButton.relocate(scale * 150, scale * 870);
		unQuestionButton.setPrefSize(scale * 100, scale * 100);
		unQuestionButton.setStyle("-fx-font-size: 35px;");
		unQuestionButton.setVisible(false);
		unQuestionButton.setOnAction(e -> unQuestionButton());

		questionText.setFont(Font.font("Arial", 40 * scale));
		questionText.relocate(scale * 250, scale * 900);
		questionText.setFill(Color.YELLOW);
		questionText.setStroke(Color.ROYALBLUE);
		questionText.setStrokeWidth(1);

		neutralBackGround.setPrefSize(150, 50);
		neutralBackGround.relocate(scale * 1420, scale * 900);
		neutralBackGround.setOnAction(e -> gameScene.setFill(Color.GREY));

		gameDifficultyText.setFont(Font.font("Arial", 100 * scale));
		gameDifficultyText.relocate(
				PLAYER_GAME_WIDTH / 2 - title.getBoundsInParent().getWidth() / 2 + PlayerScreenWidth * 150,
				GAME_HEIGHT / PLAYER_GAME_HEIGHT * 150);
		gameDifficultyText.setFill(Color.YELLOW);
		gameDifficultyText.setStroke(Color.ROYALBLUE);
		gameDifficultyText.setStrokeWidth(3);
		gameDifficultyText.setEffect(new DropShadow());
		gameDifficultyText.setOpacity(0);

		// these will be for the menu explaining all the mouvement abilities and what
		// the game is about
		int OpacitySetter = 1;

		nukeExp.relocate(scale * 325, scale * 555);
		nukeExp.setFitHeight(50);
		nukeExp.setFitWidth(30);
		nukeExp.setOpacity(OpacitySetter);

		BombHintExp.setFont(Font.font("Harlow Solid Italic", 25 * scale));
		BombHintExp.setFill(Color.WHITE);
		BombHintExp.setEffect(new DropShadow());
		BombHintExp.setStroke(Color.WHITE);
		BombHintExp.setStrokeWidth(1);
		BombHintExp.relocate(scale * 200, scale * 570);
		BombHintExp.setOpacity(OpacitySetter);

		ultraShotExp.relocate(scale * 315, scale * 477);
		ultraShotExp.setFitHeight(30);
		ultraShotExp.setFitWidth(50);
		ultraShotExp.setOpacity(OpacitySetter);

		ultraShotTextExp.setFont(Font.font("Harlow Solid Italic", 25 * scale));
		ultraShotTextExp.setFill(Color.WHITE);
		ultraShotTextExp.setEffect(new DropShadow());
		ultraShotTextExp.setStroke(Color.WHITE);
		ultraShotTextExp.setStrokeWidth(1);
		ultraShotTextExp.relocate(scale * 200, scale * 480);
		ultraShotTextExp.setOpacity(OpacitySetter);

		bulletImageExp.relocate(scale * 300, scale * 655);
		bulletImageExp.setFitHeight(25);
		bulletImageExp.setFitWidth(12);
		bulletImageExp.setOpacity(OpacitySetter);

		bulletTextImageExp.setFont(Font.font("Harlow Solid Italic", 25 * scale));
		bulletTextImageExp.setFill(Color.WHITE);
		bulletTextImageExp.setEffect(new DropShadow());
		bulletTextImageExp.setStroke(Color.WHITE);
		bulletTextImageExp.setStrokeWidth(1);
		bulletTextImageExp.relocate(scale * 200, scale * 660);
		bulletTextImageExp.setOpacity(OpacitySetter);

		bulletUpgradeExp.setFont(Font.font("Harlow Solid Italic", 25 * scale));
		bulletUpgradeExp.setFill(Color.WHITE);
		bulletUpgradeExp.setEffect(new DropShadow());
		bulletUpgradeExp.setStroke(Color.WHITE);
		bulletUpgradeExp.setStrokeWidth(1);
		bulletUpgradeExp.relocate(scale * 313, scale * 655);
		bulletUpgradeExp.setOpacity(OpacitySetter);

		rocketTextExplanation.setFont(Font.font("Harlow Solid Italic", 25 * scale));
		rocketTextExplanation.setFill(Color.WHITE);
		rocketTextExplanation.setEffect(new DropShadow());
		rocketTextExplanation.setStroke(Color.WHITE);
		rocketTextExplanation.setStrokeWidth(1);
		rocketTextExplanation.relocate(scale * 403, scale * 480);
		rocketTextExplanation.setOpacity(OpacitySetter);

		nukeTextExplanation.setFont(Font.font("Harlow Solid Italic", 25 * scale));
		nukeTextExplanation.setFill(Color.WHITE);
		nukeTextExplanation.setEffect(new DropShadow());
		nukeTextExplanation.setStroke(Color.WHITE);
		nukeTextExplanation.setStrokeWidth(1);
		nukeTextExplanation.relocate(scale * 403, scale * 570);
		nukeTextExplanation.setOpacity(OpacitySetter);

		bulletUpgradeExplanaiton.setFont(Font.font("Harlow Solid Italic", 25 * scale));
		bulletUpgradeExplanaiton.setFill(Color.WHITE);
		bulletUpgradeExplanaiton.setEffect(new DropShadow());
		bulletUpgradeExplanaiton.setStroke(Color.WHITE);
		bulletUpgradeExplanaiton.setStrokeWidth(1);
		bulletUpgradeExplanaiton.relocate(scale * 403, scale * 655);
		bulletUpgradeExplanaiton.setOpacity(OpacitySetter);

		EnemiesToUnlockText.setFont(Font.font("Arial", 25 * scale));
		EnemiesToUnlockText.setFill(Color.YELLOW);
		EnemiesToUnlockText.setEffect(new DropShadow());
	EnemiesToUnlockText.setStroke(Color.ROYALBLUE);
		EnemiesToUnlockText.setStrokeWidth(1);
		EnemiesToUnlockText.relocate(scale * 403, scale * 805);
		EnemiesToUnlockText.setOpacity(OpacitySetter);

		storyTxt.setFont(Font.font("Arial", 25 * scale));
		storyTxt.relocate(scale * 200, scale * 100);
		storyTxt.setFill(Color.YELLOW);
		storyTxt.setStroke(Color.ROYALBLUE);

		storyTxt.setEffect(new DropShadow());
		storyTxt.setOpacity(OpacitySetter);
		menu.setVisible(false);
		root = new Group();
		root.getChildren().addAll(score, highscore, titleScreen, EnemyProgression, gameScreen, menu, coin, nuke,
				BombHint, coinText, ultraShot, ultraShotText, heart, heart2, heart3, heart4, heart5, heart6, heart7,
				heart8, heart9, heart10, heart11, heart12, heart13, heart14, heart15, bulletImage, bulletTextImage,
				bulletUpgrade, bulletUpgrade2,bossHealthBar);

//		Color gameColor = Color.GREY;
//		if (difficutlyType == 0) {
//			gameColor = Color.INDIANRED;
//		} else if (difficutlyType == 1) {
//			gameColor = Color.ROYALBLUE;
//		} else if (difficutlyType == 2) {
//			gameColor = Color.DARKGREEN;
//		} else if (difficutlyType == 3) {
//			gameColor = Color.BLACK;
//		} else if (difficutlyType == 4) {
//			gameColor = Color.GREY;
//		}

		// todo
		// add a text saying , can you make it to x amount like 100000,
		// if player got to 5863 points, say can you make it to 10000
		// expoenetially get bigger and say the end goal is 100000 points
		gameScene = new Scene(root, GAME_WIDTH, GAME_HEIGHT, gameColor); // royalblue,iondianred, grey, darkgreen

		gameScene.setFill(gameColor);

		gameScene.setOnMouseClicked(event -> {
			if (event.getButton() == MouseButton.PRIMARY) {
				shootBullet(event);
			} else if (event.getButton() == MouseButton.SECONDARY) {
				shootMissile(event);

			}

		});

		gameScene.setCursor(new ImageCursor(cursorImage, cursorImage.getWidth() / 2, cursorImage.getHeight() / 2));

		// d gameScene.add // add the th8inkgy here so mouse cannot move off the screen
		gameScene.setOnMouseMoved(event -> updateTankDirection(event.getX(), event.getY()));

		gameScene.setOnKeyPressed(key -> keyPressed(key));
		gameScene.setOnKeyReleased(key -> keyReleased(key));

		primaryStage.setScene(gameScene);
//		primaryStage.initStyle(StageStyle.UNDECORATED);
		// primaryStage.setResizable(false);
		primaryStage.setFullScreen(true);
		// primaryStage.setFullScreenExitHint("Esc to exit");
		primaryStage.show();

	}

	public void autoGun() {
		// give a timer and when player holds down they will shoot so fingy wont hurt ,
		// but may take a while to put togeth so for later
	}

	public void questionButton() {
		// maybe instead of dooing a new tage i simply set al lthe opacityies to zero
		// and then i just creata some new text to eplani nhow th egame workd and how to
		// use the abbilities here
		// gameDifficultyText.setVisible(false);
		titleScreen.setVisible(false);
		menu.setVisible(true);
		unQuestionButton.setVisible(true);
		EnemyProgression.setVisible(true);
		EnemiesUnlockedMenu();

	}

	public void unQuestionButton() {
		titleScreen.setVisible(true);
		questionText.setVisible(false);
		unQuestionButton.setVisible(false);
		EnemyProgression.setVisible(false);
		menu.setVisible(false);
	}

	public void upgradeMenu() {

	}

	private void EnemiesUnlockedMenu() {
		unknownEnemy1.setOpacity(1);
		unknownEnemy2.setOpacity(1);
		unknownEnemy3.setOpacity(1);
		unknownEnemy4.setOpacity(1);
		unknownEnemy5.setOpacity(1);
		unknownEnemy6.setOpacity(1);
		unknownEnemy7.setOpacity(1);
		unknownEnemy8.setOpacity(1);
		unknownEnemy9.setOpacity(1);
		unknownEnemy10.setOpacity(1);

		if (highScore >= basicEnemieScore + 100) {
			basicZombieImg.setOpacity(1);
			unknownEnemy1.setOpacity(0);
		}
		if (highScore >= mediumSkeletonScore + 100) {
			skeletonZombieImg.setOpacity(1);
			unknownEnemy2.setOpacity(0);
		}
		if (highScore >= largeLadyScore + 100) {
			ladyZombieImg.setOpacity(1);
			unknownEnemy3.setOpacity(0);
		}
		if (highScore >= missleScore + 100) {
			missleImg.setOpacity(1);
			unknownEnemy4.setOpacity(0);
		}
		if (highScore >= FastRatScore + 100) {
			ratImg.setOpacity(1);
			unknownEnemy5.setOpacity(0);
		}
		if (highScore >= poisonSourcerSpawnScore + 200) {
			poisonSourcerImg.setOpacity(1);
			unknownEnemy6.setOpacity(0);
		}
		if (highScore >= deflectorSpawnScore + 200) {
			deflectorImg.setOpacity(1);
			unknownEnemy7.setOpacity(0);
		}
		if (highScore >= GolemSpawnScore + 200) {
			GolemImg.setOpacity(1);
			unknownEnemy8.setOpacity(0);
		}
		if (highScore >= SourcerSpawnScore + 200) {
			sourcerImg.setOpacity(1);
			unknownEnemy9.setOpacity(0);
		}
	}

	public void keyPressed(KeyEvent key) {

		if (gameState == PLAYING) {
			if (!keyboard.contains(KeyCode.P)) {
				if (key.getCode() == KeyCode.P) {
					pauseGame();
					upgradeMenu();
					EnemiesUnlockedMenu();
					if (isPaused()) {
						EnemyProgression.setVisible(true);
						music.stop();
					} else {
						music.continuePlaying();
						music.loop();

						EnemyProgression.setVisible(false);
					}
				}
			}
		}
		if (key.getCode() == KeyCode.DELETE) {
			Platform.exit();
		}
//SOMETHING LIKE PRESS W,A,S,D TO START PROLLY
		if (!keyboard.contains(KeyCode.W) || !keyboard.contains(KeyCode.A) || !keyboard.contains(KeyCode.S)
				|| !keyboard.contains(KeyCode.D)) {
			if (gameState == TITLE_SCREEN) {
				if (key.getCode() == KeyCode.W || key.getCode() == KeyCode.A || key.getCode() == KeyCode.S
						|| key.getCode() == KeyCode.D) {
					startGame();
				}
			}

		}
		if (!keyboard.contains(KeyCode.SPACE) && TotalCoins >= nukeCost) {
			if (key.getCode() == KeyCode.SPACE) {
				playSoundFX(15);
				coinCounter(0);
				nuke();

				TotalCoins -= nukeCost;

				// switched the order sound and nuke commands because the audio has a delay
			}
		}
		if (!keyboard.contains(KeyCode.Q) && TotalCoins >= bulletUpgradePrice) {
			// playSoundFX(6);
			if (key.getCode() == KeyCode.Q) {
				playSoundFX(5);
				upgradeBullets();
				// coinCounter(0);
			}
		}
		if (!keyboard.contains(KeyCode.E) && TotalCoins >= snipeCost) {
			if (key.getCode() == KeyCode.E) {
				canSnipe = true;
				TotalCoins -= snipeCost;
			}
		}

		keyboard.add(key.getCode());

	}

	public void upgradeBullets() {

		bulletUpgradeLevel++;
		// bulletUpgradeLevel ++;
		TotalCoins -= bulletUpgradePrice;
		bulletUpgradePrice += 1000 * bulletUpgradeMultiplier;
		bulletUpgradeMultiplier++;

	}

	public void keyReleased(KeyEvent key) {
		keyboard.remove(key.getCode());

	}

	public void updateGame(double elapsedTime) {
		if (gameState == PLAYING)
			;// && difficultyModeisSelected == true
		updatePlayer(elapsedTime);
		updateEnemies(elapsedTime);
		updateBullets(elapsedTime);
		updateMissiles(elapsedTime);
		coinCounter(0);

		// updateTankDirection(elapsedTime, elapsedTime);
		if (gameState == PLAYING) {
			checkPlayerCollision();
			checkBulletCollision();
			ultraShot();
			playedSound();
			
		}
		

		cleanup();
	}
	
	public void updateBossModehealth( int type , int lives,int poisonlives,int sourcerlives,int ninjalives) {
		//double healthPercentage = (lives / (double) maxHealth) * 100; //poison is 12, sourcer is 13, ninja is 14
		
		bossHealthBar.setVisible(true);
		double healthPercantage = 100;
		if(type == 12) {
			healthPercantage = (lives / (double) poisonlives) * 100;
		//	System.out.println(" its checking healtbar and lives" + lives + " and total healt " + poisonlives + " ehalthpercentage " + healthPercantage);
		}
		else if(type == 13) {
			healthPercantage = (lives / (double) sourcerlives) * 100;
		}
		else if(type == 14) {
			healthPercantage = (lives /(double) ninjalives) * 100;
		}
		if(healthPercantage == 100) {
			
			FullhealthBarImg.setOpacity(1);
		}
		else if(healthPercantage >= 97) {
			
			FullhealthBarImg.setOpacity(0);
			healthBar1.setOpacity(1);
		}
		else if(healthPercantage >= 94) {
			FullhealthBarImg.setOpacity(0);
			healthBar1.setOpacity(0);
			healthBar2.setOpacity(1);
		}
		else if(healthPercantage >= 91) {
			FullhealthBarImg.setOpacity(0);
			healthBar1.setOpacity(0);
			healthBar2.setOpacity(0);
			healthBar3.setOpacity(1);
		}else if(healthPercantage >= 88){
			FullhealthBarImg.setOpacity(0);
			healthBar1.setOpacity(0);
			healthBar2.setOpacity(0);
			healthBar3.setOpacity(0);
			healthBar4.setOpacity(1);
		}else if(healthPercantage >= 85) {
			FullhealthBarImg.setOpacity(0);
			healthBar1.setOpacity(0);
			healthBar2.setOpacity(0);
			healthBar3.setOpacity(0);
			healthBar4.setOpacity(0);
			healthBar5.setOpacity(1);
		}else if(healthPercantage >= 82) {
			
			healthBar2.setOpacity(0);
			healthBar3.setOpacity(0);
			healthBar4.setOpacity(0);
			healthBar5.setOpacity(0);
			healthBar6.setOpacity(1);
		}else if(healthPercantage >= 79) {
			
			healthBar3.setOpacity(0);
			healthBar4.setOpacity(0);
			healthBar5.setOpacity(0);
			healthBar6.setOpacity(0);
			healthBar7.setOpacity(1);
		}
		else if(healthPercantage >= 76) {
			
			healthBar3.setOpacity(0);
			healthBar4.setOpacity(0);
			healthBar5.setOpacity(0);
			healthBar6.setOpacity(0);
			healthBar7.setOpacity(0);
			healthBar8.setOpacity(1);
		}else if(healthPercantage >= 73){
			
			healthBar4.setOpacity(0);
			healthBar5.setOpacity(0);
			healthBar6.setOpacity(0);
			healthBar7.setOpacity(0);
			healthBar8.setOpacity(0);
			healthBar9.setOpacity(1);
		}else if(healthPercantage >= 70) {
			
			healthBar5.setOpacity(0);
			healthBar6.setOpacity(0);
			healthBar7.setOpacity(0);
			healthBar8.setOpacity(0);
			healthBar9.setOpacity(0);
			healthBar10.setOpacity(1);
		}else if(healthPercantage >= 67) {
			
			healthBar6.setOpacity(0);
			healthBar7.setOpacity(0);
			healthBar8.setOpacity(0);
			healthBar9.setOpacity(0);
			healthBar10.setOpacity(0);
			healthBar11.setOpacity(1);
		}else if(healthPercantage >= 64) {
			healthBar7.setOpacity(0);
			healthBar8.setOpacity(0);
			healthBar9.setOpacity(0);
			healthBar10.setOpacity(0);
			healthBar11.setOpacity(0);
			healthBar12.setOpacity(1);
		}
		else if(healthPercantage >= 61) {
			
			healthBar9.setOpacity(0);
			healthBar10.setOpacity(0);
			healthBar11.setOpacity(0);
			healthBar12.setOpacity(0);
			healthBar13.setOpacity(1);
		}else if(healthPercantage >= 58){
			
			healthBar9.setOpacity(0);
			healthBar10.setOpacity(0);
			healthBar11.setOpacity(0);
			healthBar12.setOpacity(0);
			healthBar13.setOpacity(0);
			healthBar14.setOpacity(1);
		}else if(healthPercantage >= 55) {
			
			healthBar9.setOpacity(0);
			healthBar10.setOpacity(0);
			healthBar11.setOpacity(0);
			healthBar12.setOpacity(0);
			healthBar13.setOpacity(0);
			healthBar14.setOpacity(0);
			healthBar15.setOpacity(1);
		}else if(healthPercantage >= 52) {
		;
			healthBar9.setOpacity(0);
			healthBar10.setOpacity(0);
			healthBar11.setOpacity(0);
			healthBar12.setOpacity(0);
			healthBar13.setOpacity(0);
			healthBar14.setOpacity(0);
			healthBar15.setOpacity(0);
			healthBar16.setOpacity(1);
		}
		else if(healthPercantage >= 49) {
			
			healthBar10.setOpacity(0);
			healthBar11.setOpacity(0);
			healthBar12.setOpacity(0);
			healthBar13.setOpacity(0);
			healthBar14.setOpacity(0);
			healthBar15.setOpacity(0);
			healthBar16.setOpacity(0);
			healthBar17.setOpacity(1);
		}
		else if(healthPercantage >= 46) {
			
			healthBar11.setOpacity(0);
			healthBar12.setOpacity(0);
			healthBar13.setOpacity(0);
			healthBar14.setOpacity(0);
			healthBar15.setOpacity(0);
			healthBar16.setOpacity(0);
			healthBar17.setOpacity(0);
			healthBar18.setOpacity(1);
		}else if(healthPercantage >= 43){
			
			healthBar12.setOpacity(0);
			healthBar13.setOpacity(0);
			healthBar14.setOpacity(0);
			healthBar15.setOpacity(0);
			healthBar16.setOpacity(0);
			healthBar17.setOpacity(0);
			healthBar18.setOpacity(0);
			healthBar19.setOpacity(1);
		}else if(healthPercantage >= 40) {
			
			healthBar13.setOpacity(0);
			healthBar14.setOpacity(0);
			healthBar15.setOpacity(0);
			healthBar16.setOpacity(0);
			healthBar17.setOpacity(0);
			healthBar18.setOpacity(0);
			healthBar19.setOpacity(0);
			healthBar20.setOpacity(1);
		}else if(healthPercantage >= 37) {
			
			healthBar14.setOpacity(0);
			healthBar15.setOpacity(0);
			healthBar16.setOpacity(0);
			healthBar17.setOpacity(0);
			healthBar18.setOpacity(0);
			healthBar19.setOpacity(0);
			healthBar20.setOpacity(0);
			healthBar21.setOpacity(1);
		}
		else if(healthPercantage >= 34) {
			
			healthBar13.setOpacity(0);
			healthBar14.setOpacity(0);
			healthBar15.setOpacity(0);
			healthBar16.setOpacity(0);
			healthBar17.setOpacity(0);
			healthBar18.setOpacity(0);
			healthBar19.setOpacity(0);
			healthBar20.setOpacity(0);
			healthBar21.setOpacity(0);
			healthBar22.setOpacity(1);
		}
		else if(healthPercantage >= 31) {
			
			healthBar12.setOpacity(0);
			healthBar13.setOpacity(0);
			healthBar14.setOpacity(0);
			healthBar15.setOpacity(0);
			healthBar16.setOpacity(0);
			healthBar17.setOpacity(0);
			healthBar18.setOpacity(0);
			healthBar19.setOpacity(0);
			healthBar20.setOpacity(0);
			healthBar21.setOpacity(0);
			healthBar22.setOpacity(0);
			healthBar23.setOpacity(1);
		}else if(healthPercantage >= 28){
			
			healthBar14.setOpacity(0);
			healthBar15.setOpacity(0);
			healthBar16.setOpacity(0);
			healthBar17.setOpacity(0);
			healthBar18.setOpacity(0);
			healthBar19.setOpacity(0);
			healthBar20.setOpacity(0);
			healthBar21.setOpacity(0);
			healthBar22.setOpacity(0);
			healthBar23.setOpacity(0);
			healthBar24.setOpacity(1);
		}else if(healthPercantage >= 25) {
			
			healthBar15.setOpacity(0);
			healthBar16.setOpacity(0);
			healthBar17.setOpacity(0);
			healthBar18.setOpacity(0);
			healthBar19.setOpacity(0);
			healthBar20.setOpacity(0);
			healthBar21.setOpacity(0);
			healthBar22.setOpacity(0);
			healthBar23.setOpacity(0);
			healthBar24.setOpacity(0);
			healthBar25.setOpacity(1);
		}else if(healthPercantage >= 22) {
			
			healthBar20.setOpacity(0);
			healthBar21.setOpacity(0);
			healthBar22.setOpacity(0);
			healthBar23.setOpacity(0);
			healthBar24.setOpacity(0);
			healthBar25.setOpacity(0);
			healthBar26.setOpacity(1);
		}
		else if(healthPercantage >= 19) {
			healthBar21.setOpacity(0);
			healthBar22.setOpacity(0);
			healthBar23.setOpacity(0);
			healthBar24.setOpacity(0);
			healthBar25.setOpacity(0);
			healthBar26.setOpacity(0);
			healthBar27.setOpacity(1);
		}
		else if(healthPercantage >= 16) {
			healthBar22.setOpacity(0);
			healthBar23.setOpacity(0);
			healthBar24.setOpacity(0);
			healthBar25.setOpacity(0);
			healthBar26.setOpacity(0);
			healthBar27.setOpacity(0);
			healthBar28.setOpacity(1);
		}else if(healthPercantage >= 13){
			healthBar23.setOpacity(0);
			healthBar24.setOpacity(0);
			healthBar25.setOpacity(0);
			healthBar26.setOpacity(0);
			healthBar27.setOpacity(0);
			healthBar28.setOpacity(0);
			healthBar29.setOpacity(1);
		}else if(healthPercantage >= 10) {
			healthBar24.setOpacity(0);
			healthBar25.setOpacity(0);
			healthBar26.setOpacity(0);
			healthBar27.setOpacity(0);
			healthBar28.setOpacity(0);
			healthBar29.setOpacity(0);
			healthBar30.setOpacity(1);
		}else if(healthPercantage >= 5) {
			healthBar25.setOpacity(0);
			healthBar26.setOpacity(0);
			healthBar27.setOpacity(0);
			healthBar28.setOpacity(0);
			healthBar29.setOpacity(0);
			healthBar30.setOpacity(0);
			healthBar31.setOpacity(1);
		}
		else if(healthPercantage >= .01) {
			healthBar26.setOpacity(0);
			healthBar27.setOpacity(0);
			healthBar28.setOpacity(0);
			healthBar29.setOpacity(0);
			healthBar30.setOpacity(0);
			healthBar31.setOpacity(0);
			healthBar32.setOpacity(1);
		}
		else if(healthPercantage == 0) {
			healthBar27.setOpacity(0);
			healthBar28.setOpacity(0);
			healthBar29.setOpacity(0);
			healthBar30.setOpacity(0);
			healthBar31.setOpacity(0);
			healthBar32.setOpacity(0);
			
		
		}
		
	}


	public void updateEnemies(double elapsedTime) {

		enemies newEnemy = new enemies();

		if (newEnemiesTimer < 0 && gameState == PLAYING && enemySpawnType(newEnemy.getEnemyType()) && bossMode == false) {
			if(newEnemy.getType() == 12 || newEnemy.getType() == 13|| newEnemy.getType() == 14) {
				bossMode = true;
				setBossHeatlthToZero();
				//System.out.println("boss mode is on");
			}
			double side = Math.random() * 4; // Math.random() * 4
			double spawnX = 0, spawnY = 0;

			if (side < 1) { // Top side
				spawnX = Math.random() * PLAYER_GAME_WIDTH;
				spawnY = -newEnemy.getHeight() - 100;
			}else if (side < 2) { // Right side
				spawnX = PLAYER_GAME_WIDTH + 100;
				spawnY = Math.random() * PLAYER_GAME_HEIGHT;
			} else if (side < 3) { // Bottom side
				spawnX = Math.random() * PLAYER_GAME_WIDTH;
				spawnY = PLAYER_GAME_HEIGHT + 100;
			} else { // Left side
				spawnX = -newEnemy.getWidth() - 100;
				spawnY = Math.random() * PLAYER_GAME_WIDTH;
			}
			// System.out.println("spawn x positino" + Math.round(spawnX) + " spawn y
			// position" + Math.round(spawnY));
			newEnemy.setPosition(spawnX, spawnY);
		//	System.out.println( " spawnx" + spawnX + " spawny" + spawnY);
			// System.out.println(Math.round(spawnX));

			enemies.getChildren().add(newEnemy);
			newEnemiesTimer = Math.random() - difficultyCurve + .355; // how fast you want the enemies to spawn // -
																		// dificultyCurve
			// System.out.println("new enemies timer " + newEnemiesTimer);
			// System.out.println("difficultyCurve " + difficultyCurve);
			if (difficultyScoreAmplifier < Score) {

				difficultyCurve += 0.0001;
			} else {
				difficultyCurve += 0;
			}
		} else {
			newEnemiesTimer -= elapsedTime;
		}

		for (Node enemyNode : enemies.getChildren()) {
			
			
			if (enemyNode instanceof enemies) {

				
				enemies enemy = (enemies) enemyNode;
				int type = enemy.getType();
				if(type == 12 || type == 13 || type == 14) {
					if(enemy.isDead()) {
						bossMode = false;
					setBossHeatlthToZero();
					//	System.out.println("boss mode of ");
						}
				}
				
				// Calculate the direction vector from the enemy to the player
				double deltaX = tank.getPositionX() - enemy.getEnemyX();
				double deltaY = tank.getPositionY() - enemy.getEnemyY();

				double angleToPlayer = Math.toDegrees(Math.atan2(deltaY, deltaX));
				if (!enemy.isDead() && type != 4 && type != 5 && type != 10 && type != 0 && type != 15) {
					enemy.setRotate(angleToPlayer + 90);
				}

				// Normalize the vector to get the direction
				double length = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
				double directionX = deltaX / length;
				double directionY = deltaY / length;

				// Set the enemy's velocity based on the normalized direction

				double speed = enemySpeed(type);

				if (type == 15 || type == 4 || type == 5 || type == 10 || type == 0 && !enemy.isVelocitySet()) {// type 4 is coin
																									// zombie while type
																									// 5 is missle
																									// zombie while type
																									// 10 is a heart
																									// while type 0 is
																									// the bullet type 15 is the flying bomb wehre you shoot and it hurts you 

					double angleDeviation;
					enemy.setVelocitySet(true);
					if (type == 4)
						angleDeviation = RNG.nextDouble() * Math.toRadians(60) - Math.toRadians(30);
					else if (type == 10)
						angleDeviation = RNG.nextDouble() * Math.toRadians(30) - Math.toRadians(30);
					else {
						angleDeviation = RNG.nextDouble() * Math.toRadians(20) - Math.toRadians(10);
					}
					double newDirectionX = Math.cos(angleDeviation) * directionX
							- Math.sin(angleDeviation) * directionY;
					double newDirectionY = Math.sin(angleDeviation) * directionX
							+ Math.cos(angleDeviation) * directionY;
					newEnemy.setVelocity(newDirectionX * speed, newDirectionY * speed);

					double newAngleToPlayer = Math
							.toDegrees(Math.atan2(newDirectionY * length, newDirectionX * length));
					newEnemy.setRotate(newAngleToPlayer + 180);

					// System.out.println(" flag should be up ");
				}

				if (!enemy.isDead() && type != 4 && type != 5 && type != 10 && type != 0 && type != 15) {
					enemy.setVelocity(directionX * speed, directionY * speed);
				}
				final double LEFT_BOUNDARY = -2000;
				final double RIGHT_BOUNDARY = PLAYER_GAME_WIDTH + 2000;
				final double TOP_BOUNDARY = -2000;
				final double BOTTOM_BOUNDARY = PLAYER_GAME_HEIGHT + 2000;

				if (newEnemy.getPositionY() < LEFT_BOUNDARY || newEnemy.getPositionX() > RIGHT_BOUNDARY
						|| newEnemy.getPositionY() < TOP_BOUNDARY || newEnemy.getPositionY() > BOTTOM_BOUNDARY) {
					newEnemy.kill();
			//		System.out.println(" coin heart missle bullet" + newEnemy.getType() + " enemy " + enemy.getType());
				//	System.out.println(" enemy bullet killed");
				}
				// Update the enemy's position based on its velocity
				if( type == 12 || type == 13 || type == 14 && bossMode == true) {
					updateBossModehealth(type,enemy.getLives(),enemy.getpoisonSourcerMiniBoss13lives(),enemy.getsourcerMiniBoss14lives(),enemy.getninjaMiniBoss15lives());
				
				}
				enemy.update(elapsedTime);

				newEnemy.update(elapsedTime);

			}

		}

	}
	

	public double enemySpeed(int type) {
		int randomNumber = RNG.nextInt(10);
		double speed = 0;

		if (type == 9) {
			speed = (100 + randomNumber);// basic zombie
		} else if (type == 1) {
			speed = (70 + randomNumber); // medium zombie skeleton
		} else if (type == 2) {
			speed = (40 + randomNumber);// large lady zombie
		} else if (type == 3) {
			speed = (330 + randomNumber + randomNumber * 10); // fast mouse
		} else if (type == 4) {
			speed = (150 + randomNumber); // coin speed
		} else if (type == 5) {
			speed = (700 + randomNumber); // enemy missle
		} else if (type == 6) {
			speed = 13 + randomNumber; // big boy golem
		} else if (type == 7) {
			speed = 30 + randomNumber; // sourcer
		} else if (type == 8) {
			speed = 30 + randomNumber; // deflector enemy
		} else if (type == 10) {
			speed = 150 + randomNumber; // heart
		} else if (type == 11) {

			speed = 40 + randomNumber; // poison sourcer
		}else if (type == 12) { // poison sourcer mini boss
			speed = 115 + randomNumber;
		}else if (type == 13) { // sourcer mini boss
			speed = 100 + randomNumber;
		}else if (type == 14) { // ninja mini boss
			speed = 130 + randomNumber;
		}else if(type == 15) {
			speed = 80 + randomNumber;
		}

		return speed;

	}

	public boolean enemySpawnType(int type) {

		if (Score >= basicEnemieScore && type == 9) {
			return true; // basic enemy
		} else if (Score >= CoinsSpawnScore && (type == 4)) {
			return true; // coins enemy
		} else if (Score >= missleScore && (type == 5)) {
			return true; // missle
		} else if (Score >= mediumSkeletonScore && (type == 1)) {

			return true; // medium enemy
		} else if (Score >= largeLadyScore && (type == 2)) {

			return true; // large lady enemy
		} else if (Score >= FastRatScore && (type == 3)) {

			return true; // fast mouse
		} else if (Score >= GolemSpawnScore && (type == 6)) {
			return true;// big boy golem
		} else if (Score >= SourcerSpawnScore && type == 7) {
			return true; // sourcer
		} else if (Score >= deflectorSpawnScore && type == 8) {
			return true; // deflector enemy
		}
		// type 9 is the deflected bullets dont want that spawning only when hit on the
		// deflector zombie(type 8)
		else if (Score >= heartSpawnScore && type == 10) {
			return true; // hearts
		} else if (Score >= poisonSourcerSpawnScore && type == 11) {
			return true; // spawn poison sourcer enemy
		}else if (Score >= bossPoisonSourcerSpawnScore && type == 12) {
			return true;
		}else if(Score >= bossSourcerSpanwScore && type == 13) {
			return true;
		}else if(Score >= bossNinjaSpawnScore && type == 14) {
			return true;
		}else if(Score >= flyingBombScore && type == 15) {
			return true;
		}
		return false;

	}

	public void checkPlayerCollision() {
		for (int i = 0; i < enemies.getChildren().size(); i++) {
			enemies enemy = (enemies) enemies.getChildren().get(i);
			if (enemy.intersect(tank)) {
				if (enemy.getType() == 4) {// the coin enemy

					TotalCoins += 500;
					playSoundFX(11);
					scoreCounter();
					coinCounter(enemy.getCoins());
				}
				if (enemy.getType() == 10) { // heart
					playSoundFX(11);
					lives++;
					lives++;
					playerLives();
					scoreCounter();
				}
				if (enemy.getType() == 0 && enemy.isBomb() == true) {// poison
					playerLives();
					playerLives();
				}
				if(enemy.getType() == 15) {
					playerLives();
					playerLives();
				}
				if (enemy.getType() != 4 && enemy.getType() != 10) {
					enemy.kill();
					enemy.setVelocity(0, 0);
					// enemies.getChildren().remove(enemy);
					playSoundFX(3);
					playerLives();
				}
				if(enemy.getType() == 12 ||enemy.getType() ==  13 ||  enemy.getType() == 14) {
					playerLives();
					playerLives();
					playerLives();
					playerLives();
					playerLives();
					playerLives();
					playerLives();
					playerLives();
					playerLives();
					playerLives();
					setBossHeatlthToZero();
					
				}

				enemies.getChildren().remove(enemy);
			}

		}

	}

	public void cleanup() {
		// clean up zombies that are ready to be deleted
		for (int i = 0; i < enemies.getChildren().size(); i++) {
			enemies enemy = (enemies) enemies.getChildren().get(i);
			if (enemy.isReadyForCleanUp()) {
				enemies.getChildren().remove(enemy);
				i--;
			}
			// clean up bullets that are ready to be deleted
		}
		for (int i = 0; i < bullets.getChildren().size(); i++) {
			bullet b = (bullet) bullets.getChildren().get(i);
			if (b.isReadyForCleanUp()) {
				bullets.getChildren().remove(b);
				i--;
			}
		}
	}

	public void sourcerBulletToEnnemy(double Xcoord, double Ycoord) {
		// ACCIDENTALY MADE THE SOURCER SPAN IN ANYTYPE OF ENEMY THAT ARE AVAILABLE TO
		// THE PLAYER
		int randomNumber = RNG.nextInt(181) - 90;
		int randomNumber2 = RNG.nextInt(181) - 90;

		double PlayerXcoord = tank.getPositionX();
		double PlayerYcoord = tank.getPositionY();
		// System.out.println(" x cooordinate " + Xcoord + " y coordinate " + Ycoord + "
		// playerx coordinate " + PlayerXcoord + " player y coodinate " + PlayerYcoord);

		// spawn in the bullet enemy
		enemies newEnemy = new enemies();
		int type = newEnemy.getType();
		if (type != 7  && type != 5 &&  type != 0 && type != 12 && type != 13 && type != 14) {
			newEnemy.setPosition(Xcoord + randomNumber, Ycoord + randomNumber2);
			enemies.getChildren().add(newEnemy);
		}

	}

	public void bomerBulletToBomb(double Xcoord, double Ycoord) {
		// i beleive there is a glitch wehre enemies will randomly spawn around the
		// poison stain , look at it later

		int randomNumber = RNG.nextInt(400) - 200;
		int randomNumber2 = RNG.nextInt(400) - 200;
		// System.out.println(randomNumber);
		bomerEnemy newEnemy = new bomerEnemy();

		int type = newEnemy.getType();

		if (type == 0) {
			// System.out.println(" x cooordinate " + Xcoord + " y coordinate " + Ycoord );
			newEnemy.setPosition(Xcoord + randomNumber, Ycoord + randomNumber2);
			enemies.getChildren().add(newEnemy);
		}
		
	}

	public void bulletdeflect(double Xcoord, double Ycoord) {
		// int randNumber = RNG.nextInt(61)-30;

		BulletEnemies newEnemy = new BulletEnemies();

		int type = newEnemy.getType();
		if (type == 0 && !newEnemy.isVelocitySetBullet()) {// !newEnemy.setVelocitySetBullet()
			if (!newEnemy.isVelocitySetBullet() && !newEnemy.isVelocitySet()) {

				newEnemy.setVelocitySetBullet(true);
				newEnemy.setVelocitySet(true);

				newEnemy.setPosition(Xcoord, Ycoord);

				// set the direction the bullet will fly
				double deltaX = tank.getPositionX() - newEnemy.getEnemyX();
				double deltaY = tank.getPositionY() - newEnemy.getEnemyY();

				double length = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
				double directionX = deltaX / length;
				double directionY = deltaY / length;

				// double angleToPlayer = Math.toDegrees(Math.atan2(deltaY,deltaX));

				// make it so the bullet will usually offset a bit
				// double speed = enemySpeed(type);
				double angleDeviation = RNG.nextDouble() * Math.toRadians(80) - Math.toRadians(40);
				double newDirectionX = Math.cos(angleDeviation) * directionX - Math.sin(angleDeviation) * directionY;
				double newDirectionY = Math.sin(angleDeviation) * directionX + Math.cos(angleDeviation) * directionY;
				newEnemy.setVelocity(newDirectionX * 350, newDirectionY * 350);
				// System.out.println( newEnemiesTimer);

				double newAngleToPlayer = Math.toDegrees(Math.atan2(newDirectionY * length, newDirectionX * length));
				newEnemy.setRotate(newAngleToPlayer + 90);
			}
		}
		enemies.getChildren().add(newEnemy);

	}

	public void checkBulletCollision() {
		for (int i = 0; i < bullets.getChildren().size(); i++) {
			bullet b = (bullet) bullets.getChildren().get(i);

			for (int j = 0; j < enemies.getChildren().size(); j++) {
				enemies enemy = (enemies) enemies.getChildren().get(j);
				// simpleface.simpleface.enemies bomerEnemy = (enemies)
				// enemies.getChildren().get(j);
				int type = enemy.getEnemyType();
				if (enemy.intersect(b)) {
					double Xcoord = enemy.getEnemyX();
					double Ycoord = enemy.getEnemyY();
					if (enemy instanceof bomerEnemy) {
						// System.out.println("bomer enenmy hit ");// if it happens to be the bomb enemy
						// the bullet will simple not hit it thereby not shooting abything back e
						continue;
						// !!! fix it when you hit deflector the posion slplats spawwn again
					}
					if (enemy instanceof BulletEnemies) {
						continue;
					}
					if (enemy.isDead()) {
						continue;
					}
					if(type == 15) {
						playerLives();
						playerLives();
						playerLives();
						playSoundFX(3);
					}

					if (type == 7 || type  == 13) {
					
						playSoundFX(17);
						sourcerBulletToEnnemy(Xcoord, Ycoord);
						// sp[awn enemy sound
					}
					if (type == 8 || type == 14) {

						
						playSoundFX(16);
						bulletdeflect(Xcoord, Ycoord);
						// sword deflection sound
					}
					if (type == 11 || type == 12) {
						
						// sound splat
						playSoundFX(12);
						bomerBulletToBomb(Xcoord, Ycoord);
					}
					
					if (type != 7 && type != 8 && type != 11 && type != 9 && type != 12 && type != 13 && type != 14) {
						playSoundFX(1);
					}
					b.kill();
					enemy.hit();
					if (enemy.isDead()) {
						playSoundFX(2);
					}
					// sound
					// playSoundFX(1);
					coinCounter(enemy.getCoins());
					scoreCounter();

				}

			}
		}
	}

	public void playMusic(int i) {
		music.setFile(i);
		music.play();
		music.loop();
	}

	public void stopMusic(int i) {
		music.stop();
	}

	public void playSoundFX(int i) {
		soundFX.setFile(i);
		soundFX.play();
	}

	public void playedSound() {
		if (TotalCoins >= bulletUpgradePrice && playedBulletUpgradeSound == false) {
			playSoundFX(6);
			playedBulletUpgradeSound = true;
		} else if (TotalCoins < bulletUpgradePrice) {
			playedBulletUpgradeSound = false;
		}
		if (TotalCoins >= rocketCost && rocketUpgradeSound == false) {
			playSoundFX(8);
			rocketUpgradeSound = true;
		} else if (TotalCoins < rocketCost) {
			rocketUpgradeSound = false;
		}
		if (TotalCoins >= nukeCost && nukeUpgradeSound == false) {
			playSoundFX(9);
			nukeUpgradeSound = true;
		} else if (TotalCoins < nukeCost) {
			nukeUpgradeSound = false;
		}
	}

	public void updateBullets(double elapsedTime) {
		for (int i = 0; i < bullets.getChildren().size(); i++) {
			bullet b = (bullet) bullets.getChildren().get(i);
			b.update(elapsedTime);

			if (b.getPositionY() < -b.getHeight() || b.getPositionY() > PLAYER_GAME_HEIGHT
					|| b.getPositionX() > PLAYER_GAME_WIDTH || b.getPositionX() < -b.getWidth()) {
				// System.out.print("b. killed");
				b.kill();
				// bullets.getChildren().remove(b);
			}
		}

	}

	public void updateMissiles(double elapsedTime) {
		for (int i = 0; i < missiles.getChildren().size(); i++) {
			missile M = (missile) missiles.getChildren().get(i);
			M.update(elapsedTime);
			// coinCounter(0);

			if (M.getPositionY() < -M.getHeight() || M.getPositionY() > PLAYER_GAME_HEIGHT
					|| M.getPositionX() > PLAYER_GAME_WIDTH || M.getPositionX() < -M.getWidth()) {
				// System.out.println("M.killed");
				missiles.getChildren().remove(M);
			}
		}

	}

	public void shootBullet(MouseEvent event) {
		if (gameState == PLAYING && canSnipe == true && !isPaused()) {
			
			tank.shoot(event.getX(), event.getY());
			tank.shoot(event.getX(), event.getY());
			tank.shoot(event.getX(), event.getY());
			tank.shoot(event.getX(), event.getY());
			tank.shoot(event.getX(), event.getY());
			tank.shoot(event.getX(), event.getY());
			tank.shoot(event.getX(), event.getY());
			tank.shoot(event.getX(), event.getY());
			tank.shoot(event.getX(), event.getY());
			tank.shoot(event.getX(), event.getY());
			tank.shoot(event.getX(), event.getY());
			tank.shoot(event.getX(), event.getY());
			tank.shoot(event.getX(), event.getY());
			tank.shoot(event.getX(), event.getY());
			tank.shoot(event.getX(), event.getY());
			tank.shoot(event.getX(), event.getY());
			tank.shoot(event.getX(), event.getY());
			tank.shoot(event.getX(), event.getY());
			tank.shoot(event.getX(), event.getY());
			tank.shoot(event.getX(), event.getY());
			playSoundFX(20);
			canSnipe = false;
		} else if (gameState == PLAYING && bulletUpgradeLevel == 0 && !isPaused()) {
			tank.shoot(event.getX(), event.getY());
			playSoundFX(18);
		} else if (gameState == PLAYING && bulletUpgradeLevel == 1 && !isPaused()) {
			playSoundFX(19);
			tank.shoot(event.getX() + 10, event.getY() + 10);
			tank.shoot(event.getX() - 10, event.getY() - 10);
		} else if (gameState == PLAYING && bulletUpgradeLevel == 2 && !isPaused()) {
			playSoundFX(19);
			tank.shoot(event.getX(), event.getY());
			tank.shoot(event.getX() + 20, event.getY() + 20);
			tank.shoot(event.getX() - 20, event.getY() - 20);
		} else if (gameState == PLAYING && bulletUpgradeLevel >= 3 && !isPaused()) {
			// should be redicoulsy good and hard to get
			playSoundFX(20);
			tank.shoot(event.getX(), event.getY());
			tank.shoot(event.getX() + 20, event.getY() + 20);
			tank.shoot(event.getX() - 20, event.getY() - 20);
			tank.shoot(event.getX(), event.getY());
			tank.shoot(event.getX() + 90, event.getY() + 90);
			tank.shoot(event.getX() - 90, event.getY() - 90);

		}

	}


	public void shootMissile(MouseEvent event) {
		if (gameState == PLAYING && TotalCoins >= rocketCost && !isPaused()) {
			tank.shootMissile(event.getX(), event.getY());
			playSoundFX(14);
			TotalCoins -= rocketCost;
		}
	}

	public void updateTankDirection(double mouseX, double mouseY) {
		if (gameState == PLAYING) {
			tank.updateTankDirection(mouseX, mouseY);

		}
	}

	public void updatePlayer(double elapsedTime) {

		if (keyboard.contains(KeyCode.A)) {
			tank.driveLeft(elapsedTime);
		}
		if (keyboard.contains(KeyCode.D)) {
			tank.driveRight(elapsedTime);
		}
		if (keyboard.contains(KeyCode.W)) {
			tank.driveUp(elapsedTime);
		}
		if (keyboard.contains(KeyCode.S)) {
			tank.driveDown(elapsedTime);
		}

	}

	public void startGame() {
		if (difficultyModeisSelected == true) {

			enemies.getChildren().clear();
			bullets.getChildren().clear();
			missiles.getChildren().clear();
			if (difficutlyType == 0) {
				heart.setOpacity(1);
				heart2.setOpacity(1);
				heart3.setOpacity(1);
				heart4.setOpacity(1);
				heart5.setOpacity(1);
				heart6.setOpacity(1);
				heart7.setOpacity(1);
				heart8.setOpacity(1);
				heart9.setOpacity(1);
				heart10.setOpacity(1);
				lives = 10;
			} else {
				heart.setOpacity(1);
				heart2.setOpacity(1);
				heart3.setOpacity(1);
				heart4.setOpacity(1);
				heart5.setOpacity(1);
				heart6.setOpacity(1);
				heart7.setOpacity(0);
				heart8.setOpacity(0);
				heart9.setOpacity(0);
				heart10.setOpacity(0);
				heart11.setOpacity(0);
				heart12.setOpacity(0);
				heart13.setOpacity(0);
				heart14.setOpacity(0);
				heart15.setOpacity(0);
				lives = 6;
			}

			// TotalCoins =0;
			tank.revive();
			gameTimer.start();
			gameState = PLAYING;

			titleScreen.setVisible(false);
			menu.setVisible(false);
			EnemyProgression.setVisible(false);

		//	System.out.println(" music started");
			playMusic(0);
			music.play();

		}

	}

	public void upgradeScreen() {
		gameState = UPGRADE_SCREEN;

	}

	public void easyMode() {
		// int PlayerScreenWidth = GAME_WIDTH/PLAYER_GAME_WIDTH;
		// int PlayerScreenHeight = GAME_HEIGHT/PLAYER_GAME_HEIGHT;
		difficultyModeisSelected = true;
		difficutlyType = 0;
		Score = 0;
		difficultyCurve = -1.0;
		TotalCoins = 1000;

		// set opacity to abilities since there will be enough coins at the start
		ultraShot.setOpacity(1);
		ultraShotText.setOpacity(1);
		nuke.setOpacity(1);
		BombHint.setOpacity(1);
		playSoundFX(17);
		// set the font when you click on the difficulty
		gameDifficultyText.setText(" Easy ");
		gameDifficultyText.setFont(Font.font("Arial", 100));
		gameDifficultyText.relocate(
				PLAYER_GAME_WIDTH / 2 - title.getBoundsInParent().getWidth() / 2 + PlayerScreenWidth * 150,
				scale* 150);
		gameDifficultyText.setFill(Color.YELLOW);
		gameDifficultyText.setStroke(Color.ROYALBLUE);
		gameDifficultyText.setStrokeWidth(3);
		gameDifficultyText.setEffect(new DropShadow());
		gameDifficultyText.setOpacity(1);

		gameColor = Color.ROYALBLUE;
		gameScene.setFill(gameColor);

		System.out.println("easy mode " + difficultyCurve + " difficulty type" + difficutlyType);

	}

	public void normalMode() {
		// int PlayerScreenWidth = GAME_WIDTH/PLAYER_GAME_WIDTH;
		// int PlayerScreenHeight = GAME_HEIGHT/PLAYER_GAME_HEIGHT;
		difficultyModeisSelected = true;

		ultraShot.setOpacity(1);
		ultraShotText.setOpacity(1);
		nuke.setOpacity(0);
		BombHint.setOpacity(0);
		Score = 0;

		TotalCoins = 300;
		difficutlyType = 1;
		difficultyCurve = .15;// change to 0.1
		gameDifficultyText.setFont(Font.font("Arial", 100));
		gameDifficultyText.relocate(
				PLAYER_GAME_WIDTH / 2 - title.getBoundsInParent().getWidth() / 2 + PlayerScreenWidth * 70,
				scale * 150);
		gameDifficultyText.setFill(Color.LIGHTGREEN);
		gameDifficultyText.setStroke(Color.ROYALBLUE);
		gameDifficultyText.setStrokeWidth(3);
		gameDifficultyText.setEffect(new DropShadow());
		gameDifficultyText.setText(" VeryEasy ");
		gameDifficultyText.setOpacity(1);

		gameColor = Color.DARKGREEN;
		gameScene.setFill(gameColor);
		playSoundFX(17);
		System.out.println("normal  mode " + difficultyCurve + " difficulty type" + difficutlyType);
	}

	public void hardMode() {
		// int PlayerScreenWidth = GAME_WIDTH/PLAYER_GAME_WIDTH;
		// int PlayerScreenHeight = GAME_HEIGHT/PLAYER_GAME_HEIGHT;
		difficultyModeisSelected = true;

		ultraShot.setOpacity(0);
		ultraShotText.setOpacity(0);
		nuke.setOpacity(0);
		BombHint.setOpacity(0);
		Score = 400;

		TotalCoins = 0;
		difficutlyType = 2;
		difficultyCurve = 0.25;
		gameDifficultyText.setText(" OUTRAGEOUSLY EASY! ");
		gameDifficultyText.setFont(Font.font("Arial", 125));
		gameDifficultyText.relocate(
				PLAYER_GAME_WIDTH / 6 - title.getBoundsInParent().getWidth() / 4 + PlayerScreenWidth * 50,
				scale * 150);
		gameDifficultyText.setFill(Color.RED);
		gameDifficultyText.setStroke(Color.BLACK);
		gameDifficultyText.setStrokeWidth(3);
		gameDifficultyText.setEffect(new DropShadow());
		gameDifficultyText.setOpacity(1);

		gameColor = Color.INDIANRED;
		gameScene.setFill(gameColor);
		// primaryStage.setScene(gameScene);
		// primaryStage.show();
		playSoundFX(17);
		System.out.println(" hard mode " + difficultyCurve + " difficulty type" + difficutlyType);
	}

	public void ultraHardMode() {
//	int PlayerScreenWidth = GAME_WIDTH/PLAYER_GAME_WIDTH;
		// if(highScore >= enableImpossibleMode) {
		difficultyModeisSelected = true;
		difficutlyType = 3;
		difficultyCurve = 0.4;
		gameDifficultyText.setText(" IMPOSSIBLE ");
		gameDifficultyText.setFont(Font.font("Arial", 125 * scale));
		gameDifficultyText.relocate(
				PLAYER_GAME_WIDTH / 6 - title.getBoundsInParent().getWidth() / 4 + PlayerScreenWidth * 370,
				scale * 150);
		gameDifficultyText.setFill(Color.BLACK);
		gameDifficultyText.setStroke(Color.RED);
		gameDifficultyText.setStrokeWidth(3);
		gameDifficultyText.setEffect(new DropShadow());
		gameDifficultyText.setOpacity(1);
		Score = 1000;
		TotalCoins = -1000;
		// gameBackground.setStyle("-fx-background-color: yellow;")
		gameColor = Color.BLACK;
		gameScene.setFill(gameColor);
		// primaryStage.show();
		playSoundFX(17);
	//	System.out.println(" ultra mode " + difficultyCurve);
		// }
	}

	public void gameOver() {
		scoreChallange();
		highscore.setFont(Font.font("Arial", 20 * scale));
		difficultyModeisSelected = false;
		difficultyCurve = 0;
		Score = 0;
		TotalCoins = 0;
		bulletUpgradeLevel = 0;
		newHighScore = false;
		gameState = TITLE_SCREEN;
		bossMode = false;
		setBossHeatlthToZero();
		

		titleScreen.setVisible(true);
		gameTimer.stop();
		music.stop();

		title.setText("Operation Z-Lost");
		tank.setPosition(PLAYER_GAME_WIDTH / 2 - tank.getWidth() / 2, PLAYER_GAME_HEIGHT / 2);

		if (highScore >= enableImpossibleMode) {
			impossiblyNormal.setVisible(true);
		}
		// tank.setPosition(PLAYER_GAME_WIDTH / 2 - tank.getWidth() / 2,
		// PLAYER_GAME_HEIGHT / 2);
	}
	public void setBossHeatlthToZero() {
		healthBar1.setOpacity(0);
		healthBar2.setOpacity(0);
		healthBar3.setOpacity(0);
		healthBar4.setOpacity(0);
		healthBar5.setOpacity(0);
		healthBar6.setOpacity(0);
		healthBar7.setOpacity(0);
		healthBar8.setOpacity(0);
		healthBar9.setOpacity(0);
		healthBar10.setOpacity(0);
		healthBar12.setOpacity(0);
		healthBar13.setOpacity(0);
		healthBar14.setOpacity(0);
		healthBar15.setOpacity(0);
		healthBar16.setOpacity(0);
		healthBar17.setOpacity(0);
		healthBar18.setOpacity(0);
		healthBar19.setOpacity(0);
		healthBar20.setOpacity(0);
		healthBar21.setOpacity(0);
		healthBar22.setOpacity(0);
		healthBar23.setOpacity(0);
		healthBar24.setOpacity(0);
		healthBar25.setOpacity(0);
		healthBar26.setOpacity(0);
		healthBar27.setOpacity(0);
		healthBar28.setOpacity(0);
		healthBar29.setOpacity(0);
		healthBar30.setOpacity(0);
		healthBar31.setOpacity(0);
		healthBar32.setOpacity(0);
		FullhealthBarImg.setOpacity(0);
		
	}

	public boolean isPaused() {

		return previousTime == -1;

	}

	public void scoreChallange() {
		newScoreChallange = highScore + 5000;
		newScoreChallange = (int) (Math.ceil(newScoreChallange / 1000.0) * 1000);
		ScoreChallangePrompt.setOpacity(1);
		ScoreChallangePrompt.setText("Good job soldier, but can you get to " + newScoreChallange + "?");
	}

	public void pauseGame() {
		if (isPaused()) {
			gameTimer.start();
		} else {
			gameTimer.stop();

		}
	}

	public void nuke() {
		for (int i = 0; i < enemies.getChildren().size(); i++) {
			enemies enemy = (enemies) enemies.getChildren().get(i);
			if(enemy.getType() != 12 && enemy.getType() != 13 && enemy.getType() != 14) {
				enemy.kill();
				enemy.setVelocity(0,0);
				coinCounter(enemy.getCoins());
			}
		//	enemy.kill();
		//	enemy.setVelocity(0, 0);
		//	coinCounter(enemy.getCoins());
		}

	}

	
	public void scoreCounter() {
		Score += 10;

		score.setText("Score: " + Score);
		if (Score > highScore && newHighScore == false) {
			playSoundFX(7);
			newHighScore = true;
			highscore.setFont(Font.font("Arial", 25* scale));
		}
		if (Score > highScore) {
			highScore = Score;
			highscore.setText("High Score: " + highScore);
		}
	}

	public void coinCounter(int coins) {
		// coins were great but each enemy is givven a rnom form 1-10 or whaterver and
		// that wont change each bullet hit , but it will cahnge if it s another enemy
		// even if ti is the same type whcih is ok
		TotalCoins += coins;
		coinText.setText("X " + TotalCoins);

		if (TotalCoins >= bulletUpgradePrice) {

			nuke.setOpacity(1);
			BombHint.setOpacity(1);
			ultraShot.setOpacity(1);
			ultraShotText.setOpacity(1);
			bulletTextImage.setOpacity(1);
			bulletImage.setOpacity(1);
			bulletUpgrade.setOpacity(1);

		} else if (TotalCoins >= nukeCost) {

			nuke.setOpacity(1);
			BombHint.setOpacity(1);
			ultraShot.setOpacity(1);
			ultraShotText.setOpacity(1);
			bulletTextImage.setOpacity(0);
			bulletImage.setOpacity(0);
			bulletUpgrade.setOpacity(0);

		} else if (TotalCoins >= rocketCost) {

			ultraShot.setOpacity(1);
			ultraShotText.setOpacity(1);
			nuke.setOpacity(0);
			BombHint.setOpacity(0);
			bulletImage.setOpacity(0);
			bulletUpgrade.setOpacity(0);
			bulletTextImage.setOpacity(0);
		} else {
			nuke.setOpacity(0);
			BombHint.setOpacity(0);
			ultraShot.setOpacity(0);
			ultraShotText.setOpacity(0);
			bulletImage.setOpacity(0);
			bulletUpgrade.setOpacity(0);
			bulletTextImage.setOpacity(0);
		}

	}

	public void ultraShot() {
		// playSoundFX(14);
		for (int i = 0; i < missiles.getChildren().size(); i++) {
			missile M = (missile) missiles.getChildren().get(i);
			// playSoundFX(14);
			for (int j = 0; j < enemies.getChildren().size(); j++) {
				enemies enemy = (enemies) enemies.getChildren().get(j);

				if (enemy.intersect(M)) {
					if (enemy.isDead()) {
						continue;
					}

					if (enemy.getType() == 6) {
						enemy.hit();
						// playSoundFX(1);
						continue;
					}
					if(enemy.getType() == 7) {
						enemy.hit();
					continue;
					}
					if(enemy.getType() == 12){
						enemy.hit();
						continue;
					}
					if(enemy.getType() == 13) {
						enemy.hit();
						continue;
					}
					if(enemy.getType() == 14) {
						enemy.hit();
						continue;
					}
					

					enemy.kill();
					enemy.setVelocity(0, 0);
					scoreCounter();
					coinCounter(enemy.getCoins());

				}

			}

		}

	}

	public void playerLives() {
		lives--;
		if (lives == 15) {
			heart15.setOpacity(1);
		}
		if (lives == 14) {
			heart15.setOpacity(0);
			heart14.setOpacity(1);
		}
		if (lives == 13) {
			heart14.setOpacity(0);
			heart13.setOpacity(1);
		}
		if (lives == 12) {
			heart13.setOpacity(0);
			heart12.setOpacity(1);
		}
		if (lives == 11) {
			heart12.setOpacity(0);
			heart11.setOpacity(1);
		}
		if (lives == 10) {
			heart11.setOpacity(0);
			heart10.setOpacity(1);
		}
		if (lives == 9) {
			heart10.setOpacity(0);
			heart9.setOpacity(1);
		}
		if (lives == 8) {
			heart9.setOpacity(0);
			heart8.setOpacity(1);
		}
		if (lives == 7) {
			heart8.setOpacity(0);
			heart7.setOpacity(1);
		}
		if (lives == 6) {
			heart7.setOpacity(0);
			heart6.setOpacity(1);
		}
		if (lives == 5) {
			heart6.setOpacity(0);
			heart5.setOpacity(1);
		}
		if (lives == 4) {
			heart5.setOpacity(0);
			heart4.setOpacity(1);
		}
		if (lives == 3) {
			heart4.setOpacity(0);
			heart3.setOpacity(1);
		}
		if (lives == 2) {
			heart3.setOpacity(0);
			heart2.setOpacity(1);
		}
		if (lives == 1) {
			heart2.setOpacity(0);
		}
		if (lives == 0) {
			heart.setOpacity(0);
			tank.kill();
			enemies.getChildren().clear();
			playSoundFX(4);
			// playSoundFX(4);
			// playSoundFX(10);
			gameOver();

		}

	}

	public static void main(String[] args) {
		launch();
	}

}