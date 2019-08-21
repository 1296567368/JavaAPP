package com.lmz.snake.client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.lmz.snake.core.Food;
import com.lmz.snake.core.MyFrame;
import com.lmz.snake.core.MySnake;
import com.lmz.snake.util.ImageUtil;

public class SnakeClient extends MyFrame{

	public MySnake mySnake = new MySnake(100,100);
	public Food food = new Food();
	Image background = ImageUtil.images.get("background");
	Image fail = ImageUtil.images.get("fail");
	
	
	public void loadFram() {
		super.loadFrame();
		//添加键盘监听事件
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				mySnake.keyPressed(e);//将键盘事件交给mysnake处理
			}
		});
	}
	
	/**
	 *绘制界面 
	 */
	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, null);
		if (mySnake.live) {
			mySnake.draw(g);//若蛇活着那就一直绘制蛇
			if (food.live) {//若食物或者就一直绘制食物
				food.draw(g);
				food.eaten(mySnake);//食物被吃方法
			}else {
				food = new Food();
			}
		}else {
			g.drawImage(fail, (background.getWidth(null)-fail.getWidth(null))/2,(background.getHeight(null)-fail.getHeight(null))/2,null);
		}
		drawScore(g);
	}
	
	/**
	 * 绘制分数栏
	 * @param g
	 */
	public void drawScore(Graphics g) {
		g.setFont(new Font("Courier New", Font.BOLD, 40));
		g.setColor(Color.WHITE);
		g.drawString("SCORE"+mySnake.score, 700, 100);
	}
	
	public static void main(String[] args) {
		new SnakeClient().loadFram();//加载窗体
	}
}
