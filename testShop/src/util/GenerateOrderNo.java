package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerateOrderNo {
	/**
	 * �����󣬿���Ϊ�������
	 */
	private static Object lockObj = "lockerOrder";
	/**
	 * ���������ɼ�����
	 */
	private static long orderNumCount = 0L;
	/**
	 * ÿ�������ɶ������������ֵ
	 */
	private static int maxPerMSECSize = 1000;

	/**
	 * ���ɷ��ظ������ţ���������1����1000��������չ
	 */
	public static String generate() {
		String finOrderNum = "";
		try {
			// �������ɵĶ�����
			synchronized (lockObj) {
				// ȡϵͳ��ǰʱ����Ϊ�����ű���ǰ�벿�֣���ȷ������
				long nowLong = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
				// �����������ֵ���㣬����չ����Ŀǰ1���봦���ֵ1000����1��100��
				if (orderNumCount >= maxPerMSECSize) {
					orderNumCount = 0L;
				}
				// ��װ������
				String countStr = maxPerMSECSize + orderNumCount + "";
				finOrderNum = nowLong + countStr.substring(1);
				orderNumCount++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finOrderNum;
	}
}
