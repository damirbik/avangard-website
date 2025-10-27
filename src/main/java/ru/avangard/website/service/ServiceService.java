package ru.avangard.website.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.avangard.website.repository.IServiceRepository;
import ru.avangard.website.repository.ServiceShortProjection;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceService {

    public final IServiceRepository serviceRepository;

    public List<ru.avangard.website.entity.Service> findBySubcategorySubcategoryId(Long id){
        return serviceRepository.findBySubcategorySubcategoryId(id);
    }

    public List<ru.avangard.website.entity.Service> findBySubcategoryCategoryCategoryId(Long id){
        return serviceRepository.findBySubcategoryCategoryCategoryId(id);
    }

    public List<ru.avangard.website.entity.Service> findAllWithSubcategoryAndCategory(){
        return serviceRepository.findAllWithSubcategoryAndCategory();
    }

    public Optional<ru.avangard.website.entity.Service> findByIdWithSubcategoryAndCategory(Long serviceId){
        return serviceRepository.findByIdWithSubcategoryAndCategory(serviceId);
    }

    public List<ru.avangard.website.entity.Service> getAllServices() {
        return serviceRepository.findAll();
    }

    public List<ServiceShortProjection> findShortBySubcategoryId(Long id){
        return serviceRepository.findShortBySubcategoryId(id);
    }

    public Optional<ru.avangard.website.entity.Service> getServiceById(Long id) {
        return serviceRepository.findById(id);
    }

    public ru.avangard.website.entity.Service createService(ru.avangard.website.entity.Service service) {
        return serviceRepository.save(service);
    }

    public ru.avangard.website.entity.Service updateService(Long id, ru.avangard.website.entity.Service service) {
        service.setServiceId(id);
        return serviceRepository.save(service);
    }

    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return serviceRepository.existsById(id);
    }
}

