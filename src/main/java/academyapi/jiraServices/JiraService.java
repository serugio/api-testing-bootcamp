package academyapi.jiraServices;

import academyapi.baseservice.BaseService;

/**
 * Service Page One
 *
 * @author Brayhan.Criollo
 */
public class JiraService extends BaseService {

    protected  String endpoint = "https://automation-bootcamp.atlassian.net/rest/api/2/issue/";
    protected  String issuesEndpoint = "https://automation-bootcamp.atlassian.net/rest/agile/1.0/board/1/issue";
    protected  String authorization = "Basic c2VydWdpbzQ0QGdtYWlsLmNvbTpJY2szUFQ4WWZweVp1T0xKTGpEVEUzMzU=";


    public  String getEndpoint() {
        return this.endpoint;
    }
    public  String getAuthorization() {
        return this.authorization;
    }
    public  String getIssuesEndpoint() {
        return this.issuesEndpoint;
    }
}
