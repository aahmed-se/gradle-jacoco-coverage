package com.gradle.jacoco.gitdiff.report;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HTMLReportFormatStrategy implements ReportFormatStrategy {
	private static final Logger log = LoggerFactory
			.getLogger(HTMLReportFormatStrategy.class);

	public void save(Report report) {
		NativeReport nativeReport = (NativeReport) report;
		try {
			new HTMLReport(nativeReport.getProjectData(), nativeReport
					.getDestinationDir(), nativeReport.getFinder(),
					nativeReport.getEncoding());
		} catch (Exception e) {
			log.error("Saving HTML report failed.", e);
		}
	}

	public ReportFormat getName() {
		return ReportFormat.HTML;
	}
}
