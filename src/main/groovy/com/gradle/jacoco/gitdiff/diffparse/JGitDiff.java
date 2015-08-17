package com.gradle.jacoco.gitdiff.diffparse;

/** Copyright Steve Jin 2013 */
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
 
public class JGitDiff 
{
  public static void main(String[] args) throws Exception
  {
    File gitWorkDir = new File("/home/aahmed/workspace/turn");
    Git git = Git.open(gitWorkDir);
 
    String oldHash = "65f491fac523af374d16a8f7b0d1698e2f33365d";
 
    ObjectId headId = git.getRepository().resolve("HEAD^{tree}");
    ObjectId oldId = git.getRepository().resolve(oldHash + "^{tree}");
 
    ObjectReader reader = git.getRepository().newObjectReader();
     
    CanonicalTreeParser oldTreeIter = new CanonicalTreeParser();
    oldTreeIter.reset(reader, oldId);
    CanonicalTreeParser newTreeIter = new CanonicalTreeParser();
    newTreeIter.reset(reader, headId);
 
    List<DiffEntry> diffs= git.diff()
            .setNewTree(newTreeIter)
            .setOldTree(oldTreeIter)
            .call();
     
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    DiffFormatter df = new DiffFormatter(out);
    df.setRepository(git.getRepository());
 
    for(DiffEntry diff : diffs)
    {
      df.format(diff);
      diff.getOldId();
      String diffText = out.toString("UTF-8");
      System.out.println(diffText);
      out.reset();
    }
  }
}