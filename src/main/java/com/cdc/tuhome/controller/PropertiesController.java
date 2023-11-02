package com.cdc.tuhome.controller;

import com.cdc.tuhome.dto.PropertiesDTO;
import com.cdc.tuhome.service.interfaces.IPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/properties")
public class PropertiesController {
    private IPropertiesService propertiesService;
    
    @Autowired
    public void setPropertiesService(IPropertiesService propertyService) {
        this.propertiesService = propertyService; 
    }

    @GetMapping("/view/list")
    public String getProperties(Model model) {
        List<PropertiesDTO> prop = propertiesService.getProperties();
        model.addAttribute("properties", prop);
        return "properties/showAll";
    }

    @GetMapping("/view/create")
    public String create() {
        return "properties/create";
    }

    @GetMapping("/view/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        PropertiesDTO prop = propertiesService.getPropertyById(id);
        model.addAttribute("property", prop);
        return "properties/update";
    }

    @PostMapping("/update")
    public String updateProperty(
            @RequestParam Long id,
            @RequestParam String address,
            @RequestParam String propertyType,
            @RequestParam String description,
            @RequestParam Date availabilityDate,
            @RequestParam Boolean availabilityStatus
    ) {
        PropertiesDTO propertyDTO = new PropertiesDTO();

        propertyDTO.setId(id);
        propertyDTO.setPropertyType(propertyType);
        propertyDTO.setAddress(address);
        propertyDTO.setDescription(description);
        propertyDTO.setAvailabilityDate(availabilityDate);
        propertyDTO.setAvailabilityStatus(availabilityStatus);

        PropertiesDTO updatedProperty = propertiesService.updateProperty(id, propertyDTO);

        return "redirect:/properties/view/list";
    }

    @PostMapping()
    public String createProperty(
            @RequestParam String address,
            @RequestParam String propertyType,
            @RequestParam String description,
            @RequestParam Date availabilityDate,
            @RequestParam Boolean availabilityStatus
    ) {
        PropertiesDTO propertyDTO = new PropertiesDTO();

        propertyDTO.setPropertyType(propertyType);
        propertyDTO.setAddress(address);
        propertyDTO.setDescription(description);
        propertyDTO.setAvailabilityDate(availabilityDate);
        propertyDTO.setAvailabilityStatus(availabilityStatus);

        PropertiesDTO createdProperty = propertiesService.createProperty(propertyDTO);
        return "redirect:/properties/view/list";
    }



    @GetMapping("/delete/{id}")
    public String deleteProperty(@PathVariable Long id) {
        propertiesService.deleteProperty(id);
        return "redirect:/properties/view/list";
    }
}
