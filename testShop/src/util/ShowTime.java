package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ShowTime {
	
	public static String Time() {
		Date d = new Date();// ��ȡʱ��
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss ");// ת����ʽ
		String Time = sdf.format(d);
		return Time;
	}

	public static void main(String[] args) {
		String time = Time();
		System.out.println(time);
	}

}
