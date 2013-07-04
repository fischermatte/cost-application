package com.unisys.ch.jax.costserver.service;

import java.util.*;

import com.unisys.ch.jax.costserver.model.Project;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.stereotype.Service;

import com.unisys.ch.jax.costserver.model.Cost;

@Service
public class CostServiceInMemory implements CostService {

    private Long NUMBER_OF_ENTRIES_TO_GENERATE = 100L;
    private static final Calendar INITIAL_DATE = Calendar.getInstance();
    {
        INITIAL_DATE.set(Calendar.YEAR, 2013);
        INITIAL_DATE.set(Calendar.MONTH, 0);
        INITIAL_DATE.set(Calendar.DAY_OF_MONTH, 1);
        INITIAL_DATE.set(Calendar.HOUR, 18);
        INITIAL_DATE.set(Calendar.MINUTE, 00);
    }

    private TreeMap<Long, Cost> cachedToDos;

    public CostServiceInMemory() {
        cachedToDos = new TreeMap<Long, Cost>();
        generateDummyEntities();
    }
    public Cost findById(Long id) {
        return cachedToDos.get(id);
    }

    public Collection<Cost> findAll() {
        return cachedToDos.values();
    }

    public Cost save(Cost todo) {
        if (todo.getId() == null) {
            todo.setId(++NUMBER_OF_ENTRIES_TO_GENERATE);
        }
        return cachedToDos.put(todo.getId(), todo);
    }

    public void delete(Long id) {
        cachedToDos.remove(id);
    }

    @Override
    public Collection<Cost> findAll(int start, int amount) {
        ArrayList<Cost> retVal = new ArrayList<Cost>(findAll());
        Collections.sort(retVal, new PropertyComparator("workDay",true,false));
        if (start > retVal.size()) {
            return new ArrayList<Cost>(0);
        } else {
            return retVal.subList(start,
                    Math.min(start + amount, retVal.size()));
        }
    }

    private void generateDummyEntities() {
        for (long x = 1; x <= NUMBER_OF_ENTRIES_TO_GENERATE; x++) {
            Cost todo = new Cost(x, "Aufwand " + x, "Siehe JIRA-" + x, createDate(x), 8.0, Project.BAFU);
            cachedToDos.put(x, todo);
        }
    }

    private Calendar createDate(long x) {
        Calendar date = Calendar.getInstance();
        date.setTime(INITIAL_DATE.getTime());
        date.add(Calendar.DAY_OF_YEAR, (int) x);
        return date;
    }

}
