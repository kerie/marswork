/**
 * 
 */
package com.marswork.core.common.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.marswork.core.minitools.object.BasicUtils;

/**
 * <p>
 * <p>
 * 
 * @author MarsDJ
 * @since 2012-2-25
 * @version 1.0
 */
public class MarsThreadPoolHelper {

	private static ExecutorService pool;

	private static int corePoolSize = 5;

	private static int maximumPoolSize = 100;

	private static int workQueueSize = 50;

	private static int keepAliveTime = 1000;

	private static TimeUnit unit = TimeUnit.MILLISECONDS;

	private static RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();

	private static ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(
			workQueueSize);

	public static ExecutorService getPoolInstance() {
		if (pool == null) {
			useCustomerPool(corePoolSize, workQueueSize, maximumPoolSize, handler);
		}
		return pool;
	}

	public static void toDoTask(Runnable task) throws InterruptedException, ExecutionException {
		getPoolInstance().execute(task);
	}

	public static <V> Future<V> toDoTask(MarsTask<V> task) throws InterruptedException,
			ExecutionException {
		return getPoolInstance().submit(task);
	}

	public static ExecutorService getCurrentThreadPool() {
		return pool;
	}

	public static void useSinglePool() {
		pool = Executors.newSingleThreadExecutor();
	}

	public static void useChachedPool() {
		pool = Executors.newCachedThreadPool();
	}

	public static void useFixedPool(int nThreads) {
		pool = Executors.newFixedThreadPool(nThreads);
	}

	public static void useCustomerPool(int corePoolSize, int workQueueSize, int maximumPoolSize) {
		pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
				workQueue, handler);
	}

	public static void useCustomerPool(int corePoolSize, int workQueueSize, int maximumPoolSize,
			RejectedExecutionHandler handler) {
		pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
				workQueue, handler);
	}

	public static void useDefaultPool() {
		pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
				workQueue, handler);
	}

	public static void shutdown() {
		if (pool != null) {
			pool.shutdown();
		}
	}
}
