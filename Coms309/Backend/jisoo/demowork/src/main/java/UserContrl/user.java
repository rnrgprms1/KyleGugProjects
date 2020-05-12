package UserContrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
//@RestController
public class user {
	
	@Autowired
	database db;
	
	@GetMapping("/Person/{id}")
	Person getPerson(@PathVariable Integer Id_number) 
	{
		return db.getOne(Id_number);
	}
	
	@GetMapping("/Person/{password}")
	Person getpass(@PathVariable Integer passward)
	{
		return db.getOne(passward);
	}
	@GetMapping("/Person/{friend}")
	Person getFirend(@PathVariable Integer friend)
		{
			return db.getOne(friend);
		}
		
	@RequestMapping("/Person")
	List<Person> hello() {
		return db.findAll();
	}
	
	@PostMapping("/person")
	Person createPerson(@RequestBody Person p) {
		db.save(p);
		return p;
	}
	
	
	
	
	}


