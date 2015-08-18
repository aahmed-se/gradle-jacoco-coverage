package com.gradle.jacoco.gitdiff.coveragedata2;

public class LineData implements CoverageData{

	private boolean isCovered;
	private int lineNum;
	
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
	
	public int getLineNumber() {
		return lineNum;
	}

	
	public boolean isCovered() {
		return isCovered;
	}
}
