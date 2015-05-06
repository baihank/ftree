package com.family.tree.checker;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.family.tree.domain.UserBaseDO;
import com.family.tree.mapper.UserBaseMapper;
import com.family.tree.utils.Constants;
import com.family.tree.utils.FtreeException;

public class UserBaseChecker {
	private final static Logger logger = LoggerFactory.getLogger(UserBaseChecker.class);   
	@Autowired
	private UserBaseMapper userBaseMapper;
	

	public void checkInputFields(UserBaseDO userBaseDO){
		if(userBaseDO==null){
			logger.error("UserBaseChecker@checkInputFields系统错误，userBaseDO不能为空!");
			throw new FtreeException("系统错误，输入参数不能为空!");
		}
		
		if(userBaseDO.getUserName()==null || userBaseDO.getUserName().length()<=0){
			logger.error("UserBaseChecker@checkInputFields系统错误，用户名不能为空!");
			throw new FtreeException("用户名不能为空!");
		}
		
		if(!userBaseDO.getUserName().startsWith(Constants.LNAME)){
			logger.error("UserBaseChecker@checkInputFields，姓名必须为全名，必须姓“孔”!");
			throw new FtreeException("姓名必须为全名，必须姓“孔”!");
		}
		
		if(userBaseDO.getFather()==null || userBaseDO.getFather().length()<=0){
			logger.error("UserBaseChecker@checkInputFields系统错误，父亲名不能为空!");
			throw new FtreeException("父亲名不能为空!");
		}
		
		if(!userBaseDO.getFather().startsWith(Constants.LNAME)){
			logger.error("UserBaseChecker@checkInputFields，父亲必须为全名，必须姓“孔”!");
			throw new FtreeException("父亲必须为全名，必须姓“孔”!");
		}		
	}
	
	@SuppressWarnings("null")
	public void checkInputUniq(UserBaseDO userBaseDO){
		List<UserBaseDO> userBaseDOs = this.userBaseMapper.selectByUserNameAndFather(userBaseDO.getUserName(), userBaseDO.getFather());
		if(userBaseDOs!=null && userBaseDOs.size()>0){
			logger.error("UserBaseChecker@checkInputUniq同用户名、同父亲名的记录必须唯一!");
			throw new FtreeException("记录已经存在，不允许进行添加!");
		}
	}
}
