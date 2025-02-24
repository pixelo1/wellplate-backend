package com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAiConfig {

    @Value("${open-ai.api.key}")
    private String apiKey;

    @Bean
    public OpenAiChatModel openAiChatModel() {
        return OpenAiChatModel.builder()
                .openAiApi(openAiApi())
                .defaultOptions(openAiChatOptions())
                .build();
    }

    private OpenAiApi openAiApi() {
        return OpenAiApi.builder()
                .apiKey(apiKey)
                .build();
    }

    private OpenAiChatOptions openAiChatOptions() {
        return OpenAiChatOptions.builder()
                .model(OpenAiApi.ChatModel.GPT_4_O)
                .temperature(0.5)
                .maxTokens(100)
                .build();
    }
}
