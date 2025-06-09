package org.demo1.controller;

import org.demo1.model.Image;
import org.demo1.service.ImageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/images")
public class ImageWebController {

    private final ImageService imageService;

    public ImageWebController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping
    public String showGallery(Model model) {
        List<Image> images = imageService.getAll();
        model.addAttribute("images", images);
        return "images"; // thymeleaf template
    }

    @PostMapping("/upload-form")
    public String uploadFromForm(@RequestParam("file") MultipartFile file) throws Exception {
        imageService.save(file);
        return "redirect:/images";
    }
}
