/**
 * 
 */
package org.wltea.analyzer.dic;

/**
 * <p>
 * 字典类型
 * <p>
 * 包括主词典，姓氏词典，量词词典<br>
 * 后缀词典，副词介词词典和停止词集合
 * 
 * @author MarsDJ
 * @since 2012-1-4
 * @version 1.0
 */
public enum DictCategory {
	/**
	 * 主词典
	 */
	MAIN("/org/wltea/analyzer/dic/main.dic") {
		public DictSegment getAccordingSegment() {
			return Dictionary.getInstance().getMainDict();
		}
	},
	/**
	 * 姓氏词典
	 */
	SURNAME("/org/wltea/analyzer/dic/surname.dic") {
		public DictSegment getAccordingSegment() {
			return Dictionary.getInstance().getSurnameDict();
		}
	},
	/**
	 * 量词词典
	 */
	QUANTIFIER("/org/wltea/analyzer/dic/quantifier.dic") {
		public DictSegment getAccordingSegment() {
			return Dictionary.getInstance().getQuantifierDict();
		}
	},
	/**
	 * 后缀词典
	 */
	SUFFIX("/org/wltea/analyzer/dic/suffix.dic") {
		public DictSegment getAccordingSegment() {
			return Dictionary.getInstance().getSuffixDict();
		}
	},
	/**
	 * 副词，介词词典
	 */
	PREP("/org/wltea/analyzer/dic/preposition.dic") {
		public DictSegment getAccordingSegment() {
			return Dictionary.getInstance().getPrepDict();
		}
	},
	/**
	 * 停止词集合
	 */
	STOP("/org/wltea/analyzer/dic/stopword.dic") {
		public DictSegment getAccordingSegment() {
			return Dictionary.getInstance().getStopWords();
		}
	};

	private String baseFilePath;

	private DictCategory(String baseFilePath) {
		this.baseFilePath = baseFilePath;

	}

	public String getFilePath() {
		return baseFilePath;
	}

	public abstract DictSegment getAccordingSegment();
}
