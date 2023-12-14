/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.component.cassandra.integration;


import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.literal;

import java.util.Arrays;
import java.util.List;

import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.update.Update;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cassandra.CassandraConstants;
import org.apache.camel.component.cassandra.springboot.BaseCassandra;
import org.apache.camel.spring.boot.CamelAutoConfiguration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;


@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@CamelSpringBootTest
@SpringBootTest(
    classes = {
        CamelAutoConfiguration.class,
        CassandraComponentProducerUnpreparedIT.class,
        CassandraComponentProducerUnpreparedIT.TestConfiguration.class
    }
)
@DisabledIfSystemProperty(named = "ci.env.name", matches = "github.com", disabledReason = "Disabled on GH Action due to Docker limit")
public class CassandraComponentProducerUnpreparedIT extends BaseCassandra {

    static final String CQL = "insert into camel_user(login, first_name, last_name) values (?, ?, ?)";
    static final String NO_PARAMETER_CQL = "select login, first_name, last_name from camel_user";

    
    
    
    @Produce("direct:input")
    ProducerTemplate producerTemplate;

    @Produce("direct:inputNoParameter")
    ProducerTemplate noParameterProducerTemplate;
   

    @Test
    public void testRequestUriCql() {
        producerTemplate.requestBody(Arrays.asList("w_jiang", "Willem", "Jiang"));

        ResultSet resultSet = getSession()
                .execute(String.format("select login, first_name, last_name from camel_user where login = '%s'", "w_jiang"));
        Row row = resultSet.one();
        assertNotNull(row);
        assertEquals("Willem", row.getString("first_name"));
        assertEquals("Jiang", row.getString("last_name"));
    }

    @Test
    public void testRequestNoParameterNull() {
        Object response = noParameterProducerTemplate.requestBody(null);

        assertNotNull(response);
        assertInstanceOf(List.class, response);
    }

    @Test
    public void testRequestNoParameterEmpty() {
        Object response = noParameterProducerTemplate.requestBody(null);

        assertNotNull(response);
        assertInstanceOf(List.class, response);
    }

    @Test
    public void testRequestMessageCql() {
        producerTemplate.requestBodyAndHeader(new Object[] { "Claus 2", "Ibsen 2", "c_ibsen" }, CassandraConstants.CQL_QUERY,
                "update camel_user set first_name=?, last_name=? where login=?");

        ResultSet resultSet = getSession()
                .execute(String.format("select login, first_name, last_name from camel_user where login = '%s'", "c_ibsen"));
        Row row = resultSet.one();
        assertNotNull(row);
        assertEquals("Claus 2", row.getString("first_name"));
        assertEquals("Ibsen 2", row.getString("last_name"));
    }

    /**
     * Test with incoming message containing a header with RegularStatement.
     */
    @Test
    public void testRequestMessageStatement() {
        Update update = QueryBuilder.update("camel_user")
                .setColumn("first_name", literal("Claus 2"))
                .setColumn("last_name", literal("Ibsen 2"))
                .whereColumn("login").isEqualTo(literal("c_ibsen"));
        producerTemplate.requestBodyAndHeader(null, CassandraConstants.CQL_QUERY, update.build());

        ResultSet resultSet = getSession()
                .execute(String.format("select login, first_name, last_name from camel_user where login = '%s'", "c_ibsen"));
        Row row = resultSet.one();
        assertNotNull(row);
        assertEquals("Claus 2", row.getString("first_name"));
        assertEquals("Ibsen 2", row.getString("last_name"));
    }


    // *************************************
    // Config
    // *************************************

    @Configuration
    public class TestConfiguration {

        @Bean
        public RouteBuilder routeBuilder() {
            return new RouteBuilder() {
                @Override
                public void configure() {

                    from("direct:input")
                            .to(String.format("cql://%s/%s?cql=%s&prepareStatements=false", getUrl(), KEYSPACE_NAME, CQL));
                    from("direct:inputNoParameter").to(
                            String.format("cql://%s/%s?cql=%s&prepareStatements=false", getUrl(), KEYSPACE_NAME, NO_PARAMETER_CQL));
                }
            };
        }
    }
}
