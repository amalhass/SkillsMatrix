package com.ibm.SkillsMatrix.Controller;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	@Autowired
	ServletContext servletContext;

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

/*	@RequestMapping(value = "/upload")
	public void uploadFile(@RequestParam("uploadedFile") MultipartFile uploadedFileRef) {
		Person user = new Person();
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

	 //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "C:/Users/IBM_ADMIN/Documents/workspace-sts-3.8.4.RELEASE/IbmSkillsMatrix/src/main/resources/static/images/";
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public void upload(@RequestParam("uploadfile") MultipartFile uploadfile) {

		try {
			// Get the filename and build the local file path (be sure that the
			// application have write permissions on such directory)

			String filename = uploadfile.getOriginalFilename();
			File newFile = new File(filename);
			newFile.createNewFile();
			File f1 = new File(
					"C:/Users/IBM_ADMIN/Documents/workspace-sts-3.8.4.RELEASE/IbmSkillsMatrix/src/main/resources/static/images/zineb.jpg");
			newFile.renameTo(f1);
			// String directory =
			// "C:/Users/IBM_ADMIN/Documents/workspace-sts-3.8.4.RELEASE/IbmSkillsMatrix/src/main/resources/static/images";
			// String filepath = Paths.get(directory, filename).toString();

			FileWriter fw = new FileWriter(newFile.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			// bw.write(bw);
			bw.close();
			// Save the file locally
			// BufferedOutputStream stream = new BufferedOutputStream(new
			// FileOutputStream(new File(filepath)));
			// stream.write(uploadfile.getBytes());
			// stream.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}

	}
	

	    @PostMapping("/uploadi") // //new annotation since 4.3
	    public void singleFileUpload(@RequestParam("file") MultipartFile file,
	                                   RedirectAttributes redirectAttributes) {

	        if (file.isEmpty()) {
	            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
	          //  return "redirect:uploadStatus";
	        }

	        try {

	            // Get the file and save it somewhere
	            byte[] bytes = file.getBytes();
	            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
	            Files.write(path, bytes);

	           // redirectAttributes.addFlashAttribute("message","You successfully uploaded '" + file.getOriginalFilename() + "'");

	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	    
	    }*/
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> uploadFile(
	    @RequestParam("uploadfile") MultipartFile uploadfile) {
	  
	  try {
	    // Get the filename and build the local file path (be sure that the 
	    // application have write permissions on such directory)
	    String filename = uploadfile.getOriginalFilename();
	    String directory = "C:/Users/IBM_ADMIN/Documents/workspace-sts-3.8.4.RELEASE/IbmSkillsMatrix/uploads";
	    String filepath = Paths.get(directory, filename).toString();
	    
	    // Save the file locally
	    BufferedOutputStream stream =
	        new BufferedOutputStream(new FileOutputStream(new File(filepath)));
	    stream.write(uploadfile.getBytes());
	    stream.close();
	  }
	  catch (Exception e) {
	    System.out.println(e.getMessage());
	    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	  }
	  
	  return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/getBy", method = RequestMethod.GET)
	public List<Person> SearchByCountry(@RequestParam String country, @RequestParam String site,
			@RequestParam String ibmId, @RequestParam String Skill) {
		System.out.println("SearchByCountry in:" + site);
		System.out.println("SearchByCountry in:" + country);
		String requeteDeb = "\"selector\":{";
		String countryR = "\"Country\":" + country;
		String siteR = "\"Site\":" + site;
		boolean comma = false;
		String ibmIdR = "\"IbmId\":" + ibmId;
		String skillsR = "\"Skills\":{\"$elemMatch\":{\"Name\":" + Skill + "}}";
		if (!country.equals("\"\"")) {
			// return skillsmatrixdb.findByIndex("\"selector\":{ \"Site\":" +
			// site + ", \"IbmId\":" + ibmId+ "
			// ,\"Skills\":{\"$elemMatch\":{\"Name\":" + Skill + "}}}",
			// Person.class);
			requeteDeb += countryR;
			comma=true;
		}
		if (!site.equals("\"\"")) {
			if (comma == true) {
				requeteDeb += ",";
			}
			// return skillsmatrixdb.findByIndex("\"selector\":{ \"IbmId\":" +
			// ibmId + " ,\"Skills\":{\"$elemMatch\":{\"Name\":" + Skill +
			// "}}}", Person.class);
			requeteDeb += siteR;
			comma=true;
			System.out.println(requeteDeb);
		}
		if (!ibmId.equals("\"\"")) {
			if (comma == true) {
				requeteDeb += ",";
			}
			requeteDeb += ibmIdR;
			comma=true;
			System.out.println(requeteDeb);
			// return skillsmatrixdb.findByIndex("\"selector\":{ \"Country\":" +
			// country + ", \"Site\":" + site+ "
			// ,\"Skills\":{\"$elemMatch\":{\"Name\":" + Skill + "}}}",
			// Person.class);

		}
		if (!Skill.equals("\"\"")) {
			if (comma == true) {
				requeteDeb += ",";
			}
			requeteDeb += skillsR;
			comma=true;
			System.out.println(requeteDeb);
			// return skillsmatrixdb.findByIndex("\"selector\":{ \"Country\":" +
			// country + ", \"Site\":" + site + ", \"IbmId\":" + ibmId +
			// "}",Person.class);
		}
		// return skillsmatrixdb.findByIndex("\"selector\":{ \"Country\":" +
		// country + ", \"Site\":" + site+ ", \"IbmId\":" + ibmId + "
		// ,\"Skills\":{\"$elemMatch\":{\"Name\":" + Skill + "}}}",
		// Person.class);

		return skillsmatrixdb.findByIndex(requeteDeb + "}", Person.class);

	}

}
