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
package org.apache.camel.component.azure.cosmosdb.springboot;

import java.util.List;
import javax.annotation.Generated;
import com.azure.cosmos.ConsistencyLevel;
import com.azure.cosmos.CosmosAsyncClient;
import com.azure.cosmos.models.ChangeFeedProcessorOptions;
import com.azure.cosmos.models.CosmosQueryRequestOptions;
import com.azure.cosmos.models.PartitionKey;
import com.azure.cosmos.models.ThroughputProperties;
import org.apache.camel.component.azure.cosmosdb.CosmosDbComponent;
import org.apache.camel.component.azure.cosmosdb.CosmosDbConfiguration;
import org.apache.camel.component.azure.cosmosdb.CosmosDbOperationsDefinition;
import org.apache.camel.spring.boot.ComponentConfigurationPropertiesCommon;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Azure Cosmos DB is Microsofts globally distributed, multi-model database
 * service for operational and analytics workloads. It offers multi-mastering
 * feature by automatically scaling throughput, compute, and storage. This
 * component interacts with Azure CosmosDB through Azure SQL API
 * 
 * Generated by camel-package-maven-plugin - do not edit this file!
 */
@Generated("org.apache.camel.springboot.maven.SpringBootAutoConfigurationMojo")
@ConfigurationProperties(prefix = "camel.component.azure-cosmosdb")
public class CosmosDbComponentConfiguration
        extends
            ComponentConfigurationPropertiesCommon {

    /**
     * Whether to enable auto configuration of the azure-cosmosdb component.
     * This is enabled by default.
     */
    private Boolean enabled;
    /**
     * Sets the flag to enable client telemetry which will periodically collect
     * database operations aggregation statistics, system information like
     * cpu/memory and send it to cosmos monitoring service, which will be
     * helpful during debugging. DEFAULT value is false indicating this is opt
     * in feature, by default no telemetry collection.
     */
    private Boolean clientTelemetryEnabled = false;
    /**
     * The component configurations. The option is a
     * org.apache.camel.component.azure.cosmosdb.CosmosDbConfiguration type.
     */
    private CosmosDbConfiguration configuration;
    /**
     * Enables connections sharing across multiple Cosmos Clients. The default
     * is false. When you have multiple instances of Cosmos Client in the same
     * JVM interacting to multiple Cosmos accounts, enabling this allows
     * connection sharing in Direct mode if possible between instances of Cosmos
     * Client. Please note, when setting this option, the connection
     * configuration (e.g., socket timeout config, idle timeout config) of the
     * first instantiated client will be used for all other client instances.
     */
    private Boolean connectionSharingAcrossClientsEnabled = false;
    /**
     * Sets the consistency levels supported for Azure Cosmos DB client
     * operations in the Azure Cosmos DB service. The requested ConsistencyLevel
     * must match or be weaker than that provisioned for the database account.
     * Consistency levels by order of strength are STRONG, BOUNDED_STALENESS,
     * SESSION and EVENTUAL. Refer to consistency level documentation for
     * additional details:
     * https://docs.microsoft.com/en-us/azure/cosmos-db/consistency-levels
     */
    private ConsistencyLevel consistencyLevel = ConsistencyLevel.SESSION;
    /**
     * Sets the container partition key path.
     */
    private String containerPartitionKeyPath;
    /**
     * Sets the boolean to only return the headers and status code in Cosmos DB
     * response in case of Create, Update and Delete operations on CosmosItem.
     * In Consumer, it is enabled by default because of the ChangeFeed in the
     * consumer that needs this flag to be enabled and thus is shouldn't be
     * overridden. In Producer, it advised to disable it since it reduces the
     * network overhead
     */
    private Boolean contentResponseOnWriteEnabled = true;
    /**
     * Inject an external CosmosAsyncClient into the component which provides a
     * client-side logical representation of the Azure Cosmos DB service. This
     * asynchronous client is used to configure and execute requests against the
     * service. The option is a com.azure.cosmos.CosmosAsyncClient type.
     */
    private CosmosAsyncClient cosmosAsyncClient;
    /**
     * Sets if the component should create Cosmos container automatically in
     * case it doesn't exist in Cosmos database
     */
    private Boolean createContainerIfNotExists = false;
    /**
     * Sets if the component should create Cosmos database automatically in case
     * it doesn't exist in Cosmos account
     */
    private Boolean createDatabaseIfNotExists = false;
    /**
     * Sets the Azure Cosmos database endpoint the component will connect to.
     */
    private String databaseEndpoint;
    /**
     * Sets the flag to enable writes on any regions for geo-replicated database
     * accounts in the Azure Cosmos DB service. When the value of this property
     * is true, the SDK will direct write operations to available writable
     * regions of geo-replicated database account. Writable regions are ordered
     * by PreferredRegions property. Setting the property value to true has no
     * effect until EnableMultipleWriteRegions in DatabaseAccount is also set to
     * true. DEFAULT value is true indicating that writes are directed to
     * available writable regions of geo-replicated database account.
     */
    private Boolean multipleWriteRegionsEnabled = true;
    /**
     * Sets the preferred regions for geo-replicated database accounts. For
     * example, East US as the preferred region. When EnableEndpointDiscovery is
     * true and PreferredRegions is non-empty, the SDK will prefer to use the
     * regions in the container in the order they are specified to perform
     * operations.
     */
    private List<String> preferredRegions;
    /**
     * Sets whether to allow for reads to go to multiple regions configured on
     * an account of Azure Cosmos DB service. DEFAULT value is true. If this
     * property is not set, the default is true for all Consistency Levels other
     * than Bounded Staleness, The default is false for Bounded Staleness. 1.
     * endpointDiscoveryEnabled is true 2. the Azure Cosmos DB account has more
     * than one region
     */
    private Boolean readRequestsFallbackEnabled = true;
    /**
     * Sets throughput of the resources in the Azure Cosmos DB service. The
     * option is a com.azure.cosmos.models.ThroughputProperties type.
     */
    private ThroughputProperties throughputProperties;
    /**
     * Allows for bridging the consumer to the Camel routing Error Handler,
     * which mean any exceptions occurred while the consumer is trying to pickup
     * incoming messages, or the likes, will now be processed as a message and
     * handled by the routing Error Handler. By default the consumer will use
     * the org.apache.camel.spi.ExceptionHandler to deal with exceptions, that
     * will be logged at WARN or ERROR level and ignored.
     */
    private Boolean bridgeErrorHandler = false;
    /**
     * Sets the ChangeFeedProcessorOptions to be used. Unless specifically set
     * the default values that will be used are: maximum items per page or
     * FeedResponse: 100 lease renew interval: 17 seconds lease acquire
     * interval: 13 seconds lease expiration interval: 60 seconds feed poll
     * delay: 5 seconds maximum scale count: unlimited. The option is a
     * com.azure.cosmos.models.ChangeFeedProcessorOptions type.
     */
    private ChangeFeedProcessorOptions changeFeedProcessorOptions;
    /**
     * Sets if the component should create Cosmos lease container for the
     * consumer automatically in case it doesn't exist in Cosmos database
     */
    private Boolean createLeaseContainerIfNotExists = false;
    /**
     * Sets if the component should create Cosmos lease database for the
     * consumer automatically in case it doesn't exist in Cosmos account
     */
    private Boolean createLeaseDatabaseIfNotExists = false;
    /**
     * Sets the hostname. The host: a host is an application instance that uses
     * the change feed processor to listen for changes. Multiple instances with
     * the same lease configuration can run in parallel, but each instance
     * should have a different instance name. If not specified, this will be a
     * generated random hostname.
     */
    private String hostName;
    /**
     * Sets the lease container which acts as a state storage and coordinates
     * processing the change feed across multiple workers. The lease container
     * can be stored in the same account as the monitored container or in a
     * separate account. It will be auto created if
     * createLeaseContainerIfNotExists is set to true.
     */
    private String leaseContainerName = "camel-lease";
    /**
     * Sets the lease database where the leaseContainerName will be stored. If
     * it is not specified, this component will store the lease container in the
     * same database that is specified in databaseName. It will be auto created
     * if createLeaseDatabaseIfNotExists is set to true.
     */
    private String leaseDatabaseName;
    /**
     * Sets the itemId in case needed for operation on item like delete, replace
     */
    private String itemId;
    /**
     * Sets partition key. Represents a partition key value in the Azure Cosmos
     * DB database service. A partition key identifies the partition where the
     * item is stored in. The option is a com.azure.cosmos.models.PartitionKey
     * type.
     */
    private PartitionKey itemPartitionKey;
    /**
     * Whether the producer should be started lazy (on the first message). By
     * starting lazy you can use this to allow CamelContext and routes to
     * startup in situations where a producer may otherwise fail during starting
     * and cause the route to fail being started. By deferring this startup to
     * be lazy then the startup failure can be handled during routing messages
     * via Camel's routing error handlers. Beware that when the first message is
     * processed then creating and starting the producer may take a little time
     * and prolong the total processing time of the processing.
     */
    private Boolean lazyStartProducer = false;
    /**
     * The CosmosDB operation that can be used with this component on the
     * producer.
     */
    private CosmosDbOperationsDefinition operation = CosmosDbOperationsDefinition.listDatabases;
    /**
     * An SQL query to execute on a given resources. To learn more about Cosmos
     * SQL API, check this link {link
     * https://docs.microsoft.com/en-us/azure/cosmos-db/sql-query-getting-started}
     */
    private String query;
    /**
     * Set additional QueryRequestOptions that can be used with queryItems,
     * queryContainers, queryDatabases, listDatabases, listItems, listContainers
     * operations. The option is a
     * com.azure.cosmos.models.CosmosQueryRequestOptions type.
     */
    private CosmosQueryRequestOptions queryRequestOptions;
    /**
     * Whether autowiring is enabled. This is used for automatic autowiring
     * options (the option must be marked as autowired) by looking up in the
     * registry to find if there is a single instance of matching type, which
     * then gets configured on the component. This can be used for automatic
     * configuring JDBC data sources, JMS connection factories, AWS Clients,
     * etc.
     */
    private Boolean autowiredEnabled = true;
    /**
     * Sets either a master or readonly key used to perform authentication for
     * accessing resource.
     */
    private String accountKey;

    public Boolean getClientTelemetryEnabled() {
        return clientTelemetryEnabled;
    }

    public void setClientTelemetryEnabled(Boolean clientTelemetryEnabled) {
        this.clientTelemetryEnabled = clientTelemetryEnabled;
    }

    public CosmosDbConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(CosmosDbConfiguration configuration) {
        this.configuration = configuration;
    }

    public Boolean getConnectionSharingAcrossClientsEnabled() {
        return connectionSharingAcrossClientsEnabled;
    }

    public void setConnectionSharingAcrossClientsEnabled(
            Boolean connectionSharingAcrossClientsEnabled) {
        this.connectionSharingAcrossClientsEnabled = connectionSharingAcrossClientsEnabled;
    }

    public ConsistencyLevel getConsistencyLevel() {
        return consistencyLevel;
    }

    public void setConsistencyLevel(ConsistencyLevel consistencyLevel) {
        this.consistencyLevel = consistencyLevel;
    }

    public String getContainerPartitionKeyPath() {
        return containerPartitionKeyPath;
    }

    public void setContainerPartitionKeyPath(String containerPartitionKeyPath) {
        this.containerPartitionKeyPath = containerPartitionKeyPath;
    }

    public Boolean getContentResponseOnWriteEnabled() {
        return contentResponseOnWriteEnabled;
    }

    public void setContentResponseOnWriteEnabled(
            Boolean contentResponseOnWriteEnabled) {
        this.contentResponseOnWriteEnabled = contentResponseOnWriteEnabled;
    }

    public CosmosAsyncClient getCosmosAsyncClient() {
        return cosmosAsyncClient;
    }

    public void setCosmosAsyncClient(CosmosAsyncClient cosmosAsyncClient) {
        this.cosmosAsyncClient = cosmosAsyncClient;
    }

    public Boolean getCreateContainerIfNotExists() {
        return createContainerIfNotExists;
    }

    public void setCreateContainerIfNotExists(Boolean createContainerIfNotExists) {
        this.createContainerIfNotExists = createContainerIfNotExists;
    }

    public Boolean getCreateDatabaseIfNotExists() {
        return createDatabaseIfNotExists;
    }

    public void setCreateDatabaseIfNotExists(Boolean createDatabaseIfNotExists) {
        this.createDatabaseIfNotExists = createDatabaseIfNotExists;
    }

    public String getDatabaseEndpoint() {
        return databaseEndpoint;
    }

    public void setDatabaseEndpoint(String databaseEndpoint) {
        this.databaseEndpoint = databaseEndpoint;
    }

    public Boolean getMultipleWriteRegionsEnabled() {
        return multipleWriteRegionsEnabled;
    }

    public void setMultipleWriteRegionsEnabled(
            Boolean multipleWriteRegionsEnabled) {
        this.multipleWriteRegionsEnabled = multipleWriteRegionsEnabled;
    }

    public List<String> getPreferredRegions() {
        return preferredRegions;
    }

    public void setPreferredRegions(List<String> preferredRegions) {
        this.preferredRegions = preferredRegions;
    }

    public Boolean getReadRequestsFallbackEnabled() {
        return readRequestsFallbackEnabled;
    }

    public void setReadRequestsFallbackEnabled(
            Boolean readRequestsFallbackEnabled) {
        this.readRequestsFallbackEnabled = readRequestsFallbackEnabled;
    }

    public ThroughputProperties getThroughputProperties() {
        return throughputProperties;
    }

    public void setThroughputProperties(
            ThroughputProperties throughputProperties) {
        this.throughputProperties = throughputProperties;
    }

    public Boolean getBridgeErrorHandler() {
        return bridgeErrorHandler;
    }

    public void setBridgeErrorHandler(Boolean bridgeErrorHandler) {
        this.bridgeErrorHandler = bridgeErrorHandler;
    }

    public ChangeFeedProcessorOptions getChangeFeedProcessorOptions() {
        return changeFeedProcessorOptions;
    }

    public void setChangeFeedProcessorOptions(
            ChangeFeedProcessorOptions changeFeedProcessorOptions) {
        this.changeFeedProcessorOptions = changeFeedProcessorOptions;
    }

    public Boolean getCreateLeaseContainerIfNotExists() {
        return createLeaseContainerIfNotExists;
    }

    public void setCreateLeaseContainerIfNotExists(
            Boolean createLeaseContainerIfNotExists) {
        this.createLeaseContainerIfNotExists = createLeaseContainerIfNotExists;
    }

    public Boolean getCreateLeaseDatabaseIfNotExists() {
        return createLeaseDatabaseIfNotExists;
    }

    public void setCreateLeaseDatabaseIfNotExists(
            Boolean createLeaseDatabaseIfNotExists) {
        this.createLeaseDatabaseIfNotExists = createLeaseDatabaseIfNotExists;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getLeaseContainerName() {
        return leaseContainerName;
    }

    public void setLeaseContainerName(String leaseContainerName) {
        this.leaseContainerName = leaseContainerName;
    }

    public String getLeaseDatabaseName() {
        return leaseDatabaseName;
    }

    public void setLeaseDatabaseName(String leaseDatabaseName) {
        this.leaseDatabaseName = leaseDatabaseName;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public PartitionKey getItemPartitionKey() {
        return itemPartitionKey;
    }

    public void setItemPartitionKey(PartitionKey itemPartitionKey) {
        this.itemPartitionKey = itemPartitionKey;
    }

    public Boolean getLazyStartProducer() {
        return lazyStartProducer;
    }

    public void setLazyStartProducer(Boolean lazyStartProducer) {
        this.lazyStartProducer = lazyStartProducer;
    }

    public CosmosDbOperationsDefinition getOperation() {
        return operation;
    }

    public void setOperation(CosmosDbOperationsDefinition operation) {
        this.operation = operation;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public CosmosQueryRequestOptions getQueryRequestOptions() {
        return queryRequestOptions;
    }

    public void setQueryRequestOptions(
            CosmosQueryRequestOptions queryRequestOptions) {
        this.queryRequestOptions = queryRequestOptions;
    }

    public Boolean getAutowiredEnabled() {
        return autowiredEnabled;
    }

    public void setAutowiredEnabled(Boolean autowiredEnabled) {
        this.autowiredEnabled = autowiredEnabled;
    }

    public String getAccountKey() {
        return accountKey;
    }

    public void setAccountKey(String accountKey) {
        this.accountKey = accountKey;
    }
}