package com.codeincoffee.codereview.common;

public class ResultModule {

	private Module module;

	private int result;
	
	private int limit;
	
	public ResultModule(Module module, int result, int limit) {
		this.module = module;
		this.result = result;
		this.limit = limit;
	}

	public boolean fail() {
		return result > limit;
	}
	
	public String trace() {
		return module.name() + ":" +  module.value();
	}
	
	public String toFailedString() {
		StringBuffer sb = new StringBuffer().append(result)
		.append(" which is out of limit: ").append(limit)
		.append("\nin ").append(trace());
		return sb.toString();
	}
}
