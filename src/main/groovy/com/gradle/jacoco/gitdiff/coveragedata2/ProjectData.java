package com.gradle.jacoco.gitdiff.coveragedata2;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import com.gradle.jacoco.gitdiff.coveragedata.PackageData;

public class ProjectData implements CoverageData {
	
	private Map classes = new HashMap();

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

	public SortedSet getPackages() {

		return new TreeSet(this.children.values());

	}

	public Collection getSourceFiles() {
		SortedSet sourceFileDatas = new TreeSet();

		Iterator iter = this.children.values().iterator();
		while (iter.hasNext()) {
			PackageData packageData = (PackageData) iter.next();
			sourceFileDatas.addAll(packageData.getSourceFiles());
		}
		return sourceFileDatas;
	}
	
	public int getNumberOfClasses() {
		return this.classes.size();
	}

}
