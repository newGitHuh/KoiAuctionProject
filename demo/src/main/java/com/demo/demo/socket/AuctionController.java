package com.demo.demo.socket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class AuctionController  {
    @MessageMapping("/bid")
    @SendTo("/topic/auction")
    public Bid handleBid(Bid bid) throws Exception {
        // Process bid, e.g., save to DB, perform validation
        // Return the updated bid information to all subscribers
        return bid;
    }
}
