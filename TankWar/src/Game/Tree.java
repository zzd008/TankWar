package Game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

/**
 * ����
 * @author zzd
 *
 */
public class Tree {
	/**
	 * ���ĳߴ�
	 */
	public static final int width = 30;
	public static final int length = 30;
	int x, y;
	TankClient tc ;
	/**
	 * ������ͼ
	 */
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] treeImags = null;
	static {
		treeImags = new Image[]{
				tk.getImage("tree.gif"),
		};
	}
	
	
	public Tree(int x, int y, TankClient tc) {  //Tree�Ĺ��췽��������x��y��tc����
		this.x = x;
		this.y = y;
		this.tc = tc;
	}
	/**
	 * ������
	 * @param g
	 */
	public void draw(Graphics g) {          
		g.drawImage(treeImags[0],x, y, null);
	}
	public Rectangle getRect() {
		return new Rectangle(x, y, width,length);
	}
	
}
