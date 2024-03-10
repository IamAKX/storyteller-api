package com.storyteller.utilities;

import com.storyteller.entities.*;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

public class ServiceHelper {

    public static String[] getNullPropertyNames(User user) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(user);
        List<String> nullPropertyNames = new ArrayList<>();
        for (PropertyDescriptor propertyDescriptor : beanWrapper.getPropertyDescriptors()) {
            String propertyName = propertyDescriptor.getName();
            Object propertyValue = beanWrapper.getPropertyValue(propertyName);
            // Exclude properties with numeric types from being considered for updating
            if (propertyValue == null || (propertyValue instanceof String && ((String) propertyValue).isEmpty())|| (long.class.isAssignableFrom(propertyDescriptor.getPropertyType()))) {
                nullPropertyNames.add(propertyName);
            }
        }
        return nullPropertyNames.toArray(new String[0]);
    }

    public static String[] getNullPropertyNames(Category category) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(category);
        List<String> nullPropertyNames = new ArrayList<>();
        for (PropertyDescriptor propertyDescriptor : beanWrapper.getPropertyDescriptors()) {
            String propertyName = propertyDescriptor.getName();
            Object propertyValue = beanWrapper.getPropertyValue(propertyName);
            // Exclude properties with numeric types from being considered for updating
            if (propertyValue == null || (propertyValue instanceof String && ((String) propertyValue).isEmpty())|| (long.class.isAssignableFrom(propertyDescriptor.getPropertyType()))) {
                nullPropertyNames.add(propertyName);
            }
        }
        return nullPropertyNames.toArray(new String[0]);
    }

    public static String[] getNullPropertyNames(Author author) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(author);
        List<String> nullPropertyNames = new ArrayList<>();
        for (PropertyDescriptor propertyDescriptor : beanWrapper.getPropertyDescriptors()) {
            String propertyName = propertyDescriptor.getName();
            Object propertyValue = beanWrapper.getPropertyValue(propertyName);
            // Exclude properties with numeric types from being considered for updating
            if (propertyValue == null || (propertyValue instanceof String && ((String) propertyValue).isEmpty())|| (long.class.isAssignableFrom(propertyDescriptor.getPropertyType()))) {
                nullPropertyNames.add(propertyName);
            }
        }
        return nullPropertyNames.toArray(new String[0]);
    }

    public static String[] getNullPropertyNames(Subscription subscription) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(subscription);
        List<String> nullPropertyNames = new ArrayList<>();
        for (PropertyDescriptor propertyDescriptor : beanWrapper.getPropertyDescriptors()) {
            String propertyName = propertyDescriptor.getName();
            Object propertyValue = beanWrapper.getPropertyValue(propertyName);
            // Exclude properties with numeric types from being considered for updating
            if (propertyValue == null || (propertyValue instanceof String && ((String) propertyValue).isEmpty())|| (propertyName.equals("id"))) {
                nullPropertyNames.add(propertyName);
            }
        }
        return nullPropertyNames.toArray(new String[0]);
    }

    public static String[] getNullPropertyNames(Story story) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(story);
        List<String> nullPropertyNames = new ArrayList<>();
        for (PropertyDescriptor propertyDescriptor : beanWrapper.getPropertyDescriptors()) {
            String propertyName = propertyDescriptor.getName();
            Object propertyValue = beanWrapper.getPropertyValue(propertyName);
            // Exclude properties with numeric types from being considered for updating
            if (propertyValue == null || (propertyValue instanceof String && ((String) propertyValue).isEmpty())|| (long.class.isAssignableFrom(propertyDescriptor.getPropertyType()))) {
                nullPropertyNames.add(propertyName);
            }
        }
        return nullPropertyNames.toArray(new String[0]);
    }

    public static String[] getNullPropertyNames(StoryChat story) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(story);
        List<String> nullPropertyNames = new ArrayList<>();
        for (PropertyDescriptor propertyDescriptor : beanWrapper.getPropertyDescriptors()) {
            String propertyName = propertyDescriptor.getName();
            Object propertyValue = beanWrapper.getPropertyValue(propertyName);
            // Exclude properties with numeric types from being considered for updating
            if (propertyValue == null || (propertyValue instanceof String && ((String) propertyValue).isEmpty())|| (long.class.isAssignableFrom(propertyDescriptor.getPropertyType()))) {
                nullPropertyNames.add(propertyName);
            }
        }
        return nullPropertyNames.toArray(new String[0]);
    }
}
