package simpleface.simpleface;

import java.util.HashSet;
import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import java.lang.Math;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 * JavaFX App
 */
public interface GameTick {
	
	/**
	 * Code to be executed for each tick of game time
	 * 
	 * @param elapsedTime amount of time that has passed since the previous game tick in seconds
	 */
	public void gameTick(double elapsedTime);
}
