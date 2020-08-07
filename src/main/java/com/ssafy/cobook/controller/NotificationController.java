package com.ssafy.cobook.controller;

import com.ssafy.cobook.domain.user.User;
import com.ssafy.cobook.service.NotificationService;
import com.ssafy.cobook.service.dto.notification.NotificationReqDto;
import com.ssafy.cobook.service.dto.notification.NotificationResponseDto;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/noti")
@RequiredArgsConstructor
public class NotificationController {

    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    private final NotificationService notificationService;

    @ApiOperation(value = "알람 가져오기")
    @ApiImplicitParams({@ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")})
    @GetMapping
    public ResponseEntity<List<NotificationResponseDto>> getNotifications(@ApiIgnore final Authentication authentication) {
        if( authentication == null) {
            return ResponseEntity.ok().build();
        }
        Long userId = ((User) authentication.getPrincipal()).getId();
        return ResponseEntity.status(HttpStatus.OK).body(notificationService.getNotis(userId));
    }

    @ApiOperation(value = "알람 저장하기")
    @ApiImplicitParams({@ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")})
    @PostMapping
    public ResponseEntity<Void> saveNotification(@ApiIgnore final Authentication authentication, @RequestBody final NotificationReqDto requestDto) {
        Long userId = ((User) authentication.getPrincipal()).getId();
        notificationService.saveNoti(requestDto, userId);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "알람 읽기")
    @ApiImplicitParams({@ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")})
    @PutMapping("/{notiId}")
    public ResponseEntity<Void> readNotification(@ApiIgnore final Authentication authentication,
                                                 @PathVariable("notiId") final Long notiId) {
        Long userId = ((User) authentication.getPrincipal()).getId();
        notificationService.readNoti(notiId, userId);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "알람 삭제")
    @ApiImplicitParams({@ApiImplicitParam(name = "jwt", value = "JWT Token", required = true, dataType = "string", paramType = "header")})
    @DeleteMapping("/{notiId}")
    public ResponseEntity<Void> deleteNotification(@ApiIgnore final Authentication authentication,
                                                 @PathVariable("notiId") final Long notiId) {
        Long userId = ((User) authentication.getPrincipal()).getId();
        notificationService.deleteNoti(notiId, userId);
        return ResponseEntity.ok().build();
    }
}
