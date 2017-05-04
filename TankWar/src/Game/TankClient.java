package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import javax.swing.JFrame;

/**
 *  主窗体类
 * @author 周致达
 * 游戏的整体思路是：通过线程不断重画游戏界面  并在游戏中实现各个元素类的逻辑关系 
 */
public class TankClient extends Frame{
	/**
	 * 第几关
	 */
	public int gamecount=1;
	/**
	 * 只能产生另一个我方坦克的标志
	 */
	public int mytankflag=1;
	public static int flag=1;//游戏结束后不让坦克再产生!
	/**
	 * 下次出现坦克的数量
	 */
	
	/**
	 * 通关的标志
	 */
	public static int flag1=1;
	public static int tankcount=6;
	/**
	 * 游戏界面的宽度
	 */
	public static final int GAME_WIDTH=800;
	/**
	 * 游戏界面的高度 
	 */
	public static final int GAME_HEIGHT=600;
	/**
	 * 游戏分数
	 */
	public static int score=0;//静态 我方两个坦克共同分数!
	/**
	 * 杀敌数
	 */
	public static int count=0;
	
	Image offScreenImage = null;  //建立一张虚拟图片 
	/*
	 * 构建公共墙
	 */
	ArrayList<Wall> Wall1 = new ArrayList<Wall>();
	/**
	 * 金属墙构建
	 */
	ArrayList<Wall2> Wall2 = new ArrayList<Wall2>();
	
	Tank mytank=new Tank(200, 580,this,true,0);//构建我方坦克
	Tank mytank1=new Tank(560,580, this, true,1);//我方坦克二号
	/**
	 * 我方坦克集合
	 */
	ArrayList<Tank> mytanks = new ArrayList<Tank>();
	
	Bullet bullet=null;//构建子弹
	Home home=new Home(368, 545, this);//家
	Blood blood = new Blood(); // 实例化生命
	
	 /**
	  * 子弹容器
	  */
	ArrayList<Bullet> bullets=new ArrayList<Bullet>(); 
	/**
	 * 爆炸容器
	 */
	ArrayList<Boom> booms=new ArrayList<Boom>(); 
	/**
	 * 敌方坦克容器
	 */
	ArrayList<Tank> tanks=new ArrayList<Tank>();
	/**
	 * 树容器
	 */
	ArrayList<Tree> trees = new ArrayList<Tree>();
	
	
	/**
	 * 设置窗体的名字
	 * @param 窗体的名字
	 */
	public TankClient(String string) {
		this.setTitle(string);
	}
	/**
	 * paint方法(画出界面所需的元素(如子弹 坦克 墙 等)) 通过线程一直被调用进行画面更新
	 */
	public void paint(Graphics g){
		/**
		 * 画出我方每个坦克
		 */
		if(this.mytankflag==0){
			for(int i=0;i<mytanks.size();i++){
				Tank myt=mytanks.get(i);
				myt.draw(g);
				myt.eat(blood);// 吃血
				//坦克不能撞老家
				myt.throghhome(this.home);
				//我方坦克并不能撞墙
				for(int i1=0; i1<Wall1.size(); i1++) {
					Wall t = Wall1.get(i1);
					myt.throghWall(t);
				}
				for(int i2=0; i2<Wall2.size(); i2++) {
					Wall2 t = Wall2.get(i2);
					myt.throghWall(t);
				}
				//不撞敌方坦克
				myt.HitTanks(tanks);
			}
		}
			else {
				for(int i=0;i<1;i++){
					Tank myt=mytanks.get(i);
					myt.draw(g);
					myt.eat(blood);// 吃血
					//坦克不能撞老家
					myt.throghhome(this.home);
					//我方坦克并不能撞墙
					for(int i1=0; i1<Wall1.size(); i1++) {
						Wall t = Wall1.get(i1);
						myt.throghWall(t);
					}
					for(int i2=0; i2<Wall2.size(); i2++) {
						Wall2 t = Wall2.get(i2);
						myt.throghWall(t);
					}
					//不撞敌方坦克
					myt.HitTanks(tanks);
				}
			}
		
		//我方坦克不能相撞
		if(this.mytankflag==0){
			mytank.Hitmytank(mytanks.get(1));
			mytanks.get(1).Hitmytank(mytank);
		}
		//mytank.draw(g);//画出我方坦克
		//mytank.eat(blood);// 充满血--生命值
		//画血
		blood.draw(g);
		//画出公共墙
		for(int i=0; i<Wall1.size(); i++) {
			Wall t = Wall1.get(i);
			t.draw(g);
		}
		//画出金属墙
		for(int i=0; i<Wall2.size(); i++) {
			Wall2 t = Wall2.get(i);
			t.draw(g);
		}
		//画树
		for(int i=0; i<trees.size(); i++) {
			Tree t= trees.get(i);
			t.draw(g);
		}
		//画老家
		
		home.draw(g);
		
		g.setColor(Color.red);
		//Font f=g.getFont();
		//g.setFont(new Font(" ", Font.PLAIN, 20));
		//g.drawString("分    数:"+score,20,40);//在窗体中显示分数
		//g.drawString("杀敌数:"+count,20,80);//在窗体中显示杀敌数
		//g.drawString("剩余坦克数:"+tanks.size(),200,40);//在窗体中显示剩余坦克数量
		if(this.home.isLive()){
			g.drawString("第"+gamecount+"关",372 ,320 );
			g.drawString("坦克1号生命值:"+mytank.getlife(),3,320);//我方坦克的生命
			if(this.mytankflag==0){
				g.drawString("坦克2号生命值:"+mytanks.get(1).getlife(),705,320);//我方坦克1的生命	
			}
		}
		//g.setFont(f);
		//如果坦克全部被消灭 重新画敌方坦克 数量自己指定
		/*if(tanks.size() <= 0){
			for(int i=0;i<=tankcount;i++){
				tanks.add(new Tank(150+50*i,200,this,false));
			}
			}
			*/	
		if(tanks.size()<=0&&flag!=0&&tankcount!=10){
			tanks.add(new Tank(5,20,this,false));
			tanks.add(new Tank(200,20,this,false));
			//tanks.add(new Tank(400,20,this,false));		
			tanks.add(new Tank(555,20,this,false));
			tanks.add(new Tank(765,20,this,false));
			if(tankcount==6){
				tanks.add(new Tank(5,340,this,false));
				tanks.add(new Tank(770,340,this,false));
			}
			if(tankcount==8){
				tanks.add(new Tank(5,340,this,false));
				tanks.add(new Tank(770,340,this,false));
				tanks.add(new Tank(5,580,this,false));
				tanks.add(new Tank(770,580,this,false));
			}
			tankcount+=2;
			gamecount++;
			if(tankcount==10){
				flag1=0;
			}
			//System.out.println(tankcount);
			//System.out.println(tanks.size());
		}
		
		
		//敌方坦克不撞老家
		for(int i=0;i<tanks.size();i++){
			Tank t=tanks.get(i);
			t.throghhome(this.home);
		}
		
		
		//敌方坦克并不能撞墙/撞树/撞血..
		for(int i=0; i<tanks.size(); i++) {
			Tank t = tanks.get(i);
			for(int j=0; j<Wall1.size(); j++) {
				Wall t1 = Wall1.get(j);
				t.throghWall(t1);
			}
			for(int j=0; j<Wall2.size(); j++) {
				Wall2 t2 = Wall2.get(j);
				t.throghWall(t2);
			}
			for(int k=0;k<trees.size();k++){
				Tree p=trees.get(k);
				t.Hittree(p);
			}
			t.HitTanks(tanks);
			t.throghhome(home);
			for(int k1=0;k1<mytanks.size();k1++){
				Tank myt=mytanks.get(k1);
				t.Hitmytank(myt);
			}
			t.Hitblood(blood);
		}
		
		//画敌方坦克
		for(int i=0; i<tanks.size(); i++) {
			Tank t = tanks.get(i);
			t.draw(g);
		}
		
		//画出子弹
		for(int i=0;i<bullets.size();i++){
			Bullet m=bullets.get(i);
			m.hitTanks(tanks);
			m.hitTank(mytank);
			if(this.mytankflag==0){
				m.hitTank(mytanks.get(1));
			}
			m.hitHome();
			for(int j=0; j<Wall1.size(); j++) {
				Wall t1 = Wall1.get(j);
				m.hitWall(t1);
			}
			for(int j=0; j<Wall2.size(); j++) {
				Wall2 t1 = Wall2.get(j);
				m.hitWall(t1);
			}
			m.draw(g);
		}
		
		//画出爆炸
		for(int i=0;i<booms.size();i++){
			Boom m=booms.get(i);
			m.draw(g);
		}
		
	}
	
	/**
	 * 该方法用于解决画面闪烁问题
	 */
	public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.black);
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}
	
	/**
	 *该方法用于游戏窗体的一些属性设置
	 */
	public void launchFrame(){
		
		this.setSize(GAME_WIDTH, GAME_HEIGHT);
		this.setLocation(800, 300);
		this.setIconImage(Toolkit.getDefaultToolkit().createImage("tk2.png"));//窗体标题栏图标
		this.setResizable(false); //设置此窗体不可由用a户调整大小 全屏有问题！
		this.setBackground(Color.black);//设置窗体背景颜色
		this.setVisible(true);
		addWindowListener(new WindowAdapter() { //添加监听器 关闭窗体
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		//设置敌方坦克出现的位置
		tanks.add(new Tank(5,20,this,false));
		tanks.add(new Tank(200,20,this,false));		
		tanks.add(new Tank(555,20,this,false));
		tanks.add(new Tank(765,20,this,false));
		
		/**
		 * 我方坦克集合
		 */
		mytanks.add(mytank);
		mytanks.add(mytank1);
		
		//公共墙
		//家周围的墙
		for(int i=0;i<4;i++){
			Wall1.add(new Wall(345,580-20*i,this));
		}
		for(int i=0;i<4;i++){
			Wall1.add(new Wall(415,580-20*i,this));
		}
		Wall1.add(new Wall(355,520,this));
		Wall1.add(new Wall(375,520,this));
		Wall1.add(new Wall(395,520,this));
		//其他墙
		for(int i=0;i<8;i++){
			Wall1.add(new Wall(45,70+20*i,this));
		}
		for(int i=0;i<8;i++){
			Wall1.add(new Wall(65,70+20*i,this));
		}
		
		for(int i=0;i<8;i++){
			Wall1.add(new Wall(145,70+20*i,this));
		}
		for(int i=0;i<8;i++){
			Wall1.add(new Wall(165,70+20*i,this));
		}
		for(int i=0;i<8;i++){
			Wall1.add(new Wall(245,70+20*i,this));
		}
		for(int i=0;i<8;i++){
			Wall1.add(new Wall(265,70+20*i,this));
		}
		for(int i=0;i<8;i++){
			Wall1.add(new Wall(725,70+20*i,this));
		}
		for(int i=0;i<8;i++){
			Wall1.add(new Wall(705,70+20*i,this));
		}
		for(int i=0;i<8;i++){
			Wall1.add(new Wall(625,70+20*i,this));
		}
		for(int i=0;i<8;i++){
			Wall1.add(new Wall(605,70+20*i,this));
		}
		for(int i=0;i<8;i++){
			Wall1.add(new Wall(525,70+20*i,this));
		}
		for(int i=0;i<8;i++){
			Wall1.add(new Wall(505,70+20*i,this));
		}
		
		for(int i=0;i<5;i++){
			Wall1.add(new Wall(340,100+20*i,this));
		}
		for(int i=0;i<5;i++){
			Wall1.add(new Wall(420,100+20*i,this));
		}
		for(int i=0;i<3;i++){
			Wall1.add(new Wall(360+20*i,100,this));
		}
		for(int i=0;i<3;i++){
			Wall1.add(new Wall(360+20*i,180,this));
		}
		
		//下方
		
		for(int i=0;i<8;i++){
			Wall1.add(new Wall(45,530-20*i,this));
		}
		for(int i=0;i<8;i++){
			Wall1.add(new Wall(65,530-20*i,this));
		}
		
		for(int i=0;i<8;i++){
			Wall1.add(new Wall(145,530-20*i,this));
		}
		for(int i=0;i<8;i++){
			Wall1.add(new Wall(165,530-20*i,this));
		}
		for(int i=0;i<8;i++){
			Wall1.add(new Wall(245,530-20*i,this));
		}
		for(int i=0;i<8;i++){
			Wall1.add(new Wall(265,530-20*i,this));
		}
		for(int i=0;i<8;i++){
			Wall1.add(new Wall(725,530-20*i,this));
		}
		for(int i=0;i<8;i++){
			Wall1.add(new Wall(705,530-20*i,this));
		}
		for(int i=0;i<8;i++){
			Wall1.add(new Wall(625,530-20*i,this));
		}
		for(int i=0;i<8;i++){
			Wall1.add(new Wall(605,530-20*i,this));
		}
		for(int i=0;i<8;i++){
			Wall1.add(new Wall(525,530-20*i,this));
		}
		for(int i=0;i<8;i++){
			Wall1.add(new Wall(505,530-20*i,this));
		}
		
		for(int i=0;i<5;i++){
			Wall1.add(new Wall(340,480-20*i,this));
		}
		for(int i=0;i<5;i++){
			Wall1.add(new Wall(420,480-20*i,this));
		}
		for(int i=0;i<3;i++){
			Wall1.add(new Wall(360+20*i,400,this));
		}
		for(int i=0;i<3;i++){
			Wall1.add(new Wall(360+20*i,480,this));
		}
		
		
		//金属墙
		for(int i=0;i<4;i++){
			Wall2.add(new Wall2(20*i, 300,this));
		}
		for(int i=0;i<4;i++){
			Wall2.add(new Wall2(760-20*i, 300,this));
		}
		for(int i=0;i<4;i++){
			Wall2.add(new Wall2(340+20*i, 300,this));
		}
		for(int i=0;i<1;i++){
			Wall2.add(new Wall2(372+20*i, 132,this));
		}
		for(int i=0;i<1;i++){
			Wall2.add(new Wall2(372+20*i, 432,this));
		}
		
		
		//树
		for (int i = 0; i < 4; i++) { 
				trees.add(new Tree( 150+30 * i, 300, this));
		}
		for (int i = 0; i < 4; i++) { 
			trees.add(new Tree( 520+30 * i, 300, this));
		}
		
		//添加键盘监听器 让坦克动起来
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				 mytank.keyPressed(e);
				}
			public void keyReleased(KeyEvent e){
				mytank.keyReleased(e);
			}
		});
		new Thread(new PaintThread()) .start();	//调用线程 让坦克动起来
	}
	
	/**
	 * 主函数
	 * @param args
	 */
	public static void main(String[] args) {
		TankClient tc=new TankClient("坦克大战     Author: zzd");
		tc.launchFrame();
	}
	
	/**
	 * 线程 调用paint()函数 进行画面更新
	 * @author周致达
	 *
	 */
	private class PaintThread implements Runnable{
		public void run(){
			while(true){
				repaint();
				try {
					Thread.sleep(60);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	

}