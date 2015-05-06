package com.family.tree.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UserBaseDO implements Serializable {
	private static final long serialVersionUID = 5680134616200682547L;

	private long userId;
	private String userName;
	private String familyName;
	private long identityNum;
	private String education;
	private String school;
	private String title;
	private String nativePlace;
	private String address;
	private long phoneNum;
	private int postCode;
	private String mailAddr;
	private int sex;
	private Date gmtBirth;
	private Date gmtDeath;
	private long parentId;
	private String birthAddr;
	private String baseInfo;
	private int status;
	private Date gmtCreate;
	private Date gmtModified;
	private String lastModifyUser;
	private String features;
	private String father;
	private String mother;
	private String wife;
	private String detailName;
	private int familyRank;
	private String subGroup;

	private List<UserBaseDO> children;
	private List<UserBaseDO> brothers;

	public List<UserBaseDO> getChildren() {
		return children;
	}

	public void setChildren(List<UserBaseDO> children) {
		this.children = children;
	}

	public List<UserBaseDO> getBrothers() {
		return brothers;
	}

	public void setBrothers(List<UserBaseDO> brothers) {
		this.brothers = brothers;
	}

	public String getWife() {
		return wife;
	}

	public void setWife(String wife) {
		this.wife = wife;
	}

	public String getFather() {
		return father;
	}

	public void setFather(String father) {
		this.father = father;
	}

	public String getMother() {
		return mother;
	}

	public void setMother(String mother) {
		this.mother = mother;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public long getIdentityNum() {
		return identityNum;
	}

	public void setIdentityNum(long identityNum) {
		this.identityNum = identityNum;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(long phoneNum) {
		this.phoneNum = phoneNum;
	}

	public int getPostCode() {
		return postCode;
	}

	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}

	public String getMailAddr() {
		return mailAddr;
	}

	public void setMailAddr(String mailAddr) {
		this.mailAddr = mailAddr;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public Date getGmtBirth() {
		return gmtBirth;
	}

	public String getGmtBirthString() {
		if (gmtBirth == null)
			return null;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(gmtBirth);
	}

	public void setGmtBirth(Date gmtBirth) {
		this.gmtBirth = gmtBirth;
	}

	public Date getGmtDeath() {
		return gmtDeath;
	}

	public void setGmtDeath(Date gmtDeath) {
		this.gmtDeath = gmtDeath;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public String getBirthAddr() {
		return birthAddr;
	}

	public void setBirthAddr(String birthAddr) {
		this.birthAddr = birthAddr;
	}

	public String getBaseInfo() {
		return baseInfo;
	}

	public void setBaseInfo(String baseInfo) {
		this.baseInfo = baseInfo;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public String getLastModifyUser() {
		return lastModifyUser;
	}

	public void setLastModifyUser(String lastModifyUser) {
		this.lastModifyUser = lastModifyUser;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDetailName() {
		return detailName;
	}

	public void setDetailName(String detailName) {
		this.detailName = detailName;
	}

	public int getFamilyRank() {
		return familyRank;
	}

	public void setFamilyRank(int familyRank) {
		this.familyRank = familyRank;
	}

	public String getSubGroup() {
		return subGroup;
	}

	public void setSubGroup(String subGroup) {
		this.subGroup = subGroup;
	}

}
