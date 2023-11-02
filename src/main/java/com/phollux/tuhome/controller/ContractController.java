package com.phollux.tuhome.controller;

import com.phollux.tuhome.domain.Property;
import com.phollux.tuhome.domain.User;
import com.phollux.tuhome.model.ContractDTO;
import com.phollux.tuhome.repos.PropertyRepository;
import com.phollux.tuhome.repos.UserRepository;
import com.phollux.tuhome.service.ContractService;
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
@RequestMapping("/contracts")
public class ContractController {

    private final ContractService contractService;
    private final UserRepository userRepository;
    private final PropertyRepository propertyRepository;

    public ContractController(final ContractService contractService,
            final UserRepository userRepository, final PropertyRepository propertyRepository) {
        this.contractService = contractService;
        this.userRepository = userRepository;
        this.propertyRepository = propertyRepository;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("tenantValues", userRepository.findAll(Sort.by("id"))
                .stream()
                .collect(CustomCollectors.toSortedMap(User::getId, User::getName)));
        model.addAttribute("landlordValues", propertyRepository.findAll(Sort.by("id"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Property::getId, Property::getName)));
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("contracts", contractService.findAll());
        return "contract/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("contract") final ContractDTO contractDTO) {
        return "contract/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("contract") @Valid final ContractDTO contractDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "contract/add";
        }
        contractService.create(contractDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("contract.create.success"));
        return "redirect:/contracts";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Integer id, final Model model) {
        model.addAttribute("contract", contractService.get(id));
        return "contract/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Integer id,
            @ModelAttribute("contract") @Valid final ContractDTO contractDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "contract/edit";
        }
        contractService.update(id, contractDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("contract.update.success"));
        return "redirect:/contracts";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Integer id,
            final RedirectAttributes redirectAttributes) {
        contractService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("contract.delete.success"));
        return "redirect:/contracts";
    }

}
