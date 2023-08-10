package board.security.mapper;

import org.apache.ibatis.annotations.Mapper;

import board.security.User;

@Mapper
public interface userMapper {

	User getUserInfo(String insertedId);

}
