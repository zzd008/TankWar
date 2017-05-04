package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
/**
 * 老家类
 * @author zzd
 *
 */
public class Home {
	public int flag=1;
	/**
	 * 老家的位置
	 */
	private int x, y;
	private TankClient tc;
	/**
	 * 家的尺寸
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

	public Home(int x, int y, TankClient tc) {// 构造函数
		this.x = x;
		this.y = y;
		this.tc = tc; 
	}

	public void gameOver(Graphics g) {

		tc.tanks.clear();// 清理页面
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
		Color c = g.getColor(); // 设置参数
		g.setColor(Color.white);
		Font f = g.getFont();
		g.setFont(new Font(" ", Font.PLAIN, 30));
		g.drawImage(homeImags[1], 60, 40, null);
		g.drawString("杀敌数:"+tc.count,320,340);//在窗体中显示杀敌数
		g.drawString("按F2重新开始 ", 290, 410);
		g.drawString("分    数:"+tc.score,320,270);//在窗体中显示分数
		g.setFont(f);
		g.setColor(c);

	}
	public void gamewin(Graphics g) {
		
		tc.tanks.clear();// 清理页面工作
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
		Color c = g.getColor(); // 设置参数
		g.setColor(Color.pink);
		Font f = g.getFont();
		g.setFont(new Font(" ", Font.PLAIN, 40));
		g.drawImage(homeImags[2], 100, 100, null);
		g.drawString("杀敌数:"+tc.count,300,440);//在窗体中显示杀敌数
		//g.drawString("恭喜通关！ ", 300, 300);
		g.drawString("分    数:"+tc.score,300,360);//在窗体中显示分数
		g.setFont(f);
		g.setColor(c);
		
	}
	/**
	 * 家的画笔
	 * @param g
	 */
	public void draw(Graphics g) {

		if (live||flag==0) { // 如果活着，就画出home
			if(flag!=0){
				g.drawImage(homeImags[0], x, y, null);
			}
			if(tc.flag1==0&&tc.tanks.size()<=0){
				flag=0;
				gamewin(g);
			}
		} 
		else if(!live&&flag!=0){
			gameOver(g); // 调用游戏结束
		}
	}

	

	public boolean isLive() { // 判读是否还活着
		return live;
	}

	public void setLive(boolean live) { // 设置生命
		this.live = live;
	}

	public Rectangle getRect() { // 返回长方形实例
		return new Rectangle(x, y, width, length);
	}

}
