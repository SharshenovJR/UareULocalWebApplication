package com.petabyte.digitalpersonaweb.dto.reader;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReaderDto {
    private ReaderDescriptionDto description;
    private ReaderCapabilityDto capability;
}
