package com.family.tree.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.family.tree.domain.UserBaseDO;
import com.family.tree.utils.UserQuery;

public interface UserBaseMapper {	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	UserBaseDO selectByUserId(long userId);
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	int countByUserName(UserQuery userQuery);
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	List<UserBaseDO> selectByUserName(UserQuery userQuery);
	
	/**
	 * 
	 * @param parentId
	 * @return
	 */
	List<UserBaseDO> selectByParentId(long parentId);
	
	/**
	 * 
	 * @param userName father
	 * @return
	 */
	List<UserBaseDO> selectByUserNameAndFather(@Param("userName")String userName, @Param("father")String father);
	
	/**
	 * 
	 * @param userBaseDO
	 * @return
	 */
	int insert(UserBaseDO userBaseDO);
	
	/**
	 * 
	 * @param userBaseDO
	 * @return
	 */
	int update(UserBaseDO userBaseDO);
	
	/**
	 * 
	 * @param userBaseDO
	 * @return
	 */
	int delete(UserBaseDO userBaseDO);

}
