package com.lmz.snake.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class GameUtil {
	
	/**
	 * 根据图片相对路径获取图片
	 */
	public static Image getImage(String imagePath) {
		URL url = GameUtil.class.getClassLoader().getResource(imagePath);
		BufferedImage img = null;
		try {
			img = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
	
	/**
	 * 指定角度旋转图片
	 * @return
	 */
	public static Image rotateImage(BufferedImage image, int rotate) {
		int width = image.getWidth();
		int height = image.getHeight();
		int type = image.getColorModel().getTransparency();//获取透明度
		BufferedImage img;//创建一个图片
		Graphics2D g2;//创建一个画笔
		//初始化g2
		(g2 = (img = new BufferedImage(width, height, type)).createGraphics())
		.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		
		g2.rotate(Math.toRadians(rotate),width/2,height/2);//旋转rotate度
		g2.drawImage(image, 0, 0, null);//将imge画到img中
		g2.dispose();
		return img;
	}

}
