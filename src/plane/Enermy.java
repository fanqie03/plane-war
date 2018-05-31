package plane;

import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import util.Constent;
import util.Frame;

public class Enermy extends Frame{
	private PathTransition pathTransition = new PathTransition();
	private Timeline shoot;
	private boolean finish = false;
	
	
	public Enermy(Image image, double speed){
		super(image, Math.random()*Constent.WIDTH, -60, speed);
		pathMove();
	}
	
	public Enermy(Image image, double starX, double starY, double speed) {
		super(image, starX, starY, speed);
		pathMove();
	}


	/**
	 * µĐ»úÉä»÷×°ÖĂ
	 * @param pane »­˛Ľ
	 * @param enermy µĐ»ú
	 */
	public void initShoot(Pane pane, Enermy enermy){
		EventHandler<ActionEvent> eventHandler = e -> {
			Bullet bullet = new Bullet(enermy, Constent.BULLET, 180, 2, pathTransition);
			bullet.play(pane);
			GameFrame.enermyBullets.add(bullet);
		};
		shoot = new Timeline(
				new KeyFrame(Duration.millis(1500), eventHandler)
				);
		shoot.setCycleCount(Timeline.INDEFINITE);
		shoot.play();
	}
	
	/**
	 * ÍŁÖąÉä»÷
	 */
	public void shootStop(){
		shoot.stop();
	}
	/**
	 * ÍŁÖą·É»ú»î¶Ż
	 */
	public void stopActivity(){
		shootStop();
		pathTransition.stop();
	}
	/**
	 * µĐ»úŇĆ¶ŻÂ·Ďß
	 */
	public void pathMove(){
		Line line = new Line(getImageView().getX(),getImageView().getY(),
				getImageView().getX(),getImageView().getY()+1.5*Constent.WIDTH);
		
		pathTransition = new PathTransition();
		pathTransition.setNode(getImageView());
		pathTransition.setPath(line);
		pathTransition.setDuration(Duration.millis(getSpeed()*1000));
		pathTransition.play();
		pathTransition.setOnFinished(e -> {
			GameFrame.pane.getChildren().remove(getImageView());
			shoot.pause();
			finish = true;
		});
	}
	/**
	 * ĘÇ·ń·ÉĐĐ˝áĘř
	 * @return finish
	 */
	public boolean isFinish(){
		return finish;
	}
	public PathTransition getPathtansition(){
		return pathTransition;
	}

	public double getPosX(){
		return getX();
	}
	
	public double getPosY(){
		return pathTransition.getNode().getTranslateY();
	}
	
}
