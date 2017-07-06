package com.ibm.SkillsMatrix.Controller;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.expression.Maps;

import com.cloudant.client.api.Database;
import com.cloudant.client.api.model.Response;
import com.ibm.SkillsMatrix.Bean.Person;
import com.ibm.SkillsMatrix.Business.PersonService;

@Controller
@RestController
@RequestMapping("/person")

public class PersonController {

	@Autowired
	Database skillsmatrixdb;
	@Autowired
	PersonService personService;
	

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Person> getAll() {
		return personService.getAll();
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public Response saveUser(@RequestBody Person person) {
		return personService.addPerson(person);

	}

	@RequestMapping(value = "/getOne", method = RequestMethod.GET)
	public Person getPerson(@RequestParam String Id) {
		return personService.getUser(Id);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public Response updatePerson(@RequestBody Person person) {
		return personService.updateUser(person);
	}

	@RequestMapping(value = "/upload")
	public void uploadFile(@RequestParam("uploadedFile") MultipartFile uploadedFileRef) {
		Person user=new Person();
		String fileName = uploadedFileRef.getOriginalFilename();
		String path = "C:/" + fileName;
		byte[] buffer = new byte[100000];
		File outputFile = new File(path);
		user.setAttachment(path);
		FileInputStream reader = null;
		FileOutputStream writer = null;
		int totalBytes = 0;
		try {
			outputFile.createNewFile();
			reader = (FileInputStream) uploadedFileRef.getInputStream();
			writer = new FileOutputStream(outputFile);
			int bytesRead = 0;
			while ((bytesRead = reader.read(buffer)) != -1) {
				writer.write(buffer);
				totalBytes += bytesRead;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public void upload(@RequestParam("uploadfile") MultipartFile uploadfile) {
	  
	  try {
	    // Get the filename and build the local file path (be sure that the 
	    // application have write permissions on such directory)
	
	    String filename = uploadfile.getOriginalFilename();
	    String directory = "C:/Users/IBM_ADMIN/Documents/workspace-sts-3.8.4.RELEASE/IbmSkillsMatrix/src/main/resources/static/images";
	    String filepath = Paths.get(directory, filename).toString();
	    
	    // Save the file locally
	    BufferedOutputStream stream =
	        new BufferedOutputStream(new FileOutputStream(new File(filepath)));
	    stream.write(uploadfile.getBytes());
	    stream.close();
	  }
	  catch (Exception e) {
	    System.out.println(e.getMessage());
	    
	  }
	  
	  
	} 
	  
  
}
