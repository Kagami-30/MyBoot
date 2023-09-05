package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.repository.PersonRepository;

import jakarta.transaction.Transactional;

@Controller
public class MyController{

	
	@Autowired
	PersonRepository repository;

@RequestMapping("/")
public ModelAndView index(@ModelAttribute("formModel") Person Person,ModelAndView mav) {
	mav.setViewName("index");
	mav.addObject("title","Hello page");
	mav.addObject("msg","this is JPA sample data");
	List<Person> list = repository.findAll();
	mav.addObject("data",list);
	return mav;
}

@RequestMapping(value = "/", method = RequestMethod.POST)
@Transactional
public ModelAndView form(
		@ModelAttribute("formModel")@Validated Person person,
		BindingResult result,
		ModelAndView mav) {
	ModelAndView res = null;
	System.out.println(result.getFieldErrors());
	if(!result.hasErrors()) {
		repository.saveAndFlush(person);
		res = new ModelAndView("redirect:/");
	}else {
		mav.setViewName("index");
		mav.addObject("title","Hello page");
		mav.addObject("msg","sorry, error is occored...");
		Iterable<Person> list = repository.findAll();
		mav.addObject("datalist",list);
		res = mav;
	}
	
	return res;
}
}


























//@Controller
//public class MyController{

	/*
	@Autowired
	PersonRepository repository;

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute Person Person, 
			@PathVariable int id, ModelAndView mav) {
		mav.setViewName("edit");
		mav.addObject("title","edit Person");
		Optional<Person> data = repository.findById((long)id);
		mav.addObject("formModel",data.get());
		return mav;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@Transactional
	public ModelAndView update(@ModelAttribute Person Person,
			ModelAndView mav) {
		repository.saveAndFlush(Person);
		return new ModelAndView("redirect:/");
	}
	
	
	
	@RequestMapping("/")
	public ModelAndView index(
			@ModelAttribute("formModel") Person Person,
			ModelAndView mav) {
		mav.setViewName("index");
		mav.addObject("title","Hello page");
		mav.addObject("msg","this is JPA sample data");
		List<Person> list = repository.findAll();
		mav.addObject("data",list);
		return mav;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@Transactional
	public ModelAndView form(
			@ModelAttribute("formModel")Person Person,
			ModelAndView mav) {
		repository.saveAndFlush(Person);
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable int id, 
			ModelAndView mav) {
		mav.setViewName("delete");
		mav.addObject("title","Delete Person");
		mav.addObject("msg","Can I delete this record");
		Optional<Person> data = repository.findById((long)id);
		mav.addObject("formModel",data.get());
		return mav;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@Transactional
	public ModelAndView remove(@RequestParam long id,
			ModelAndView mav) {
		repository.deleteById(id);
		return new ModelAndView("redirect:/");
	}
	
	 @PostConstruct
	    public void init () {
	        //１つ目のダミーデータ作成
	        Person p1 = new Person();
	        p1.setName("taro");
	        p1.setAge(39);
	        p1.setMail("taro@yamada");
	        repository.saveAndFlush(p1);
	        //２つ目のダミーデータ作成
	        Person p2 = new Person();
	        p2.setName("hanako");
	        p2.setAge(28);
	        p2.setMail("hanako@flower");
	        repository.saveAndFlush(p2);
	        //３つ目のダミーデータ作成
	        Person p3 = new Person();
	        p3.setName("sachico");
	        p3.setAge(17);
	        p3.setMail("sachico@happy");
	        repository.saveAndFlush(p3);
	        
	    }

	/* 
	 @RequestMapping(value = "/edit{id}", method = RequestMethod.GET)
	 public ModelAndView edit(
			 @ModelAttribute Person Person,
			 @PathVariable int id,ModelAndView mav) {
		 mav.setViewName("edit");
		 mav.addObject("title","edit Person.");
		 Optional<Person> data = repository.findById((long)id);
		 mav.addObject("formModel",data.get());
		 return mav;
	 }

	 @RequestMapping(value = "/edit{id}", method = RequestMethod.POST)
	 @Transactional
	 public ModelAndView update(
			 @ModelAttribute Person Person,
			 ModelAndView mav) {
		 repository.saveAndFlush(Person);
		 return new ModelAndView("redirect:/");
	 }
	 */



/*
@Controller
public class MyController{
	
	@Autowired
	PersonRepository repository;
	
	@RequestMapping("/")
	public ModelAndView index(
			@ModelAttribute("formModel") Person Person,
			ModelAndView mav) {
		mav.setViewName("index");
		mav.addObject("title","Hello page");
		mav.addObject("msg","this is JPA sample data");
		List<Person> list = repository.findAll();
		mav.addObject("data",list);
		return mav;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@Transactional
	public ModelAndView form(
			@ModelAttribute("formModel")Person Person,
			ModelAndView mav) {
		repository.saveAndFlush(Person);
		return new ModelAndView("ewdirect:/");
	}
	
	 @PostConstruct
	    public void init () {
	        //１つ目のダミーデータ作成
	        Person p1 = new Person();
	        p1.setName("taro");
	        p1.setAge(39);
	        p1.setMail("taro@yamada");
	        repository.saveAndFlush(p1);
	        //２つ目のダミーデータ作成
	        Person p2 = new Person();
	        p2.setName("hanako");
	        p2.setAge(28);
	        p2.setMail("hanako@flower");
	        repository.saveAndFlush(p2);
	        //３つ目のダミーデータ作成
	        Person p3 = new Person();
	        p3.setName("sachico");
	        p3.setAge(17);
	        p3.setMail("sachico@happy");
	        repository.saveAndFlush(p3);
	    }
}
*/

/*
@Controller
public class MyController{
	
	@Autowired
	PersonRepository repository;
	
	@RequestMapping("/")
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("index");
		mav.addObject("title","Hello page");
		mav.addObject("msg","this is JPA sample data");
		Iterable<Person> list = repository.findAll();
		mav.addObject("data",list);
		return mav;
	}
	
	
	@PostConstruct
    public void init () {
        //１つ目のダミーデータ作成
        Person p1 = new Person();
        p1.setName("taro");
        p1.setAge(39);
        p1.setMail("taro@yamada");
        repository.saveAndFlush(p1);
        //２つ目のダミーデータ作成
        Person p2 = new Person();
        p2.setName("hanako");
        p2.setAge(28);
        p2.setMail("hanako@flower");
        repository.saveAndFlush(p2);
        //３つ目のダミーデータ作成
        Person p3 = new Person();
        p3.setName("sachico");
        p3.setAge(17);
        p3.setMail("sachico@happy");
        repository.saveAndFlush(p3);
    }
}
*/
/*
package com.example.demo;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
*/
/*
public class MyController {
	@RequestMapping("/{num}")
	public ModelAndView index(@PathVariable int num,ModelAndView mav) {
		mav.setViewName("home");
		mav.addObject("num",num);
		if(num >= 0) {
			mav.addObject("check","num >= data.size()?0:num");
		}else {
			mav.addObject("check","num >= data.size()-1?0:num * -1");
		}
		ArrayList<DataObject>data = new ArrayList<DataObject>();
		data.add(new DataObject (0, "taro","taro@yamada"));
		data.add(new DataObject (1, "hanako","hanako@flower"));
		data.add(new DataObject (2,"sachiko","sachiko@happy"));
		mav.addObject("data",data);
		return mav;
	}
*/	
	/*
	@RequestMapping("/")
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("home");
		ArrayList<String[]>data = new ArrayList<String[]>();
		data.add(new String[] {"taro","taro@yamada", "090-999-999"});
		data.add(new String[] {"hanako","hanako@flower", "080-888-888"});
		data.add(new String[] {"sachiko","sachiko@happy", "080-888-888"});
		mav.addObject("data",data);
		return mav;
	}
	*/
	/*
	//@RequestMapping(value="/", method=RequestMethod.GET)
	//@RequestMapping("/{id}")
	//public ModelAndView index(@PathVariable int id,ModelAndView mav) {
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("home");
		mav.addObject("id",id);
		mav.addObject("check",id %2 == 0);
		mav.addObject("trueVal","Even number");
		mav.addObject("falseVal","Odd number");
		mav.addObject("msg","message 1<hr/>message 2<br/>message 3");
		return mav;
	}
	*/
	/*
	@RequestMapping(value="/", method=RequestMethod.POST)
	public ModelAndView form(
			@RequestParam(value="check1", required=false) boolean check1,
			@RequestParam(value="radio1", required=false) String radio1,
			@RequestParam(value="select1", required=false) String select1,
			@RequestParam(value="select2", required=false) String[] select2,
			ModelAndView mav) {
		String res = "";
		try {
			res = "check:" + check1 +
			" radio:" + radio1 +
			" select:" + select1 +
			"\nselect2:";
		} catch (NullPointerException e) {}
		try {
			res += select2[0];
			for(int i = 1; i < select2.length; i++) {
				res += ", " + select2[i];
			}
		}catch (NullPointerException e) {
			res += "null";
		}
		
		mav.addObject("msg",res);
		mav.setViewName("index");
		return mav;
	}*/

