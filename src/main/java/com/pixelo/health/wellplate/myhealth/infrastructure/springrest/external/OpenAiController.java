package com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external;

import com.pixelo.health.wellplate.framework.spi.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ai")
public class OpenAiController {

    private final OpenAiChatModel openAiChatModel;

    @GetMapping("/gpt")
    public ResultResponse<?> get(
            @RequestParam(value = "message", required = false) String message
    ) {
        UserMessage userMessage = new UserMessage(message);
        Prompt prompt = new Prompt(userMessage);
        ChatResponse call = openAiChatModel.call(prompt);

        return ResultResponse.ok(call.getResult().getOutput().getText());
    }
}
