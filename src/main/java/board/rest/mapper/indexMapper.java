package board.rest.mapper;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import board.common.CamelMap;
import board.common.DataMap;

/**************************************************
* @FileName : indexMapper.java
* @Description:
* @Author : se-in shin
* @Version : 2021. 8. 12.
* @Copyright : ⓒADUP. All Right Reserved
**************************************************/
@Repository
public interface indexMapper {

	/**************************************************
	* @MethodName : indexList
	* @Description: 메인 조회
	* @param paramMap
	* @return List<CamelMap>
	* @Author : se-in shin
	* @Version : 2021. 8. 17.
	**************************************************/
	public List<CamelMap> indexList(DataMap paramMap) throws SQLException;

	/**************************************************
	* @MethodName : boardCount
	* @Description: 메인 게시판 카운트 조회
	* @param paramMap
	* @return int
	* @Author : se-in shin
	* @Version : 2021. 8. 17.
	**************************************************/
	public int boardCount(DataMap paramMap) throws SQLException;

	/**************************************************
	* @MethodName : indexInfo
	* @Description: 게시판 상세 조회
	* @param paramMap
	* @return CamelMap
	* @Author : se-in shin
	* @Version : 2021. 8. 18.
	**************************************************/
	public CamelMap indexInfo(DataMap paramMap) throws SQLException;

	/**************************************************
	* @MethodName : indexUpdate
	* @Description: 게시판 수정
	* @param paramMap
	* @return int
	* @Author : se-in shin
	* @Version : 2021. 8. 18.
	**************************************************/
	public int indexUpdate(DataMap paramMap) throws SQLException;

	/**************************************************
	* @MethodName : indexWrite
	* @Description: 게시판 등록
	* @param paramMap
	* @return int
	* @Author : se-in shin
	* @Version : 2021. 8. 18.
	**************************************************/
	public int indexWrite(DataMap paramMap) throws SQLException;

	/**************************************************
	* @MethodName : indexDelete
	* @Description: 게시판 삭제
	* @param paramMap
	* @return int
	* @throws SQLException 
	* @Author : se-in shin
	* @Version : 2021. 8. 18.
	**************************************************/
	public int indexDelete(DataMap paramMap) throws SQLException;
}