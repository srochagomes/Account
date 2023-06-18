package br.com.account.account.types.exceptions

import java.lang.RuntimeException

class BusinessException(message:String): RuntimeException(message)  {
}