/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.infra.metadata.schema.refresher.type;

import org.apache.shardingsphere.infra.metadata.schema.ShardingSphereSchema;
import org.apache.shardingsphere.infra.metadata.schema.builder.SchemaBuilderMaterials;
import org.apache.shardingsphere.infra.metadata.schema.model.IndexMetaData;
import org.apache.shardingsphere.infra.metadata.schema.refresher.SchemaRefresher;
import org.apache.shardingsphere.sql.parser.sql.common.statement.ddl.CreateIndexStatement;

import java.util.Collection;

/**
 * ShardingSphere schema refresher for create index statement.
 */
public final class CreateIndexStatementSchemaRefresher implements SchemaRefresher<CreateIndexStatement> {
    
    @Override
    public void refresh(final ShardingSphereSchema schema, 
                        final Collection<String> routeDataSourceNames, final CreateIndexStatement sqlStatement, final SchemaBuilderMaterials materials) {
        if (null == sqlStatement.getIndex()) {
            return;
        }
        String indexName = sqlStatement.getIndex().getIdentifier().getValue();
        String tableName = sqlStatement.getTable().getTableName().getIdentifier().getValue();
        schema.get(tableName).getIndexes().put(indexName, new IndexMetaData(indexName));
    }
}
