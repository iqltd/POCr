package com.pocr.core.util;

import org.jdom2.DocType;
import org.jdom2.Namespace;

import com.pocr.core.facelet.xhtml.NamespaceEnum;

public class JdomUtil {

	private JdomUtil() {
	}

	public static DocType getDoctype() {
		return new DocType("PUBLIC", "-//W3C//DTD XHTML 1.0 Transitional//EN",
				"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd");
	}

	public static Namespace getNamespace(final NamespaceEnum nsEnum) {
		return Namespace.getNamespace(nsEnum.getPrefix(), nsEnum.getUri());
	}

}
