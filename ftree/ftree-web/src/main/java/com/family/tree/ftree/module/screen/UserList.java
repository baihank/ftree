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

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.family.tree.domain.UserBaseDO;
import com.family.tree.service.UserInfoService;
import com.family.tree.type.SexType;
import com.family.tree.utils.UserQuery;

public class UserList {
	@Autowired
	private UserInfoService userInfoService;
	
    public void execute(@Param("userName")String userName, TurbineRunData rundata, Context context) {  
    	UserQuery userQuery = this.buildQuery(userName, rundata, context);
    	
    	//查询数据
    	this.userInfoService.query(userQuery);
    	this.buildBaseInfo(userQuery);
    	context.put("query", userQuery);
    	
    }
    
    private void buildBaseInfo(UserQuery userQuery){
    	if(userQuery!=null && userQuery.getResult()!=null && userQuery.getResult().size()>0){
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    		
    		for(UserBaseDO userBaseDO:userQuery.getResult()){
    			StringBuilder sb = new StringBuilder();
    			sb.append(userBaseDO.getUserName()).append("，");
    			
    			if(userBaseDO.getSex()==1 || userBaseDO.getSex()==2){
    				sb.append(SexType.valueOf(userBaseDO.getSex()).getName()).append("，");
    			}    		
    			sb.append("父 ").append(userBaseDO.getFather()).append("。");
    			
    			if(userBaseDO.getGmtBirth()!=null){
    				sb.append("于").append(sdf.format(userBaseDO.getGmtBirth()));
    				
    				if(userBaseDO.getBirthAddr()!=null){
        				sb.append("出生于").append(userBaseDO.getBirthAddr()).append("。");
        			} else {
        				sb.append("出生。");
        			}
    			} else if(userBaseDO.getBirthAddr()!=null){
    				sb.append("出生于").append(userBaseDO.getBirthAddr()).append("。");
    			} 
    			
    			userBaseDO.setBaseInfo(sb.toString());
    		}
    	}
    		
    }
    
    private UserQuery buildQuery(String userName, TurbineRunData rundata, Context context){
    	UserQuery query = new UserQuery();    	

		//查询关键字
    	if(userName==null || userName.length()<=0){
        	query.setUserName(rundata.getParameters().getString("userName"));
    	} else {
    		query.setUserName(userName);
    	}
    	
    	
    	// 翻页设置
        query.setPageIndex(rundata.getParameters().getInt("pageIndex", 0));
        query.setPageSize(rundata.getParameters().getInt("pageSize", UserQuery.DEFAULT_PAGE_SIZE));
        
        return query;
    }
}
