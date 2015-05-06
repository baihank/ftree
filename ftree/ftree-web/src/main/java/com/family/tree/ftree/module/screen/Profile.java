package com.family.tree.ftree.module.screen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.family.tree.domain.LifeLogDO;
import com.family.tree.domain.UserBaseDO;
import com.family.tree.service.UserInfoService;
import com.family.tree.type.LifeLogType;
import com.family.tree.utils.UserQuery;

public class Profile {
	@Autowired
	private UserInfoService userInfoService;
	
    public void execute(@Param("userId")long userId, TurbineRunData rundata, Context context) {  
    	if(userId<=0){
    		context.put("msg", "系统错误或操作错误，请联系管理员!");
    	} else {
    		UserBaseDO userBaseDO = this.userInfoService.getUserByUserId(userId);
    		context.put("userBaseDO", userBaseDO);
    		
    		//获取孩子信息
    		List<UserBaseDO> children = this.userInfoService.selectByParentId(userBaseDO.getUserId());
			if(children==null || children.size()<=0){
				children = new ArrayList<UserBaseDO>();
				
				UserBaseDO child = new UserBaseDO();
				child.setUserId(0);
				child.setUserName("无信息");
				children.add(child);
			}
			context.put("children", children);
    		
			//获取兄弟信息
			List<UserBaseDO> brothers = null;
    		if(userBaseDO.getParentId()>0){
    			brothers = this.userInfoService.selectByParentId(userBaseDO.getParentId());
    		}    		
    		if(brothers==null || brothers.size()<=1){
				brothers = new ArrayList<UserBaseDO>();
				
				UserBaseDO brother = new UserBaseDO();
				brother.setUserId(0);
				brother.setUserName("无信息");
				brothers.add(brother);
			} else if(brothers.size()>1){
				for(Iterator<UserBaseDO> ite = brothers.iterator();ite.hasNext();){
					UserBaseDO self = ite.next();
					if(self.getUserId()==userId) ite.remove();
				}
			}
			context.put("brothers", brothers);	
			
			//判断父亲信息是否存在
			this.initProperParents(userBaseDO, context);
    		
    		List<LifeLogDO> lifeLogDOs = this.userInfoService.getLifeLogDOsByUserId(userId);
    		if(lifeLogDOs!=null && lifeLogDOs.size()>0){
    			for(LifeLogDO lifeLogDO:lifeLogDOs){
    				String key = LifeLogType.valueOf(lifeLogDO.getType()).getName();
    				context.put(key, lifeLogDO.getLog());
    			}
    		}
    	}
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
    			sb.append(properFather.getUserName());
    			if(properFather.getGmtBirthString()!=null){
    				sb.append("(出生于").append(properFather.getGmtBirthString()).append(")，");	    			
    			} else {
    				sb.append("，");
    			}    			
    			sb.append("父:").append(properFather.getFather());	  			
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
