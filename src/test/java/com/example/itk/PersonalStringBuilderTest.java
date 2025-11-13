package com.example.itk;


import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class PersonalStringBuilderTest {
    private static final String FIRST_APPEND = "ITK";
    private static final String SECOND_APPEND = ", ITK";

    @Test
    public void test() {
        PersonalStringBuilder personalStringBuilder = new PersonalStringBuilder(FIRST_APPEND);

        //add word, then add another, then undo the last one
        {
            assertThat(personalStringBuilder.toString(), equalTo(FIRST_APPEND));
            personalStringBuilder.append(SECOND_APPEND);
            assertThat(personalStringBuilder.toString(), equalTo(FIRST_APPEND + SECOND_APPEND));
            personalStringBuilder.undo();
            assertThat(personalStringBuilder.toString(), equalTo(FIRST_APPEND));
        }

        //add the second word again, undo it again
        {
            personalStringBuilder.append(SECOND_APPEND);
            assertThat(personalStringBuilder.toString(), equalTo(FIRST_APPEND + SECOND_APPEND));
            personalStringBuilder.undo();
            assertThat(personalStringBuilder.toString(), equalTo(FIRST_APPEND));
        }
    }
}