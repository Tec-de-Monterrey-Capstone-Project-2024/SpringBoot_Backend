package com.springboot.connectmate.repositories;

import com.springboot.connectmate.dtos.SuggestionDTO;
import org.springframework.stereotype.Component;

@Component
public class SuggestionRepository {
    public void save(SuggestionDTO suggestion){
        System.out.println("Simulating the saving of the suggestion in the database (DB): " + suggestion.getDescription());
        System.out.println(suggestion.getId());
        System.out.println(suggestion.getDescription());
        System.out.println(suggestion.getStatus());
    }

    public SuggestionDTO updateSuggestion(long id, SuggestionDTO suggestion){


        return suggestion;
    }

    public SuggestionDTO findByID(long id){
        SuggestionDTO response = new SuggestionDTO();
        response.setId(id);
        response.setDetails("Not enough people on virtual floor.");
        response.setDescription("http://localhost:8080/api/suggestion/" + id);

        return response;
    }

}
