package atelier.Destinataire.Store.controller;

import  atelierASI2.CommonModel.Store.StoreOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


//ONLY FOR TEST NEED ALSO TO ALLOW CROOS ORIGIN ON WEB BROWSER SIDE
@CrossOrigin
@RestController
public class StoreRestController {

	

	@Autowired
	private StoreService storeService;
	
	@RequestMapping(method= RequestMethod.POST,value="/buy")
	private boolean getAllCards(@RequestBody StoreOrder order) {
		return storeService.buyCard(order.getUser_id(),order.getCard_id());

	}
	
	@RequestMapping(method=RequestMethod.POST,value="/sell")
	private boolean getCard(@RequestBody StoreOrder order) {
		 return storeService.sellCard(order.getUser_id(),order.getCard_id());
	}
	
}
