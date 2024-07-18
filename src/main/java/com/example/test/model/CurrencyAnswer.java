package com.example.test.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("Cur_ID")
    @Column(name = "cur_id")
    private Long curId;

    @JsonProperty("Date")
    @Column(name = "date")
    private LocalDate date;

    @JsonProperty("Cur_Abbreviation")
    @Column(name = "cur_abbreviation")
    private String curAbbreviation;

    @JsonProperty("Cur_Scale")
    @Column(name = "cue_scale")
    private Integer curScale;

    @JsonProperty("Cur_Name")
    @Column(name = "cur_name")
    private String curName;

    @JsonProperty("Cur_OfficialRate")
    @Column(name = "cur_officialRate")
    private BigDecimal curOfficialRate;
}
