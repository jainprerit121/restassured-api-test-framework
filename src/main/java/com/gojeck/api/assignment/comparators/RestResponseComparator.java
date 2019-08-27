package com.gojeck.api.assignment.comparators;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.gojeck.api.assignment.utils.CustomLogger;
import com.gojeck.api.assignment.utils.RestAssuredAPIUtils;

public class RestResponseComparator {

	private static final int MAX_THREADS = 10;
	private static final int THREAD_TIMEOUT = 120;
	private static final int BATCH_SIZE = 499;
	private static AtomicInteger mismatchCount = new AtomicInteger();

	/**
	 * Why?
	 * Ans: This method is implemented so that we can split 
	 * total requests in batches and then run them in parallel
	 * @throws InterruptedException 
	 */
	public void runBatchCompare(
			List<String> endPointsFromFile1,
			List<String> endPointsFromFile2) throws InterruptedException {

		AtomicInteger counter = new AtomicInteger(0);
		AtomicInteger startBatch = new AtomicInteger(0);

		ExecutorService executor = Executors.newFixedThreadPool(MAX_THREADS);

		endPointsFromFile1.forEach(lineFromFile1 ->{
			endPointsFromFile2.forEach(lineFromFile2 ->{

				//Creating thread for batch
				if(counter.get()==BATCH_SIZE){
					int endBatch=counter.get();
					compareBatch(executor, endPointsFromFile1,endPointsFromFile2, startBatch.get(), endBatch);
					startBatch.set(endBatch+1);
				}
			});
			counter.getAndIncrement();
		});

		if((counter.get()-(startBatch.get()))>1){
			int endBatch=counter.get()-1;
			compareBatch(executor, endPointsFromFile1, endPointsFromFile2, startBatch.get(), endBatch);
		}

		executor.shutdown();
		executor.awaitTermination(THREAD_TIMEOUT, TimeUnit.MINUTES);
	}

	public void compareBatch(ExecutorService executer, List<String> endPointsFromFile1,
			List<String> endPointsFromFile2, int startBatch, int endBatch){
		Thread compareThread = new Thread(new Runnable() {

			@Override
			public void run() {
				Map<String, String> headers = new HashMap<>();
				headers.put("Content-Type", "application/json");

				for(int i = startBatch; i<=endBatch; i++){
					
					String restEndPoint1 = endPointsFromFile1.get(i);
					RestAssuredAPIUtils restUtil = new RestAssuredAPIUtils();
					String response1 = restUtil.GET(headers, restEndPoint1.trim()).getBody().asString();
					
					String restEndPoint2 = endPointsFromFile2.get(i);
					RestAssuredAPIUtils restUtil2 = new RestAssuredAPIUtils();
					String response2 = restUtil2.GET(headers, restEndPoint2.trim()).getBody().asString();
					
					CustomLogger.info("Comparing "+restEndPoint1+" & "+restEndPoint2);
					CustomLogger.info("Comparing "+response1+" & "+response2);
					
					JSONComparator comparator = new JSONComparator(response1, response2);
					if(!comparator.compare())
					{
						mismatchCount.getAndIncrement();
						CustomLogger.error(restEndPoint1+" not equals "+restEndPoint2);
					}
				}
			}
		}, "Batch "+startBatch+"-"+endBatch);

		executer.execute(compareThread);
	}
	
	public boolean hasFailures(){
		return mismatchCount.get()>0;
	}
}
