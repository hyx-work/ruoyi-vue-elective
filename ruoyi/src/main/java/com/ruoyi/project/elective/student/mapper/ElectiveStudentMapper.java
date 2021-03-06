package com.ruoyi.project.elective.student.mapper;

import com.ruoyi.project.elective.student.domain.ElectiveStudent;

import java.util.List;

/**
 * studentMapper接口
 *
 * @author Sunss
 * @date 2020-02-11
 */
public interface ElectiveStudentMapper {
    /**
     * 查询student
     *
     * @param id studentID
     * @return student
     */
    public ElectiveStudent selectElectiveStudentById(Long id);

    /**
     * 查询student列表
     *
     * @param electiveStudent student
     * @return student集合
     */
    public List<ElectiveStudent> selectElectiveStudentList(ElectiveStudent electiveStudent);

    /**
     * 新增student
     *
     * @param electiveStudent student
     * @return 结果
     */
    public int insertElectiveStudent(ElectiveStudent electiveStudent);

    /**
     * 修改student
     *
     * @param electiveStudent student
     * @return 结果
     */
    public int updateElectiveStudent(ElectiveStudent electiveStudent);

    /**
     * 删除student
     *
     * @param id studentID
     * @return 结果
     */
    public int deleteElectiveStudentById(Long id);

    /**
     * 批量删除student
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteElectiveStudentByIds(Long[] ids);

    /**
     * 查询导出用的学生数据
     * @param electiveStudent
     * @return
     */
    List<ElectiveStudent> selectExportList(ElectiveStudent electiveStudent);

    /**
     * 根据userId获取学生
     * @param userId
     * @return
     */
    ElectiveStudent selectStudentByUserId(Long userId);

    void deleteStudentByDeptId(Long deptId);
}
