package com.edu.cs.go.bet.csgobetcore.controller.impl;

import com.edu.cs.go.bet.csgobetcore.controller.MatchController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatchControllerImpl implements MatchController {

    @Override
    @PostMapping(MATCH_CREATE)
    public String matchCreate() {
        return null;
    }
}
