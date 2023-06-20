package br.com.account.account.type.exception

import java.lang.RuntimeException

class NotFoundException(message: String?) : RuntimeException(message) {
}