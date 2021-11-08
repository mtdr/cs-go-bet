package com.edu.cs.go.bet.server.controller;

public interface MatchController {
    String BASE_PATH = "/api";
    String MATCH_CREATE = BASE_PATH + "/match/create";

    String matchCreate();
}
