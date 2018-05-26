package elisa.devtest.endtoend;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreparedJsonStatement implements PreparedStatementCallback<Boolean> {
    private final String groupIdentifier;
    private final String json;

    public PreparedJsonStatement(String groupIdentifier, String json) {
        this.groupIdentifier = groupIdentifier;
        this.json = json;
    }


    @Override
    public Boolean doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
        preparedStatement.setString(1, groupIdentifier);
        preparedStatement.setString(2, json);
        return preparedStatement.execute();
    }
}
