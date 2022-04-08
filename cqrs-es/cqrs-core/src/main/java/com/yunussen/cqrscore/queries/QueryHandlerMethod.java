package com.yunussen.cqrscore.queries;

import com.yunussen.cqrscore.domain.BaseEntity;

import java.util.List;

@FunctionalInterface
public interface QueryHandlerMethod<T extends BaseQuery> {
    List<BaseEntity> handle(T query);
}
