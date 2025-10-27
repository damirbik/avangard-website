package ru.avangard.website.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "service")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private Long serviceId;

    @Column(name = "title", length = 500)
    private String title;

    @Column(name = "main_text", length = 5000)
    private String  mainText;

    @Column(name = "pic_link_preview")
    private String picLinkPreview;

    @Column(name = "extra_text", length = 2000)
    private String extraText;

    @Column(name = "price")
    private int price;

    @Column(name = "important", length = 1000)
    private String important;

    @Column(name = "pic_link_main")
    private String picLinkMain;

    @Column(name = "meta_title")
    private String metaTitle = "ООО «Авангард» — Юридическая помощь при ДТП";

    @Column(name = "meta_description", length = 400)
    private String metaDescription = "ООО «Авангард» - Любой вид Юридической помощи при ДТП. Помогаем клиентам отстоять их интересы и получить достойную компенсацию в г. Томске и Области";

    @Column(name = "meta_keywords")
    private String metaKeywords = "";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subcategory_id", nullable = false)
    @JsonBackReference("subcategory-service") // Это "обратная" сторона связи
    private Subcategory subcategory;
}
