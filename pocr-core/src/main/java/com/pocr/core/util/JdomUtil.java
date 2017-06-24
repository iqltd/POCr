package com.pocr.core.util;

import org.codehaus.plexus.util.xml.Xpp3Dom;
import org.jdom2.DocType;
import org.jdom2.Namespace;

import com.pocr.core.facelet.xhtml.NamespaceEnum;

import java.util.Map;

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

	public static Xpp3Dom createElement(String name, Map<String, String> children) {
        final Xpp3Dom conf = new Xpp3Dom(name);

        for (final Map.Entry<String, String> entry : children.entrySet()) {
            conf.addChild(createElement(entry.getKey(),
                    entry.getValue()));
        }
        return conf;
    }

	public static Xpp3Dom createElement(final String name, final String value) {
		final Xpp3Dom element = new Xpp3Dom(name);
		element.setValue(value);
		return element;
	}
}
