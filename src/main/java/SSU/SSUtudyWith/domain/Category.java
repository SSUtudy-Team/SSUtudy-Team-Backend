package SSU.SSUtudyWith.domain;

import lombok.Getter;


@Getter
public enum Category {
    JAVA("Java", 3.2),
    Python("Python", 3),
    네트워크("네트워크", 2.6),
    알고리즘("알고리즘", 2.7),
    컴퓨터구조("컴퓨터 구조", 2.5),
    인공지능("인공지능", 2.7),
    데이터베이스("데이터베이스", 2.6),
    머신러닝("머신러닝", 2.2),
    CS("CS", 2.7),
    백엔드("백엔드", 2.3),
    프론트엔드("프론트엔드", 2.3),
    클라이언트("클라이언트", 2.3),
    UIUX("UI/UX", 2),
    게임("게암", 1.8),
    디자인패턴("디자인패턴", 1.9),
    클린코드("클린코드", 1.7),
    사이드프로젝트("사이드 프로젝트", 2.6),
    공모전("공모전", 1.1),
    헤커톤("해커톤", 1.3),
    기타("기타", 1);

    private final String name;
    private final double score;

    Category(String name, double score) {
        this.name = name;
        this.score = score;
    }


    /**
     * 1. 소프트웨어학부
     * 2. 알고리즘
     * 3. 컴퓨터구조
     * 4. CS
     * 5. 컴퓨터 비전
     * 6. 코딩테스트
     * 7. 인공지능
     * 8. 머신러닝
     * 9. 공모전
     * 10. 해커톤
     */
}
