package de.securitysquad.webifier.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

/**
 * Created by samuel on 08.11.16.
 */
public class WebifierConfigLoader {
    private File configFolder = new File(System.getProperty("user.home"), ".webifier/tester");
    private ObjectMapper mapper = new ObjectMapper();

    public WebifierConfig load(String name) throws IOException {
        try {
            return loadExternal(name);
        } catch (IOException e) {
            copyInternal(name);
            System.out.println("Loading internal configuration!");
            return loadInternal(name);
        }
    }

    private void copyInternal(String name) throws IOException {
        configFolder.mkdirs();
        copyInputStreamToFile(getInternalStream(name), new File(configFolder, name));
    }

    private WebifierConfig loadExternal(String name) throws IOException {
        InputStream is = new FileInputStream(getExternalFile(name));
        return load(is);
    }

    private File getExternalFile(String name) {
        return new File(configFolder, name);
    }

    private WebifierConfig loadInternal(String name) throws IOException {
        InputStream is = getInternalStream(name);
        return load(is);
    }

    private InputStream getInternalStream(String name) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
//        return ClassLoader.getSystemResourceAsStream(name);
    }

    private WebifierConfig load(InputStream is) throws IOException {
        return mapper.readValue(is, WebifierConfig.class);
    }
}
