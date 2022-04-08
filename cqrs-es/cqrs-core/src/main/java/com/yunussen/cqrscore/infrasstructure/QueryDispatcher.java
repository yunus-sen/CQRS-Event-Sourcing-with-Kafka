package com.yunussen.cqrscore.infrasstructure;

import com.yunussen.cqrscore.domain.BaseEntity;
import com.yunussen.cqrscore.queries.BaseQuery;
import com.yunussen.cqrscore.queries.QueryHandlerMethod;

import java.util.List;

public interface QueryDispatcher {
    <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler);

    <U extends BaseEntity> List<U> send(BaseQuery query);
}
