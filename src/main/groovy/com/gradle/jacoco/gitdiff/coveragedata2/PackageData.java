package com.gradle.jacoco.gitdiff.coveragedata2;

import java.util.Collection;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

import com.gradle.jacoco.gitdiff.coveragedata.ClassData;
import com.gradle.jacoco.gitdiff.coveragedata.SourceFileData;

public class PackageData implements CoverageData {

	private String name;

	public String getName() {
		return this.name;
	}

	public String getSourceFileName() {
		return this.name.replace('.', '/');
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

	public Collection getSourceFiles() {
		SortedMap sourceFileDatas = new TreeMap();

		Iterator iter = this.children.values().iterator();
		while (iter.hasNext()) {
			ClassData classData = (ClassData) iter.next();
			String sourceFileName = classData.getSourceFileName();
			SourceFileData sourceFileData = (SourceFileData) sourceFileDatas
					.get(sourceFileName);
			if (sourceFileData == null) {
				sourceFileData = new SourceFileData(sourceFileName);
				sourceFileDatas.put(sourceFileName, sourceFileData);
			}
			sourceFileData.addClassData(classData);
		}

		return sourceFileDatas.values();
	}
	
	public int getNumberOfChildren() {
		return this.children.size();

	}

}
