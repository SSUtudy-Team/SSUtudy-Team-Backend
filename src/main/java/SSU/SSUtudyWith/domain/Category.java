package SSU.SSUtudyWith.domain;

import lombok.Getter;


@Getter
public enum Category {
    C1("소프트웨어학부", 3),
    C2("알고리즘", 2.7),
    C3("컴퓨터 구조", 2.5),
    C4("CS", 2.7),
    C5("컴퓨터 비전", 2),
    C6("코딩 테스트", 1.5),
    C7("인공지능", 2.7),
    C8("머신러닝", 2.2),
    C9("공모전", 1.2),
    C10("해커톤", 1.2);

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
