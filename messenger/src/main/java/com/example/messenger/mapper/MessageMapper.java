package com.example.messenger.mapper;

import com.example.messenger.dto.MessageDto;
import com.example.messenger.model.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface MessageMapper {

    @Mapping(source = "sender.login", target = "sender")
    @Mapping(source = "recipient.login", target = "recipient")
    @Mapping(source = "chat.id", target = "chat")
    MessageDto toDto(Message message);
}
