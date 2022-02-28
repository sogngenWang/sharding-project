package com.wsg.sharding.algo.common;

import org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory;

import javax.sql.DataSource;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class HintAlgorithmTest {

    private static DataSource dataSource = null;

    public static void main(String[] args) throws Exception {
        File ymlfile = new File(HintAlgorithmTest.class.getResource("/META-INF/config-test.yaml").getFile());
        dataSource = YamlShardingSphereDataSourceFactory.createDataSource(ymlfile);

        try (
                Connection conn = dataSource.getConnection();
                Statement statement = conn.createStatement();
        ) {
            statement.execute("select * from test.hero limit 10");
            try (ResultSet rs = statement.getResultSet()) {
                while (rs.next()) {
                    System.out.println("result---------" + rs.getLong("id"));
                }
            }
        }
    }


}
