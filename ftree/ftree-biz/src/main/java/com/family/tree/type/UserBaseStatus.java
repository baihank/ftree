package com.family.tree.type;

public enum UserBaseStatus {
	DELETED(-1, "已删除"), UNCONFORMED(0, "未确认"), CONFIRMED(1, "已确认");

	private final int status;
	private final String desc;

	private UserBaseStatus(int status, String desc) {
		this.status = status;
		this.desc = desc;
	}

	public int getStatus() {
		return status;
	}

	public String getDesc() {
		return desc;
	}

	public static UserBaseStatus valueOf(Integer status){
		if(status!=null){
			for(UserBaseStatus s:UserBaseStatus.values()){
				if(s.getStatus()==status){
					return s;
				}
			}
		}		
		return null;
	}
}
