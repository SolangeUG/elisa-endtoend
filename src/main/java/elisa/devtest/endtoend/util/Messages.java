package elisa.devtest.endtoend.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Application messages constants
 * @author Solange U. Gasengayire
 */
public abstract class Messages {

    private static ResourceBundle messages =
            ResourceBundle.getBundle("messages", Locale.getDefault());

    public static final String DATASOURCE_CONNECTION_EXCEPTION =
            messages.getString("exception.datasource.connection");

    static final String DATASOURCE_JSON_INSERTION_EXCEPTION =
            messages.getString("exception.datasource.json.insertion");

    public static final String ORDER_PROCESSING_EXCEPTION =
            messages.getString("exception.order.process");

    public static final String ORDER_SAVING_EXCEPTION =
            messages.getString("exception.order.save");

    public static final String ORDER_RETRIEVING_EXCEPTION =
            messages.getString("exception.order.find");

    public static final String CUSTOMER_RETRIEVING_BY_ID_EXCEPTION =
            messages.getString("exception.customer.find.by.id");

    public static final String CUSTOMER_RETRIEVING_BY_COMPANY_NAME_EXCEPTION =
            messages.getString("exception.customer.find.by.company");

    public static final String CUSTOMER_SAVING_EXCEPTION =
            messages.getString("exception.customer.save");

    public static final String JSON_PARSING_EXCEPTION =
            messages.getString("exception.parsing.json");

    public static final String REST_PROCESSING_EXCEPTION =
            messages.getString("exception.rest.process");

    public static final String REST_ECHO_RESOURCE_GET_MESSAGE =
            messages.getString("rest.echo.resource.get");
}
