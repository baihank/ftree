package com.family.tree.type;

/**
 * 
 * ѧ��
 * 
 * @author feihu
 *
 */
public enum EducationType {
	NULL(0, "null", "δȷ��"),BASE(1, "base", "Сѧ"),JUNIOR(2, "junior", "����"),SENIOR(3, "senior", "����"),
	BACHELOR(4, "bachelor", "��ѧ����"),MASTER(4, "master", "˶ʿ"),DOCTOR(5, "doctor", "��ʿ");
	
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
	 * ȡ�ø��˾�������
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