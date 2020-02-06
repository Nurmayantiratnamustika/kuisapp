package com.nurmayanti.kuisapp;

public class Question {
    private String Question;
    private String Option1;
    private  String Option2;
    private String Option3;
    private String Answer;

    public  Question(){

}

    public Question(String question, String option1, String option2, String option3, String answer) {
        Question = question;
        Option1 = option1;
        Option2 = option2;
        Option3 = option3;
        Answer = answer;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getOption1() {
        return Option1;
    }

    public void setOption1(String option1) {
        Option1 = option1;
    }

    public String getOption2() {
        return Option2;
    }

    public void setOption2(String option2) {
        Option2 = option2;
    }

    public String getOption3() {
        return Option3;
    }

    public void setOption3(String option3) {
        Option3 = option3;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }
}
