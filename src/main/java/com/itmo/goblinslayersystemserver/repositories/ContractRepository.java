package com.itmo.goblinslayersystemserver.repositories;

import com.itmo.goblinslayersystemserver.dao.ContractDao;
import com.itmo.goblinslayersystemserver.dao.QContractDao;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;

public interface ContractRepository extends JpaRepository<ContractDao, Integer>,
        QuerydslPredicateExecutor<ContractDao>,
        QuerydslBinderCustomizer<QContractDao> {

    @Override
    default void customize(
            QuerydslBindings bindings, QContractDao root) {
        bindings.bind(String.class)
                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
    }
}
