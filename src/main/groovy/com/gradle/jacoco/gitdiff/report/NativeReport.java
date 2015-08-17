package com.gradle.jacoco.gitdiff.report;

import com.gradle.jacoco.gitdiff.coveragedata.ProjectData;
import com.gradle.jacoco.gitdiff.coveragedata.util.FileFinder;

import java.io.File;

public class NativeReport implements Report {
	private NullReport nullReport;
	private ProjectData projectData;
	private File destinationDir;
	private FileFinder finder;
	private String encoding;
	//private ReportFormatStrategyRegistry formatStrategyRegistry;

	public NativeReport(ProjectData projectData, File destinationDir,
			FileFinder finder, String encoding) {
		this.nullReport = new NullReport();
		this.projectData = projectData;
		this.destinationDir = destinationDir;
		this.finder = finder;
		this.destinationDir = destinationDir;
		this.encoding = encoding;

	}

	public void export(ReportFormat reportFormat) {
		//
	}

	public ReportName getName() {
		return ReportName.COVERAGE_REPORT;
	}

	public Report getByName(ReportName name) {
		if (getName().equals(name)) {
			return this;
		}
		return nullReport;
	}

	public ProjectData getProjectData() {
		return projectData;
	}

	public File getDestinationDir() {
		return destinationDir;
	}

	public FileFinder getFinder() {
		return finder;
	}

	public String getEncoding() {
		return encoding;
	}
}
