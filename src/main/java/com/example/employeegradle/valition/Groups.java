package com.example.employeegradle.valition;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

import com.example.employeegradle.valition.Groups.First;
import com.example.employeegradle.valition.Groups.Second;
import com.example.employeegradle.valition.Groups.Third;

@GroupSequence({Default.class,First.class,Second.class,Third.class})
public interface Groups {
    public interface First{}
    public interface Second{}
    public interface Third{}
}