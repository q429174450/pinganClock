package com.paic.arch.interviews;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeConverterImpl implements TimeConverter {

	/**
	 * ʱ���ʽת������24Сʱ����ת������������
	 */
	@Override
	public String convertTime(String aTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		try {
			Date stringToDate = sdf.parse(aTime);//У��ʱ���ʽ�Ƿ���ȷ
		} catch (ParseException e) {
			return "��ʱ�����ʽ��";
		}
		String[] times = aTime.split(":");//���ʱ����
		StringBuffer sb = new StringBuffer();
		int s = Integer.parseInt(times[2])%2;//��ȡ��һ���Ƿ�����
		int h1 = Integer.parseInt(times[0])/5;//��ȡ��2�����Ƹ���
		int h2 = Integer.parseInt(times[0])%5;//��ȡ��3�����Ƹ���
		int m1 = Integer.parseInt(times[1])/5;//��ȡ��4�����Ƹ���
		int m2 = Integer.parseInt(times[1])%5;//��ȡ��5�����Ƹ���
		String str1 = "O";//��һ�ŵ��������
		if(s == 0){//�����0���������
			str1 = "Y";
		}
		String str2 = getLightCount(4,h1);//�ڶ����������
		String str3 = getLightCount(4,h2);//�������������
		String str4 = getLightCount(11,m1);//�������������
		String str5 = getLightCount(4,m2);//�������������
		
		str2 = str2.replace('1', 'R');//������ɫ
		str3 = str3.replace('1', 'R');//������ɫ
		str4 = str4.replace('1', 'Y');//������ɫ
		char[] chrCharArray = str4.toCharArray();//�����4�ţ�15/30/45������ɫΪ��ɫ
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
		return sb.toString();//����ʱ���ʽת��
	}

	/**
	 * ��ʽ��ÿ���������
	 * @param k ��ʾһ�Ŷ��ٸ���
	 * @param j ���ĵ���
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
