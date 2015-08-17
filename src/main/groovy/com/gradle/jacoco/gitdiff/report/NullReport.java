package com.gradle.jacoco.gitdiff.report;

public class NullReport implements Report {

	public void export(ReportFormat reportFormat) {
		//no action performed
	}

	public ReportName getName() {
		return ReportName.NULL_REPORT;
	}

	public Report getByName(ReportName name) {
		return this;
	}
}
