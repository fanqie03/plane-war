package plane;

import javafx.scene.image.Image;
import javafx.scene.media.MediaPlayer;
import util.Constent;
import util.Frame;

public class Plane extends Frame{
	private int HP = 1;

	
	public Plane(Image image, double x, double y, double speed){
		super(image,x,y,speed);
		setWidth(50);
		setHeight(50);
	}
	
	public Plane(String path, double x, double y, double speed){
		super(path, x, y, speed);
	}
	/**
	 * 飞机是否活着
	 * @return HP
	 */
	public boolean isLive(){
		return HP>0?true:false;
	}
	
	/**
	 * 创建子弹
	 */
	public void play(){
				Bullet bullet = new Bullet(this, Constent.BULLET, 0, 1);
				bullet.play(GameFrame.pane);
				GameFrame.planeBullet.add(bullet);
				MediaPlayer mediaPlayer = new MediaPlayer(Constent._fireBullet);
				mediaPlayer.play();
	}
	/**
	 * 减少飞机生命值
	 */
	public void deducateHP(){
		if(HP>0)
			HP--;
	}
	
	public int getHP(){
		return HP;
	}
	public void setCenterX(double x){
		setX(-getWidth()/2+x);
	}
	
	public void setCenterY(double y){
		setY(-getHeight()/2+y);
	}
}
