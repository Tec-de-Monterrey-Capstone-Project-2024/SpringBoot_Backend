package com.springboot.connectmate.services;

import com.springboot.connectmate.dtos.SuggestionDTO;

import java.util.List;

public interface SuggestionService {
    List<SuggestionDTO> getAllSuggestions();
    SuggestionDTO getSuggestionById(long id);
}
