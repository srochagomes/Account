package br.com.account.account.type.exception

import java.lang.RuntimeException

class BusinessException(message:String): RuntimeException(message)  {
}