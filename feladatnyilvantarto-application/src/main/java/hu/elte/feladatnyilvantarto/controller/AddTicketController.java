package hu.elte.feladatnyilvantarto.controller;

import hu.elte.feladatnyilvantarto.service.TicketService;
import hu.elte.feladatnyilvantarto.webdomain.form.AddTicketRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AddTicketController {

    @Autowired
    private TicketService ticketService;

    @RequestMapping("/addticket")
    public String addTicket(Model model) {

        if(!model.containsAttribute("addticketform")) {
            model.addAttribute("addticketform", new AddTicketRequest());
        }

        return "addticket";
    }
}
