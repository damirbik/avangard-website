package ru.avangard.website.controller;


import lombok.RequiredArgsConstructor;
import ru.avangard.website.entity.Service;
import ru.avangard.website.repository.ServiceShortProjection;
import ru.avangard.website.service.ServiceService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/api/services")
@RequiredArgsConstructor
public class ServiceController {

    private final ServiceService serviceService;

    /**
     * GET /api/services
     * Получить все услуги (базовая информация)
     */
    @GetMapping
    public ResponseEntity<List<Service>> getAllServices() {
        List<Service> services = serviceService.getAllServices();
        return ResponseEntity.ok(services);
    }

    /**
     * GET /api/services/with-details
     * Получить все услуги с полной информацией о подкатегориях и категориях
     */
//    @GetMapping("/with-details")
//    public ResponseEntity<List<Service>> getAllServicesWithDetails() {
//        List<Service> services = serviceService.findAllWithSubcategoryAndCategory();
//        return ResponseEntity.ok(services);
//    }

    /**
     * GET /api/services/{id}
     * Получить услугу по ID (базовая информация)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Service> getServiceById(@PathVariable Long id) {
        return serviceService.getServiceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


//    @GetMapping("/{id}/with-details")
//    public ResponseEntity<Service> getServiceWithDetails(@PathVariable Long id) {
//        return serviceService.findByIdWithSubcategoryAndCategory(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }

    /**
     * GET /api/services/subcategory/{subcategoryId}
     * Получить все услуги по ID подкатегории
     */
    @GetMapping("/subcategory/{subcategoryId}")
    public ResponseEntity<List<Service>> getServicesBySubcategory(@PathVariable Long subcategoryId) {
        List<Service> services = serviceService.findBySubcategorySubcategoryId(subcategoryId);
        return ResponseEntity.ok(services);
    }

    /**
     * GET /api/services/category/{categoryId}
     * Получить все услуги по ID категории
     */
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Service>> getServicesByCategory(@PathVariable Long categoryId) {
        List<Service> services = serviceService.findBySubcategoryCategoryCategoryId(categoryId);
        return ResponseEntity.ok(services);
    }

    @GetMapping("/subcategory/short/{subcategoryId}")
    public ResponseEntity<List<ServiceShortProjection>> getShortBySubcategoryId(@PathVariable Long subcategoryId){
        List<ServiceShortProjection> services = serviceService.findShortBySubcategoryId(subcategoryId);
        return ResponseEntity.ok(services);
    }

    /**
     * POST /api/services
     * Создать новую услугу
     */
    @PostMapping
    public ResponseEntity<Service> createService(@RequestBody Service service) {
        Service createdService = serviceService.createService(service);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdService);
    }

    /**
     * PUT /api/services/{id}
     * Обновить существующую услугу
     */
    @PutMapping("/{id}")
    public ResponseEntity<Service> updateService(@PathVariable Long id, @RequestBody Service service) {
        if (!serviceService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Service updatedService = serviceService.updateService(id, service);
        return ResponseEntity.ok(updatedService);
    }

    /**
     * DELETE /api/services/{id}
     * Удалить услугу по ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        if (!serviceService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        serviceService.deleteService(id);
        return ResponseEntity.noContent().build();
    }
}
