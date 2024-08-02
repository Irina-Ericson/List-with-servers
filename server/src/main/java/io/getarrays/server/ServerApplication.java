package io.getarrays.server;

import io.getarrays.server.model.Server;
import io.getarrays.server.repo.ServerRepo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static io.getarrays.server.enumeration.Status.SERVER_DOWN;
import static io.getarrays.server.enumeration.Status.SERVER_UP;

import org.springframework.web.filter.CorsFilter;
@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);

	}
	@Bean
	CommandLineRunner run(ServerRepo serverRepo){
		return args ->{
			serverRepo.save(new Server(null, "192.168.1.160", "Ubuntu Linux", "16 GB", "Personal PC",
							"http://localhost:8080/server/image/server_1.jpg", SERVER_UP));
			serverRepo.save(new Server(null, "192.168.1.58", "Ubuntu Linux", "8 GB", "Personal PC",
					"http://localhost:8080/server/image/server_2.jpg", SERVER_DOWN));
			serverRepo.save(new Server(null, "192.168.1.21", "Windows", "16 GB", "Personal PC",
					"http://localhost:8080/server/image/server_3.jpg", SERVER_UP));
			serverRepo.save(new Server(null, "192.168.1.14", "Ubuntu Linux", "32 GB", "Router",
					"http://localhost:8080/server/image/server_4.jpg", SERVER_DOWN));


		};

	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource=new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration=new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Requested-Method", "Access-Control-Requested-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type",
				"Accept", "Jwt-Token", "Authorization", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials",
				"Filename"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}
}
