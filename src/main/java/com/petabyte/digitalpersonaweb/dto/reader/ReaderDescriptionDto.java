package com.petabyte.digitalpersonaweb.dto.reader;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReaderDescriptionDto {
    private String name;
    private String serialNumber;
    private int productId;
    private int vendorId;
    private String productName;
    private String vendorName;
    private String modality;
    private String technology;
    private ReaderVersionDto firmwareVersion;
    private ReaderVersionDto hardwareVersion;
    private int bcdRevision;
}
