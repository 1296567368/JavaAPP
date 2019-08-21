package com.lmz.snake.core;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.lmz.snake.constant.Constant;

public class MyFrame  extends Frame{

	
	/**
	 * 加载窗体
	 * 
	 */ 
	public void loadFrame() {
		this.setTitle("贪吃蛇");
		this.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		this.setBackground(Color.BLACK);
		this.setLocationRelativeTo(null);//设置居中
		//设置可关闭
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		//设置课件
		this.setVisible(true);
		
		
		new MyThread().start();
	}
	
	//创建不断重绘的线程
	class MyThread extends Thread{
		
		public void run() {
			
			while(true) {
				repaint();//重绘方法 frame的方法
				try {
					sleep(40);	//控制画面刷新间隔时间...也就是蛇的运行速度了??...无语
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	Image backImg = null;
	//防止图片闪烁设置双重缓存
	public void update(Graphics g) {

		if (backImg == null) {
			backImg = createImage(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);//创建背景图片
		}
		Graphics backg = backImg.getGraphics();//将图片设置为背景图
		Color color = backg.getColor();
		backg.setColor(Color.BLACK);//设置背景为白色
		backg.fillRect(0, 0, Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		backg.setColor(color);
		paint(backg);
		g.drawImage(backImg, 0, 0, null);
	}
	
	
}
