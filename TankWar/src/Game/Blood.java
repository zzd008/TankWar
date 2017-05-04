package Game;

import java.awt.*;
import java.util.Random;
/**
 * 血类 我方坦克吃了加血
 * @author zzd
 *
 */
public class Blood {
	/**
	 * 血的尺寸
	 */
	public static final int width = 36;
	public static final int length = 36;
	/**
	 * 血的位置
	 */
	private int x, y;
	TankClient tc;
	private static Random r = new Random();

	int step = 0; 
	private boolean live = true;
	/**
	 * 贴图 血
	 */
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] bloodImags = null;
	static {
		bloodImags = new Image[] { 
				tk.getImage("hp.png"), 
				};
	}
	/**
	 * 血块随机出现的位置
	 */
	private int[][] poition = { {100,220},{700,260} ,{100,520},{560,560},{300,560},{380,220},{400,70},};
	/**
	 * 画笔
	 * @param g
	 */
	public void draw(Graphics g) {
		if(this.live){
			if (r.nextInt(100) > 98) {
				this.live = true;
				move();
			}
		}
		else
			return;
		g.drawImage(bloodImags[0], x, y, null);

	}
	/**
	 * 移动函数
	 */
	private void move() {
		step++;
		if (step == poition.length) {
			step = 0;
		}
		x = poition[step][0];
		y = poition[step][1];
		
	}

	public Rectangle getRect() { //返回长方形范围
		return new Rectangle(x, y, width, length);
	}

	public boolean isLive() {//判断是否还活着
		return live;
	}

	public void setLive(boolean live) {  //设置生命
		this.live = live;
	}

}