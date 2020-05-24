package com.yura.soapproducer.dto;

import java.util.Objects;

public class UserDto {

    private Integer id;
    private String name;
    private String email;
    private String password;

    public UserDto() {

    }

    public UserDto(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.email = builder.email;
        this.password = builder.password;
    }

    public Integer getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UserDto userDto = (UserDto) obj;
        return Objects.equals(id, userDto.id) &&
                Objects.equals(name, userDto.name) &&
                Objects.equals(email, userDto.email) &&
                Objects.equals(password, userDto.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer id;
        private String name;
        private String email;
        private String password;

        private Builder() {

        }

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public UserDto build() {
            return new UserDto(this);
        }
    }
}
