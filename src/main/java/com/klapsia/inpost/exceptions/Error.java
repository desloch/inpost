package com.klapsia.inpost.exceptions;

public record Error(int httpStatus, String errorDescription) {
}
