package plane;

import javafx.animation.PathTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import util.Frame;

public class Bullet extends Frame{
	Frame center;
	double degree;
	Line line;
	PathTransition pathTransition;
	private boolean finish = false;
	
	public Bullet(ImageView center, Image image, double degree, double speed) {
		super(image, -20, -20, speed);
		this.degree = degree;
		double Radians = Math.toRadians(degree-90);
		getImageView().setRotate(Radians);
		line = new Line(getX(),getY(),getX()+Math.cos(Radians)*400,getY()+Math.sin(Radians)*400);
		pathTransition = new PathTransition();
		pathTransition.setPath(line);
		pathTransition.setNode(getImageView());
		pathTransition.setDuration(Duration.millis(4000));
		pathTransition.setOnFinished(e -> {
			GameFrame.pane.getChildren().remove(getImageView());
			finish = true;
		});
	}
	
	Bullet(Frame center, Image image, double degree, double speedSecond){
		super(image, -20, -20, speedSecond);
		this.center = center;
		this.degree = degree;
		double centerX = center.getX()+center.getWidth()/2;
		double centerY = center.getY() - 20;
		double Radians = Math.toRadians(degree-90);
		getImageView().setRotate(degree);
		line = new Line(centerX, centerY, centerX+Math.cos(Radians)*500, centerY+Math.sin(Radians)*500);
		pathTransition = new PathTransition();
		pathTransition.setDuration(Duration.millis(speedSecond*1000));
		pathTransition.setPath(line);
		pathTransition.setNode(getImageView());
		pathTransition.setOnFinished(e -> {
			GameFrame.pane.getChildren().remove(getImageView());
			finish = true;
		});
	}
	double centerX;
	double centerY;
	Bullet(Frame center, Image image, double degree, double speedSecond,PathTransition pathTransition){
		super(image, -20, -20, speedSecond);
		centerX = center.getWidth()/2 + center.getX();
		centerY = center.getHeight() + pathTransition.getNode().getTranslateY();
		this.center = center;
		this.degree = degree;
		double Radians = Math.toRadians(degree-90);
		getImageView().setRotate(degree);
		line = new Line(centerX, centerY, centerX+Math.cos(Radians)*500, centerY+Math.sin(Radians)*500);
		this.pathTransition = new PathTransition();
		this.pathTransition.setDuration(Duration.millis(speedSecond*1000));
		this.pathTransition.setPath(line);
		this.pathTransition.setNode(getImageView());
		this.pathTransition.setOnFinished(e -> {
			GameFrame.pane.getChildren().remove(getImageView());
			finish = true;
		});
		

	}
	/**
	 * 子弹飞行动画停止
	 */
	public void stop(){
		pathTransition.stop();
	}
	/**
	 * 子弹是否飞行完毕
	 * @return finish
	 */
	public boolean isFinish(){
		return finish;
	}
	/**
	 * 开始飞行动画，需要画布参数
	 * @param pane 画布
	 */
	public void play(Pane pane){
		pane.getChildren().add(getImageView());
		this.pathTransition.play();
	}
	
	public PathTransition getPathtransition(){
		return pathTransition;
	}
	
	public double getPosX(){
		return getX();
	}
	public double getPosY(){
		return pathTransition.getNode().getTranslateY();
	}
}
