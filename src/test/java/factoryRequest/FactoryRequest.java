package factoryRequest;

public class FactoryRequest {

    public static final String POST="post";
    public static final String GET="get";
    public static final String PUT="put";
    public static final String DELETE="delete";

    public static IRequest make(String typeRequest){
        IRequest request;
        switch (typeRequest.toLowerCase()){
            case POST:
                request= new RequestPOST();
                break;
            case PUT:
                request= new RequestPUT();
                break;
            case DELETE:
                request= new RequestDELETE();
                break;
            case GET:
                request= new RequestGET();
                break;
            default:
                request= new RequestGET();
                break;
        }
         return request;
    }

}
