package com.itmo.goblinslayersystemserver.controllers;

import com.itmo.goblinslayersystemserver.dto.ContractCreateDto;
import com.itmo.goblinslayersystemserver.dto.ContractDto;
import com.itmo.goblinslayersystemserver.dto.ContractUpdateDto;
import com.itmo.goblinslayersystemserver.dto.ItemsDto;
import com.itmo.goblinslayersystemserver.models.Contract;
import com.itmo.goblinslayersystemserver.models.enums.AdventurerRank;
import com.itmo.goblinslayersystemserver.models.enums.ContractStatus;
import com.itmo.goblinslayersystemserver.services.IContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(Endpoints.ContractsRestControllerV1)
public class ContractsRestControllerV1 {

    @Autowired
    private IContractService contractService;

    /**
     * Get запрос серверу для получения списка контрактов из системы
     **/
    @GetMapping(produces = {"application/json"})
    public ItemsDto<ContractDto> getContracts(@RequestParam(required = false) String nameContract,
                                              @RequestParam(required = false) Integer customer,
                                              @RequestParam(required = false) Integer executor,
                                              @RequestParam(required = false) AdventurerRank minRank,
                                              @RequestParam(required = false) ContractStatus contractStatus,
                                              @RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "5") int size) {

        Page<Contract> contractsPage = contractService.get(
                nameContract,
                customer,
                executor,
                minRank,
                contractStatus,
                page,
                size
        );

        return new ItemsDto<>(
                contractsPage.getNumber(),
                contractsPage.getTotalElements(),
                contractsPage.getTotalPages(),
                contractsPage
                        .getContent()
                        .stream()
                        .map(ContractDto::new)
                        .collect(Collectors.toList())
        );
    }

    /**
     * Post запрос серверу для создания списка контрактов в системе
     **/
    @PostMapping(consumes = {"application/json"}, produces = {"application/json"})
    public ContractDto createContracts(HttpServletResponse response, @RequestBody ContractCreateDto contract) {
        return new ContractDto(contractService.create(contract));
    }

    /**
     * Get запрос серверу для получения контракта из системы по его ID
     **/
    @GetMapping(value = "/{id}", consumes = {"application/json"}, produces = {"application/json"})
    public ContractDto getContract(HttpServletResponse response, @PathVariable Integer id) {
        return new ContractDto(contractService.get(id));
    }

    /**
     * Put запрос серверу для обновления контракта в системе по его ID
     **/
    @PutMapping(value = "/{id}", consumes = {"application/json"}, produces = {"application/json"})
    public ContractDto updateContract(HttpServletResponse response, @PathVariable Integer id, @RequestBody ContractUpdateDto contract) {
        return new ContractDto(contractService.update(id, contract));
    }
}
