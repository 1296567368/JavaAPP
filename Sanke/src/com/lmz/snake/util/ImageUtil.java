package com.lmz.snake.util;
/**
 * 存储图片方便实用
 * @author 12965
 *
 */

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import com.lmz.snake.constant.Constant;

public class ImageUtil {
	
	//用于存储图片
	public static Map<String, Image> images = new HashMap<>();

	static {
		images.put("snake_body", GameUtil.getImage(Constant.IMG_PRE+"snake_body.png"));
		images.put("food", GameUtil.getImage(Constant.IMG_PRE+"food.png"));
		images.put("snake_head", GameUtil.getImage(Constant.IMG_PRE+"snake_head.png"));
		images.put("background", GameUtil.getImage(Constant.IMG_PRE+"background.jpg"));
		images.put("fail", GameUtil.getImage(Constant.IMG_PRE+"fail.png"));
		
	}
	
	
}
