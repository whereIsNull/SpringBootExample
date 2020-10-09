package com.mymongo.springboot;

import java.io.IOException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mymongo.springboot.dao.MongoDao;
import com.mymongo.springboot.domain.User;
import com.mymongo.springboot.exceptions.StorageFileNotFoundException;
import com.mymongo.springboot.repository.UserRepository;

@Controller
public class UploadFormController {

	@Autowired
	private UserRepository userRepository;

	
	public UploadFormController() {
	}

	@GetMapping("/movies")
	public String listUploadedFiles(Model model) throws IOException {

//		model.addAttribute("files", mongoDao.loadAll().map(
//				path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
//						"serveFile", path.getFileName().toString()).build().toUri().toString())
//				.collect(Collectors.toList()));
		
		model.addAttribute("user", new User());

		return "uploadForm";
	}

	@GetMapping("/movies/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

		Resource file = null;//mongoDao.loadAsResource(filename);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}

	@PostMapping("/movies")
	public String handleFileUpload(@RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) {

//		mongoDao.store(file);
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");
		System.out.println("uploaded: " + file.getOriginalFilename());
		return "redirect:/movies";
	}
	
	@PostMapping("/user")
	public String handleUserUpload(@ModelAttribute User user, Model model) {
		userRepository.save(user);
		return "redirect:/movies";
	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}

}