package com.gradle.jacoco.gitdiff.parsing;

import static java.lang.Integer.parseInt;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class Parser {
    private final Coverage.Builder builder = Coverage.Builder.get();

    private final XmlPullParserFactory parserFactory;

    public Parser() throws XmlPullParserException {
        parserFactory = parserFactory();
    }

    private static XmlPullParserFactory parserFactory() throws XmlPullParserException {
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setValidating(false);

        return factory;
    }

    public Coverage parse(String reportName) throws XmlPullParserException, IOException {
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
                        skipPackage(parser);

                    } else if ("counter".equalsIgnoreCase(name)) {
                        handleCounter(parser);
                    }
                    break;
                }
            }

        } finally {
            bufferedInput.close();
        }

        return builder.build();
    }

    private static void skipPackage(XmlPullParser parser) throws XmlPullParserException, IOException {
        while (isPackageEnd(parser)) {
            parser.nextToken();
        }
    }

    private static boolean isPackageEnd(XmlPullParser parser) throws XmlPullParserException {
        return XmlPullParser.END_TAG == parser.getEventType() && "package".equalsIgnoreCase(parser.getName());
    }

    private void handleCounter(XmlPullParser parser) {
        String type = parser.getAttributeValue(null, "type");

        int covered = parseInt(parser.getAttributeValue(null, "covered"));
        int missed = parseInt(parser.getAttributeValue(null, "missed"));
        Metric metric = new Metric(covered, missed);

        if ("INSTRUCTION".equalsIgnoreCase(type)) {
            builder.instructions(metric);
        } else if ("LINE".equalsIgnoreCase(type)) {
            builder.lines(metric);
        } else if ("COMPLEXITY".equalsIgnoreCase(type)) {
            builder.branches(metric);
        } else if ("METHOD".equalsIgnoreCase(type)) {
            builder.methods(metric);
        } else if ("CLASS".equalsIgnoreCase(type)) {
            builder.classes(metric);
        }
    }
}