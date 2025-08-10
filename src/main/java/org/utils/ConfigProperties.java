package org.utils;

import org.aeonbits.owner.Config;

@Config.Sources(value = "file:./src/main/resources/config.properties")
public interface ConfigProperties extends Config {
	long TIME_FOR_IMPLICIT_WAIT();
	long TIME_FOR_DEFAULT_WAIT();
	long TIME_FOR_EXPLICIT_WAIT_LOW();
	long TIME_FOR_EXPLICIT_WAIT_HIGH();

	String base_url();
	String login_url();
	String DATA_FILE();
	String DATA_FILE_PATH();

}
