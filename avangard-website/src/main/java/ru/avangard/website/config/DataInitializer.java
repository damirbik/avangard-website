package ru.avangard.website.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.avangard.website.entity.Category;
import ru.avangard.website.entity.Subcategory;
import ru.avangard.website.entity.Service;
import ru.avangard.website.repository.ICategoryRepository;
import ru.avangard.website.repository.ISubcategoryRepository;
import ru.avangard.website.repository.IServiceRepository;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final ICategoryRepository categoryRepository;
    private final ISubcategoryRepository subcategoryRepository;
    private final IServiceRepository serviceRepository;


    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Проверяем, есть ли уже данные в базе
        /*serviceRepository.deleteAll(); очистка бд
        subcategoryRepository.deleteAll();
        categoryRepository.deleteAll();
        System.out.println("Старые данные удалены");*/
        if (categoryRepository.count() > 0) {
            System.out.println("✅ База данных уже содержит данные. Пропускаем инициализацию.");
            return;
        }

        System.out.println("🔄 Начинаем инициализацию базы данных...");

        // Создаем категории
        Category webDev = new Category();
        webDev.setCategoryName("Веб-разработка");

        Category mobileDev = new Category();
        mobileDev.setCategoryName("Мобильная разработка");

        Category design = new Category();
        design.setCategoryName("Дизайн");

        Category marketing = new Category();
        marketing.setCategoryName("Маркетинг");

        // Сохраняем категории
        webDev = categoryRepository.save(webDev);
        mobileDev = categoryRepository.save(mobileDev);
        design = categoryRepository.save(design);
        marketing = categoryRepository.save(marketing);

        System.out.println("✅ Создано категорий: " + categoryRepository.count());

        // Создаем подкатегории
        // Веб-разработка
        Subcategory frontend = new Subcategory();
        frontend.setSubcategoryName("Frontend");
        frontend.setCategory(webDev);

        Subcategory backend = new Subcategory();
        backend.setSubcategoryName("Backend");
        backend.setCategory(webDev);

        Subcategory fullstack = new Subcategory();
        fullstack.setSubcategoryName("Fullstack");
        fullstack.setCategory(webDev);

        // Мобильная разработка
        Subcategory ios = new Subcategory();
        ios.setSubcategoryName("iOS");
        ios.setCategory(mobileDev);

        Subcategory android = new Subcategory();
        android.setSubcategoryName("Android");
        android.setCategory(mobileDev);

        Subcategory crossPlatform = new Subcategory();
        crossPlatform.setSubcategoryName("Кроссплатформенные");
        crossPlatform.setCategory(mobileDev);

        // Дизайн
        Subcategory uiUx = new Subcategory();
        uiUx.setSubcategoryName("UI/UX дизайн");
        uiUx.setCategory(design);

        Subcategory graphicDesign = new Subcategory();
        graphicDesign.setSubcategoryName("Графический дизайн");
        graphicDesign.setCategory(design);

        Subcategory webDesign = new Subcategory();
        webDesign.setSubcategoryName("Веб-дизайн");
        webDesign.setCategory(design);

        // Маркетинг
        Subcategory seo = new Subcategory();
        seo.setSubcategoryName("SEO");
        seo.setCategory(marketing);

        Subcategory smm = new Subcategory();
        smm.setSubcategoryName("SMM");
        smm.setCategory(marketing);

        // Сохраняем все подкатегории
        List<Subcategory> subcategories = Arrays.asList(
                frontend, backend, fullstack, ios, android, crossPlatform,
                uiUx, graphicDesign, webDesign, seo, smm
        );

        subcategoryRepository.saveAll(subcategories);
        System.out.println("✅ Создано подкатегорий: " + subcategoryRepository.count());

        // Создаем услуги
        createServices(frontend, backend, ios, android, uiUx, graphicDesign, seo, smm);

        System.out.println("✅ Создано услуг: " + serviceRepository.count());
        System.out.println("🎉 База данных успешно инициализирована с тестовыми данными!");
    }

    private void createServices(Subcategory frontend, Subcategory backend, Subcategory ios,
                                Subcategory android, Subcategory uiUx, Subcategory graphicDesign,
                                Subcategory seo, Subcategory smm) {

        // Frontend услуги
        Service reactService = new Service();
        reactService.setTitle("Разработка на React");
        reactService.setMainText("Создание современных веб-приложений с использованием React. Включает разработку компонентов, управление состоянием и интеграцию с API.");
        reactService.setSubcategory(frontend);
        reactService.setPrice(50000);
        reactService.setPicLinkPreview("/images/react-preview.jpg");
        reactService.setPicLinkMain("/images/react-main.jpg");
        reactService.setImportant("Срок разработки: 2-4 недели. Гарантия 6 месяцев.");
        reactService.setMetaTitle("React разработка | Профессиональные услуги");
        reactService.setMetaDescription("Разработка веб-приложений на React. Современные технологии, качественный код.");
        reactService.setMetaKeywords("react, разработка, веб-приложения, javascript");
        reactService.setExtraText("Дополнительно: оптимизация производительности, адаптивная верстка.");

        Service vueService = new Service();
        vueService.setTitle("Vue.js приложения");
        vueService.setMainText("Разработка интерактивных пользовательских интерфейсов на Vue.js. Composition API, Vuex, Vue Router.");
        vueService.setSubcategory(frontend);
        vueService.setPrice(45000);
        vueService.setPicLinkPreview("/images/vue-preview.jpg");
        vueService.setPicLinkMain("/images/vue-main.jpg");
        vueService.setImportant("Поддержка Vue 3. Интеграция с любым бэкендом.");
        vueService.setMetaTitle("Vue.js разработка | Создание SPA");
        vueService.setMetaDescription("Профессиональная разработка на Vue.js. Быстрые и отзывчивые интерфейсы.");
        vueService.setMetaKeywords("vue, javascript, spa, фронтенд");
        vueService.setExtraText("Включает код-ревью и тестирование.");

        // Backend услуги
        Service springService = new Service();
        springService.setTitle("Spring Boot API");
        springService.setMainText("Разработка REST API на Spring Boot. Безопасность, документация Swagger, интеграция с БД.");
        springService.setSubcategory(backend);
        springService.setPrice(60000);
        springService.setPicLinkPreview("/images/spring-preview.jpg");
        springService.setPicLinkMain("/images/spring-main.jpg");
        springService.setImportant("Поддержка всех современных стандартов безопасности.");
        springService.setMetaTitle("Spring Boot API | Backend разработка");
        springService.setMetaDescription("Создание надежных REST API на Spring Boot для вашего бизнеса.");
        springService.setMetaKeywords("spring, java, api, backend");
        springService.setExtraText("Деплой на сервер включен в стоимость.");

        Service nodeService = new Service();
        nodeService.setTitle("Node.js микросервисы");
        nodeService.setMainText("Разработка масштабируемых микросервисов на Node.js. Docker, Kubernetes, мониторинг.");
        nodeService.setSubcategory(backend);
        nodeService.setPrice(55000);
        nodeService.setPicLinkPreview("/images/node-preview.jpg");
        nodeService.setPicLinkMain("/images/node-main.jpg");
        nodeService.setImportant("Архитектура готова к масштабированию.");
        nodeService.setMetaTitle("Node.js микросервисы | Современный бэкенд");
        nodeService.setMetaDescription("Создание микросервисной архитектуры на Node.js для высоких нагрузок.");
        nodeService.setMetaKeywords("node, микросервисы, javascript, backend");
        nodeService.setExtraText("Настройка CI/CD включена.");

        // iOS услуги
        Service iosSwiftService = new Service();
        iosSwiftService.setTitle("iOS приложения на Swift");
        iosSwiftService.setMainText("Разработка нативных iOS приложений на Swift. UIKit, SwiftUI, Core Data.");
        iosSwiftService.setSubcategory(ios);
        iosSwiftService.setPrice(80000);
        iosSwiftService.setPicLinkPreview("/images/ios-preview.jpg");
        iosSwiftService.setPicLinkMain("/images/ios-main.jpg");
        iosSwiftService.setImportant("Публикация в App Store включена.");
        iosSwiftService.setMetaTitle("iOS разработка | Приложения для Apple");
        iosSwiftService.setMetaDescription("Создание качественных iOS приложений для iPhone и iPad на Swift.");
        iosSwiftService.setMetaKeywords("ios, swift, приложения, apple");
        iosSwiftService.setExtraText("Тестирование на реальных устройствах.");

        Service iosObjcService = new Service();
        iosObjcService.setTitle("iOS приложения на Objective-C");
        iosObjcService.setMainText("Разработка и поддержка приложений на Objective-C для legacy проектов.");
        iosObjcService.setSubcategory(ios);
        iosObjcService.setPrice(70000);
        iosObjcService.setPicLinkPreview("/images/objc-preview.jpg");
        iosObjcService.setPicLinkMain("/images/objc-main.jpg");
        iosObjcService.setImportant("Совместимость со старыми версиями iOS.");
        iosObjcService.setMetaTitle("Objective-C разработка | Поддержка legacy");
        iosObjcService.setMetaDescription("Профессиональная разработка и поддержка приложений на Objective-C.");
        iosObjcService.setMetaKeywords("objective-c, ios, legacy, разработка");
        iosObjcService.setExtraText("Рефакторинг и оптимизация существующего кода.");

        // Android услуги
        Service androidKotlinService = new Service();
        androidKotlinService.setTitle("Android приложения на Kotlin");
        androidKotlinService.setMainText("Современная разработка Android приложений на Kotlin. Jetpack Compose, Room, Coroutines.");
        androidKotlinService.setSubcategory(android);
        androidKotlinService.setPrice(75000);
        androidKotlinService.setPicLinkPreview("/images/kotlin-preview.jpg");
        androidKotlinService.setPicLinkMain("/images/kotlin-main.jpg");
        androidKotlinService.setImportant("Material Design 3. Поддержка новых версий Android.");
        androidKotlinService.setMetaTitle("Android Kotlin | Современные приложения");
        androidKotlinService.setMetaDescription("Разработка современных Android приложений на Kotlin с использованием лучших практик.");
        androidKotlinService.setMetaKeywords("android, kotlin, jetpack, разработка");
        androidKotlinService.setExtraText("Адаптация под разные размеры экранов.");

        Service androidJavaService = new Service();
        androidJavaService.setTitle("Android приложения на Java");
        androidJavaService.setMainText("Разработка надежных Android приложений на Java. Поддержка старых версий Android.");
        androidJavaService.setSubcategory(android);
        androidJavaService.setPrice(65000);
        androidJavaService.setPicLinkPreview("/images/java-preview.jpg");
        androidJavaService.setPicLinkMain("/images/java-main.jpg");
        androidJavaService.setImportant("Совместимость с Android 5.0+");
        androidJavaService.setMetaTitle("Android Java | Надежные приложения");
        androidJavaService.setMetaDescription("Создание стабильных Android приложений на Java для широкой аудитории.");
        androidJavaService.setMetaKeywords("android, java, разработка, приложения");
        androidJavaService.setExtraText("Оптимизация производительности.");

        // UI/UX дизайн
        Service mobileDesignService = new Service();
        mobileDesignService.setTitle("Дизайн мобильного приложения");
        mobileDesignService.setMainText("Полный цикл проектирования UI/UX для мобильных приложений. Прототипирование, визуальный дизайн, гайдлайны.");
        mobileDesignService.setSubcategory(uiUx);
        mobileDesignService.setPrice(40000);
        mobileDesignService.setPicLinkPreview("/images/mobile-design-preview.jpg");
        mobileDesignService.setPicLinkMain("/images/mobile-design-main.jpg");
        mobileDesignService.setImportant("Адаптация под iOS и Android гайдлайны.");
        mobileDesignService.setMetaTitle("UI/UX дизайн мобильных приложений");
        mobileDesignService.setMetaDescription("Профессиональный дизайн мобильных приложений с учетом UX лучших практик.");
        mobileDesignService.setMetaKeywords("ui, ux, дизайн, мобильные, приложения");
        mobileDesignService.setExtraText("Создание интерактивных прототипов.");

        Service webDesignService = new Service();
        webDesignService.setTitle("Дизайн веб-сайта");
        webDesignService.setMainText("Создание современного дизайна для веб-сайтов. User research, wireframes, visual design.");
        webDesignService.setSubcategory(uiUx);
        webDesignService.setPrice(35000);
        webDesignService.setPicLinkPreview("/images/web-design-preview.jpg");
        webDesignService.setPicLinkMain("/images/web-design-main.jpg");
        webDesignService.setImportant("Адаптивный дизайн для всех устройств.");
        webDesignService.setMetaTitle("Дизайн веб-сайтов | UI/UX проектирование");
        webDesignService.setMetaDescription("Разработка удобных и красивых интерфейсов для веб-сайтов любой сложности.");
        webDesignService.setMetaKeywords("веб-дизайн, ui, ux, интерфейсы");
        webDesignService.setExtraText("Подготовка макетов для разработчиков.");

        // Графический дизайн
        Service logoService = new Service();
        logoService.setTitle("Разработка логотипа");
        logoService.setMainText("Создание уникального логотипа для вашего бренда. Несколько концепций, фирменные цвета.");
        logoService.setSubcategory(graphicDesign);
        logoService.setPrice(20000);
        logoService.setPicLinkPreview("/images/logo-preview.jpg");
        logoService.setPicLinkMain("/images/logo-main.jpg");
        logoService.setImportant("Неограниченное количество правок в процессе.");
        logoService.setMetaTitle("Дизайн логотипа | Создание бренда");
        logoService.setMetaDescription("Профессиональный дизайн запоминающегося логотипа для вашей компании.");
        logoService.setMetaKeywords("логотип, бренд, дизайн, фирменный стиль");
        logoService.setExtraText("Предоставление логотипа во всех форматах.");

        Service brandingService = new Service();
        brandingService.setTitle("Фирменный стиль");
        brandingService.setMainText("Разработка полного фирменного стиля: брендбук, визитки, бланки, презентации.");
        brandingService.setSubcategory(graphicDesign);
        brandingService.setPrice(50000);
        brandingService.setPicLinkPreview("/images/branding-preview.jpg");
        brandingService.setPicLinkMain("/images/branding-main.jpg");
        brandingService.setImportant("Полный пакет брендинга под ключ.");
        brandingService.setMetaTitle("Фирменный стиль | Брендбук разработка");
        brandingService.setMetaDescription("Создание комплексного фирменного стиля для укрепления позиций бренда.");
        brandingService.setMetaKeywords("фирменный стиль, брендбук, айдентика, дизайн");
        brandingService.setExtraText("Готовые шаблоны для всех носителей.");

        // SEO
        Service seoPromotionService = new Service();
        seoPromotionService.setTitle("SEO продвижение сайта");
        seoPromotionService.setMainText("Комплексное SEO продвижение вашего сайта. Аудит, техническая оптимизация, контент-стратегия.");
        seoPromotionService.setSubcategory(seo);
        seoPromotionService.setPrice(30000);
        seoPromotionService.setPicLinkPreview("/images/seo-preview.jpg");
        seoPromotionService.setPicLinkMain("/images/seo-main.jpg");
        seoPromotionService.setImportant("Ежемесячные отчеты о результатах.");
        seoPromotionService.setMetaTitle("SEO продвижение | Поисковая оптимизация");
        seoPromotionService.setMetaDescription("Профессиональное SEO продвижение сайтов для роста органического трафика.");
        seoPromotionService.setMetaKeywords("seo, продвижение, оптимизация, трафик");
        seoPromotionService.setExtraText("Работа с семантическим ядром и ссылочной массой.");

        Service seoAuditService = new Service();
        seoAuditService.setTitle("Технический SEO аудит");
        seoAuditService.setMainText("Глубокий анализ технического состояния сайта. Выявление ошибок и рекомендации по исправлению.");
        seoAuditService.setSubcategory(seo);
        seoAuditService.setPrice(15000);
        seoAuditService.setPicLinkPreview("/images/seo-audit-preview.jpg");
        seoAuditService.setPicLinkMain("/images/seo-audit-main.jpg");
        seoAuditService.setImportant("Подробный отчет с приоритизацией задач.");
        seoAuditService.setMetaTitle("SEO аудит | Технический анализ сайта");
        seoAuditService.setMetaDescription("Профессиональный технический SEO аудит для улучшения позиций в поиске.");
        seoAuditService.setMetaKeywords("seo, аудит, технический, анализ");
        seoAuditService.setExtraText("Рекомендации по исправлению ошибок.");

        // SMM
        Service instagramService = new Service();
        instagramService.setTitle("Ведение Instagram");
        instagramService.setMainText("Полное ведение бизнес-аккаунта в Instagram: контент-план, сторис, реклама, аналитика.");
        instagramService.setSubcategory(smm);
        instagramService.setPrice(25000);
        instagramService.setPicLinkPreview("/images/instagram-preview.jpg");
        instagramService.setPicLinkMain("/images/instagram-main.jpg");
        instagramService.setImportant("Рост вовлеченности гарантирован.");
        instagramService.setMetaTitle("SMM Instagram | Продвижение бизнеса");
        instagramService.setMetaDescription("Профессиональное ведение Instagram для увеличения продаж и узнаваемости бренда.");
        instagramService.setMetaKeywords("smm, instagram, продвижение, социальные сети");
        instagramService.setExtraText("Еженедельные отчеты о результатах.");

        Service smmStrategyService = new Service();
        smmStrategyService.setTitle("Стратегия SMM");
        smmStrategyService.setMainText("Разработка комплексной SMM стратегии для вашего бизнеса. Анализ ЦА, выбор площадок, KPI.");
        smmStrategyService.setSubcategory(smm);
        smmStrategyService.setPrice(20000);
        smmStrategyService.setPicLinkPreview("/images/smm-strategy-preview.jpg");
        smmStrategyService.setPicLinkMain("/images/smm-strategy-main.jpg");
        smmStrategyService.setImportant("Готовая стратегия на 6 месяцев.");
        smmStrategyService.setMetaTitle("SMM стратегия | Продвижение в соцсетях");
        smmStrategyService.setMetaDescription("Создание эффективной SMM стратегии для достижения маркетинговых целей.");
        smmStrategyService.setMetaKeywords("smm, стратегия, продвижение, социальные сети");
        smmStrategyService.setExtraText("Рекомендации по контенту и таргетингу.");

        // Сохраняем все услуги
        List<Service> services = Arrays.asList(
                reactService, vueService, springService, nodeService,
                iosSwiftService, iosObjcService, androidKotlinService, androidJavaService,
                mobileDesignService, webDesignService, logoService, brandingService,
                seoPromotionService, seoAuditService, instagramService, smmStrategyService
        );

        serviceRepository.saveAll(services);
    }
}