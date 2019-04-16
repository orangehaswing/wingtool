package com.orangehaswing.cron.demo;

import com.orangehaswing.cron.CronUtil;

/**
 * 定时任务样例
 */
public class JobMainTest {

	public static void main(String[] args) {
		CronUtil.setMatchSecond(true);
		CronUtil.start(false);
	}
}
