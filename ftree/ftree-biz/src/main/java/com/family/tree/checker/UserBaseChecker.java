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
			logger.error("UserBaseChecker@checkInputFieldsϵͳ����userBaseDO����Ϊ��!");
			throw new FtreeException("ϵͳ���������������Ϊ��!");
		}
		
		if(userBaseDO.getUserName()==null || userBaseDO.getUserName().length()<=0){
			logger.error("UserBaseChecker@checkInputFieldsϵͳ�����û�������Ϊ��!");
			throw new FtreeException("�û�������Ϊ��!");
		}
		
		if(!userBaseDO.getUserName().startsWith(Constants.LNAME)){
			logger.error("UserBaseChecker@checkInputFields����������Ϊȫ���������ա��ס�!");
			throw new FtreeException("��������Ϊȫ���������ա��ס�!");
		}
		
		if(userBaseDO.getFather()==null || userBaseDO.getFather().length()<=0){
			logger.error("UserBaseChecker@checkInputFieldsϵͳ���󣬸���������Ϊ��!");
			throw new FtreeException("����������Ϊ��!");
		}
		
		if(!userBaseDO.getFather().startsWith(Constants.LNAME)){
			logger.error("UserBaseChecker@checkInputFields�����ױ���Ϊȫ���������ա��ס�!");
			throw new FtreeException("���ױ���Ϊȫ���������ա��ס�!");
		}		
	}
	
	@SuppressWarnings("null")
	public void checkInputUniq(UserBaseDO userBaseDO){
		List<UserBaseDO> userBaseDOs = this.userBaseMapper.selectByUserNameAndFather(userBaseDO.getUserName(), userBaseDO.getFather());
		if(userBaseDOs!=null && userBaseDOs.size()>0){
			logger.error("UserBaseChecker@checkInputUniqͬ�û�����ͬ�������ļ�¼����Ψһ!");
			throw new FtreeException("��¼�Ѿ����ڣ�������������!");
		}
	}
}
