package com.learn.springbootkubernetes;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import io.jaegertracing.Configuration;
import io.jaegertracing.Configuration.ReporterConfiguration;
import io.jaegertracing.Configuration.SamplerConfiguration;
import io.jaegertracing.Configuration.SenderConfiguration;
import java.util.Arrays;

@SpringBootApplication
public class SpringbootKubernetesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootKubernetesApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}

		};
	}
	@Bean
    public io.opentracing.Tracer initTracer() {
		Configuration config = new io.jaegertracing.Configuration("java-trace-demo");
        Configuration.SenderConfiguration sender = new Configuration.SenderConfiguration();
        sender.withEndpoint("http://my-jaeger-collector:14268/api/traces"); 
        SamplerConfiguration samplerConfig = new SamplerConfiguration().withType("const").withParam(1);
        ReporterConfiguration reporterConfig = new ReporterConfiguration().withLogSpans(true);
        return config.withSampler(samplerConfig).withReporter(reporterConfig).getTracer();
    }

}
