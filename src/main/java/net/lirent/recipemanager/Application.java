package net.lirent.recipemanager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

/**
 * Spring Boot application
 * @author Lirent
 */

@SpringBootApplication
@ComponentScan(basePackages = {"net.lirent.recipemanager"})
@Slf4j
public class Application {
	private static final String VERSION = Optional
			.ofNullable(Application.class.getPackage().getImplementationVersion()).orElse("SNAPSHOT");
	private static final String UNKNOWN = "unknown";
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		log.info("##### Started RecipeApplication version {} #####", VERSION);

		Properties prop = new Properties();
		try (InputStream is = Application.class.getResourceAsStream("/BOOT-INF/classes/git.properties")) {
			prop.load(is);

			String branch = prop.getProperty("git.branch", UNKNOWN);
			String versio = prop.getProperty("git.build.version", UNKNOWN);
			String abbrev = prop.getProperty("git.commit.id.abbrev", UNKNOWN);
			log.info("##### Commit: version={}, branch={}, short={}", versio, branch, abbrev);
		} catch (Exception e) {
			// nop
		}
	}

}
