package com.itmo.goblinslayersystemserver.controllers;

import com.itmo.goblinslayersystemserver.models.Contract;
import com.itmo.goblinslayersystemserver.models.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/contracts")
public class ContractsController {

    /**
     * Get запрос серверу для получения списка контрактов из системы
     **/
    @GetMapping(produces = {"application/json"})
    public ArrayList<Contract> getContracts() {

        return new ArrayList<>();
    }

    /**
     * Post запрос серверу для создания списка контрактов в системе
     **/
    @PostMapping(consumes = {"application/json"}, produces = {"application/json"})
    public ArrayList<Contract> createContracts(@RequestBody ArrayList<Contract> contractArrayList) {

        return contractArrayList;
    }

    /**
     * Get запрос серверу для получения контракта из системы по его ID
     **/
    @GetMapping(value = "/{id}", consumes = {"application/json"}, produces = {"application/json"})
    public Contract getContract(@PathVariable Integer id) {

        return new Contract();
    }

    /**
     * Put запрос серверу для обновления контракта в системе по его ID
     **/
    @PutMapping(value = "/{id}", consumes = {"application/json"}, produces = {"application/json"})
    public Contract updateContract(@PathVariable Integer id) {

        return new Contract();
    }

    /**
     * Delete запрос серверу для удаления контракта из системы по его ID
     **/
    @DeleteMapping(value = "/{id}", consumes = {"application/json"}, produces = {"application/json"})
    public String deleteContract(@PathVariable Integer id) {

        return "";
    }
}
