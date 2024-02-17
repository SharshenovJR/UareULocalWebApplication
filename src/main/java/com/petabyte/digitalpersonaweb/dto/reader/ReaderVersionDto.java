package com.petabyte.digitalpersonaweb.dto.reader;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReaderVersionDto {
    public int major;
    public int minor;
    public int maintenance;
}
