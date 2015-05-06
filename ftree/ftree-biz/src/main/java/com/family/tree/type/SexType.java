package com.family.tree.type;

/**
 * 
 * 性别
 * 
 * @author feihu
 *
 */
public enum SexType {
	NULL(0, ""),MALE(1, "男"),FEMALE(2, "女");
	
	private final int value;
	private final String name;	

	private SexType(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}


	/**
	 * 取得个人经历类型
	 * 
	 * @param status
	 * @return
	 */
	public static SexType valueOf(Integer value) {
		if (value != null) {
			for (SexType s : SexType.values()) {
				if (s.getValue() == value) {
					return s;
				}
			}
		}
		return null;
	}
}