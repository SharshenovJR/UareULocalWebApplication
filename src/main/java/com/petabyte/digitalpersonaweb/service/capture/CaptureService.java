package com.petabyte.digitalpersonaweb.service.capture;

import com.digitalpersona.uareu.Reader;
import com.digitalpersona.uareu.UareUException;

public interface CaptureService {
    Reader.CaptureResult capture(Reader reader, boolean stream) throws UareUException, InterruptedException;
}
