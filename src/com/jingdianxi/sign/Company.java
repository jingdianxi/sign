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
	// 考勤记录，key签到日期，value当天考勤记录
	private Map<String, List<SignRecord>> map = new HashMap<String, List<SignRecord>>();
	// 控制台输入
	private Scanner scan = new Scanner(System.in);
	// 日期格式
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	// 时间格式
	SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");

	public void showMenu() {
		while (true) {
			// 显示菜单
			System.out.println("员工考勤系统");
			System.out.println("0------退出");
			System.out.println("1------签到");
			System.out.println("2------签退");
			System.out.println("3------显示考勤信息");
			// 当天日期
			String date = dateFormat.format(new Date());
			// 输入指令
			String order = scan.nextLine();
			// 判断指令
			switch (order) {
			case "0":
				// 退出
				System.out.println("谢谢使用");
				scan.close();
				return;
			case "1":
				// 签到
				System.out.println("请输入要签到的员工姓名");
				String empIn = scan.nextLine();
				// 判断考勤记录Map中是否存在当天签到记录键值对
				if (!map.containsKey(date)) {
					// 若不存在，则新建当天签到记录键值对
					List<SignRecord> signRecords = new ArrayList<SignRecord>();
					SignRecord signRecord = new SignRecord(empIn, timeFormat.format(new Date()));
					signRecords.add(signRecord);
					map.put(date, signRecords);
					System.out.println(empIn + "签到成功");
				} else {
					// 若存在，则判断该员工是否已签到
					List<SignRecord> signRecords = map.get(date);
					boolean isSign = false;
					for (SignRecord signRecord : signRecords) {
						if (signRecord.getEmpName().equals(empIn)) {
							isSign = true;
							break;
						}
					}
					if (isSign) {
						// 若已签到，则提示信息
						System.out.println("您今天已签到");
					} else {
						// 若未签到，则保存签到记录
						SignRecord signRecord = new SignRecord(empIn, timeFormat.format(new Date()));
						signRecords.add(signRecord);
						map.put(date, signRecords);
						System.out.println(empIn + "签到成功");
					}
				}
				break;
			case "2":
				// 签退
				System.out.println("请输入要签退的员工姓名");
				String empOut = scan.nextLine();
				// 判断考勤记录Map中是否存在当天签到记录键值对
				if (!map.containsKey(date)) {
					System.out.println("今天无签到记录，无法签退");
				} else {
					// 若存在，则判断该员工是否已签到
					List<SignRecord> signRecords = map.get(date);
					boolean isSign = false;
					for (SignRecord signRecord : signRecords) {
						if (signRecord.getEmpName().equals(empOut)) {
							// 若已签到，则更新签退记录
							int index = signRecords.indexOf(signRecord);
							isSign = true;
							signRecord.setTimeEnd(timeFormat.format(new Date()));
							signRecords.set(index, signRecord);
							map.put(date, signRecords);
							System.out.println(empOut + "签退成功");
							break;
						}
					}
					if (!isSign) {
						// 若未签到，则提示信息
						System.out.println("您今天未签到，无法签退");
					}
				}
				break;
			case "3":
				// 按日期查找
				System.out.println("请输入要查找的日期(yyyyMMdd)");
				String dateQuery = scan.nextLine();
				try {
					dateFormat.parse(dateQuery);
				} catch (ParseException exception) {
					// TODO Auto-generated catch block
					System.out.println("日期格式错误");
					break;
				}
				List<SignRecord> signRecords = map.get(date);
				if (signRecords != null && !signRecords.isEmpty()) {
					for (SignRecord signRecord : signRecords) {
						System.out.print(signRecord.getEmpName() + "签到时间为" + signRecord.getTimeStart() + "签退时间为");
						if (signRecord.getTimeEnd() != null) {
							System.out.println(signRecord.getTimeEnd());
						} else {
							System.out.println();
						}
					}
				} else {
					System.out.println("查询日期签到记录为空");
				}
				break;
			default:
				// 指令错误，提示并返回
				System.out.println("指令错误");
				break;
			}
		}
	}
}
