package plane;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * 爆炸类
 * @author Administrator
 *
 */
public class Explosion {
	
	public static Image image[] = {
			new Image("image/bigExplosion.gif"),
			new Image("image/explosion.gif")
	};
	/**
	 * 在（x,y）处发生爆炸
	 * @param x 坐标x
	 * @param y 坐标y
	 * @param i 爆炸类型
	 */
	public static void play(double x,double y,int i){
		ImageView imageView = new ImageView(image[i]);
		imageView.setX(x);
		imageView.setY(y);
		GameFrame.pane.getChildren().add(imageView);
	}
	
}
