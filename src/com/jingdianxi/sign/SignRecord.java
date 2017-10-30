package com.jingdianxi.sign;

public class SignRecord {
	// 考勤员工姓名
	private String empName;
	// 签到时间
    private String timeStart;
    // 签退时间
    private String timeEnd;
    // 有参构造方法
    public SignRecord(String empName, String timeStart) {
    	this.empName = empName;
    	this.timeStart = timeStart;
    }
    
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getTimeStart() {
		return timeStart;
	}
	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}
	public String getTimeEnd() {
		return timeEnd;
	}
	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}
}
