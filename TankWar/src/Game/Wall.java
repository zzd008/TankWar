package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
/**
 *ǽ����
 * @author ���´�
 *����Ϊ��Ϸ�е�ǽ��
 */
public class Wall {
	/**
	 * ����ǽ��ͼƬ
	 */
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] wallImags = null;
	static {
		wallImags = new Image[] { 
				tk.getImage("wall1.gif"),
			};
	}
	/**
	 * ǽ��λ�� �� ��
	 */
	int x, y;
	public static final int width = 20; 
	public static final int height = 20;
	/**
	 * ����TankClient�����ã�����ʹ�����еĳ�Ա����
	 */
	TankClient tc ;
	/**
	 * ���캯��
	 */
	public Wall(int x, int y, TankClient tc) {
		this.x = x;
		this.y = y;
		this.tc = tc;
	}
	/**
	 * ǽ�Ļ���
	 * @param g
	 */
	public void draw(Graphics g) {
		g.drawImage(wallImags[0], x, y, null);
	}
	/**
	 * �÷������ڵõ�ǽ����Χ�ľ������� ���ڽ��̹�˲��ܴ�ǽ
	 * @return ����ֵΪǽ����Χ�ľ�������
	 */
	public Rectangle getRect() {
		return new Rectangle(x, y, width, height);
	}
}
