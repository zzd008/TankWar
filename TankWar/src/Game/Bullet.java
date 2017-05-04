package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;
/**
 * 子弹类
 * @author 周致达
 */
public class Bullet {
	/**
	 * 控制面板
	 */
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	/**
	 * 我方坦克子弹图片数组
	 */
	private static Image[] bulletImg1 = null; 
	static {
		bulletImg1 = new Image[] {
				tk.getImage("bullet.png"),
				tk.getImage("bullet_d.png"),
				tk.getImage("bullet_l.png"),
				tk.getImage("bullet_r.png"),
								
		};
	}
	/**
	 * 敌方坦克子弹图片数组
	 */
	private static Image[] bulletImg2 = null; 
	static {
		bulletImg2 = new Image[] {
				tk.getImage("bulletU.gif"),
				tk.getImage("bulletD.gif"),
				tk.getImage("bulletL.gif"),
				tk.getImage("bulletR.gif"),		
		};
	}
	
	/**
	 * 子弹的水平移动速度
	 */
	public static  int  XSPEED=20; // final private 可防止修改数据  也可以写一个函数对速度修改
	/**
	 * 子弹的竖直速度
	 */
	public static  int YSPEED=20;
	/**
	 * 子弹的宽度
	 */
	public static  int WIDTH=10;//子弹的尺寸可变 
	/**
	 * 子弹的高度
	 */
	public static  int HEIGHT=10;
	/**
	 * 子弹的横坐标
	 */
	private int x;
	/**
	 * 子弹的纵坐标
	 */
	private int y;
	/**
	 * 子弹是否活着
	 */
	private boolean islive=true;
	/**
	 * 判断是谁的子弹
	 */
	private boolean ismine=true;
	/**
	 * 保留TankClient的引用，方便使用其中的成员变量
	 */
	private TankClient tc=null;
	/**
	 * 子弹的方向  与坦克的朝向一致
	 */
	private Tank.Direction Dir;
	/**
	 * 判断子弹的生死
	 * @return返回子弹的生死
	 */
	public boolean live(){
		return islive;
	}
	
	public  Bullet(int x,int y,Tank.Direction Dir,TankClient tc){
		this.x=x;
		this.y=y;
		this.Dir=Dir;
		this.tc=tc;
	}
	/**
	 * 子弹类的构造函数
	 * @param x
	 * @param y
	 * @param Dir
	 * @param tc
	 * @param ismine
	 */
	public  Bullet(int x,int y,Tank.Direction Dir,TankClient tc,boolean ismine){
		this.x=x;
		this.y=y;
		this.Dir=Dir;
		this.tc=tc;
		this.ismine=ismine;
	}
	/**
	 * 子弹类的画笔
	 * @param g
	 */
	public void draw(Graphics g){
		if(!live()){ //子弹死了就从容器中移除
			tc.bullets.remove(this);
			return;
		}
		Color c=g.getColor();
		if(islive){
			if(!this.ismine){
				switch (this.Dir) {  //根据方向选择子弹的图片
				case U:
					g.drawImage(bulletImg2[0], x-2, y-5, null);
					break;
				case D:
					g.drawImage(bulletImg2[1], x-4, y+8, null);
					break;
				case L:
					g.drawImage(bulletImg2[2], x-2, y, null);
					break;
				case R:
					g.drawImage(bulletImg2[3], x, y+2, null);
					break;	
				}
			}
			else {
				g.setColor(Color.white);
				
				switch (this.Dir) {  //根据方向选择子弹的图片
				case U:
					g.fillOval(x, y, WIDTH, HEIGHT);
					break;
				case D:
					g.fillOval(x, y, WIDTH, HEIGHT);
					break;
				case L:
					g.fillOval(x, y, WIDTH, HEIGHT);
					break;
				case R:
					g.fillOval(x, y, WIDTH, HEIGHT);
					break;	
					
				}
				}
			}
		else
			g.dispose();
		
			g.setColor(c);
		move();
	}
	
	/**
	 * 得到子弹外围所构成的矩形
	 * @return 子弹外围所构成的矩形
	 */
	public Rectangle getrect(){
		return new Rectangle(x,y,WIDTH,HEIGHT);
	}
	
	/**
	 * 打击多个坦克
	 * @param tanks
	 * @return
	 */
	
	public boolean hitTanks(ArrayList<Tank> tanks){
		for(int i=0;i<tanks.size();i++){
			if(hitTank(tanks.get(i)))
				return true;
		}
		return false;
	}
	
	/**
	 * 打击一个坦克
	 * @param t
	 * @return
	 */
	public boolean hitTank(Tank t){
		if(this.islive&&this.getrect().intersects(t.getrect())&&t.live()&&this.ismine!=t.mine()){
			Boom n=new Boom(t.getx(),t.gety(),tc);
			tc.booms.add(n);
			if(t.mine()){
				t.dislife();//如果我方坦克被打中 每次我方坦克生命减一
				if(t.getlife()<=0){
					t.setlive(false);
				}
			}
			else {
				t.setlive(false);
				tc.score+=10;//分数加10
				tc.count+=1;//杀敌数加1
				tc.tanks.remove(t);
			}
			this.islive=false;
		//	System.out.println(666);
			return true;
		}
			return false;
	}
	
	
	/**
	 * 解决子弹打到墙消失
	 * @param w一面墙
	 * @return true false(可以不要返回值)
	 */
	public boolean hitWall(Wall w) {
		if(this.islive && this.getrect().intersects(w.getRect())) {
			this.islive = false;
			this.tc.Wall1.remove(w); // 子弹打到公共墙上时则移除被击中的墙
			return true;
		}
		return false;
	}
	
	public boolean hitWall(Wall2 w) { // 子弹打到金属墙上
		if (this.islive && this.getrect().intersects(w.getRect())) {
			this.islive = false;//子弹不能穿越金属墙
			//this.tc.Wall2.remove(w); 
			return true;
		}
		return false;
	}
	//子弹打到家
	public boolean hitHome() { // 当子弹打到家时
		if (this.islive && this.getrect().intersects(tc.home.getRect())) {
			this.islive = false;
			this.tc.home.setLive(false); // 当家接受一枪时就死亡
			tc.flag=0;
			return true;
		}
		return false;
	}


	
	/**
	 * 子弹的移动函数
	 */
	public void move(){
		/*
		 * 如果子弹越界 让其消失
		 */
		if (x<0||y<0||x>TankClient.GAME_WIDTH||y>TankClient.GAME_HEIGHT) 
				this.islive=false;
				switch(Dir) {   //左右
				case L:   { x-=XSPEED; break; }  
				case U:   { y-=YSPEED; break;} 
				case R:   { x+=XSPEED; break;}  
				case D:   { y+=YSPEED; break;}    
				case STOP:  break;
					}
			}

	
}
