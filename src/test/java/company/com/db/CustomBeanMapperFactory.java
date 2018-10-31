package company.com.db;

import org.skife.jdbi.v2.BeanMapper;
import org.skife.jdbi.v2.DefaultMapper;
import org.skife.jdbi.v2.ResultSetMapperFactory;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.util.Map;

public class CustomBeanMapperFactory implements ResultSetMapperFactory {
    @Override
    public boolean accepts(Class type, StatementContext ctx) {
        return ctx.columnMapperFor(type) == null;
    }

    @Override
    public ResultSetMapper mapperFor(Class type, StatementContext ctx) {
        if (Map.class.isAssignableFrom(type)) {
            return new DefaultMapper();
        }

        return new BeanMapper<>(type);
    }
}
