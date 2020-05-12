package drawgame;

import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@Controller
@RestController
@RequestMapping("/api/")
public class ApiController {
    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);
    
    @Autowired
    database db;
    
    Integer findIdbyusername(String username) {
        Integer result = -1;
        List<Person> list = db.findAll();
        Iterator<Person> iterator = list.iterator();
          while(iterator.hasNext()) {
             Person currentUser = iterator.next();
             String currentUsername = currentUser.username;
             if (currentUsername.equals(username)) {
                 result = currentUser.Id;
                 return result;
             }
          }
        
        
        return result;
    }
    
    @RequestMapping(value= "addf",method= RequestMethod.POST)
    public String TestPost(HttpServletRequest request) {
        try{
            String name = request.getParameter("name");
            Person user = db.findById(Integer.parseInt(request.getParameter("id"))).get();
            Integer fid = findIdbyusername(request.getParameter("name"));
            if (fid == -1) {
                return "Could not find friend username: " + request.getParameter("name");
            }
            user.addFriend(fid, request.getParameter("name"));
            db.save(user);
            logger.info("user : "+ name);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }
    
    @RequestMapping(value= "delf",method= RequestMethod.POST)
    String deleteFriend(HttpServletRequest request){
        String name = request.getParameter("name");
        Person user = db.findById(Integer.parseInt(request.getParameter("id"))).get();
        Integer fid = findIdbyusername(request.getParameter("name"));
        user.deleteFriend(fid);
        db.save(user);
        logger.info("user : "+ name);
        return " your friend has been deleted";
    }
}
