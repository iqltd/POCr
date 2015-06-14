package com.test.pocr.util;

import static com.test.pocr.util.Util.extractClassFromQualifiedName;
import static com.test.pocr.util.Util.extractPackageFromQualifiedName;
import static com.test.pocr.util.Util.validateQualifiedClassName;

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
		extractPackageFromQualifiedName(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void extractPackageFromQualifiedNameEmpty() {
		final String packageName = extractPackageFromQualifiedName("");
		Assert.assertEquals("", packageName);
	}

	@Test
	public void extractPackageFromQualifiedNameSimple() {
		final String packageName = extractPackageFromQualifiedName(CLASS_NAME);
		Assert.assertEquals("", packageName);
	}

	@Test
	public void extractPackageFromQualifiedNameQualified() {
		final String packageName = extractPackageFromQualifiedName(QUALIFIED_NAME);
		Assert.assertEquals(PACKAGE_NAME, packageName);
	}

	/* extractClassFromQualifiedName */

	@Test(expected = NullPointerException.class)
	public void extractClassFromQualifiedNameNull() {
		extractClassFromQualifiedName(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void extractClassFromQualifiedNameEmpty() {
		extractClassFromQualifiedName("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void extractClassFromQualifiedNameInvalid1() {
		extractClassFromQualifiedName("a£4d,");
	}

	@Test
	public void extractClassFromQualifiedNameValid1() {
		final String className = extractClassFromQualifiedName(CLASS_NAME);
		Assert.assertEquals(CLASS_NAME, className);
	}

	@Test
	public void extractClassFromQualifiedNameValid2() {
		final String className = extractClassFromQualifiedName(QUALIFIED_NAME);
		Assert.assertEquals(CLASS_NAME, className);
	}

	/* validateQualifiedClassNameNull */

	@Test(expected = NullPointerException.class)
	public void validateQualifiedClassNameNull() {
		validateQualifiedClassName(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void validateQualifiedClassNameEmpty() {
		validateQualifiedClassName("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void validateQualifiedClassNameIllegal1() {
		validateQualifiedClassName("a£4d,");
	}

	@Test(expected = IllegalArgumentException.class)
	public void validateQualifiedClassNameIllegal2() {
		validateQualifiedClassName("a£4d,.");
	}

	@Test(expected = IllegalArgumentException.class)
	public void validateQualifiedClassNameIllegal3() {
		validateQualifiedClassName(".a£4d,.");
	}

	@Test(expected = IllegalArgumentException.class)
	public void validateQualifiedClassNameIllegal4() {
		validateQualifiedClassName(" .A");
	}

	@Test(expected = IllegalArgumentException.class)
	public void validateQualifiedClassNameIllegal5() {
		validateQualifiedClassName("..");
	}

	@Test
	public void validateQualifiedClassNameOk1() {
		validateQualifiedClassName(CLASS_NAME);
	}

	@Test
	public void validateQualifiedClassNameOk2() {
		validateQualifiedClassName(QUALIFIED_NAME);
	}

}
