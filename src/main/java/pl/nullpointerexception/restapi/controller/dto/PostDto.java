package pl.nullpointerexception.restapi.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class PostDto {
    @Id
    private long id;
    private String title;
    private String content;
    private LocalDateTime created;
}
