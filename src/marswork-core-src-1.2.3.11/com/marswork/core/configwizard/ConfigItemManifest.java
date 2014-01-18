/**
 * 
 */
package com.marswork.core.configwizard;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.marswork.core.exceptions.config.PropertiesNotFoundException;
import com.marswork.core.minitools.file.PropertiesOperator;
import com.marswork.core.minitools.project.ProjectUtils;

/**
 * <p>
 * 配置项名单
 * <p>
 * MarsWork框架中的所有基础配置<br>
 * 配置项名单
 * 
 * @author MarsDJ
 * @since 2011-9-17
 * @version 1.0
 */
public enum ConfigItemManifest {

	/**
	 * server--Server 访问
	 * 
	 */
	COM_MARSWORK_SERVER_SERVERACCESS,
	/**
	 * server--Re5Server 访问
	 * 
	 */
	COM_MARSWORK_SERVER_RE5ACCESS,

	/**
	 * database--数据库服务主机IP
	 * 
	 */
	COM_MARSWORK_DATABASE_HOSTIP,
	/**
	 * database--数据库服务端口
	 */
	COM_MARSWORK_DATABASE_PORT,
	/**
	 * database--数据库类型
	 */
	COM_MARSWORK_DATABASE_DATABASETYPE,
	/**
	 * database--数据库架构
	 */
	COM_MARSWORK_DATABASE_DATABASESCHEME,
	/**
	 * database--数据库登录名
	 */
	COM_MARSWORK_DATABASE_USERNAME,
	/**
	 * database--数据库登录密码
	 */
	COM_MARSWORK_DATABASE_USERPWD,
	/**
	 * database--数据库名
	 */
	COM_MARSWORK_DATABASE_DATABASENAME,
	/**
	 * database--初始连接数
	 */
	COM_MARSWORK_DATABASE_INITIALCONNECTIONS,
	/**
	 * database--递增链接数
	 */
	COM_MARSWORK_DATABASE_INCREMENTALCONNECTIONS,
	/**
	 * database--最大连接数
	 */
	COM_MARSWORK_DATABASE_MAXCONNECTIONS,
	/**
	 * database--取不到连接时最多尝试次数
	 */
	COM_MARSWORK_DATABASE_MAXATTEMPTION,
	/**
	 * database--额外参数，以如下形式得到结果
	 * 例：useUnicode=true,characterEncoding=UTF-8
	 */
	COM_MARSWORK_DATABASE_EXTRAARGS,
	/**
	 * database--cache--内存最大成员数
	 */
	COM_MARSWORK_DATABASE_CACHE_MAXELEMENTSINMEMORY,
	/**
	 * database--cache--内存驱逐策略
	 */
	COM_MARSWORK_DATABASE_CACHE_MEMORYSTOREEVICTIONPOLICY,
	/**
	 * database--cache--是否允许输出到磁盘
	 */
	COM_MARSWORK_DATABASE_CACHE_OVERFLOWTODISK,
	/**
	 * database--cache--成员是否永久有效
	 */
	COM_MARSWORK_DATABASE_CACHE_ETERNAL,
	/**
	 * database--cache--element最大存活时间，单位秒
	 */
	COM_MARSWORK_DATABASE_CACHE_TIMETOLIVESECONDS,
	/**
	 * database--cache--element最大闲置时间，单位秒
	 */
	COM_MARSWORK_DATABASE_CACHE_TIMETOIDLESECONDS,
	/**
	 * database--cache--持久化到磁盘
	 */
	COM_MARSWORK_DATABASE_CACHE_DISKPERSISTENT,
	/**
	 * database--cache--失效element清理线程运行时间间隔是0秒
	 */
	COM_MARSWORK_DATABASE_CACHE_DISKEXPIRYTHREADINTERVALSECONDS,
	
	
	/**
	 * bean--META-INF包的绝对路径
	 */
	COM_MARSWORK_DEVELOP_META,
	/**
	 * bean--逆向生成POJO的路径
	 */
	COM_MARSWORK_DEVELOP_BEAN_POJOPATH,
	/**
	 * bean--逆向生成POJO的包名
	 */
	COM_MARSWORK_DEVELOP_BEAN_POJOPACKAGENAME,
	/**
	 * bean--逆向生成CRIT的路径
	 */
	COM_MARSWORK_DEVELOP_BEAN_CRITPATH,
	/**
	 * bean--逆向生成CRIT的包名
	 */
	COM_MARSWORK_DEVELOP_BEAN_CRITPACKAGENAME,
	/**
	 * view--逆向生成jsp源的目录名
	 */
	COM_MARSWORK_DEVELOP_VIEW_VIEWPACKAGENAME,
	/**
	 * view--逆向生成HELPER的路径
	 */
	COM_MARSWORK_DEVELOP_VIEW_VIEWPATH,
	/**
	 * bean--生成页面单元的路径
	 */
	COM_MARSWORK_DEVELOP_UNIT_UNITPATH,
	/**
	 * bean--生成页面单元的的包名
	 */
	COM_MARSWORK_DEVELOP_UNIT_UNITPACKAGENAME,
	/**
	 * bean--生成url重写规则的路径
	 */
	COM_MARSWORK_DEVELOP_REWRITER_REWRITERPATH,
	/**
	 * bean--生成url重写规则的的包名
	 */
	COM_MARSWORK_DEVELOP_REWRITER_REWRITERPACKAGENAME,
	/**
	 * bean--生成action的路径
	 */
	COM_MARSWORK_DEVELOP_ACTION_ACTIONPATH,
	/**
	 * bean--生成action的包名
	 */
	COM_MARSWORK_DEVELOP_ACTION_ACTIONPACKAGENAME,
	/**
	 * view--指定web工程的webcontent目录
	 */
	COM_MARSWORK_DEVELOP_WEB_WEBPATH,

	
	/**
	 * view--逆向生成HELPER的模式，分为ignore和update<br>
	 * ignore表示生成过的helper不会在将来的build中更新，update反之
	 */
	COM_MARSWORK_VIEW_VIEWBUILDMODE,
	/**
	 * view--逆向生成HELPER的包名
	 */
	COM_MARSWORK_VIEW_VIEWPACKAGENAME,

	/**
	 * page view--icon设置
	 */
	COM_MARSWORK_TAGLIB_PAGEVIEW_PAGEICON,

	/**
	 * file uploader--文件上传绝对路径
	 */
	COM_MARSWORK_FILEUPLOADER_UPLOADFILEPATH,
	/**
	 * file uploader--文件访问路径前缀
	 */
	COM_MARSWORK_FILEUPLOADER_PREFIX,

	/**
	 * fileconverter swfconverter --转换器路径
	 */
	COM_MARSWORK_FILECONVERTER_SWFCONVERTERPATH,
	/**
	 * fileconverter flvconverter --转换器路径
	 */
	COM_MARSWORK_FILECONVERTER_FLVCONVERTERPATH,

	/**
	 * fulltext extdict --全文检索扩展词库
	 */
	COM_MARSWORK_FULLTEXT_EXTDICT,
	/**
	 * fulltext extdict --全文检索扩展停止词库
	 */
	COM_MARSWORK_FULLTEXT_EXTSTOPWORD,
	/**
	 * fulltext luceneindex -- 创建全文索引的位置，绝对路径
	 */
	COM_MARSWORK_FULLTEXT_LUCENE_INDEX,

	/**
	 * webim defualtimg --默认头像图片路径
	 */
	COM_MARSWORK_WEBIM_DEFUALTIMG,

	/**
	 * webservice -- web服务基础访问路径
	 */
	COM_MARSWORK_WEBWERVICE_SERVICE,

	/**
	 * exceptions -- 扩展的异常配置文件
	 */
	COM_MARSWORK_EXCEPTIONS_EXTEXCEPTIONS,

	/**
	 * authority -- 权限，角色的枚举
	 */
	COM_MARSWORK_AUTHORITY_ROLE,
	/**
	 * authority -- 权限，关系的枚举
	 */
	COM_MARSWORK_AUTHORITY_RELATION,
	/**
	 * authority -- 权限，增值服务的枚举
	 */
	COM_MARSWORK_AUTHORITY_FUNCTION,

	/**
	 * mail -- 默认帐户的用户名
	 */
	COM_MARSWORK_TOOLBOX_MAIL_USERNAME,
	/**
	 * mail -- 默认帐户的密码
	 */
	COM_MARSWORK_TOOLBOX_MAIL_PASSWORD,
	/**
	 * mail -- SMTP服务主机IP
	 */
	COM_MARSWORK_TOOLBOX_MAIL_SMTPHOST,
	/**
	 * mail -- SMTP服务主机端口
	 */
	COM_MARSWORK_TOOLBOX_MAIL_SMTPPORT,
	/**
	 * mail -- 默认帐户的显示名，别名
	 */
	COM_MARSWORK_TOOLBOX_MAIL_USERALIAS,

	/**
	 * oauth -- 设置accesstoken是否会过期
	 */
	COM_MARSWORK_OAUTH_ACCESSTOKEN_EXPIRE,
	/**
	 * oauth -- 客户端，provider的IP
	 */
	COM_MARSWORK_OAUTH_CLIENT_HOST,
	/**
	 * oauth -- 客户端，客户端的key
	 */
	COM_MARSWORK_OAUTH_CLIENT_KEY,
	/**
	 * oauth -- 客户端，客户端的secret
	 */
	COM_MARSWORK_OAUTH_CLIENT_SECRET,
	/**
	 * oauth -- 客户端，客户端在服务端的用户名
	 */
	COM_MARSWORK_OAUTH_CLIENT_USERNAME,
	/**
	 * oauth -- 客户端，客户端在服务端的密码
	 */
	COM_MARSWORK_OAUTH_CLIENT_PASSWORD, ;

	/**
	 * 获取基础配置信息
	 * 
	 * @return 基础配置信息
	 * @throws PropertiesNotFoundException
	 *             主配置文件无效
	 */
	public String getValue() throws PropertiesNotFoundException {
		return PropertiesOperator.getClassRootProperties("marswork", this.name().toLowerCase()
				.replace("_", "."));
	}

	/**
	 * 写基础配置信息
	 * 
	 * @param value
	 *            要写入的值
	 * @throws UnsupportedEncodingException
	 *             编码例外
	 * @throws IOException
	 *             文件读写例外
	 */
	public void setValue(String value) throws UnsupportedEncodingException, IOException {
		PropertiesOperator op = new PropertiesOperator(ProjectUtils.getTreadRelativePath()
				+ PropertiesOperator.attachFileName("marswork"));
		op.writeProperties(this.name().toLowerCase().replace("_", "."), value);
	}
}
