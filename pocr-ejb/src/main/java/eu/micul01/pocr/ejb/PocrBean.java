package eu.micul01.pocr.ejb;

import java.io.File;
import java.io.IOException;

import javax.ejb.Stateless;

import com.pocr.core.application.ApplicationDeployer;
import com.pocr.core.dto.FormDto;
import com.pocr.core.facelet.JsfApplicationBuilder;

import eu.micul01.pocr.converter.Entity2Dto;
import eu.micul01.pocr.entity.ApplicationEntity;
import eu.micul01.pocr.entity.FormEntity;

@Stateless(name = "pocrBean", mappedName = "pocrBean")
public class PocrBean {

	public void deployApplication(final ApplicationEntity app)
			throws IOException, ClassNotFoundException {

		final JsfApplicationBuilder builder = new JsfApplicationBuilder(
				app.getName());

		final FormEntity form = app.getForms().get(0);

		final FormDto formDto = Entity2Dto.convert2FormDto(form);
		builder.addForm(formDto);

		final File outputFolder = builder.getGenerator().generateApplication();
		ApplicationDeployer deployer = new ApplicationDeployer(outputFolder);
		deployer.deployApplication();

	}
}
