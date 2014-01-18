/**
 * 
 */
package com.marswork.core.enums;

/**
 * @author Nordy.SU
 */
public enum Whether implements I_Category<Whether> {

	TRUE("是"), FALSE("否");

	private String name;

	private Whether(String name) {
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
	public Whether reflect(String categoryName) {
		for (Whether category : values()) {
			if (category.getCategoryName().equals(categoryName)) {
				return category;
			}
		}
		return null;
	}

}
