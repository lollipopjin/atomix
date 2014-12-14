/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.kuujo.copycat.collections;

import net.kuujo.copycat.StateMachine;
import net.kuujo.copycat.cluster.ClusterConfig;
import net.kuujo.copycat.collections.internal.collection.AsyncSetState;
import net.kuujo.copycat.collections.internal.collection.DefaultAsyncSet;
import net.kuujo.copycat.collections.internal.collection.DefaultAsyncSetState;
import net.kuujo.copycat.internal.util.Services;
import net.kuujo.copycat.spi.ExecutionContext;

/**
 * Asynchronous set.
 *
 * @author <a href="http://github.com/kuujo">Jordan Halterman</a>
 *
 * @param <T> The set data type.
 */
public interface AsyncSet<T> extends AsyncCollection<T> {

  /**
   * Creates a new asynchronous set.
   *
   * @param name The asynchronous set name.
   * @param <T> The set data type.
   * @return The asynchronous set.
   */
  static <T> AsyncSet<T> create(String name) {
    return create(name, Services.load("copycat.cluster", ClusterConfig.class), Services.load(String.format("copycat.set.%s", name), AsyncSetConfig.class), ExecutionContext.create());
  }

  /**
   * Creates a new asynchronous set.
   *
   * @param name The asynchronous set name.
   * @param cluster The cluster configuration.
   * @param <T> The set data type.
   * @return The asynchronous set.
   */
  @SuppressWarnings("unchecked")
  static <T> AsyncSet<T> create(String name, ClusterConfig cluster) {
    return create(name, cluster, Services.load(String.format("copycat.set.%s", name), AsyncSetConfig.class), ExecutionContext.create());
  }

  /**
   * Creates a new asynchronous set.
   *
   * @param name The asynchronous set name.
   * @param config The set configuration.
   * @param <T> The set data type.
   * @return The asynchronous set.
   */
  @SuppressWarnings("unchecked")
  static <T> AsyncSet<T> create(String name, AsyncSetConfig config) {
    return create(name, Services.load("copycat.cluster", ClusterConfig.class), config, ExecutionContext.create());
  }

  /**
   * Creates a new asynchronous set.
   *
   * @param name The asynchronous set name.
   * @param context The user execution context.
   * @param <T> The set data type.
   * @return The asynchronous set.
   */
  @SuppressWarnings("unchecked")
  static <T> AsyncSet<T> create(String name, ExecutionContext context) {
    return create(name, Services.load("copycat.cluster", ClusterConfig.class), Services.load(String.format("copycat.set.%s", name), AsyncSetConfig.class), context);
  }

  /**
   * Creates a new asynchronous set.
   *
   * @param name The asynchronous set name.
   * @param cluster The cluster configuration.
   * @param config The set configuration.
   * @param <T> The set data type.
   * @return The asynchronous set.
   */
  @SuppressWarnings("unchecked")
  static <T> AsyncSet<T> create(String name, ClusterConfig cluster, AsyncSetConfig config) {
    return create(name, cluster, config, ExecutionContext.create());
  }

  /**
   * Creates a new asynchronous set.
   *
   * @param name The asynchronous set name.
   * @param cluster The cluster configuration.
   * @param context The user execution context.
   * @param <T> The set data type.
   * @return The asynchronous set.
   */
  @SuppressWarnings("unchecked")
  static <T> AsyncSet<T> create(String name, ClusterConfig cluster, ExecutionContext context) {
    return create(name, cluster, Services.load(String.format("copycat.set.%s", name), AsyncSetConfig.class), context);
  }

  /**
   * Creates a new asynchronous set.
   *
   * @param name The asynchronous set name.
   * @param config The set configuration.
   * @param context The user execution context.
   * @param <T> The set data type.
   * @return The asynchronous set.
   */
  @SuppressWarnings("unchecked")
  static <T> AsyncSet<T> create(String name, AsyncSetConfig config, ExecutionContext context) {
    return create(name, Services.load("copycat.cluster", ClusterConfig.class), config, context);
  }

  /**
   * Creates a new asynchronous set.
   *
   * @param name The asynchronous set name.
   * @param cluster The cluster configuration.
   * @param config The set configuration.
   * @param context The user execution context.
   * @param <T> The set data type.
   * @return The asynchronous set.
   */
  @SuppressWarnings("unchecked")
  static <T> AsyncSet<T> create(String name, ClusterConfig cluster, AsyncSetConfig config, ExecutionContext context) {
    return new DefaultAsyncSet(StateMachine.create(name, AsyncSetState.class, new DefaultAsyncSetState<>(), cluster, config, context));
  }

}