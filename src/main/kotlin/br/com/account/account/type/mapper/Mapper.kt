package br.com.account.account.type.mapper

interface Mapper<ENTRY,RETURN> {
    fun from(entry: ENTRY): RETURN
}