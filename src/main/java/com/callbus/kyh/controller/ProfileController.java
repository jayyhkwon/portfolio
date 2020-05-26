package com.callbus.kyh.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yhkwon on 2020/05/26
 */

@RestController
@RequiredArgsConstructor
public class ProfileController {

    private final Environment env;


    @GetMapping("/profile")
    public String profile() {
        List<String> profiles = Arrays.asList(env.getActiveProfiles());

        return profiles.stream()
                .findAny()
                .orElse("");
    }
}
