package com.phollux.tuhome.controller;

import com.phollux.tuhome.model.FurnitureInventoryDTO;
import com.phollux.tuhome.service.FurnitureInventoryService;
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
@RequestMapping("/furnitureInventories")
public class FurnitureInventoryController {

    private final FurnitureInventoryService furnitureInventoryService;

    public FurnitureInventoryController(final FurnitureInventoryService furnitureInventoryService) {
        this.furnitureInventoryService = furnitureInventoryService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("furnitureInventories", furnitureInventoryService.findAll());
        return "furnitureInventory/list";
    }

    @GetMapping("/add")
    public String add(
            @ModelAttribute("furnitureInventory") final FurnitureInventoryDTO furnitureInventoryDTO) {
        return "furnitureInventory/add";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute("furnitureInventory") @Valid final FurnitureInventoryDTO furnitureInventoryDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "furnitureInventory/add";
        }
        furnitureInventoryService.create(furnitureInventoryDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("furnitureInventory.create.success"));
        return "redirect:/furnitureInventories";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Integer id, final Model model) {
        model.addAttribute("furnitureInventory", furnitureInventoryService.get(id));
        return "furnitureInventory/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Integer id,
            @ModelAttribute("furnitureInventory") @Valid final FurnitureInventoryDTO furnitureInventoryDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "furnitureInventory/edit";
        }
        furnitureInventoryService.update(id, furnitureInventoryDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("furnitureInventory.update.success"));
        return "redirect:/furnitureInventories";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Integer id,
            final RedirectAttributes redirectAttributes) {
        final String referencedWarning = furnitureInventoryService.getReferencedWarning(id);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            furnitureInventoryService.delete(id);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("furnitureInventory.delete.success"));
        }
        return "redirect:/furnitureInventories";
    }

}
