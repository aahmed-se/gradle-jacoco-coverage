package com.gradle.jacoco.gitdiff.parsing;

import static java.lang.Integer.parseInt;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class Parser {
	private final Coverage.Builder builder = Coverage.Builder.get();

	private HashMap<String, LinkedHashSet<Integer>> coverageInfo = new HashMap<String, LinkedHashSet<Integer>>();

	private final XmlPullParserFactory parserFactory;

	public Parser() throws XmlPullParserException {
		parserFactory = parserFactory();
	}

	private static XmlPullParserFactory parserFactory()
			throws XmlPullParserException {
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		factory.setNamespaceAware(true);
		factory.setValidating(false);

		return factory;
	}

	public Coverage parse(String reportName) throws XmlPullParserException,
	IOException {
		FileInputStream fileInput = new FileInputStream(new File(reportName));
		BufferedInputStream bufferedInput = new BufferedInputStream(fileInput);

		try {
			XmlPullParser parser = parserFactory.newPullParser();
			parser.setInput(bufferedInput, null);

			while (XmlPullParser.END_DOCUMENT != parser.nextToken()) {
				int eventType = parser.getEventType();
				String name = parser.getName();

				switch (eventType) {
				case XmlPullParser.START_TAG:
					if ("package".equalsIgnoreCase(name)) {
						handleParser(parser);
					}
					break;
				}
			}

		} finally {
			bufferedInput.close();
		}
		
		for (Map.Entry<String, LinkedHashSet<Integer>> entry : coverageInfo.entrySet()) {
		    System.out.println(entry.getKey() + " -- " + entry.getValue());

		}

		return builder.build();
	}

	private static boolean isPackageEnd(XmlPullParser parser)
			throws XmlPullParserException {
		return XmlPullParser.END_TAG == parser.getEventType()
				&& "package".equalsIgnoreCase(parser.getName());
	}

	private static boolean isSourceFileEnd(XmlPullParser parser)
			throws XmlPullParserException {
		return XmlPullParser.END_TAG == parser.getEventType()
				&& "sourcefile".equalsIgnoreCase(parser.getName());
	}

	private void handleParser(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		String type = parser.getAttributeValue(null, "name");
		System.out.println("Package Name: " + type);
		while (!isPackageEnd(parser)) {
			parser.nextToken();
			if (parser.getEventType() == XmlPullParser.START_TAG) {
				String name = parser.getName();
				if ("SOURCEFILE".equalsIgnoreCase(name)) {
					handleSourceFile(parser,type);
				}
			}

		}
	}

	private void handleSourceFile(XmlPullParser parser,String packageName)
			throws XmlPullParserException, IOException {
		String type = parser.getAttributeValue(null, "name");
		System.out.println("Sourcefile Name:" + type);

		String scopedName = packageName + "/" + type;
		if (!coverageInfo.containsKey(type)) {
			coverageInfo.put(scopedName, new LinkedHashSet<Integer>());
		}
		while (!isSourceFileEnd(parser)) {
			parser.nextToken();
			if (parser.getEventType() == XmlPullParser.START_TAG) {
				String name = parser.getName();
				if ("Line".equalsIgnoreCase(name)) {
					int lineNum = parseInt(parser.getAttributeValue(null, "nr"));
					int covered = parseInt(parser.getAttributeValue(null, "ci"));

					if (covered > 0) {
						coverageInfo.get(scopedName).add(lineNum);
					}

				}
			}

		}

	}

}