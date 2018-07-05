package elisa.devtest.endtoend.util;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Class to represent precompiled JSON SQL statement
 */
public class PreparedJsonStatement implements PreparedStatementCallback<Boolean> {
    private final String groupIdentifier;
    private final String json;

    /**
     * Constructor
     * @param groupIdentifier entry identifier
     * @param json JSON content
     */
    PreparedJsonStatement(String groupIdentifier, String json) {
        this.groupIdentifier = groupIdentifier;
        this.json = json;
    }

    /**
     * Return result of prepared statement execution
     * @param preparedStatement provided prepared statement
     * @return result of execution
     * @throws SQLException in case of SQL error
     * @throws DataAccessException data access API agnostic exception
     */
    @Override
    public Boolean doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
        preparedStatement.setString(1, groupIdentifier);
        preparedStatement.setString(2, json);
        return preparedStatement.execute();
    }
}
