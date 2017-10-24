package com.babytrak24.controller;

import java.io.File;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.babytrak24.model.Login;
import com.babytrak24.model.Transaction;
import com.babytrak24.model.UberImage;
import com.babytrak24.service.ImageService;
import com.babytrak24.service.TransactionService;
import com.babytrak24.util.CustomFileUtils;

@Controller
@EnableWebMvc
@SessionAttributes("login")
public class BabyTrak24AppController {

	@Autowired
	private TransactionService transactionService;
	private ImageService imageService = new ImageService();

	public static final Logger logger = LoggerFactory.getLogger(BabyTrak24AppController.class);

	/**
	 * Redirect to the Home Page ( View Mapping )
	 */
	@GetMapping("")
	public String index(ModelMap model) {
		return "redirect:/login";
	}

	/**
	 * View and Model retrieved Retrieve all user transactions matching login email,
	 * and looping through the transactions to retrieve all image details from S3
	 * Load all the details to a UberImage POJO for easy looping in JSTL
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView showAllImages(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("login") Login login) {
		ModelAndView mav = new ModelAndView("home");
		Transaction transaction = new Transaction();
		transaction.setEmail(login.getEmail());
		List<Transaction> imageTransactions = transactionService.findAll(Example.<Transaction>of(transaction,
				ExampleMatcher.matching().withMatcher("email", ExampleMatcher.GenericPropertyMatchers.ignoreCase())));
		List<UberImage> uberImages = new ArrayList<UberImage>();
		imageTransactions.forEach(imageTran -> {
			UberImage uberImage = new UberImage();
			uberImage.setImagePath(imageService.read(imageTran.getFileName()));
			uberImage.setTransaction(imageTran);
			uberImages.add(uberImage);
		});
		mav.addObject("login", login);
		mav.addObject("uberImages", uberImages);
		return mav;
	}

	/**
	 * View Mapping Display the Upload View
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("login") Login login) {
		ModelAndView mav = new ModelAndView("upload");
		mav.addObject("login", login);
		return mav;
	}

	/**
	 * View Mapping Display the Update View similar to Upload, however this display
	 * Image and its details Find Image using the transaction id of the image
	 */
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("login") Login login, @RequestParam("id") Long id) {
		Transaction transaction = transactionService.findOne(id);
		UberImage uberImage = new UberImage();
		uberImage.setImagePath(imageService.read(transaction.getFileName()));
		uberImage.setTransaction(transaction);

		ModelAndView mav = new ModelAndView("update");
		mav.addObject("login", login);
		mav.addObject("uberImage", uberImage);
		return mav;
	}

	/**
	 * View Mapping Display the Delete View similar to Upload, however this will
	 * just have a button to delete Find Image using the transaction id of the image
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("login") Login login, @RequestParam("id") Long id) {
		Transaction transaction = transactionService.findOne(id);
		UberImage uberImage = new UberImage();
		uberImage.setImagePath(imageService.read(transaction.getFileName()));
		uberImage.setTransaction(transaction);

		ModelAndView mav = new ModelAndView("delete");
		mav.addObject("login", login);
		mav.addObject("uberImage", uberImage);
		return mav;
	}

	/**
	 * Multipart file retrieved is converted to a file, using CustomUtils and
	 * uploaded to S3 The transaction details along with fileName, fileDescription
	 * is updated in the Transacations table
	 */
	@PostMapping("/uploadImage")
	public String uploadImage(@ModelAttribute("login") Login login, @RequestParam("file") MultipartFile mpartfile,
			@ModelAttribute("transaction") Transaction transaction, ModelMap modelMap) {

		String nextlocation = "redirect:/home";
		try {
			File file = CustomFileUtils.convertFromMultiPart(mpartfile);
			if (!(file.length() / 1024 / 1024 > 1)) {
				imageService.add(transaction.getFileName(), file);
				transaction.setEmail(login.getEmail());
				transaction.setProfileImageS3Path(file.getName());
				transaction.setUploadTime(new Date());
				transaction.setUpdatedTime(new Date());
				transactionService.save(transaction);
			} else {
				nextlocation = "redirect:/upload";
				modelMap.put("message", "Please upload image that is less than 1MB");
			}
		} catch (Exception e) {
			nextlocation = "upload";
			modelMap.put("message", "Sorry !! failed to Upload to S3, please try again");
		}
		return nextlocation;

	}

	/**
	 * Multipart file retrieved is converted to a file, using CustomUtils and
	 * uploaded to S3 The transaction details along with fileName, fileDescription
	 * is updated in the Transacations table
	 */
	@PostMapping("/updateImage")
	public String updateImage(@ModelAttribute("login") Login login, @RequestParam("file") MultipartFile mpartfile,
			@RequestParam("id") Long id, @ModelAttribute("transaction") Transaction transaction, ModelMap modelMap) {
		String nextlocation = "redirect:/home";
		try {
			Transaction updatableTransaction = transactionService.findOne(id);
			File file = CustomFileUtils.convertFromMultiPart(mpartfile);
			imageService.add(updatableTransaction.getFileName(), file);
			updatableTransaction.setFileDescription(transaction.getFileDescription());
			updatableTransaction.setUpdatedTime(new Date());
			transactionService.saveAndFlush(updatableTransaction);
		} catch (Exception e) {
			nextlocation = "upload";
			modelMap.put("message", "Sorry !! failed to Upload to S3, please try again");
		}
		return nextlocation;

	}

	/**
	 * Using the transaction id, fileName details are retrieved from Transaction
	 * table and file is deleted from S3 along with transaction in the Transaction
	 * table
	 */
	@PostMapping("/deleteImage")
	public String deleteImage(@ModelAttribute("login") Login login, @RequestParam("id") Long id, ModelMap modelMap) {
		String nextlocation = "redirect:/home";
		try {
			Transaction deleteTransaction = transactionService.findOne(id);
			imageService.delete(deleteTransaction.getFileName());

			transactionService.delete(id);
		} catch (Exception e) {
			nextlocation = "redirect:/delete?id=" + id;
			modelMap.put("message", "Sorry !! failed to Delete in S3, please try again");
		}
		return nextlocation;

	}

}
