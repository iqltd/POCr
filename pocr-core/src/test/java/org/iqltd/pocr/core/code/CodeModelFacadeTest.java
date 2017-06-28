package org.iqltd.pocr.core.code;

import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CodeModelFacadeTest {

    private static final String CLASS_NAME = "test.Class";
    private static final String FIELD_NAME = "field";
    private static final String SETTER_NAME = "setField";
    private static final String GETTER_NAME = "getField";

    private CodeModelFacade code;

    @Before
    public void init() {
        code = new CodeModelFacade(CLASS_NAME);
    }

    @Test
    public void addField_nominal() {
        code.addField(FIELD_NAME, Integer.class);

        Assert.assertTrue(getBean().fields().containsKey(FIELD_NAME));
    }

    private JDefinedClass getBean() {
        return code.getCodeModel()._getClass(CLASS_NAME);
    }

    @Test
    public void addGetter_nominal() {
        code.addGetter(FIELD_NAME, String.class);

        Assert.assertNotNull(getBean().getMethod(GETTER_NAME, new JType[0]));
    }

    @Test
    public void addSetter_nominal() {
        code.addSetter(FIELD_NAME, int.class);

        JType[] signature = { code.getCodeModel().INT };
        Assert.assertNotNull(getBean().getMethod(SETTER_NAME, signature));
    }
}
