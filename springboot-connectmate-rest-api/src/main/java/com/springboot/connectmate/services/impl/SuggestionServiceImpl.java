package com.springboot.connectmate.services.impl;

import com.springboot.connectmate.dtos.SuggestionDTO;
import com.springboot.connectmate.services.SuggestionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SuggestionServiceImpl implements SuggestionService{
    public List<SuggestionDTO> getAllSuggestions(){
        List<SuggestionDTO> suggestions = new ArrayList<>();
        suggestions.add(new SuggestionDTO());

        return suggestions;
    }

    public SuggestionDTO getSuggestionById(long id){
       return new SuggestionDTO();
    }
}
