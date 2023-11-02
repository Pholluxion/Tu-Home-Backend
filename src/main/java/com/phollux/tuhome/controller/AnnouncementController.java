package com.phollux.tuhome.controller;

import com.phollux.tuhome.domain.Property;
import com.phollux.tuhome.model.AnnouncementDTO;
import com.phollux.tuhome.repos.PropertyRepository;
import com.phollux.tuhome.service.AnnouncementService;
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
@RequestMapping("/announcements")
public class AnnouncementController {

    private final AnnouncementService announcementService;
    private final PropertyRepository propertyRepository;

    public AnnouncementController(final AnnouncementService announcementService,
            final PropertyRepository propertyRepository) {
        this.announcementService = announcementService;
        this.propertyRepository = propertyRepository;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("propertyValues", propertyRepository.findAll(Sort.by("id"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Property::getId, Property::getName)));
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("announcements", announcementService.findAll());
        return "announcement/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("announcement") final AnnouncementDTO announcementDTO) {
        return "announcement/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("announcement") @Valid final AnnouncementDTO announcementDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "announcement/add";
        }
        announcementService.create(announcementDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("announcement.create.success"));
        return "redirect:/announcements";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Integer id, final Model model) {
        model.addAttribute("announcement", announcementService.get(id));
        return "announcement/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Integer id,
            @ModelAttribute("announcement") @Valid final AnnouncementDTO announcementDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "announcement/edit";
        }
        announcementService.update(id, announcementDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("announcement.update.success"));
        return "redirect:/announcements";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Integer id,
            final RedirectAttributes redirectAttributes) {
        announcementService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("announcement.delete.success"));
        return "redirect:/announcements";
    }

}
