package org.stagemonitor.standalone;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.stagemonitor.core.Stagemonitor;
import org.stagemonitor.requestmonitor.MonitorRequests;

public class Main {

	private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

	public static void main(String[] args) {
		Stagemonitor.init();
		new Main().execute();
	}

	private void execute() {
		executorService.scheduleWithFixedDelay(new Runnable() {
			public void run() {
				doBatchWork();
			}
		},0, 1, TimeUnit.SECONDS);
	}

	@MonitorRequests
	private void doBatchWork() {
		System.out.println("doing batch work...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("done with batch work...");
	}
}
