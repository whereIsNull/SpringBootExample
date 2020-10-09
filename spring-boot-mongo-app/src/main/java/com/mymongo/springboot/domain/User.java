package com.mymongo.springboot.domain;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class User {

	@Id
	private String id;
	private String email;
	private String nick;
	private String name;
}
