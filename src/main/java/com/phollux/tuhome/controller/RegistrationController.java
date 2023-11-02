package com.phollux.tuhome.controller;

import com.phollux.tuhome.domain.DocumentType;
import com.phollux.tuhome.domain.Role;
import com.phollux.tuhome.model.RegistrationRequest;
import com.phollux.tuhome.repos.DocumentTypeRepository;
import com.phollux.tuhome.repos.RoleRepository;
import com.phollux.tuhome.service.RegistrationService;
import com.phollux.tuhome.util.CustomCollectors;
import com.phollux.tuhome.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class RegistrationController {

    private final RegistrationService registrationService;
    private final RoleRepository roleRepository;
    private final DocumentTypeRepository documentTypeRepository;

    public RegistrationController(final RegistrationService registrationService, RoleRepository roleRepository, DocumentTypeRepository documentTypeRepository) {
        this.registrationService = registrationService;
        this.roleRepository = roleRepository;
        this.documentTypeRepository = documentTypeRepository;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("roleValues", roleRepository.findAll(Sort.by("id"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Role::getId, Role::getName)));
        model.addAttribute("documentTypeValues", documentTypeRepository.findAll(Sort.by("id"))
                .stream()
                .collect(CustomCollectors.toSortedMap(DocumentType::getId, DocumentType::getName)));
    }

    @GetMapping("/register")
    public String register(@ModelAttribute final RegistrationRequest registrationRequest) {
        return "registration/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute @Valid final RegistrationRequest registrationRequest,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (!bindingResult.hasFieldErrors("email") && registrationService.emailExists(registrationRequest)) {
            bindingResult.rejectValue("email", "registration.register.taken");
        }
        if (bindingResult.hasErrors()) {
            return "registration/register";
        }
        registrationService.register(registrationRequest);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("registration.register.success"));
        return "redirect:/login";
    }

}
