
package com.mycompany.ca2.resources;
import com.mycompany.ca2.model.*;
import com.mycompany.ca2.service.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import javax.ws.rs.POST;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author gusta
 */

@Path("/myBank")
public class CustomerResource {
    
    CustomerService cs = new CustomerService();
    
    //curl -v -X GET -G "http://localhost:49000/api/myBank/example"
    @GET
    @Path("/example")   
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCustomers() {
        
        ArrayList<Customer> customers = cs.createExampleCustomers();
        Gson gson = new Gson();
        
        return Response.status(Response.Status.CREATED).entity(gson.toJson(customers)).build();
    }
    
    //curl -v -X GET -G "http://localhost:49000/api/myBank/getAllCustomers"
    @GET
    @Path("/getAllCustomers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomers() {
        Gson gson = new Gson();
        ArrayList<Customer> c = cs.getAllCustomers();
        return Response.status(Response.Status.CREATED).entity(gson.toJson(c)).build();
    }
    
    // curl -v -X GET http://localhost:49000/api/myBank/login/gustavo@hotmail.com/87654321
    @GET
    @Path("/login/{email}/{password}")
    public Customer login(@PathParam("email") String email, @PathParam("password") int password){
        Gson gson = new Gson();
        Customer current = cs.searchCustomer(email);
        if(current.getEmail()==null){
//            return Response.status(Response.Status.CREATED).entity("Email not found").build();
            return null;
        }
        else{
            if(current.getPassword()==password){
//                return Response.status(Response.Status.CREATED).entity(gson.toJson(current)).build();
                return current;
            }
            else{
//                return Response.status(Response.Status.CREATED).entity("Wrong Password").build();
                return null;
            }
        }
    }
    
    // curl -v -X GET http://localhost:49000/api/myBank/logged?email=gustavo@hotmail.com
    @GET
    @Path("/logged")
    public Customer getCustomer(@QueryParam("email") String email){

        Customer current = cs.searchCustomer(email);

                return current;

    }
    
    // curl -v -X GET http://localhost:49000/api/myBank/check?email=gustavo@hotmail.com
    @GET
    @Path("/check")
    public boolean checkEmail(@QueryParam("email") String email){

        Customer current = cs.searchCustomer(email);
        if(current.getEmail()!=null){
            return true;
        }
        else{
            return false;
        }

                

    }
    
    //curl -v POST http://localhost:49000/api/myBank/newCustomer?branch=99IE -d "{\"name\":\"Enda Stafford\",\"address\":\"15 Baggot Street\",\"email\":\"enda@ncirl.ie\",\"password\":11111111,\"branch\":\"99IE\"}"
    @POST
    @Path("/newCustomer")
    public Response newCustomer(@QueryParam("name") String name, @QueryParam("address") String address, @QueryParam("email") String email, @QueryParam("password") int password, @QueryParam("branch") String branch, String body){
        Gson gson = new GsonBuilder().registerTypeAdapter(Customer.class, new CustomerInstanceCreator(name,address,email,password,branch)).create();
        Customer c = gson.fromJson(body, Customer.class);
        cs.createCustomer(c);
        return Response.status(Response.Status.CREATED).entity(gson.toJson(c)).build();    
    }
    
    public class CustomerInstanceCreator implements InstanceCreator<Customer>
    {
        final private String name;
        final private String address;
        final private String email;
        final private int password;
        final private String branch;
        final private ArrayList<Account> accounts;

        public CustomerInstanceCreator(String name, String address, String email, int password, String branch)
        {
            this.name = name;
            this.address = address;
            this.email = email;
            this.password = password;
            this.branch = branch;
            this.accounts = new ArrayList<>();
            accounts.add(new Account(branch,"CURRENT"));

        }

        @Override
        public Customer createInstance(Type type)
        {
            Customer c = new Customer(name,address,email,password,branch);
            return c; 
        }
    }
    
    //curl -v -X GET http://localhost:49000/api/myBank/newAccount/06GE?email=gustavo@hotmail.com 
    @GET
    @Path("/newAccount/{branch}/{type}")
    public Response newAccount(@QueryParam("email") String email, @PathParam("branch") String branch, @PathParam("type") String type){
        Account a = new Account(branch,type);
        String response = cs.createAccount(email,a);
        return Response.status(Response.Status.CREATED).entity(response).build();
    }
    
    //curl -v -X GET http://localhost:49000/api/myBank/lodgement/06GE/8742872/100
    @GET
    @Path("/lodgement/{branch}/{acc}/{value}")
    public Response lodgement(@PathParam("branch") String branch, @PathParam("acc") int acc, @PathParam("value") double value){
        
        String response = cs.lodgement(value, branch, acc);
        
        return Response.status(Response.Status.CREATED).entity(response).build();
    }
    
    //curl -v -X GET http://localhost:49000/api/myBank/withdrawal/06GE/8742872/100
    @GET
    @Path("/withdrawal/{branch}/{acc}/{value}")
    public Response withdrawal(@PathParam("branch") String branch, @PathParam("acc") int acc, @PathParam("value") double value){
        
        String response = cs.withdrawal(value, branch, acc);
        
        return Response.status(Response.Status.CREATED).entity(response).build();
    }
    
    //curl -v -X GET http://localhost:49000/api/myBank/transfer/06GE/8742872/100/06GE/87182342/100
    @GET
    @Path("/transfer/{branchEnviando}/{accNumberEnviando}/{branchRecebendo}/{accNumberRecebendo}/{value}")
    public Response transfer(@PathParam("branchEnviando") String branchEnviando, @PathParam("accNumberEnviando") int accNumberEnviando,
            @PathParam("branchRecebendo") String branchRecebendo, @PathParam("accNumberRecebendo") int accNumberRecebendo, @PathParam("value") double value){
        
        String response = cs.transfer(branchEnviando, accNumberEnviando, branchRecebendo, accNumberRecebendo, value);
        
        return Response.status(Response.Status.CREATED).entity(response).build();
    
    }

}