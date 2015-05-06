package com.family.tree.type;

/**
 * 
 * ���˾������ͣ�
 * 
 * @author feihu
 *
 */
public enum LifeLogType {
	NULL(0, "null", "δ֪״̬"),MARRIGE(1, "marrige", "�����Ϣ"),CHILDREN(2, "childrenDesc", "������Ϣ"),
	EDUCATION(3, "edulog", "������Ϣ"),ACHIEVEMENT(4, "achievement", "���˳ɾ�"),DEATH(5, "death", "ȥ����Ϣ");
	
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
	 * ȡ�ø��˾�������
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