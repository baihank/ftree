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
    		context.put("msg", "ϵͳ����������������ϵ����Ա!");
    	} else {
    		UserBaseDO userBaseDO = this.userInfoService.getUserByUserId(userId);
    		context.put("userBaseDO", userBaseDO);
    		
    		//��ȡ������Ϣ
    		List<UserBaseDO> children = this.userInfoService.selectByParentId(userBaseDO.getUserId());
			if(children==null || children.size()<=0){
				children = new ArrayList<UserBaseDO>();
				
				UserBaseDO child = new UserBaseDO();
				child.setUserId(0);
				child.setUserName("����Ϣ");
				children.add(child);
			}
			context.put("children", children);
    		
			//��ȡ�ֵ���Ϣ
			List<UserBaseDO> brothers = null;
    		if(userBaseDO.getParentId()>0){
    			brothers = this.userInfoService.selectByParentId(userBaseDO.getParentId());
    		}    		
    		if(brothers==null || brothers.size()<=1){
				brothers = new ArrayList<UserBaseDO>();
				
				UserBaseDO brother = new UserBaseDO();
				brother.setUserId(0);
				brother.setUserName("����Ϣ");
				brothers.add(brother);
			} else if(brothers.size()>1){
				for(Iterator<UserBaseDO> ite = brothers.iterator();ite.hasNext();){
					UserBaseDO self = ite.next();
					if(self.getUserId()==userId) ite.remove();
				}
			}
			context.put("brothers", brothers);	
			
			//�жϸ�����Ϣ�Ƿ����
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
    				sb.append("(������").append(properFather.getGmtBirthString()).append(")��");	    			
    			} else {
    				sb.append("��");
    			}    			
    			sb.append("��:").append(properFather.getFather());	  			
    			properFather.setDetailName(sb.toString());  
    			
    			if(userBaseDO.getParentId()==properFather.getUserId()){
    				validParentId = true;
    			}
    		}
    		
    		//������û�����û���ҵ������ף���������ϵ
    		if(!validParentId) userBaseDO.setParentId(0);
    		
    		context.put("properFathers", properFathers);
    	}    	
    }

}
