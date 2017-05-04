package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
/**
 * 爆炸类
 * @author 周致达
 
 */
public class Boom {
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	/**
	 * 爆炸贴图
	 */
	private static Image[] imgs = { // 存储爆炸图片 从小到大的爆炸效果图
		tk.getImage("0.gif"),
		tk.getImage("1.gif"),
		tk.getImage("2.gif"),
		tk.getImage("3.gif"),
		tk.getImage("4.gif"),
		tk.getImage("5.gif"),
		tk.getImage("6.gif"),
		tk.getImage("7.gif"),
		tk.getImage("8.gif"),
		};
	
	int step = 0;//爆炸的次数
	
	/**
	 * 爆炸的位置坐标
	 */
	private int x,y;
	/**
	 * 爆炸是否存在
	 */
	private boolean islive=true;
	/**
	 * 保留TankClient的引用，方便使用其中的成员变量
	 */
	TankClient tc=null;
	/**
	 * 判断爆炸是否存在
	 * @return返回爆炸存在与否
	 */
	private boolean live() {
		return islive;
	}

	public Boom(int x,int y){
		this.x=x;
		this.y=y;
	}
	
	/**
	 * 爆炸类构造函数
	 */
	public Boom(int x,int y,TankClient tc){
		this(x,y);
		this.tc=tc;
	}
	/**
	 * 爆炸类画笔
	 */
	public void draw(Graphics g){
		if(!live()){ 	//爆炸没了就从容器中移除
			tc.booms.remove(this);
			return;
		}
		
		if (step == imgs.length) {
			islive = false;
			step = 0;
			return;
		}

		g.drawImage(imgs[step], x, y, null);
		step++;
	}
	
}
