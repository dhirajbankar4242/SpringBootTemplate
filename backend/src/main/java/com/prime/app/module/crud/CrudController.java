package com.prime.app.module.crud;

import com.prime.app.config.exceptions.ApplicationException;
import com.prime.app.config.exceptions.NoRecordFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("censorDetails")
public class CrudController {

    private final CrudService service;
    @GetMapping
    public List<Crud> getAll(){
        return service.getAll();
    }

    @GetMapping("{id}")
    public void getById(@PathVariable String id) throws NoRecordFoundException {

    }

    @PostMapping
    public void save(@RequestBody CrudDto dto) throws ApplicationException {
        service.save(dto);
    }

    @PutMapping("/trigger")
    public void update(@RequestBody CrudDto dto) throws NoRecordFoundException, ApplicationException{
        service.update(dto);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) throws NoRecordFoundException{

    }
}
