package com.petabyte.digitalpersonaweb.dto.reader;

import com.digitalpersona.uareu.Fmd;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FingerprintDto {
    private Fmd.Format format;
    private byte[] data;
}
