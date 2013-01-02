/*
 * Created on Nov 23, 2009
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * 
 * Copyright @2009-2013 the original author or authors.
 */
package org.fest.reflect.beanproperty;

import static org.fest.util.Preconditions.checkNotNull;
import static org.fest.util.Preconditions.checkNotNullOrEmpty;

import javax.annotation.Nonnull;

import org.fest.reflect.exception.ReflectionError;
import org.fest.reflect.reference.TypeRef;

/**
 * Holds the type of the property to access via Bean Introspection, preserving generic types that otherwise would be
 * lost due to erasure.
 * 
 * @param <T> the type of the property.
 * @author Alex Ruiz
 * @since 1.2
 */
public class PropertyTypeRef<T> {
  private final String propertyName;
  private final TypeRef<T> value;

  PropertyTypeRef(@Nonnull String propertyName, @Nonnull TypeRef<T> type) {
    this.propertyName = checkNotNullOrEmpty(propertyName);
    this.value = checkNotNull(type);
  }

  /**
   * <p>
   * Creates a new
   * <a href="http://docs.oracle.com/javase/tutorial/javabeans/index.html" target="_blank">JavaBeans</a> property
   * accessor.
   * </p>
   *
   * <p>
   * Examples demonstrating usage of the fluent interface:
   *
   * <pre>
   * // import static {@link org.fest.reflect.core.Reflection#property(String) org.fest.reflect.core.Reflection.property};
   *
   * // Retrieves the value of the property "name"
   * String name = {@link org.fest.reflect.core.Reflection#property(String) property}("name").{@link PropertyName#ofType(Class) ofType}(String.class).{@link PropertyType#in(Object) in}(person).{@link PropertyAccessor#get() get}();
   * 
   * // Sets the value of the property "name" to "Yoda"
   * {@link org.fest.reflect.core.Reflection#property(String) property}("name").{@link PropertyName#ofType(Class) ofType}(String.class).{@link PropertyType#in(Object) in}(person).{@link PropertyAccessor#set(Object) set}("Yoda");
   * 
   * // Retrieves the value of the property "powers"
   * List&lt;String&gt; powers = {@link org.fest.reflect.core.Reflection#property(String) property}("powers").{@link PropertyName#ofType(org.fest.reflect.reference.TypeRef) ofType}(new {@link org.fest.reflect.reference.TypeRef TypeRef}&lt;List&lt;String&gt;&gt;() {}).{@link PropertyTypeRef#in(Object) in}(jedi).{@link PropertyAccessor#get() get}();
   *
   * // Sets the value of the property "powers"
   * List&lt;String&gt; powers = new ArrayList&lt;String&gt;();
   * powers.add("heal");
   * {@link org.fest.reflect.core.Reflection#property(String) property}("powers").{@link PropertyName#ofType(org.fest.reflect.reference.TypeRef) ofType}(new {@link org.fest.reflect.reference.TypeRef TypeRef}&lt;List&lt;String&gt;&gt;() {}).{@link PropertyTypeRef#in(Object) in}(jedi).{@link PropertyAccessor#set(Object) set}(powers);
   * </pre>
   * </p>
   *
   * @param target the object containing the property to access.
   * @return the created property accessor.
   * @throws NullPointerException if the given target is {@code null}.
   * @throws ReflectionError if a property with a matching name and type cannot be found.
   */
  public @Nonnull PropertyAccessor<T> in(@Nonnull Object target) {
    return new PropertyAccessor<T>(checkNotNullOrEmpty(propertyName), value.rawType(), target);
  }
}