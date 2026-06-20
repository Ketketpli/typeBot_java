package com.katz.typeBot.controller;

import com.katz.typeBot.service.PerfilService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/perfis")
@RequiredArgsConstructor
public class PerfilController {

    @Autowired
    private PerfilService perfilService;
}
