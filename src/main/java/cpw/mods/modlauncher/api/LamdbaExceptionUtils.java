/*
 * ModLauncher - for launching Java programs with in-flight transformation ability.
 *
 *     Copyright (C) 2017-2022 cpw
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser General Public License as published by
 *     the Free Software Foundation, version 3 of the License.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package cpw.mods.modlauncher.api;

import java.util.function.*;

/**
 * From stackoverflow: https://stackoverflow.com/a/27644392
 * @deprecated Use {@link LambdaExceptionUtils}
 */
@Deprecated(forRemoval = true)
public class LamdbaExceptionUtils {

    /**
     * .forEach(rethrowConsumer(name -> System.out.println(Class.forName(name)))); or .forEach(rethrowConsumer(ClassNameUtil::println));
     */
    public static <T, E extends Exception> Consumer<T> rethrowConsumer(Consumer_WithExceptions<T, E> consumer) {
        return LambdaExceptionUtils.rethrowConsumer(consumer::accept);
    }

    public static <T, U, E extends Exception> BiConsumer<T, U> rethrowBiConsumer(BiConsumer_WithExceptions<T, U, E> biConsumer) {
        return LambdaExceptionUtils.rethrowBiConsumer(biConsumer::accept);
    }

    /**
     * .map(rethrowFunction(name -> Class.forName(name))) or .map(rethrowFunction(Class::forName))
     */
    public static <T, R, E extends Exception> Function<T, R> rethrowFunction(Function_WithExceptions<T, R, E> function) {
        return LambdaExceptionUtils.rethrowFunction(function::apply);
    }

    /**
     * rethrowSupplier(() -> new StringJoiner(new String(new byte[]{77, 97, 114, 107}, "UTF-8"))),
     */
    public static <T, E extends Exception> Supplier<T> rethrowSupplier(Supplier_WithExceptions<T, E> function) {
        return LambdaExceptionUtils.rethrowSupplier(function::get);
    }

    /**
     * uncheck(() -> Class.forName("xxx"));
     */
    public static void uncheck(Runnable_WithExceptions t) {
        LambdaExceptionUtils.uncheck(t::run);
    }

    /**
     * uncheck(() -> Class.forName("xxx"));
     */
    public static <R, E extends Exception> R uncheck(Supplier_WithExceptions<R, E> supplier) {
        return LambdaExceptionUtils.uncheck(supplier::get);
    }

    /**
     * uncheck(Class::forName, "xxx");
     */
    public static <T, R, E extends Exception> R uncheck(Function_WithExceptions<T, R, E> function, T t) {
        return LambdaExceptionUtils.uncheck(function::apply, t);
    }

    @FunctionalInterface
    @Deprecated(forRemoval = true)
    public interface Consumer_WithExceptions<T, E extends Exception> {
        void accept(T t) throws E;
    }

    @FunctionalInterface
    @Deprecated(forRemoval = true)
    public interface BiConsumer_WithExceptions<T, U, E extends Exception> {
        void accept(T t, U u) throws E;
    }

    @FunctionalInterface
    @Deprecated(forRemoval = true)
    public interface Function_WithExceptions<T, R, E extends Exception> {
        R apply(T t) throws E;
    }

    @FunctionalInterface
    @Deprecated(forRemoval = true)
    public interface Supplier_WithExceptions<T, E extends Exception> {
        T get() throws E;
    }

    @FunctionalInterface
    @Deprecated(forRemoval = true)
    public interface Runnable_WithExceptions<E extends Exception> {
        void run() throws E;
    }
}
