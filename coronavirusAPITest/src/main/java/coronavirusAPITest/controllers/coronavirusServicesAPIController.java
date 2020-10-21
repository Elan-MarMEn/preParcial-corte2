package coronavirusAPITest.controllers;

import coronavirusAPITest.service.coronavirusServicesInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/covid19")
public class coronavirusServicesAPIController {

    @Autowired
    coronavirusServicesInterface coronavirusServices;

    @RequestMapping(value = "/countries",method = RequestMethod.GET)
    public ResponseEntity<?> getAllContries(){
        try {
            //obtener datos que se enviarán a través del API
            return new ResponseEntity<>(coronavirusServices.getAllStatistics(), HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(coronavirusServicesAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/countries/{country}",method = RequestMethod.GET)
    public ResponseEntity<?> getPrivinciesBeyContries(@PathVariable String country){
        try {
            //obtener datos que se enviarán a través del API
            return new ResponseEntity<>(coronavirusServices.getStatisticsByCountryName(country), HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(coronavirusServicesAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
