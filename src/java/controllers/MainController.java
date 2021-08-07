/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import services.RestCall;

/**
 *
 * @author WORKSTATION
 */
@Controller
public class MainController {
    RestCall restCall = new RestCall();
    Logger logger = Logger.getLogger(MainController.class.getName());
    
    @RequestMapping({"/", "/index"})
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView("index");
        JSONArray serviceProviders = new JSONArray();
        JSONArray cities = new JSONArray();
        JSONArray meals = new JSONArray();
        try{
        serviceProviders = new JSONArray(restCall.executeGet("getAllServiceProviders"));
        cities = new JSONArray(restCall.executeGet("getAllCities"));
        meals = new JSONArray(restCall.executeGet("getAllMeals"));
        } catch (JSONException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mv.addObject("serviceProviders", serviceProviders);
        mv.addObject("cities", cities);
        mv.addObject("meals", meals);
        return mv;
    }
    
    @RequestMapping({"/citySearchResult"})
    public ModelAndView citySearchResult(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("index");
        JSONArray meals = new JSONArray();
        JSONArray cities = new JSONArray();
        meals = (JSONArray) request.getSession().getAttribute("mealResult");
        try{
            cities = new JSONArray(restCall.executeGet("getAllCities"));
        } catch (JSONException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mv.addObject("cities", cities);
        mv.addObject("meals", meals);
        return mv;
    }
    
    @RequestMapping(value = {"/searchCity"}, method = RequestMethod.GET)
    @ResponseBody
public String searchCity(HttpServletRequest request,
HttpServletResponse response,
@RequestParam(required = false) String city
) throws JSONException {
    JSONArray meals = new JSONArray();
        JSONObject js = new JSONObject();
        try{
        js.put("name", city);
        meals = new JSONArray(restCall.executeRequest("getMealItemsByLocation", js));
        request.getSession().setAttribute("mealResult", meals);
        } catch (JSONException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
            String resp = "00";
            System.out.println("response here...."+resp);
            return resp;
}

@RequestMapping({"/dashboard"})
    public ModelAndView dashboard() throws JSONException{
        ModelAndView mv = new ModelAndView("index");
        JSONObject res = new JSONObject();
        
        try{
            res = new JSONObject(restCall.executeGet("getAdminStats"));
        } catch (JSONException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       mv.addObject("totalVendors", res.getString("totalVendors"));
       mv.addObject("totalTransactions", res.getString("totalTransactions"));
       mv.addObject("totalPendingTransactions", res.getString("totalPendingTransactions"));
       mv.addObject("totalSuccessfulTransactions", res.getString("totalSuccessfulTransactions"));
       mv.addObject("totalPackages", res.getString("totalPackages"));
       mv.addObject("totalNumberofPayments", res.getString("totalNumberofPayments"));
       mv.addObject("totalUsers", res.getString("totalUsers"));
       mv.addObject("totalPayments", res.getString("totalPayments"));
       mv.addObject("totalUnverifiedUsers", res.getString("totalUnverifiedUsers"));
       mv.addObject("totalCustomers", res.getString("totalCustomers"));
       mv.addObject("totalMessages", res.getString("totalMessages"));
       mv.addObject("totalVendors", res.getString("totalVendors"));
       mv.addObject("totalVerifiedUsers", res.getString("totalVerifiedUsers"));
        
        return mv;
    }
    
    @RequestMapping({"/vendors"})
    public ModelAndView vendors(){
        ModelAndView mv = new ModelAndView("vendors");
        JSONArray vendors = new JSONArray();
        try{
        vendors = new JSONArray(restCall.executeGet("getVendors"));
        } catch (JSONException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mv.addObject("vendors", vendors);
        mv.addObject("vendorsLength", vendors.length());
        
        
        return mv;
    }
    
    @RequestMapping({"/packages"})
    public ModelAndView packages(){
        ModelAndView mv = new ModelAndView("packages");
        JSONArray packages = new JSONArray();
        try{
        packages = new JSONArray(restCall.executeGet("getPackages"));
        } catch (JSONException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mv.addObject("packages", packages);
        mv.addObject("packagesLength", packages.length());
        
        
        return mv;
    }
    
       @RequestMapping(value = {"/createPackage"}, method = RequestMethod.POST)
    @ResponseBody
public String createPackage(HttpServletRequest request,
HttpServletResponse response,
@RequestParam("name") String name, @RequestParam("description") String description, @RequestParam("toCurrency") String toCurrency,
@RequestParam("fromCurrency") String fromCurrency, @RequestParam("rate") String rate, @RequestParam("charges") String charges
) throws JSONException {
   JSONObject js = new JSONObject();
   JSONObject res = new JSONObject();         
            js.put("name", name);
            js.put("description", description);
            js.put("toCurrency", toCurrency);
            js.put("fromCurrency", fromCurrency);
            js.put("rate", rate);
            js.put("charges", charges);
            logger.info("data hitting..."+js);
            try {
             res = new JSONObject(restCall.executeRequest("createPackages", js));
            }
            catch(Exception ex){
                logger.info("Exception occured..."+ex);
                return "01";
            }
            String resp = res.getString("responseCode");
            return resp;
}

 @RequestMapping(value = {"/updatePackage"}, method = RequestMethod.POST)
    @ResponseBody
public String updatePackage(HttpServletRequest request,
HttpServletResponse response, @RequestParam("id") int id,
@RequestParam("name") String name, @RequestParam("description") String description, @RequestParam("toCurrency") String toCurrency,
@RequestParam("fromCurrency") String fromCurrency, @RequestParam("rate") String rate, @RequestParam("charges") String charges
) throws JSONException {
   JSONObject js = new JSONObject();
   JSONObject res = new JSONObject();         
            js.put("name", name);
            js.put("id", id);
            js.put("description", description);
            js.put("toCurrency", toCurrency);
            js.put("fromCurrency", fromCurrency);
            js.put("rate", rate);
            js.put("charges", charges);
            logger.info("data hitting..."+js);
            try {
             res = new JSONObject(restCall.executeRequest("updatePackages", js));
            }
            catch(Exception ex){
                logger.info("Exception occured..."+ex);
                return "01";
            }
            String resp = res.getString("responseCode");
            return resp;
}

 @RequestMapping(value = {"/deletePackage"}, method = RequestMethod.POST)
    @ResponseBody
public String deletePackage(HttpServletRequest request,
HttpServletResponse response, @RequestParam("id") int id
) throws JSONException {
   JSONObject js = new JSONObject();
   JSONObject res = new JSONObject();   
            js.put("id", id);
            logger.info("data hitting..."+js);
            try {
             res = new JSONObject(restCall.executeRequest("deletePackages", js));
            }
            catch(Exception ex){
                logger.info("Exception occured..."+ex);
                return "01";
            }
            String resp = res.getString("responseCode");
            return resp;
}

 @RequestMapping(value = {"/disableVendor"}, method = RequestMethod.POST)
    @ResponseBody
public String disableVendor(HttpServletRequest request,
HttpServletResponse response, @RequestParam("email") String email
) throws JSONException {
   JSONObject js = new JSONObject();
   JSONObject res = new JSONObject();   
            js.put("emailAddress", email);
            logger.info("data hitting..."+js);
            try {
             res = new JSONObject(restCall.executeRequest("disableVendor", js));
            }
            catch(Exception ex){
                logger.info("Exception occured..."+ex);
                return "01";
            }
            String resp = res.getString("responseCode");
            return resp;
}

@RequestMapping(value = {"/approveVendor"}, method = RequestMethod.POST)
    @ResponseBody
public String approveVendor(HttpServletRequest request,
HttpServletResponse response, @RequestParam("email") String email
) throws JSONException {
   JSONObject js = new JSONObject();
   JSONObject res = new JSONObject();   
            js.put("emailAddress", email);
            logger.info("data hitting..."+js);
            try {
             res = new JSONObject(restCall.executeRequest("approveVendor", js));
            }
            catch(Exception ex){
                logger.info("Exception occured..."+ex);
                return "01";
            }
            String resp = res.getString("responseCode");
            return resp;
}

@RequestMapping({"/transactions"})
    public ModelAndView transactions(){
        ModelAndView mv = new ModelAndView("transactions");
        JSONArray transactions = new JSONArray();
        try{
        transactions = new JSONArray(restCall.executeGet("getAllTransactions"));
        } catch (JSONException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mv.addObject("transactions", transactions);
        mv.addObject("transactionsLength", transactions.length());
        
        
        return mv;
    }
    
    @RequestMapping(value = {"/updateTransaction"}, method = RequestMethod.POST)
    @ResponseBody
public String updateTransaction(HttpServletRequest request,
HttpServletResponse response, @RequestParam("id") String transactionId, @RequestParam("status") String status
) throws JSONException {
   JSONObject js = new JSONObject();
   JSONObject res = new JSONObject();   
            js.put("transactionId", transactionId);
            js.put("status", status);
            logger.info("data hitting..."+js);
            try {
             res = new JSONObject(restCall.executeRequest("updateTransaction", js));
            }
            catch(Exception ex){
                logger.info("Exception occured..."+ex);
                return "01";
            }
            String resp = res.getString("responseCode");
            return resp;
}

@RequestMapping({"/users"})
    public ModelAndView users(){
        ModelAndView mv = new ModelAndView("users");
        JSONArray users = new JSONArray();
        try{
        users = new JSONArray(restCall.executeGet("getUsers"));
        } catch (JSONException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mv.addObject("users", users);
        mv.addObject("usersLength", users.length());
        
        
        return mv;
    }
    
  @RequestMapping({"/faq"})
    public ModelAndView faq(){
        ModelAndView mv = new ModelAndView("faq");
        
        return mv;
    }
    
    @RequestMapping({"/suggestions"})
    public ModelAndView suggestions(){
        ModelAndView mv = new ModelAndView("suggestions");
        JSONArray suggestions = new JSONArray();
        try{
        suggestions = new JSONArray(restCall.executeGet("getAllSuggestions"));
        } catch (JSONException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mv.addObject("suggestions", suggestions);
        mv.addObject("suggestionsLength", suggestions.length());
        
        
        return mv;
    }

    
}
