package com.itmo.goblinslayersystemserver.repositories;

import com.itmo.goblinslayersystemserver.dao.UserDao;
import com.itmo.goblinslayersystemserver.dao.QUserDao;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;

public interface UserRepository extends JpaRepository<UserDao, Integer>,
        QuerydslPredicateExecutor<UserDao>,
        QuerydslBinderCustomizer<QUserDao> {

    UserDao findByUsername(String username);

    @Override
    default void customize(
            QuerydslBindings bindings, QUserDao root) {
        bindings.bind(String.class)
                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
    }
}
