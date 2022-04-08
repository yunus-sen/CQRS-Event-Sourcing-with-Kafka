package com.yunussen.accountquery.api.queries;

import com.yunussen.accountquery.api.dto.EqualityType;
import com.yunussen.cqrscore.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindAccountWithBalanceQuery extends BaseQuery {
    private EqualityType equalityType;
    private double balance;
}
