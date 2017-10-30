package com.jingdianxi.sign;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Company {
	// ���ڼ�¼��keyǩ�����ڣ�value���쿼�ڼ�¼
	private Map<String, List<SignRecord>> map = new HashMap<String, List<SignRecord>>();
	// ����̨����
	private Scanner scan = new Scanner(System.in);
	// ���ڸ�ʽ
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	// ʱ���ʽ
	SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");

	public void showMenu() {
		while (true) {
			// ��ʾ�˵�
			System.out.println("Ա������ϵͳ");
			System.out.println("0------�˳�");
			System.out.println("1------ǩ��");
			System.out.println("2------ǩ��");
			System.out.println("3------��ʾ������Ϣ");
			// ��������
			String date = dateFormat.format(new Date());
			// ����ָ��
			String order = scan.nextLine();
			// �ж�ָ��
			switch (order) {
			case "0":
				// �˳�
				System.out.println("ллʹ��");
				scan.close();
				return;
			case "1":
				// ǩ��
				System.out.println("������Ҫǩ����Ա������");
				String empIn = scan.nextLine();
				// �жϿ��ڼ�¼Map���Ƿ���ڵ���ǩ����¼��ֵ��
				if (!map.containsKey(date)) {
					// �������ڣ����½�����ǩ����¼��ֵ��
					List<SignRecord> signRecords = new ArrayList<SignRecord>();
					SignRecord signRecord = new SignRecord(empIn, timeFormat.format(new Date()));
					signRecords.add(signRecord);
					map.put(date, signRecords);
					System.out.println(empIn + "ǩ���ɹ�");
				} else {
					// �����ڣ����жϸ�Ա���Ƿ���ǩ��
					List<SignRecord> signRecords = map.get(date);
					boolean isSign = false;
					for (SignRecord signRecord : signRecords) {
						if (signRecord.getEmpName().equals(empIn)) {
							isSign = true;
							break;
						}
					}
					if (isSign) {
						// ����ǩ��������ʾ��Ϣ
						System.out.println("��������ǩ��");
					} else {
						// ��δǩ�����򱣴�ǩ����¼
						SignRecord signRecord = new SignRecord(empIn, timeFormat.format(new Date()));
						signRecords.add(signRecord);
						map.put(date, signRecords);
						System.out.println(empIn + "ǩ���ɹ�");
					}
				}
				break;
			case "2":
				// ǩ��
				System.out.println("������Ҫǩ�˵�Ա������");
				String empOut = scan.nextLine();
				// �жϿ��ڼ�¼Map���Ƿ���ڵ���ǩ����¼��ֵ��
				if (!map.containsKey(date)) {
					System.out.println("������ǩ����¼���޷�ǩ��");
				} else {
					// �����ڣ����жϸ�Ա���Ƿ���ǩ��
					List<SignRecord> signRecords = map.get(date);
					boolean isSign = false;
					for (SignRecord signRecord : signRecords) {
						if (signRecord.getEmpName().equals(empOut)) {
							// ����ǩ���������ǩ�˼�¼
							int index = signRecords.indexOf(signRecord);
							isSign = true;
							signRecord.setTimeEnd(timeFormat.format(new Date()));
							signRecords.set(index, signRecord);
							map.put(date, signRecords);
							System.out.println(empOut + "ǩ�˳ɹ�");
							break;
						}
					}
					if (!isSign) {
						// ��δǩ��������ʾ��Ϣ
						System.out.println("������δǩ�����޷�ǩ��");
					}
				}
				break;
			case "3":
				// �����ڲ���
				System.out.println("������Ҫ���ҵ�����(yyyyMMdd)");
				String dateQuery = scan.nextLine();
				try {
					dateFormat.parse(dateQuery);
				} catch (ParseException exception) {
					// TODO Auto-generated catch block
					System.out.println("���ڸ�ʽ����");
					break;
				}
				List<SignRecord> signRecords = map.get(date);
				if (signRecords != null && !signRecords.isEmpty()) {
					for (SignRecord signRecord : signRecords) {
						System.out.print(signRecord.getEmpName() + "ǩ��ʱ��Ϊ" + signRecord.getTimeStart() + "ǩ��ʱ��Ϊ");
						if (signRecord.getTimeEnd() != null) {
							System.out.println(signRecord.getTimeEnd());
						} else {
							System.out.println();
						}
					}
				} else {
					System.out.println("��ѯ����ǩ����¼Ϊ��");
				}
				break;
			default:
				// ָ�������ʾ������
				System.out.println("ָ�����");
				break;
			}
		}
	}
}
