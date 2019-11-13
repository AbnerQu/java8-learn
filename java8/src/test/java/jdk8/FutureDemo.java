/**
 * Welcome to https://waylau.com
 */
package jdk8;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 
 * @since 1.0.0 2019年1月17日
 * @author <a href="https://waylau.com">Way Lau</a> 
 */
public class FutureDemo {

	/**
	 * @param args
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		int nThreads = 2;
		long oneSecond = 1000L;
		
		ExecutorService exec = Executors.newWorkStealingPool(nThreads);

		Callable<String> taskA = () -> {
			Thread.sleep(oneSecond*2); // 2秒
			return "I am A";
		};
		
		Callable<String> taskB = () -> {
			Thread.sleep(oneSecond*1); // 1秒
			return "I am B";
		};

		Future<String> resultA = exec.submit(taskA);
		Future<String> resultB = exec.submit(taskB);
		
		// 阻塞直到get方法返回值
		System.out.println(resultA.get());
		System.out.println(resultB.get());
		
		//
		// 提交多个任务
		ExecutorService exec2 = Executors.newWorkStealingPool(nThreads);
		List<Callable<String>> tasks = new ArrayList<>();
		tasks.add(taskA);
		tasks.add(taskB);
		List<Future<String>> results = exec2.invokeAll(tasks);
		
		// 阻塞直到所有任务完成
		results.stream().forEach((result) -> {
			try {
				System.out.println(result.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		});
		
		//
		// 提交多个任务
		ExecutorService exec3 = Executors.newWorkStealingPool(nThreads);
		List<Callable<String>> tasks2 = new ArrayList<>();
		tasks2.add(taskA);
		tasks2.add(taskB);
		
		// 任意一个完成即可返回
		String resultTask = exec3.invokeAny(tasks2);
		System.out.println(resultTask);	
		
		//
		// 顺序返回结果
		ExecutorCompletionService<String> exec4 = new ExecutorCompletionService<String>(exec);
		
		Future<String> resultA4 = exec4.submit(taskA);
		Future<String> resultB4 = exec4.submit(taskB);
		
		List<Future<String>> results4 = new ArrayList<>();
		results4.add(resultA4);
		results4.add(resultB4);
		
		// 按顺序返回结果
		results4.stream().forEach((result) -> {
			try {
				System.out.println(result.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		});
		
		
	}

}
