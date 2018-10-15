package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ShowTime {
	
	public static String Time() {
		Date d = new Date();// 获取时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss ");// 转换格式
		String Time = sdf.format(d);
		return Time;
	}

	public static void main(String[] args) {
		String time = Time();
		System.out.println(time);
	}

}
