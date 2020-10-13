package academyapi;

import academyapi.pojos.BankTransactionPOJO;
import academyapi.serviceBankTransaction.BankTransactionService;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


/**
 * bankTransaction API test
 *
 * @author sergio garcia
 */
public class bankTransactionTest {

    //given
    BankTransactionService bankTransactionService = new BankTransactionService();
    Response response;


    @Test (priority = 1)
    public void verifyEmptyEndpoint() {

        //when
        response = bankTransactionService.requestGetMethod(bankTransactionService.getEndpoint());
        Assert.assertEquals(response.getStatusCode(), 200);

        //then
        BankTransactionPOJO[] responseArray = response.getBody().as(BankTransactionPOJO[].class);
        if(responseArray.length > 0 ){
            for (BankTransactionPOJO transaction: responseArray) {
                bankTransactionService.requestDeleteMethod(bankTransactionService.getEndpoint(), transaction.getId());
            }
        }
        System.out.println("endpoint is already empty");
    }

    @Test (priority = 2)
    public void initializeBankTransactionPojos() throws Exception {
        //Given
        BankTransactionPOJO bankTransaction;
        List<BankTransactionPOJO> bankTransactions = new ArrayList<>();
        //when
        for (int i = 0; i < 20; i++) {
            String id = Integer.toString(i);
            String name = "name " +i;
            String lastName = "lastName " +i;
            String accountNumber = bankTransactionService.getRandomNumber(i, 99999);
            String amount = bankTransactionService.getRandomNumber(i, 99999);
            String transactionType = "transactionType " +i;
            String email = "email" +i + "@fake.com";
            boolean active = true;
            String country = "Colombia";
            String telephone = "123456" +i;

            bankTransaction = new BankTransactionPOJO(id, name, lastName, accountNumber, amount, transactionType, email, active, country, telephone);
            bankTransactions.add(bankTransaction);
        }

        //then
        String email = "";
        boolean duplicates=false;
        for (int i = 0; i < bankTransactions.size(); i++) {
            for (int j = i + 1; j < bankTransactions.size(); j++) {
                if (j != i && bankTransactions.get(i).getEmail().equals(bankTransactions.get(j).getEmail())){
                    duplicates = true;
                }
            }
        }
        if(duplicates){
            throw new Exception("email duplicate");
        }else{
            for (BankTransactionPOJO transaction: bankTransactions) {
                bankTransactionService.requestPostMethod(bankTransactionService.getEndpoint(), transaction);
            }
        }
        }

    @Test (priority = 3)
    public void emailNegativeTest() throws Exception {
        //Given
        BankTransactionPOJO bankTransaction;
        //when
        String id = "1";
        String name = "nameNegativeTest";
        String lastName = "lastName";
        String accountNumber = bankTransactionService.getRandomNumber(1, 99999);
        String amount = bankTransactionService.getRandomNumber(1, 99999);
        String transactionType = "transactionType";
        String email = "email1@fake.com";
        boolean active = true;
        String country = "Colombia";
        String telephone = "123456";
        bankTransaction = new BankTransactionPOJO(id, name, lastName, accountNumber, amount, transactionType, email, active, country, telephone);
        //then
        BankTransactionPOJO[] responseArray = response.getBody().as(BankTransactionPOJO[].class);
        boolean duplicates=false;
        for (int i = 0; i < responseArray.length; i++) {
            if (responseArray[i].getEmail().equals(bankTransaction.getEmail())){
                    duplicates = true;
                }
        }
        if(duplicates){
            throw new Exception("email duplicate, then post request is not going to be send");
        }else{
            bankTransactionService.requestPostMethod(bankTransactionService.getEndpoint(), bankTransaction);
        }
    }

}