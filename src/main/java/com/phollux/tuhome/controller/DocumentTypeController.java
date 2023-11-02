package com.phollux.tuhome.controller;

import com.phollux.tuhome.model.DocumentTypeDTO;
import com.phollux.tuhome.service.DocumentTypeService;
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
@RequestMapping("/documentTypes")
public class DocumentTypeController {

    private final DocumentTypeService documentTypeService;

    public DocumentTypeController(final DocumentTypeService documentTypeService) {
        this.documentTypeService = documentTypeService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("documentTypes", documentTypeService.findAll());
        return "documentType/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("documentType") final DocumentTypeDTO documentTypeDTO) {
        return "documentType/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("documentType") @Valid final DocumentTypeDTO documentTypeDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (!bindingResult.hasFieldErrors("name") && documentTypeService.nameExists(documentTypeDTO.getName())) {
            bindingResult.rejectValue("name", "Exists.documentType.name");
        }
        if (!bindingResult.hasFieldErrors("abbreviation") && documentTypeService.abbreviationExists(documentTypeDTO.getAbbreviation())) {
            bindingResult.rejectValue("abbreviation", "Exists.documentType.abbreviation");
        }
        if (bindingResult.hasErrors()) {
            return "documentType/add";
        }
        documentTypeService.create(documentTypeDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("documentType.create.success"));
        return "redirect:/documentTypes";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Long id, final Model model) {
        model.addAttribute("documentType", documentTypeService.get(id));
        return "documentType/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Long id,
            @ModelAttribute("documentType") @Valid final DocumentTypeDTO documentTypeDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        final DocumentTypeDTO currentDocumentTypeDTO = documentTypeService.get(id);
        if (!bindingResult.hasFieldErrors("name") &&
                !documentTypeDTO.getName().equalsIgnoreCase(currentDocumentTypeDTO.getName()) &&
                documentTypeService.nameExists(documentTypeDTO.getName())) {
            bindingResult.rejectValue("name", "Exists.documentType.name");
        }
        if (!bindingResult.hasFieldErrors("abbreviation") &&
                !documentTypeDTO.getAbbreviation().equalsIgnoreCase(currentDocumentTypeDTO.getAbbreviation()) &&
                documentTypeService.abbreviationExists(documentTypeDTO.getAbbreviation())) {
            bindingResult.rejectValue("abbreviation", "Exists.documentType.abbreviation");
        }
        if (bindingResult.hasErrors()) {
            return "documentType/edit";
        }
        documentTypeService.update(id, documentTypeDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("documentType.update.success"));
        return "redirect:/documentTypes";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        final String referencedWarning = documentTypeService.getReferencedWarning(id);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            documentTypeService.delete(id);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("documentType.delete.success"));
        }
        return "redirect:/documentTypes";
    }

}
