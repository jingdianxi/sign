package com.jingdianxi.sign;

public class SignRecord {
	// ����Ա������
	private String empName;
	// ǩ��ʱ��
    private String timeStart;
    // ǩ��ʱ��
    private String timeEnd;
    // �вι��췽��
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
