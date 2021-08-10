package mvc.db.dto;

import java.util.Date;

public class MemberDto {	
	private String memId;
	private String memName;
	private String passwd;
	private String birthday;
	private String gender;
	private String email;
	private String phone;
	private Date joinDate;	
	private Date updateDate;
	private String isActive;
	private String isDelete;
	
	public String getMemId() {
		return memId;
	}
	public MemberDto setMemId(String memId) {
		this.memId = memId;
		return this;
	}
	public String getMemName() {
		return memName;
	}
	public MemberDto setMemName(String memName) {
		this.memName = memName;
		return this;
	}
	public String getPasswd() {
		return passwd;
	}
	public MemberDto setPasswd(String passwd) {
		this.passwd = passwd;
		return this;
	}
	public String getBirthday() {
		return birthday;
	}
	public MemberDto setBirthday(String birthday) {
		this.birthday = birthday;
		return this;
	}
	public String getGender() {
		return gender;
	}
	public MemberDto setGender(String gender) {
		this.gender = gender;
		return this;
	}
	public String getEmail() {
		return email;
	}
	public MemberDto setEmail(String email) {
		this.email = email;
		return this;
	}
	public String getPhone() {
		return phone;
	}
	public MemberDto setPhone(String phone) {
		this.phone = phone;
		return this;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public MemberDto setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
		return this;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public MemberDto setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
		return this;
	}
	public String getIsActive() {
		return isActive;
	}
	public MemberDto setIsActive(String isActive) {
		this.isActive = isActive;
		return this;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public MemberDto setIsDelete(String isDelete) {
		this.isDelete = isDelete;
		return this;
	}	
}