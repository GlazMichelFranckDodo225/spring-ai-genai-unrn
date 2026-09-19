package com.dgmf.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenAIController {
    // private final OpenAiChatModel chatModel;
    private final ChatClient chatClient;

    public OpenAIController(OpenAiChatModel chatModel) {
        // this.chatModel = chatModel;
        this.chatClient = ChatClient.create(chatModel);
    }

    @GetMapping("/api/{message}")
    // public String getAnswer(@PathVariable String message) {
    public ResponseEntity<String> getAnswer(@PathVariable String message) {
        // String response = chatModel.call(message);
        String response = chatClient
                .prompt(message)
                .call()
                .content();

        // return response;
        // return "With ChatClient : " + response;
        return ResponseEntity.ok(response);
    }
}
