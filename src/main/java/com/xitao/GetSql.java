package com.xitao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class GetSql {


    private static final String[] sqlTemplate = {
            "CREATE TABLE `%s` ( `id` bigint(20) NOT NULL comment '主键id',\n" +
                    " `orderId` varchar(20) DEFAULT NULL comment '订单id',\n" +
                    " `parentOrderId` varchar(20) DEFAULT NULL comment '父订单Id',\n" +
                    " `totalPrice` decimal(20,2) DEFAULT NULL comment '商品总价',\n" +
                    " `price` decimal(20,2) DEFAULT NULL comment '订单应收',\n" +
                    " `discount` decimal(20,2) DEFAULT NULL comment '订单折扣',\n" +
                    " `payDiscount` decimal(20,2) DEFAULT NULL comment '支付优惠',\n" +
                    " `shouldRefundAmount` decimal(20,2) DEFAULT NULL comment '应退金额',\n" +
                    " `realRefundAmount` decimal(20,2) DEFAULT NULL comment '实退金额',\n" +
                    " `yun` decimal(20,2) DEFAULT NULL comment '运费',\n" +
                    " `paidIn` decimal(20,2) DEFAULT NULL comment '订单实付',\n" +
                    " `pin` varchar(50) DEFAULT NULL comment '用户pin',\n" +
                    " `otherMoney` decimal(20,2) DEFAULT NULL comment '其他费用',\n" +
                    " `currency` smallint(6) DEFAULT NULL comment '币种',\n" +
                    " `orderCode` smallint(6) DEFAULT NULL comment '订单机构号',\n" +
                    " `orderType` smallint(6) DEFAULT NULL comment '订单类型',\n" +
                    " `payMode` smallint(6) DEFAULT NULL comment '支付方式',\n" +
                    " `psMode` int(11) DEFAULT NULL COMMENT '配送站点编号',\n" +
                    " `orderTime` datetime DEFAULT NULL comment '下单时间',\n" +
                    " `hxTime` datetime DEFAULT NULL comment '核销时间',\n" +
                    " `createTime` datetime DEFAULT NULL comment '创建时间',\n" +
                    " `updateTime` datetime DEFAULT NULL comment '更新时间',\n" +
                    " `orderBankStatus` tinyint(4) DEFAULT NULL comment '订单状态',\n" +
                    " `yn` tinyint(4) DEFAULT NULL comment '是否有效',\n" +
                    " `cxStatus` tinyint(4) DEFAULT NULL comment '冲销状态',\n" +
                    " `ver` smallint(6) DEFAULT NULL comment '版本号',\n" +
                    " `orderDueType` smallint(6) DEFAULT NULL comment '订单应收类型',\n" +
                    " `realDuePrice` decimal(20,2) DEFAULT NULL comment '真正应收金额',\n" +
                    " `realPayPrice` decimal(20,2) DEFAULT NULL comment '真正实收金额',\n" +
                    " `signs` smallint(6) DEFAULT NULL comment '新老系统标识:0老系统流程,1新系统流程',\n" +
                    " `bizSign` varchar(64) DEFAULT NULL comment '业务标记',\n" +
                    " `orderDueDiscount` decimal(20,2) DEFAULT NULL comment '原始订单总优惠',\n" +
                    " `orderVirtrulPay` decimal(20,2) DEFAULT NULL comment '原始虚拟资金',\n" +
                    " `orderPayDiscount` decimal(20,2) DEFAULT NULL comment '原始支付总优惠',\n" +
                    " `orderNeedPay` decimal(20,2) DEFAULT NULL comment '原始应付款金额',\n" +
                    " `confirmStatus` smallint(6) DEFAULT NULL comment '对账状态',\n" +
                    " `orderStatus` smallint(6) DEFAULT NULL comment '订单状态',\n" +
                    " `shouldMorePayRefund` decimal(20,2) DEFAULT NULL comment '多支付应退金额',\n" +
                    " PRIMARY KEY (`id`), KEY `idx_orderbank0000_orderid` (`orderId`), KEY `idx_updateTime` (`updateTime`) ) comment='订单台账' DEFAULT CHARSET=utf8;",

            "CREATE TABLE `%s` ( `id` bigint(20) NOT NULL AUTO_INCREMENT comment '主键id',\n" +
                    "  `orderBankNo` bigint(20) DEFAULT NULL comment '订单台账号',\n" +
                    " `orderId` varchar(20) DEFAULT NULL comment '订单id',\n" +
                    " `receivableType` tinyint(4) DEFAULT NULL comment '应收类型',\n" +
                    " `receivableId` varchar(30) DEFAULT NULL comment '应收ID',\n" +
                    " `price` decimal(20,2) DEFAULT NULL comment '应收金额',\n" +
                    " `businessType` smallint(6) DEFAULT NULL comment '业务类型',\n" +
                    " `businessNo` varchar(30) DEFAULT NULL comment '业务id',\n" +
                    " `cxId` varchar(30) DEFAULT NULL comment '冲销id',\n" +
                    " `nremark` varchar(100) DEFAULT NULL comment '对内备注',\n" +
                    " `operator` varchar(20) DEFAULT NULL comment '操作者',\n" +
                    " `createTime` datetime DEFAULT NULL comment '创建时间',\n" +
                    " `updateTime` datetime DEFAULT NULL comment '更新时间',\n" +
                    " `dataType` tinyint(4) DEFAULT NULL comment '数据类型',\n" +
                    " `yn` tinyint(4) DEFAULT NULL comment '是否有效',\n" +
                    " `ver` smallint(6) DEFAULT NULL comment '版本',\n" +
                    " `systemNo` smallint(6) DEFAULT NULL comment '业务方系统号',\n" +
                    " PRIMARY KEY (`id`), KEY `idx_orderId` (`orderId`), KEY `idx_updateTime` (`updateTime`) ) comment='应收详情' DEFAULT CHARSET=utf8;",

            "CREATE TABLE `%s` ( `id` bigint(20) NOT NULL AUTO_INCREMENT comment '主键id',\n" +
            " `orderBankNo` bigint(20) DEFAULT NULL comment '订单台账号',\n" +
            " `orderId` varchar(20) DEFAULT NULL comment '订单id',\n" +
            " `payType` smallint(6) DEFAULT NULL comment '支付类型',\n" +
            " `payId` varchar(50) DEFAULT NULL comment '支付id',\n" +
            " `payEnum` smallint(6) DEFAULT NULL comment '支付枚举',\n" +
            " `price` decimal(20,2) DEFAULT NULL comment '支付金额',\n" +
            " `currency` smallint(6) DEFAULT NULL comment '币种',\n" +
            " `pin` varchar(50) DEFAULT NULL comment 'PIN',\n" +
            " `businessType` smallint(6) DEFAULT NULL comment '业务类型',\n" +
            " `businessNo` varchar(60) DEFAULT NULL comment '业务号',\n" +
            " `cxId` varchar(30) DEFAULT NULL comment '冲销ID',\n" +
            " `operator` varchar(20) DEFAULT NULL comment '操作人',\n" +
            " `payTime` datetime DEFAULT NULL comment '支付时间',\n" +
            " `createTime` datetime DEFAULT NULL comment '创建时间',\n" +
            " `updateTime` datetime DEFAULT NULL comment '更新时间',\n" +
            " `dataType` tinyint(4) DEFAULT NULL comment '数据类型',\n" +
            " `yn` tinyint(4) DEFAULT NULL comment '有效',\n" +
            " `ver` smallint(6) DEFAULT NULL comment '版本',\n" +
            " `currencyPrice` decimal(20,2) DEFAULT NULL comment '原货币金额',\n" +
            " `systemNo` smallint(6) DEFAULT NULL comment '业务方系统号',\n" +
            " `status` smallint(6) DEFAULT NULL comment '资金状态',\n" +
            " `morePayRefund` decimal(20,2) DEFAULT NULL comment '多支付金额',\n" +
            " PRIMARY KEY (`id`), KEY `idx_orderId` (`orderId`), KEY `idx_updateTime` (`updateTime`) ) comment='实收详情'  DEFAULT CHARSET=utf8;",

            "CREATE TABLE `%s` ( `id` bigint(20) NOT NULL AUTO_INCREMENT comment '主键id',\n" +
                    " `orderId` bigint(20) DEFAULT NULL comment '订单id',\n" +
                    " `uuid` varchar(50) NOT NULL comment '交易防重id',\n" +
                    " PRIMARY KEY (`id`), UNIQUE KEY `uuid_unique` (`uuid`) ) comment='业务防重表'  DEFAULT CHARSET=utf8;"
    };


    private static final String[] prefixArr={"orderbank_","duedetail_","paydetail_","orderbiz_"};


    public static void main(String[] args) throws Exception {
        File fs= new File("/Users/wangtao68/dev/sql.txt");
        System.setOut(new PrintStream(new FileOutputStream(fs)));
        for(int i=0;i<sqlTemplate.length;i++) {
            for(int j=0;j<200;j++) {
                String num = String.format("%04d",j);
                String prefix = prefixArr[i]+num;
                String sql = String.format(sqlTemplate[i],prefix);
                System.out.println();
                System.out.println(sql);
            }
        }


    }
}
