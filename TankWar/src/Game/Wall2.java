package Game;

import java.awt.*;
/**
 * ����ǽ��
 * @author zzd
 *
 */
public class Wall2 {
	/**
	 * ǽ�ĳߴ�
	 */
	public static final int width = 30; 
	public static final int length = 30;
	int x, y;

	TankClient tc;
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] wallImags = null;
	static {
		wallImags = new Image[] { // �������Wall��ͼƬ
		tk.getImage("Wall2.gif"), };
	}

	public Wall2(int x, int y, TankClient tc) { // ���캯��
		this.x = x;
		this.y = y;
		this.tc = tc; 
	}
	//����
	public void draw(Graphics g) {
		g.drawImage(wallImags[0], x, y, null);
	}

	public Rectangle getRect() {  
		return new Rectangle(x, y, width, length);
	}
}

