package com.aracelyjacinto.expenses.config;

import com.sendgrid.SendGrid;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class SendgridConfig {

  private final Environment env;

  public SendgridConfig(Environment env) {
    this.env = env;
  }

  @Bean
  public SendGrid sendGrid() {
    return new SendGrid(env.getProperty("env.sendgrid.apikey"));
  }
}
