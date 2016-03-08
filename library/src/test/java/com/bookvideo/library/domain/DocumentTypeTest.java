package com.bookvideo.library.domain;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

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

        assertThat(documentTypes, is(notNullValue()));
        assertThat(documentTypes, hasSize(5));
        assertThat(documentTypes, contains(
                DocumentType.WELCOME,
                DocumentType.DASHBOARD,
                DocumentType.CONTENT,
                DocumentType.MENU,
                null));
    }
}