package org.cc.utils;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

/**
 * 用于屏幕辅助计算的工具类
 * @author CC11001100
 *
 */
public class ScreenUtils {

	/**
	 * 返回屏幕大小
	 * @return Dimension 屏幕大小
	 */
	public static  Dimension getScreenSize(){
		return Toolkit.getDefaultToolkit().getScreenSize();
	}
	
	/**
	 * needs the window frame width and height. 返回窗体若居中时应处位置
	 * @param width 当前窗体的宽度
	 * @param height 当前窗体的高度
	 * @return Point 应该处的位置
	 */
	public static Point getLocationOnScreenCenter(int width,int height){
		int x=Toolkit.getDefaultToolkit().getScreenSize().width/2-width/2;
		int y=Toolkit.getDefaultToolkit().getScreenSize().height/2-height/2;
		return new Point(x,y);
	}
	
	/**
	 * 如果与某元素居中的话那么偏移量是多少呢
	 * @param w 当前窗体的宽度
	 * @param h 当前窗体的高度
	 * @param pw 父窗体的宽度
	 * @param ph 父窗体的高度
	 * @return Point 应该处的位置
	 */
	public static Point getLocationOnFooCenter(int w,int h,int pw,int ph){
		return new Point((pw-w)/2,(ph-h)/2);
	}
	
}
