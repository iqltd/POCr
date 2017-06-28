package com.pocr.core;


import com.pocr.core.application.ApplicationDeployer;
import com.pocr.core.application.ApplicationGenerator;
import com.pocr.core.dto.FormDto;
import com.pocr.core.facelet.JsfApplicationBuilder;

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
