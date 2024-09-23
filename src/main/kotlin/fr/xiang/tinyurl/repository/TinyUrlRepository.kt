package fr.xiang.tinyurl.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TinyUrlRepository : CrudRepository<TinyUrl, String>
