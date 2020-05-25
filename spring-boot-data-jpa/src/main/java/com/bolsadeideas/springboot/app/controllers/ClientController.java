package com.bolsadeideas.springboot.app.controllers;

import java.net.MalformedURLException;
import java.util.Map;
import java.io.IOException;
import java.lang.Object;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.bolsadeideas.springboot.app.models.entity.Client;
import com.bolsadeideas.springboot.app.models.service.IClientService;
import com.bolsadeideas.springboot.app.models.service.IUploadFileService;
import com.bolsadeideas.springboot.app.util.paginator.PageRender;

@Controller
@SessionAttributes("client")
public class ClientController {

	@Autowired
	private IClientService clientService;

	@Autowired
	private IUploadFileService uploadFileService;

	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> showPhoto(@PathVariable String filename) {

		Resource resource = null;
		try {
			resource = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ResponseEntity.ok().header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
	}

	@GetMapping(value = "/show/{id}")
	public String show(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Client client = clientService.findOne(id);

		if (client == null) {
			flash.addFlashAttribute("error", "The client does not exist");
			return "redirect:/listclient";
		}

		model.put("client", client);
		model.put("title", "Client Detail: " + client.getName() + " " + client.getLastName());

		return "show";
	}

	@RequestMapping(value = "/listclient", method = RequestMethod.GET)
	public String toListClient(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

		Pageable pageRequest = PageRequest.of(page, 4);

		Page<Client> clients = clientService.findAll(pageRequest);

		PageRender<Client> pageRender = new PageRender<>("/listclient", clients);

		model.addAttribute("title", "Clients List");
		model.addAttribute("clients", clients);
		model.addAttribute("page", pageRender);

		return "listclient";

	}

	@RequestMapping(value = "/form")
	public String create(Map<String, Object> model) {

		Client client = new Client();
		model.put("client", client);
		model.put("title", "Client Form");

		return "form";
	}

	@RequestMapping(value = "/form/{id}")
	public String edit(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Client client = null;

		if (id > 0) {
			client = clientService.findOne(id);
			if (client == null) {
				flash.addFlashAttribute("error", "The client id does not exist");
				return "redirect:/listclient";
			}

		} else {
			flash.addFlashAttribute("error", "The client id could not be zero");
			return "redirect:/listclient";
		}

		model.put("client", client);
		model.put("title", "Edit Client");

		return "form";
	}

	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String save(@Valid Client client, BindingResult result, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) {

		if (result.hasErrors()) {

			model.addAttribute("title", "Client Form");

			return "form";
		}

		if (!foto.isEmpty()) {

			if (client.getId() != null && client.getId() > 0 && client.getFoto() != null
					&& client.getFoto().length() > 0) {

				uploadFileService.delete(client.getFoto());

			}

			String uniqueFilename = null;
			try {
				uniqueFilename = uploadFileService.copy(foto);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// String rootPath = "C://tmp/uploads"; //windows
			// String rootPath = "/home/marcoortega/temp-spring-boot/uploads";

			flash.addFlashAttribute("info", "The file '" + uniqueFilename + "' has been uploaded successfuly");
			client.setFoto(uniqueFilename);
		}

		String messageFlash = (client.getId() != null) ? "Client edited successfully" : "Client created successfully";

		clientService.saveClient(client);
		status.setComplete();
		flash.addFlashAttribute("success", messageFlash);

		return "redirect:listclient";
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			Client client = clientService.findOne(id);

			clientService.delete(id);
			flash.addFlashAttribute("success", "Client deleted successfully");

			if (uploadFileService.delete(client.getFoto())) {
				flash.addFlashAttribute("info", "Photo " + client.getFoto() + " deleted successfully");
			}

		}

		return "redirect:/listclient";
	}

}
