package mybatis.sample.mapper;

import mybatis.sample.model.Test2;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@SuppressWarnings("javadoc")
public interface Test2Mapper {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TEST2
     *
     * @mbg.generated
     */
    @Delete({
            "delete from TEST2",
            "where ID = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TEST2
     *
     * @mbg.generated
     */
    @Insert({
            "insert into TEST2 (ID, TEXT, ",
            "OBJ)",
            "values (#{id,jdbcType=BIGINT}, #{text,jdbcType=VARCHAR}, ",
            "#{obj,jdbcType=BLOB})"
    })
    int insert(Test2 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TEST2
     *
     * @mbg.generated
     */
    int insertSelective(Test2 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TEST2
     *
     * @mbg.generated
     */
    @Select({
            "select",
            "ID, TEXT, OBJ",
            "from TEST2",
            "where ID = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("mybatis.sample.mapper.Test2Mapper.ResultMapWithBLOBs")
    Test2 selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TEST2
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Test2 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TEST2
     *
     * @mbg.generated
     */
    @Update({
            "update TEST2",
            "set TEXT = #{text,jdbcType=VARCHAR},",
            "OBJ = #{obj,jdbcType=BLOB}",
            "where ID = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKeyWithBLOBs(Test2 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TEST2
     *
     * @mbg.generated
     */
    @Update({
            "update TEST2",
            "set TEXT = #{text,jdbcType=VARCHAR}",
            "where ID = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Test2 record);
}