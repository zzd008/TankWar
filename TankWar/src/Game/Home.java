package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
/**
 * �ϼ���
 * @author zzd
 *
 */
public class Home {
	public int flag=1;
	/**
	 * �ϼҵ�λ��
	 */
	private int x, y;
	private TankClient tc;
	/**
	 * �ҵĳߴ�
	 */
	public static final int width = 46, length = 48;
	private boolean live = true;

	private static Toolkit tk = Toolkit.getDefaultToolkit(); 
	private static Image[] homeImags = null;
	static {
		homeImags = new Image[] { 
				tk.getImage("home1.png"), 
				tk.getImage("over.png"), 
				tk.getImage("win2.png"), 
				};
	}

	public Home(int x, int y, TankClient tc) {// ���캯��
		this.x = x;
		this.y = y;
		this.tc = tc; 
	}

	public void gameOver(Graphics g) {

		tc.tanks.clear();// ����ҳ��
		tc.Wall1.clear();
		tc.Wall2.clear();
		tc.trees.clear();
		tc.bullets.clear();
		tc.mytank.setlive(false);
		if(tc.mytankflag==0){
			tc.mytanks.get(1).setlive(false);
		}
		tc.home.setLive(false);
		tc.blood.setLive(false);
		Color c = g.getColor(); // ���ò���
		g.setColor(Color.white);
		Font f = g.getFont();
		g.setFont(new Font(" ", Font.PLAIN, 30));
		g.drawImage(homeImags[1], 60, 40, null);
		g.drawString("ɱ����:"+tc.count,320,340);//�ڴ�������ʾɱ����
		g.drawString("��F2���¿�ʼ ", 290, 410);
		g.drawString("��    ��:"+tc.score,320,270);//�ڴ�������ʾ����
		g.setFont(f);
		g.setColor(c);

	}
	public void gamewin(Graphics g) {
		
		tc.tanks.clear();// ����ҳ�湤��
		tc.Wall1.clear();
		tc.Wall2.clear();
		tc.trees.clear();
		tc.bullets.clear();
		tc.mytank.setlive(false);
		if(tc.mytankflag==0){
			tc.mytanks.get(1).setlive(false);
		}
		tc.home.setLive(false);
		tc.blood.setLive(false);
		Color c = g.getColor(); // ���ò���
		g.setColor(Color.pink);
		Font f = g.getFont();
		g.setFont(new Font(" ", Font.PLAIN, 40));
		g.drawImage(homeImags[2], 100, 100, null);
		g.drawString("ɱ����:"+tc.count,300,440);//�ڴ�������ʾɱ����
		//g.drawString("��ϲͨ�أ� ", 300, 300);
		g.drawString("��    ��:"+tc.score,300,360);//�ڴ�������ʾ����
		g.setFont(f);
		g.setColor(c);
		
	}
	/**
	 * �ҵĻ���
	 * @param g
	 */
	public void draw(Graphics g) {

		if (live||flag==0) { // ������ţ��ͻ���home
			if(flag!=0){
				g.drawImage(homeImags[0], x, y, null);
			}
			if(tc.flag1==0&&tc.tanks.size()<=0){
				flag=0;
				gamewin(g);
			}
		} 
		else if(!live&&flag!=0){
			gameOver(g); // ������Ϸ����
		}
	}

	

	public boolean isLive() { // �ж��Ƿ񻹻���
		return live;
	}

	public void setLive(boolean live) { // ��������
		this.live = live;
	}

	public Rectangle getRect() { // ���س�����ʵ��
		return new Rectangle(x, y, width, length);
	}

}
