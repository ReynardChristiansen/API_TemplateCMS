package com.api.templatecms.apitemplatecms.dto.responses.ott;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OttResponseMenuType {
    String menuType;
    List<OttResponseMenuTypeDetail> menus;
}
