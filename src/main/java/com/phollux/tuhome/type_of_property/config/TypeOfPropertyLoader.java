package com.phollux.tuhome.type_of_property.config;

import com.phollux.tuhome.type_of_property.domain.TypeOfProperty;
import com.phollux.tuhome.type_of_property.repos.TypeOfPropertyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TypeOfPropertyLoader implements ApplicationRunner {

    private final TypeOfPropertyRepository typeOfPropertyRepository;


    public TypeOfPropertyLoader(TypeOfPropertyRepository repository) {
        this.typeOfPropertyRepository = repository;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {

        if(typeOfPropertyRepository.count()!= 0){
            return;
        }

        log.info("initializing Type Of Property");

        TypeOfProperty typeHouse = new TypeOfProperty();
        typeHouse.setName("Casa Unifamiliar");
        typeHouse.setDescription("Una vivienda independiente diseñada para albergar a una sola familia.");
        typeOfPropertyRepository.save(typeHouse);

        TypeOfProperty typeApartment = new TypeOfProperty();
        typeApartment.setName("Apartamento");
        typeApartment.setDescription("Una unidad residencial dentro de un edificio.");
        typeOfPropertyRepository.save(typeApartment);

        TypeOfProperty typeFlat = new TypeOfProperty();
        typeFlat.setName("Piso");
        typeFlat.setDescription("Una unidad residencial en un edificio, generalmente en uno de los pisos.");
        typeOfPropertyRepository.save(typeFlat);

        TypeOfProperty typeDuplex = new TypeOfProperty();
        typeDuplex.setName("Dúplex");
        typeDuplex.setDescription("Una estructura de dos unidades conectadas, generalmente una sobre la otra.");
        typeOfPropertyRepository.save(typeDuplex);

        TypeOfProperty typeLoft = new TypeOfProperty();
        typeLoft.setName("Loft");
        typeLoft.setDescription("Un espacio grande y abierto, a menudo en edificios industriales.");
        typeOfPropertyRepository.save(typeLoft);

        TypeOfProperty typeMobileHome = new TypeOfProperty();
        typeMobileHome.setName("Casa Móvil");
        typeMobileHome.setDescription("Una vivienda prefabricada que puede transportarse.");
        typeOfPropertyRepository.save(typeMobileHome);

        TypeOfProperty typeEstate = new TypeOfProperty();
        typeEstate.setName("Finca");
        typeEstate.setDescription("Una propiedad de tierras extensas, comúnmente utilizada para actividades agrícolas o recreativas.");
        typeOfPropertyRepository.save(typeEstate);

        TypeOfProperty typePenthouse = new TypeOfProperty();
        typePenthouse.setName("Penthouse");
        typePenthouse.setDescription("Un apartamento lujoso ubicado en la parte superior de un edificio.");
        typeOfPropertyRepository.save(typePenthouse);

        TypeOfProperty typeOfficeSpace = new TypeOfProperty();
        typeOfficeSpace.setName("Oficina Comercial");
        typeOfficeSpace.setDescription("Un espacio destinado para actividades comerciales y de oficina.");
        typeOfPropertyRepository.save(typeOfficeSpace);

        TypeOfProperty typeCabin = new TypeOfProperty();
        typeCabin.setName("Cabaña");
        typeCabin.setDescription("Una pequeña casa de madera, típicamente ubicada en entornos rurales o montañosos.");
        typeOfPropertyRepository.save(typeCabin);

        TypeOfProperty typeEcoHouse = new TypeOfProperty();
        typeEcoHouse.setName("Casa Ecológica");
        typeEcoHouse.setDescription("Una vivienda diseñada con consideraciones ambientales para minimizar su impacto en el medio ambiente.");
        typeOfPropertyRepository.save(typeEcoHouse);

        TypeOfProperty typeFarm = new TypeOfProperty();
        typeFarm.setName("Granja");
        typeFarm.setDescription("Una propiedad utilizada para la agricultura y la cría de animales.");
        typeOfPropertyRepository.save(typeFarm);

        TypeOfProperty typeStudioApartment = new TypeOfProperty();
        typeStudioApartment.setName("Apartamento Estudio");
        typeStudioApartment.setDescription("Un apartamento pequeño que combina la sala de estar y el área de dormir en un solo espacio.");
        typeOfPropertyRepository.save(typeStudioApartment);

        TypeOfProperty typeMansion = new TypeOfProperty();
        typeMansion.setName("Mansión");
        typeMansion.setDescription("Una residencia grande y lujosa, a menudo con terrenos extensos.");
        typeOfPropertyRepository.save(typeMansion);

        TypeOfProperty typeApartmentBuilding = new TypeOfProperty();
        typeApartmentBuilding.setName("Edificio de Apartamentos");
        typeApartmentBuilding.setDescription("Una estructura diseñada para albergar múltiples unidades de vivienda.");
        typeOfPropertyRepository.save(typeApartmentBuilding);

        TypeOfProperty typeCountryHouse = new TypeOfProperty();
        typeCountryHouse.setName("Casa de Campo");
        typeCountryHouse.setDescription("Una propiedad rural utilizada como residencia secundaria para escapadas de fin de semana.");
        typeOfPropertyRepository.save(typeCountryHouse);

        TypeOfProperty typeCondo = new TypeOfProperty();
        typeCondo.setName("Condominio");
        typeCondo.setDescription("Una propiedad en un edificio con servicios compartidos y áreas comunes.");
        typeOfPropertyRepository.save(typeCondo);

        TypeOfProperty typeChalet = new TypeOfProperty();
        typeChalet.setName("Chalet");
        typeChalet.setDescription("Una casa de lujo con jardín, comúnmente ubicada en áreas suburbanas.");
        typeOfPropertyRepository.save(typeChalet);




    }
}
