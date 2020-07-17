package com.itmo.goblinslayersystemserver.repositories;

import com.itmo.goblinslayersystemserver.models.QUser;
import com.itmo.goblinslayersystemserver.models.User;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;

public interface UserRepository extends JpaRepository<User, Integer>,
        QuerydslPredicateExecutor<User>,
        QuerydslBinderCustomizer<QUser> {

    User findByUsername(String username);

    @Override
    default void customize(
            QuerydslBindings bindings, QUser root) {
        bindings.bind(String.class)
                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
    }
}
