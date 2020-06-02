package net.benfro.lab.translationfrontend.security

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, Long> {
    fun findByUsername(username: String): User
}

@Repository
interface RoleRepository : CrudRepository<Role, Long> {

}

@Repository
interface AuthorityRepository : CrudRepository<Authority, Long> {

}

