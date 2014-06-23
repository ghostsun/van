package com.sunan.van.regedit.client;

import java.util.List;

public class TestCallback implements SubCallback {

	@Override
	public void execute(String dataId, List<String> pubList) {
		System.out.println("dataId: " + dataId);
		for(String pub:pubList){
			System.out.println("--" + pub);
		}

	}

}
