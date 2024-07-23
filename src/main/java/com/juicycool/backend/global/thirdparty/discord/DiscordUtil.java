package com.juicycool.backend.global.thirdparty.discord;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.juicycool.backend.domain.auth.event.SignUpLoggingEvent;
import com.juicycool.backend.global.thirdparty.discord.properties.DiscordProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
@RequiredArgsConstructor
@Slf4j
public class DiscordUtil {

    private final DiscordProperties discordProperties;

    @EventListener
    public void signupLoggingHandler(SignUpLoggingEvent signupLoggingEvent) {
        JsonObject emailField = new JsonObject();
        emailField.add("name", new JsonPrimitive("이메일"));
        emailField.add("value", new JsonPrimitive(signupLoggingEvent.getEmail()));

        JsonObject nameField = new JsonObject();
        nameField.add("name", new JsonPrimitive("이름"));
        nameField.add("value", new JsonPrimitive(signupLoggingEvent.getName()));

        JsonArray fields = new JsonArray();
        fields.add(emailField);
        fields.add(nameField);

        JsonObject embed = new JsonObject();
        embed.add("description", new JsonPrimitive("새로운 회원가입을 시도하였습니다"));
        embed.add("color", new JsonPrimitive(255));
        embed.add("fields", fields);

        JsonObject jsonObject = new JsonObject();
        jsonObject.add("content", new JsonPrimitive("## 회원가입 알림"));
        JsonArray embeds = new JsonArray();
        embeds.add(embed);
        jsonObject.add("embeds", embeds);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<>(jsonObject.toString(), headers);
        log.info(discordProperties.getSignUpURL());
        restTemplate.postForObject(discordProperties.getSignUpURL(), entity, String.class);
    }
}

