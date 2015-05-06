/*
 * Copyright 2010 Alibaba Group Holding Limited.
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.family.tree.ftree.module.screen;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.family.tree.domain.LifeLogDO;
import com.family.tree.domain.UserBaseDO;
import com.family.tree.service.UserInfoService;
import com.family.tree.type.EducationType;
import com.family.tree.type.LifeLogType;
import com.family.tree.utils.UserQuery;

public class Edit {
	@Autowired
	private UserInfoService userInfoService;
	
    public void execute(@Param("userId")long userId, 
    		@Param("isEdit")long isEdit, 
    		@Param("userName")String userName,
    		@Param("flag")int flag,
    		@Param("msg")String msg,
    		TurbineRunData rundata, Context context) {
    	//初始化常量
    	this.initConstants(context);
    	
    	if(userId>0 && isEdit==1){
    		UserBaseDO userBaseDO = this.userInfoService.getUserByUserId(userId);
    		context.put("userBaseDO", userBaseDO);
    		
    		//初始化父亲人选
    		this.initProperParents(userBaseDO, context);
    		
    		List<LifeLogDO> lifeLogDOs = this.userInfoService.getLifeLogDOsByUserId(userId);
    		if(lifeLogDOs!=null && lifeLogDOs.size()>0){
    			for(LifeLogDO lifeLogDO:lifeLogDOs){
    				String key = LifeLogType.valueOf(lifeLogDO.getType()).getName();
    				context.put(key, lifeLogDO.getLog());
    			}
    		}
    		
    		//输出设置父子关系的提示信息
    		if(flag==1){
    			context.put("msg", "请设置父子关系!");
    		}
    	} else if(userName!=null && userName.length()>0){ 
    		if(context.get("msg")==null || context.get("msg").toString().length()<=0) context.put("msg", "该家族成员尚不存在，请添加成员!");
    		context.put("userName", userName);
    	}
    }
    
    private void initConstants(Context context){
    	context.put("educationTypes", EducationType.values());
    }
    
    private void initProperParents(UserBaseDO userBaseDO, Context context){
    	String fatherName = userBaseDO.getFather();
    	
    	UserQuery userQuery = new UserQuery();
    	userQuery.setUserName(fatherName);
    	userQuery.setPageIndex(0);
    	userQuery.setPageSize(20);
    	
    	boolean validParentId = false;
    	userQuery = this.userInfoService.query(userQuery);
    	if(userQuery.getTotalRecord()>0 && userQuery.getTotalRecord()<20){
    		List<UserBaseDO> properFathers = userQuery.getResult();
    		
    		for(UserBaseDO properFather:properFathers){
    			StringBuilder sb = new StringBuilder();
    			sb.append("父亲:").append(properFather.getUserName());
    			/*
    			if(properFather.getGmtBirthString()!=null){
    				sb.append("(出生于").append(properFather.getGmtBirthString()).append(")，");	    			
    			} else {
    				sb.append("，");
    			}*/
    			
    			sb.append(" 祖父:").append(properFather.getFather());	  			
    			properFather.setDetailName(sb.toString());  
    			
    			if(userBaseDO.getParentId()==properFather.getUserId()){
    				validParentId = true;
    			}
    		}
    		
    		//如果在用户表中没有找到合理父亲，则修正关系
    		if(!validParentId) userBaseDO.setParentId(0);
    		
    		context.put("properFathers", properFathers);
    	}    	
    }
}
