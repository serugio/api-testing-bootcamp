package academyapi.serviceBankTransaction;

import academyapi.baseservice.BaseService;
import io.restassured.response.Response;

/**
 * Service Page One
 *
 * @author Brayhan.Criollo
 */
public class BankTransactionService extends BaseService {

    protected  String endpoint = "https://5f846ea5c29abd001618fa87.mockapi.io/v1/bankTransaction";



    public  String getEndpoint() {
        return this.endpoint;
    }
}