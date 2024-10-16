package com.juicycool.backend.global.thirdparty.discord.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties("discord")
public class DiscordProperties {
    private final String signUpURL;
    private final String errorURL;
}
