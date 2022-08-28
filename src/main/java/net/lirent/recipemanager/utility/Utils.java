package net.lirent.recipemanager.utility;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.FeatureDescriptor;
import java.util.stream.Stream;

/**
 * <p>
 * Utility methods are always nice to have and grouped in the same class.
 * </p>
 *
 * @author Lirent
 */
public class Utils {

    /**
     * Return all <b>null<b/> property of the object.
     * @param object obj
     * @return list of fields
     */
        public static String[] getNullPropertyNames(Object object) {
            final BeanWrapper wrappedSource = new BeanWrapperImpl(object);
            return Stream.of(wrappedSource.getPropertyDescriptors())
                    .map(FeatureDescriptor::getName)
                    .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null)
                    .toArray(String[]::new);
        }

    }
