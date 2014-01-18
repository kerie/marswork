package com.marswork.core.minitools.cache;

import java.lang.ref.SoftReference;
import java.util.Collection;
import java.util.HashMap;

import org.apache.log4j.Logger;

/**
 * <p>
 * 实体缓存类
 * <p>
 * 
 * @author Nordy.SU
 * @since 2011-05-29
 * @since 1.0
 * @version 1.0
 * @param <K>
 *            Key Type
 * @param <V>
 *            Value Type
 */
public class ItemCache<K, V> {

	/**
	 * 缓存实体的Builder
	 * 
	 * @author Nordy
	 * @param <K>
	 *            Key Type
	 * @param <V>
	 *            Value Type
	 */
	public interface ValueBuilder<K, V> {

		/**
		 * 创建实体
		 * 
		 * @param key
		 *            实体的KEY
		 * @return 创建的实体
		 */
		V build(K key);
	}

	private static final Logger logger = Logger.getLogger(ItemCache.class);

	private final HashMap<K, SoftReference<V>> mCache;

	private final ValueBuilder<K, V> mBuilder;

	/**
	 * 构造方法
	 * 
	 * @param builder
	 *            实体构造类，用户创建缓存实体
	 */
	public ItemCache(ValueBuilder<K, V> builder) {
		this.mCache = new HashMap<K, SoftReference<V>>();
		this.mBuilder = builder;
	}

	/**
	 * 清空缓存
	 */
	public void clear() {
		this.mCache.clear();
	}

	/**
	 * 判断是否已经缓存Key对应的数据实体
	 * 
	 * @param key
	 *            指定的KEY
	 * @return 缓存中已经有Key对应的数据实体
	 */
	public boolean contains(String key) {
		return this.mCache.containsKey(key);
	}

	/**
	 * 从缓存中获取实体，如果不在缓存中，则使用Builder构造一个
	 * 
	 * @param key
	 *            实体的KEY
	 * @return 实体
	 */
	public V get(K key) {
		V value = null;

		SoftReference<V> reference = this.mCache.get(key);
		if (reference != null) {
			value = reference.get();
		}

		// not in cache or gc'd
		if (value == null) {
			logger.debug("Cache miss for " + key);
			value = this.mBuilder.build(key);
			this.put(key, value);
		}
		return value;
	}

	/**
	 * 往缓存中添加实体
	 * 
	 * @param key
	 *            实体的KEY
	 * @param value
	 *            实体
	 * @return 加入缓存的实体
	 */
	public void put(K key, V value) {
		this.mCache.put(key, new SoftReference<V>(value));
	}

	/**
	 * 从缓存中删除一个实体
	 * 
	 * @param key
	 *            实体的KEY
	 * @return 删除的实体
	 */
	public void remove(K key) {
		this.mCache.remove(key);
	}

	/**
	 * 缓存中数据实体数量
	 * 
	 * @return how many Entities is hold
	 */
	public int size() {
		return this.mCache.size();
	}

	public Collection<K> getKetSet() {
		return mCache.keySet();
	}
}
