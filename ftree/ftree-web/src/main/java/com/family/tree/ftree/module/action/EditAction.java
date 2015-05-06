package com.family.tree.ftree.module.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.family.tree.domain.LifeLogDO;
import com.family.tree.domain.UserBaseDO;
import com.family.tree.service.UserInfoService;
import com.family.tree.type.LifeLogType;

public class EditAction {
	private final static Logger logger = LoggerFactory.getLogger(EditAction.class); 
	
	@Autowired
	private UserInfoService userInfoService;
	
	public void doAddUser(TurbineRunData rundata, Context context, Navigator nav) {
		try{
			//构建结构化信息
			UserBaseDO userBaseDO = this.buildStruct(rundata, context);
			
			//构建非结构化信息
			List<LifeLogDO> lifeLogDOs = this.buildUnStruct(rundata, context);
			
			//保存结构化信息
			this.addUserBaseDO(userBaseDO);
			
			//若结构化信息保存不成功，则直接返回，不保存非结构化信息
			if(userBaseDO.getUserId()<=0){
				context.put("msg", "提交信息失败!");
			} else {
				//保存非结构化信息
				this.addUnStruct(lifeLogDOs, userBaseDO.getUserId());
				context.put("msg", "保存信息成功!");
				context.put("userId", userBaseDO.getUserId());
				nav.redirectTo("ftreeLink")
					.withTarget("edit")
					.withParameter("isEdit", "1")
					.withParameter("userId", String.valueOf(userBaseDO.getUserId()));
			}
		} catch (Exception e) {
			logger.error("EditAction@doAddUser error!", e);
			context.put("msg", e.getMessage());
		}					
	}
	
	private UserBaseDO buildStruct(TurbineRunData rundata, Context context){
		long userId = rundata.getParameters().getLong("userId", 0);
		long parentId = rundata.getParameters().getLong("parentId", 0);
		String userName = rundata.getParameters().getString("userName", null);		
		String familyName = rundata.getParameters().getString("familyName", null);		
		long identityNum = rundata.getParameters().getLong("identityNum", 0);		
		String education = rundata.getParameters().getString("education", null);		
		String school = rundata.getParameters().getString("school", null);		
		String title = rundata.getParameters().getString("title", null);		
		String nativePlace = rundata.getParameters().getString("nativePlace", null);		
		String address = rundata.getParameters().getString("address", null);
		long phoneNum = rundata.getParameters().getLong("phoneNum", 0);		
		int postCode = rundata.getParameters().getInt("postCode", 0);	
		String mailAddr = rundata.getParameters().getString("mailAddr", null);
		int sex = rundata.getParameters().getInt("sex", 0);		
		Date gmtBirth = rundata.getParameters().getDate("gmtBirth", new SimpleDateFormat("yyyy-MM-dd"), null);		
		String father = rundata.getParameters().getString("father", null);		
		String mother = rundata.getParameters().getString("mother", null);		
		String wife = rundata.getParameters().getString("wife", null);		
		String birthAddr = rundata.getParameters().getString("birthAddr", null);		
		UserBaseDO userBaseDO = new UserBaseDO();
		userBaseDO.setUserId(userId);
		userBaseDO.setUserName(userName);
		userBaseDO.setFamilyName(familyName);
		userBaseDO.setIdentityNum(identityNum);
		userBaseDO.setEducation(education);
		userBaseDO.setSchool(school);
		userBaseDO.setTitle(title);
		userBaseDO.setNativePlace(nativePlace);
		userBaseDO.setAddress(address);
		userBaseDO.setPhoneNum(phoneNum);
		userBaseDO.setPostCode(postCode);
		userBaseDO.setMailAddr(mailAddr);
		userBaseDO.setSex(sex);
		userBaseDO.setGmtBirth(gmtBirth);
		userBaseDO.setFather(father);
		userBaseDO.setMother(mother);
		userBaseDO.setWife(wife);
		userBaseDO.setBirthAddr(birthAddr);
		userBaseDO.setParentId(parentId);
		
		userBaseDO.setStatus(0);
		context.put("userBaseDO", userBaseDO);
		return userBaseDO;
	}
	
	private long addUserBaseDO(UserBaseDO userBaseDO){
		long userId = this.userInfoService.addUser(userBaseDO);	
		return userId;
	}

	private List<LifeLogDO> buildUnStruct(TurbineRunData rundata, Context context){	
		List<LifeLogDO> lifeLogDOs = new ArrayList<LifeLogDO>();
		for(int i=0;i<5;i++){
			LifeLogType lifeLogType = LifeLogType.valueOf(i);
			
			String log = rundata.getParameters().getString(lifeLogType.getName(), null);
			if(log!=null && log.length()>0){
				context.put(lifeLogType.getName(), log);
				
				//保存数据
				LifeLogDO lifeLogDO = new LifeLogDO();
				lifeLogDO.setLog(log);
				lifeLogDO.setType(lifeLogType.getValue());
				lifeLogDO.setStatus(0);
				
				lifeLogDOs.add(lifeLogDO);
			}			
		}
		
		return lifeLogDOs;
	}
	
	private void addUnStruct(List<LifeLogDO> lifeLogDOs, long userId){
		if(lifeLogDOs!=null && lifeLogDOs.size()>0){
			for(LifeLogDO lifeLogDO:lifeLogDOs){
				lifeLogDO.setUserId(userId);
				this.userInfoService.addLifeLog(lifeLogDO); 
			}
		}		
	}
}
