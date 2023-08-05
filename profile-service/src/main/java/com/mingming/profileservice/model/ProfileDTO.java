package com.mingming.profileservice.model;

import com.mingming.profileservice.data.Profile;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProfileDTO {
    private Long id;
    private String email;
    private String status;
    private Double initialBalancce;
    private String name;
    private String role;

    public static Profile dtoToEntity(ProfileDTO profileDTO){
        Profile profile = new Profile();
        BeanUtils.copyProperties(profileDTO, profile);
        return profile;
    }

    public static ProfileDTO entityToDto(Profile profile){
        ProfileDTO profileDTO = new ProfileDTO();
        BeanUtils.copyProperties(profile, profileDTO);
        return profileDTO;
    }
}
