package com.api.templatecms.apitemplatecms.dto.responses.ott;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OttResponseUser {
    String userName;
    String fullName;
    List<OttResponseGroup> userGroup;
    List<OttResponseMenuType> userMenu;
}
