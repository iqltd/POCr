package com.pocr.core.application;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.pocr.core.exception.PocrException;
import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.InvocationResult;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;

public class ApplicationDeployer {

	private ApplicationDeployer() {
	}

	// TODO get it from the environment variables
	 public static final String M2_HOME = "/usr/share/maven";
//	public static final String M2_HOME = System.getenv("M2_HOME");

	public static void deployApplication(final File outputFolder) {
		final InvocationRequest request = new DefaultInvocationRequest();
		final List<String> goals = new ArrayList<String>();
		goals.add("clean");
		goals.add("install");
		goals.add("wildfly:deploy");

		request.setGoals(goals);
		request.setBaseDirectory(outputFolder);

		final Invoker invoker = new DefaultInvoker();
		invoker.setMavenHome(new File(M2_HOME));

		try {
			final InvocationResult result = invoker.execute(request);
			if (result.getExitCode() != 0) {
				throw new PocrException("Problem occurred at deployment. Code: " + result.getExitCode());
			}
		} catch (final MavenInvocationException e) {
			throw new PocrException("Problem occurred at deployment", e);
		}
	}

}
