package com.moldedbits.genesis.models.response;

import java.util.List;

import lombok.Data;

@Data
public class PassageDetails {

    private String difficulty;

    private DisplayName displayName;

    private PassageText passageText;

    private List<Questions> questions;

    private List<Sentence> sentences;

    @Data
    public static class DisplayName {
        private String english;
        private String spanish;
    }

    @Data
    public static class PassageText {
        private String english;
        private String spanish;
    }

    @Data
    public static class Questions {
        private String answerText;

        private QuestionText questionText;
        private String type;
        private List<String> options;
    }

    @Data
    public static class QuestionText {
        private String english;
        private String spanish;
    }

    @Data
    public static class Sentence {
        private String english;
        private String spanish;
    }
}

