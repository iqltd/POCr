package org.iqltd.pocr.core.util;

import org.junit.Assert;
import org.junit.Test;

public class UtilTest {

	private final static String CLASS_NAME = "ClassName";
	private final static String PACKAGE_NAME = "com.pachet";
	private final static String QUALIFIED_NAME = PACKAGE_NAME + "."
			+ CLASS_NAME;

	/* extractPackageFromQualifiedName */

	@Test(expected = NullPointerException.class)
	public void extractPackageFromQualifiedNameNull() {
		Util.extractPackageFromQualifiedName(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void extractPackageFromQualifiedNameEmpty() {
		final String packageName = Util.extractPackageFromQualifiedName("");
		Assert.assertEquals("", packageName);
	}

	@Test
	public void extractPackageFromQualifiedNameSimple() {
		final String packageName = Util.extractPackageFromQualifiedName(CLASS_NAME);
		Assert.assertEquals("", packageName);
	}

	@Test
	public void extractPackageFromQualifiedNameQualified() {
		final String packageName = Util.extractPackageFromQualifiedName(QUALIFIED_NAME);
		Assert.assertEquals(PACKAGE_NAME, packageName);
	}

	/* extractClassFromQualifiedName */

	@Test(expected = NullPointerException.class)
	public void extractClassFromQualifiedNameNull() {
		Util.extractClassFromQualifiedName(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void extractClassFromQualifiedNameEmpty() {
		Util.extractClassFromQualifiedName("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void extractClassFromQualifiedNameInvalid1() {
		Util.extractClassFromQualifiedName("a£4d,");
	}

	@Test
	public void extractClassFromQualifiedNameValid1() {
		final String className = Util.extractClassFromQualifiedName(CLASS_NAME);
		Assert.assertEquals(CLASS_NAME, className);
	}

	@Test
	public void extractClassFromQualifiedNameValid2() {
		final String className = Util.extractClassFromQualifiedName(QUALIFIED_NAME);
		Assert.assertEquals(CLASS_NAME, className);
	}

	/* validateQualifiedClassNameNull */

	@Test(expected = NullPointerException.class)
	public void validateQualifiedClassNameNull() {
		Util.validateQualifiedClassName(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void validateQualifiedClassNameEmpty() {
		Util.validateQualifiedClassName("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void validateQualifiedClassNameIllegal1() {
		Util.validateQualifiedClassName("a£4d,");
	}

	@Test(expected = IllegalArgumentException.class)
	public void validateQualifiedClassNameIllegal2() {
		Util.validateQualifiedClassName("a£4d,.");
	}

	@Test(expected = IllegalArgumentException.class)
	public void validateQualifiedClassNameIllegal3() {
		Util.validateQualifiedClassName(".a£4d,.");
	}

	@Test(expected = IllegalArgumentException.class)
	public void validateQualifiedClassNameIllegal4() {
		Util.validateQualifiedClassName(" .A");
	}

	@Test(expected = IllegalArgumentException.class)
	public void validateQualifiedClassNameIllegal5() {
		Util.validateQualifiedClassName("..");
	}

	@Test
	public void validateQualifiedClassNameOk1() {
		Util.validateQualifiedClassName(CLASS_NAME);
	}

	@Test
	public void validateQualifiedClassNameOk2() {
		Util.validateQualifiedClassName(QUALIFIED_NAME);
	}

}
