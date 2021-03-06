package com.orangehaswing.core.test.swing;

import com.orangehaswing.core.lang.Console;
import com.orangehaswing.core.swing.clipboard.ClipboardListener;
import com.orangehaswing.core.swing.clipboard.ClipboardUtil;
import org.junit.Ignore;
import org.junit.Test;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.Transferable;

public class ClipboardMonitorTest {

	@Test
	@Ignore
	public void monitorTest() {
		// 第一个监听
		ClipboardUtil.listen(new ClipboardListener() {
			
			@Override
			public Transferable onChange(Clipboard clipboard, Transferable contents) {
				Object object = ClipboardUtil.getStr(contents);
				Console.log("1# {}", object);
				return contents;
			}
			
		}, false);
		
		// 第二个监听
		ClipboardUtil.listen(new ClipboardListener() {
			
			@Override
			public Transferable onChange(Clipboard clipboard, Transferable contents) {
				Object object = ClipboardUtil.getStr(contents);
				Console.log("2# {}", object);
				return contents;
			}
			
		});

	}
}
