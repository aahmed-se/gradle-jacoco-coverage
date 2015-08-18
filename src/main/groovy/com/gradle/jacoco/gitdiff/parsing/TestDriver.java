package com.gradle.jacoco.gitdiff.parsing;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

public class TestDriver {

	public static void main(String[] args) throws XmlPullParserException, IOException {
		Parser temp = new Parser();
		Coverage temp1 = temp.parse("/home/aahmed/workspace/turn/modules/datamine/build/reports/jacoco/codeCoverageReport/codeCoverageReport.xml");
		
		System.out.println(temp1);

	}

}
