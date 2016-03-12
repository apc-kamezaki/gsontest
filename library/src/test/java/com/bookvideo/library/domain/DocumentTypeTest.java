package com.bookvideo.library.domain;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class DocumentTypeTest {
    private Gson gson;

    @Before
    public void setUp() throws Exception {
        gson = GsonCreator.getInstance().createGson();
    }

    @Test
    public void shouldDeserializeDocumentType() throws Exception {
        List<DocumentType> documentTypes;
        try (InputStream is = getClass().getResourceAsStream("/document/document_type.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            Type listType = new TypeToken<List<DocumentType>>(){}.getType();
            documentTypes = gson.fromJson(reader, listType);
        }

        assertThat(documentTypes)
                .isNotNull()
                .hasSize(5)
                .contains(
                        DocumentType.WELCOME,
                        DocumentType.DASHBOARD,
                        DocumentType.CONTENT,
                        DocumentType.MENU,
                        null);
    }
}