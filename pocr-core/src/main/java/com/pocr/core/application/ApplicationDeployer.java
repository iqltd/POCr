package com.pocr.core.application;

import com.pocr.core.exception.PocrException;
import com.pocr.core.util.Configuration;
import org.apache.maven.shared.invoker.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static com.pocr.core.constants.MavenConstants.Goal.*;

public class ApplicationDeployer {

	private final File sourceFolder;

	public ApplicationDeployer(final File sourceFolder) {
		this.sourceFolder = sourceFolder;
	}

	public void deployApplication() {
		try {
			final Invoker invoker = getInvoker();
			final InvocationResult result = invoker.execute(getInvocationRequest());
			if (result.getExitCode() != 0) {
				throw new PocrException("Problem occurred at deployment. Maven invoker result code: "
                        + result.getExitCode());
			}
		} catch (final MavenInvocationException e) {
			throw new PocrException("Problem occurred at deployment", e);
		}
	}

	private Invoker getInvoker() {
		final Invoker invoker = new DefaultInvoker();
		invoker.setMavenHome(new File(Configuration.MAVEN_HOME.value));
		return invoker;
	}

	private InvocationRequest getInvocationRequest() {
		final InvocationRequest request = new DefaultInvocationRequest();
		request.setGoals(getGoals());
		request.setBaseDirectory(sourceFolder);
		return request;
	}

	private static List<String> getGoals() {
		String [] goals = {CLEAN, PACKAGE, DEPLOY};
		return Arrays.asList(goals);
	}

}
