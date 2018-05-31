package plane;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import util.Constent;
import util.MyLinkedList;

/**
 * 游戏入口
 * @author Administrator
 *
 */
public class GameFrame extends Application{
	public static Text text = new Text();
	public static Integer score = 0;
	public static int dScore = 100;
	public static Pane pane = new Pane();
//	public static ArrayList<Bullet> enermyBullets = new ArrayList<>();
//	public static ArrayList<Enermy> enermys = new ArrayList<>();
//	public static ArrayList<Bullet> planeBullet = new ArrayList<>();
	
	public static MyLinkedList<Bullet> enermyBullets = new MyLinkedList<Bullet>();
	public static MyLinkedList<Enermy> enermys = new MyLinkedList<Enermy>();
	public static MyLinkedList<Bullet> planeBullet = new MyLinkedList<Bullet>();
	
	public Timeline planeShoot;
	public Timeline produceEnermy;
	public Timeline check;
	Plane plane = new Plane(Constent.PLANE,
			Constent.WIDTH/2-35,Constent.HEIGHT-65,Constent.SPEED);
	
	public static int fa=0;
	public static Stage stage = new Stage();
	
	
	@Override
	public void start(Stage primaryStage) {
		newStage();
	}
	
	/**
	 * 创建游戏舞台
	 */
	public void newStage(){
		Scene scene = new Scene(gamePane(),Constent.WIDTH,Constent.HEIGHT);
		stage.close();
		stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Plane war");
		stage.show();
	}
	/**
	 * 创建游戏画布
	 * @return 画布
	 */
	
	public Pane gamePane(){
		pane.getChildren().clear();
		pane = new Pane();
		ImageView bg = new ImageView("image/bg0.jpg");
				
		//飞机移动
		pane.setOnMouseMoved(e -> {
			if(plane.isLive()){
				plane.setCenterX(e.getX());
				plane.setCenterY(e.getY());
			}
		});
		
		//产生敌人
		produceEnermy();
		
		//游戏背景音乐
		Constent.gameMusic.setVolume(150);
		Constent.gameMusic.setCycleCount(Integer.MAX_VALUE);
		Constent.gameMusic.play();
		
		pane.getChildren().addAll(bg,plane.getImageView());
		

		//检测
		check = new Timeline(new KeyFrame(Duration.millis(100), e -> check()));
		check.setCycleCount(Timeline.INDEFINITE);
		check.play();
		
		
		
		//分数栏
		scoreText();
		//飞机射击
		planeShoot();
		return pane;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	/**
	 * 飞机自动射击
	 */
	public void planeShoot(){
		planeShoot = new Timeline(new KeyFrame(Duration.millis(200), e ->plane.play()));
		planeShoot.setCycleCount(Timeline.INDEFINITE);
		planeShoot.play();
	}
	/**
	 * 提高飞机射击频率
	 */
	public void increasePlaneShoot(){
		planeShoot.setRate(planeShoot.getRate()+0.01);
	}
	/**
	 * 停止飞机射击
	 */
	public void stopPlaneShoot(){
		planeShoot.stop();
	}
	/**
	 * 分数窗口
	 */
	public void scoreText(){
		text.setText("分数："+score.toString());
		text.setX(1);
		text.setY(20);
		Font font = Font.font("Times iNew Roman", FontWeight.BOLD, FontPosture.REGULAR, 20);
		text.setFont(font);
		text.setFill(Color.RED);
		pane.getChildren().add(text);
	}
	
	/**
	 * 自动产生敌机
	 */
	
	public void produceEnermy(){
		EventHandler<ActionEvent> eventHandler = e -> {
			Enermy enermy = new Enermy(Constent.ENERMY[0],5);
			enermy.initShoot(pane, enermy);
			pane.getChildren().add(enermy.getImageView());
			enermys.add(enermy);
		};
		
		
		produceEnermy = new Timeline(
				new KeyFrame(Duration.millis(200), eventHandler)
				);
		
		produceEnermy.setCycleCount(Timeline.INDEFINITE);
		produceEnermy.play();
		
		
	}
	/**
	 * 通过增加敌机产生频率来增加难度
	 */
	
	public void increaseHard(){
		produceEnermy.setRate(produceEnermy.getRate()+0.01);
	}
	/**
	 * 敌机停止产生
	 */
	public void stopProduceEnermy(){
		produceEnermy.stop();
	}
	/**
	 * 游戏检测函数，检测碰撞。
	 */
	
	public void check(){
		boolean flag;
		for(int i = 0; i < planeBullet.size(); i++){
			for(int j = 0; j < enermys.size();j++){
				flag = false;
				if(intersects(planeBullet.get(i), (enermys.get(j)))){
					
					score += dScore;
					text.setText("分数："+score.toString());
					increaseHard();
					fa++;
					if(fa==5){
						increasePlaneShoot();
						fa=0;
					}
					new MediaPlayer(Constent._smallPlaneKilled).play();
					
					pane.getChildren().remove(planeBullet.get(i).getImageView());
					pane.getChildren().remove(enermys.get(j).getImageView());
					
					enermys.get(j).shootStop();
					planeBullet.remove(i);
					enermys.remove(j);
					flag = true;
					break;
				}
				if(flag) break;
			}
		}
		
		for(int i = 0; i < enermyBullets.size(); i++){
			
			if(intersects(enermyBullets.get(i), plane)){
				
				plane.deducateHP();
				if(!plane.isLive()){
					new MediaPlayer(Constent._useBomb).play();
					check.stop();
					Explosion.play(plane.getX(), plane.getY(),0);
					gameOver();
					break;
				}
				
				pane.getChildren().remove(enermyBullets.get(i).getImageView());
				
				enermyBullets.remove(i);
				
			}
		}
		
		for(int i =0; i < planeBullet.size(); i++)
			if(planeBullet.get(i).isFinish())
				planeBullet.remove(i);
		for(int i = 0; i < enermyBullets.size(); i++)
			if(enermyBullets.get(i).isFinish())
				enermyBullets.remove(i);
		for(int i = 0; i < enermys.size(); i++)
			if(enermys.get(i).isFinish())
				enermys.remove(i);
	}
	
	/**
	 * 游戏结束函数
	 */
	
	public void gameOver(){
		
		//去掉子弹
		for(int i = 0; i < enermyBullets.size(); i++){
			enermyBullets.get(i).stop();
			pane.getChildren().remove(enermyBullets.get(i).getImageView());
		}
		enermyBullets.clear();
		for(int i = 0; i < planeBullet.size(); i++){
			planeBullet.get(i).stop();
			pane.getChildren().remove(planeBullet.get(i).getImageView());
		}
		planeBullet.clear();
		
		//停止显示敌机
		stopProduceEnermy();
		
		//去掉飞机
		pane.getChildren().remove(plane.getImageView());
		stopPlaneShoot();

		showScore();
		
	}
	
	/**
	 * 结束后的总结束
	 */
	public void showScore(){
		String string[] = {"见习飞行师","中级飞行师","高级飞行师","神级飞行师","超神级飞行师"	};
		int level=0;
		if(score<=2000)  level=0;
		else if(score<=5000)  level=1;
		else if(score<=10000) level=2;
		else if(score<=20000) level=3;
		else if(score>=50000) level=4;
		
		Text text1 = new Text(150,200,"游戏结束");
		Text text2 = new Text(145,230,"得分："+score);
		Text text3 = new Text(140,260,string[level]);
		
		text1.setFill(Color.RED);
		text2.setFill(Color.WHITE);
		text3.setFill(Color.YELLOW);
		
		
		Font font = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 22);
		text1.setFont(font);
		text2.setFont(font);
		text3.setFont(font);
		
		pane.getChildren().addAll(text1,text2,text3);
	}
	
	/**
	 * 检测碰撞：子弹与飞机的检测
	 * @param bullet 子弹
	 * @param plane 飞机
	 * @return 碰撞值
	 */
	public boolean intersects(Bullet bullet, Plane plane){
		
		if(bullet.getPathtransition().getNode().getBoundsInParent().intersects(plane.getImageView().getBoundsInParent()))
			return true;
		return false;
		

	}
	/**
	 * 检测碰撞：子弹与敌机的检测
	 * @param bullet 子弹
	 * @param enermy 敌机
	 * @return 碰撞值
	 */
	public boolean intersects(Bullet bullet, Enermy enermy){

		if(bullet.getPathtransition().getNode().getBoundsInParent().intersects(enermy.getPathtansition().getNode().getBoundsInParent()))
			return true;
		return false;

	}
}
