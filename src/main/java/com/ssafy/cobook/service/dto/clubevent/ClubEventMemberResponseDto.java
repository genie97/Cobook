package com.ssafy.cobook.service.dto.clubevent;

import com.ssafy.cobook.domain.clubeventmember.ClubEventMember;
import com.ssafy.cobook.domain.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClubEventMemberResponseDto {

    private Long id;
    private String nickName;
    private String profileImg;

    public ClubEventMemberResponseDto(ClubEventMember clubEventMember) {
        User user = clubEventMember.getUser();
        this.id = user.getId();
        this.nickName = user.getNickName();
        if (user.getProfileImg() != null) {
            this.profileImg = "https://i3a111.p.ssafy.io:8090/api/profile/images/" + this.id;
        }
    }
}
