// UserNotFoundException.java
package org.demo1.exception;

public class PermissionNotFoundException extends RuntimeException {
    public PermissionNotFoundException(String message) {
        super(message);
    }
}
