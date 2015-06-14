package com.test.pocr.util;

import com.sun.codemodel.JJavaName;

public class Util {

	public static final String POINT = ".";

	private static final String BLANK = "";

	// Not to be instantiated
	private Util() {
		throw new AssertionError();
	}

	/**
	 * Extracts the package name from a given qualified class name.
	 *
	 * @param qualifiedClassName
	 * @return packageName
	 * @throws IllegalArgumentException
	 *             if no valid package name can be extracted
	 * @throws NullPointerException
	 */
	public static String extractPackageFromQualifiedName(
			final String qualifiedClassName) {
		validateQualifiedName(qualifiedClassName);
		String packageName = BLANK;
		if (qualifiedClassName.contains(POINT)) {
			final int delimiterPosition = qualifiedClassName.lastIndexOf(POINT);
			packageName = qualifiedClassName.substring(0, delimiterPosition);
		}
		if (!JJavaName.isJavaPackageName(packageName)) {
			throw new IllegalArgumentException("Illegal package name.");
		}
		return packageName;
	}

	/**
	 * Extracts simple class name from a given qualified name
	 *
	 * @param qualifiedClassName
	 * @return
	 * @throws IllegalArgumentException
	 *             if no valid class name can be extracted
	 * @throws NullPointerException
	 */
	public static String extractClassFromQualifiedName(
			final String qualifiedClassName) {
		validateQualifiedName(qualifiedClassName);
		String className = qualifiedClassName;

		if (qualifiedClassName.contains(POINT)) {
			final int delimiterPosition = qualifiedClassName.lastIndexOf(POINT);
			className = qualifiedClassName.substring(delimiterPosition + 1);
		}
		if (!JJavaName.isJavaIdentifier(className)) {
			throw new IllegalArgumentException("Illegal package name.");
		}
		return className;
	}

	/**
	 * Validates that the argument represents a qualified class name
	 *
	 * @param qualifiedClassName
	 * @throws IllegalArgumentException
	 *             if argument does not represent a qualified class name
	 * @throws NullPointerException
	 */
	public static void validateQualifiedClassName(
			final String qualifiedClassName) {
		extractPackageFromQualifiedName(qualifiedClassName);
		extractClassFromQualifiedName(qualifiedClassName);
	}

	private static void validateQualifiedName(final String qualifiedClassName) {
		if (qualifiedClassName.isEmpty()
				|| qualifiedClassName.startsWith(POINT)
				|| qualifiedClassName.endsWith(POINT)) {
			throw new IllegalArgumentException("Illegal qualified name.");
		}
	}
}
