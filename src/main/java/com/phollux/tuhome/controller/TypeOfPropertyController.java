package com.phollux.tuhome.controller;

import com.phollux.tuhome.model.TypeOfPropertyDTO;
import com.phollux.tuhome.service.TypeOfPropertyService;
import com.phollux.tuhome.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/typeOfProperties")
public class TypeOfPropertyController {

    private final TypeOfPropertyService typeOfPropertyService;

    public TypeOfPropertyController(final TypeOfPropertyService typeOfPropertyService) {
        this.typeOfPropertyService = typeOfPropertyService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("typeOfProperties", typeOfPropertyService.findAll());
        return "typeOfProperty/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("typeOfProperty") final TypeOfPropertyDTO typeOfPropertyDTO) {
        return "typeOfProperty/add";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute("typeOfProperty") @Valid final TypeOfPropertyDTO typeOfPropertyDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "typeOfProperty/add";
        }
        typeOfPropertyService.create(typeOfPropertyDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("typeOfProperty.create.success"));
        return "redirect:/typeOfProperties";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Integer id, final Model model) {
        model.addAttribute("typeOfProperty", typeOfPropertyService.get(id));
        return "typeOfProperty/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Integer id,
            @ModelAttribute("typeOfProperty") @Valid final TypeOfPropertyDTO typeOfPropertyDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "typeOfProperty/edit";
        }
        typeOfPropertyService.update(id, typeOfPropertyDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("typeOfProperty.update.success"));
        return "redirect:/typeOfProperties";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Integer id,
            final RedirectAttributes redirectAttributes) {
        final String referencedWarning = typeOfPropertyService.getReferencedWarning(id);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            typeOfPropertyService.delete(id);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("typeOfProperty.delete.success"));
        }
        return "redirect:/typeOfProperties";
    }

}
