package com.gradle.jacoco.gitdiff.coveragedata2;

public class ClassData implements CoverageData{
	
	private boolean containsInstrumentationInfo = false;
	
	private String name;

	@Override
	public double getLineCoverageRate() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumberOfCoveredLines() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumberOfValidLines() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public boolean isValidSourceLineNumber(int lineNumber) {
		return children.containsKey(Integer.valueOf(lineNumber));
	}
	
	public String getBaseName() {
		int lastDot = this.name.lastIndexOf('.');
		if (lastDot == -1) {
			return this.name;
		}
		return this.name.substring(lastDot + 1);
	}

	public boolean containsInstrumentationInfo() {
		return this.containsInstrumentationInfo;
	}
}
