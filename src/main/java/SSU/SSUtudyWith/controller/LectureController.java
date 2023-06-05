package SSU.SSUtudyWith.controller;


import SSU.SSUtudyWith.dto.Lecture.LectureResponseDTO;

import SSU.SSUtudyWith.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LectureController {

    @Lazy
    @Autowired
    private  final LectureService lectureService;

    @GetMapping("/getLectures")
    public LectureResponseDTO findLectures() {


        return lectureService.findLecture();
    }

}
