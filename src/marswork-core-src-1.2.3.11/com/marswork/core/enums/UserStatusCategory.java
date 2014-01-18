/**
 * 
 */
package com.marswork.core.enums;

/**
 * @author Nordy.SU
 */
public enum UserStatusCategory implements I_Category<UserStatusCategory> {
	
    DISABLED("停用"), ENABLED("启用"), INACTIVE("未激活"),TIME_UP("到期");

    private String name;

    private UserStatusCategory(String name) {
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
	public UserStatusCategory reflect(String categoryName) {
		for (UserStatusCategory category : values()) {
			if (category.getCategoryName().equals(categoryName)) {
				return category;
			}
		}
		return null;
	}
}
