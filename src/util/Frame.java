package util;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * 飞机战争框架，由飞机，子弹，敌机继承
 * @author Administrator
 *
 */
public class Frame {
	private double speed;
	private ImageView imageView;
	
	
	public Frame(Image image, double x, double y, double speed) {
		this.imageView = new ImageView(image);
		this.speed = speed;
		this.imageView.setX(x);
		this.imageView.setY(y);
	}
	public Frame(String path, double x, double y, double speed) {
		this(new Image(path), x, y, speed);
	}
	
	public double getX() {
		return imageView.getX();
	}
	public void setX(double x) {
		this.imageView.setX(x);
	}

	public double getY() {
		return imageView.getY();
	}

	public void setY(double y) {
		this.imageView.setY(y);
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public ImageView getImageView() {
		return imageView;
	}

	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}
	
	public double getWidth(){
		return imageView.getFitWidth();
	}
	
	public void setWidth(double width){
		imageView.setFitWidth(width);
	}
	
	public double getHeight(){
		return imageView.getFitHeight();
	}
	
	public void setHeight(double height){
		imageView.setFitHeight(height);
	}
	
}
