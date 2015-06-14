package com.test.pocr.mvn;

import java.io.File;
import java.util.Collections;
import java.util.Properties;

import javax.naming.OperationNotSupportedException;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.InvocationResult;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;

public class TestMvnInvoker {
	
	private static String GROUP_ID = "groupId";
	private static String ARTIFACT_ID = "artifactId";
	private static String VERSION = "version";
	private static String ARCHETYPE_ARTIFACT_ID = "archetypeArtifactId";
	
	private static String BASE_DIR = "/home/micul/workspaces";
	private static String MVN_HOME = "/usr/local/maven";

	private TestMvnInvoker() throws OperationNotSupportedException {
		throw new OperationNotSupportedException("Utils class. Not to be instantiated.");
	}
	
	public static void testMvnInvoke() {
		InvocationRequest request = new DefaultInvocationRequest();
		request.setGoals( Collections.singletonList( "archetype:generate" ) );
		request.setBaseDirectory(new File(BASE_DIR));
		Properties properties = new Properties();
		properties.setProperty(GROUP_ID, "com.test.pocr");
		properties.setProperty(ARTIFACT_ID, "testgen");
		properties.setProperty(VERSION, "1.0.0");
		properties.setProperty(ARCHETYPE_ARTIFACT_ID, "maven-archetype-webapp");
		request.setProperties(properties);
		
		
		Invoker invoker = new DefaultInvoker();
		invoker.setMavenHome(new File(MVN_HOME));
	
		try {
			InvocationResult result = invoker.execute( request );
			if (result.getExitCode() != 0) {
				System.out.println("Naspa");
			}
		} catch (MavenInvocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
