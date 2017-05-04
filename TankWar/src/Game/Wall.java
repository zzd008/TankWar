package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
/**
 *墙体类
 * @author 周致达
 *该类为游戏中的墙体
 */
public class Wall {
	/**
	 * 共有墙的图片
	 */
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] wallImags = null;
	static {
		wallImags = new Image[] { 
				tk.getImage("wall1.gif"),
			};
	}
	/**
	 * 墙的位置 长 宽
	 */
	int x, y;
	public static final int width = 20; 
	public static final int height = 20;
	/**
	 * 保留TankClient的引用，方便使用其中的成员变量
	 */
	TankClient tc ;
	/**
	 * 构造函数
	 */
	public Wall(int x, int y, TankClient tc) {
		this.x = x;
		this.y = y;
		this.tc = tc;
	}
	/**
	 * 墙的画笔
	 * @param g
	 */
	public void draw(Graphics g) {
		g.drawImage(wallImags[0], x, y, null);
	}
	/**
	 * 该方法用于得到墙所包围的矩形区域 用于解决坦克不能穿墙
	 * @return 返回值为墙所包围的矩形区域
	 */
	public Rectangle getRect() {
		return new Rectangle(x, y, width, height);
	}
}
