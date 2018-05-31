package util;

import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;

public class Constent {
	public static final int HEIGHT = 500;
	public static final int WIDTH = 400;
	public static int SPEED = 5;
	
	public static Image PLANE = new Image("image/plane.png"); 
	public static Image PLANE1 = new Image("image/plane1.png");
	public static Image PLANE2 = new Image("image/plane2.png");
	public static Image BULLET = new Image("image/bullet.png");
	public static Image ENERMY[] = { 
			new Image("image/emermy0.png"),
			new Image("image/emermy1.png"),
			new Image("image/emermy2.png"),
			new Image("image/emermy3.png"),
			new Image("image/emermy4.png")};
	
	public static String mediaPath = new File("").toURI().toString()+"src/sound/";
	
	public static Media _gameMusic = new Media(mediaPath+"game_music.wav");
	public static Media _fireBullet = new Media(mediaPath+"fire_bullet.wav");
	public static Media _gameOver = new Media(mediaPath + "game_over.wav");
	public static Media _smallPlaneKilled = new Media(mediaPath+"small_plane_killed.wav");
	public static Media _useBomb = new Media(mediaPath+"use_bomb.wav");
	
	public static MediaPlayer gameMusic = new MediaPlayer(_gameMusic);
	public static MediaPlayer fireBullet = new MediaPlayer(_fireBullet);
	public static MediaPlayer gameOver = new MediaPlayer(_gameOver);
	public static MediaPlayer smallPlaneKilled = new MediaPlayer(_smallPlaneKilled);
	public static MediaPlayer useBomb = new MediaPlayer(_useBomb);
	
	
	
	
	public static Ellipse ellipse = new Ellipse(WIDTH/2, HEIGHT/2, WIDTH/3, WIDTH/3);
	public static Line planeLine = new Line();

	/**
	 * 在游戏窗口画动作线
	 * @return 线
	 */
	
	public static Line line(){
		double r = Math.random();
		return new Line(r*WIDTH,0,r*WIDTH,HEIGHT+40);
	}
	
	/**
	 * 游戏背景音乐的实现
	 */
	
	public static void backgroungVoice() {

		Media media = new Media(new File("").toURI().toString()+"src/sound/game_music.wav");
		
		MediaPlayer gameMusic = new MediaPlayer(media);
		gameMusic.setCycleCount(Integer.MAX_VALUE);
		gameMusic.play();
	}
}
