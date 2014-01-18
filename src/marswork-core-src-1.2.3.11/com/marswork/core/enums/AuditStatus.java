/**
 * 
 */
package com.marswork.core.enums;


/**
 * 
 * <p>
 * <p>
 * @author Nordy.SU
 * @author MarsDJ
 * @since 2012-7-12
 * @version 1.0
 */
public enum AuditStatus implements I_Category<AuditStatus> {

	PASSED("通过"), REFUSED("未通过"), PENDING("未审核"), DELETED("已删除"), ALL("所有");

	private String name;

	private AuditStatus(String name) {
		this.name = name;
	}

	@Override
	public String getCategoryName() {
		return this.name;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.marswork.core.enums.Category#reflect(java.lang.String)
	 */
	@Override
	public AuditStatus reflect(String categoryName) {
		for (AuditStatus category : values()) {
			if (category.getCategoryName().equals(categoryName)) {
				return category;
			}
		}
		return null;
	}

}
