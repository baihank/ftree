package com.family.tree.type;

/**
 * 
 * �Ա�
 * 
 * @author feihu
 *
 */
public enum SexType {
	NULL(0, ""),MALE(1, "��"),FEMALE(2, "Ů");
	
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
	 * ȡ�ø��˾�������
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