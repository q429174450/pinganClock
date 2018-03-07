package com.paic.arch.interviews;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeConverterImpl implements TimeConverter {

	/**
	 * 时间格式转换，将24小时进制转换成亮钟数量
	 */
	@Override
	public String convertTime(String aTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		try {
			Date stringToDate = sdf.parse(aTime);//校验时间格式是否正确
		} catch (ParseException e) {
			return "非时分秒格式。";
		}
		String[] times = aTime.split(":");//拆分时分秒
		StringBuffer sb = new StringBuffer();
		int s = Integer.parseInt(times[2])%2;//获取第一排是否亮灯
		int h1 = Integer.parseInt(times[0])/5;//获取第2排亮灯个数
		int h2 = Integer.parseInt(times[0])%5;//获取第3排亮灯个数
		int m1 = Integer.parseInt(times[1])/5;//获取第4排亮灯个数
		int m2 = Integer.parseInt(times[1])%5;//获取第5排亮灯个数
		String str1 = "O";//第一排灯亮灯情况
		if(s == 0){//如果是0，则灯亮，
			str1 = "Y";
		}
		String str2 = getLightCount(4,h1);//第二排亮灯情况
		String str3 = getLightCount(4,h2);//第三排亮灯情况
		String str4 = getLightCount(11,m1);//第四排亮灯情况
		String str5 = getLightCount(4,m2);//第五排亮灯情况
		
		str2 = str2.replace('1', 'R');//处理颜色
		str3 = str3.replace('1', 'R');//处理颜色
		str4 = str4.replace('1', 'Y');//处理颜色
		char[] chrCharArray = str4.toCharArray();//处理第4排，15/30/45分钟颜色为红色
		for(int i = 0; i < chrCharArray.length;i++){
			if(i == 2 || i == 5 || i == 8){
				if(chrCharArray[i] == 'Y'){
					chrCharArray[i] = 'R';
				}
			}
		}
		str4 = String.valueOf(chrCharArray);
		str5 = str5.replace('1', 'Y');
		sb.append(str1 + "\r\n");
		sb.append(str2 + "\r\n");
		sb.append(str3 + "\r\n");
		sb.append(str4 + "\r\n");
		sb.append(str5);
		return sb.toString();//返回时间格式转换
	}

	/**
	 * 格式化每排亮灯情况
	 * @param k 表示一排多少个等
	 * @param j 亮的灯数
	 * @return
	 */
	private static String getLightCount(int k,int j){
		char[] retChars = new char[k];
		for(int i = 1;i <= k;i++){
			if(i <= j){
				retChars[i-1] = '1';
			}else {
				retChars[i-1] = 'O';
			}
		}
		return String.valueOf(retChars);
	}
}
