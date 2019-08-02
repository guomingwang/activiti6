package com.activiti6.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wgm
 * @since 2019/7/28 2:36
 */
public class UserTaskService implements Serializable {

    public String getAssignee() {
        return null;
    }

    public List<String> getCandidateUsers() {
        List<String> strings = new ArrayList<>();
        strings.add("userB");
        strings.add("userC");
        return strings;
    }

    public List<String> getCandidateGroups() {
        List<String> strings = new ArrayList<>();
        strings.add("GroupA");
        strings.add("GroupB");
        return strings;
    }
}
