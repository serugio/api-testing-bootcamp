package academyapi;

import academyapi.jiraServices.JiraService;
import academyapi.pojos.*;
import academyapi.serviceBankTransaction.BankTransactionService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


/**
 * bankTransaction API test
 *
 * @author sergio garcia
 */
public class jiraBugsTest {

    //given
    JiraService jiraService = new JiraService();
    Response response;


    @Test(priority = 4)
    public void createJiraBug() {

        //when
        JiraBugPOJO jiraBugPOJO = new JiraBugPOJO();
        Fields fields = new Fields();
        Project project = new Project();
        project.setKey("AT");
        fields.setProject(project);
        fields.setSummary("issue test from API testing");
        fields.setDescription("Creating an issue from API using restAssure");
        Issuetype issuetype = new Issuetype();
        issuetype.setName("Bug");
        fields.setIssuetype(issuetype);
        jiraBugPOJO.setFields(fields);

        response = jiraService.requestPostBasicAuthMethod(jiraService.getEndpoint(), jiraBugPOJO, jiraService.getAuthorization());
        response.then().assertThat().statusCode(201);
    }

    @Test(priority = 5)
    public void validateJiraBugId() {

        //when
        response = jiraService.requestGetBasicAuthMethod(jiraService.getIssuesEndpoint(), jiraService.getAuthorization());
        response.prettyPrint();
    }

}