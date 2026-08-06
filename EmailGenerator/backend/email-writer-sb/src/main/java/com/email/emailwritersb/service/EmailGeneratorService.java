package com.email.emailwritersb.service;

import com.email.emailwritersb.model.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.Map;

@Service
public class EmailGeneratorService{


    private final WebClient webClient;

    @Value("${gemini.api.url}")
    private String geminiAPIUrl;

    @Value("${gemini.api.key}")
    private String geminiAPIKey;


    public EmailGeneratorService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public String generateEmailReply(EmailRequest emailRequest){
        // Build the prompt
        String prompt = buildPrompt(emailRequest);


        //Craft a request
        Map<String, Object> requestBody = Map.of(
                "contents", new Object[]{
                        Map.of("parts", new Object[]{
                                Map.of("text",prompt)
                        })
                }
        );
        // Do request and get response

        String response = webClient.post()
                .uri(geminiAPIUrl+geminiAPIKey)
                .header("Content-Type","application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        // extract the response and return the extracted response


        return extractResponseContent(response);




    }

    private String extractResponseContent(String response) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response);
            return rootNode.path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text")
                    .asString();


        } catch (Exception e) {
            return "Error processing request: "+ e.getMessage();
        }
    }

    private String buildPrompt(EmailRequest emailRequest) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("Generate a professional email reply for the following email content. " +
                "Please dont generate a subject line. ");

        if(emailRequest.getTone() != null && !emailRequest.getTone().isEmpty()){
            prompt.append("Use a ").append(emailRequest.getTone()).append(" tone. ");
        }

        prompt.append("\n Original email : \n").append(emailRequest.getEmailContent());
        return prompt.toString();
    }

}


/**
 * In Java Spring WebFlux programming, bodyToMono is a command that tells your application to take the raw body of an HTTP request or response and convert it into a single, asynchronous object.
 *
 * Medium
 *  +1
 * To understand it easily, break the name down into two simple parts: Body to Mono.
 * 1. What is the "Body"?
 * The Body is the actual data container sent over the internet.
 *
 * Medium
 *  +1
 * When someone sends an API request to your server, the data they send (like a JSON object containing user details) is the request body.
 * When you call an external API, the data it sends back to you is the response body.
 *
 * Medium
 *  +4
 * 2. What is a "Mono"?
 * In reactive programming, a Mono is like a promise, a voucher, or a tracking ID for a package. Instead of making your application
 * stop and freeze while waiting for data to download over a slow network, the system immediately gives you a Mono wrapper.
 * This wrapper promises: "I don't have the data right now, but I will give you zero or one item as soon as it finishes downloading."
 *
 *
 * Putting It Together: An Analogy
 * Imagine you order a single custom pizza online:
 * Without bodyToMono (Traditional Blocking): You drive to the restaurant, stand at the counter, and freeze completely.
 * You cannot check your phone or talk to anyone until the pizza is baked and handed to you.
 *
 * With bodyToMono (Reactive Non-blocking): You place the order online. The website immediately hands you a digital receipt (the Mono).
 * You can go clean your room, watch a movie, or work. When the pizza arrives (the Body data), the receipt automatically updates,
 * and you eat it.
 *
 *
 * Use bodyToMono if you are expecting one single item (like a specific User, a Product description, or a single status message)
 * or nothing at all.
 * Use bodyToFlux if you are expecting a stream of multiple items (like a whole list of users or a continuous feed of data).
 */