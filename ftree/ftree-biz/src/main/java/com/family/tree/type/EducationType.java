package com.family.tree.type;

/**
 * 
 * 学历
 * 
 * @author feihu
 *
 */
public enum EducationType {
	NULL(0, "null", "未确认"),BASE(1, "base", "小学"),JUNIOR(2, "junior", "初中"),SENIOR(3, "senior", "高中"),
	BACHELOR(4, "bachelor", "大学本科"),MASTER(4, "master", "硕士"),DOCTOR(5, "doctor", "博士");
	
	private final int value;
	private final String name;
	private final String desc;

	private EducationType(int value, String name, String desc) {
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
	public static EducationType valueOf(Integer value) {
		if (value != null) {
			for (EducationType s : EducationType.values()) {
				if (s.getValue() == value) {
					return s;
				}
			}
		}
		return null;
	}
}