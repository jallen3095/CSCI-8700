package edu.uno.omahelp.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("event/")
public class EventController {

  @Autowired
    private EventDao eventDao;
    //@RequestMapping("/event/list")
    //public List<Event> listEvents() {}

    //@RequestMapping("/event/register")
    //public String registerEvent() {}
}
