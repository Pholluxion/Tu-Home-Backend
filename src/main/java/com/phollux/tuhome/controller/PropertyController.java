package com.phollux.tuhome.controller;

import com.phollux.tuhome.domain.FurnitureInventory;
import com.phollux.tuhome.domain.TypeOfProperty;
import com.phollux.tuhome.model.PropertyDTO;
import com.phollux.tuhome.repos.FurnitureInventoryRepository;
import com.phollux.tuhome.repos.TypeOfPropertyRepository;
import com.phollux.tuhome.service.PropertyService;
import com.phollux.tuhome.util.CustomCollectors;
import com.phollux.tuhome.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
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
@RequestMapping("/properties")
public class PropertyController {

    private final PropertyService propertyService;
    private final TypeOfPropertyRepository typeOfPropertyRepository;
    private final FurnitureInventoryRepository furnitureInventoryRepository;

    public PropertyController(final PropertyService propertyService,
            final TypeOfPropertyRepository typeOfPropertyRepository,
            final FurnitureInventoryRepository furnitureInventoryRepository) {
        this.propertyService = propertyService;
        this.typeOfPropertyRepository = typeOfPropertyRepository;
        this.furnitureInventoryRepository = furnitureInventoryRepository;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("typeOfPropertyValues", typeOfPropertyRepository.findAll(Sort.by("id"))
                .stream()
                .collect(CustomCollectors.toSortedMap(TypeOfProperty::getId, TypeOfProperty::getName)));
        model.addAttribute("furnitureInventoryValues", furnitureInventoryRepository.findAll(Sort.by("id"))
                .stream()
                .collect(CustomCollectors.toSortedMap(FurnitureInventory::getId, FurnitureInventory::getFurnitureType)));
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("properties", propertyService.findAll());
        return "property/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("property") final PropertyDTO propertyDTO) {
        return "property/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("property") @Valid final PropertyDTO propertyDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "property/add";
        }
        propertyService.create(propertyDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("property.create.success"));
        return "redirect:/properties";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Integer id, final Model model) {
        model.addAttribute("property", propertyService.get(id));
        return "property/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Integer id,
            @ModelAttribute("property") @Valid final PropertyDTO propertyDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "property/edit";
        }
        propertyService.update(id, propertyDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("property.update.success"));
        return "redirect:/properties";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Integer id,
            final RedirectAttributes redirectAttributes) {
        final String referencedWarning = propertyService.getReferencedWarning(id);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            propertyService.delete(id);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("property.delete.success"));
        }
        return "redirect:/properties";
    }

}
