package factoryRequest;

public class RequestInformation {
    private String url;
    private String body;
    private String typeAuthentication;
    private String valueAuthentication;
    public RequestInformation(){}

    public RequestInformation(String url, String body,String typeAuthentication,String valueAuthentication ){
        this.url=url;
        this.body=body;
        this.typeAuthentication=typeAuthentication;
        this.valueAuthentication=valueAuthentication;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTypeAuthentication() {
        return typeAuthentication;
    }

    public void setTypeAuthentication(String typeAuthentication) {
        this.typeAuthentication = typeAuthentication;
    }

    public String getValueAuthentication() {
        return valueAuthentication;
    }

    public void setValueAuthentication(String valueAuthentication) {
        this.valueAuthentication = valueAuthentication;
    }
}
