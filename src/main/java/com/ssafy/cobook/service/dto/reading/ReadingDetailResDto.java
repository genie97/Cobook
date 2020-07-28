package com.ssafy.cobook.service.dto.reading;

import com.ssafy.cobook.domain.book.Book;
import com.ssafy.cobook.domain.reading.Reading;
import com.ssafy.cobook.domain.readingmember.ReadingMember;
import com.ssafy.cobook.domain.user.User;
import com.ssafy.cobook.service.dto.book.BookSimpleResDto;
import com.ssafy.cobook.service.dto.post.PostByMembersResDto;
import com.ssafy.cobook.service.dto.post.PostSimpleResDto;
import com.ssafy.cobook.service.dto.question.QuestionResDto;
import com.ssafy.cobook.service.dto.user.UserByPostDto;
import com.ssafy.cobook.service.dto.user.UserResponseDto;
import com.ssafy.cobook.service.dto.user.UserSimpleResDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReadingDetailResDto {

    private Long id;
    private String name;
    private String description;
    private LocalDateTime dateTime;
    private String place;
    private Integer participantCnt;
    private Boolean closed;
    private BookSimpleResDto book;
    private List<QuestionResDto> questions;
    private List<UserByPostDto> members;
    private List<PostByMembersResDto> memberPosts;

    public ReadingDetailResDto(Reading reading, List<PostByMembersResDto> memberPosts) {
        this.id = reading.getId();
        this.name = reading.getTitle();
        this.dateTime = reading.getDateTime();
        this.place = reading.getPlace();
        this.description = reading.getDescription();
        this.participantCnt = reading.getMembers().size();
        this.closed = reading.getClosed();
        this.book = new BookSimpleResDto(reading.getBook());
        this.questions = reading.getQuestions().stream()
                .map(QuestionResDto::new)
                .collect(Collectors.toList());
        this.members = reading.getMembers().stream()
                .map(ReadingMember::getUser)
                .map(UserByPostDto::new)
                .collect(Collectors.toList());
        this.memberPosts = memberPosts;
    }
}
