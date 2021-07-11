package com.itmo.goblinslayersystemserver.services.implementation;

import com.itmo.goblinslayersystemserver.dao.RoleDao;
import com.itmo.goblinslayersystemserver.dto.RoleDto;
import com.itmo.goblinslayersystemserver.exceptions.NotFoundException;
import com.itmo.goblinslayersystemserver.dao.enums.RoleEnum;
import com.itmo.goblinslayersystemserver.repositories.RoleRepository;
import com.itmo.goblinslayersystemserver.services.IRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RolesService implements IRolesService {

    @Autowired
    RoleRepository repository;

    @Override
    public RoleDao get(Integer id) {
        try {
            return repository.findById(id).get();
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    @Override
    public RoleDao get(RoleEnum role) {
        return repository.findByName(role);
    }

    @Override
    public RoleDao get(RoleDto role) {
        return repository.findByName(role.getDbRole());
    }

    @Override
    public List<RoleDao> get(List<RoleEnum> rolesEnum) {
        return rolesEnum.stream()
                .map(roleEnum -> get(roleEnum))
                .collect(Collectors.toList());
    }

    @Override
    public List<RoleDao> getByDtoName(List<RoleDto> rolesEnum) {
        return get(rolesEnum.stream()
                .map(RoleDto::getDbRole)
                .collect(Collectors.toList()));
    }
}
