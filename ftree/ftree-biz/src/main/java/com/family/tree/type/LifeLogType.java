package com.family.tree.type;

/**
 * 
 * 个人经历类型：
 * 
 * @author feihu
 *
 */
public enum LifeLogType {
	NULL(0, "null", "未知状态"),MARRIGE(1, "marrige", "结婚信息"),CHILDREN(2, "childrenDesc", "生子信息"),
	EDUCATION(3, "edulog", "教育信息"),ACHIEVEMENT(4, "achievement", "个人成就"),DEATH(5, "death", "去世信息");
	
	private final int value;
	private final String name;	
	private final String desc;

	private LifeLogType(int value, String name, String desc) {
		this.value = value;
		this.name = name;
		this.desc = desc;
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}
	
	public String getDesc() {
		return desc;
	}

	/**
	 * 取得个人经历类型
	 * 
	 * @param status
	 * @return
	 */
	public static LifeLogType valueOf(Integer value) {
		if (value != null) {
			for (LifeLogType s : LifeLogType.values()) {
				if (s.getValue() == value) {
					return s;
				}
			}
		}
		return null;
	}
}