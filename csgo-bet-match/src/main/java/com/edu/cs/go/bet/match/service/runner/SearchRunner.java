package com.edu.cs.go.bet.match.service.runner;

import com.edu.cs.go.bet.api.dto.common.Game;

import java.util.concurrent.CompletableFuture;

/**
 * Фабричный интерфейс генератора сторон игры для переданных участников.
 */
public interface SearchRunner {

    /**
     * @return CompletableFuture с результатом создания игры.
     */
    CompletableFuture<Game> searchRun();
}
