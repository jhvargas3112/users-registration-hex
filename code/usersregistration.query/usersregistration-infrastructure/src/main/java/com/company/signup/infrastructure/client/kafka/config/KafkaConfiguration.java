package com.company.signup.infrastructure.client.kafka.config;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
public class KafkaConfiguration {

  private Bootstrap bootstrap;

  private Linger linger;

  private Batch batch;

  private Buffer buffer;

  private Key key;

  private Value value;

  @ConfigurationProperties(prefix = "bootstrap.servers")
  public static class Bootstrap {
    @NotBlank
    @Getter
    @Setter
    @org.springframework.beans.factory.annotation.Value("${bootstrap.servers}")
    private String servers;

  }

  @org.springframework.beans.factory.annotation.Value("${acks}")
  private Integer acks;

  @ConfigurationProperties(prefix = "linger")
  public static class Linger {

    @NotBlank
    @Getter
    @Setter
    private Integer ms;

  }

  @org.springframework.beans.factory.annotation.Value("${retries}")
  private Integer retries;

  @ConfigurationProperties(prefix = "batch")
  public static class Batch {

    @NotBlank
    @Getter
    @Setter
    private Long size;

  }

  @ConfigurationProperties(prefix = "buffer")
  public static class Buffer {

    @NotBlank
    @Getter
    @Setter
    private Long memory;

  }

  @ConfigurationProperties(prefix = "key")
  public static class Key {

    @NotBlank
    @Getter
    @Setter
    private String serializer;

  }

  @ConfigurationProperties(prefix = "value")
  public static class Value {

    @NotBlank
    @Getter
    @Setter
    private String serializer;

  }

}
