package Game;

import java.awt.*;
/**
 * 金属墙类
 * @author zzd
 *
 */
public class Wall2 {
	/**
	 * 墙的尺寸
	 */
	public static final int width = 30; 
	public static final int length = 30;
	int x, y;

	TankClient tc;
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] wallImags = null;
	static {
		wallImags = new Image[] { // 储存金属Wall的图片
		tk.getImage("Wall2.gif"), };
	}

	public Wall2(int x, int y, TankClient tc) { // 构造函数
		this.x = x;
		this.y = y;
		this.tc = tc; 
	}
	//画笔
	public void draw(Graphics g) {
		g.drawImage(wallImags[0], x, y, null);
	}

	public Rectangle getRect() {  
		return new Rectangle(x, y, width, length);
	}
}

