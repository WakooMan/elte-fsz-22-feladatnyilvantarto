package hu.elte.feladatnyilvantarto.controller;

import hu.elte.feladatnyilvantarto.domain.Group;
import hu.elte.feladatnyilvantarto.domain.Priority;
import hu.elte.feladatnyilvantarto.domain.Ticket;
import hu.elte.feladatnyilvantarto.domain.User;
import hu.elte.feladatnyilvantarto.service.GroupsService;
import hu.elte.feladatnyilvantarto.service.TicketService;
import hu.elte.feladatnyilvantarto.service.UserService;

import hu.elte.feladatnyilvantarto.webdomain.form.AddTicketRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AddTicketController extends AuthenticatedControllerBase {

    @Autowired
    private TicketService ticketService;
    @Autowired
    private GroupsService groupsService;

    @Autowired
    private UserService userService;

    @RequestMapping("/addticket")
    public String addTicket(Model model) {

        if(!model.containsAttribute("addticketform")) {
            model.addAttribute("addticketform", new AddTicketRequest());
        }
        model.addAttribute("groups", groupsService.listExhaustiveGroupsOfUser(GetAuthenticatedUser()));
        model.addAttribute("fullname",GetAuthenticatedUser().getName());

        return "addticket";
    }

    @PostMapping("/addticket/action")
    public String addTicketAction(@Valid AddTicketRequest addticketform,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors())
        {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addticketform",bindingResult);
            redirectAttributes.addFlashAttribute("addticketform",addticketform);
            return "redirect:/addticket";
        }
        else
        {
            Group group = groupsService.getGroupById(addticketform.getGroupId());
            ArrayList<User> user= new ArrayList<>();
            LocalDateTime dl = LocalDateTime.parse(addticketform.getDeadline());
            ticketService.createTicket(addticketform.getName(),addticketform.getDescription(),GetAuthenticatedUser(),
                    user, dl, false,
                    group, Priority.valueOf(addticketform.getPriority()) );
            return "redirect:/tickets";
        }
    }
}
