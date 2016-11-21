package be.itflex.ds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class DatasourceConfiguration {

    @Autowired
    private DataSource ds;

    @PostConstruct
    public void init() throws SQLException {
        final Connection conn = ds.getConnection();
        System.out.println(conn.getMetaData().getDatabaseProductName());
        final String catalog = conn.getCatalog();
        final ResultSet rs = conn.getMetaData().getTables(catalog, conn.getSchema(), null, new String[]{"TABLE"});
        while (rs.next()) {
            System.out.println(rs.getString("TABLE_NAME"));
        }
        rs.close();
    }

}
