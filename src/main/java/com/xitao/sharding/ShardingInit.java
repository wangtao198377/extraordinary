//package com.xitao.sharding;
//
//import com.google.common.collect.Lists;
//import io.shardingsphere.core.api.ShardingDataSourceFactory;
//import io.shardingsphere.core.api.config.MasterSlaveRuleConfiguration;
//import io.shardingsphere.core.api.config.ShardingRuleConfiguration;
//import io.shardingsphere.core.api.config.TableRuleConfiguration;
//import io.shardingsphere.core.api.config.strategy.StandardShardingStrategyConfiguration;
//import io.shardingsphere.core.util.DataSourceUtil;
//
//import javax.sql.DataSource;
//import java.sql.SQLException;
//import java.util.*;
//
//
//public class ShardingInit {
//
//    public DataSource getShardingDataSource() throws SQLException {
//        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
//        shardingRuleConfig.getTableRuleConfigs().add(getOrderTableRuleConfiguration());
//        shardingRuleConfig.getTableRuleConfigs().add(getOrderItemTableRuleConfiguration());
//        shardingRuleConfig.getBindingTableGroups().add("t_order, t_order_item");
//        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("user_id", ModuloShardingDatabaseAlgorithm.class.getName()));
//        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("order_id", ModuloShardingTableAlgorithm.class.getName()));
//        shardingRuleConfig.setMasterSlaveRuleConfigs(getMasterSlaveRuleConfigurations());
//        return ShardingDataSourceFactory.createDataSource(createDataSourceMap(), shardingRuleConfig, new HashMap<String, Object>(), new Properties());
//    }
//
//    public TableRuleConfiguration getOrderTableRuleConfiguration() {
//        TableRuleConfiguration result = new TableRuleConfiguration();
//        result.setLogicTable("t_order");
//        result.setActualDataNodes("ds${0..1}.t_order${[0, 1]}");
//        result.setKeyGeneratorColumnName("order_id");
//        return result;
//    }
//
//    TableRuleConfiguration getOrderItemTableRuleConfiguration() {
//        TableRuleConfiguration result = new TableRuleConfiguration();
//        result.setLogicTable("t_order_item");
//        result.setActualDataNodes("ds${0..1}.t_order_item${[0, 1]}");
//        return result;
//    }
//
//    List<MasterSlaveRuleConfiguration> getMasterSlaveRuleConfigurations() {
//        MasterSlaveRuleConfiguration masterSlaveRuleConfig1 = new MasterSlaveRuleConfiguration();
//        masterSlaveRuleConfig1.setName("ds0");
//        masterSlaveRuleConfig1.setMasterDataSourceName("ds_master0");
//        masterSlaveRuleConfig1.setSlaveDataSourceNames(Arrays.asList("ds_master0_slave0", "ds_master0_slave1"));
//
//        MasterSlaveRuleConfiguration masterSlaveRuleConfig2 = new MasterSlaveRuleConfiguration();
//        masterSlaveRuleConfig2.setName("ds1");
//        masterSlaveRuleConfig2.setMasterDataSourceName("ds_master1");
//        masterSlaveRuleConfig2.setSlaveDataSourceNames(Arrays.asList("ds_master1_slave0", "ds_master1_slave1"));
//        return Lists.newArrayList(masterSlaveRuleConfig1, masterSlaveRuleConfig2);
//    }
//
//    Map<String, DataSource> createDataSourceMap() {
//        Map<String, DataSource> result = new HashMap<>();
//
//        result.put("ds_master0", DataSourceUtil.createDataSource("ds_master0"));
//        result.put("ds_master0_slave0", DataSourceUtil.createDataSource("ds_master0_slave0"));
//        result.put("ds_master0_slave1", DataSourceUtil.createDataSource("ds_master0_slave1"));
//        result.put("ds_master1", DataSourceUtil.createDataSource("ds_master1"));
//        result.put("ds_master1_slave0", DataSourceUtil.createDataSource("ds_master1_slave0"));
//        result.put("ds_master1_slave1", DataSourceUtil.createDataSource("ds_master1_slave1"));
//
//        MasterSlaveRuleConfiguration masterSlaveRuleConfig1 = new MasterSlaveRuleConfiguration();
//        masterSlaveRuleConfig1.setName("ds0");
//        masterSlaveRuleConfig1.setMasterDataSourceName("ds_master0");
//        masterSlaveRuleConfig1.setSlaveDataSourceNames(Arrays.asList("ds_master0_slave0", "ds_master0_slave1"));
//
//        MasterSlaveRuleConfiguration masterSlaveRuleConfig2 = new MasterSlaveRuleConfiguration();
//        masterSlaveRuleConfig2.setName("ds1");
//        masterSlaveRuleConfig2.setMasterDataSourceName("ds_master1");
//        masterSlaveRuleConfig2.setSlaveDataSourceNames(Arrays.asList("ds_master1_slave0", "ds_master1_slave1"));
//
//        return result;
//    }
//
//
//    public static void main(String[] args) {
//
//    }
//}
