package Game;

import java.awt.*;
import java.util.Random;
/**
 * Ѫ�� �ҷ�̹�˳��˼�Ѫ
 * @author zzd
 *
 */
public class Blood {
	/**
	 * Ѫ�ĳߴ�
	 */
	public static final int width = 36;
	public static final int length = 36;
	/**
	 * Ѫ��λ��
	 */
	private int x, y;
	TankClient tc;
	private static Random r = new Random();

	int step = 0; 
	private boolean live = true;
	/**
	 * ��ͼ Ѫ
	 */
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] bloodImags = null;
	static {
		bloodImags = new Image[] { 
				tk.getImage("hp.png"), 
				};
	}
	/**
	 * Ѫ��������ֵ�λ��
	 */
	private int[][] poition = { {100,220},{700,260} ,{100,520},{560,560},{300,560},{380,220},{400,70},};
	/**
	 * ����
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
	 * �ƶ�����
	 */
	private void move() {
		step++;
		if (step == poition.length) {
			step = 0;
		}
		x = poition[step][0];
		y = poition[step][1];
		
	}

	public Rectangle getRect() { //���س����η�Χ
		return new Rectangle(x, y, width, length);
	}

	public boolean isLive() {//�ж��Ƿ񻹻���
		return live;
	}

	public void setLive(boolean live) {  //��������
		this.live = live;
	}

}