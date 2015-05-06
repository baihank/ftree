package com.family.tree.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.family.tree.checker.UserBaseChecker;
import com.family.tree.domain.LifeLogDO;
import com.family.tree.domain.UserBaseDO;
import com.family.tree.mapper.LifeLogMapper;
import com.family.tree.mapper.UserBaseMapper;
import com.family.tree.utils.UserQuery;

public class UserInfoService {
	@Autowired
	private UserBaseMapper userBaseMapper;
	
	@Autowired
	private LifeLogMapper lifeLogMapper;
	
	@Autowired
	private UserBaseChecker userBaseChecker;
	
	public UserQuery query(UserQuery userQuery){
		int total = this.userBaseMapper.countByUserName(userQuery);
		userQuery.setTotalRecord(total);		
		List<UserBaseDO> list = this.userBaseMapper.selectByUserName(userQuery);
		userQuery.setResult(list);
		
		return userQuery;
	}
	
	public UserBaseDO getUserByUserId(long userId){
		return this.userBaseMapper.selectByUserId(userId);
	}
	
	public List<UserBaseDO> selectByParentId(long parentId){
		return this.userBaseMapper.selectByParentId(parentId);
	}
	
	public List<LifeLogDO> getLifeLogDOsByUserId(long userId){
		return this.lifeLogMapper.selectByUserId(userId);
	}

	public long addUser(UserBaseDO userBaseDO){
		long userId = userBaseDO.getUserId();
		
		this.userBaseChecker.checkInputFields(userBaseDO);
		if(userId>0){
			this.userBaseMapper.update(userBaseDO);
			return userId;
		} else {
			this.userBaseChecker.checkInputUniq(userBaseDO);
			return this.userBaseMapper.insert(userBaseDO);
		}	
	}
	
	public long addLifeLog(LifeLogDO lifeLogDO){
		long userId = lifeLogDO.getUserId();
		int type = lifeLogDO.getType();
		
		if(userId>0 && type>0){
			LifeLogDO exist = this.lifeLogMapper.selectByUserIdAndType(userId, type);
			
			if(exist!=null){
				this.lifeLogMapper.update(lifeLogDO);
				return exist.getId();
			}
		}	
		
		return this.lifeLogMapper.insert(lifeLogDO);
	}
}
