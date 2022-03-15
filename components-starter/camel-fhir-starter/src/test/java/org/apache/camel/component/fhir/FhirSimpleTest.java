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
package org.apache.camel.component.fhir;

import java.util.UUID;
import ca.uhn.fhir.rest.api.MethodOutcome;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.fhir.internal.FhirApiCollection;
import org.apache.camel.component.fhir.internal.FhirCreateApiMethod;
import org.apache.camel.spring.boot.CamelAutoConfiguration;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test simple scenario, without custom component configuration
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@CamelSpringBootTest
@SpringBootTest(
        classes = {
                CamelAutoConfiguration.class,
                FhirSimpleTest.class,
                FhirSimpleTest.TestConfiguration.class,
                FhirServer.class,
        }
)
public class FhirSimpleTest extends AbstractFhirTestSupport {

    private static final String PATH_PREFIX = FhirApiCollection.getCollection().getApiName(FhirCreateApiMethod.class).getName();
    private static final String GIVEN_NAME = UUID.randomUUID().toString();
    private static final String FAMILY_NAME = UUID.randomUUID().toString();

    @BeforeEach
    @Override
    public void cleanFhirServerState() {
        // NO-OP
    }

    @Test
    public void testCreateResource() throws Exception {
        Patient patient = new Patient().addName(new HumanName().addGiven(GIVEN_NAME).setFamily(FAMILY_NAME));

        MethodOutcome result = requestBody("direct://RESOURCE", patient);

        assertNotNull(result, "resource result");
        assertTrue(result.getCreated());
    }

    @Configuration
    public class TestConfiguration {
        @Bean
        public RouteBuilder routeBuilder() {
            return new RouteBuilder() {
                @Override
                public void configure() {
                    from("direct://RESOURCE")
                            .to("fhir://" + PATH_PREFIX + "/resource?inBody=resource&serverUrl=" + service.getServiceBaseURL()
                                    + "&fhirVersion=R4");
                }
            };
        }
    }
}
