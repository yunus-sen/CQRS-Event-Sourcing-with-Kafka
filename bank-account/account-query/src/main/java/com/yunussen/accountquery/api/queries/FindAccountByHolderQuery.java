package com.yunussen.accountquery.api.queries;

import com.yunussen.cqrscore.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindAccountByHolderQuery extends BaseQuery {
    private String accountHolder;
}
