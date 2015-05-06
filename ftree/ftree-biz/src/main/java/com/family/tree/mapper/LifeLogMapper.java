package com.family.tree.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.family.tree.domain.LifeLogDO;

public interface LifeLogMapper {	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	LifeLogDO selectById(long id);
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	List<LifeLogDO> selectByUserId(long userId);
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	LifeLogDO selectByUserIdAndType(@Param("userId")long userId, @Param("type")int type);
	
	/**
	 * 
	 * @param userBaseDO
	 * @return
	 */
	int insert(LifeLogDO lifeLogDO);
	
	/**
	 * 
	 * @param userBaseDO
	 * @return
	 */
	int update(LifeLogDO lifeLogDO);
	
	/**
	 * 
	 * @param userBaseDO
	 * @return
	 */
	int delete(LifeLogDO lifeLogDO);

}
