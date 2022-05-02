package com.chocksaway.gallery.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Table
@Getter
@Setter
@Accessors(chain = true)
public class Picture {
    private String name;

    @Size(max = 4000)
    @NotBlank
    private String description;

    public Picture(String name, String description) {
        this.name = name;
        this.description = description;
    }
}