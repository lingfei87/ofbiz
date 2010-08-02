/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.ofbiz.sql;

import org.ofbiz.base.lang.SourceMonitored;

@SourceMonitored
public final class OrderByItem extends Atom {
    public enum Order { DEFAULT, ASCENDING, DESCENDING };
    public enum Nulls { DEFAULT, FIRST, LAST };

    private final Order order;
    private final Nulls nulls;
    private final String functionName;
    private final String fieldName;

    public OrderByItem(Order order, Nulls nulls, String functionName, String fieldName) {
        this.order = order;
        this.nulls = nulls;
        this.functionName = functionName;
        this.fieldName = fieldName;
    }

    public final Order getOrder() {
        return order;
    }

    public final Nulls getNulls() {
        return nulls;
    }

    public final String getFunctionName() {
        return functionName;
    }

    public final String getFieldName() {
        return fieldName;
    }

    public boolean equals(Object o) {
        if (o instanceof OrderByItem) {
            OrderByItem other = (OrderByItem) o;
            return order.equals(other.order) && nulls.equals(other.nulls) && equalsHelper(functionName, other.functionName) && fieldName.equals(other.fieldName);
        } else {
            return false;
        }
    }

    public StringBuilder appendTo(StringBuilder sb) {
        if (functionName != null) sb.append(functionName).append('(');
        sb.append(fieldName);
        if (functionName != null) sb.append(')');
        switch (order) {
            case ASCENDING:
                sb.append(" ASC");
                break;
            case DESCENDING:
                sb.append(" DESC");
                break;
        }
        switch (nulls) {
            case FIRST:
                sb.append(" NULLS FIRST");
                break;
            case LAST:
                sb.append(" NULLS LAST");
                break;
        }
        return sb;
    }
}
