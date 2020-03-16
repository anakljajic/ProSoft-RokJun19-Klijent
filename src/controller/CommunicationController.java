/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Linija;
import domain.LinijaStanica;
import domain.Stanica;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import transfer.RequestObject;
import transfer.ResponseObject;
import util.Operation;

/**
 *
 * @author student1
 */
public class CommunicationController {

    private static CommunicationController instance;
    private Socket socket;
    private List<LinijaStanica> linijeStanice;

    private CommunicationController() throws IOException {
        socket = new Socket("localhost", 9000);
        linijeStanice = new ArrayList<>();
    }

    public static CommunicationController getInstance() throws IOException {
        if (instance == null) {
            instance = new CommunicationController();
        }
        return instance;
    }

//    public User logIn(String username, String password) throws IOException, ClassNotFoundException, Exception {
//        RequestObject request=new RequestObject();
//        request.setOperation(Operation.OPERATION_LOGIN);
//        
//        Map<String, String> data=new HashMap<>();
//        data.put("username", username);
//        data.put("password", password);
//        request.setData(data);
//        
//        sendRequest(request);
//        ResponseObject response=receiveResponse();
//        
//        if(response.getException()!=null){
//            throw response.getException();
//        }
//        return (User)response.getData();
//    }
//    
//    public List<Manufacturer> getAllManufacturers() throws Exception{
//        RequestObject request=new RequestObject();
//        request.setOperation(Operation.OPERATION_GET_ALL_MANUFACTURERS);
//        
//        sendRequest(request);
//        ResponseObject response=receiveResponse();
//        
//        if(response.getException()!=null){
//            throw response.getException();
//        }
//        return (List<Manufacturer>)response.getData();
//    }
//    
//    public void saveProduct(Product product) throws Exception{
//        RequestObject request=new RequestObject();
//        request.setOperation(Operation.OPERATION_SAVE_PRODUCT);
//        
//        request.setData(product);
//        
//        sendRequest(request);
//        ResponseObject response=receiveResponse();
//        
//        if(response.getException()!=null){
//            throw response.getException();
//        }
//        
//    }
//    
//    public List<Product> getAllProducts() throws Exception{
//        RequestObject request=new RequestObject();
//        request.setOperation(Operation.OPERATION_GET_ALL_PRODUCTS);
//        
//        sendRequest(request);
//        ResponseObject response=receiveResponse();
//        
//        if(response.getException()!=null){
//            throw response.getException();
//        }
//        return (List<Product>)response.getData();
//    }
//    
//    public Invoice saveInvoice(Invoice invoice) throws Exception{
//        RequestObject request=new RequestObject();
//        request.setOperation(Operation.OPERATION_SAVE_INVOICE);
//        
//        request.setData(invoice);
//        
//        sendRequest(request);
//        ResponseObject response=receiveResponse();
//        
//        if(response.getException()!=null){
//            throw response.getException();
//        }
//        return (Invoice)response.getData();
//    }
    private void sendRequest(RequestObject request) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();
    }

    private ResponseObject receiveResponse() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        return response;
    }

    public List<Stanica> getAllStanice() throws Exception {
        RequestObject request = new RequestObject();
        request.setOperation(Operation.OPERATION_GET_ALL_STANICE);

        sendRequest(request);
        ResponseObject response = receiveResponse();

        if (response.getException() != null) {
            throw response.getException();
        }
        return (List<Stanica>) response.getData();
    }

    public List<LinijaStanica> vratiSveLinijeStaniceIzMemorije(List<LinijaStanica> linijeStanice) {
        this.linijeStanice = linijeStanice;
        return linijeStanice;
    }

    public Linija sacuvajLiniju(Linija linija) throws Exception {
        RequestObject request = new RequestObject();
        request.setOperation(Operation.OPERATION_SAVE_LINIJA);

        request.setData(linija);

        sendRequest(request);
        ResponseObject response = receiveResponse();

        if (response.getException() != null) {
            throw response.getException();
        }
        return (Linija) response.getData();
    }

    public void sacuvajMedjustanice(List<LinijaStanica> linijeStanice) throws Exception {
        RequestObject request = new RequestObject();
        request.setOperation(Operation.OPERATION_SAVE_LINIJE_STANICE);

        request.setData(linijeStanice);

        sendRequest(request);
        ResponseObject response = receiveResponse();

        if (response.getException() != null) {
            throw response.getException();
        }
    }
}
