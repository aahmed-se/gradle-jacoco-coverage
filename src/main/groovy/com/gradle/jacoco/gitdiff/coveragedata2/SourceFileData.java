package com.gradle.jacoco.gitdiff.coveragedata2;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import com.gradle.jacoco.gitdiff.coveragedata.util.StringUtil;

public class SourceFileData implements CoverageData {
	
	private String name;

	/**
	 * @param name In the format, "net/sourceforge/cobertura/coveragedata/SourceFileData.java"
	 */
	public SourceFileData(String name) {
		if (name == null)
			throw new IllegalArgumentException(
					"Source file name must be specified.");
		this.name = name;
	}
	
	public String getNormalizedName() {
		String fullNameWithoutExtension;
		int lastDot = this.name.lastIndexOf('.');
		if (lastDot == -1) {
			fullNameWithoutExtension = this.name;
		} else {
			fullNameWithoutExtension = this.name.substring(0, lastDot);
		}

		return StringUtil.replaceAll(fullNameWithoutExtension, "/", ".");
	}
	
	public String getPackageName() {
		int lastSlash = this.name.lastIndexOf('/');
		if (lastSlash == -1) {
			return null;
		}
		return StringUtil.replaceAll(this.name.substring(0, lastSlash), "/",
				".");
	}
	
	public String getBaseName() {
		String fullNameWithoutExtension;
		int lastDot = this.name.lastIndexOf('.');
		if (lastDot == -1) {
			fullNameWithoutExtension = this.name;
		} else {
			fullNameWithoutExtension = this.name.substring(0, lastDot);
		}

		int lastSlash = fullNameWithoutExtension.lastIndexOf('/');
		if (lastSlash == -1) {
			return fullNameWithoutExtension;
		}
		return fullNameWithoutExtension.substring(lastSlash + 1);
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean isValidSourceLineNumber(int lineNumber) {

			Iterator iter = this.children.values().iterator();
			while (iter.hasNext()) {
				ClassData classData = (ClassData) iter.next();
				if (classData.isValidSourceLineNumber(lineNumber))
					return true;
			}
		return false;
	}

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

	public boolean containsInstrumentationInfo() {
		Iterator iter = this.children.values().iterator();
		while (iter.hasNext()) {
			ClassData classData = (ClassData) iter.next();
			if (!classData.containsInstrumentationInfo())
				return false;
		}
		return true;
	}
	
	public SortedSet getClasses() {
		return new TreeSet(this.children.values());

	}

}
