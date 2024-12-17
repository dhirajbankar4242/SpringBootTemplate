package com.prime.app.module.common.service;

import com.prime.app.module.crud.Crud;
import com.prime.app.module.crud.CrudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomId {

    private final CrudRepository crudRepository;
    public String generateCustomId() {

        Optional<Crud> latestCrud = crudRepository.findTopByOrderByIdDesc();

        if (latestCrud.isPresent()) {
            String lastId = latestCrud.get().getId();
            int number = Integer.parseInt(lastId.replace("kosin-", ""));
            return "kosin-" + (number + 1);
        } else {
            return "kosin-1";
        }
    }
}
