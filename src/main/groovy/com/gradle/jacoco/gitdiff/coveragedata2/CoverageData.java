package com.gradle.jacoco.gitdiff.coveragedata2;

import java.util.HashMap;
import java.util.Map;

import com.gradle.jacoco.gitdiff.coveragedata2.CoverageData;

public interface CoverageData {
	
	Map<Object, CoverageData> children = new HashMap<Object, CoverageData>();
	
	double getLineCoverageRate();

	int getNumberOfCoveredLines();

	int getNumberOfValidLines();
		
}
