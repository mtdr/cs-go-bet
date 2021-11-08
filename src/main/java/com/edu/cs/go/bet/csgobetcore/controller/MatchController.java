package com.edu.cs.go.bet.csgobetcore.controller;

public interface MatchController {
    String BASE_PATH = "/api";
    String MATCH_CREATE = BASE_PATH + "/match/create";

    String matchCreate();
}
