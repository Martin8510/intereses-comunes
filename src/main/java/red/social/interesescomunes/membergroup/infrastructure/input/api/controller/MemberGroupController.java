package red.social.interesescomunes.membergroup.infrastructure.input.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import red.social.interesescomunes.membergroup.application.input.IMemberGroupServicePort;
import red.social.interesescomunes.membergroup.domain.model.MemberGroup;
import red.social.interesescomunes.membergroup.infrastructure.input.api.dto.request.MemberGroupRequest;
import red.social.interesescomunes.membergroup.infrastructure.input.api.dto.response.MemberGroupResponse;
import red.social.interesescomunes.membergroup.infrastructure.input.api.mapper.IMemberGroupRestMapper;

@RestController
@RequestMapping("/api/v1/member-group")
public class MemberGroupController {
    @Autowired
    private  IMemberGroupServicePort service;
    @Autowired
    private IMemberGroupRestMapper mapper;

    @PostMapping("/join")
    public ResponseEntity<MemberGroupResponse> joinGroup(@RequestBody MemberGroupRequest request) {
        MemberGroup joined = service.joinGroup(request.getMemberId(), request.getGroupId());
        return ResponseEntity.ok(mapper.toResponse(joined));
    }

    @PostMapping("/leave")
    public ResponseEntity<Void> leaveGroup(@RequestBody MemberGroupRequest request) {
        service.leaveGroup(request.getMemberId(), request.getGroupId());
        return ResponseEntity.noContent().build();
    }
}
