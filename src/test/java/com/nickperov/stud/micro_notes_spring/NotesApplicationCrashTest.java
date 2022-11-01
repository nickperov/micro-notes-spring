package com.nickperov.stud.micro_notes_spring;

import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class NotesApplicationCrashTest {

    @Test
    public void testWebServerFail() throws Exception {
        assertThrows(RuntimeException.class, () -> Application.main(new String[]{"9999999"}));
    }

}
