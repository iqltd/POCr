package org.iqltd.pocr.core;


import org.iqltd.pocr.core.application.ApplicationDeployer;
import org.iqltd.pocr.core.application.ApplicationGenerator;
import org.iqltd.pocr.core.dto.FormDto;
import org.iqltd.pocr.core.facelet.JsfApplicationBuilder;

import java.io.File;
import java.io.IOException;

public class Pocr {

    public void generateAndDeployApplication(final String appName, final FormDto form)
            throws IOException, ClassNotFoundException {

        final JsfApplicationBuilder builder = new JsfApplicationBuilder(appName);
        builder.addForm(form);

        ApplicationGenerator generator = new ApplicationGenerator(builder.build());
        final File outputFolder = generator.generateApplication();

        ApplicationDeployer deployer = new ApplicationDeployer(outputFolder);
        deployer.deployApplication();
    }

}
