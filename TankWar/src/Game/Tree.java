package Game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

/**
 * 树类
 * @author zzd
 *
 */
public class Tree {
	/**
	 * 树的尺寸
	 */
	public static final int width = 30;
	public static final int length = 30;
	int x, y;
	TankClient tc ;
	/**
	 * 树的贴图
	 */
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] treeImags = null;
	static {
		treeImags = new Image[]{
				tk.getImage("tree.gif"),
		};
	}
	
	
	public Tree(int x, int y, TankClient tc) {  //Tree的构造方法，传递x，y和tc对象
		this.x = x;
		this.y = y;
		this.tc = tc;
	}
	/**
	 * 树画笔
	 * @param g
	 */
	public void draw(Graphics g) {          
		g.drawImage(treeImags[0],x, y, null);
	}
	public Rectangle getRect() {
		return new Rectangle(x, y, width,length);
	}
	
}
